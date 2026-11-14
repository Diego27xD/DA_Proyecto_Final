package DAO;

import BEAN.Habilidad;
import UTIL.DbBean;
import java.sql.ResultSet;
import java.util.Vector;

public class HabilidadDAO {
    public HabilidadDAO(){
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

    public Vector<Habilidad> listaHabilidades(boolean sw, String cad){
        DbBean conBD = new DbBean();
        Vector<Habilidad> lista;
        lista = new Vector<Habilidad>();
        String sql;
        ResultSet rst;
        try{
            sql = "select Id_Habilidad, DescHab from Habilidades ";
            if(sw == true){
                sql = sql + " where DescHab like '%"+ cad.replace("'", "''") +"%'";
            }
            sql = sql + " order by Id_Habilidad";
            rst = conBD.resultadoSQL(sql);
            while(rst.next()){
                Habilidad obj = new Habilidad();
                obj.setIdHabilidad(rst.getInt(1));
                obj.setDescHab(vacio(rst.getString(2)));
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

    public void insertaHabilidad(Habilidad obj){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "insert into Habilidades (DescHab) values (";
            sql = sql + texto(obj.getDescHab()) +")";
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

    public void actualizaHabilidad(Habilidad obj){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "update Habilidades set DescHab = "+ texto(obj.getDescHab()) +" ";
            sql = sql + " where Id_Habilidad = "+ obj.getIdHabilidad() +"";
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

    public boolean eliminaHabilidad(int id){
        boolean sw = false;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "select count(*) from HabilEmpleado where Id_Habilidad = "+ id +"";
            ResultSet rst = conBD.resultadoSQL(sql);
            int nHijos = 0;
            if(rst.next()){
                nHijos = rst.getInt(1);
            }
            rst.close();
            if(nHijos == 0){
                sql = "delete from Habilidades where Id_Habilidad = "+ id +"";
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
