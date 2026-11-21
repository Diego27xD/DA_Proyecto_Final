/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UTIL;

import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class PruebaReporteGenero {

    public static void main(String[] args) {

        DbBean db = new DbBean();

        try {
            if (!db.conecta()) {
                System.out.println("Error al conectar con SQL Server");
                return;
            }

            InputStream archivo =
                PruebaReporteGenero.class.getResourceAsStream(
                    "/REPORTES/ReporteGenero.jasper"
                );

            if (archivo == null) {
                System.out.println("No se encontro ReporteGenero.jasper");
                return;
            }

            try (InputStream reporte = archivo) {

                JasperPrint resultado =
                    JasperFillManager.fillReport(
                        reporte,
                        new HashMap<String, Object>(),
                        db.getConexion()
                    );

                JasperViewer.viewReport(resultado, false);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                db.desconecta();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
