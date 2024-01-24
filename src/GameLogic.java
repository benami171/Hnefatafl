import java.lang.reflect.Array;
import java.util.*;

public class GameLogic implements PlayableLogic {
    // COMMIT TEST

    private ConcretePiece[][] board;
    private final int BOARD_SIZE = 11;
    private ConcretePlayer player1;
    private ConcretePlayer player2;
    private ConcretePlayer currPlayer;
    private boolean isPlayar1turn;
    private ConcretePiece[] ConcretePieceArr;
    private Position kingPosition;
    private Stack<Back> backs = new Stack<Back>();
    private ArrayList<Step> steps = new ArrayList<Step>();

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
        for (int i = 0; i < 13; i++) {
            if (i == 6) {
                ConcretePieceArr[i] = new King(player1, 7);
                i++;
                //  continue;
            }
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
        board[5][5] = ConcretePieceArr[6];                               //king
        ConcretePieceArr[6].addMove(new Position(5, 5));        //king
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
            ConcretePieceArr[i] = new Pawn(player2, i - 12);
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

        //backs.add(new Back(0,0,0,0, 0, 0, null, null));


    }

    @Override
    public boolean move(Position a, Position b) {
        Position corner1 = new Position(0, 0);
        Position corner2 = new Position(0, 10);
        Position corner3 = new Position(10, 0);
        Position corner4 = new Position(10, 10);


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
        if (!Objects.equals(aPiece.getType(), "♔"))
            if (b.equals(corner1) || b.equals(corner2) || b.equals(corner3) || b.equals(corner4))
                return false;


        //adding step to this position
        Step tmpStep = new Step(b);

        if (!steps.contains(tmpStep)) {
            steps.add(tmpStep);
        } else {
            for (int i = 0; i < steps.size(); i++) {
                if (tmpStep.equals(steps.get(i)))
                    steps.get(i).addStep();
            }


        }
        //moving
        board[b.getY()][b.getX()] = board[a.getY()][a.getX()];
        board[a.getY()][a.getX()] = null;

        //adding distance and the move
//        ConcretePieceArr[board[b.getY()][b.getX()].getNumber()].addMove(new Position(b.getX(),b.getY()));
//        System.out.println("piece ConcretePieceArr[0] = " + (ConcretePieceArr[board[b.getY()][b.getX()].getNumber()].getNumber() - 1 ));
//        System.out.println("board number for that piece = " + board[b.getY()][b.getX()].getNumber());


        //eating
        int y = b.getY();
        int x = b.getX();

        ArrayList<Pawn> tmpEaten = new ArrayList<Pawn>();
        ArrayList<Position> tmpPos = new ArrayList<Position>();

        int tmpC = 0;
        int tmpIndex = -1;
        Pawn CurrMoved = null;
        Back back = new Back(a.getX(), a.getY(), b.getX(), b.getY(), a.distance(b), CurrMoved);

        if (!Objects.equals(aPiece.getType(), "♔")) {
            if (isPlayar1turn) {

                if (y != 0 && board[y - 1][x] != null && board[y - 1][x].getOwner() == player2) { //checks if hes not on the sides and that he has an enemy next to him
                    if ((y - 2 < 0) || (board[y - 2][x] != null && board[y - 2][x].getOwner() == player1 && !Objects.equals(board[y - 2][x].getType(), "♔"))) {   //checks if he׳s pinning him to a wall or if he has an ally pinning him
                        tmpIndex = board[y - 1][x].getNumber(); //eaten index
                        board[y - 1][x] = null; //remove eaten
                        ConcretePieceArr[board[y][x].getNumber()].eat(); //add eat to killer
                        if (y - 2 > 0) {
                            ConcretePieceArr[board[y - 2][x].getNumber()].eat(); //ally eat++
                            back.addAlly(new Pawn(player1, board[y - 2][x].getNumber())); //add ally to list for back button
                        }
                        tmpEaten.add(new Pawn(player2, tmpIndex)); //add eaten to list
                        tmpC++; //add 1 to eat amount
                        tmpPos.add(new Position(x, y - 1)); // adding dead position


                    }
                }
                if (y != 10 && board[y + 1][x] != null && board[y + 1][x].getOwner() == player2) { //down
                    if ((y + 2 > 10) || (board[y + 2][x] != null && board[y + 2][x].getOwner() == player1 && !Objects.equals(board[y + 2][x].getType(), "♔"))) {
                        tmpIndex = board[y + 1][x].getNumber();
                        board[y + 1][x] = null;
                        ConcretePieceArr[board[y][x].getNumber()].eat();
                        if (y + 2 < 10) {
                            back.addAlly(new Pawn(player1, board[y + 2][x].getNumber()));
                            ConcretePieceArr[board[y + 2][x].getNumber()].eat();
                        }
                        tmpEaten.add(new Pawn(player2, tmpIndex));
                        tmpC++;
                        tmpPos.add(new Position(x, y + 1));

                    }
                }
                if (x != 0 && board[y][x - 1] != null && board[y][x - 1].getOwner() == player2) { //left
                    if ((x - 2 < 0) || (board[y][x - 2] != null && board[y][x - 2].getOwner() == player1 && !Objects.equals(board[y][x - 2].getType(), "♔"))) {
                        tmpIndex = board[y][x - 1].getNumber();
                        board[y][x - 1] = null;
                        ConcretePieceArr[board[y][x].getNumber()].eat();
                        if (x - 2 > 0) {
                            back.addAlly(new Pawn(player1, board[y][x - 2].getNumber()));
                            ConcretePieceArr[board[y][x - 2].getNumber()].eat();
                        }
                        tmpEaten.add(new Pawn(player2, tmpIndex));
                        tmpC++;
                        tmpPos.add(new Position(x - 1, y));


                    }
                }
                if (x != 10 && board[y][x + 1] != null && board[y][x + 1].getOwner() == player2) { //right
                    if ((x + 2 > 10) || (board[y][x + 2] != null && board[y][x + 2].getOwner() == player1 && !Objects.equals(board[y][x + 2].getType(), "♔"))) {
                        tmpIndex = board[y][x + 1].getNumber();
                        board[y][x + 1] = null;
                        ConcretePieceArr[board[y][x].getNumber()].eat();
                        if (x + 2 < 10) {
                            back.addAlly(new Pawn(player1, board[y][x + 2].getNumber()));
                            ConcretePieceArr[board[y][x + 2].getNumber()].eat();
                        }

                        tmpEaten.add(new Pawn(player2, tmpIndex));
                        tmpC++;
                        tmpPos.add(new Position(x + 1, y));

                    }
                }
                CurrMoved = new Pawn(player1, board[y][x].getNumber());

            } else {

                if (y != 0 && board[y - 1][x] != null && board[y - 1][x].getOwner() == player1 && !Objects.equals(board[y - 1][x].getType(), "♔")) { //up
                    if ((y - 2 < 0) || (board[y - 2][x] != null && board[y - 2][x].getOwner() == player2)) {
                        tmpIndex = board[y - 1][x].getNumber();
                        board[y - 1][x] = null;
                        ConcretePieceArr[board[y][x].getNumber()].eat();
                        if (y - 2 > 0) {
                            back.addAlly(new Pawn(player1, board[y - 2][x].getNumber()));
                            ConcretePieceArr[board[y - 2][x].getNumber()].eat();
                        }

                        tmpEaten.add(new Pawn(player1, tmpIndex));
                        tmpC++;
                        tmpPos.add(new Position(x, y - 1));

                    }
                }
                if (y != 10 && board[y + 1][x] != null && board[y + 1][x].getOwner() == player1 && !Objects.equals(board[y + 1][x].getType(), "♔")) { //down
                    if ((y + 2 > 10) || (board[y + 2][x] != null && board[y + 2][x].getOwner() == player2)) {
                        tmpIndex = board[y + 1][x].getNumber();
                        board[y + 1][x] = null;
                        ConcretePieceArr[board[y][x].getNumber()].eat();
                        if (y + 2 < 10) {
                            back.addAlly(new Pawn(player1, board[y + 2][x].getNumber()));
                            ConcretePieceArr[board[y + 2][x].getNumber()].eat();
                        }
                        tmpEaten.add(new Pawn(player1, tmpIndex));
                        tmpC++;
                        tmpPos.add(new Position(x, y + 1));

                    }
                }
                if (x != 0 && board[y][x - 1] != null && board[y][x - 1].getOwner() == player1 && !Objects.equals(board[y][x - 1].getType(), "♔")) { //left
                    if ((x - 2 < 0) || (board[y][x - 2] != null && board[y][x - 2].getOwner() == player2)) {
                        tmpIndex = board[y][x - 1].getNumber();
                        board[y][x - 1] = null;
                        ConcretePieceArr[board[y][x].getNumber()].eat();
                        if (x - 2 > 0) {
                            back.addAlly(new Pawn(player1, board[y][x - 2].getNumber()));
                            ConcretePieceArr[board[y][x - 2].getNumber()].eat();
                        }
                        tmpEaten.add(new Pawn(player1, tmpIndex));
                        tmpC++;
                        tmpPos.add(new Position(x - 1, y));

                    }
                }
                if (x != 10 && board[y][x + 1] != null && board[y][x + 1].getOwner() == player1 && !Objects.equals(board[y][x + 1].getType(), "♔")) { //right
                    if ((x + 2 > 10) || (board[y][x + 2] != null && board[y][x + 2].getOwner() == player2)) {
                        tmpIndex = board[y][x + 1].getNumber();
                        board[y][x + 1] = null;
                        ConcretePieceArr[board[y][x].getNumber()].eat();
                        if (x + 2 < 10) {
                            back.addAlly(new Pawn(player1, board[y][x + 2].getNumber()));
                            ConcretePieceArr[board[y][x + 2].getNumber()].eat();
                        }
                        tmpEaten.add(new Pawn(player1, tmpIndex));
                        tmpC++;
                        tmpPos.add(new Position(x + 1, y));

                    }
                }

                CurrMoved = new Pawn(player2, board[y][x].getNumber());

            }
        }

        back.setEaten(tmpEaten);
        back.setEatNum(tmpC);
        back.setEatenPos(tmpPos);
        backs.add(back);

        isPlayar1turn = !isPlayar1turn;
        ((ConcretePiece) aPiece).addMove(b);

        isGameFinished();
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

        Iterator<ConcretePiece> ite = new Iterator<ConcretePiece>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public ConcretePiece next() {
                return null;
            }
        };

        //return stats for current round and resets them
//        if (isDefendersWon()) {
//            printFinishedGame();
////            int min = Integer.MAX_VALUE;
////            int minIndex = -1;
////
////            for (int j = 0; j < 12; j++) {
////                min = Integer.MAX_VALUE;
////                minIndex = -1;
////
////                for(int i = 0 ; i < 12 ; i++){
////                    if( ConcretePieceArr[i].getMoves() !=null && ConcretePieceArr[i].getMoves().size() > 1 && ConcretePieceArr[i].getMoves().size() < min ) {
////                        min = ConcretePieceArr[i].getMoves().size();
////                        minIndex = i;
////                    }
////                }
////                if(minIndex == -1)
////                    break;
////
////                ConcretePieceArr[minIndex].printMoves(); //print moves and sets to null
////            }
////
////            for (int j = 12; j < 37; j++) {
////                min = Integer.MAX_VALUE;
////                minIndex = -1;
////
////                for(int i = 12 ; i < 37 ; i++){
////                    if( ConcretePieceArr[i].getMoves() !=null && ConcretePieceArr[i].getMoves().size() > 1 && ConcretePieceArr[i].getMoves().size() < min ) {
////                        min = ConcretePieceArr[i].getMoves().size();
////                        minIndex = i;
////                    }
////                }
////                if(minIndex == -1)
////                    break;
////
////                ConcretePieceArr[minIndex].printMoves(); //print moves and sets to null
////            }
//
//
//        } else if (isAttackersWon()) {
//
//        }
        if (isDefendersWon() || isAttackersWon()) {
            printFinishedGame();
            return true;
        }
        return false;
    }

    public Position getKingPosition() {
        ArrayList<Position> kingMoves = ConcretePieceArr[6].getMoves();
        return kingMoves.get(kingMoves.size() - 1);
    }

    public boolean isDefendersWon() {
        ConcretePiece corner1 = board[0][0];
        ConcretePiece corner2 = board[0][10];
        ConcretePiece corner3 = board[10][0];
        ConcretePiece corner4 = board[10][10];
        if (corner1 != null && Objects.equals(corner1.getType(), "♔")) {
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

    public boolean isAttackersWon() {

        int temp = 4;
        int count = 0;
        int x = getKingPosition().getX();
        int y = getKingPosition().getY();
        if (x == 0 || y == 0 || x == 10 || y == 10)
            temp = 3;

        if (y != 0 && board[y - 1][x] != null) { //up
            if (board[y - 1][x].getOwner() == player2)
                count++;
        }
        if (y != 10 && board[y + 1][x] != null) { //down
            if (board[y + 1][x].getOwner() == player2)
                count++;
        }
        if (x != 0 && board[y][x - 1] != null) { //left
            if (board[y][x - 1].getOwner() == player2)
                count++;
        }
        if (x != 10 && board[y][x + 1] != null) { //right
            if (board[y][x + 1].getOwner() == player2)
                count++;
        }

        if (count == temp) {
            player2.addWin();
            return true;
        }

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

        if (!backs.isEmpty()) {
            Back tmp = backs.pop();
            isPlayar1turn = !isPlayar1turn;
            int eatNum = tmp.getEatNum();

            ArrayList<Pawn> eatenPawns = tmp.getEaten();
            ArrayList<Pawn> allies = tmp.getAllies();
            ArrayList<Position> eatenPos = tmp.getEatenPositions();


            while (eatNum != 0) { //removing eating amount
                ConcretePieceArr[board[tmp.getCurrY()][tmp.getCurrX()].getNumber() - 1].puke();
                eatNum--;
            }
            board[tmp.getOrgY()][tmp.getOrgX()] = board[tmp.getCurrY()][tmp.getCurrX()]; //move back the piece we moved
            board[tmp.getCurrY()][tmp.getCurrX()] = null; //removing from last positions

            //restore eaten pieces

            while (!eatenPawns.isEmpty()) {

                board[eatenPos.getLast().getY()][eatenPos.getLast().getX()] = ConcretePieceArr[eatenPawns.getLast().getNumber()];
                ConcretePieceArr[eatenPawns.getLast().getNumber()].addMove(new Position(eatenPos.getLast().getX(), eatenPos.getLast().getY()));
                if (!allies.isEmpty())
                    ConcretePieceArr[allies.getLast().getNumber()].puke(); //removing

                eatenPos.removeLast();
                eatenPawns.removeLast();

            }

            while (!allies.isEmpty()) {
                allies.getLast().puke();
                allies.removeLast();
            }

            steps.getLast().removeStep();
            steps.removeLast();

            //restore stats
            //distance removal + move removal

            //ConcretePieceArr[board[tmp.getCurrY()][tmp.getCurrX()].getNumber()].removeMove(tmp.getDist());


        }
    }

    @Override
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    //    ==================================== COMPARATORS ======================================
//    Compare ConcretePieceArr by the winner and the moves
    public void printFinishedGame() {
        ArrayList<ConcretePiece> win = new ArrayList<ConcretePiece>();
        ArrayList<ConcretePiece> lose = new ArrayList<ConcretePiece>();
//        Iterate through ConcretePieceArr and add the first 12 pieces to 'win' and the rest to 'lose'
        for (int i = 0; i < ConcretePieceArr.length; i++) {
            if (i < 13) {
                win.add(ConcretePieceArr[i]);
            } else {
                lose.add(ConcretePieceArr[i]);
            }
        }
        printByMoves(win, lose);
        System.out.println("***************************************************************************");
        printByKills(win, lose);
        System.out.println("***************************************************************************");
        printByDistance(win, lose);
        System.out.println("***************************************************************************");
        printPositionByMoves();
        System.out.println("***************************************************************************");



    }

    private void printByMoves(ArrayList<ConcretePiece> win, ArrayList<ConcretePiece> lose) {
        win.sort((o1, o2) -> {
//            The sort will be by the number of sizes + if D1 and D3 do the same moves, than D1 will be first
            if (o1.getMoves().size() == o2.getMoves().size()) {
                if (o1.getNumber() < o2.getNumber()) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return o1.getMoves().size() - o2.getMoves().size();
        });
        lose.sort((o1, o2) -> {
            if (o1.getMoves().size() == o2.getMoves().size()) {
                if (o1.getNumber() < o2.getNumber()) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return o1.getMoves().size() - o2.getMoves().size();
        });
        for (ConcretePiece piece : win) {
            if (piece.getMoves().size() <= 1)
                continue;
            System.out.println(piece.getName() + ": " + piece.getMoves());
        }
        for (ConcretePiece piece : lose) {
            if (piece.getMoves().size() <= 1)
                continue;
            System.out.println(piece.getName() + ": " + piece.getMoves());
        }
    }

    private void printByKills(ArrayList<ConcretePiece> win, ArrayList<ConcretePiece> lose) {
        win.sort((o1, o2) -> {
//            The sort will be by the number of sizes + if D1 and D3 do the same moves, than D1 will be first
            if (o1.getEatnums() == o2.getEatnums()) {
                if (o1.getNumber() < o2.getNumber()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return o1.getEatnums() - o2.getEatnums();
        });
        lose.sort((o1, o2) -> {
            if (o1.getEatnums() == o2.getEatnums()) {
                if (o1.getNumber() < o2.getNumber()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return o1.getEatnums() - o2.getEatnums();
        });
        for (ConcretePiece piece : win) {
            if (piece.getEatnums() <= 0)
                continue;
            System.out.println(piece.getName() + ": " + piece.getEatnums() + " kills");
        }
        for (ConcretePiece piece : lose) {
            if (piece.getEatnums() <= 0)
                continue;
            System.out.println(piece.getName() + ": " + piece.getEatnums() + " kills");
        }
    }

    public void printByDistance(ArrayList<ConcretePiece> win, ArrayList<ConcretePiece> lose) {
        win.sort((o1, o2) -> {
//            The sort will be by the number of sizes + if D1 and D3 do the same moves, than D1 will be first
            if (o1.getDistance() == o2.getDistance()) {
                if (o1.getNumber() < o2.getNumber()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return o1.getDistance() - o2.getDistance();
        });
        lose.sort((o1, o2) -> {
            if (o1.getDistance() == o2.getDistance()) {
                if (o1.getNumber() < o2.getNumber()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return o1.getDistance() - o2.getDistance();
        });
        for (ConcretePiece piece : win) {
            if (piece.getDistance() <= 0)
                continue;
            System.out.println(piece.getName() + ": " + piece.getDistance() + " squares");
        }
        for (ConcretePiece piece : lose) {
            if (piece.getDistance() <= 0)
                continue;
            System.out.println(piece.getName() + ": " + piece.getDistance() + " squares");
        }
    }
    public void printPositionByMoves(){
//        I need to print the position and then all the distinct pieces that was on this position for example:
        // (1,1): 2 pieces
        // (1,2): 1 piece
        // (1,3): 6 piece and so on, but only position that have at least 2 pieces distinct pieces on this position
        // but make sure that if a piece "D2" was on the positions (1,1) then (1,2), and then (1,1) again, it will count as 1 pieces


        HashMap<Position, Integer> map = new HashMap<Position, Integer>();
        for (ConcretePiece piece : ConcretePieceArr) {
            if (piece.getMoves().size() <= 1)
                continue;
//            Remove all the duplicate positions from the list
            HashSet<Position> set = new HashSet<>(piece.getMoves());
            for (Position position : set) {
//                Iterate through all keys in the hashmap and then compare the position.getX() and position.getY() to the key.getX() and key.getY()
//                if they are equal then add 1 to the value of the key
                for (Map.Entry<Position, Integer> entry : map.entrySet()) {
                    if (entry.getKey().getX() == position.getX() && entry.getKey().getY() == position.getY()) {
                        entry.setValue(entry.getValue() + 1);
                    }
                }
//                Else add the position to the hashmap with value 1
                if (!map.containsKey(position)) {
                    map.put(position, 1);
                }
            }
        }
        for (Map.Entry<Position, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " pieces");
            }
        }

    }

}
