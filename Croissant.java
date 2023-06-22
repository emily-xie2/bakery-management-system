public class Croissant extends Food {
    
    /**
     * Croissant
     * This constructor creates a new Croissant object.
     */
    public Croissant() {
        // Name, price
        super("Croissant", 6.00);
    }

    /**
     * itemDescription
     * This method prints the description of an item.
     */
    public void printItemDescription(){
        System.out.println("An extra flaky and buttery croissant.");
    }
}