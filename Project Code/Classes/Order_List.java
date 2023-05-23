package classes;

import java.util.List;
import java.util.ArrayList;

public class OrderList {
    private List<Product> itemList;
    private boolean listValidation;
    private static double totalCost;
    private static int points;

    public OrderList() {
    }

    public void addTotalCost(double cost) {
        totalCost += cost;
    }

    public static double calculateProductCost(double price, int quantity) {
        return price * quantity;
    }

    public static double getTotalCost() {
        return totalCost;
    }

    public void addPoints(int additionalPoints) {
        points += additionalPoints;
    }

    public static void subtractPoints(int subtractedPoints) {
        points -= subtractedPoints;
    }

    public static double calculateCostAfterDiscount(int pointsUsed) {
        double costAfterDiscount = totalCost - (pointsUsed / 100.0);
        totalCost = 0;
        return costAfterDiscount;
    }
}