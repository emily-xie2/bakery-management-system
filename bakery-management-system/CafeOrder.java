// Import classes
import java.util.ArrayList;

public class CafeOrder extends CustomerOrder {
    final String CAFE_ORDER_TYPE = "physical";
    
    /**
     * CafeOrder
     * This constructor creates a new Cafe Order object.
     * @param orderedItems A list of ordered items 
     */
    public CafeOrder(ArrayList<String> orderedItems) {
        super(orderedItems);
        this.orderType = CAFE_ORDER_TYPE;
    }
}