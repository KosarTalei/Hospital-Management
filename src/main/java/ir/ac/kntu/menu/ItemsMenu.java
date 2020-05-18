package ir.ac.kntu.menu;

import ir.ac.kntu.department.Item;
import ir.ac.kntu.helper.ScannerWrapper;

public class ItemsMenu {
    private static ItemsMenu instance = new ItemsMenu();

    private ItemsMenu() {
    }

    public static ItemsMenu getInstance() {
        return instance;
    }

    public Item.Option getOption() {
        Item.Option[] options = Item.Option.values();
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return Item.Option.UNDEFINED;
    }

    public void printTheMenu() {
        System.out.println("***********************************");
        System.out.println("Item options:");
        System.out.println("1-Equipment failure report");
        System.out.println("2-See room's item");
        System.out.println("3-See item's checkup date");
        System.out.println("4-See item is healthy?");
        System.out.println("5-Add item");
        System.out.println("6-Remove item");
        System.out.println("7-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }
}