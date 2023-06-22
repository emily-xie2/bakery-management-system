public class Coffee extends Drink {
    
    /**
     * Coffee
     * This constructor creates a new Coffee object. 
     */
    public Coffee() {
        // Name, price, is returnable
        super("Coffee", 1.75, false);
    }
    
    /**
     * itemDescription
     * This method prints the description of an item.
     */
    public void printItemDescription(){
        System.out.println("Home brewed coffee.");
    }
}