//Fawaz Bukhowa

package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Model {
    //  final String so value cannot be modified
    final String url = "jdbc:mysql://localhost:3306/hr?useSSL=false&useUnicode=true";

    final String driver = "com.mysql.jdbc.Driver";
    final String user = "root";
    final String pass = "Rene$11223344";
    Connection conn = null;
    DefaultTableModel tblModel = new DefaultTableModel();

    public Connection getConnection() throws Exception{
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Established connection");
            return conn;

        }catch(Exception e) {
            System.out.println("Error connecting: "+e.getMessage());
        }
        return null;
    }

    public void disconnect() {
        conn = null;

        if(conn == null) {
            System.out.println("Connection finished...");
        }
    }
    public void deleteRecord(String table, String primaryField, String id){
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "DELETE FROM "+table+" WHERE "+primaryField+" = '" + id + "'";
            instruction.executeUpdate(query);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally {
            disconnect();
        }
    }
}
