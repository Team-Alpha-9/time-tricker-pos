package views;

import controllers.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Kalindu
 */
//Save button Part
public class frmCustomers extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection conn;
    int cusID = 0;

    public frmCustomers() {
        initComponents();
        conn = ConnectDB.getConn();

        filltblcustomers();
    }

    private int saveCustomers() {
        int saveDone = 0;
        try {
            pst = conn.prepareStatement("INSERT INTO customer(name,address,email,contact) VALUES (?,?,?,?)");
            pst.setString(1, txtCusName.getText());
            pst.setString(2, txtCusAddress.getText());
            pst.setString(3, txtCusEmail.getText());
            pst.setString(4, txtMobileNumber.getText());

            saveDone = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            //alerts.getErrorAlert(e);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
                //alerts.getErrorAlert(e);
            }
        }
        return saveDone;
    }

    // Update Customers
    private int updateCustomers() {
        int saveDone = 0;
        try {
            pst = conn.prepareStatement("UPDATE customer SET name = ?, address = ? ,email = ?, contact = ? WHERE id = ?");
            pst.setString(1, txtCusName.getText());
            pst.setString(2, txtCusAddress.getText());
            pst.setString(3, txtCusEmail.getText());
            pst.setString(4, txtMobileNumber.getText());

            saveDone = pst.executeUpdate();
            // saveDone = Statement.RETURN_GENERATED_KEYS;
        } catch (Exception e) {
            e.printStackTrace();
            //alerts.getErrorAlert(e);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
                //alerts.getErrorAlert(e);
            }
        }
        return saveDone;
    }

    // Delete Customers
    private int deleteCustomers() {
        int deleteDone = 0;
        try {
            pst = conn.prepareStatement("DELETE FROM customer WHERE id = ?");
            pst.setInt(1, cusID);

            deleteDone = pst.executeUpdate();
            // saveDone = Statement.RETURN_GENERATED_KEYS;
        } catch (Exception e) {
            e.printStackTrace();
            //alerts.getErrorAlert(e);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
                //alerts.getErrorAlert(e);
            }
        }
        return deleteDone;
    }

    // Reset Details
    private void resetAll() {
        {

            txtCusCode.setText("");
            txtCusName.setText("");
            txtMobileNumber.setText("");
            txtCusEmail.setText("");
            txtCusAddress.setText("");

            cusID = 0;

            filltblcustomers();
        }
    }

    // Details in the table
    private void getCustomersDetailByName(int cusId) {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM customer WHERE id = ? ");
            pst.setInt(1, cusId);
            rs = pst.executeQuery();

//            if (!rs.isBeforeFirst()) {
//                userType.resetAll();
//            }
            if (rs.next()) {
                cusID = rs.getInt(1);
                //txtCusCode.setText(rs.getString(2));
                txtCusName.setText(rs.getString(3));
                txtCusAddress.setText(rs.getString(4));
                txtMobileNumber.setText(rs.getString(5));
                txtCusEmail.setText(rs.getString(5));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                //alerts.getErrorAlert(e);
            }
        }
    }

    // Save to table part
    private void filltblcustomers() {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT id AS 'Id', name AS 'Customer Name',contact AS 'Mobile Number',email AS 'Email' FROM customer");
            rs = pst.executeQuery();

            //To remove previously added rows
            while (tblCustomer.getRowCount() > 0) {
                ((DefaultTableModel) tblCustomer.getModel()).removeRow(0);
            }

            // Fill data to tblUser
            tblCustomer.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                //alerts.getErrorAlert(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtMobileNumber = new javax.swing.JTextField();
        txtCusEmail = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtCusCode = new javax.swing.JTextField();
        txtCusName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCusAddress = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer");
        setType(java.awt.Window.Type.UTILITY);

        jPanel7.setLayout(new java.awt.GridLayout(1, 4, 10, 5));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel7.add(btnSave);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel7.add(btnDelete);

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel7.add(btnReset);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel7.add(btnUpdate);

        jPanel3.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        jLabel6.setText("Mobile Number");
        jPanel3.add(jLabel6);

        jLabel9.setText("Email");
        jPanel3.add(jLabel9);

        jPanel2.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        jLabel5.setText("Customer Code");
        jPanel2.add(jLabel5);

        jLabel2.setText("Customer Name");
        jPanel2.add(jLabel2);

        jLabel10.setText("Address");
        jPanel2.add(jLabel10);

        jPanel6.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        txtMobileNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMobileNumberActionPerformed(evt);
            }
        });
        jPanel6.add(txtMobileNumber);

        txtCusEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusEmailActionPerformed(evt);
            }
        });
        jPanel6.add(txtCusEmail);

        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 5, 5));
        jPanel4.add(txtCusCode);
        jPanel4.add(txtCusName);

        txtCusAddress.setColumns(20);
        txtCusAddress.setRows(5);
        jScrollPane1.setViewportView(txtCusAddress);

        jPanel4.add(jScrollPane1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Code", "Customer Name", "Address", "Mobile Number", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCustomer);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(953, 624));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMobileNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMobileNumberActionPerformed

    }//GEN-LAST:event_txtMobileNumberActionPerformed

    private void txtCusEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusEmailActionPerformed

    }//GEN-LAST:event_txtCusEmailActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int deleteCustomers = 0;
        if (cusID > 0) {

            int result = JOptionPane.showConfirmDialog(rootPane, "Sure? You want to delete?", txtCusName.getText(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                deleteCustomers = deleteCustomers();
            } else if (result == JOptionPane.NO_OPTION) {

            } else {

            }

            if (deleteCustomers > 0) {
                resetAll();
                JOptionPane.showMessageDialog(this, "User delete done", "User Delete", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int saveCustomers = saveCustomers();
        if (saveCustomers > 0) {
            //resetAll();
            JOptionPane.showMessageDialog(this, "Data Save Done ", "User Save", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int updateCustomers = updateCustomers();
        if (updateCustomers > 0) {

            resetAll();
            JOptionPane.showMessageDialog(this, "Data Update Done ", "User Update", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        if (tblCustomer.getModel().getRowCount() > 0) {
            try {
                int row = tblCustomer.getSelectedRow();
                cusID = Integer.parseInt(tblCustomer.getModel().getValueAt(row, 0).toString());
                getCustomersDetailByName(cusID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetAll();

    }//GEN-LAST:event_btnResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCustomers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextArea txtCusAddress;
    private javax.swing.JTextField txtCusCode;
    private javax.swing.JTextField txtCusEmail;
    private javax.swing.JTextField txtCusName;
    private javax.swing.JTextField txtMobileNumber;
    // End of variables declaration//GEN-END:variables

}
