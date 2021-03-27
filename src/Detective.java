class Detective extends Player{
    protected boolean detected;
    Detective(String name){
        this.name = name;
        this.setDetective(true);
        this.setVillager(true);
        this.setAlive(true);
        this.setHaveNightJob(true);
        this.setRole("detective");
    }

    public boolean isDetected() {
        return detected;
    }

    public void setDetected(boolean detected) {
        this.detected = detected;
    }
}

