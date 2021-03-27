class Doctor extends Player{
    Doctor(String name){
        this.name = name;
        this.setDoctor(true);
        this.setAlive(true);
        this.setHaveNightJob(true);
        this.setVillager(true);
    }
    public void save(String name){
        for (int i = 0; i < Main.players.length; i++) {
            if (name.equals(Main.players[i].name))
                Main.players[i].setSavedByDoctor(true);
        }
    }
}
