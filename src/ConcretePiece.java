import java.util.ArrayList;

public abstract class ConcretePiece implements Piece {

    //  private Piece[][] board;
    private Player owner;
    private String type;
    private int number;
    private ArrayList<Position> moves;
    private int distance;

    public ConcretePiece(Player player, int index){
        this.owner = player;
        this.number = index;
        this.distance = 0;
        moves = new ArrayList<>();
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getType() {
        return type;
    }
    public ArrayList getMoves(){
        return moves;
    }

    public void addMove(Position a){
        if (moves.size()>0) {
            this.distance+= moves.get(getMoves().size()-1).distance(a);
        }
        moves.add(a);
    }

    @Override
    public String toString() {
        return "ConcretePiece{" +
                "owner=" + owner +
                ", type='" + type + '\'' +
                ", number=" + number +
                '}';
    }

    public abstract String getName();
    public abstract int getEatnums();
    public int getNumber() {
        return this.number;
    }



}
