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
                else if (roleToAssign.equals("villager")) {
                    players[i] = new Villager(nameToAssign);
                    numberOfVillagers++;
                }
                else if (roleToAssign.equals("detective")) {
                    players[i] = new Detective(nameToAssign);
                    numberOfVillagers++;
                }
                else if (roleToAssign.equals("doctor")) {
                    players[i] = new Doctor(nameToAssign);
                    numberOfVillagers++;
                }
                else if (roleToAssign.equals("bulletproof")) {
                    players[i] = new Bulletproof(nameToAssign);
                    numberOfVillagers++;
                }
                else if (roleToAssign.equals("mafia")) {
                    players[i] = new Mafia(nameToAssign);
                    numberOfMafias++;
                }
                else if (roleToAssign.equals("godfather")) {
                    players[i] = new Godfather(nameToAssign);
                    numberOfMafias++;
                }
                else if (roleToAssign.equals("silencer")) {
                    players[i] = new Silencer(nameToAssign);
                    numberOfMafias++;
                }
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
        String voteOrder;
        String[] voterAndVotee;
        while (true){
            int r = 0;
            System.out.println(ANSI_YELLOW + "Day " + dayCounter++);
            while (true){
                int voterReminder = 0;
                int voteeReminder = 0;
                voteOrder = scan.nextLine();
                if (voteOrder.equals("end_vote"))
                    break;
                else {
                    voterAndVotee = voteOrder.split(" ");
                    for (int i = 0; i < players.length; i++) {
                        if (!voterAndVotee[0].equals(players[i].name) || !voterAndVotee[1].equals(players[i].name)) {
                            r++;
                        }
                        if (r == namesArr.length) {
                            System.out.println(ANSI_RED + "user not found");
                        }
                        if (voterAndVotee[0].equals(players[i].name))
                            voterReminder = i;
                        if (voterAndVotee[1].equals(players[i].name))
                            voteeReminder = i;
                    }
                    if (players[voterReminder].isSilence())
                        System.out.println(ANSI_RED + "voter is silenced");
                    else if (!players[voteeReminder].isAlive())
                        System.out.println(ANSI_RED + "votee already dead");
                    else players[voteeReminder].setNumberOfVotes(players[voteeReminder].getNumberOfVotes() + 1);
                }
                int maxVote = 0;
                int maxVotePlayer = 0;
                for (int i = 0; i < players.length; i++) {
                    if (players[i].getNumberOfVotes() > maxVote) {
                        maxVote = players[i].getNumberOfVotes();
                        maxVotePlayer = i;
                    }
                }
                int numberOfMaxVotePlayers = 0;
                for (Player player : players) {
                    if (player.getNumberOfVotes() == maxVote)
                        numberOfMaxVotePlayers++;
                }
                if (numberOfMaxVotePlayers > 1)
                    System.out.println(ANSI_YELLOW + "nobody died");
                else if (numberOfMaxVotePlayers == 1){
                    if (players[maxVotePlayer].getRole().equals("joker")) {
                        System.out.println(ANSI_YELLOW + "Joker WON!");
                        System.exit(0);
                    }
                    else {
                        players[maxVotePlayer].setAlive(false);
                        if (players[maxVotePlayer].isMafia())
                            numberOfMafias--;
                        else if (players[maxVotePlayer].isVillager()) {
                            numberOfVillagers--;
                            System.out.println(ANSI_RESET + players[maxVotePlayer].name + ANSI_RED + " died");
                        }
                    }
                    if (numberOfMafias == 0) {
                        System.out.println(ANSI_GREEN + "Villagers WON!");
                        System.exit(0);
                    }
                    else if (numberOfVillagers <= numberOfMafias) {
                        System.out.println(ANSI_RED + "Mafia Won");
                        System.exit(0);
                    }
                }
            }
            System.out.println(ANSI_BLUE + "Night " + nightCounter++);
            for (int i = 0; i < players.length; i++) {
                if (players[i].isHaveNightJob() && players[i].isAlive()){
                    System.out.println(players[i].name + " : " + players[i].getRole());
                }
            }
            firstLoop:while (true){
                String nightOrder;
                nightOrder = scan.nextLine();
                int subjectReminder = 0;
                int objectReminder = 0;
                String[] subjectAndObject = nightOrder.split(" ");
                for (int i = 0; i < players.length; i++) {
                    if (subjectAndObject[0].equals(players[i].name)){
                        if (!players[i].isHaveNightJob())
                            System.out.println(ANSI_RED + " user can not wake up during night");
                        else if (!players[i].isAlive()){
                            System.out.println(ANSI_RED + "user is dead");
                            continue firstLoop;
                        }
                        else subjectReminder = i;
                    }
                }
            }
        }
    }
}