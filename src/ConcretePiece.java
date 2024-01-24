import java.util.ArrayList;

public abstract class ConcretePiece implements Piece {

    //  private Piece[][] board;
    private Player owner;
    private String type;
    private int number;
    private ArrayList<Position> moves;
    private int distance;
    private int eatAmount = 0;

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
    public void setDistance(Position a, Position b) {
        distance += a.distance(b);
    }

    public void removeDistance(int dist) {
        distance -= dist;
    }


    @Override
    public String getType() {
        return type;
    }

    public ArrayList<Position> getMoves(){
        return moves;
    }

    public void addMove(Position a){
        if (!moves.isEmpty()) {
            this.distance += moves.get(getMoves().size()-1).distance(a);
        }
        moves.add(a);
    }
//    public void printMoves(){
//        if(number == 7){
//            System.out.print("K" + number + ": ");
//        } else if(number <= 12) {
//            System.out.print("D" + number + ": ");
//        }else {
//            System.out.print("A" + number + ": ");
//        }
//
//        if(moves != null) {
//            for (Position move : moves) {
//                System.out.print(move);
//            }
//        }
//        this.moves = null;
//        System.out.println();
//
//    }


    public void removeMove(int distance){
        if (!moves.isEmpty()) {
            this.distance -= distance;
            moves.remove(moves.size() - 1);
        }

    }

    @Override
    public String toString() {
        return "ConcretePiece{" +
                "owner=" + owner +
                ", type='" + type + '\'' +
                ", number=" + number +
                '}';
    }


    public int getEatAmount() {
        return eatAmount;
    }
     public void eat(){ eatAmount++; }
    public void puke() {eatAmount--; }

    public abstract String getName();
    public abstract int getEatnums();
    public int getNumber() {
        return number;
    }


    public int getDistance() {
        return distance;
    }
}