package DAO;

import BEAN.RequisitoRol;
import UTIL.DbBean;
import java.sql.ResultSet;
import java.util.Vector;

public class RequisitoRolDAO {
    public RequisitoRolDAO(){
    }

    public Vector<RequisitoRol> listaRequisitos(boolean sw, int idRol){
        DbBean conBD = new DbBean();
        Vector<RequisitoRol> listReq;
        listReq = new Vector<RequisitoRol>();
        String sql;
        ResultSet rstReqs;
        try{
            sql = "select IdRol, DescripReq from RequisitoRol ";
            if(sw == true){
                sql = sql + " where IdRol = "+ idRol +"";
            }
            sql = sql + " order by DescripReq";
            rstReqs = conBD.resultadoSQL(sql);
            while(rstReqs.next()){
                RequisitoRol req = new RequisitoRol();
                req.setIdRol(rstReqs.getInt(1));
                req.setDescripReq(rstReqs.getString(2));
                listReq.addElement(req);
            }
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        try{
            conBD.desconecta();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        return listReq;
    }

    public boolean insertaRequisito(RequisitoRol req){
        boolean sw = false;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "insert into RequisitoRol (IdRol, DescripReq) values (";
            sql = sql + ""+ req.getIdRol() +", '"+ req.getDescripReq().replace("'", "''") +"')";
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
        return sw;
    }

    public boolean actualizaRequisito(RequisitoRol req, String descAnterior){
        boolean sw = false;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "update RequisitoRol set DescripReq = '"+ req.getDescripReq().replace("'", "''") +"' ";
            sql = sql + " where IdRol = "+ req.getIdRol() +" ";
            sql = sql + " and DescripReq = '"+ descAnterior.replace("'", "''") +"'";
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
        return sw;
    }

    public boolean eliminaRequisito(int idRol, String descripReq){
        boolean sw = false;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "delete from RequisitoRol where IdRol = "+ idRol +" ";
            sql = sql + " and DescripReq = '"+ descripReq.replace("'", "''") +"'";
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
        return sw;
    }

    public void borraRequisitos(int idRol){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "delete from RequisitoRol where IdRol = "+ idRol +"";
            conBD.ejecutaSQL(sql);
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        try{
            conBD.desconecta();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
    }
}
