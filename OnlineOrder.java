// Import classes
import java.util.ArrayList;

public class OnlineOrder extends CustomerOrder {
    final String ONLINE_ORDER_TYPE = "online";
    
    /**
     * OnlineOrder
     * This constructor creates a new Online Order object based on the list of ordered items.
     * @param orderedItems A String list of ordered items
     */
    public OnlineOrder(ArrayList<String> orderedItems) {
        super(orderedItems);
        this.orderType = ONLINE_ORDER_TYPE;
    }
}