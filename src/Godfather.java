class Godfather extends Mafia{
    Godfather(String name){
        super(name);
        this.setGodfather(true);
        this.setMafia(true);
        this.setHaveNightJob(true);
        this.setAlive(true);
        this.setRole("godfather");
    }
}
