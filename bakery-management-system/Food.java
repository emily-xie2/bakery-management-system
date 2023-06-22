public class Food extends Item {
    
    /**
     * Food
     * This constructor creates a new Food object.
     * @param name A string of the food's name 
     * @param price A double of the food's price
     */
    public Food(String name, double price) {
        // Name, type, price, is returnable
        super(name, "Food", price, false);
    }
}