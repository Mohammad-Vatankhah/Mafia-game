class Joker extends Player{
    Joker(String name){
        players[counter] = new Player(name);
        players[counter].setJoker(true);
        players[counter++].setAlive(true);
    }
}
