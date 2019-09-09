package models;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Locations extends Model {

    public String[] getLocationsList() {
        String[] arrLocations;
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT * FROM locations LIMIT 50";
            ResultSet rs = instruction.executeQuery(query);

            rs.last();                      //go to the last one
            int countRows = rs.getRow();    //to get number of rows
            arrLocations = new String[countRows + 1];
            rs.beforeFirst();               //restart position again
            arrLocations[0] = "Select Location";
            for (int i = 1; i < arrLocations.length; i++) {
                rs.next();
                arrLocations[i] = rs.getString("location_id") + " - " + rs.getString("street_address") + ". " + rs.getString("city") + ", " + rs.getString("state_province") + ", " + rs.getString("fk_country");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            arrLocations = new String[0];
        }
        disconnect();
        return arrLocations;
    }

    public void insertLocation(String street_address, String postal_code, String city, String state_province, String country) {
        try {
            conn = getConnection();
            String fk_country = country.split(" - ")[0];
            String query = "INSERT INTO locations (street_address, postal_code, city, state_province, fk_country) "
                    + "VALUES ('" + street_address + "', '" + postal_code + "', '" + city + "', '" + state_province + "', '" + country + "');";

            Statement instruction = conn.createStatement();
            instruction.executeUpdate(query);
            System.out.println("Succesful Update...");
            disconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void updateLocation(String street_address, String postal_code, String city, String state_province, String country, String location_id) {
        try {
            conn = getConnection();
            String fk_country = country.split(" - ")[0];
            String query = "UPDATE locations "
                    + "SET street_address = '" + street_address + "', "
                    + "postal_code = '" + postal_code + "', "
                    + "city = '" + city + "', "
                    + "state_province = '" + state_province + "', "
                    + "fk_country = '" + fk_country + "' "
                    + "WHERE location_id = '" + location_id + "';";

            Statement instruction = conn.createStatement();
            instruction.executeUpdate(query);
            System.out.println("Succesful Update...");
            disconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    public TableModel showAllRecords() {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT *, concat(c.country_id, ' - ', c.name) as country "
                    + "FROM locations l inner join countries c "
                    + "on (l.fk_country=c.country_id)";
            ResultSet rs = instruction.executeQuery(query);

            tblModel.setColumnIdentifiers(new Object[]{
                "ID",
                "Street Address",
                "Postal Code",
                "City",
                "State",
                "Country"
            });
            while (rs.next()) {
                tblModel.addRow(new Object[]{
                    rs.getString("location_id"),
                    rs.getString("street_address"),
                    rs.getString("postal_code"),
                    rs.getString("city"),
                    rs.getString("state_province"),
                    rs.getString("country")
                });
            }
            disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tblModel;
    }
}
