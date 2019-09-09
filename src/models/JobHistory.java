package models;


import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JobHistory extends Model {

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

    public void updateDepartment(String text, String text0, String toString, Object selectedItem) {
        throw new UnsupportedOperationException("Not supported yet.");
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
            String query = "SELECT jh.id, concat(e.employee_id, ' - ', e.first_name, ' ', e.last_name) as employee, \n" +
                            "jh.fk_department, jh.fk_job,  jh.hire_date, jh.end_date FROM job_history jh \n" +
                            "inner join employees e on (jh.fk_employee=e.employee_id)";
            ResultSet rs = instruction.executeQuery(query);

            tblModel.setColumnIdentifiers(new Object[]{
                "ID",
                "Employee",
                "Job",
                "Department",
                "Hire Date",
                "End Date"
            });
            while (rs.next()) {
                tblModel.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("employee"),
                    rs.getString("fk_job"),
                    rs.getString("fk_department"),
                    rs.getString("hire_date"),
                    rs.getString("end_date")});
            }
            disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tblModel;
    }

    public void updateJobHistory(String text, Object selectedItem, Object selectedItem0, Object selectedItem1, String text0, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void insertJobHistory(String employee, String hire_date, String end_date, String fk_job, String fk_department) {
        try {
            conn = getConnection();
            String fk_employee= employee.split(" - ")[0];

            String query = "INSERT INTO job_history (fk_employee, hire_date, end_date, fk_job, fk_department) "
                    + "VALUES ('"+fk_employee+"', '"+hire_date+"', '"+end_date+"', '"+fk_job+"', '"+fk_department+"');";
            System.out.println(query);
            Statement instruction = conn.createStatement();
            instruction.executeUpdate(query);
            System.out.println("Succesful Insert...");
            disconnect();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
