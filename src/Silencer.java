class Silencer extends Player{
    protected int silenceCount = 1;
    Silencer(String name){
        this.name = name;
        this.setMafia(true);
        this.setSilencer(true);
        this.setAlive(true);
        this.setHaveNightJob(true);
        this.setRole("silencer");
    }

    public int getSilenceCount() {
        return silenceCount;
    }

    public void setSilenceCount(int silenced) {
        this.silenceCount += silenceCount;
    }
}
