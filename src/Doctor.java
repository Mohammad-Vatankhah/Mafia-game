class Doctor extends Player{
    Doctor(String name){
        players[counter] = new Player(name);
        players[counter].setDoctor(true);
        players[counter].setAlive(true);
        players[counter].setHaveNightJob(true);
        players[counter++].setVillager(true);
    }
    public void save(String name){
        for (int i = 0; i < players.length; i++) {
            if (name.equals(players[i].name))
                players[i].setSavedByDoctor(true);
        }
    }
}
