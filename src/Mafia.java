class Mafia extends Player{
    Mafia(String name){
        super(name);
        setMafia(true);
        setAlive(true);
        players[counter].name = name;
        players[counter].setMafia(true);
        players[counter++].setAlive(true);
    }
}
