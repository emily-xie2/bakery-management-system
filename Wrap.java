public class Wrap extends Food {
    
    /**
     * Wrap
     * This constructor creates a new Wrap object.
     */
    public Wrap() {
        // Name, price
        super("Wrap", 11.50);
    }  

    /**
     * itemDescription
     * This method prints the description of an item.
     */
    public void printItemDescription(){
        System.out.println("Grilled chicken with peanut sauce wrapped in a white tortilla.");
    }
}