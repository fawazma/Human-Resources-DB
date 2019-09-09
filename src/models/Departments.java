package models;


import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Departments extends Model {

    public String[] getDepartmentsList() throws Exception {
        String[] arrDepartments;
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT department_id FROM departments ORDER by department_id";
            ResultSet rs = instruction.executeQuery(query);
            rs.last();                      //go to the last one
            int countRows = rs.getRow();    //to get number of rows
            arrDepartments = new String[countRows + 1];
            rs.beforeFirst();               //restart position again
            arrDepartments[0] = "Select Department";
            for (int i = 0; i < countRows; i++) {
                rs.next();
                arrDepartments[i + 1] = rs.getString("department_id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return arrDepartments;
    }


    public void deleteDepartment(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateDepartment(String department_id, String name, String employee_manager, String location) {
        String query="";
        try {
            Regions regionsModel = new Regions();
            conn = getConnection();

            String fk_employee_manager = employee_manager.split(" - ")[0];
            String fk_location = location.split(" - ")[0];

            query = "UPDATE `departments` "
                    + "SET `name` = '"+name+"', "
                    + "`fk_employee_manager` = '"+fk_employee_manager+"', "
                    + "`fk_location` = '"+fk_location+"' "
                    + "WHERE (`department_id` = '"+department_id+"')";

            Statement instruction = conn.createStatement();
            instruction.executeUpdate(query);
            System.out.println("Succesful Update...");
            disconnect();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, query + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void insertDepartment(String department_id, String name, String employee_manager, String location) {
        String query="";
        try {
            Regions regionsModel = new Regions();
            conn = getConnection();

            String fk_employee_manager = employee_manager.split(" - ")[0];
            String fk_location = location.split(" - ")[0];

            query = "INSERT INTO departments (department_id, name, fk_employee_manager, fk_location) VALUES "
                    + "('"+department_id+"', '"+name+"','"+fk_employee_manager+"', '"+fk_location+"');";

            Statement instruction = conn.createStatement();
            instruction.executeUpdate(query);
            System.out.println("Succesful Insert...");
            disconnect();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, query + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public DefaultTableModel showAllRecords() {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT d.department_id, d.name, concat(e.employee_id, ' - ', e.first_name, ' ',  e.last_name) as employee, "
                    + "concat(l.location_id, ' - ', l.street_address, '. ', l.city, ', ', l.state_province, ', ', fk_country) as location FROM departments d\n"
                    + "inner join employees e on (d.fk_employee_manager= e.employee_id)\n"
                    + "inner join locations l on (d.fk_location= l.location_id) order by d.name;";
            ResultSet rs = instruction.executeQuery(query);

            tblModel.setColumnIdentifiers(new Object[]{
                "ID",
                "Department",
                "Employee",
                "Location"
            });
            while (rs.next()) {
                tblModel.addRow(new Object[]{
                    rs.getString("department_id"),
                    rs.getString("name"),
                    rs.getString("employee"),
                    rs.getString("location")});
            }
            disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tblModel;
    }


}
