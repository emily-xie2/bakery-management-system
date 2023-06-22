public class Pop extends Drink {
    
    /**
     * Pop
     * This constructor creates a new Pop object.
     */
    public Pop() {
        // Name, price, is returnable
        super("Pop", 1.0, true);
    }

    /**
     * itemDescription
     * This method prints the description of an item.
     */
    public void printItemDescription(){
        System.out.println("Everyone's favourite pop drink.");
    }
}