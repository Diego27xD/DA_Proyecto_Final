/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTIL;

import BEAN.CabCv;
import BEAN.ExperienciaAcademica;
import BEAN.ExperienciaLaboral;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Vector;

/**
 *
 * @author dflor
 */
public class CvValidation {

    public static String texto(String valor) {
        return valor == null ? "" : valor.trim();
    }

    public static String fecha(String valor, boolean obligatoria, String campo, String origen, Vector<String> errores) {
        valor = texto(valor);
        if (valor.isEmpty()) {
            if (obligatoria) {
                errores.addElement(origen + ": falta " + campo + ".");
            }
            return "";
        }
        try {
            LocalDate fecha;
            if (valor.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                fecha = LocalDate.parse(valor);
            } else if (valor.matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
                fecha = LocalDate.parse(valor, DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));
            } else {
                throw new IllegalArgumentException();
            }
            if (fecha.getYear() < 1 || fecha.getYear() > 9999) {
                throw new IllegalArgumentException();
            }
            return fecha.toString();
        } catch (Exception e) {
            errores.addElement(origen + ": " + campo + " no es una fecha real. Use yyyy-MM-dd o dd/MM/yyyy.");
            return valor;
        }
    }

    public static boolean existe(int id, Vector<Vector> opciones) {
        for (int i = 0; i < opciones.size(); i++) {
            if (Integer.parseInt(opciones.get(i).get(0).toString()) == id) {
                return true;
            }
        }
        return false;
    }

    public static int referencia(String valor, Vector<Vector> opciones, String campo, int fila, Vector<String> errores) {
        valor = texto(valor);
        int id = 0;
        if (valor.matches("[0-9]+(\\s*-.*)?")) {
            try {
                id = Integer.parseInt(valor.split("\\s*-", 2)[0].trim());
            } catch (Exception e) {
                id = 0;
            }
            if (existe(id, opciones)) {
                return id;
            }
        } else {
            int coincidencias = 0;
            for (int i = 0; i < opciones.size(); i++) {
                if (valor.equalsIgnoreCase(opciones.get(i).get(1).toString().trim())) {
                    id = Integer.parseInt(opciones.get(i).get(0).toString());
                    coincidencias++;
                }
            }
            if (coincidencias == 1) {
                return id;
            }
            if (coincidencias > 1) {
                errores.addElement("Fila " + fila + ": " + campo + " no válido. Seleccione la opción con su código.");
                return 0;
            }
        }
        errores.addElement("Fila " + fila + ": " + campo + " no corresponde a un registro existente de la base de datos.");
        return 0;
    }

    public static boolean mismaLaboral(ExperienciaLaboral a, ExperienciaLaboral b) {
        return a.getIdEmpresa() == b.getIdEmpresa() && texto(a.getFech_Ini()).equals(texto(b.getFech_Ini()))
                && texto(a.getFech_Fin()).equals(texto(b.getFech_Fin())) && texto(a.getDescRol()).equals(texto(b.getDescRol()));
    }

    public static boolean mismaAcademica(ExperienciaAcademica a, ExperienciaAcademica b) {
        return a.getIdInstitucion() == b.getIdInstitucion() && texto(a.getFech_Ini()).equals(texto(b.getFech_Ini()))
                && texto(a.getFech_Fin()).equals(texto(b.getFech_Fin())) && texto(a.getEstadoAvance()).equals(texto(b.getEstadoAvance()))
                && texto(a.getGradoAcad()).equals(texto(b.getGradoAcad()));
    }

    public static void validar(CabCv cv, Vector<Vector> empleados, Vector<Vector> empresas, Vector<Vector> instituciones, Vector<String> errores) {
        if (!existe(cv.getIdEmpleado(), empleados)) {
            errores.addElement("El empleado del CV no existe.");
        }
        for (int i = 0; i < cv.getExperienciasLaborales().size(); i++) {
            ExperienciaLaboral exp = cv.getExperienciasLaborales().get(i);
            String origen = exp.getFilaOrigen() > 0 ? "Fila " + exp.getFilaOrigen() + ", laboral" : "Experiencia laboral " + (i + 1);
            if (!existe(exp.getIdEmpresa(), empresas)) {
                errores.addElement(origen + ": empresa inexistente.");
            }
            exp.setFech_Ini(fecha(exp.getFech_Ini(), true, "fecha inicio laboral", origen, errores));
            exp.setFech_Fin(fecha(exp.getFech_Fin(), false, "fecha final laboral", origen, errores));
            exp.setDescRol(texto(exp.getDescRol()));
            if (exp.getDescRol().length() > 150) {
                errores.addElement(origen + ": descripción de rol supera 150 caracteres.");
            }
            if (exp.getFech_Ini().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}") && exp.getFech_Fin().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}") && exp.getFech_Fin().compareTo(exp.getFech_Ini()) < 0) {
                errores.addElement(origen + ": la fecha final laboral es anterior a la inicial.");
            }
            for (int j = 0; j < i; j++) {
                ExperienciaLaboral anterior = cv.getExperienciasLaborales().get(j);
                if (anterior.getIdEmpresa() == exp.getIdEmpresa() && texto(anterior.getFech_Ini()).equals(exp.getFech_Ini())) {
                    errores.addElement(origen + ": existe otra experiencia con la misma empresa y fecha inicial.");
                }
            }
        }
        for (int i = 0; i < cv.getExperienciasAcademicas().size(); i++) {
            ExperienciaAcademica exp = cv.getExperienciasAcademicas().get(i);
            String origen = exp.getFilaOrigen() > 0 ? "Fila " + exp.getFilaOrigen() + ", académica" : "Experiencia académica " + (i + 1);
            if (!existe(exp.getIdInstitucion(), instituciones)) {
                errores.addElement(origen + ": institución inexistente.");
            }
            exp.setFech_Ini(fecha(exp.getFech_Ini(), true, "fecha inicio académica", origen, errores));
            exp.setFech_Fin(fecha(exp.getFech_Fin(), false, "fecha fin académica", origen, errores));
            exp.setEstadoAvance(texto(exp.getEstadoAvance()));
            exp.setGradoAcad(texto(exp.getGradoAcad()));
            if (exp.getEstadoAvance().length() > 50) {
                errores.addElement(origen + ": estado de avance supera 50 caracteres.");
            }
            if (exp.getGradoAcad().length() > 100) {
                errores.addElement(origen + ": grado académico supera 100 caracteres.");
            }
            if (exp.getFech_Ini().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}") && exp.getFech_Fin().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}") && exp.getFech_Fin().compareTo(exp.getFech_Ini()) < 0) {
                errores.addElement(origen + ": la fecha fin académica es anterior a la inicial.");
            }
            for (int j = 0; j < i; j++) {
                ExperienciaAcademica anterior = cv.getExperienciasAcademicas().get(j);
                if (anterior.getIdInstitucion() == exp.getIdInstitucion() && texto(anterior.getFech_Ini()).equals(exp.getFech_Ini())) {
                    errores.addElement(origen + ": existe otra experiencia con la misma institución y fecha inicial.");
                }
            }
        }
    }

    public static String errores(Vector<String> errores) {
        StringBuilder mensaje = new StringBuilder();
        for (int i = 0; i < errores.size(); i++) {
            mensaje.append(errores.get(i)).append('\n');
        }
        return mensaje.toString();
    }
}
