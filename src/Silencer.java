class Silencer extends Player{
    protected int silenced = 1;
    Silencer(String name){
        this.name = name;
        this.setMafia(true);
        this.setSilencer(true);
        this.setAlive(true);
        this.setHaveNightJob(true);
        this.setRole("silencer");
    }

    public int isSilenced() {
        return silenced;
    }

    public void setSilenced(int silenced) {
        this.silenced += silenced;
    }
}
