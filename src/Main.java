import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberOfVillagers = 0;
        int numberOfMafias = 0;
        int dayCounter = 1;
        int nightCounter = 1;
        String order;
        System.out.println("Welcome to " + ANSI_RED + "MAFIA" + "\n" + ANSI_RESET + "Enter 'create_game' to start a new game");
        order = scan.next();
        while (!order.equals("create_game")){
            System.out.println(ANSI_RED + "no game created");
            order = scan.next();
        }
    }
}
