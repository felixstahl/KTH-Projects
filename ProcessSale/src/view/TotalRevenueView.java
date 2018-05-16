package view;

import model.TotalRevenueObserver;

/**
 * This is the display showing the total revenue to the user.
 */
public class TotalRevenueView implements TotalRevenueObserver {

    /**
     * This method prints the total revenue
     * @param TotalRevenue This is the total revenue.
     */
    public void totalRevenueChanged(double TotalRevenue) {
        showTotal(TotalRevenue);
    }

    private void showTotal(double runningTotal) {
        System.out.println("[UserLog] Total:" + runningTotal);
    }
}
