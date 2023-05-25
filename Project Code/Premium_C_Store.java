package main.java;

import Classes.Order;
import Classes.Order_List;
import Classes.Product;
import main.java.Main_Class;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Premium_C_Store extends javax.swing.JFrame {

    private int i=1;
    private int id = 0;
    
    public Premium_C_Store() {
        initComponents();
        showProducts();
    }
    
    public ArrayList<Product> products(){
        String query = "Select ProductName,cost,quantity  from products where " +
                       "not exists(Select ProductName from offers where products.ProductName = offers.ProductName)";
       
        String query1 = "Select ProductName,newcost,quantity from offers";
        
        ArrayList<Product> products = new ArrayList<>();
        try{
            Statement stm = MyConnection.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(query1); 
            Product product1; 
            while(rs.next()){
                product1 = new Product(i,rs.getString("ProductName"),rs.getInt("quantity"),rs.getInt("newcost"));
                products.add(product1);
                i=i+1;
            }
        
            stm = MyConnection.getConnection().createStatement();
            rs = stm.executeQuery(query); 
            Product product; 
            while(rs.next()){
                product = new Product(i,rs.getString("ProductName"),rs.getInt("quantity"),rs.getInt("cost"));
                products.add(product);
                i=i+1;
            }
           
            i=1;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return products;
    }
    
    public void showProducts(){
        ArrayList<Product> list = products();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<list.size();i++){
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getProductName();
            row[2] = list.get(i).getQuantity();
            row[3] = list.get(i).getPrice();
            model.addRow(row);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        log_out = new javax.swing.JButton();
        backToCustomerMenu = new javax.swing.JButton();
        historyList = new javax.swing.JButton();
        submitOrder = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        productname = new javax.swing.JTextField();
        add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("FitLab Store");
        jLabel10.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        
           