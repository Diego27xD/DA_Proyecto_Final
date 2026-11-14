package UTIL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {

    public Util(){

    }

    public int idNext(String nombTbl, String nombCamp){
        DbBean con = new DbBean();
        int IM = 0;
        try{
            String sql = "SELECT ISNULL(MAX("+ nombCamp +"), 0) + 1 AS idMax FROM "+ nombTbl +"";
            ResultSet result = con.resultadoSQL(sql);
            if(result.next()){
                IM = result.getInt(1);
            }
            result.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            con.desconecta();
        }catch(Exception e){
            e.printStackTrace();
        }
        return IM;
    }

    public boolean repExp(String nombTbl, String nombCamp, String cad){
        DbBean con = new DbBean();
        boolean sw = false;
        try{
            String sql = "SELECT * FROM "+ nombTbl +" where "+ nombCamp +" = '"+ cad.replace("'", "''") +"'";
            ResultSet result = con.resultadoSQL(sql);
            if(result.next()){
                sw = true;
            }
            result.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            con.desconecta();
        }catch(Exception e){
            e.printStackTrace();
        }
        return sw;
    }

    public int numRows(String sql){
        int nR = 0;
        DbBean con = new DbBean();
        String bigSQL = "SELECT COUNT(*) AS NumReg FROM ("+ sql +") DERIVEDTBL";
        try{
            ResultSet resultado = con.resultadoSQL(bigSQL);
            if(resultado.next()){
                nR = resultado.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            con.desconecta();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return nR;
    }
}
