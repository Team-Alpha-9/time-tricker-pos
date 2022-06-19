package views;

import com.mxrck.autocompleter.TextAutoCompleter;
import controllers.ConnectDB;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ravin
 */
public class frmGrn extends javax.swing.JFrame {

    private PreparedStatement pst;
    private Connection conn;

    int supId;
    String supName;

    public frmGrn() {
        initComponents();

        conn = ConnectDB.getConn();

        autocompletePCode();

        autocompletePName();
        autocompleteSupplierName();

    }

    /**
     * Creates new form frmGrm
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void resetLine() {
        txtPCode.setText("");
        txtPname.setText("");
        txtUnitPriceGrn.setText("0");
        txtQtyGrn.setText("0");
        txtAmountGrn.setText("0");
    }

    private void addItem() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) TblGRN.getModel();

            Vector<String> itenm = new Vector<>();
            itenm.add(txtPCode.getText());
            itenm.add(txtPname.getText());
            itenm.add(txtUnitPriceGrn.getText());
            itenm.add(txtQtyGrn.getText());
            itenm.add(txtAmountGrn.getText());

            dtm.addRow(itenm);
            txtInteCountGrn.setText(String.valueOf(TblGRN.getRowCount()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeAllItems() {
        try {
            while (TblGRN.getRowCount() > 0) {
                ((DefaultTableModel) TblGRN.getModel()).removeRow(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeItem() {
        try {
            if (TblGRN.getRowCount() > 0) {

                ((DefaultTableModel) TblGRN.getModel()).removeRow(TblGRN.getSelectedRow());

                txtInteCountGrn.setText(String.valueOf(TblGRN.getRowCount()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calAmount() {
        try {
            double qty = 0;
            double unitPrice = 0;
            double amount = 0;

            qty = Double.parseDouble(txtQtyGrn.getText());
            unitPrice = Double.parseDouble(txtUnitPriceGrn.getText());

            amount = (qty * unitPrice);

            txtAmountGrn.setText(String.valueOf(amount));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void increasTotalAmount() {
        try {
            double subToal = Double.parseDouble(txtTSubTotalGrn.getText());
            double total = Double.parseDouble(txtAmountGrn.getText());

            subToal += total;

            txtTSubTotalGrn.setText(String.valueOf(subToal));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void autocompletePCode() {
        ResultSet rs = null;
        try {
            TextAutoCompleter autoCompleter = new TextAutoCompleter(txtPCode);
            if (autoCompleter.getItems() != null) {
                autoCompleter.removeAllItems();
            }

            pst = conn.prepareStatement("SELECT code FROM product");
            rs = pst.executeQuery();

            while (rs.next()) {
                autoCompleter.addItem(rs.getString(1));
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

    private void autocompletePName() {
        ResultSet rs = null;
        try {
            TextAutoCompleter autoCompleter = new TextAutoCompleter(txtPname);
            if (autoCompleter.getItems() != null) {
                autoCompleter.removeAllItems();
            }

            pst = conn.prepareStatement("SELECT name FROM product");
            rs = pst.executeQuery();

            while (rs.next()) {
                autoCompleter.addItem(rs.getString(1));
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

    private void autocompleteSupplierName() {
        ResultSet rs = null;
        try {
            TextAutoCompleter autoCompleter = new TextAutoCompleter(tctSupplierName);
            if (autoCompleter.getItems() != null) {
                autoCompleter.removeAllItems();
            }

            pst = conn.prepareStatement("SELECT name FROM supplier");
            rs = pst.executeQuery();

            while (rs.next()) {
                autoCompleter.addItem(rs.getString(1));
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

    private int saveGrn() {
        int saveDone = 0;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("INSERT INTO grn(supplier_id ,date,sub_total,item_count,status,user_id  ) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, supId);
            pst.setString(2, ((JTextField) dcDateGrn.getDateEditor().getUiComponent()).getText());
            pst.setString(3, txtTSubTotalGrn.getText());
            pst.setString(4, txtInteCountGrn.getText());
            pst.setString(5, "Active");
            pst.setInt(6, 1);

            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                saveDone = rs.getInt(1);
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
        return saveDone;
    }

    private int saveGrnProduct(int grnId) {
        int saveDone = 0;
        try {

            conn.setAutoCommit(false);
            pst = conn.prepareStatement("INSERT INTO grn_product(grn_id, quantity, sale_price, product_code) VALUES(?,?,?,?)");

            for (int i = 0; i < TblGRN.getRowCount(); i++) {
                pst.setInt(1, grnId);
                pst.setString(2, TblGRN.getValueAt(i, 3).toString());
                pst.setString(3, TblGRN.getValueAt(i, 2).toString());
                pst.setString(4, TblGRN.getValueAt(i, 0).toString());
                pst.addBatch();
            }

            int[] executeBatch = pst.executeBatch();
            saveDone = executeBatch.length;
            conn.commit();
            conn.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
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

    private int updateStock() {
        int saveDone = 0;
        try {

            conn.setAutoCommit(false);
            pst = conn.prepareStatement("UPDATE stock SET qty = (qty - ?) WHERE id = ?");

            for (int i = 0; i < TblGRN.getRowCount(); i++) {

                pst.setDouble(1, Double.parseDouble(TblGRN.getValueAt(i, 3).toString()));
                pst.setString(2, TblGRN.getValueAt(i, 5).toString());

                pst.addBatch();
            }

            int[] executeBatch = pst.executeBatch();
            saveDone = executeBatch.length;
            conn.commit();
            conn.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
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

        tctSupplierName.setText("");
        txtInteCountGrn.setText("");
        txtTSubTotalGrn.setText("");

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tctSupplierName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dcDateGrn = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtInteCountGrn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTSubTotalGrn = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnOrder = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtPCode = new javax.swing.JTextField();
        txtPname = new javax.swing.JTextField();
        txtUnitPriceGrn = new javax.swing.JTextField();
        txtQtyGrn = new javax.swing.JTextField();
        txtAmountGrn = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnAddToCart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblGRN = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnRemoveAllGrn = new javax.swing.JButton();
        btnRemoveItemGrn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));

        jPanel4.setLayout(new java.awt.GridLayout(16, 1, 5, 5));

        jLabel1.setText("Supplier Name:");
        jPanel4.add(jLabel1);

        tctSupplierName.setToolTipText("Customer Name:");
        tctSupplierName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tctSupplierNameKeyReleased(evt);
            }
        });
        jPanel4.add(tctSupplierName);

        jLabel4.setText("Date");
        jPanel4.add(jLabel4);

        dcDateGrn.setForeground(java.awt.SystemColor.text);
        dcDateGrn.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(dcDateGrn);

        jLabel2.setText("Item Count:");
        jPanel4.add(jLabel2);

        txtInteCountGrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInteCountGrnActionPerformed(evt);
            }
        });
        jPanel4.add(txtInteCountGrn);

        jLabel3.setText("Sub Total");
        jPanel4.add(jLabel3);

        txtTSubTotalGrn.setText("0");
        txtTSubTotalGrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTSubTotalGrnActionPerformed(evt);
            }
        });
        jPanel4.add(txtTSubTotalGrn);

        jPanel5.setPreferredSize(new java.awt.Dimension(190, 50));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        btnOrder.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOrder.setText("Order");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });
        jPanel5.add(btnOrder);

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setText("Reset All");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel5.add(btnReset);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));

        jPanel6.setLayout(new java.awt.GridLayout(2, 5, 22, 5));

        jLabel9.setText("Product Code");
        jPanel6.add(jLabel9);

        jLabel10.setText("Product Name");
        jPanel6.add(jLabel10);

        jLabel13.setText("Unit Price");
        jPanel6.add(jLabel13);

        jLabel11.setText("Quantity");
        jPanel6.add(jLabel11);

        jLabel14.setText("Amount");
        jPanel6.add(jLabel14);

        txtPCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPCodeKeyReleased(evt);
            }
        });
        jPanel6.add(txtPCode);

        txtPname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(txtPname);

        txtUnitPriceGrn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(txtUnitPriceGrn);

        txtQtyGrn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQtyGrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyGrnActionPerformed(evt);
            }
        });
        txtQtyGrn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyGrnKeyReleased(evt);
            }
        });
        jPanel6.add(txtQtyGrn);

        txtAmountGrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountGrnActionPerformed(evt);
            }
        });
        jPanel6.add(txtAmountGrn);

        jPanel7.setLayout(new java.awt.GridLayout(1, 2, 10, 5));

        btnAddToCart.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddToCart.setText("Add To Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        jPanel7.add(btnAddToCart);

        TblGRN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TblGRN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Sale Price", "Qty", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblGRN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblGRNMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblGRN);
        if (TblGRN.getColumnModel().getColumnCount() > 0) {
            TblGRN.getColumnModel().getColumn(0).setResizable(false);
            TblGRN.getColumnModel().getColumn(1).setResizable(false);
            TblGRN.getColumnModel().getColumn(2).setResizable(false);
            TblGRN.getColumnModel().getColumn(3).setResizable(false);
            TblGRN.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel8.setLayout(new java.awt.GridLayout(1, 2, 10, 5));

        btnRemoveAllGrn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemoveAllGrn.setText("Remove All");
        btnRemoveAllGrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllGrnActionPerformed(evt);
            }
        });
        jPanel8.add(btnRemoveAllGrn);

        btnRemoveItemGrn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemoveItemGrn.setText("Remove Item");
        btnRemoveItemGrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveItemGrnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 396, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnRemoveItemGrn)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoveItemGrn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQtyGrnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyGrnKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            increasTotalAmount();
            /*getProductDataByCode(txtPCodeGrn.getText());*/
            txtQtyGrn.requestFocus(true);
        }

    }//GEN-LAST:event_txtQtyGrnKeyReleased

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        addItem();
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void TblGRNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblGRNMouseClicked

    }//GEN-LAST:event_TblGRNMouseClicked

    private void btnRemoveAllGrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllGrnActionPerformed
        removeAllItems();
    }//GEN-LAST:event_btnRemoveAllGrnActionPerformed

    private void btnRemoveItemGrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveItemGrnActionPerformed
        removeItem();
    }//GEN-LAST:event_btnRemoveItemGrnActionPerformed

    private void txtQtyGrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyGrnActionPerformed
        calAmount();
    }//GEN-LAST:event_txtQtyGrnActionPerformed

    private void txtAmountGrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountGrnActionPerformed

    }//GEN-LAST:event_txtAmountGrnActionPerformed

    private void txtInteCountGrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInteCountGrnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInteCountGrnActionPerformed

    private void txtTSubTotalGrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTSubTotalGrnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTSubTotalGrnActionPerformed

    private void tctSupplierNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tctSupplierNameKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            getSupplierDataByName(tctSupplierName.getText());
        }
    }//GEN-LAST:event_tctSupplierNameKeyReleased

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed

        int saveGrn = saveGrn();
        if (saveGrn > 0) {
            int saveGrnProduct = saveGrnProduct(saveGrn);
            if (saveGrnProduct > 0) {
                updateStock();
                //resetAll();
                JOptionPane.showMessageDialog(this, "Data Save Done ", "User Save", JOptionPane.INFORMATION_MESSAGE);

            }

        }
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        resetAll();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtPCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCodeKeyReleased
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            getSupplierDataByName(txtPCode.getText());
            txtPname.requestFocus(true);
        }
    }//GEN-LAST:event_txtPCodeKeyReleased

    private void getSupplierDataByName(String supName) {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT id, name FROM supplier WHERE name = ?");
            pst.setString(1, supName);
            rs = pst.executeQuery();

            if (rs.next()) {
                supId = rs.getInt(1);

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

    private void getProductIdByName(String pCode) {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT id, name FROM product WHERE name = ?");
            pst.setString(1, pCode);
            rs = pst.executeQuery();

            if (rs.next()) {
                txtPCode.setText(rs.getString(1));
                txtPname.setText(rs.getString(2));

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
            java.util.logging.Logger.getLogger(frmGrn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGrn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGrn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGrn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGrn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblGRN;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnRemoveAllGrn;
    private javax.swing.JButton btnRemoveItemGrn;
    private javax.swing.JButton btnReset;
    private com.toedter.calendar.JDateChooser dcDateGrn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JTextField tctSupplierName;
    private javax.swing.JTextField txtAmountGrn;
    private javax.swing.JTextField txtInteCountGrn;
    private javax.swing.JTextField txtPCode;
    private javax.swing.JTextField txtPname;
    private javax.swing.JTextField txtQtyGrn;
    private javax.swing.JTextField txtTSubTotalGrn;
    private javax.swing.JTextField txtUnitPriceGrn;
    // End of variables declaration//GEN-END:variables
}
