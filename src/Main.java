import java.util.Scanner;

public class Main {

    public static Player[] players;


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
        String[] roles = {"joker", "villager", "detective", "doctor", "bulletproof", "mafia", "godfather", "silencer"};
        String order;
        System.out.println("Welcome to " + ANSI_RED + "MAFIA" + "\n" + ANSI_RESET + "Enter 'create_game' and players name to start a new game");
        order = scan.next();
        String names = scan.nextLine();
        String[] namesArr = names.split(" ");
        while (!order.equals("create_game")) {
            System.out.println(ANSI_RED + "no game created");
            order = scan.next();
        }
        String nameToAssign;
        String roleToAssign;
        players = new Player[namesArr.length];
        System.out.println(ANSI_RESET + "Enter 'assign_role' (player_name) (role_name) to assign the roles.");
        firstLoop:for (int i = 0; i < namesArr.length - 1; ) {
            int r = 0;
            order = scan.next();
            while(order.equals("start_ game")){
                System.out.println(ANSI_RED + "one or more player do not have a role.");
                order = scan.next();
            }
            nameToAssign = scan.next();
            roleToAssign = scan.next();
            for (String s : namesArr) {
                if (!nameToAssign.equals(s)) {
                    r++;
                }
                if (r == namesArr.length) {
                    System.out.println(ANSI_RED + "user not found");
                    continue firstLoop;
                }
            }
            r = 0;
            for (String role : roles) {
                if (!roleToAssign.equals(role)) {
                    r++;
                }
                if (r == roles.length) {
                    System.out.println(ANSI_RED + "role not found");
                    continue firstLoop;
                }
                if (roleToAssign.equals("joker"))
                    players[i] = new Joker(nameToAssign);
                else if (roleToAssign.equals("villager"))
                    players[i] = new Villager(nameToAssign);
                else if (roleToAssign.equals("detective"))
                    players[i] = new Detective(nameToAssign);
                else if (roleToAssign.equals("doctor"))
                    players[i] = new Doctor(nameToAssign);
                else if (roleToAssign.equals("bulletproof"))
                    players[i] = new Bulletproof(nameToAssign);
                else if (roleToAssign.equals("mafia"))
                    players[i] = new Mafia(nameToAssign);
                else if (roleToAssign.equals("godfather"))
                    players[i] = new Godfather(nameToAssign);
                else if (roleToAssign.equals("silencer"))
                    players[i] = new Silencer(nameToAssign);
            }
        i++;
        }
        System.out.println(ANSI_RESET + "Enter 'start_game' to start the game.");
        order = scan.next();
        if (!order.equals("start_game")){
            System.out.println(ANSI_RED + "Wrong order!Try again.");
        }
        for (int i = 0; i < players.length - 1; i++) {
            System.out.println(ANSI_RESET + players[i].name + " : " + players[i].getRole());
        }
        System.out.println(ANSI_RED + "Ready?" + ANSI_YELLOW + " Set!" + ANSI_GREEN + " Go.");
    }
}
