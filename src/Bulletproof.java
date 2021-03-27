class Bulletproof extends Player{
    Bulletproof(String name){
        players[counter] = new Player(name);
        players[counter].setBulletproof(true);
        players[counter].setAlive(true);
        players[counter++].setVillager(true);
    }
}
