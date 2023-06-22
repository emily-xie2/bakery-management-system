// Import classes
import java.util.ArrayList;

public class Supplier {
    private ArrayList<SupplierOrderInfo> requestedOrders;
    
    /**
     * Supplier
     * This constructor creates a new Supplier object.
     */
    public Supplier() {
        this.requestedOrders = new ArrayList<SupplierOrderInfo>();
    }
    
    /**
     * addOrder
     * This method adds a requested order to the supplier's list of orders.
     * @param info A SupplierOrderInfo object representing the newly requested order
     */
    public void addOrder(SupplierOrderInfo info) {
        this.requestedOrders.add(info);
    }
    
    /**
     * confirmOrder
     * This method confirms an order from the supploer has arrived and removes it from the list of requested orders.
     * @param orderId An integer of the order ID
     * @return A list of Item objects
     */
    public ArrayList<Item> confirmOrder(int orderId) {
        for (int i = 0; i < requestedOrders.size(); i++) {
            if (requestedOrders.get(i).orderId == orderId) {
                ArrayList<Item> result = requestedOrders.get(i).items;
                requestedOrders.remove(i);
                return result;
            }
        }
        return new ArrayList<Item>();
    }
    
    /**
     * cancelOrder
     * This method cancels an order by removing it from the list of requested orders.
     * @param orderId An integer of the order ID
     */
    public void cancelOrder(int orderId) {
        boolean found = false;
        for (int i = 0; i < requestedOrders.size(); i++) {
            if (requestedOrders.get(i).orderId == orderId) {
                requestedOrders.remove(i);
                found = true;
            }
        }
        if (found) {
            System.out.println("Successfully canceled the order with ID: " + orderId);
        } 
        else {
            System.out.println("Supplier does not have a request with ID: " + orderId + ". Cancellation failed.");
        }
    }
    
    /**
     * getRequestedOrders
     * This method gets all the current requested orders from the supplier.
     * @return List of SupplierOrderInfo Objects
     */
    public ArrayList<SupplierOrderInfo> getRequestedOrders() {
        return this.requestedOrders;
    }
    
    /**
     * printOrders
     * This method prints a list of the orders requested from the supplier.
     */
    public void printOrders() {
        if (requestedOrders.size() == 0) {
            System.out.println("There are no current requests for the supplier.");
        } 
        else {
            System.out.println("List of orders from the supplier: ");
            for (int i = 0; i < this.requestedOrders.size(); i++) {
                this.requestedOrders.get(i).displayInfo();
            }
        }
    }
}