class Bulletproof extends Player{
    Bulletproof(String name){
        super(name);
        setBulletproof(true);
        setAlive(true);
        setVillager(true);
        players[counter].name = name;
        players[counter].setBulletproof(true);
        players[counter].setAlive(true);
        players[counter++].setVillager(true);
    }
}
