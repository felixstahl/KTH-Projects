package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class which holds the total sum of all purchases.
 */
public class TotalRevenue {
    private double totalRevenue;
    private List<TotalRevenueObserver> TotalRevenueObservers = new ArrayList<>();

    /**
     * This is the constructor of the object TotalRevenue.
     * It sets the variable totalRevenue to zero as it is constructed.
     */
    public TotalRevenue() {
        this.totalRevenue = 0;
    }

    /**
     * This method adds the total price of each purchase to the variable.
     * @param revenue This is the total revenue which will show how much earnings the day has made.
     */
    public void addRevenue(double revenue) {
        totalRevenue += revenue;
        broadcast();
    }

    /**
     *This method adds an observer to the list of observers.
     * @param observer This observer is the TotalRevenueView(in our case) which will observe the total revenue.
     */
    public void addPriceObserver(TotalRevenueObserver observer) {
        TotalRevenueObservers.add(observer);
    }

    private void broadcast() {
        for (TotalRevenueObserver x : TotalRevenueObservers) {
            x.totalRevenueChanged(totalRevenue);
        }
    }
}
