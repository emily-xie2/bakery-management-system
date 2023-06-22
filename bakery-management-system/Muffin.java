public class Muffin extends Food {
    
    /**
     * Muffin
     * This constructor creates a new Muffin object.
     */
    public Muffin() {
        // Name, price
        super("Muffin", 5.50);
    }
    
    /**
     * itemDescription
     * This method prints the description of an item.
     */
    public void printItemDescription(){
        System.out.println("A flavourful muffin with blueberries and honey.");
    }
}