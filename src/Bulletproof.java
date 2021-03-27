class Bulletproof extends Player{
    Bulletproof(String name){
        this.name = name;
        this.setBulletproof(true);
        this.setAlive(true);
        this.setVillager(true);
        this.setRole("bulletproof");
    }
}
