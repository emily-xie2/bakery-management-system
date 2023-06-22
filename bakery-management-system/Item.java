public abstract class Item {
    static int idCount = 0;
    int id;
    String name;
    final String TYPE;
    double price;
    boolean isOnHold;
    private boolean isReturnable;
    
    /**
     * Item
     * This constructor creates a new Item object.
     * @param name A string of the item's name 
     * @param type A string of the item's type
     * @param price A double of the item's price
     * @param isReturnable A boolean that asks if the item is returnable
     */
    public Item(String name, String type, double price, boolean isReturnable) {
        this.id = idCount;
        idCount++;
        this.name = name;
        this.TYPE = type;
        this.price = price;
        this.isReturnable = isReturnable;
        this.isOnHold = false;
    }
    
    /**
     * setItemName
     * This method sets an item's name.
     * @param newName A string representing the name of an item
     */
    public void setItemName(String newName) {
        this.name = newName;
    }
    
    /**
     * setPrice
     * This method sets an item's price.
     * @param price A double of the item's price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * isReturnable
     * This method checks if an item is returnable.
     * @return A boolean providing information on whether or not the item is returnable
     */
    public boolean isReturnable() {
        return this.isReturnable;
    }

    /**
     * itemDescription
     * This method prints the description of an item.
     */
    public void printItemDescription() {
    }
}