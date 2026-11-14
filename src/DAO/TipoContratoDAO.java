package DAO;

import BEAN.TipoContrato;
import UTIL.DbBean;
import UTIL.Util;
import java.sql.ResultSet;
import java.util.Vector;

public class TipoContratoDAO {
    
    public TipoContratoDAO(){
    }
    
    public Vector<TipoContrato> listaTipoContratos(boolean sw, String cad){
        DbBean conBD = new DbBean();
        Vector<TipoContrato> listTipos;
        listTipos = new Vector<TipoContrato>();
        String sql;
        ResultSet rstTipos;
        try{
            sql = "select IdTipContrato, DescTipContrato from TipoContrato ";
            if(sw == true){
                sql = sql + " where DescTipContrato like '"+ cad.replace("'", "''") +"%'";
            }
            sql = sql + " order by IdTipContrato";
            rstTipos = conBD.resultadoSQL(sql);
            while(rstTipos.next()){
                TipoContrato tipo = new TipoContrato();
                tipo.setIdTipContrato(rstTipos.getInt(1));
                tipo.setDescTipContrato(rstTipos.getString(2));
                listTipos.addElement(tipo);
            }
            rstTipos.close();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        try{
            conBD.desconecta();
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        return listTipos;
    }
    
    public int insertaTipoContrato(TipoContrato tipo){
        int resultado = 0;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "insert into TipoContrato (DescTipContrato) values (";
            sql = sql + "'"+ tipo.getDescTipContrato().replace("'", "''") +"')";
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
    
    public int actualizaTipoContrato(TipoContrato tipo){
        int resultado = 0;
        String sql;
        DbBean conBD = new DbBean();
        try{
            sql = "update TipoContrato set DescTipContrato = '"+ tipo.getDescTipContrato().replace("'", "''") +"' ";
            sql = sql + " where IdTipContrato = "+ tipo.getIdTipContrato() +"";
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
    
    public boolean existeDescripcion(String desc, int idTipo){
        Util u = new Util();
        String sql;
        sql = "select IdTipContrato from TipoContrato where DescTipContrato = '"+ desc.replace("'", "''") +"' ";
        sql = sql + " and IdTipContrato <> "+ idTipo +"";
        return u.numRows(sql) > 0;
    }
    
    public int cuentaContratos(int idTipo){
        Util u = new Util();
        return u.numRows("select IdContrato from Contrato where IdTipContrato = "+ idTipo +"");
    }
    
    public boolean eliminaTipoContrato(int idTipo){
        boolean sw = false;
        String sql;
        if(cuentaContratos(idTipo) == 0){
            DbBean conBD = new DbBean();
            try{
                sql = "delete from TipoContrato where IdTipContrato = "+ idTipo +"";
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
