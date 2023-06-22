// Import classes
import java.util.Scanner;
import java.util.ArrayList;

/** 
 * [ManagerMenu.java]
 * Desc: Helps in managing the inventory of a cafe.
 * @author Emily Xie
 * @version Apr 2022
 */ 

public class ManagerMenu {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        CafeManager manager = new CafeManager();
        final ManagerUtility MANAGER_UTIL = manager.managerUtility;
        final Inventory INVENTORY = manager.inventory;
        final Supplier SUPPLIER = manager.supplier;
        
        // Displays the main menu with options for user to choose from
        while(true){
            System.out.print("\n");
            System.out.println("Choose from the options below:");
            System.out.println("1) Supplier");
            System.out.println("2) Cafe");
            System.out.println("3) Online system");
            System.out.println("4) Print a list");
            int choice = MANAGER_UTIL.getValidInt(4);
            
            // Provides options regarding the supplier
            if (choice == 1){
                System.out.print("\n");
                System.out.println("What is the reason you are contacting the supplier?");
                System.out.println("1) Place an order to the supplier.");
                System.out.println("2) Cancel an order from the supplier.");
                System.out.println("3) Return items to the supplier.");
                System.out.println("4) Confirm order from supplier has arrived.");
                choice = MANAGER_UTIL.getValidInt(4);
                // Places an order to the supplier
                if (choice == 1) {
                    ArrayList<String> namesRequested = MANAGER_UTIL.handleSupplierOrder();
                    ArrayList<Item> itemsRequested = MANAGER_UTIL.createItemsFromName(namesRequested);
                    if (itemsRequested.size() > 0) {
                        SUPPLIER.addOrder(new SupplierOrderInfo(itemsRequested));
                    }
                    System.out.println("Successfully requested the order.");
                }
                // Cancels an order from the supplier
                else if (choice == 2) {
                    ArrayList<SupplierOrderInfo> orders = SUPPLIER.getRequestedOrders();
                    if (orders.size() != 0) {
                        // Print all the orders from the supplier
                        SUPPLIER.printOrders();
                        // Takes the ID of the request and removes it from the list of orders pending to arrive1
                        System.out.println("Enter the ID for the request you want to cancel:");
                        String id = scan.nextLine();
                        try {
                            SUPPLIER.cancelOrder(Integer.parseInt(id));
                        } 
                        // If the ID given is not a number, print a message saying so
                        catch (Exception e) {
                            System.out.println("The ID must be a number.");
                        }
                    } 
                    // If there are no requests, print a message saying so
                    else {
                        System.out.println("There are no orders requested from the suppliers.");
                    }
                }
                // Returns items to the supplier
                else if (choice == 3) {
                    ArrayList<Item> returnable = INVENTORY.getReturnableItems();
                    // Checks if there are returnable items in the inventory
                    if (returnable.size() == 0) {
                        System.out.println("No returnable items are found in the inventory.");
                    } 
                    else {
                        System.out.println("What would you like to return? (e.g. Type A5 for 5 of item A). Type \"complete\" to confirm the return.");
                        System.out.println("List of returnable items:");
                        // Finds the returnable items and returns them
                        ArrayList<String> itemNames = ManagerUtility.displayItemList(returnable);
                        ArrayList<String> namesRequested = MANAGER_UTIL.getItemsFromUser(itemNames);
                        if (manager.isValidOrder(namesRequested)) {
                            ArrayList<Item> items = INVENTORY.getItemsFromOrder(namesRequested);
                            INVENTORY.removeItems(items);
                            System.out.println("Successfully returned the items.");
                        }
                    }                          
                } 
                // Confirms the order from the supplier has arrived
                else if (choice == 4) {
                    ArrayList<SupplierOrderInfo> orders = SUPPLIER.getRequestedOrders();
                    if (orders.size() != 0) {
                        // Print all the orders from the supplier
                        SUPPLIER.printOrders();
                        // Takes the ID of the request and adds the items from supplier into the inventory
                        System.out.println("Enter the ID for the request you want to confirm:");
                        String id = scan.nextLine();
                        try {
                            ArrayList<Item> confirmedItems = SUPPLIER.confirmOrder(Integer.parseInt(id));
                            if (confirmedItems.size() > 0) {
                                INVENTORY.addItems(confirmedItems);
                                System.out.println("Successfully confirmed the order with ID: " + id);
                            } 
                            // If the ID given is not found, print a message saying so
                            else {
                                System.out.println("Supplier does not have a request with ID: " + id + ". Cancellation failed.");
                            }
                        } 
                        // If the ID given is not a number, print a message saying so
                        catch (Exception e) {
                            System.out.println("The ID must be a number.");
                        }
                    } 
                    // If there are no requests, print a message saying so
                    else {
                        System.out.println("There are no orders requested from the supplier.");
                    }  
                }
            }
            
            // Provides options regarding the cafe
            else if (choice == 2) {
                System.out.print("\n");
                System.out.println("What action involving the cafe would you like to make?");
                System.out.println("1) Input a customer's order.");
                System.out.println("2) Input a customer's return.");
                choice = MANAGER_UTIL.getValidInt(2);
                // Inputs a customer's order
                if (choice == 1) {
                    manager.getOrdersFromCustomer(false);
                } 
                // Inputs a customer's return
                else if (choice == 2) {
                    System.out.println("What would the customer like to return? (e.g. Type A5 for 5 of item A). Type \"complete\" to confirm the return.");
                    // Print out the items that are available for return
                    System.out.println("List of returnable items: ");
                    ArrayList<String> items = MANAGER_UTIL.getAllReturnableItems();
                    MANAGER_UTIL.printItemTypesFromList(items);
                    // Record user input
                    ArrayList<String> namesRequested = MANAGER_UTIL.getItemsFromUser(items);
                    ArrayList<Item> itemsRequested = MANAGER_UTIL.createItemsFromName(namesRequested);
                    // Add the returned items to the inventory
                    INVENTORY.addItems(itemsRequested);
                }
            }
            
            // Provides options regarding the online ordering system
            else if (choice == 3) {
                System.out.print("\n");
                System.out.println("What would you like to do with the online ordering system?");
                System.out.println("1) Input an online order.");
                System.out.println("2) Confirm an online order has been picked up.");
                System.out.println("3) Cancel an online order.");
                choice = MANAGER_UTIL.getValidInt(3);
                // Inputs an online order
                if (choice == 1) {
                    manager.getOrdersFromCustomer(true);
                } 
                // Confirms an online order has been picked up
                else if (choice == 2) {
                    // If there are no online orders, print a message saying so
                    if (manager.getOnlineOrders().size() == 0) {
                        System.out.println("There are currently no online orders.");
                    }
                    else {
                        manager.printOnlineOrders();
                        System.out.println("Enter the ID for the online order you want to confirm:");
                        String id = scan.nextLine();
                        try {
                            manager.completeOnlineOrder(Integer.parseInt(id));
                        } 
                        // If the ID given is not a number, print a message saying so
                        catch (Exception e) {
                            System.out.println("The ID must be a number.");
                        }
                    }
                } 
                // Cancels an online order
                else if (choice == 3) {
                    // If there are no online orders, print a message saying so
                    if (manager.getOnlineOrders().size() == 0) {
                        System.out.println("There are currently no online orders.");
                    } 
                    else {
                        // Prints all the online orders
                        manager.printOnlineOrders();
                        // Takes the ID of the online order and removes it from the list of orders pending to be picked up by customer
                        System.out.println("Enter the ID for the online order you want to cancel:");
                        String id = scan.nextLine();
                        try {
                            manager.cancelOnlineOrder(Integer.parseInt(id));
                        } 
                        // If the ID given is not a number, print a message saying so
                        catch (Exception e) {
                            System.out.println("The ID must be a number.");
                        }
                    }
                }
            } 
            
            // Prints a list
            else if (choice == 4) {
                System.out.print("\n");
                System.out.println("1) List of items on hold.");
                System.out.println("2) List of items not on hold.");
                System.out.println("3) List of all items requested from the supplier.");
                System.out.println("4) List of all items.");
                choice = MANAGER_UTIL.getValidInt(4);
                // Prints a list of items on hold
                if (choice == 1) {
                    INVENTORY.displayItemsWithOnHoldInfo(true);
                } 
                // Prints a list of items not on hold
                else if (choice == 2) {
                    INVENTORY.displayItemsWithOnHoldInfo(false);
                } 
                // Prints a list of all the items requested from the supplier
                else if (choice == 3) {
                    SUPPLIER.printOrders();
                }
                // Prints all items
                else if (choice == 4) {
                    if (INVENTORY.getItems().size() == 0) {
                        System.out.println("The inventory is currently empty.");
                    }
                    else {
                        ManagerUtility.displayItemList(INVENTORY.getItems());
                    }
                }
            }
        }
    }
}