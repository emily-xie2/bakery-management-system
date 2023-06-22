public class LunchBox {
    Food food;
    Drink drink;
    Donut donut;
    final double PRICE;
    
    /**
     * LunchBox
     * This constructor initializes a lunchbox based on the food, drink and donut given.
     * @param food A Food object
     * @param drink A Drink object
     * @param donut A Donut object
     */
    public LunchBox(Food food, Drink drink, Donut donut) {
        this.food = food;
        this.drink = drink;
        this.donut = donut;
        this.PRICE = 25.00;
    }
}