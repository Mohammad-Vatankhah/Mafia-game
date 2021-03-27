class Godfather extends Player{
    Godfather(String name){
        super(name);
        setGodfather(true);
        setAlive(true);
        setMafia(true);
        setHaveNightJob(true);
        players[counter].name = name;
        players[counter].setGodfather(true);
        players[counter].setMafia(true);
        players[counter].setHaveNightJob(true);
        players[counter++].setAlive(true);
    }
}
