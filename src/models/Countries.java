package models;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Countries extends Model{

    public DefaultTableModel showAllRecords() {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT c.country_id, c.name as country, r.name "
                    + "as region FROM countries c "
                    + "inner join regions r on (c.fk_region= r.region_id) "
                    + "order by c.name";
            ResultSet rs = instruction.executeQuery(query);
            tblModel.setColumnIdentifiers(new Object[]{
                "ID",
                "Country",
                "Region"});
            while(rs.next()) {
                tblModel.addRow(new Object[]{
                    rs.getString("country_id"),
                    rs.getString("country"),
                    rs.getString("region")});
            }
            disconnect();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return tblModel;

    }

    public void insertCountry(String country_id, String name, String fk_region) {
        try {
            Regions regionsModel = new Regions();
            HashMap<Integer, String> regions = regionsModel.getHashRegions();

            for(HashMap.Entry entry: regions.entrySet()){
                if(fk_region.equals(entry.getValue())){
                    fk_region = entry.getKey().toString();
                    break; //One to one map break
                }
            }
            conn = getConnection();

            String query = "INSERT INTO countries (country_id, name, fk_region) "
                    + "VALUES ('"+country_id+"', '"+name+"', '"+fk_region+"')";

            Statement instruction = conn.createStatement();
            instruction.executeUpdate(query);
            System.out.println("Succesful Insert...");
            disconnect();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void deleteCountry(String id) {

        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "DELETE FROM countries WHERE country_id = '"+id+"'";
            instruction.executeUpdate(query);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally {
            disconnect();
        }
    }

    public void updateCountry(String country_id, String name, String fk_region) {
        try {
            Regions regionsModel = new Regions();
            HashMap<Integer, String> regions = regionsModel.getHashRegions();


            for(HashMap.Entry entry: regions.entrySet()){
                if(fk_region.equals(entry.getValue())){
                    fk_region = entry.getKey().toString();
                    break; //One to one map break
                }
            }


            conn = getConnection();

            Statement instruction = conn.createStatement();
            String query = "UPDATE countries SET "
                    + "country_id='" + country_id + "', "
                    + "name='"+name+"', "
                    + "fk_region='"+fk_region+"'"
                    + "WHERE (`country_id` = '"+country_id+"')";
            instruction.executeUpdate(query);
            //PreparedStatement ps = conn.prepareStatement(query);
            //ps.execute
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            System.out.println("Updated...");
            disconnect();
        }
    }

    public String[] getCountriesList() throws Exception {
        String[] arrCountries;
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT concat(country_id, ' - ', name) as country FROM countries;";
            ResultSet rs = instruction.executeQuery(query);
            rs.last();                      //go to the last one
            int countRows = rs.getRow();    //to get number of rows
            arrCountries = new String[countRows + 1];
            rs.beforeFirst();               //restart position again
            arrCountries[0] = "Select Country";
            for (int i = 1; i < countRows; i++) {
                rs.next();
                arrCountries[i] = rs.getString("country");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return arrCountries;
    }


}
