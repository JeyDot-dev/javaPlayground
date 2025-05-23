package basicjava;

import java.util.Scanner;

public class finalProject {
    private final String[]    articles = {"chopsticks", "forks", "knives", "incense", "mugs", "napkins", "natto", "beer", "wand", "lego"};
    private final float[]     price = {2f, 3f, 4.2f, 3.99f, 5f, 1.50f, 6.45f, 3.20f, 18.5f, 12.40f};
//    private final int[]       cart = new int[10];
    private float       total = 0f;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        String menuInput;

        System.out.println("Welcome, here are the available articles:");
        for (int i = 0; i < articles.length; i++) {
            System.out.printf("%12s:%.2f", articles[i], price[i]);
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        while (true) {
            System.out.printf("--Main menu | current total is %.2fchf. |  enter \"shop\" or \"exit\": ", total);
            menuInput = scanner.nextLine().toLowerCase().trim();
            while (menuInput.equals("shop")) {
                System.out.print("Enter article or 'complete' to return to the menu: ");
                input = scanner.nextLine().toLowerCase().strip();
                if (input.equals("complete")){
                    System.out.println("Returning to main menu");
                    break;
                }
                try {
                    int i = searchArticle(input);
                    System.out.print("Please enter the quantity you would like to add to your cart: ");
                    int quantity = Integer.parseInt(scanner.nextLine().toLowerCase().strip());
//                    cart[i] += quantity;
                    total += price[i] * quantity;
                    System.out.printf("%d %s has been added to the cart.%n--The current total is: %.2fchf.%n", quantity, articles[i], total);
                }catch (ItemNotFoundException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (menuInput.equals("exit")) {
                System.out.println("Goodbye!");
                return;
            }
        }

    }

    private int searchArticle (String input) throws ItemNotFoundException{
        for (int i = 0; i < articles.length; i++) {
            if (input.equals(articles[i])) {
                System.out.println(i);
                return i;
            }
        }
        throw new ItemNotFoundException("Article \""+input+"\" not found");
    }
}

class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
