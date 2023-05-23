package classes;

public class Offers {
    private String itemListName;
    private double discount;
    private double cost;

    public Offers(String itemListName, double discount, double cost) {
        this.itemListName = itemListName;
        this.discount = discount;
        this.cost = cost;
    }

    public static double calculateNewCost(double discount, double cost) {
        double newCost = cost - (cost * (discount / 100));
        return newCost;
    }
}

In this alternative implementation, I made the following changes:

The package name has been changed to lowercase ("classes") to follow Java naming conventions.
The variable Cost has been changed to lowercase (cost) to follow Java naming conventions.
The method NewCost has been renamed to calculateNewCost to follow Java naming conventions.
The calculateNewCost method no longer initializes newCost to 0 since it is not necessary.
The code formatting has been adjusted for improved readability.
Please note that the functionality of the class remains the same, but the code structure and style have been slightly modified.