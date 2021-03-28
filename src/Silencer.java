class Silencer extends Player{
    protected int silenced;
    Silencer(String name){
        this.name = name;
        this.setMafia(true);
        this.setSilencer(true);
        this.setAlive(true);
        this.setHaveNightJob(true);
        this.setRole("silencer");
    }
    public void silence(String name){
        for (int i = 0; i < Main.players.length; i++) {
            if (name.equals(Main.players[i].name))
                Main.players[i].setSilence(true);
        }
    }

    public int isSilenced() {
        return silenced;
    }

    public void setSilenced(int silenced) {
        this.silenced += silenced;
    }
}
