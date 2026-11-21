package UTIL;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 */
public class CvPlantilla {

    private static String xml(String texto) {
        StringBuilder limpio = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '\t' || c == '\r' || c == '\n' || c >= 32) {
                limpio.append(c);
            }
        }
        return limpio.toString().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    private static String celda(String ref, String texto, int estilo) {
        return "<c r=\"" + ref + "\" s=\"" + estilo + "\" t=\"inlineStr\"><is><t xml:space=\"preserve\">" + xml(texto) + "</t></is></c>";
    }

    private static void entrada(ZipOutputStream zip, String nombre, String texto) throws Exception {
        zip.putNextEntry(new ZipEntry(nombre));
        zip.write(texto.getBytes(StandardCharsets.UTF_8));
        zip.closeEntry();
    }

    private static String lista(String rango, String nombre) {
        return "<dataValidation type=\"list\" allowBlank=\"1\" showErrorMessage=\"1\" errorStyle=\"stop\" errorTitle=\"Seleccione una opción\" error=\"Use un registro de la lista de la base de datos.\" sqref=\"" + rango + "\"><formula1>" + nombre + "</formula1></dataValidation>";
    }

    private static String fecha(String columna, String inicio) {
        String formula = "OR(" + columna + "2=\"\",AND(ISNUMBER(" + columna + "2)," + columna + "2=INT(" + columna + "2)," + columna + "2&gt;=DATE(1900,1,1)," + columna + "2&lt;=DATE(9999,12,31)";
        if (inicio != null) {
            formula += ",ISNUMBER(" + inicio + "2)," + columna + "2&gt;=" + inicio + "2";
        }
        formula += "))";
        return "<dataValidation type=\"custom\" allowBlank=\"1\" showErrorMessage=\"1\" errorStyle=\"stop\" showInputMessage=\"1\" promptTitle=\"Fecha\" prompt=\"Ingrese una fecha real; se muestra yyyy-mm-dd. El fin no puede ser anterior al inicio.\" errorTitle=\"Fecha inválida\" error=\"Ingrese una fecha real. La fecha fin debe ser igual o posterior al inicio.\" sqref=\"" + columna + "2:" + columna + "10001\"><formula1>" + formula + "</formula1></dataValidation>";
    }

    private static String longitud(String columna, int maximo) {
        return "<dataValidation type=\"textLength\" operator=\"lessThanOrEqual\" allowBlank=\"1\" showErrorMessage=\"1\" errorStyle=\"stop\" errorTitle=\"Texto demasiado largo\" error=\"Máximo " + maximo + " caracteres.\" sqref=\"" + columna + "2:" + columna + "10001\"><formula1>" + maximo + "</formula1></dataValidation>";
    }

    public static byte[] generar(Vector<Vector> empleados, Vector<Vector> empresas, Vector<Vector> instituciones) throws Exception {
        ByteArrayOutputStream memoria = new ByteArrayOutputStream();
        try (ZipOutputStream zip = new ZipOutputStream(memoria)) {
            entrada(zip, "[Content_Types].xml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\"><Default Extension=\"rels\" ContentType=\"application/vnd.openxmlformats-package.relationships+xml\"/><Default Extension=\"xml\" ContentType=\"application/xml\"/><Override PartName=\"/xl/workbook.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml\"/><Override PartName=\"/xl/styles.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml\"/><Override PartName=\"/xl/worksheets/sheet1.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml\"/><Override PartName=\"/xl/worksheets/sheet2.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml\"/></Types>");
            entrada(zip, "_rels/.rels", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\"><Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument\" Target=\"xl/workbook.xml\"/></Relationships>");
            String nombres = "<definedName name=\"Empleados\">'Catalogos'!$A$2:$A$" + Math.max(2, empleados.size() + 1) + "</definedName><definedName name=\"Empresas\">'Catalogos'!$B$2:$B$" + Math.max(2, empresas.size() + 1) + "</definedName><definedName name=\"Instituciones\">'Catalogos'!$C$2:$C$" + Math.max(2, instituciones.size() + 1) + "</definedName>";
            entrada(zip, "xl/workbook.xml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><workbook xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"><bookViews><workbookView activeTab=\"0\"/></bookViews><sheets><sheet name=\"Plantilla descargable\" sheetId=\"1\" r:id=\"rId1\"/><sheet name=\"Catalogos\" sheetId=\"2\" state=\"hidden\" r:id=\"rId2\"/></sheets><definedNames>" + nombres + "</definedNames><calcPr calcId=\"0\" fullCalcOnLoad=\"1\"/></workbook>");
            entrada(zip, "xl/_rels/workbook.xml.rels", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\"><Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet\" Target=\"worksheets/sheet1.xml\"/><Relationship Id=\"rId2\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet\" Target=\"worksheets/sheet2.xml\"/><Relationship Id=\"rId3\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles\" Target=\"styles.xml\"/></Relationships>");
            entrada(zip, "xl/styles.xml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><styleSheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"><numFmts count=\"1\"><numFmt numFmtId=\"164\" formatCode=\"yyyy-mm-dd\"/></numFmts><fonts count=\"2\"><font><sz val=\"11\"/><name val=\"Calibri\"/></font><font><b/><color rgb=\"FFFFFFFF\"/><sz val=\"11\"/><name val=\"Calibri\"/></font></fonts><fills count=\"3\"><fill><patternFill patternType=\"none\"/></fill><fill><patternFill patternType=\"gray125\"/></fill><fill><patternFill patternType=\"solid\"><fgColor rgb=\"FF234F70\"/><bgColor indexed=\"64\"/></patternFill></fill></fills><borders count=\"1\"><border/></borders><cellStyleXfs count=\"1\"><xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\"/></cellStyleXfs><cellXfs count=\"3\"><xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\" xfId=\"0\"/><xf numFmtId=\"0\" fontId=\"1\" fillId=\"2\" borderId=\"0\" xfId=\"0\" applyAlignment=\"1\"><alignment wrapText=\"1\" vertical=\"center\"/></xf><xf numFmtId=\"164\" fontId=\"0\" fillId=\"0\" borderId=\"0\" xfId=\"0\" applyNumberFormat=\"1\"/></cellXfs><cellStyles count=\"1\"><cellStyle name=\"Normal\" xfId=\"0\" builtinId=\"0\"/></cellStyles></styleSheet>");
            StringBuilder hoja = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"><sheetViews><sheetView workbookViewId=\"0\"><pane ySplit=\"1\" topLeftCell=\"A2\" activePane=\"bottomLeft\" state=\"frozen\"/></sheetView></sheetViews><cols>");
            int[] anchos = {42, 38, 17, 17, 45, 42, 17, 17, 25, 30};
            for (int i = 0; i < 10; i++) {
                hoja.append("<col min=\"").append(i + 1).append("\" max=\"").append(i + 1).append("\" width=\"").append(anchos[i]).append("\" customWidth=\"1\"/>");
            }
            hoja.append("</cols><sheetData><row r=\"1\" ht=\"32\" customHeight=\"1\">");
            for (int i = 0; i < 10; i++) {
                hoja.append(celda("" + (char) ('A' + i) + "1", CvCsv.ENCABEZADOS[i], 1));
            }
            hoja.append("</row>");
            String[] columnasFecha = {"C", "D", "G", "H"};
            for (int fila = 2; fila <= 10001; fila++) {
                hoja.append("<row r=\"").append(fila).append("\">");
                for (int j = 0; j < columnasFecha.length; j++) {
                    String col = columnasFecha[j];
                    hoja.append("<c r=\"").append(col).append(fila).append("\" s=\"2\"/>");
                }
                hoja.append("</row>");
            }
            hoja.append("</sheetData><autoFilter ref=\"A1:J10001\"/><dataValidations count=\"10\">");
            hoja.append(lista("A2:A10001", "Empleados")).append(lista("B2:B10001", "Empresas")).append(lista("F2:F10001", "Instituciones"));
            hoja.append(fecha("C", null)).append(fecha("D", "C")).append(fecha("G", null)).append(fecha("H", "G"));
            hoja.append(longitud("E", 150)).append(longitud("I", 50)).append(longitud("J", 100));
            hoja.append("</dataValidations></worksheet>");
            entrada(zip, "xl/worksheets/sheet1.xml", hoja.toString());
            StringBuilder catalogos = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"><sheetData><row r=\"1\">");
            catalogos.append(celda("A1", "EMPLEADOS", 1)).append(celda("B1", "EMPRESAS", 1)).append(celda("C1", "INSTITUCIONES", 1)).append("</row>");
            int maximo = Math.max(empleados.size(), Math.max(empresas.size(), instituciones.size()));
            for (int i = 0; i < Math.max(1, maximo); i++) {
                catalogos.append("<row r=\"").append(i + 2).append("\">");
                Vector[] listas = {empleados, empresas, instituciones};
                for (int j = 0; j < 3; j++) {
                    if (i < listas[j].size()) {
                        Vector fila = (Vector) listas[j].get(i);
                        catalogos.append(celda("" + (char) ('A' + j) + (i + 2), fila.get(0).toString() + " - " + fila.get(1).toString(), 0));
                    } else if (i == 0) {
                        catalogos.append(celda("" + (char) ('A' + j) + "2", "", 0));
                    }
                }
                catalogos.append("</row>");
            }
            catalogos.append("</sheetData></worksheet>");
            entrada(zip, "xl/worksheets/sheet2.xml", catalogos.toString());
        }
        return memoria.toByteArray();
    }
}
