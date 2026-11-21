/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BEAN.CabCv;
import BEAN.ExperienciaAcademica;
import BEAN.ExperienciaLaboral;
import UTIL.CvValidation;
import UTIL.DbBean;
import UTIL.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author dflor
 */
public class CvDAO {
    private String error = "";

    public String getError() {
        return error;
    }

    public Vector<Vector> listaEmpleados() {
        error = "";
        DbBean db = new DbBean();
        Util util = new Util();
        String query = "select IdEmpleado, Apellidos+' '+Nombres from dbo.Empleado order by Apellidos,Nombres,IdEmpleado";
        Vector<Vector> listaEmpleados = new Vector<Vector>();
        try {
            if (!db.conecta()) {
                throw new SQLException("No se pudo conectar a BD_RRHH.");
            }

            listaEmpleados = util.listarOpciones(db, query);
        } catch (Exception e) {
            error = e.getMessage();
            if (error == null || error.isEmpty()) {
                error = "No se pudo completar la operación. Revise los datos del CV.";
            }
            e.printStackTrace();
            return null;
        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
        return listaEmpleados;
    }

    public Vector<Vector> listaEmpresas() {
        error = "";
        DbBean db = new DbBean();
        Util util = new Util();
        String query = "select IdEmpresa, Descrip from dbo.Empresa order by Descrip,IdEmpresa";
        Vector<Vector> listaEmpresas = new Vector<Vector>();
        try {
            if (!db.conecta()) {
                throw new SQLException("No se pudo conectar a BD_RRHH.");
            }

            listaEmpresas = util.listarOpciones(db, query);
        } catch (Exception e) {
            error = e.getMessage();
            if (error == null || error.isEmpty()) {
                error = "No se pudo completar la operación. Revise los datos del CV.";
            }
            e.printStackTrace();
            return null;
        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
        return listaEmpresas;
    }

    public Vector<Vector> listaInstituciones() {
        error = "";
        DbBean db = new DbBean();
        Util util = new Util();
        String query = "select IdInstitucion, NombInstitucion from dbo.InstitucionAcad order by NombInstitucion,IdInstitucion";
        Vector<Vector> listaInstituciones = new Vector<Vector>();
        try {
            if (!db.conecta()) {
                throw new SQLException("No se pudo conectar a BD_RRHH.");
            }

            listaInstituciones = util.listarOpciones(db, query);
        } catch (Exception e) {
            error = e.getMessage();
            if (error == null || error.isEmpty()) {
                error = "No se pudo completar la operación. Revise los datos del CV.";
            }
            e.printStackTrace();
            return null;
        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
        return listaInstituciones;
    }

    public Vector<Vector> listaCV(int empleado, int empresa, int institucion) {
        error = "";
        DbBean db = new DbBean();
        Util util = new Util();
        Vector<Vector> lista = new Vector<Vector>();
        String sql;
        sql = "select c.Id_CV,c.IdEmpleado,e.Apellidos,e.Nombres ";
        sql += "from dbo.Cab_CV c, dbo.Empleado e ";
        sql += "where c.IdEmpleado=e.IdEmpleado ";
        if (empleado > 0) {
            sql += "and c.IdEmpleado=" + empleado + " ";
        }
        sql += "order by e.Apellidos,e.Nombres,c.Id_CV";
        try {
            if (!db.conecta()) {
                throw new SQLException("No se pudo conectar a BD_RRHH.");
            }
            ResultSet rst = db.resultadoSQL(sql);
            try {
                while (rst.next()) {
                    CabCv datos = cargaCV(db, rst.getInt(1));
                    boolean tieneEmpresa = empresa == 0;
                    boolean tieneInstitucion = institucion == 0;
                    for (int i = 0; i < datos.getExperienciasLaborales().size(); i++) {
                        if (datos.getExperienciasLaborales().get(i).getIdEmpresa() == empresa) {
                            tieneEmpresa = true;
                        }
                    }
                    for (int i = 0; i < datos.getExperienciasAcademicas().size(); i++) {
                        if (datos.getExperienciasAcademicas().get(i).getIdInstitucion() == institucion) {
                            tieneInstitucion = true;
                        }
                    }
                    if (tieneEmpresa && tieneInstitucion) {
                        Vector vec = new Vector();
                        vec.addElement(rst.getInt(1));
                        vec.addElement(rst.getInt(2));
                        vec.addElement(rst.getString(3) + " " + rst.getString(4));
                        vec.addElement(datos.getExperienciasLaborales().size());
                        vec.addElement(datos.getExperienciasAcademicas().size());
                        lista.addElement(vec);
                    }
                }
            } finally {

                db.cerrarTransaccion(rst);
            }
        } catch (Exception e) {
            error = e.getMessage();
            if (error == null || error.isEmpty()) {
                error = "No se pudo completar la operación. Revise los datos del CV.";
            }
            e.printStackTrace();
            return null;
        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
        return lista;
    }

    public CabCv cargaCV(int id) {
        error = "";
        DbBean db = new DbBean();
        try {
            if (!db.conecta()) {
                throw new SQLException("No se pudo conectar a BD_RRHH.");
            }
            return cargaCV(db, id);
        } catch (Exception e) {
            error = e.getMessage();
            if (error == null || error.isEmpty()) {
                error = "No se pudo completar la operación. Revise los datos del CV.";
            }
            e.printStackTrace();
            return null;
        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
    }

    private CabCv cargaCV(DbBean db, int id) throws SQLException {
        CabCv cv = new CabCv();
        ResultSet rst = db.resultadoSQL("select Id_CV,IdEmpleado from dbo.Cab_CV where Id_CV=" + id);
        try {
            if (!rst.next()) {
                throw new SQLException("El CV seleccionado ya no existe.");
            }
            cv.setId_CV(rst.getInt(1));
            cv.setIdEmpleado(rst.getInt(2));
        } finally {
            db.cerrarTransaccion(rst);
        }

        rst = db.resultadoSQL("select IdEmpresa,Fech_Ini,Fech_Fin,DescRol from dbo.Det_ExpLab where Id_CV=" + id + " order by Fech_Ini,IdEmpresa");
        try {
            while (rst.next()) {
                ExperienciaLaboral exp = new ExperienciaLaboral();
                exp.setIdEmpresa(rst.getInt(1));
                exp.setFech_Ini(rst.getString(2));
                exp.setFech_Fin(rst.getString(3));
                exp.setDescRol(rst.getString(4));
                cv.getExperienciasLaborales().addElement(exp);
            }
        } finally {
            db.cerrarTransaccion(rst);
        }
        rst = db.resultadoSQL("select IdInstitucion,Fech_Ini,Fech_Fin,EstadoAvance,GradoAcad from dbo.Det_ExpAcad where Id_CV=" + id + " order by Fech_Ini,IdInstitucion");
        try {
            while (rst.next()) {
                ExperienciaAcademica exp = new ExperienciaAcademica();
                exp.setIdInstitucion(rst.getInt(1));
                exp.setFech_Ini(rst.getString(2));
                exp.setFech_Fin(rst.getString(3));
                exp.setEstadoAvance(rst.getString(4));
                exp.setGradoAcad(rst.getString(5));
                cv.getExperienciasAcademicas().addElement(exp);
            }
        } finally {
            db.cerrarTransaccion(rst);
        }
        return cv;
    }

    private int creaCV(DbBean db, int empleado) throws SQLException {
        int result = 0;
        try {
            db.ejecutaSQL("insert into dbo.Cab_CV(IdEmpleado) values (" + empleado + ")");
            result = buscaCV(db, empleado);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    private int buscaCV(DbBean db, int empleado) throws SQLException {
        ResultSet rst = null;
        try {
            rst = db.resultadoSQL(
                    "select Id_CV from dbo.Cab_CV where IdEmpleado=" + empleado
            );
            if (rst != null && rst.next()) {
                return rst.getInt("Id_CV");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            db.cerrarTransaccion(rst);
        }
        return 0;
    }
    
    private void insertarExperienciaLaboral(DbBean db, int id, ExperienciaLaboral exp) throws SQLException {
        try{
            String inicio, fin, rol;
        inicio = "N'" + exp.getFech_Ini().replace("-", "") + "'";
        if (CvValidation.texto(exp.getFech_Fin()).isEmpty()) {
            fin = "NULL";
        } else {
            fin = "N'" + exp.getFech_Fin().replace("-", "") + "'";
        }
        if (CvValidation.texto(exp.getDescRol()).isEmpty()) {
            rol = "NULL";
        } else {
            rol = "N'" + exp.getDescRol().replace("'", "''") + "'";
        }
        String sql;
        sql = "insert into dbo.Det_ExpLab(Id_CV,IdEmpresa,Fech_Ini,Fech_Fin,DescRol) values (";
        sql += id + "," + exp.getIdEmpresa() + "," + inicio + ",";
        sql += fin + "," + rol + ")";
        db.ejecutaSQL(sql);
        
        }catch (SQLException e) {
            throw e;
        }
    }
    private void insertarExperienciaAcademica(DbBean db, int id, ExperienciaAcademica exp) throws SQLException {
        try{
            String inicio, fin, avance, grado;
        inicio = "N'" + exp.getFech_Ini().replace("-", "") + "'";
        if (CvValidation.texto(exp.getFech_Fin()).isEmpty()) {
            fin = "NULL";
        } else {
            fin = "N'" + exp.getFech_Fin().replace("-", "") + "'";
        }
        if (CvValidation.texto(exp.getEstadoAvance()).isEmpty()) {
            avance = "NULL";
        } else {
            avance = "N'" + exp.getEstadoAvance().replace("'", "''") + "'";
        }
        if (CvValidation.texto(exp.getGradoAcad()).isEmpty()) {
            grado = "NULL";
        } else {
            grado = "N'" + exp.getGradoAcad().replace("'", "''") + "'";
        }
        String sql;
        sql = "insert into dbo.Det_ExpAcad(Id_CV,IdInstitucion,Fech_Ini,Fech_Fin,EstadoAvance,GradoAcad) values (";
        sql += id + "," + exp.getIdInstitucion() + "," + inicio + ",";
        sql += fin + "," + avance + "," + grado + ")";
        db.ejecutaSQL(sql);
        }catch (SQLException e) {
            throw e;
        }
    }
    private void validarDatos(DbBean db, CabCv cv, Vector<String> errores) throws SQLException {
        try{
            Util util = new Util();
            CvValidation.validar(cv,
                util.listarOpciones(db, "select IdEmpleado,Apellidos+' '+Nombres from dbo.Empleado"),
                util.listarOpciones(db, "select IdEmpresa,Descrip from dbo.Empresa"),
                util.listarOpciones(db, "select IdInstitucion,NombInstitucion from dbo.InstitucionAcad"), errores);
        }catch (SQLException e) {
            throw e;
        }
    }
    
    public int guardaCV(CabCv cv) {
        error = "";
        
        DbBean db = new DbBean();
        boolean transaccion = false;
        try {
            if (!db.conecta()) {
                throw new SQLException("No se pudo conectar a BD_RRHH.");
            }
            db.getConexion().setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
            db.getConexion().setAutoCommit(false);
            transaccion = true;
            Vector<String> errores = new Vector<String>();
            validarDatos(db, cv, errores);
            if (!errores.isEmpty()) {
                throw new SQLException(CvValidation.errores(errores));
            }
            int existente = buscaCV(db, cv.getIdEmpleado());
            if (existente != 0 && existente != cv.getId_CV()) {
                throw new SQLException("Este empleado ya tiene un CV. Selecciónelo en la grilla para actualizarlo.");
            }
            int id = cv.getId_CV();
            if (id == 0) {
                id = creaCV(db, cv.getIdEmpleado());
            } else {
                cargaCV(db, id);
                if (db.ejecutaSQL("update dbo.Cab_CV set IdEmpleado=" + cv.getIdEmpleado() + " where Id_CV=" + id) != 1) {
                    throw new SQLException("El CV ya no existe.");
                }
                db.ejecutaSQL("delete from dbo.Det_ExpLab where Id_CV=" + id);
                db.ejecutaSQL("delete from dbo.Det_ExpAcad where Id_CV=" + id);
            }
            for (int i = 0; i < cv.getExperienciasLaborales().size(); i++) {
                insertarExperienciaLaboral(db, id, cv.getExperienciasLaborales().get(i));
            }
            for (int i = 0; i < cv.getExperienciasAcademicas().size(); i++) {
                insertarExperienciaAcademica(db, id, cv.getExperienciasAcademicas().get(i));
            }
            db.getConexion().commit();
            return id;
        } catch (Exception e) {
            error = e.getMessage();
            if (error == null || error.isEmpty()) {
                error = "No se pudo completar la operación. Revise los datos del CV.";
            }
            
            if (transaccion) {
                try {
                    db.getConexion().rollback();
                } catch (SQLException ex) {
                    error += "\nNo se pudo deshacer la operación: " + ex.getMessage();
                }
            }
            return 0;
        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public boolean eliminaCV(int id) {
        error = "";
        
        DbBean db = new DbBean();
        boolean transaccion = false;
        try {
            if (!db.conecta()) {
                throw new SQLException("No se pudo conectar a BD_RRHH.");
            }
            db.getConexion().setAutoCommit(false);
            transaccion = true;
            db.ejecutaSQL("delete from dbo.Det_ExpLab where Id_CV=" + id);
            db.ejecutaSQL("delete from dbo.Det_ExpAcad where Id_CV=" + id);
            if (db.ejecutaSQL("delete from dbo.Cab_CV where Id_CV=" + id) != 1) {
                throw new SQLException("El CV ya no existe.");
            }
            db.getConexion().commit();
            return true;
        } catch (Exception e) {
            error = e.getMessage();
            if (error == null || error.isEmpty()) {
                error = "No se pudo completar la operación. Revise los datos del CV.";
            }
           
            if (transaccion) {
                try {
                    db.getConexion().rollback();
                } catch (SQLException ex) {
                    error += "\nNo se pudo deshacer la operación: " + ex.getMessage();
                }
            }
            return false;
        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
    }
    public int[] importar(Vector<CabCv> lote) {
        error = "";
  
        DbBean db = new DbBean();
        Util util = new Util();
        boolean transaccion = false;
        int[] total = {0, 0, 0, 0};
        try {
            if (lote == null || lote.isEmpty()) {
                throw new SQLException("No hay CV para importar.");
            }
            if (!db.conecta()) {
                throw new SQLException("No se pudo conectar a BD_RRHH.");
            }
            db.getConexion().setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
            db.getConexion().setAutoCommit(false);
            transaccion = true;
            Vector<CabCv> existentes = new Vector<CabCv>();
            Vector<String> errores = new Vector<String>();
            Vector<Vector> empleados = util.listarOpciones(db, "select IdEmpleado,Apellidos+' '+Nombres from dbo.Empleado");
            Vector<Vector> empresas = util.listarOpciones(db, "select IdEmpresa,Descrip from dbo.Empresa");
            Vector<Vector> instituciones = util.listarOpciones(db, "select IdInstitucion,NombInstitucion from dbo.InstitucionAcad");
            // Se comprueba TODO el lote antes del primer INSERT.
            for (int i = 0; i < lote.size(); i++) {
                CabCv nuevo = lote.get(i);
                CvValidation.validar(nuevo, empleados, empresas, instituciones, errores);
                for (int j = 0; j < i; j++) {
                    if (lote.get(j).getIdEmpleado() == nuevo.getIdEmpleado()) {
                        errores.addElement("El lote contiene dos cabeceras para el mismo empleado.");
                    }
                }
                int id = buscaCV(db, nuevo.getIdEmpleado());
                CabCv anterior = id == 0 ? new CabCv() : cargaCV(db, id);
                existentes.addElement(anterior);
                for (int j = 0; j < nuevo.getExperienciasLaborales().size(); j++) {
                    ExperienciaLaboral exp = nuevo.getExperienciasLaborales().get(j);
                    for (int k = 0; k < anterior.getExperienciasLaborales().size(); k++) {
                        ExperienciaLaboral ya = anterior.getExperienciasLaborales().get(k);
                        if (exp.getIdEmpresa() == ya.getIdEmpresa() && CvValidation.texto(exp.getFech_Ini()).equals(ya.getFech_Ini()) && !CvValidation.mismaLaboral(exp, ya)) {
                            errores.addElement("Fila " + exp.getFilaOrigen() + ": experiencia laboral ya existente con la misma empresa y fecha inicial, pero con datos distintos. Corríjala desde el formulario.");
                        }
                    }
                }
                for (int j = 0; j < nuevo.getExperienciasAcademicas().size(); j++) {
                    ExperienciaAcademica exp = nuevo.getExperienciasAcademicas().get(j);
                    for (int k = 0; k < anterior.getExperienciasAcademicas().size(); k++) {
                        ExperienciaAcademica ya = anterior.getExperienciasAcademicas().get(k);
                        if (exp.getIdInstitucion() == ya.getIdInstitucion() && CvValidation.texto(exp.getFech_Ini()).equals(ya.getFech_Ini()) && !CvValidation.mismaAcademica(exp, ya)) {
                            errores.addElement("Fila " + exp.getFilaOrigen() + ": experiencia académica ya existente con la misma institución y fecha inicial, pero con datos distintos. Corríjala desde el formulario.");
                        }
                    }
                }
            }
            if (!errores.isEmpty()) {
                throw new SQLException(CvValidation.errores(errores));
            }
            for (int i = 0; i < lote.size(); i++) {
                CabCv nuevo = lote.get(i), anterior = existentes.get(i);
                int id = anterior.getId_CV();
                if (id == 0) {
                    id = creaCV(db, nuevo.getIdEmpleado());
                    total[0]++;
                }
                for (int j = 0; j < nuevo.getExperienciasLaborales().size(); j++) {
                    ExperienciaLaboral exp = nuevo.getExperienciasLaborales().get(j);
                    boolean repetida = false;
                    for (int k = 0; k < anterior.getExperienciasLaborales().size(); k++) {
                        ExperienciaLaboral ya = anterior.getExperienciasLaborales().get(k);
                        if (CvValidation.mismaLaboral(exp, ya)) {
                            repetida = true;
                        }
                    }
                    if (repetida) {
                        total[3]++;
                    } else {
                        insertarExperienciaLaboral(db, id, exp);
                        total[1]++;
                    }
                }
                for (int j = 0; j < nuevo.getExperienciasAcademicas().size(); j++) {
                    ExperienciaAcademica exp = nuevo.getExperienciasAcademicas().get(j);
                    boolean repetida = false;
                    for (int k = 0; k < anterior.getExperienciasAcademicas().size(); k++) {
                        ExperienciaAcademica ya = anterior.getExperienciasAcademicas().get(k);
                        if (CvValidation.mismaAcademica(exp, ya)) {
                            repetida = true;
                        }
                    }
                    if (repetida) {
                        total[3]++;
                    } else {
                        insertarExperienciaAcademica(db, id, exp);
                        total[2]++;
                    }
                }
            }
            db.getConexion().commit();
            return total;
        } catch (Exception e) {
            error = e.getMessage();
            if (error == null || error.isEmpty()) {
                error = "No se pudo completar la operación. Revise los datos del CV.";
            }
            
            if (transaccion) {
                try {
                    db.getConexion().rollback();
                } catch (SQLException ex) {
                }
            }
            return null;
        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
