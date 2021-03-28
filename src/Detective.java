class Detective extends Player{
    protected int detected;
    Detective(String name){
        this.name = name;
        this.setDetective(true);
        this.setVillager(true);
        this.setAlive(true);
        this.setHaveNightJob(true);
        this.setRole("detective");
    }

    public int isDetected() {
        return detected;
    }

    public void setDetected(int detected) {
        this.detected += detected;
    }
}

