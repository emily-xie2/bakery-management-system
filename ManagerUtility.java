// Import classes
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ManagerUtility {
    private final ArrayList<Item> ITEM_TEMPLATE;

    /**
     * ManagerUtility
     * This constructor creates a new Manager Utility object.
     */
    public ManagerUtility() {
        ITEM_TEMPLATE = generateItemTemplate();
    }
    
    /**
     * generateItemTemplate
     * This method generates a template for all of the available items in the store.
     * @return A template with all of the cafe items
     */
    private ArrayList<Item> generateItemTemplate() {
        ArrayList<Item> template = new ArrayList<Item>();
        template.add(new Coffee());
        template.add(new Croissant());
        template.add(new Donut());
        template.add(new Muffin());
        template.add(new Pop());
        template.add(new Sandwich());
        template.add(new Tea());
        template.add(new Wrap());
        return template;
    }
    
    /**
     * displayItemList
     * This method displays a list of items sorted by type and alphabetical name.
     * @param givenItems A list of given Item objects
     * @return A list of Strings representing the unique items
     */
    public static ArrayList<String> displayItemList(ArrayList<Item> givenItems) {
        // Sorting the item list based on type and name
        Collections.sort(givenItems, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                int compareType = item1.TYPE.compareTo(item2.TYPE);
                if (compareType != 0) {
                    return compareType;
                }
                return item1.name.compareTo(item2.name);
            }
        });
        int count = 0;
        char itemLetter = 'A';
        ArrayList<String> itemNames = new ArrayList<String>();
        // Printing out item details with itemLetter as a counter. Example: A) item details
        for (int i = 0; i < givenItems.size(); i++) {
            Item currentItem = givenItems.get(i);
            count ++;
            if (i == givenItems.size() - 1) {
                itemNames.add(currentItem.name);
                System.out.print(itemLetter + ") " + "Amount: " + count + ". \t Price: $" + currentItem.price + ". \t Name:  " + currentItem.name + ". \t Description:  ");
                currentItem.printItemDescription();
            } 
            else {
                Item nextItem = givenItems.get(i + 1);
                if (currentItem.name != nextItem.name) {
                    itemNames.add(currentItem.name);
                    System.out.print(itemLetter + ") " + "Amount: " + count + ". \t Price: $" + currentItem.price + ". \t Name:  " + currentItem.name + ". \t Description:  ");
                    currentItem.printItemDescription();
                    count = 0;
                    itemLetter++;
                }
            }
        }
        return itemNames;
    }
    
    /**
     * getAllItemTypes
     * This method gets a list of all of the available item types.
     * @return A list of Strings representing item names
     */
    public ArrayList<String> getAllItemTypes() {
        ArrayList<String> itemTypes = new ArrayList<String>();
        for (int i = 0; i < ITEM_TEMPLATE.size(); i++) {
            itemTypes.add(ITEM_TEMPLATE.get(i).name);
        }
        return itemTypes;
    }
    
    /**
     * getAllDrinks
     * This method gets a list of Drink objects from all the available item objects.
     * @return A list of Strings representing item names
     */
    public ArrayList<String> getAllDrinks() {
        ArrayList<String> itemTypes = new ArrayList<String>();
        char itemLetter = 'A';
        for (int i = 0; i < ITEM_TEMPLATE.size(); i++) {
            if (ITEM_TEMPLATE.get(i).TYPE.equals("Drink")) {
                itemTypes.add(ITEM_TEMPLATE.get(i).name);
                itemLetter++;
            }
        }
        return itemTypes;
    }
    
    /**
     * getAllReturnableItems
     * This method gets a list of returnable item types from all the available item objects.
     * @return A list of String representing item names
     */
    public ArrayList<String> getAllReturnableItems() {
        ArrayList<String> itemTypes = new ArrayList<String>();
        for (int i = 0; i < ITEM_TEMPLATE.size(); i++) {
            if (ITEM_TEMPLATE.get(i).isReturnable()) {
                itemTypes.add(ITEM_TEMPLATE.get(i).name);
            }   
        }
        return itemTypes;
    }
    
    /**
     * printItemTypesFromList
     * This method, given a list of item names, prints a list of the items with a letter identifier such as A, B, C, etc.
     * @param names A String list of item names
     */
    public void printItemTypesFromList(ArrayList<String> names) {
        char letter = 'A';
        for (int i = 0; i < names.size(); i++) {
            System.out.println(letter + ") " + names.get(i));
            letter++;
        }
    }
    
    /**
     * getDistinctNames
     * This method gets the distinct item names from a list of strings.
     * @param names A String list of item names
     * @return The filtered list of distinct names
     */
    public ArrayList<String> getDistinctNames(ArrayList<String> names) {
        ArrayList<String> filteredNames = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            if (!filteredNames.contains(names.get(i))) {
                filteredNames.add(names.get(i));
            }
        }
        return filteredNames;
    }
    
    /**
     * getCountOfName
     * This method gets a count of a certain item based on its name.
     * @param requestedName A string of the requested name
     * @param names A String list of item names
     * @return The number of items with the requested name among all of the items in the list
     */
    public int getCountOfName(String requestedName, ArrayList<String> names) {
        int count = 0;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(requestedName)) {
                count ++;
            }
        }
        return count;
    }
    
    /**
     * createItemFromName
     * This method creates an Item object based on the passed in item name.
     * @param name A string of the item name
     * @return An Item object
     * @exception IllegalArgumentException If the name passed in does not represent any items that the cafe offers
     */
    private Item createItemFromName(String name){
        if (name.equals("Sandwich")) {
            return new Sandwich();
        }
        else if (name.equals("Muffin")) {
            return new Muffin();
        }
        else if (name.equals("Wrap")) {
            return new Wrap();
        }
        else if (name.equals("Croissant")) {
            return new Croissant();
        }
        else if (name.equals("Donut")) {
            return new Donut();
        }
        else if (name.equals("Pop")) {
            return new Pop();
        }
        else if (name.equals("Coffee")) {
            return new Coffee();
        }
        else if (name.equals("Tea")) {
            return new Tea();
        }
        throw new IllegalArgumentException("Item with name " + name + " does not exist in the store.");
    }
    
    /**
     * createItemsFromName
     * This method creates items from a list of item names passed in.
     * @param namesRequested A list of Strings of the names requested
     * @return A list of Item objects
     */
    public ArrayList<Item> createItemsFromName(ArrayList<String> namesRequested) {
        ArrayList<Item> itemsToAdd = new ArrayList<Item>();
        for (int i = 0; i < namesRequested.size(); i++) {
            itemsToAdd.add(createItemFromName(namesRequested.get(i)));
        }
        return itemsToAdd;
    }

    /**
     * getItemFromInput
     * This method gets a single item from the input and checks if it is a valid item in the array list.
     * @param itemNames A String list of the items' names, which acts as a list of available options for the user
     * @return A String of the item's name
     */
    private String getItemFromInput(ArrayList<String> itemNames) {
        Scanner scan = new Scanner(System.in);
        String input;
        String itemName = "";
        boolean complete = false;
        //keeps scanning until the user enters a valid input
        while(!complete) {
            input = scan.nextLine();
            if (input.length() == 1 && input.charAt(0) >= 'A' && input.charAt(0) <= 'Z') {
                int index = letterToIndex(input.charAt(0));
                if (itemNames.size() > index) {
                    itemName = itemNames.get(index);
                    complete = true;
                }
                else {
                    System.out.println("Input letter is not part of the choices, please enter again.");
                }
            }
            else {
                System.out.println("Invalid input. Proper example: \"A\"");
            }
        }
        scan.close();
        return itemName;
    }
    
    /**
     * getItemsForLunchBox
     * This method gets the items the user wants for the lunch box.
     * @return A list of Item names that the user entered
     */
    public ArrayList<String> getItemsForLunchBox() {
        // Ask for Sandwich or Wrap
        System.out.println("Does the customer want a sandwich or a wrap?");
        ArrayList<String> foodOptions = new ArrayList<String>();
        foodOptions.add("Sandwich");
        foodOptions.add("Wrap");
        printItemTypesFromList(foodOptions);
        String food = getItemFromInput(foodOptions);
        
        // Asks for the type of drink
        System.out.println("What kind of drink does the customer want?");
        ArrayList<String> drinkOptions = getAllDrinks();
        printItemTypesFromList(drinkOptions);
        String drink = getItemFromInput(drinkOptions);
        
        // Generates a list of items that the lunchbox contains
        ArrayList<String> result = new ArrayList<String>();
        result.add(food);
        result.add(drink);
        result.add("Donut");
        return result;
    }
    
    /**
     * isValidInput
     * This method checks if the input is in the form of A5, etc.
     * @param input A string of the user input
     * @return False if the input is not in the correct form, true otherwise
     * @exception Catches non-numeric input thrown by parseInt and returns false, indicating invalid input
     */
    private boolean isValidInput(String input) {
        if (input.length() < 2) {
            return false;
        }
        char letter = input.charAt(0);
        if (letter < 'A' || letter > 'Z') {
            return false;
        }
        String count = input.substring(1);
        try {
            Integer.parseInt(count);
        } 
        catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * letterToIndex
     * This method finds a respective letter for an item.
     * @param letter A character of the alphabet
     * @return The integer index letter
     */
    private static int letterToIndex(char letter) {
        return letter - 'A';
    }
    
    /**
     * getItemsFromUser
     * This method takes in user input by presenting available inventory items to them.
     * @param availableItems A list of Strings representing item names
     * @return A list of Strings representing the items that the user have chosen
     */
    public ArrayList<String> getItemsFromUser(ArrayList<String> availableItems) {
        Scanner scan = new Scanner(System.in);
        String currentAnswer = "";
        String userOrderName = "";
        ArrayList<String> namesRequested = new ArrayList<String>();
        while(!currentAnswer.equals("complete")) {
            currentAnswer = scan.nextLine();
            if (currentAnswer.equals("complete")) {}
            // If the input is valid, then decode the input by converting the letter to appropriate index
            else if (isValidInput(currentAnswer)) {
                char letter = currentAnswer.charAt(0);
                int index = letterToIndex(letter);
                int count = Integer.parseInt(currentAnswer.substring(1));
                if (availableItems.size() > index) {
                    userOrderName = availableItems.get(index);
                    // Checks if the item is a lunch box; if so, perform different operations to get lunchbox inputs
                    if (userOrderName.equals("Lunch Box")) {
                        for (int i = 1; i <= count; i++) {
                            System.out.println("Please enter details for lunch box " + i + ".");
                            ArrayList<String> orderedItemNames = getItemsForLunchBox();
                            for (int j = 0; j < orderedItemNames.size(); j++) {
                                namesRequested.add(orderedItemNames.get(j));
                            }
                        }
                        System.out.println("Lunch box order complete, please enter other items or type \"complete\" to confirm the order.");
                    } 
                    else {
                        for (int i = 0; i < count; i++) {
                            namesRequested.add(userOrderName);
                        }
                    }
                } 
                else { 
                    System.out.println("Letter " + letter + " does not represent any item in the list.");
                }
            } 
            else {
                System.out.println("Invalid input format, proper example: A5");
            }
        }
        scan.close();
        return namesRequested;
    }
    
    /**
     * handleSupplierOrder
     * This method handles the input of names of items requested from the supplier.
     * @return A list of Strings representing the names of the requested items
     */
    public ArrayList<String> handleSupplierOrder() {
        System.out.println("What would you like to order? (e.g. Type A5 for 5 of item A). Type \"complete\" to confirm the order");
        System.out.println("List of available items: ");
        ArrayList<String> itemTypes = getAllItemTypes();
        printItemTypesFromList(itemTypes);
        ArrayList<String> namesRequested = getItemsFromUser(itemTypes);
        return namesRequested;
    }
    
    /**
     * getValidInt
     * This method continuously asks the user for an int until it is within range of the options.
     * @param max An int indicating the maximum number that is valid for the options
     * @exception Catches if the input is not an integer, prints that it is invalid
     */
    public int getValidInt(int max){
        Scanner scan = new Scanner(System.in);
        while(true){
            try{
                String userInput = scan.nextLine();
                int input = Integer.parseInt(userInput);
                // Returns the input if it is valid
                if (input > 0 && input <= max){
                    return input;
                }
                // Prints a message saying the input is invalid if the input is not a choice on the menu list
                else{
                    System.out.println("Invalid input. Please enter an integer between 1-" + max + ".");
                }
            }
            // Catches if the input is not an integer
            catch (Exception e){
                System.out.println("Invalid input. Please enter an integer between 1-" + max + ".");
            }
        }
    }
}