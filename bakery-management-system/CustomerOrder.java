// Import classes
import java.util.ArrayList;

public abstract class CustomerOrder {
    private static int count = 0;
    int customerOrderId;
    ArrayList<String> orderedItemNames; // Stores the types for each item
    ArrayList<Item> orderedItems;
    String orderType;
    
    /**
     * CustomerOrder
     * This constructor creates a new Customer Order object.
     * @param orderedItemNames A list of the names of item ordered
     */
    public CustomerOrder(ArrayList<String> orderedItemNames) {
        this.customerOrderId = count;
        count++;
        this.orderedItemNames = orderedItemNames;
        this.orderedItems = new ArrayList<Item>();
        this.orderType = "";
    }

    /**
     * updateItemInfo
     * This method updates the online order's associated items.
     * @param items A list of Item objects
     */
    public void updateItemInfo(ArrayList<Item> items) {
        this.orderedItems = items;
    }

    /**
     * getItemsOnHold
     * This method gets all the items that the order is associated with.
     * @return A list of Item objects
     */
    public ArrayList<Item> getItemsOnHold() {
        return orderedItems;
    }

    /**
     * displayInfo
     * This method displays information for an order.
     */
    public void displayInfo() {
        System.out.println("Order ID: " + customerOrderId);
        System.out.println("Items include:");
        ManagerUtility.displayItemList(orderedItems);
        System.out.print("\n");
    }
}