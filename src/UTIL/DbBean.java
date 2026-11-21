package UTIL;
import java.sql.*;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
    
    public void connectRep(String ruta, HashMap m, boolean sw)throws SQLException, JRException{
        conecta();
        JasperReport reporte = null;
        JasperPrint imp;
        reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
        if(sw == false){
            imp = JasperFillManager.fillReport(reporte, null, dbCon);
        }else{
            imp = JasperFillManager.fillReport(reporte, m, dbCon);
        }
        JasperViewer ver = new JasperViewer(imp, false);
        ver.setTitle("Reporte");
        ver.setVisible(true);                
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
        try {
            return s.executeUpdate(sql);
        } finally {
            s.close();
        }
    }
    public void cerrarTransaccion(ResultSet rst) {
        if (rst == null) {
            return;
        }
        try{
            java.sql.Statement sentencia = rst.getStatement();
            try {
                rst.close();
            } finally {
                sentencia.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
