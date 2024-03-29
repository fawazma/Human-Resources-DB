
package views;
import models.Employees;
import javax.swing.table.DefaultTableModel;


public class DeptStatsV extends javax.swing.JFrame {
    /**
     * Creates new form tblModelVista
     */
    Employees conn = new Employees();
    boolean bFlag;
    DefaultTableModel model;

    /**
     * Creates new form EmployeesView
     */
    public DeptStatsV() throws Exception {
        initComponents();
        tblEmployeesStats.setModel(conn.getEmployeesStatsList());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployeesStats = new javax.swing.JTable();

        setTitle("Department Stats / Employees per department");

        tblEmployeesStats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Quantity", "Department"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmployeesStats.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeesStatsMouseClicked(evt);
            }
        });
        tblEmployeesStats.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEmployeesStatsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployeesStats);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmployeesStatsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeesStatsMouseClicked

    }//GEN-LAST:event_tblEmployeesStatsMouseClicked

    private void tblEmployeesStatsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEmployeesStatsKeyReleased

    }//GEN-LAST:event_tblEmployeesStatsKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEmployeesStats;
    // End of variables declaration//GEN-END:variables
}
