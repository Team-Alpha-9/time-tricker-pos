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
 * @author 94766
 */
public class frmInvoice extends javax.swing.JFrame {

    private PreparedStatement pst;
    private Connection conn;
    int cusId;

    /**
     * Creates new form invoices
     */
    public frmInvoice() {
        initComponents();

        conn = ConnectDB.getConn();

        autocompletePCode();
        autocompletePName();
        autocompleteCusName();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dcDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtInteCount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTAmount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNetAmount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPayAmount = new javax.swing.JTextField();
        lblDB = new javax.swing.JLabel();
        lblDB_Value = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnReset = new javax.swing.JButton();
        btnPay = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtPCode = new javax.swing.JTextField();
        txtPName = new javax.swing.JTextField();
        txtUnitPrice = new javax.swing.JTextField();
        lblQty = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnAddCart = new javax.swing.JButton();
        btnLineReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnRemoveAll = new javax.swing.JButton();
        btnRemoveItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));

        jPanel4.setLayout(new java.awt.GridLayout(16, 1, 5, 5));

        jLabel4.setText("Date");
        jPanel4.add(jLabel4);

        dcDate.setForeground(java.awt.SystemColor.text);
        dcDate.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(dcDate);

        jLabel1.setText("Customer Name:");
        jPanel4.add(jLabel1);

        txtCustomerName.setToolTipText("Customer Name:");
        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });
        txtCustomerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustomerNameKeyReleased(evt);
            }
        });
        jPanel4.add(txtCustomerName);

        jLabel2.setText("Item Count:");
        jPanel4.add(jLabel2);

        txtInteCount.setText("0");
        jPanel4.add(txtInteCount);

        jLabel3.setText("Total Amount:");
        jPanel4.add(jLabel3);

        txtTAmount.setText("0");
        jPanel4.add(txtTAmount);

        jLabel8.setText("Discount:");
        jPanel4.add(jLabel8);

        txtDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiscountKeyReleased(evt);
            }
        });
        jPanel4.add(txtDiscount);

        jLabel6.setText("Net Amount:");
        jPanel4.add(jLabel6);
        jPanel4.add(txtNetAmount);

        jLabel7.setText("Payable Amount:");
        jPanel4.add(jLabel7);

        txtPayAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayAmountKeyReleased(evt);
            }
        });
        jPanel4.add(txtPayAmount);

        lblDB.setText("Due / Balance:");
        jPanel4.add(lblDB);

        lblDB_Value.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        lblDB_Value.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDB_Value.setText("0.00");
        lblDB_Value.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblDB_Value);

        jPanel5.setPreferredSize(new java.awt.Dimension(190, 50));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setText("Reset");
        jPanel5.add(btnReset);

        btnPay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPay.setText("Pay");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });
        jPanel5.add(btnPay);

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
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
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

        jLabel12.setText("Avelable Qty");
        jPanel6.add(jLabel12);

        jLabel11.setText("Quantity");
        jPanel6.add(jLabel11);

        jLabel14.setText("Amount");
        jPanel6.add(jLabel14);

        txtPCode.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPCodeKeyReleased(evt);
            }
        });
        jPanel6.add(txtPCode);

        txtPName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPNameActionPerformed(evt);
            }
        });
        txtPName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPNameKeyReleased(evt);
            }
        });
        jPanel6.add(txtPName);

        txtUnitPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUnitPrice.setText("0");
        jPanel6.add(txtUnitPrice);

        lblQty.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQty.setText("0");
        jPanel6.add(lblQty);

        txtQty.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
        });
        jPanel6.add(txtQty);

        txtAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel6.add(txtAmount);

        jPanel7.setLayout(new java.awt.GridLayout(1, 2, 10, 5));

        btnAddCart.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddCart.setText("Add Cart");
        btnAddCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCartActionPerformed(evt);
            }
        });
        jPanel7.add(btnAddCart);

        btnLineReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLineReset.setText("Reset");
        btnLineReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLineResetActionPerformed(evt);
            }
        });
        jPanel7.add(btnLineReset);

        tblInvoice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Price", "Qty", "T Amount"
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
        tblInvoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInvoiceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblInvoice);
        if (tblInvoice.getColumnModel().getColumnCount() > 0) {
            tblInvoice.getColumnModel().getColumn(0).setResizable(false);
            tblInvoice.getColumnModel().getColumn(1).setResizable(false);
            tblInvoice.getColumnModel().getColumn(2).setResizable(false);
            tblInvoice.getColumnModel().getColumn(3).setResizable(false);
            tblInvoice.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel8.setLayout(new java.awt.GridLayout(1, 2, 10, 5));

        btnRemoveAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemoveAll.setText("Remove All");
        btnRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllActionPerformed(evt);
            }
        });
        jPanel8.add(btnRemoveAll);

        btnRemoveItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemoveItem.setText("Remove Item");
        btnRemoveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveItemActionPerformed(evt);
            }
        });
        jPanel8.add(btnRemoveItem);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 795, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void resetLine() {
        txtPCode.setText("");
        txtPName.setText("");
        txtUnitPrice.setText("0");
        txtQty.setText("0");
        txtAmount.setText("0");
        lblQty.setText("0");
    }

    private void addItem() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblInvoice.getModel();

            Vector<String> itenm = new Vector<>();
            itenm.add(txtPCode.getText());
            itenm.add(txtPName.getText());
            itenm.add(txtUnitPrice.getText());
            itenm.add(txtQty.getText());
            itenm.add(txtAmount.getText());

            dtm.addRow(itenm);
            txtInteCount.setText(String.valueOf(tblInvoice.getRowCount()));
            resetLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeAllItems() {
        try {
            while (tblInvoice.getRowCount() > 0) {
                ((DefaultTableModel) tblInvoice.getModel()).removeRow(0);
            }
            txtInteCount.setText(String.valueOf(tblInvoice.getRowCount()));
            txtTAmount.setText("0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeItem() {
        try {
            if (tblInvoice.getRowCount() > 0) {

                txtInteCount.setText(String.valueOf(tblInvoice.getRowCount()));

                decreasTotalAmount();

                ((DefaultTableModel) tblInvoice.getModel()).removeRow(tblInvoice.getSelectedRow());
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

            if (txtQty.getText().equals("")) {
                txtQty.setText("0");
            }

            qty = Double.parseDouble(txtQty.getText());
            unitPrice = Double.parseDouble(txtUnitPrice.getText());

            amount = (qty * unitPrice);

            txtAmount.setText(String.valueOf(amount));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void increasTotalAmount() {
        try {
            double totalAmount = Double.parseDouble(txtTAmount.getText());
            double amount = Double.parseDouble(txtAmount.getText());

            totalAmount += amount;

            txtTAmount.setText(String.valueOf(totalAmount));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void decreasTotalAmount() {
        try {
            double totalAmount = Double.parseDouble(txtTAmount.getText());
            double amount = Double.parseDouble(tblInvoice.getValueAt(tblInvoice.getSelectedRow(), 4).toString());

            totalAmount -= amount;

            txtTAmount.setText(String.valueOf(totalAmount));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calDiscount() {
        try {

            if (txtDiscount.getText().equals("")) {
                txtDiscount.setText("0");
            }

            double tAmount = Double.parseDouble(txtTAmount.getText());
            double dis = Double.parseDouble(txtDiscount.getText());

            double netAmount = (tAmount - dis);

            txtNetAmount.setText(String.valueOf(netAmount));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calBalance() {
        try {

            if (txtPayAmount.getText().equals("")) {
                txtPayAmount.setText("0");
            }

            double netAmount = Double.parseDouble(txtNetAmount.getText());
            double payAmount = Double.parseDouble(txtPayAmount.getText());

            double balance = 0;

            if (netAmount > payAmount) {
                balance = (netAmount - payAmount);
                lblDB.setText("Deu");
                lblDB_Value.setText(String.valueOf(balance));
            } else {
                balance = (payAmount - netAmount);
                lblDB.setText("Balance");
                lblDB_Value.setText(String.valueOf(balance));
            }

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
            TextAutoCompleter autoCompleter = new TextAutoCompleter(txtPName);
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

    private void autocompleteCusName() {
        ResultSet rs = null;
        try {
            TextAutoCompleter autoCompleter = new TextAutoCompleter(txtCustomerName);
            if (autoCompleter.getItems() != null) {
                autoCompleter.removeAllItems();
            }

            pst = conn.prepareStatement("SELECT name FROM customer");
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

    private void getProductDataByCode(String pCode) {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT product.name, stock.sale_price, stock.qty FROM product INNER JOIN stock ON product.code = stock.product_code  WHERE product.code = ?");
            pst.setString(1, pCode);
            rs = pst.executeQuery();

            if (rs.next()) {
                txtPName.setText(rs.getString(1));
                txtUnitPrice.setText(rs.getString(2));
                lblQty.setText(rs.getString(3));
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

    private void getProductDataByName(String pName) {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT product.code, stock.sale_price, stock.qty FROM product INNER JOIN stock ON product.code = stock.product_code  WHERE product.name = ?");
            pst.setString(1, pName);
            rs = pst.executeQuery();

            if (rs.next()) {
                txtPCode.setText(rs.getString(1));
                txtUnitPrice.setText(rs.getString(2));
                lblQty.setText(rs.getString(3));
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

    private void getCustomerDataByName(String cusName) {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT id, name FROM customer WHERE name = ?");
            pst.setString(1, cusName);
            rs = pst.executeQuery();

            if (rs.next()) {
                cusId = rs.getInt(1);
                txtCustomerName.setText(rs.getString(2));
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

    private int saveInvoice() {
        int saveDone = 0;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("INSERT INTO invoice(customer_id, invoice_date, item_count, total_amount, status, user_id) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, cusId);
            pst.setString(2, ((JTextField) dcDate.getDateEditor().getUiComponent()).getText());
            pst.setString(3, txtInteCount.getText());
            pst.setString(4, txtTAmount.getText());

            String status = "";

            if (lblDB.getText().equals("Deu")) {
                status = "Credit";
            } else {
                status = "Payed";
            }

            pst.setString(5, status);
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

    private void saveInvoiceItems(int invoiceId) {
        try {
            int raw = 0;
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("INSERT INTO invoice items(invoice_number, product_code, quantity, unit_price) VALUES(?,?,?,?)");

            while (tblInvoice.getRowCount() > 0) {
                pst.setInt(1, invoiceId);
                pst.setString(2, tblInvoice.getValueAt(raw, 0).toString());
                pst.setString(1, tblInvoice.getValueAt(raw, 3).toString());
                pst.setString(1, tblInvoice.getValueAt(raw, 4).toString());
                pst.addBatch();

                ++raw;
            }

            pst.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);

        } catch (Exception e) {
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
                //alerts.getErrorAlert(e);
            }
        }
    }

    private void btnAddCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCartActionPerformed
        addItem();
    }//GEN-LAST:event_btnAddCartActionPerformed

    private void btnLineResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLineResetActionPerformed
        resetLine();
    }//GEN-LAST:event_btnLineResetActionPerformed

    private void tblInvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInvoiceMouseClicked

    }//GEN-LAST:event_tblInvoiceMouseClicked

    private void btnRemoveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveItemActionPerformed
        removeItem();
    }//GEN-LAST:event_btnRemoveItemActionPerformed

    private void btnRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllActionPerformed
        removeAllItems();
    }//GEN-LAST:event_btnRemoveAllActionPerformed

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased
        if (lblQty.getText().equals(txtQty.getText()) || Double.parseDouble(lblQty.getText()) < Double.parseDouble(txtQty.getText())) {
            JOptionPane.showMessageDialog(this, "Qty is not enoghud", "Product Stock", JOptionPane.INFORMATION_MESSAGE);
            txtQty.setText(lblQty.getText());
        }

        calAmount();

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            increasTotalAmount();
            addItem();
            txtDiscount.requestFocus(true);
        }
    }//GEN-LAST:event_txtQtyKeyReleased

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    private void txtPNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPNameActionPerformed

    private void txtPCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCodeKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            getProductDataByCode(txtPCode.getText());
            txtQty.requestFocus(true);
        }
    }//GEN-LAST:event_txtPCodeKeyReleased

    private void txtDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscountKeyReleased
        calDiscount();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPayAmount.requestFocus(true);
        }

    }//GEN-LAST:event_txtDiscountKeyReleased

    private void txtPayAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayAmountKeyReleased
        calBalance();
    }//GEN-LAST:event_txtPayAmountKeyReleased

    private void txtPNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPNameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            getProductDataByName(txtPName.getText());
            txtQty.requestFocus(true);
        }
    }//GEN-LAST:event_txtPNameKeyReleased

    private void txtCustomerNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerNameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            getCustomerDataByName(txtCustomerName.getText());
        }
    }//GEN-LAST:event_txtCustomerNameKeyReleased

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        int saveInvoice = saveInvoice();
        if (saveInvoice > 0) {
            saveInvoiceItems(saveInvoice);
        }
    }//GEN-LAST:event_btnPayActionPerformed

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
            java.util.logging.Logger.getLogger(frmInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInvoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCart;
    private javax.swing.JButton btnLineReset;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnRemoveAll;
    private javax.swing.JButton btnRemoveItem;
    private javax.swing.JButton btnReset;
    private com.toedter.calendar.JDateChooser dcDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel lblDB;
    private javax.swing.JLabel lblDB_Value;
    private javax.swing.JLabel lblQty;
    private javax.swing.JTable tblInvoice;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtInteCount;
    private javax.swing.JTextField txtNetAmount;
    private javax.swing.JTextField txtPCode;
    private javax.swing.JTextField txtPName;
    private javax.swing.JTextField txtPayAmount;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtTAmount;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
