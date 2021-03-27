class Godfather extends Player{
    Godfather(String name){
        this.name = name;
        this.setGodfather(true);
        this.setMafia(true);
        this.setHaveNightJob(true);
        this.setAlive(true);
        this.setRole("godfather");
    }
}
