package main.java;

import Classes.Admin;
import Classes.Customer;
import Classes.Orders_History;
import Classes.Product;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import main.java.Main_Class;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class C_History_List extends javax.swing.JFrame {

    public C_History_List() {
        initComponents();
        populateComboBox();
    }
    
    int i = 1;

    private void populateComboBox() {
        String email = Customer.getEmail();
        PreparedStatement ps;
        String query = "SELECT orderid FROM orders WHERE customeremail='" + email + "'";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                String id = String.valueOf(i);
                orderid.addItem(id);
                i = i + 1;
            }
            MyConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Product> fetchOrders() {
        String order = orderid.getSelectedItem().toString();
        System.out.print(order);
        String query = "SELECT * FROM orderdetail WHERE orderid='" + order + "'";
        ArrayList<Product> products = new ArrayList<>();
        try {
            Statement stm = MyConnection.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(query);
            Product product;
            while (rs.next()) {
                product = new Product(rs.getInt("orderid"), rs.getString("Name"), rs.getInt("quantity"), rs.getDouble("cost"));
                products.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return products;
    }

    public void showOrders() {
        ArrayList<Product> list = fetchOrders();
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getid();
            row[1] = list.get(i).getproductName();
            row[2] = list.get(i).getquantity();
            row[3] = list.get(i).getprice();
            model.addRow(row);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        log_out = new javax.swing.JButton();
        backToOrder = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        exportPDF = new javax.swing.JButton();
        sendToEmail = new javax.swing.JButton();
        orderid = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("History Order List");

        jPanel5.setBackground(new java.awt.Color(0, 51, 102));

        jPanel6.setBackground(new java.awt.Color(0, 51, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Order History", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        log_out.setText("Log Out");
        log_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_outActionPerformed(evt);
            }
        });

        backToOrder.setText("Back to Order");
        backToOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToOrderActionPerformed(evt);
            }
        });

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Product Name", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(historyTable);

        exportPDF.setText("Export to PDF");
        exportPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPDFActionPerformed(evt);
            }
        });

        sendToEmail.setText("Send to Email");
        sendToEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendToEmailActionPerformed(evt);
            }
        });

        orderid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Order ID" }));
        orderid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backToOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(log_out)
                .addGap(36, 36, 36))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel10))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(exportPDF)
                        .addGap(18, 18, 18)
                        .addComponent(sendToEmail)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(log_out)
                    .addComponent(backToOrder)
                    .addComponent(orderid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportPDF)
                    .addComponent(sendToEmail))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }                         

    private void log_outActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // Add your handling code here
        Customer.setEmail(null);
        Customer.setFirstName(null);
        Customer.setLastName(null);
        new Login().setVisible(true);
        dispose();
    }                                       

    private void backToOrderActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // Add your handling code here
        new Order().setVisible(true);
        dispose();
    }                                           

    private void exportPDFActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // Add your handling code here
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filePath = chooser.getSelectedFile().getPath();
            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(filePath + ".pdf"));
                doc.open();
                PdfPTable table = new PdfPTable(4);
                table.addCell("Order ID");
                table.addCell("Product Name");
                table.addCell("Quantity");
                table.addCell("Price");
                for (int i = 0; i < historyTable.getRowCount(); i++) {
                    String id = historyTable.getValueAt(i, 0).toString();
                    String name = historyTable.getValueAt(i, 1).toString();
                    String quantity = historyTable.getValueAt(i, 2).toString();
                    String price = historyTable.getValueAt(i, 3).toString();
                    table.addCell(id);
                    table.addCell(name);
                    table.addCell(quantity);
                    table.addCell(price);
                }
                doc.add(table);
                doc.close();
                JOptionPane.showMessageDialog(null, "PDF Created Successfully");
            } catch (DocumentException | FileNotFoundException ex) {
                Logger.getLogger(C_History_List.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }                                         

    private void sendToEmailActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // Add your handling code here
        String email = JOptionPane.showInputDialog(null, "Enter Email Address:");
        if (email != null) {
            try {
                String filePath = "History_Order.pdf";
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(filePath));
                doc.open();
                PdfPTable table = new PdfPTable(4);
                table.addCell("Order ID");
                table.addCell("Product Name");
                table.addCell("Quantity");
                table.addCell("Price");
                for (int i = 0; i < historyTable.getRowCount(); i++) {
                    String id = historyTable.getValueAt(i, 0).toString();
                    String name = historyTable.getValueAt(i, 1).toString();
                    String quantity = historyTable.getValueAt(i, 2).toString();
                    String price = historyTable.getValueAt(i, 3).toString();
                    table.addCell(id);
                    table.addCell(name);
                    table.addCell(quantity);
                    table.addCell(price);
                }
                doc.add(table);
                doc.close();
                JOptionPane.showMessageDialog(null, "PDF Created Successfully");
                new SendEmail().sendMail(email, "Order History", "Please find attached the Order History.", filePath);
            } catch (DocumentException | FileNotFoundException ex) {
                Logger.getLogger(C_History_List.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }                                           

    private void orderidActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Add your handling code here
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.setRowCount(0);
        showOrders();
    }                                        

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new C_History_List().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton backToOrder;
    private javax.swing.JButton exportPDF;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton log_out;
    private javax.swing.JComboBox<String> orderid;
    private javax.swing.JButton sendToEmail;
    // End of variables declaration                   
}
