public class Tea extends Drink {
    
    /**
     * Tea
     * This constructor creates a new Tea object. 
     */
    public Tea() {
        // Name, price, is returnable
        super("Tea", 1.5, false);
    }
    
    /**
     * itemDescription
     * This method prints the description of an item.
     */
    public void printItemDescription(){
        System.out.println("Aromatic beverage made with fresh leaves.");
    }
}