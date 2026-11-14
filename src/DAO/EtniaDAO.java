package DAO;

import BEAN.Etnia;
import UTIL.DbBean;
import java.sql.ResultSet;
import java.util.Vector;

public class EtniaDAO {
    public EtniaDAO(){
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

    public Vector<Etnia> listaEtnias(boolean sw, String cad){
        DbBean conBD = new DbBean();
        Vector<Etnia> lista;
        lista = new Vector<Etnia>();
        String sql;
        ResultSet rst;
        try{
            sql = "select IdEtnia, descripcion from Etnia ";
            if(sw == true){
                sql = sql + " where descripcion like '%"+ cad.replace("'", "''") +"%'";
            }
            sql = sql + " order by IdEtnia";
            rst = conBD.resultadoSQL(sql);
            while(rst.next()){
                Etnia obj = new Etnia();
                obj.setIdEtnia(rst.getInt(1));
                obj.setDescripcion(vacio(rst.getString(2)));
                lista.addElement(obj);
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

    public void insertaEtnia(Etnia obj){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "insert into Etnia (descripcion) values (";
            sql = sql + texto(obj.getDescripcion()) +")";
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

    public void actualizaEtnia(Etnia obj){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "update Etnia set descripcion = "+ texto(obj.getDescripcion()) +" ";
            sql = sql + " where IdEtnia = "+ obj.getIdEtnia() +"";
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

    public boolean eliminaEtnia(int id){
        boolean sw = false;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "select count(*) from Empleado where IdEtnia = "+ id +"";
            ResultSet rst = conBD.resultadoSQL(sql);
            int nHijos = 0;
            if(rst.next()){
                nHijos = rst.getInt(1);
            }
            rst.close();
            if(nHijos == 0){
                sql = "delete from Etnia where IdEtnia = "+ id +"";
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
