class Joker extends Player{
    Joker(String name){
        this.name = name;
        this.setJoker(true);
        this.setAlive(true);
        this.setRole("joker");
    }
}
