package models;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Employees extends Model{
    
    public void insertEmployee(String first_name, String last_name, String date_of_bird, String gender, String email, String phone, String salary, String fk_job, String fk_department, String active) throws Exception {
        try {
            conn = getConnection();
            
            String query = "INSERT INTO employees ( first_name, last_name, date_of_bird, gender, email, phone, salary, fk_job, fk_department, active) "
                    + "VALUES ('"+first_name+"', '"+last_name+"', '"+date_of_bird+"', '"+gender+"', '"+email+"', '"+phone+"', '"+salary+"', '"+fk_job+"', '"+fk_department+"', '"+active+"');";
            
            Statement instruction = conn.createStatement();
            instruction.executeUpdate(query);
            //PreparedStatement ps = conn.prepareStatement(query);
            //ps.executeUpdate();
            System.out.println("Succesful Insert...");
            disconnect();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void updateEmployee(int id, String first_name, String last_name, String date_of_bird, String gender, String email, String phone, String salary, String fk_job, String fk_department, String active) throws Exception {
        try {
            conn = getConnection();
            
            Statement instruction = conn.createStatement();
            String query = "UPDATE employees SET "
                    + "first_name = '"+first_name+"', "
                    + "last_name = '"+last_name+"', "
                    + "date_of_bird = '"+date_of_bird+"', "
                    + "gender = '"+gender+"', "
                    + "email = '"+email+"', "
                    + "phone = '"+phone+"', "
                    + "salary = '"+salary+"', "
                    + "fk_job = '"+fk_job+"', "
                    + "fk_department = '"+fk_department+"', "
                    + "active = 'Yes' "
                    + "WHERE (`employee_id` = '"+id+"')";
            instruction.executeUpdate(query);
            //PreparedStatement ps = conn.prepareStatement(query);
            //ps.execute
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Updated...");
            disconnect();
        }
    }
    
    public void deleteEmployee(int id) throws Exception {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "DELETE FROM employees WHERE employee_id = "+id;
            instruction.executeUpdate(query);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            disconnect();
        }
    }
    
    public ArrayList<String> queryBD() throws Exception {
        ArrayList<String> array = new ArrayList<String>();
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query ="SELECT * FROM employees WHERE employee_id = 1";
            ResultSet rs = instruction.executeQuery(query);
            
            while(rs.next()) {
                array.add(rs.getString("employee_id"));
                array.add(rs.getString("first_name"));
                array.add(rs.getString("last_name"));
                array.add(rs.getString("email"));
                array.add(rs.getString("gender"));
                array.add(rs.getString("date_of_bird"));
                array.add(rs.getString("phone"));
                array.add(rs.getString("salary"));
                array.add(rs.getString("fk_job"));
                array.add(rs.getString("fk_department"));	
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return array;
    }
    
    public ArrayList<String> queryNext(int id) throws Exception {
        ArrayList<String> array = new ArrayList<String>();
        
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT * FROM Employees WHERE employee_id = '"+id+"'";
            ResultSet rs = instruction.executeQuery(query);
            
            while(rs.next()) {
                array.add(rs.getString("employee_id"));
                array.add(rs.getString("first_name"));
                array.add(rs.getString("last_name"));
                array.add(rs.getString("email"));
                array.add(rs.getString("gender"));
                array.add(rs.getString("date_of_bird"));
                array.add(rs.getString("phone"));
                array.add(rs.getString("salary"));
                array.add(rs.getString("fk_job"));
                array.add(rs.getString("fk_department"));	                
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return array;
    }
    public DefaultTableModel showAllRecords(String where) {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT * FROM employees " + where;
            System.out.println(query);
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
                    rs.getString("employee_id"), 
                    rs.getString("first_name"), 
                    rs.getString("last_name"), 
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getString("date_of_bird"),
                    rs.getString("phone"),
                    rs.getString("salary"),
                    rs.getString("fk_job"),
                    rs.getString("fk_department")});
            }
            disconnect();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return tblModel;
    }
    public DefaultTableModel showAllRecords() {
        return showAllRecords("");
    }


    public String[] getEmployeesList() {
        String[] arrEmployees;
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT * FROM employees";
            ResultSet rs = instruction.executeQuery(query);
            
            rs.last();                      //go to the last one
            int countRows = rs.getRow();    //to get number of rows
            arrEmployees = new String[countRows + 1];
            rs.beforeFirst();               //restart position again
            arrEmployees[0] = "Select Employees";
            for (int i = 1; i < arrEmployees.length; i++) {
                rs.next();
                arrEmployees[i] = rs.getString("employee_id") + " - " + rs.getString("first_name") + " " + rs.getString("last_name");
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
            arrEmployees = new String[0];
        }
        disconnect();
        return arrEmployees;
    }
    public DefaultTableModel getEmployeesStatsList() {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT count(*) as quant, d.name FROM human_resources.employees e \n" +
"inner join departments d on (e.fk_department=d.department_id)\n" +
"group by fk_department" ;
            System.out.println(query);
            ResultSet rs = instruction.executeQuery(query);
            tblModel.setColumnIdentifiers(new Object[]{
                "Quantity", 
                "Department"});
            while(rs.next()) {
                tblModel.addRow(new Object[]{
                    rs.getString("quant"), 
                    rs.getString("name")
                });
            }
            disconnect();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return tblModel;
        
    }
    /**
     * 9. One query must contain a sub-query. 
     */
    public DefaultTableModel getEmployeesMore1YearList() {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT first_name, last_name, date_of_bird, gender, email, phone, salary, fk_job, fk_department, active FROM human_resources.employees "
                    + "where employee_id in "
                    + "(SELECT fk_employee FROM human_resources.job_history "
                    + "WHERE hire_date < DATE_SUB(NOW(),INTERVAL 1 YEAR));" ;
            System.out.println(query);
            ResultSet rs = instruction.executeQuery(query);
            tblModel.setColumnIdentifiers(new Object[]{
                "Firstname", 
                "Lastname", 
                "DOB", 
                "Gender", 
                "Email", 
                "Phone", 
                "Salary", 
                "Job", 
                "Department"});
            while(rs.next()) {
                tblModel.addRow(new Object[]{
                    rs.getString("first_name"), 
                    rs.getString("last_name"),
                    rs.getString("date_of_bird"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("salary"),
                    rs.getString("fk_job"),
                    rs.getString("fk_department")
                });
            }
            disconnect();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return tblModel;
        
    }
    
}

