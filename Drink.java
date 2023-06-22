public class Drink extends Item {
    
    /**
     * Drink
     * This constructor creates a new Drink object.
     * @param name A string of the drink's name 
     * @param price A double of the drink's price
     * @param isReturnable A boolean that asks if the drink is returnable
     */
    public Drink(String name, double price, boolean isReturnable) {
        // Name, type, price, is returnable
        super(name, "Drink", price, isReturnable);
    }
}