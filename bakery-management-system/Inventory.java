// Import classes
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    
    /**
     * Inventory
     * This constructor creates a new Inventory object.
     */
    public Inventory() {
        items = new ArrayList<Item>(); 
    }
    
    /**
     * getItems
     * This method gets the items in the inventory
     * @return A list of items
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    
    /**
     * displayAllItems
     * This method displays all of the items in the inventory.
     */
    public void displayAllItems() {
        if (items.size() == 0) {
            System.out.println("The inventory is currently empty.");
        } 
        else {
            ManagerUtility.displayItemList(items);
        }
    }
    
    /**
     * displayItemsWithOnHoldInfo
     * This method displays all items and filters by on hold status.
     * @param onHold A boolean that checks whether or not the item is on hold
     * @return A String list representing item names
     */
    public ArrayList<String> displayItemsWithOnHoldInfo(boolean onHold) {
        String onHoldDescription = "";
        if (onHold) {
            onHoldDescription = "on hold.";
        } 
        else {
            onHoldDescription = "not on hold.";
        }
        ArrayList<Item> result = new ArrayList<Item>() ;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isOnHold == onHold) {
                result.add(items.get(i));
            }
        }
        if (result.size() == 0) {
            System.out.println("The inventory currently does not have any items that are " + onHoldDescription);
        }
        else {
            System.out.println("Items: ");
        }
        return ManagerUtility.displayItemList(result);
    }
    
    /**
     * getCountOfName
     * This method gets the count of items with the passed in name.
     * @param requestedName The String of the name of the item (donut, coffee, etc.)
     * @return The count of items with their respective name
     */
    public int getCountOfName(String requestedName) {
        int count = 0;
        for (int i = 0; i < this.items.size(); i++) {
            if (items.get(i).name.equals(requestedName) && !items.get(i).isOnHold) {
                count++;
            }
        }
        return count;
    }

    /**
     * getItemsFromOrder
     * This method gets Items from the inventory based on the item's names
     * @param itemNames A list of item name Strings as reference for the Item objects
     */
    public ArrayList<Item> getItemsFromOrder(ArrayList<String> itemNames) {
        ArrayList<Item> result = new ArrayList<Item>();
        for (int j = 0; j < itemNames.size(); j++) {
            String currentName = itemNames.get(j);
            boolean found = false;
            for (int i = this.items.size() - 1; i >= 0 ; i--) {
                Item current = this.items.get(i);
                if (current.name.equals(currentName) && !current.isOnHold && !found) {
                    found = true;
                    result.add(current);
                }
            }
        }
        return result;
    }
    
    /**
     * removeItems
     * This method removes items that are not on hold.
     * @param itemNames A list of item name Strings to be deleted from the inventory
     */
    public void removeItems(ArrayList<Item> itemsToRemove) {
        for (int j = 0; j < itemsToRemove.size(); j++) {
            boolean found = false;
            for (int i = this.items.size() - 1; i >= 0 ; i--) {
                if (this.items.get(i).equals(itemsToRemove.get(j)) && !found) {
                    this.items.remove(i);
                    found = true;
                }
            }
        }
    }
    
    /**
     * addItems
     * This method adds multiple items to the inventory.
     * @param itemsToAdd A list of Item objects to be added into the inventory
     */
    public void addItems(ArrayList<Item> itemsToAdd) {
        for (int i = 0; i < itemsToAdd.size(); i++) {
            this.items.add(itemsToAdd.get(i));
        }
    }
    
    /**
     * removeOnHoldStatus
     * This method removes the on hold status of a specified list of items.
     * @param itemsToUpdate A list of Item objects to have their status updated
     */
    public void removeOnHoldStatus(ArrayList<Item> itemsToUpdate) {
        for (int i = 0; i < itemsToUpdate.size(); i++) {
            itemsToUpdate.get(i).isOnHold = false;
        }
    }
    
    /**
     * putItemsOnHold
     * This method, given a list of item names (such as Donut, Coffee), puts them on hold.
     * @param itemNames A list of item name Strings
     * @return A list of Item objects that are on hold
     */
    public ArrayList<Item> putItemsOnHold(ArrayList<String> itemNames) {
        ArrayList<Item> resultItems = new ArrayList<Item>();
        for (int j = 0; j < itemNames.size(); j++) {
            String currentName = itemNames.get(j);
            boolean found = false;
            for (int i = this.items.size() - 1; i >= 0; i--) {
                if (!found && this.items.get(i).name.equals(currentName) && this.items.get(i).isOnHold == false) {
                    this.items.get(i).isOnHold = true;
                    resultItems.add(this.items.get(i));
                    found = true;
                }
            }
        }
        return resultItems;
    }
    
    /**
     * getReturnableItems
     * This method gets a list of returnable items from the inventory.
     * @return A list of returnable items
     */
    public ArrayList<Item> getReturnableItems() {
        ArrayList<Item> result = new ArrayList<Item>();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).isReturnable() && !this.items.get(i).isOnHold) {
                result.add(this.items.get(i));
            }
        }
        return result;
    }
}