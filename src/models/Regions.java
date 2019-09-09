package models;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

public class Regions extends Model{
    private HashMap<Integer, String> hashRegions = new HashMap<Integer, String>();

    public HashMap<Integer, String> getHashRegions() {
        return hashRegions;
    }
    
    public Regions() throws Exception {
        loadRegionsHashMap();
    }    
    
    private HashMap<Integer, String> loadRegionsHashMap() throws Exception {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query ="SELECT region_id, name FROM regions order by name;";
            ResultSet rs = instruction.executeQuery(query);
            rs.last();  //go to the last one
            int countRows = rs.getRow();
            rs.beforeFirst();
            for (int i = 0; i < countRows; i++) {
                rs.next();
                hashRegions.put(rs.getInt("region_id"), rs.getString("name"));
            }
            
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return hashRegions;
    }
    
    public String[] getRegionsList() throws Exception {
        String[] arrRegions = new String[hashRegions.size()+1];
        try {
            arrRegions[0] ="Select Region"; 
            int i =1;
            for (String name : hashRegions.values()) {
                arrRegions[i] = name;
                i++;
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return arrRegions;
    }
    
    public DefaultTableModel showAllRecords() {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT * FROM regions";
            ResultSet rs = instruction.executeQuery(query);
            tblModel.setColumnIdentifiers(new Object[]{
                "ID", 
                "First Name", 
                "Last Name", 
                "Email",
                "Gender",
                "DOB",
                "Phone",
                "Salary",
                "Job",
                "Department"});
            while(rs.next()) {
                tblModel.addRow(new Object[]{
                    rs.getString("region_id"), 
                    rs.getString("name"), 
                    rs.getString("region")});
            }
            disconnect();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return tblModel;
    }
}

