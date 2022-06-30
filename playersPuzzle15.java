public class playersPuzzle15 implements Comparable<playersPuzzle15> {
    protected String player;
    protected Integer timePlay;

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setTimePlay(Integer timePlay) {
        this.timePlay = timePlay;
    }

    public String getPlayer() {
        return player;
    }

    public Integer getTimePlay() {
        return timePlay;
    }

    @Override
    public int compareTo(playersPuzzle15 player) {
        return this.player.compareToIgnoreCase(player.player);
    }
}
