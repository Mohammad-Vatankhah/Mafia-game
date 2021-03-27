class Mafia extends Player{
    Mafia(String name){
        this.name = name;
        this.setMafia(true);
        this.setHaveNightJob(true);
        this.setAlive(true);
    }
    public void voteForShot(String name){
        for (int i = 0; i < Main.players.length; i++) {
            if (name.equals(Main.players[i].name))
                Main.players[i].setNumberOfVotes(Main.players[i].getNumberOfVotes() + 1);
        }
    }
}
