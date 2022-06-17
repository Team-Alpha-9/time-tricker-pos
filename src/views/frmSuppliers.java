/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ConnectDB;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author shehan
 */
public class frmSuppliers extends javax.swing.JFrame {

    PreparedStatement pst;
    Connection conn;
    int supID = 0;

    public frmSuppliers() {
        initComponents();
        conn = ConnectDB.getConn();

        filltblsupplier();
    }

    private int saveSuppliers() {
        int saveDone = 0;
        try {
            pst = conn.prepareStatement("INSERT INTO supplier(name,supplier_number, mobile_Number, address, Join_date, email, status ) VALUES (?,?,?,?,?,?,?)");
            pst.setString(1, txtName.getText());
            pst.setString(2, txtSupNumber.getText());
            pst.setString(3, txtMobileNumber.getText());
            pst.setString(4, txtAddress.getText());
            pst.setString(5, ((JTextField) cdJoinDate.getDateEditor().getUiComponent()).getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, cmbStatus.getModel().getSelectedItem().toString());

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

    private int updateSupplier() {

        int saveDone = 0;
        try {
            pst = conn.prepareStatement("UPDATE supplier SET supplier_number = ?, name = ?,  status = ?, mobile_number= ?,email = ?,address = ?,join_date = ? WHERE id = ?");
            pst.setString(1, txtSupNumber.getText());
            pst.setString(2, txtName.getText());
            pst.setString(3, cmbStatus.getSelectedItem().toString());
            pst.setString(4, txtMobileNumber.getText());
            pst.setString(5, txtEmail.getText());
            pst.setString(6, txtAddress.getText());
            pst.setString(7, ((JTextField) cdJoinDate.getDateEditor().getUiComponent()).getText());
            pst.setInt(8, supID);

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

    private void resetAll() {
        if (cmbStatus.getItemCount() > 0) {
            cmbStatus.setSelectedIndex(0);
        }
        txtSupNumber.setText("");
        txtName.setText("");
        txtMobileNumber.setText("");
        txtEmail.setText("");
        txtAddress.setText("");

        supID = 0;

        filltblsupplier();
    }

    private int deleteSupplier() {
        int deleteDone = 0;
        try {
            pst = conn.prepareStatement("DELETE FROM supplier WHERE id = ?");
            pst.setInt(1, supID);

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

    private void filltblsupplier() {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT id AS 'Id', name AS 'Name',mobile_number AS 'Mobile Number',email AS 'Email',join_date AS 'Join Date',status AS 'Status' FROM supplier");
            rs = pst.executeQuery();

            //To remove previously added rows
            while (tblsupplier.getRowCount() > 0) {
                ((DefaultTableModel) tblsupplier.getModel()).removeRow(0);
            }

            // Fill data to tblUser
            tblsupplier.setModel(DbUtils.resultSetToTableModel(rs));

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

    private void getSuppliersDataByName(int supId) {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT id, name, supplier_number, mobile_number, address, join_date, email, status FROM supplier WHERE id = ? ");
            pst.setInt(1, supId);
            rs = pst.executeQuery();

//            if (!rs.isBeforeFirst()) {
//                userType.resetAll();
//            }
            if (rs.next()) {
                supID = rs.getInt(1);
                txtName.setText(rs.getString(2));
                txtSupNumber.setText(rs.getString(3));
                txtMobileNumber.setText(rs.getString(4));
                txtAddress.setText(rs.getString(5));
                cdJoinDate.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(6)));
                txtEmail.setText(rs.getString(7));
                cmbStatus.getModel().setSelectedItem(rs.getString(8));

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtSupNumber = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        cmbStatus = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtMobileNumber = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        cdJoinDate = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblsupplier = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        jLabel5.setText("Supplier Number");
        jPanel1.add(jLabel5);

        jLabel2.setText("Name");
        jPanel1.add(jLabel2);

        jLabel10.setText("Status");
        jPanel1.add(jLabel10);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 136, 150));

        jPanel2.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        txtSupNumber.setText("sdsdfsd");
        jPanel2.add(txtSupNumber);

        txtName.setText("sddd");
        jPanel2.add(txtName);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Deactive" }));
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        jPanel2.add(cmbStatus);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 0, 367, 150));

        jPanel3.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        jLabel6.setText("Mobile Number");
        jPanel3.add(jLabel6);

        jLabel9.setText("Email");
        jPanel3.add(jLabel9);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 0, 100, 150));

        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        txtMobileNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMobileNumberActionPerformed(evt);
            }
        });
        jPanel4.add(txtMobileNumber);

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel4.add(txtEmail);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 6, 316, 140));

        jPanel5.setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        jLabel7.setText("Address");
        jPanel5.add(jLabel7);

        jLabel8.setText("Join Date");
        jPanel5.add(jLabel8);

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(956, 6, 122, 100));

        jPanel6.setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        jPanel6.add(jScrollPane1);

        cdJoinDate.setDateFormatString("yyyy-MM-dd");
        jPanel6.add(cdJoinDate);

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1084, 6, 258, 100));
        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 610, -1, -1));

        jPanel8.setLayout(new java.awt.GridLayout(1, 4, 5, 5));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel8.add(btnSave);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel8.add(btnUpdate);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel8.add(btnDelete);

        btnReset.setText("Resset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel8.add(btnReset);

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 600, 1260, 90));

        tblsupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Supplier Number", "Mobile Number", "Address", "Join Date", "Email", "Status"
            }
        ));
        tblsupplier.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblsupplierAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblsupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsupplierMouseClicked(evt);
            }
        });
        tblsupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblsupplierKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblsupplierKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblsupplier);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 1410, 383));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int saveSuppliers = saveSuppliers();
        if (saveSuppliers > 0) {
            resetAll();
            JOptionPane.showMessageDialog(this, "Data Save Done ", "User Save", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

  
        
    


    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void tblsupplierAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblsupplierAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblsupplierAncestorAdded

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int updateSuppliers = updateSupplier();
        if (updateSuppliers > 0) {

            resetAll();
            JOptionPane.showMessageDialog(this, "Data Update Done ", "User Update", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void txtMobileNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMobileNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMobileNumberActionPerformed

    private void tblsupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsupplierMouseClicked
        if (tblsupplier.getModel().getRowCount() > 0) {
            try {
                int row = tblsupplier.getSelectedRow();
                supID = Integer.parseInt(tblsupplier.getModel().getValueAt(row, 0).toString());
                getSuppliersDataByName(supID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tblsupplierMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        int deleteSupplier = 0;
        if (supID > 0) {

            int result = JOptionPane.showConfirmDialog(rootPane, "Sure? You want to delete?", txtName.getText(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                deleteSupplier = deleteSupplier();
            } else if (result == JOptionPane.NO_OPTION) {

            } else {

            }

            if (deleteSupplier > 0) {
                resetAll();
                JOptionPane.showMessageDialog(this, "User delete done", "User Delete", JOptionPane.INFORMATION_MESSAGE);
            }
        }


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        resetAll();

    }//GEN-LAST:event_btnResetActionPerformed

    private void tblsupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblsupplierKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tblsupplier.getModel().getRowCount() > 0) {
                try {
                    int row = tblsupplier.getSelectedRow();
                    supID = Integer.parseInt(tblsupplier.getModel().getValueAt(row, 0).toString());
                    getSuppliersDataByName(supID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_tblsupplierKeyReleased

    private void tblsupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblsupplierKeyPressed
        
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int deleteUser = 0;
            if (supID > 0) {

                int result = JOptionPane.showConfirmDialog(rootPane, "Sure? You want to delete?", txtName.getText(),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    deleteUser = deleteSupplier();
                } else if (result == JOptionPane.NO_OPTION) {

                } else {

                }

                if (deleteUser > 0) {
                    resetAll();
                    JOptionPane.showMessageDialog(this, "User delete done", "User Delete", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_tblsupplierKeyPressed

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
            java.util.logging.Logger.getLogger(frmSuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSuppliers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private com.toedter.calendar.JDateChooser cdJoinDate;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblsupplier;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMobileNumber;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSupNumber;
    // End of variables declaration//GEN-END:variables

}
