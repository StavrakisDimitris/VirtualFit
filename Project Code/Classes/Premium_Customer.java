package Classes;

import java.util.List;


public class Premium_Customer extends Customer {
   
    private double discount;
    private List<Product> favouritePr;
    private List<Offers> offer; 
    private double points;
    
    public Premium_Customer() {
     
       super();
    }
     
   
    public void getPoints(double Points,double cost){ 
       double Discount = cost - (Points/100);
       this.points= this.points - Points;
        this.discount= Discount;
        }
    

    public double getDiscount(){ return discount;}
    
    public void savePoints(double cost){
          this.points = this.points + cost;
    }
    
    
    
}