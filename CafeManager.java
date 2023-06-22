// Import classes
import java.util.ArrayList;
import java.util.Scanner;

public class CafeManager {
    Inventory inventory;
    Supplier supplier;
    ManagerUtility managerUtility;
    private ArrayList<CustomerOrder> onlineOrders;
    
    /**
     * CafeManager
     * This constructor creates a new Cafe Manager object.
     */
    public CafeManager() {
        this.inventory = new Inventory();
        this.supplier = new Supplier();
        this.managerUtility = new ManagerUtility();
        this.onlineOrders  = new ArrayList<CustomerOrder>();
    }
    
    /**
     * getOnlineOrders
     * This method prints all of the online orders.
     * @return A list of online orders
     */
    public ArrayList<CustomerOrder> getOnlineOrders() {
        return this.onlineOrders;
    }
    
    /**
     * printOnlineOrders
     * This method prints all of the online orders.
     */
    public void printOnlineOrders() {
        System.out.println("Current online orders: ");
        for (int i = 0; i< onlineOrders.size(); i++) {
            onlineOrders.get(i).displayInfo();
        }
    }
    
    /**
     * isValidOrder
     * This method checks if an order is valid by comparing the count of a certain item in the inventory to the order.
     * @param namesRequested An array of the items requested
     * @return False if there are not enough items in the inventory compared to the order amount, true otherwise
     */
    public boolean isValidOrder(ArrayList<String> namesRequested) {
        ArrayList<String> distinctNames = managerUtility.getDistinctNames(namesRequested);
        for (int i = 0; i < distinctNames.size(); i++) {
            String name = distinctNames.get(i);
            int inventoryCount = this.inventory.getCountOfName(name);
            int orderedCount = managerUtility.getCountOfName(name, namesRequested);
            if (inventoryCount < orderedCount) {
                System.out.println("Invalid request. Requested " + orderedCount + " " + name + ", but there are " + inventoryCount + " in the inventory.");
                return false;
            }
        }
        return true;
    }
    
    /**
     * processOrder
     * This method processes an order and does different things based on whether it's online or physical
     * @param order An object of customer order
     */
    public void processOrder(CustomerOrder order) {
        ArrayList<Item> items = new ArrayList<Item>();
        if (isValidOrder(order.orderedItemNames)) {
            if (order.orderType.equals("physical")) {
                items = this.inventory.getItemsFromOrder(order.orderedItemNames);
                this.inventory.removeItems(items);
                System.out.println("Order has been served.");
            } 
            else if (order.orderType.equals("online")) {
                items = this.inventory.putItemsOnHold(order.orderedItemNames);
                System.out.println("Order has been validated and processed.");
                order.updateItemInfo(items);
                onlineOrders.add(order);
            }
        }
    }
    
    /**
     * completeOnlineOrder
     * This method confirms an online order and removes the held items from the inventory.
     * @param id An int of the order ID
     */
    public void completeOnlineOrder(int id) {
        boolean completed = false;
        for (int i = onlineOrders.size() - 1; i >= 0; i--) {
            if (onlineOrders.get(i).customerOrderId == id && !completed) {
                CustomerOrder order = onlineOrders.get(i);
                this.inventory.removeItems(order.getItemsOnHold());
                System.out.println("Order has been completed and delivered.");
                completed = true;
                onlineOrders.remove(i);
            }
        }
        if (completed == false) {
            System.out.println("Order with ID: " + id + " does not exist.");
        }
    }
    
    /**
     * cancelOnlineOrder
     * This method cancels an online order based on the inputted ID.
     * @param id An int of the order ID
     */
    public void cancelOnlineOrder(int id) {
        boolean completed = false;
        for (int i = onlineOrders.size() - 1; i >= 0; i--) {
            if (onlineOrders.get(i).customerOrderId == id && !completed) {
                CustomerOrder order = onlineOrders.get(i);
                this.inventory.removeOnHoldStatus(order.getItemsOnHold());
                System.out.println("Order has been cancelled.");
                completed = true;
                onlineOrders.remove(i);
            }
        }
        if (completed == false) {
            System.out.println("Order with ID: " + id + " does not exist.");
        }
    }
    
    /**
     * getOrdersFromCustomer
     * This method takes inputs to get the customer's order.
     * @param isOnlineOrder A boolean that asks if the order is online or not.
     */
    public void getOrdersFromCustomer(boolean isOnlineOrder) {
        Scanner scan = new Scanner(System.in);
        // Gets a list of available items from the inventory
        ArrayList<String> itemNames = this.inventory.displayItemsWithOnHoldInfo(false);
        if (itemNames.isEmpty()) {
            System.out.println("There are no purchasable items.");
        }
        else {
            // Adds lunchbox option
            itemNames.add("Lunch Box");
            char lunchboxLetter = (char)('A' + (itemNames.size() - 1));
            System.out.println(lunchboxLetter + ") Combo: Lunch Box");
            // Asks for items purchased
            System.out.println("\n");
            System.out.println("What items did the customer order? (e.g. Type \"A5\" for 5 of item A). Type \"complete\" to confirm.");
            ArrayList<String> namesRequested = managerUtility.getItemsFromUser(itemNames);
            // If is an online order, manager processes online order
            if (isOnlineOrder) {
                OnlineOrder order = new OnlineOrder(namesRequested);
                processOrder(order);
            } 
            // Otherwise, manager processes physical order
            else {
                CafeOrder order = new CafeOrder(namesRequested);
                processOrder(order);
            }
            scan.close();
        }
    }
}