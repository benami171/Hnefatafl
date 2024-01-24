import java.util.ArrayList;

public class ConcretePlayer implements Player {

    private int wins;
    private boolean isPlayerOne;

    private ArrayList<ConcretePiece> pieces;
    public void addWin(){
        this.wins++;
    }

    public void resetWins(){
        this.wins = 0;
    }

    public ConcretePlayer(boolean isPlayerOne) {
        this.isPlayerOne = isPlayerOne;
//        this.pieces = new ArrayList<>();
        this.wins = 0;
    }

//    public ArrayList<ConcretePiece> getPieces() {
//        return pieces;
//    }
//
//    public void addPiece(ConcretePiece piece) {
//        pieces.add(piece);
//    }


    @Override
    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    @Override
    public int getWins() {
        return this.wins;
    }
}