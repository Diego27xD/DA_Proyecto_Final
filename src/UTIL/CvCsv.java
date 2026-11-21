package UTIL;

import BEAN.CabCv;
import BEAN.ExperienciaLaboral;
import BEAN.ExperienciaAcademica;
import BEAN.FilaCsv;
import BEAN.ResultadoCargaCsv;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Vector;

public class CvCsv {

    public static final String[] ENCABEZADOS = {"EMPLEADO", "EMPRESA", "FECHA INICIO EMPRESA", "FECHA FINAL EMPRESA", "DESCRIPCION DE ROL", "INSTITUCION", "FECHA INICIO", "FECHA FIN", "ESTADO AVANCE", "GRADO ACADEMICO"};

    private static String leerTexto(File archivo, boolean utf8) throws Exception {
        StringBuilder texto = new StringBuilder();
        InputStreamReader entrada = utf8
                ? new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT))
                : new InputStreamReader(new FileInputStream(archivo), "windows-1252");
        try (BufferedReader reader = new BufferedReader(entrada)) {
            char[] buffer = new char[4096];
            int leidos;
            while ((leidos = reader.read(buffer)) != -1) {
                texto.append(buffer, 0, leidos);
            }
        }
        return texto.toString();
    }

    public static Vector<FilaCsv> leer(File archivo) throws Exception {
        if (archivo.length() > 20L * 1024 * 1024) {
            throw new IllegalArgumentException("El CSV supera 20 MB. Divida el archivo antes de importarlo.");
        }
        String texto;
        try {
            texto = leerTexto(archivo, true);
        } catch (java.nio.charset.CharacterCodingException e) {
            texto = leerTexto(archivo, false);
        }
        if (texto.startsWith("\uFEFF")) {
            texto = texto.substring(1);
        }
        if (texto.startsWith("PK\u0003\u0004")) {
            throw new IllegalArgumentException("El archivo es un Excel, no un CSV. Guarde la hoja Plantilla descargable como CSV UTF-8.");
        }
        char separador = ',';
        int primeraFila = 1;
        if (texto.toLowerCase(java.util.Locale.ROOT).startsWith("sep=")) {
            int fin = texto.indexOf('\n');
            String linea = (fin < 0 ? texto : texto.substring(0, fin)).trim();
            if (!linea.equalsIgnoreCase("sep=;") && !linea.equalsIgnoreCase("sep=,")) {
                throw new IllegalArgumentException("El separador debe ser coma o punto y coma.");
            }
            separador = linea.charAt(4);
            texto = fin < 0 ? "" : texto.substring(fin + 1);
            primeraFila = 2;
        } else {
            int comas = 0, puntos = 0;
            boolean comillas = false, contenido = false;
            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i);
                if (c == '"') {
                    comillas = !comillas;
                }
                if (!comillas && (c == '\r' || c == '\n')) {
                    if (contenido || comas > 0 || puntos > 0) {
                        break;
                    }
                    continue;
                }
                if (!Character.isWhitespace(c) && c != '"') {
                    contenido = true;
                }
                if (!comillas && c == ',') {
                    comas++;
                }
                if (!comillas && c == ';') {
                    puntos++;
                }
            }
            if (puntos > comas) {
                separador = ';';
            }
        }
        return parsear(texto, separador, primeraFila);
    }

    public static Vector<FilaCsv> parsear(String texto, char separador, int primeraFila) {
        Vector<FilaCsv> filas = new Vector<FilaCsv>();
        Vector<String> campos = new Vector<String>();
        StringBuilder campo = new StringBuilder();
        int estado = 0, linea = primeraFila, inicio = primeraFila;
        for (int i = 0; i <= texto.length(); i++) {
            boolean fin = i == texto.length();
            char c = fin ? '\n' : texto.charAt(i);
            if (estado == 1) {
                if (fin) {
                    throw new IllegalArgumentException("Fila " + inicio + ": comillas sin cerrar.");
                }
                if (c == '"') {
                    if (i + 1 < texto.length() && texto.charAt(i + 1) == '"') {
                        campo.append('"');
                        i++;
                    } else {
                        estado = 2;
                    }
                } else if (c == '\r' || c == '\n') {
                    if (c == '\r' && i + 1 < texto.length() && texto.charAt(i + 1) == '\n') {
                        i++;
                    }
                    campo.append('\n');
                    linea++;
                } else {
                    campo.append(c);
                }
                continue;
            }
            if (c == separador || c == '\r' || c == '\n') {
                campos.addElement(campo.toString().trim());
                campo.setLength(0);
                estado = 0;
                if (c != separador) {
                    if (!fin && c == '\r' && i + 1 < texto.length() && texto.charAt(i + 1) == '\n') {
                        i++;
                    }
                    boolean vacia = true;
                    for (int j = 0; j < campos.size(); j++) {
                        if (!campos.get(j).isEmpty()) {
                            vacia = false;
                        }
                    }
                    if (!vacia) {
                        filas.addElement(new FilaCsv(inicio, campos.toArray(new String[campos.size()])));
                    }
                    if (filas.size() > 50002) {
                        throw new IllegalArgumentException("El CSV supera 50.000 filas de datos. Divídalo en archivos más pequeños.");
                    }
                    campos.clear();
                    linea++;
                    inicio = linea;
                }
            } else if (estado == 2) {
                if (c != ' ' && c != '\t') {
                    throw new IllegalArgumentException("Fila " + linea + ": hay texto después del cierre de comillas.");
                }
            } else if (c == '"') {
                if (campo.length() != 0) {
                    throw new IllegalArgumentException("Fila " + linea + ": comillas dentro de un campo sin entrecomillar.");
                }
                estado = 1;
            } else {
                campo.append(c);
            }
        }
        return filas;
    }

    private static String normaliza(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "").trim().replaceAll("\\s+", " ").toUpperCase(java.util.Locale.ROOT);
    }

    public static ResultadoCargaCsv preparar(Vector<FilaCsv> filas, Vector<Vector> empleados, Vector<Vector> empresas, Vector<Vector> instituciones) {
        ResultadoCargaCsv lote = new ResultadoCargaCsv();
        if (filas.isEmpty()) {
            lote.getErrores().addElement("El CSV está vacío.");
            return lote;
        }
        int encabezado = 0;
        String[] primera = filas.get(0).getDatos();
        if (primera.length > 5 && normaliza(primera[1]).equals("EXPERIENCIA LABORAL") && normaliza(primera[5]).equals("EXPERIENCIA ACADEMICA")) {
            encabezado = 1;
        }
        if (encabezado >= filas.size()) {
            lote.getErrores().addElement("Falta la fila de encabezados de la plantilla.");
            return lote;
        }
        String[] titulos = filas.get(encabezado).getDatos();
        if (titulos.length != 10) {
            lote.getErrores().addElement("Fila " + filas.get(encabezado).getNumero() + ": se esperaban exactamente 10 columnas.");
            return lote;
        }
        for (int i = 0; i < 10; i++) {
            if (!normaliza(titulos[i]).equals(ENCABEZADOS[i]) && !(i == 2 && normaliza(titulos[i]).equals("FECHA INICIO")) && !(i == 3 && normaliza(titulos[i]).equals("FECHA FINAL"))) {
                lote.getErrores().addElement("Columna " + (i + 1) + ": se esperaba " + ENCABEZADOS[i] + ". Conserve el orden de la plantilla.");
            }
        }
        if (!lote.getErrores().isEmpty()) {
            return lote;
        }
        if (filas.size() - encabezado - 1 > 50000) {
            lote.getErrores().addElement("El CSV supera 50.000 filas de datos. Divídalo en archivos más pequeños.");
            return lote;
        }
        for (int i = encabezado + 1; i < filas.size(); i++) {
            FilaCsv fila = filas.get(i);
            String[] d = fila.getDatos();
            int n = fila.getNumero();
            if (d.length != 10) {
                lote.getErrores().addElement("Fila " + n + ": se esperaban 10 columnas; hay " + d.length + ".");
                continue;
            }
            int id = CvValidation.referencia(d[0], empleados, "EMPLEADO", n, lote.getErrores());
            if (id == 0) {
                continue;
            }
            CabCv cv = null;
            for (int j = 0; j < lote.getCvs().size(); j++) {
                if (lote.getCvs().get(j).getIdEmpleado() == id) {
                    cv = lote.getCvs().get(j);
                }
            }
            if (cv == null) {
                cv = new CabCv();
                cv.setIdEmpleado(id);
                lote.getCvs().addElement(cv);
            }
            boolean laboral = false, academica = false;
            for (int j = 1; j <= 4; j++) {
                if (!d[j].isEmpty()) {
                    laboral = true;
                }
            }
            for (int j = 5; j <= 9; j++) {
                if (!d[j].isEmpty()) {
                    academica = true;
                }
            }
            if (laboral) {
                ExperienciaLaboral exp = new ExperienciaLaboral();
                exp.setIdEmpresa(CvValidation.referencia(d[1], empresas, "EMPRESA", n, lote.getErrores()));
                exp.setFech_Ini(CvValidation.fecha(d[2], true, "FECHA INICIO laboral", "Fila " + n, lote.getErrores()));
                exp.setFech_Fin(CvValidation.fecha(d[3], false, "FECHA FINAL laboral", "Fila " + n, lote.getErrores()));
                exp.setDescRol(d[4]);
                exp.setFilaOrigen(n);
                boolean repetida = false;
                for (int j = 0; j < cv.getExperienciasLaborales().size(); j++) {
                    if (CvValidation.mismaLaboral(cv.getExperienciasLaborales().get(j), exp)) {
                        repetida = true;
                    }
                }
                if (repetida) {
                    lote.setRepetidas(lote.getRepetidas() + 1);
                } else {
                    cv.getExperienciasLaborales().addElement(exp);
                }
            }
            if (academica) {
                ExperienciaAcademica exp = new ExperienciaAcademica();
                exp.setIdInstitucion(CvValidation.referencia(d[5], instituciones, "INSTITUCION", n, lote.getErrores()));
                exp.setFech_Ini(CvValidation.fecha(d[6], true, "FECHA INICIO académica", "Fila " + n, lote.getErrores()));
                exp.setFech_Fin(CvValidation.fecha(d[7], false, "FECHA FIN académica", "Fila " + n, lote.getErrores()));
                exp.setEstadoAvance(d[8]);
                exp.setGradoAcad(d[9]);
                exp.setFilaOrigen(n);
                boolean repetida = false;
                for (int j = 0; j < cv.getExperienciasAcademicas().size(); j++) {
                    if (CvValidation.mismaAcademica(cv.getExperienciasAcademicas().get(j), exp)) {
                        repetida = true;
                    }
                }
                if (repetida) {
                    lote.setRepetidas(lote.getRepetidas() + 1);
                } else {
                    cv.getExperienciasAcademicas().addElement(exp);
                }
            }
        }
        if (lote.getCvs().isEmpty()) {
            lote.getErrores().addElement("No hay filas de datos válidas para importar.");
        }
        for (int i = 0; i < lote.getCvs().size(); i++) {
            CvValidation.validar(lote.getCvs().get(i), empleados, empresas, instituciones, lote.getErrores());
        }
        return lote;
    }
}
