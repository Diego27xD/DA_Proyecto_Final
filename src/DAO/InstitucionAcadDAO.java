package DAO;

import BEAN.InstitucionAcad;
import UTIL.DbBean;
import java.sql.ResultSet;
import java.util.Vector;

public class InstitucionAcadDAO {
    public InstitucionAcadDAO(){
    }

    private String texto(String cad){
        if(cad == null || cad.trim().isEmpty()){
            return "null";
        }
        return "'" + cad.trim().replace("'", "''") + "'";
    }

    private String vacio(String cad){
        if(cad == null){
            return "";
        }
        return cad;
    }

    public Vector<InstitucionAcad> listaInstituciones(boolean sw, String cad){
        DbBean conBD = new DbBean();
        Vector<InstitucionAcad> listInst;
        listInst = new Vector<InstitucionAcad>();
        String sql;
        ResultSet rstInst;
        try{
            sql = "select IdInstitucion, NombInstitucion, TipInstitucion, Tlf, Web from InstitucionAcad ";
            if(sw == true){
                sql = sql + " where NombInstitucion like '%"+ cad.replace("'", "''") +"%'";
            }
            sql = sql + " order by IdInstitucion";
            rstInst = conBD.resultadoSQL(sql);
            while(rstInst.next()){
                InstitucionAcad inst = new InstitucionAcad();
                inst.setIdInstitucion(rstInst.getInt(1));
                inst.setNombInstitucion(vacio(rstInst.getString(2)));
                inst.setTipInstitucion(vacio(rstInst.getString(3)));
                inst.setTlf(vacio(rstInst.getString(4)));
                inst.setWeb(vacio(rstInst.getString(5)));
                listInst.addElement(inst);
            }
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        try{
            conBD.desconecta();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        return listInst;
    }

    public void insertaInstitucion(InstitucionAcad inst){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "insert into InstitucionAcad (NombInstitucion, TipInstitucion, Tlf, Web) values (";
            sql = sql + texto(inst.getNombInstitucion()) +", "+ texto(inst.getTipInstitucion()) +", ";
            sql = sql + texto(inst.getTlf()) +", "+ texto(inst.getWeb()) +")";
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

    public void actualizaInstitucion(InstitucionAcad inst){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "update InstitucionAcad set NombInstitucion = "+ texto(inst.getNombInstitucion()) +", ";
            sql = sql + " TipInstitucion = "+ texto(inst.getTipInstitucion()) +", Tlf = "+ texto(inst.getTlf()) +", ";
            sql = sql + " Web = "+ texto(inst.getWeb()) +" ";
            sql = sql + " where IdInstitucion = "+ inst.getIdInstitucion() +"";
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

    public boolean eliminaInstitucion(int idInst){
        boolean sw = false;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "delete from InstitucionAcad where IdInstitucion = "+ idInst +"";
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
}
