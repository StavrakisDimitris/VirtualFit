package main.java;

import Classes.Admin;
import Classes.Customer;
import Classes.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Show_Orders extends javax.swing.JFrame {

    public Show_Orders() {
        initComponents();
        combobox();
       
    }

    
    private void combobox(){
         PreparedStatement ps;
         String query = "Select orderid from orders";
        try{ 
            ps = MyConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
               String id = String.valueOf(rs.getInt("orderid"));  
               orders.addItem(id);
                 
            }
              
            MyConnection.getConnection().close();
        }     
        catch (SQLException e){
            e.printStackTrace();
        } 
    
    }
    
    
 
    public ArrayList<Product> orders(){
        String   order = orders.getSelectedItem().toString();
        String query = "Select * from orderdetail where orderid='"+order+"'";
        ArrayList<Product> products = new ArrayList<>();
        try{
            Statement stm = MyConnection.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(query); 
            Product product; 
            while(rs.next()){
                product = new Product(rs.getInt("orderid"),rs.getString("Name"),rs.getInt("quantity"),rs.getDouble("cost"));
                products.add(product);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return products;
    }

    public void show_orders(){
        ArrayList<Product> list = orders();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<list.size();i++){
            row[0] = list.get(i).getid();
            row[1] = list.get(i).getproductname();
            row[2] = list.get(i).getquantity();
            row[3] = list.get(i).getprice();
            model.addRow(row);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        log_out = new javax.swing.JButton();
        backToAdmin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        acceptBtn = new javax.swing.JButton();
        declineBtn = new javax.swing.JButton();
        orders = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Order's Requests");
        jLabel10.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout
