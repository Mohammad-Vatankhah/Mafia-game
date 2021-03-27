class Mafia extends Player{
    private boolean voted;
    Mafia(String name){
        this.name = name;
        this.setMafia(true);
        this.setHaveNightJob(true);
        this.setAlive(true);
        this.setRole("mafia");
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
