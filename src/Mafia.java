class Mafia extends Player{
    Mafia(String name){
        super(name);
        setMafia(true);
        setAlive(true);
        setHaveNightJob(true);
        players[counter].name = name;
        players[counter].setMafia(true);
        players[counter].setHaveNightJob(true);
        players[counter++].setAlive(true);
    }
    public void voteForShot(String name){
        for (int i = 0; i < players.length; i++) {
            if (name.equals(players[i].name))
                players[i].setNumberOfVotes(players[i].getNumberOfVotes() + 1);
        }
    }
}
