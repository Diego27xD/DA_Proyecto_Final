package UTIL;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class PruebaReporteEtnia {
    public static void main(String[] args) {
        DbBean db = new DbBean();
        try {
            if (!db.conecta()) {
                System.out.println("Error al conectar con SQL Server");
                return;
            }
            Map<String, Object> parametros = new HashMap<String, Object>();

            InputStream archivo = PruebaReporteEtnia.class.getResourceAsStream(
                    "/REPORTES/ReporteEtnia.jasper");
            if (archivo == null) {
                System.out.println("No se encontro ReporteEtnia.jasper. Compile primero el archivo JRXML.");
                return;
            }
            try (InputStream reporte = archivo) {
                JasperPrint resultado = JasperFillManager.fillReport(
                        reporte, parametros, db.getConexion());
                JasperViewer.viewReport(resultado, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error al generar el reporte", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { db.desconecta(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
