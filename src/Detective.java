class Detective extends Player{
    Detective(String name){
        this.name = name;
        this.setDetective(true);
        this.setVillager(true);
        this.setAlive(true);
        this.setHaveNightJob(true);
    }
    public void detect(String name){
        for (int i = 0; i < Main.players.length; i++) {
            if (name.equals(Main.players[i].name)){
                if (Main.players[i].isMafia() && !Main.players[i].isGodfather())
                    System.out.println("Yes");
                else
                    System.out.println("No");
            }

        }
    }
}
