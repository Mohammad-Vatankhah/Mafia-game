import java.util.Scanner;

public class Main {

    public static Player[] players;


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    //reset votes
    public static void resetVotes(){
        for (Player player : players) {
            player.setNumberOfVotes(0);
        }
    }

    //search for a name in players name
    public static boolean findUser(String name){
        int r = 0;
        for (Player player : players) {
            if (!name.equals(player.name))
                r++;
        }
        return r == players.length;
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
        while (!order.equals("create_game")) {
            System.out.println(ANSI_RED + "no game created");
            order = scan.next();
            names = scan.nextLine();
        }
        String[] namesArr = names.split(" ");
        String nameToAssign;
        String roleToAssign;
        players = new Player[namesArr.length - 1];
        System.out.println(ANSI_RESET + "Enter 'assign_role' (player_name) (role_name) to assign the roles.");
        firstLoop:for (int i = 0; i < namesArr.length - 1; ) {
            order = scan.next();
            nameToAssign = scan.next();
            roleToAssign = scan.next();

            //print error if we start the game before assign the roles
            while(order.equals("start_ game")){
                System.out.println(ANSI_RED + "one or more player do not have a role.");
                continue firstLoop;
            }

            int r = 0;
            for (String s : namesArr) {
                    if (!nameToAssign.equals(s)) {
                        r++;
                    }
                }
            int r1 = 0;
            for (String role : roles) {
                if (!roleToAssign.equals(role)) {
                    r1++;
                }
            }

            //print error if roles or player name entered incorrectly
            if (r1 == roles.length || r == namesArr.length) {
                System.out.println(ANSI_RED + "user or role not found");
                continue;
            }

            //assign roles to players
            else {
                if (roleToAssign.equals("joker"))
                    players[i] = new Joker(nameToAssign);
                else if (roleToAssign.equals("villager")) {
                    players[i] = new Villager(nameToAssign);
                    numberOfVillagers++;
                } else if (roleToAssign.equals("detective")) {
                    players[i] = new Detective(nameToAssign);
                    numberOfVillagers++;
                } else if (roleToAssign.equals("doctor")) {
                    players[i] = new Doctor(nameToAssign);
                    numberOfVillagers++;
                } else if (roleToAssign.equals("bulletproof")) {
                    players[i] = new Bulletproof(nameToAssign);
                    numberOfVillagers++;
                } else if (roleToAssign.equals("mafia")) {
                    players[i] = new Mafia(nameToAssign);
                    numberOfMafias++;
                } else if (roleToAssign.equals("godfather")) {
                    players[i] = new Godfather(nameToAssign);
                    numberOfMafias++;
                } else if (roleToAssign.equals("silencer")) {
                    players[i] = new Silencer(nameToAssign);
                    numberOfMafias++;
                }
            }
        i++;
        }


        System.out.println(ANSI_RESET + "Enter 'start_game' to start the game.");
        order = scan.next();
        while (!order.equals("start_game")){
            System.out.println(ANSI_RED + "Wrong order!Try again.");
            order = scan.next();
        }
        for (Player player : players) {
            System.out.println(ANSI_RESET + player.name + " : " + player.getRole());
        }
        System.out.println(ANSI_RED + "Ready?" + ANSI_YELLOW + " Set!" + ANSI_GREEN + " Go.");
        System.out.println(ANSI_YELLOW + "Day " + dayCounter++);
        String voteOrder;
        String[] voterAndVotee;
        while (true){
            String silenced = "";
            while (true){
                int voterReminder = 0;
                int voteeReminder = 0;
                voteOrder = scan.nextLine();
                voteOrder = scan.nextLine();
                if (voteOrder.equals("end_vote"))
                    break;

                //print game state
                else if (voteOrder.equals("get_game_state")) {
                    System.out.println(ANSI_RED + "Mafia = " + numberOfMafias);
                    System.out.println(ANSI_GREEN + "Villager = " + numberOfVillagers);
                }

                //distinguish who is voter and who is votee in the day
                else {
                    voterAndVotee = voteOrder.split(" ");
                        for (int i = 0; i < players.length; i++) {
                            if (voterAndVotee[0].equals(players[i].name))
                                voterReminder = i;
                            if (voterAndVotee[1].equals(players[i].name))
                                voteeReminder = i;
                    }

                    //print error if something is wrong with voter or votee
                    if (players[voterReminder].isSilence())
                        System.out.println(ANSI_RED + "voter is silenced");
                    else if (!players[voteeReminder].isAlive())
                        System.out.println(ANSI_RED + "votee already dead");
                    else if (findUser(players[voterReminder].name))
                        System.out.println(ANSI_RED + "user not found");
                    else if (!players[voterReminder].isAlive())
                        System.out.println(ANSI_RED + "voter already dead");
                    else players[voteeReminder].setNumberOfVotes(players[voteeReminder].getNumberOfVotes() + 1);
                }
            }

            //calculate who should be killed in the day
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

            //print day report
            if (numberOfMaxVotePlayers > 1)
                System.out.println(ANSI_YELLOW + "nobody died");
            else if (numberOfMaxVotePlayers == 1){
                if (players[maxVotePlayer].getRole().equals("joker")) {
                    System.out.println(ANSI_YELLOW + "Joker WON!");
                    System.exit(0);
                }
                else {
                    System.out.println(ANSI_RESET + players[maxVotePlayer].name + ANSI_RED + " died");
                    players[maxVotePlayer].setAlive(false);
                    if (players[maxVotePlayer].isMafia())
                        numberOfMafias--;
                    else if (players[maxVotePlayer].isVillager()) {
                        numberOfVillagers--;
                    }
                }

                //calculate if mafia or villager win in the day
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
            for (Player player : players) {
                if (player.isHaveNightJob() && player.isAlive()) {
                    System.out.println(player.name + " : " + player.getRole());
                }
            }
            String nightOrder;
            firstLoop:while (true) {

                nightOrder = scan.nextLine();

                if (nightOrder.equals("end_night")) {
                    System.out.println(ANSI_YELLOW + "Day " + dayCounter++);
                    break;
                }

                //print game state
                if (nightOrder.equals("get_game_state")) {
                    System.out.println(ANSI_RED + "Mafia = " + numberOfMafias);
                    System.out.println(ANSI_GREEN + "Villager = " + numberOfVillagers);

                  //handle night orders
                } else {
                    int subjectReminder = 0;
                    int objectReminder = 0;
                    String[] subjectAndObject = nightOrder.split(" ");
                    for (int i = 0; i < players.length; i++) {
                        if (subjectAndObject[0].equals(players[i].name)) {
                            if (!players[i].isHaveNightJob()) {
                                System.out.println(ANSI_RED + " user can not wake up during night");
                                continue firstLoop;
                            } else if (!players[i].isAlive()) {
                                System.out.println(ANSI_RED + "user is dead");
                                continue firstLoop;
                            } else subjectReminder = i;
                        }
                        if (subjectAndObject[1].equals(players[i].name)) {
                            if (!players[i].isAlive()) {
                                System.out.println(ANSI_RED + "user is dead");
                                continue firstLoop;
                            } else objectReminder = i;
                        }
                    }

                    //handle silencer actions in the night
                    if (players[subjectReminder] instanceof Silencer && ((Silencer) players[subjectReminder]).getSilenceCount() + 1 < nightCounter) {
                        silenced += players[objectReminder].name;
                        players[objectReminder].setSilence(true);
                        ((Silencer) players[subjectReminder]).setSilenceCount(1);

                      //handle mafias actions in the night
                    } else if (players[subjectReminder] instanceof Mafia) {
                        int shotPlayer = 0;
                        if (findUser(subjectAndObject[1])) {
                            System.out.println(ANSI_RED + "user not joined");
                        } else {
                            for (int i = 0; i < players.length; i++) {
                                if (subjectAndObject[1].equals(players[i].name))
                                    shotPlayer = i;
                            }
                            if (!players[shotPlayer].isAlive) {
                                System.out.println(ANSI_RED + "votee already dead");
                            } else {
                                players[objectReminder].setNumberOfVotes(players[objectReminder].getNumberOfVotes() + 1);
                                ((Mafia) players[subjectReminder]).setVoted(true);
                            }
                        }

                      //handle doctor actions in the night
                    } else if (players[subjectReminder] instanceof Doctor)
                        ((Doctor) players[subjectReminder]).save(subjectAndObject[1]);

                    //handle detective actions in the night
                    else if (players[subjectReminder] instanceof Detective) {
                        if (findUser(subjectAndObject[1])) {
                            System.out.println(ANSI_RED + "user not found");
                        } else if (((Detective) players[subjectReminder]).isDetected() + 1 > nightCounter) {
                            System.out.println(ANSI_RED + "detective has already asked");
                        } else {
                            if (!players[objectReminder].isAlive()) {
                                System.out.println(ANSI_RED + "suspect is dead");
                            } else {
                                if (players[objectReminder].isMafia() && !players[objectReminder].isGodfather()) {
                                    System.out.println(ANSI_RED + "Yes");
                                } else {
                                    System.out.println(ANSI_GREEN + "No");
                                }
                                ((Detective) players[subjectReminder]).setDetected(1);
                            }
                        }
                    }
                }
            }

            //calculate who should be killed in the night
            maxVote = 0;
            maxVotePlayer = 0;
            for (int i = 0; i < players.length; i++) {
                if (players[i].getNumberOfVotes() > maxVote) {
                    maxVote = players[i].getNumberOfVotes();
                    maxVotePlayer = i;
                }
            }

            numberOfMaxVotePlayers = 0;
            for (Player player : players) {
                if (player.getNumberOfVotes() == maxVote)
                    numberOfMaxVotePlayers++;
            }
            if (numberOfMaxVotePlayers > 2);

            else if (numberOfMaxVotePlayers == 2){
                int numberOfSavedByDoc = 0;
                for (Player player : players) {
                    if (player.getNumberOfVotes() == maxVote) {
                        if (player.isSavedByDoctor())
                            numberOfSavedByDoc++;
                    }
                }

                if (numberOfSavedByDoc == 0);
                else if (numberOfSavedByDoc == 1){
                    for (Player player : players) {
                        if (player.getNumberOfVotes() == maxVote && !player.isSavedByDoctor() && !player.isBulletproof()) {
                            System.out.println(ANSI_RED + "mafia tried to kill " + ANSI_RESET + player.name + "\n" + player.name + " was killed");
                            player.setAlive(false);
                            numberOfVillagers--;
                        } else if (player.getNumberOfVotes() == maxVote && !player.isSavedByDoctor() && player instanceof Bulletproof) {
                            if (((Bulletproof) player).isShotForFirstTime()) {
                                System.out.println(ANSI_RED + "mafia tried to kill " + ANSI_RESET + player.name + "\n" + player.name + " was killed");
                                player.setAlive(false);
                                numberOfVillagers--;
                            } else ((Bulletproof) player).setShotForFirstTime(true);
                        }
                    }
                }
            }

            //prints night report
            else if (numberOfMaxVotePlayers == 1){
                if (players[maxVotePlayer].isSavedByDoctor());
                else if (players[maxVotePlayer] instanceof Bulletproof){
                    if (((Bulletproof) players[maxVotePlayer]).isShotForFirstTime()){
                        System.out.println(ANSI_RED + "mafia tried to kill " + ANSI_RESET + players[maxVotePlayer].name + "\n" + players[maxVotePlayer].name + " was killed");
                        numberOfVillagers--;
                    }
                    else ((Bulletproof) players[maxVotePlayer]).setShotForFirstTime(true);
                }
            }
            System.out.println("Silenced " + silenced);

            //calculate if mafia or villager win in the night
            if (numberOfMafias == 0){
                System.out.println(ANSI_GREEN + "Villager WON");
                System.exit(0);
            }
            else if (numberOfMafias >= numberOfVillagers){
                System.out.println(ANSI_RED + "Mafia WON");
                System.exit(0);
            }

            // who was silenced in last round can speak and vote in the next round
            for (Player player : players) {
                if (player.isSilence())
                    player.setSilence(false);
            }
        }
    }
}