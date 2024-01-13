import java.util.Objects;

public class GameLogic implements PlayableLogic {
    // COMMIT TEST

    private ConcretePiece[][] board;
    private final int BOARD_SIZE = 11;
    private ConcretePlayer player1;
    private ConcretePlayer player2;
    private ConcretePlayer currPlayer;

    private boolean isPlayar1turn;
    private final ConcretePiece[] ConcretePieceArr;
    private Position kingPosition;

    public GameLogic() {
        isPlayar1turn = false;
        board = new ConcretePiece[BOARD_SIZE][BOARD_SIZE];
        ConcretePieceArr = new ConcretePiece[37];
        player1 = new ConcretePlayer(true);
        player2 = new ConcretePlayer(false);
        player1Pieces();
        player2Pieces();
    }

    private void player1Pieces() {
        King king1 = new King(player1, 7);
        ConcretePieceArr[6] = king1;
        for (int i = 0; i < 13; i++) {
            if (i == 6) continue;
            ConcretePieceArr[i] = new Pawn(player1, i + 1);
        }
        board[3][5] = ConcretePieceArr[0];
        ConcretePieceArr[0].addMove(new Position(5, 3));
        board[4][4] = ConcretePieceArr[1];
        ConcretePieceArr[1].addMove(new Position(4, 4));
        board[4][5] = ConcretePieceArr[2];
        ConcretePieceArr[2].addMove(new Position(5, 4));
        board[4][6] = ConcretePieceArr[3];
        ConcretePieceArr[3].addMove(new Position(6, 4));
        board[5][3] = ConcretePieceArr[4];
        ConcretePieceArr[4].addMove(new Position(3, 5));
        board[5][4] = ConcretePieceArr[5];
        ConcretePieceArr[5].addMove(new Position(4, 5));
        board[5][5] = ConcretePieceArr[6];
        ConcretePieceArr[6].addMove(new Position(5, 5));
        board[5][6] = ConcretePieceArr[7];
        ConcretePieceArr[7].addMove(new Position(6, 5));
        board[5][7] = ConcretePieceArr[8];
        ConcretePieceArr[8].addMove(new Position(7, 5));
        board[6][4] = ConcretePieceArr[9];
        ConcretePieceArr[9].addMove(new Position(4, 6));
        board[6][5] = ConcretePieceArr[10];
        ConcretePieceArr[10].addMove(new Position(5, 6));
        board[6][6] = ConcretePieceArr[11];
        ConcretePieceArr[11].addMove(new Position(6, 6));
        board[7][5] = ConcretePieceArr[12];
        ConcretePieceArr[12].addMove(new Position(5, 7));
    }

    private void player2Pieces() {
        for (int i = 13; i < 37; i++) {
            ConcretePieceArr[i] = new Pawn(player2, i + 1);
        }
        board[0][3] = ConcretePieceArr[13];
        ConcretePieceArr[13].addMove(new Position(3, 0));
        board[0][4] = ConcretePieceArr[14];
        ConcretePieceArr[14].addMove(new Position(4, 0));
        board[0][5] = ConcretePieceArr[15];
        ConcretePieceArr[15].addMove(new Position(5, 0));
        board[0][6] = ConcretePieceArr[16];
        ConcretePieceArr[16].addMove(new Position(6, 0));
        board[0][7] = ConcretePieceArr[17];
        ConcretePieceArr[17].addMove(new Position(7, 0));
        board[1][5] = ConcretePieceArr[18];
        ConcretePieceArr[18].addMove(new Position(5, 1));
        board[3][0] = ConcretePieceArr[19];
        ConcretePieceArr[19].addMove(new Position(0, 3));
        board[3][10] = ConcretePieceArr[20];
        ConcretePieceArr[20].addMove(new Position(10, 3));
        board[4][0] = ConcretePieceArr[21];
        ConcretePieceArr[21].addMove(new Position(0, 4));
        board[4][10] = ConcretePieceArr[22];
        ConcretePieceArr[22].addMove(new Position(10, 4));
        board[5][0] = ConcretePieceArr[23];
        ConcretePieceArr[23].addMove(new Position(0, 5));
        board[5][1] = ConcretePieceArr[24];
        ConcretePieceArr[24].addMove(new Position(1, 5));
        board[5][9] = ConcretePieceArr[25];
        ConcretePieceArr[25].addMove(new Position(9, 5));
        board[5][10] = ConcretePieceArr[26];
        ConcretePieceArr[26].addMove(new Position(10, 5));
        board[6][0] = ConcretePieceArr[27];
        ConcretePieceArr[27].addMove(new Position(0, 6));
        board[6][10] = ConcretePieceArr[28];
        ConcretePieceArr[28].addMove(new Position(10, 6));
        board[7][0] = ConcretePieceArr[29];
        ConcretePieceArr[29].addMove(new Position(0, 7));
        board[7][10] = ConcretePieceArr[30];
        ConcretePieceArr[30].addMove(new Position(10, 7));
        board[9][5] = ConcretePieceArr[31];
        ConcretePieceArr[31].addMove(new Position(5, 9));
        board[10][3] = ConcretePieceArr[32];
        ConcretePieceArr[32].addMove(new Position(3, 10));
        board[10][4] = ConcretePieceArr[33];
        ConcretePieceArr[33].addMove(new Position(4, 10));
        board[10][5] = ConcretePieceArr[34];
        ConcretePieceArr[34].addMove(new Position(5, 10));
        board[10][6] = ConcretePieceArr[35];
        ConcretePieceArr[35].addMove(new Position(6, 10));
        board[10][7] = ConcretePieceArr[36];
        ConcretePieceArr[36].addMove(new Position(7, 10));
    }

    @Override
    public boolean move(Position a, Position b) {
        Position corner1 = new Position(0,0);
        Position corner2 = new Position(0,10);
        Position corner3 = new Position(10,0);
        Position corner4 = new Position(10,10);

        if (b.getY() != a.getY() && b.getX() != a.getX()) return false;
        Piece aPiece = getPieceAtPosition(a);
        Piece bPiece = getPieceAtPosition(b);
        if (aPiece == null || bPiece != null) return false;
        if (isPlayar1turn) {
            currPlayer = this.player1;
        } else {
            currPlayer = this.player2;
        }
        if (currPlayer != aPiece.getOwner()) return false;
        if (a.getY() != b.getY()) {
            for (int i = a.getY(); i < b.getY(); i++) {
                if (board[i + 1][a.getX()] != null) return false;
            }
            for (int i = a.getY(); i > b.getY(); i--) {
                if (board[i - 1][a.getX()] != null) return false;
            }

        }
        if (a.getX() != b.getX()) {
            for (int i = a.getX(); i < b.getX(); i++) {
                if (board[a.getY()][i + 1] != null) return false;
            }
            for (int i = a.getX(); i > b.getX(); i--) {
                if (board[a.getY()][i - 1] != null) return false;
            }
        }
        if(!Objects.equals(aPiece.getType(), "♔")) if (b.equals(corner1) || b.equals(corner2) || b.equals(corner3) || b.equals(corner4)) return false;
        board[b.getY()][b.getX()] = board[a.getY()][a.getX()];
        board[a.getY()][a.getX()] = null;
        isPlayar1turn = !isPlayar1turn;
        ((ConcretePiece)aPiece).addMove(b);
        return true;
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        return board[position.getY()][position.getX()];
    }

    @Override
    public Player getFirstPlayer() {
        return player1;
    }

    @Override
    public Player getSecondPlayer() {
        return player2;
    }

    @Override
    public boolean isGameFinished() {
        return isDefendersWon()||isAttackersWon();
    }

    public boolean isDefendersWon(){
        ConcretePiece corner1 = board[0][0];
        ConcretePiece corner2 = board[0][10];
        ConcretePiece corner3 = board[10][0];
        ConcretePiece corner4 = board[10][10];
        if (corner1 != null && Objects.equals(corner1.getType(), "♔")){
            player1.addWin();
            return true;
        }
        if (corner2 != null && Objects.equals(corner2.getType(), "♔")) {
            player1.addWin();
            return true;
        }
        if (corner3 != null && Objects.equals(corner3.getType(), "♔")) {
            player1.addWin();
            return true;
        }
        if (corner4 != null && Objects.equals(corner4.getType(), "♔")) {
            player1.addWin();
            return true;
        }
        return false;
    }

    public boolean isAttackersWon(){
        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return !isPlayar1turn;
    }

    @Override
    public void reset() {
        board = new ConcretePiece[BOARD_SIZE][BOARD_SIZE];
        player1Pieces();
        player2Pieces();
        isPlayar1turn = false;
        player1.resetWins();
        player2.resetWins();
    }

    @Override
    public void undoLastMove() {
    }

    @Override
    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }
}
