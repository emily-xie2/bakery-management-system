// Import classes
import java.util.ArrayList;

public class SupplierOrderInfo {
    private static int count = 0;
    int orderId;
    private boolean hasArrived;
    ArrayList<Item> items;
    
    /**
     * SupplierOrderInfo
     * This constructor creates a new Supplier Order Info object based on the list of items passed in.
     * @param items A list of Item objects
     */
    public SupplierOrderInfo(ArrayList<Item> items) {
        this.orderId = count;
        count++;
        this.hasArrived = false;
        this.items = items;
    }
    
    /**
     * setOrderStatus
     * This method sets an order to has arrived/not arrived.
     * @param hasArrived A boolean that asks whether or not the order from the supplier has arrived
     */
    public void setOrderStatus(boolean hasArrived) {
        this.hasArrived = hasArrived; 
    }
    
    /**
     * displayInfo
     * This method displays information regarding orders from the supplier.
     */
    public void displayInfo() {
        System.out.println("Order ID: " + this.orderId);
        System.out.println("Items include:");
        ManagerUtility.displayItemList(this.items);
        System.out.print("\n");
    }
}