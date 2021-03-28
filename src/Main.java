import java.util.Scanner;

public class Main {

    public static Player[] players;


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void resetVotes(){
        for (int i = 0; i < players.length; i++) {
            players[i].setNumberOfVotes(0);
        }
    }

    public static boolean findUser(String name){
        int r = 0;
        for (int i = 0; i < players.length; i++) {
            if (!name.equals(players[i].name))
                r++;
        }
        return r != players.length;
    }

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
        System.out.println(ANSI_YELLOW + "Day " + dayCounter++);
        while (true){
            String silenced = "";
            while (true){
                int voterReminder = 0;
                int voteeReminder = 0;
                voteOrder = scan.nextLine();
                if (voteOrder.equals("end_vote"))
                    break;
                else {
                    voterAndVotee = voteOrder.split(" ");
                    if (!findUser(voterAndVotee[0])) {
                        System.out.println(ANSI_RED + "user not found");
                        continue;
                    }
                    else {
                        for (int i = 0; i < players.length; i++) {
                            if (voterAndVotee[0].equals(players[i].name))
                                voterReminder = i;
                            if (voterAndVotee[1].equals(players[i].name))
                                voteeReminder = i;
                        }
                    }
                    if (players[voterReminder].isSilence())
                        System.out.println(ANSI_RED + "voter is silenced");
                    else if (!players[voteeReminder].isAlive())
                        System.out.println(ANSI_RED + "votee already dead");
                    else players[voteeReminder].setNumberOfVotes(players[voteeReminder].getNumberOfVotes() + 1);
                }
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
            resetVotes();
            System.out.println(ANSI_BLUE + "Night " + nightCounter++);
            for (int i = 0; i < players.length; i++) {
                if (players[i].isHaveNightJob() && players[i].isAlive()){
                    System.out.println(players[i].name + " : " + players[i].getRole());
                }
            }
            String nightOrder;
            firstLoop:while (true){
                nightOrder = scan.nextLine();
                if (nightOrder.equals("end_night")) {
                    System.out.println(ANSI_YELLOW + "Day " + dayCounter++);
                    break;
                }
                if (nightOrder.equals("get_game_state")){
                    System.out.println(ANSI_RED + "Mafia = " + numberOfMafias);
                    System.out.println(ANSI_GREEN + "Villager = " + numberOfVillagers);
                }
                int subjectReminder = 0;
                int objectReminder = 0;
                String[] subjectAndObject = nightOrder.split(" ");
                for (int i = 0; i < players.length; i++) {
                    if (subjectAndObject[0].equals(players[i].name)){
                        if (!players[i].isHaveNightJob()) {
                            System.out.println(ANSI_RED + " user can not wake up during night");
                            continue firstLoop;
                        }
                        else if (!players[i].isAlive()){
                            System.out.println(ANSI_RED + "user is dead");
                            continue firstLoop;
                        }
                        else subjectReminder = i;
                    }
                    if (subjectAndObject[1].equals(players[i].name)){
                        if (!players[i].isAlive()){
                            System.out.println(ANSI_RED + "user is dead");
                            continue firstLoop;
                        }
                        else objectReminder = i;
                    }
                }

                if (players[subjectReminder] instanceof Silencer && !((Silencer) players[subjectReminder]).isSilenced()) {
                    silenced = players[objectReminder].name;
                    players[objectReminder].setSilence(true);
                    ((Silencer) players[subjectReminder]).setSilenced(true);
                }

                else if (players[subjectReminder] instanceof Mafia){
                    int shotPlayer = 0;
                    if (!findUser(subjectAndObject[1])){
                        System.out.println(ANSI_RED + "user not joined");
                        continue firstLoop;
                    }
                    else {
                        for (int i = 0; i < players.length; i++) {
                            if (subjectAndObject[1].equals(players[i].name))
                                shotPlayer = i;
                        }
                        if (!players[shotPlayer].isAlive) {
                            System.out.println(ANSI_RED + "votee already dead");
                            continue firstLoop;
                        }
                        else {
                            players[objectReminder].setNumberOfVotes(players[objectReminder].getNumberOfVotes() + 1);
                            ((Mafia) players[subjectReminder]).setVoted(true);
                        }
                    }
                }

                else if (players[subjectReminder] instanceof Doctor)
                    ((Doctor) players[subjectReminder]).save(subjectAndObject[1]);

                else if (players[subjectReminder] instanceof Detective){
                    if (!findUser(subjectAndObject[1])){
                        System.out.println(ANSI_RED + "user not found");
                        continue firstLoop;
                    }
                    else if (((Detective) players[subjectReminder]).isDetected()){
                        System.out.println(ANSI_RED + "detective has already asked");
                        continue firstLoop;
                    }
                    else {
                        for (int i = 0; i < players.length; i++) {
                            if (!players[i].isAlive()) {
                                System.out.println(ANSI_RED + "suspect is dead");
                                continue firstLoop;
                            }
                            else {
                                if (players[objectReminder].isMafia() && !players[objectReminder].isGodfather())
                                    System.out.println(ANSI_RED + "Yes");
                                else System.out.println(ANSI_GREEN + "No");
                            }
                        }
                    }
                }
            }

            maxVote = 0;
            maxVotePlayer = 0;
            for (int i = 0; i < players.length; i++) {
                if (players[i].getNumberOfVotes() > maxVote) {
                    maxVote = players[i].getNumberOfVotes();
                    maxVotePlayer = i;
                }
            }

            numberOfMaxVotePlayers = 0;
            for (int i = 0; i < players.length; i++) {
                if (players[i].getNumberOfVotes() == maxVote)
                    numberOfMaxVotePlayers++;
            }
            if (numberOfMaxVotePlayers > 2){
            }
            else if (numberOfMaxVotePlayers == 2){
                int numberOfSavedByDoc = 0;
                for (int i = 0; i < players.length; i++) {
                    if (players[i].getNumberOfVotes() == maxVote) {
                        if (players[i].isSavedByDoctor())
                            numberOfSavedByDoc++;
                    }
                }

                if (numberOfSavedByDoc == 0);
                else if (numberOfSavedByDoc == 1){
                    for (int i = 0; i < players.length; i++) {
                        if (players[i].getNumberOfVotes() == maxVote && !players[i].isSavedByDoctor() && !players[i].isBulletproof()) {
                            System.out.println(ANSI_RED + "mafia tried to kill " + ANSI_RESET + players[i].name + "\n" + players[i].name + " was killed");
                            players[i].setAlive(false);
                            numberOfVillagers--;
                        }
                        else if (players[i].getNumberOfVotes() == maxVote && !players[i].isSavedByDoctor() && players[i] instanceof Bulletproof){
                            if (((Bulletproof) players[i]).isShotForFirstTime()){
                                System.out.println(ANSI_RED + "mafia tried to kill " + ANSI_RESET + players[i].name + "\n" + players[i].name + " was killed");
                                players[i].setAlive(false);
                                numberOfVillagers--;
                            }
                            else ((Bulletproof) players[i]).setShotForFirstTime(true);
                        }
                    }
                }
            }

            else if (numberOfMaxVotePlayers == 1){
                if (players[maxVotePlayer].isSavedByDoctor());
                else if (players[maxVotePlayer] instanceof Bulletproof){
                    if (((Bulletproof) players[maxVotePlayer]).isShotForFirstTime()){
                        System.out.println(ANSI_RED + "mafia tried to kill " + ANSI_RESET + players[maxVotePlayer].name + "\n" + players[maxVotePlayer].name + " was killed");
                        numberOfVillagers--;
                    }
                    else ((Bulletproof) players[maxVotePlayer]).setShotForFirstTime(true); ;
                }
            }
            System.out.println("Silenced " + silenced);
            if (numberOfMafias == 0){
                System.out.println(ANSI_GREEN + "Villager WON");
                System.exit(0);
            }
            else if (numberOfMafias >= numberOfVillagers){
                System.out.println(ANSI_RED + "Mafia WON");
                System.exit(0);
            }
        }
    }
}