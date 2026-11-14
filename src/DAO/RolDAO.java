package DAO;

import BEAN.Rol;
import UTIL.DbBean;
import UTIL.Util;
import java.sql.ResultSet;
import java.util.Vector;

public class RolDAO {
    
    public RolDAO(){
    }
    
    public Vector<Rol> listaRoles(boolean sw, String cad){
        DbBean conBD = new DbBean();
        Vector<Rol> listRoles;
        listRoles = new Vector<Rol>();
        String sql;
        ResultSet rstRoles;
        try{
            sql = "select IdRol, DescRol, Sueldo from Rol ";
            if(sw == true){
                sql = sql + " where DescRol like '"+ cad.replace("'", "''") +"%'";
            }
            sql = sql + " order by IdRol";
            rstRoles = conBD.resultadoSQL(sql);
            while(rstRoles.next()){
                Rol rol = new Rol();
                rol.setIdRol(rstRoles.getInt(1));
                rol.setDescRol(rstRoles.getString(2));
                rol.setSueldo(rstRoles.getDouble(3));
                listRoles.addElement(rol);
            }
            rstRoles.close();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        try{
            conBD.desconecta();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        return listRoles;
    }
    
    public int insertaRol(Rol rol){
        int resultado = 0;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "insert into Rol (DescRol, Sueldo) values (";
            sql = sql + "'"+ rol.getDescRol().replace("'", "''") +"', ";
            sql = sql + ""+ rol.getSueldo() +")";
            resultado = conBD.ejecutaSQL(sql);
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        try{
            conBD.desconecta();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public int actualizaRol(Rol rol){
        int resultado = 0;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "update Rol set DescRol = '"+ rol.getDescRol().replace("'", "''") +"', ";
            sql = sql + " Sueldo = "+ rol.getSueldo() +" ";
            sql = sql + " where IdRol = "+ rol.getIdRol() +"";
            resultado = conBD.ejecutaSQL(sql);
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        try{
            conBD.desconecta();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public boolean existeDescripcion(String desc, int idRol){
        Util u = new Util();
        String sql;
        sql = "select IdRol from Rol where DescRol = '"+ desc.replace("'", "''") +"' ";
        sql = sql + " and IdRol <> "+ idRol +"";
        return u.numRows(sql) > 0;
    }
    
    public int cuentaContratos(int idRol){
        Util u = new Util();
        return u.numRows("select IdContrato from Contrato where IdRol = "+ idRol +"");
    }
    
    public int cuentaRequisitos(int idRol){
        Util u = new Util();
        return u.numRows("select IdRol from RequisitoRol where IdRol = "+ idRol +"");
    }
    
    public boolean eliminaRol(int idRol){
        boolean sw = false;
        String sql;
        if(cuentaContratos(idRol) == 0 && cuentaRequisitos(idRol) == 0){
            DbBean conBD = new DbBean();
            try{
                sql = "delete from Rol where IdRol = "+ idRol +"";
                if(conBD.ejecutaSQL(sql) > 0){
                    sw = true;
                }
            }catch(java.sql.SQLException e){
                e.printStackTrace();
            }
            try{
                conBD.desconecta();
            }catch(java.sql.SQLException e){
                e.printStackTrace();
            }
        }
        return sw;
    }
}
