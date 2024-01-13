public class ConcretePlayer implements Player {

    private int wins;
    private boolean isPlayerOne;

    public void addWin(){
        this.wins++;
    }

    public void resetWins(){
        this.wins = 0;
    }

    public ConcretePlayer(boolean isPlayerOne) {
        this.isPlayerOne = isPlayerOne;
        this.wins = 0;
    }

    @Override
    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    @Override
    public int getWins() {
        return this.wins;
    }
}
