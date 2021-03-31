class Mafia extends Player{
    Mafia(String name){
        this.name = name;
        this.setMafia(true);
        this.setHaveNightJob(true);
        this.setAlive(true);
        this.setRole("mafia");
    }
}
