package DAO;

import BEAN.Area;
import UTIL.DbBean;
import java.sql.ResultSet;
import java.util.Vector;

public class AreaDAO {
    public AreaDAO(){
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

    public Vector<Area> listaAreas(boolean sw, String cad){
        DbBean conBD = new DbBean();
        Vector<Area> lista;
        lista = new Vector<Area>();
        String sql;
        ResultSet rst;
        try{
            sql = "select IdArea, DescArea, DescSubArea from Area ";
            if(sw == true){
                sql = sql + " where DescArea like '%"+ cad.replace("'", "''") +"%'";
            }
            sql = sql + " order by IdArea";
            rst = conBD.resultadoSQL(sql);
            while(rst.next()){
                Area area = new Area();
                area.setIdArea(rst.getInt(1));
                area.setDescArea(vacio(rst.getString(2)));
                area.setDescSubArea(vacio(rst.getString(3)));
                lista.addElement(area);
            }
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        try{
            conBD.desconecta();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void insertaArea(Area area){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "insert into Area (DescArea, DescSubArea) values (";
            sql = sql + texto(area.getDescArea()) +", "+ texto(area.getDescSubArea()) +")";
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

    public void actualizaArea(Area area){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "update Area set DescArea = "+ texto(area.getDescArea()) +", ";
            sql = sql + " DescSubArea = "+ texto(area.getDescSubArea()) +" ";
            sql = sql + " where IdArea = "+ area.getIdArea() +"";
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

    public boolean eliminaArea(int idArea){
        boolean sw = false;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "select count(*) from Contrato where IdArea = "+ idArea +"";
            ResultSet rst = conBD.resultadoSQL(sql);
            int nContratos = 0;
            if(rst.next()){
                nContratos = rst.getInt(1);
            }
            rst.close();
            if(nContratos == 0){
                sql = "delete from Area where IdArea = "+ idArea +"";
                if(conBD.ejecutaSQL(sql) > 0){
                    sw = true;
                }
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
