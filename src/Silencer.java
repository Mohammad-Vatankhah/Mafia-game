class Silencer extends Player{
    Silencer(String name){
        players[counter] = new Player(name);
        players[counter].setMafia(true);
        players[counter].setSilencer(true);
        players[counter].setAlive(true);
        players[counter++].setHaveNightJob(true);
    }
    public void silence(String name){
        for (int i = 0; i < players.length; i++) {
            if (name.equals(players[i].name))
                players[i].setSilence(true);
        }
    }
}
