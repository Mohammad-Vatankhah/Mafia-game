class Godfather extends Player{
    Godfather(String name){
        players[counter] = new Player(name);
        players[counter].setGodfather(true);
        players[counter].setMafia(true);
        players[counter].setHaveNightJob(true);
        players[counter++].setAlive(true);
    }
}
