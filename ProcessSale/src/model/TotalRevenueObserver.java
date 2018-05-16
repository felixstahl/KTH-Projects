package model;

/**
 * This is the observer, notified when the total revenue is updated.
 */
public interface TotalRevenueObserver {

    /**
     * Called when the total revenue is updated.
     * @param TotalRevenue This is the new total revenue.
     */
    void totalRevenueChanged(double TotalRevenue);
}
