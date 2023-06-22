public class Donut extends Food {
    
    /**
     * Donut
     * This constructor creates a new Donut object.
     */
    public Donut() {
        // Name, price
        super("Donut", 4.50);
    }
    
    /**
     * itemDescription
     * This method prints the description of an item.
     */
    public void printItemDescription(){
        System.out.println("Soft and moist fried donut with chocolate syrup.");
    }
}