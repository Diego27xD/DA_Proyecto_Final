package UTIL;
import java.sql.*;
public class DbBean {
    String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=BD_RRHH";
    String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    private Connection dbCon;
    private String login = "sa";
    private String password = "root123"; 

    public DbBean() {
        conecta();
    }
    
    public Connection getConexion() {
        return this.dbCon;
    }

    public boolean conecta(){
        try{
            if(this.dbCon != null && !this.dbCon.isClosed()){
                return true;
            }
        }catch(SQLException ex){
        }
        try{
            Class.forName(this.dbDriver);
        }catch(java.lang.ClassNotFoundException e){
            System.out.println("Error en clase");
            return false;
        }
        try{
            this.dbCon = DriverManager.getConnection(this.dbURL, this.login, this.password);
        }catch(SQLException ex){
            System.out.println("No hay conexion al servidor");
            return false;
        }
        return true;
    }
    
    public void desconecta()throws SQLException{
        if(this.dbCon != null){
            this.dbCon.close();
        }
    }
    
    public ResultSet resultadoSQL(String sql)throws SQLException{
        Statement s = this.dbCon.createStatement();
        ResultSet r = s.executeQuery(sql);
        if(r==null){
            return null;
        }else{
            return r;
        }
    }
    public int ejecutaSQL(String sql)throws SQLException{
        Statement s = this.dbCon.createStatement();
        int r = s.executeUpdate(sql);
        if(r == 0){
            return 0;
        }else{
            return r;
        }
    }
}
