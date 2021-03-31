class Silencer extends Player{
    protected boolean silenceForFirstTime = false;
    Silencer(String name){
        this.name = name;
        this.setMafia(true);
        this.setSilencer(true);
        this.setAlive(true);
        this.setHaveNightJob(true);
        this.setRole("silencer");
    }

    public boolean isSilenceForFirstTime() {
        return silenceForFirstTime;
    }

    public void setSilenceForFirstTime(boolean silenced) {
        this.silenceForFirstTime = silenced;
    }
}
