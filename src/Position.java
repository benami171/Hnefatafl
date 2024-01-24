public class Position {
    private int x;
    private int y;

    public Position(int row, int col) {
        this.x = row;
        this.y = col;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Position a){
        if( this.x == a.x && this.y == a.y){
            return true;
        }
        return false;
    }

    public int distance (Position a){
        return Math.abs(this.x - a.x) + Math.abs(this.y - a.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", "+ y +")";
    }
}
