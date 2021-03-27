class Player {
    protected String name;
    protected String role;
    protected int numberOfVotes;
    protected boolean isAlive;
    protected boolean isMafia;
    protected boolean isJoker;
    protected boolean isVillager;
    protected boolean isDetective;
    protected boolean isDoctor;
    protected boolean isBulletproof;
    protected boolean isGodfather;
    protected boolean isSilencer;
    protected boolean savedByDoctor;
    protected boolean getShot;
    protected boolean haveNightJob;
    protected boolean silence;
    Player(String name){
        this.name = name;
    }

    Player(){}

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isMafia() {
        return isMafia;
    }

    public void setMafia(boolean mafia) {
        isMafia = mafia;
    }

    public boolean isJoker() {
        return isJoker;
    }

    public void setJoker(boolean joker) {
        isJoker = joker;
    }

    public boolean isVillager() {
        return isVillager;
    }

    public void setVillager(boolean villager) {
        isVillager = villager;
    }

    public boolean isDetective() {
        return isDetective;
    }

    public void setDetective(boolean detective) {
        isDetective = detective;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public boolean isBulletproof() {
        return isBulletproof;
    }

    public void setBulletproof(boolean bulletproof) {
        isBulletproof = bulletproof;
    }

    public boolean isGodfather() {
        return isGodfather;
    }

    public void setGodfather(boolean godfather) {
        isGodfather = godfather;
    }

    public boolean isSilencer() {
        return isSilencer;
    }

    public void setSilencer(boolean silencer) {
        isSilencer = silencer;
    }

    public boolean isSavedByDoctor() {
        return savedByDoctor;
    }

    public void setSavedByDoctor(boolean savedByDoctor) {
        this.savedByDoctor = savedByDoctor;
    }

    public boolean isGetShot() {
        return getShot;
    }

    public void setGetShot(boolean getShot) {
        this.getShot = getShot;
    }

    public boolean isHaveNightJob() {
        return haveNightJob;
    }

    public void setHaveNightJob(boolean haveNightJob) {
        this.haveNightJob = haveNightJob;
    }

    public boolean isSilence() {
        return silence;
    }

    public void setSilence(boolean silence) {
        this.silence = silence;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
