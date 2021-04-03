class Silencer extends Mafia{
    protected boolean silenceForFirstTime = false;
    Silencer(String name){
        super(name);
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
