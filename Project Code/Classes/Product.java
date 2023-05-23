package Classes;

public class Product {
    private int id;
    private String productName;
    private int quantity;
    private double price;

    public Product(int id, String productName, int quantity, double price) {
        setId(id);
        setProductName(productName);
        setQuantity(quantity);
        setPrice(price);
    }

    public Product(String productName, int quantity, double price) {
        this(0, productName, quantity, price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
