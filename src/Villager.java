class Villager extends Player{
    Villager(String name){
        players[counter] = new Player(name);
        players[counter].setVillager(true);
        players[counter++].setAlive(true);
    }
}
