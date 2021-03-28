class Bulletproof extends Player{
    protected boolean shotForFirstTime;
    Bulletproof(String name){
        this.name = name;
        this.setBulletproof(true);
        this.setAlive(true);
        this.setVillager(true);
        this.setRole("bulletproof");
    }

    public boolean isShotForFirstTime() {
        return shotForFirstTime;
    }

    public void setShotForFirstTime(boolean shotForFirstTime) {
        this.shotForFirstTime = shotForFirstTime;
    }
}
