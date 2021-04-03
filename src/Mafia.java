class Mafia extends Player{
    protected String voteeName;
    protected boolean voted;
    Mafia(String name){
        this.name = name;
        this.setMafia(true);
        this.setHaveNightJob(true);
        this.setAlive(true);
        this.setRole("mafia");
    }

    public String getVoteeName() {
        return voteeName;
    }

    public void setVoteeName(String vote) {
        this.voteeName = vote;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
