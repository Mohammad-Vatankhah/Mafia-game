class Detective extends Player{
    Detective(String name){
        players[counter] = new Player(name);
        players[counter].setDetective(true);
        players[counter].setVillager(true);
        players[counter].setAlive(true);
        players[counter++].setHaveNightJob(true);
    }
    public void detect(String name){
        for (int i = 0; i < players.length; i++) {
            if (name.equals(players[i].name)){
                if (players[i].isMafia() && !players[i].isGodfather())
                    System.out.println("Yes");
                else
                    System.out.println("No");
            }

        }
    }
}
