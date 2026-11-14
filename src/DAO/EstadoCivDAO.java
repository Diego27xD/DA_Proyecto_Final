package DAO;

import BEAN.EstadoCiv;
import UTIL.DbBean;
import java.sql.ResultSet;
import java.util.Vector;

public class EstadoCivDAO {
    public EstadoCivDAO(){
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

    public Vector<EstadoCiv> listaEstadosCiv(boolean sw, String cad){
        DbBean conBD = new DbBean();
        Vector<EstadoCiv> lista;
        lista = new Vector<EstadoCiv>();
        String sql;
        ResultSet rst;
        try{
            sql = "select IdEstCiv, descrip from EstadoCiv ";
            if(sw == true){
                sql = sql + " where descrip like '%"+ cad.replace("'", "''") +"%'";
            }
            sql = sql + " order by IdEstCiv";
            rst = conBD.resultadoSQL(sql);
            while(rst.next()){
                EstadoCiv ec = new EstadoCiv();
                ec.setIdEstCiv(rst.getInt(1));
                ec.setDescrip(vacio(rst.getString(2)));
                lista.addElement(ec);
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

    public void insertaEstadoCiv(EstadoCiv ec){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "insert into EstadoCiv (descrip) values (";
            sql = sql + texto(ec.getDescrip()) +")";
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

    public void actualizaEstadoCiv(EstadoCiv ec){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "update EstadoCiv set descrip = "+ texto(ec.getDescrip()) +" ";
            sql = sql + " where IdEstCiv = "+ ec.getIdEstCiv() +"";
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

    public boolean eliminaEstadoCiv(int idEc){
        boolean sw = false;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "delete from EstadoCiv where IdEstCiv = "+ idEc +"";
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
