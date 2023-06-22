public class Sandwich extends Food {
    
    /**
     * Sandwich
     * This constructor creates a new Sandwich object.
     */
    public Sandwich() {
        // Name, price
        super("Sandwich", 12.50);
    }

    /**
     * itemDescription
     * This method prints the description of an item.
     */
    public void printItemDescription(){
        System.out.println("Bacon, tomatoes, mayo, and lettuce on toasted bread.");
    }
}