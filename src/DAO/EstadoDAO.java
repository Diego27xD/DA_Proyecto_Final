package DAO;

import BEAN.Estado;
import UTIL.DbBean;
import java.sql.ResultSet;
import java.util.Vector;

public class EstadoDAO {
    public EstadoDAO(){
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

    public Vector<Estado> listaEstados(boolean sw, String cad){
        DbBean conBD = new DbBean();
        Vector<Estado> lista;
        lista = new Vector<Estado>();
        String sql;
        ResultSet rst;
        try{
            sql = "select IdEstado, DescEstado from Estado ";
            if(sw == true){
                sql = sql + " where DescEstado like '%"+ cad.replace("'", "''") +"%'";
            }
            sql = sql + " order by IdEstado";
            rst = conBD.resultadoSQL(sql);
            while(rst.next()){
                Estado est = new Estado();
                est.setIdEstado(rst.getInt(1));
                est.setDescEstado(vacio(rst.getString(2)));
                lista.addElement(est);
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

    public void insertaEstado(Estado est){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "insert into Estado (DescEstado) values (";
            sql = sql + texto(est.getDescEstado()) +")";
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

    public void actualizaEstado(Estado est){
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "update Estado set DescEstado = "+ texto(est.getDescEstado()) +" ";
            sql = sql + " where IdEstado = "+ est.getIdEstado() +"";
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

    public boolean eliminaEstado(int idEst){
        boolean sw = false;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "delete from Estado where IdEstado = "+ idEst +"";
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
