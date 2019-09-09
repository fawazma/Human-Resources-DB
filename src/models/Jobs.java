package models;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Jobs extends Model{
    
    public String[] getJobsList() throws Exception {
        String[] arrJobs;
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query ="SELECT job_id, title FROM jobs ORDER by title";
            ResultSet rs = instruction.executeQuery(query);
            rs.last();  //go to the last one
            int countRows = rs.getRow();
            arrJobs = new String[countRows+1];
            rs.beforeFirst();
            
            arrJobs[0] = "Select Job";
            for (int i = 0; i < countRows; i++) {
                rs.next();
                arrJobs[i+1] = rs.getString("job_id");
            }
            
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return arrJobs;
    }

    public void updateJob(String id, String title, String min_salary, String max_salary) {
        try {
            conn = getConnection();
            String query = "UPDATE jobs SET "
                    + "title = '"+title+"', "
                    + "min_salary = '"+min_salary+"', "
                    + "max_salary = '"+max_salary+"' "
                    + "WHERE (job_id = '"+id+"');";

            Statement instruction = conn.createStatement();
            instruction.executeUpdate(query);
            System.out.println("Succesful Update...");
            disconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void insertJob(String id, String title, String min_salary, String max_salary) {
        try {
            conn = getConnection();
            String query = "INSERT INTO jobs (job_id, title, min_salary, max_salary) "
                    + "VALUES ('"+id+"', '"+title+"', '"+min_salary+"', '"+max_salary+"');";
            System.out.println(query);
            Statement instruction = conn.createStatement();
            instruction.executeUpdate(query);
            System.out.println("Succesful Insert...");
            disconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public DefaultTableModel showAllRecords() {
        try {
            conn = getConnection();
            Statement instruction = conn.createStatement();
            String query = "SELECT * FROM jobs order by job_id;";
            ResultSet rs = instruction.executeQuery(query);
                        
            tblModel.setColumnIdentifiers(new Object[]{
                "ID",
                "Title",
                "Min Salary",
                "Max Salary"
            });
            while (rs.next()) {
                tblModel.addRow(new Object[]{
                    rs.getString("job_id"),
                    rs.getString("title"),
                    rs.getString("min_salary"),
                    rs.getString("max_salary")
                });
            }
            disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tblModel;
    }

}

