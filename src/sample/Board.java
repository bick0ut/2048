package sample;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public final static int ROW = 4;
    public final static int COL = 4;

    private Square[][] board;
    private int score;
    private boolean gameOver;
    public enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
    public Board(){
        this.gameOver = false;
        this.score = 0;
        this.board = new Square[ROW][COL];
        for(int x = 0; x < ROW; x++){
            for(int y = 0; y < COL; y++){
                this.board[x][y] = new Square(0, x, y);
            }
        }
        spawnSquare();
        spawnSquare();
    }

    public Square[][] getBoard(){
        return this.board;
    }
    //returns if a move has changed any squares
    public boolean makeMove(Direction direction){
        List<Square> squares = new ArrayList<>();
        boolean moved = false;

        //MOVE UP
        if(direction == Direction.UP){
            for(int x = 0; x < ROW; x++){
                for(int y = 0; y < COL; y++){
                    if(!board[x][y].isEmpty()){
                        squares.add(board[x][y]);
                    }
                }
            }


            for(int i = 0; i < squares.size(); i++){
                Square sq = squares.get(i);
                int x = sq.getX();
                int y = sq.getY();
                while(x != 0){
                    x--;
                    if(board[x][y].isEmpty()){
                        moveSquare(x+1, y, x, y);
                        moved = true;
                    } else {
                        if (board[x][y].getTier() == sq.getTier() && !board[x][y].isImmune()) {
                            score += moveSquare(x+1, y, x, y).tierUp();
                            moved = true;
                        }
                        break;
                    }
                }
            }

        } else if(direction == Direction.DOWN){ //move down
            for(int x = ROW - 1; x >= 0; x--){
                for(int y = 0; y < COL; y++){
                    if(!board[x][y].isEmpty()){
                        squares.add(board[x][y]);
                    }
                }
            }


            for(int i = 0; i < squares.size(); i++){
                Square sq = squares.get(i);
                int x = sq.getX();
                int y = sq.getY();
                while(x != ROW - 1){
                    x++;
                    if(board[x][y].isEmpty()){
                        moveSquare(x-1, y, x, y);
                        moved = true;
                    } else {
                        if (board[x][y].getTier() == sq.getTier() && !board[x][y].isImmune()) {
                            score += moveSquare(x-1, y, x, y).tierUp();
                            moved = true;
                        }
                        break;
                    }
                }
            }

        } else if(direction == Direction.LEFT){ //move left
            for(int x = 0; x < ROW; x++){
                for(int y = 0; y < COL; y++){
                    if(!board[x][y].isEmpty()){
                        squares.add(board[x][y]);
                    }
                }
            }


            for(int i = 0; i < squares.size(); i++){
                Square sq = squares.get(i);
                int x = sq.getX();
                int y = sq.getY();
                while(y != 0){
                    y--;
                    if(board[x][y].isEmpty()){
                        moveSquare(x, y+1, x, y);
                        moved = true;
                    } else {
                        if (board[x][y].getTier() == sq.getTier() && !board[x][y].isImmune()) {
                            score += moveSquare(x, y+1, x, y).tierUp();
                            moved = true;
                        }
                        break;
                    }
                }
            }

        } else if(direction == Direction.RIGHT){ //move right
            for(int x = 0; x < ROW; x++){
                for(int y = COL - 1; y >= 0; y--){
                    if(!board[x][y].isEmpty()){
                        squares.add(board[x][y]);
                    }
                }
            }


            for(int i = 0; i < squares.size(); i++){
                Square sq = squares.get(i);
                int x = sq.getX();
                int y = sq.getY();
                while(y != COL - 1){
                    y++;
                    if(board[x][y].isEmpty()){
                        moveSquare(x, y-1, x, y);
                        moved = true;
                    } else {
                        if (board[x][y].getTier() == sq.getTier() && !board[x][y].isImmune()) {
                            score += moveSquare(x, y-1, x, y).tierUp();
                            moved = true;
                        }
                        break;
                    }
                }
            }

        }

        resetImmunity();
        return moved;
    }

    public Square moveSquare(int currX, int currY, int targetX, int targetY){
        this.board[targetX][targetY] = new Square(this.board[currX][currY].getTier(), targetX, targetY);
        this.board[currX][currY] = new Square(0, currX, currY);
        return this.board[targetX][targetY];
    }
    public void spawnSquare(){
        //add each unoccupied square to a list, pick random index from 0 to size-1
        List<Square> squares = new ArrayList<>();
        for(int x = 0; x < ROW; x++){
            for(int y = 0; y < COL; y++){
                if(board[x][y].isEmpty()){
                    squares.add(board[x][y]);
                }
            }
        }

        Square sq = squares.get( (int) (Math.random()*squares.size()) );
        int x = sq.getX();
        int y = sq.getY();
        board[x][y] = new Square(1, x, y);
        checkGameOver();
    }

    public void checkGameOver(){
        //check if there are any empty spaces
        for(int x = 0; x < ROW; x++){
            for(int y = 0; y < COL; y++){
                if(board[x][y].isEmpty()){
                    return;
                }
            }
        }

        //if there are no empty spaces, check if any squares has neighbors with same tier
        for(int x = 0; x < ROW; x++){
            for(int y = 0; y < COL; y++){
                int currentTier = board[x][y].getTier();
                if(x - 1 >= 0 && board[x-1][y].getTier() == currentTier) return;
                if(x + 1 < ROW && board[x+1][y].getTier() == currentTier) return;
                if(y - 1 >= 0 && board[x][y-1].getTier() == currentTier) return;
                if(y + 1 < COL && board[x][y+1].getTier() == currentTier) return;
            }
        }
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String toString(){
        String str = "";
        for(int x = 0; x < ROW; x++){
            for(int y = 0; y < COL; y++){
                if(!board[x][y].isEmpty()){
                    str += "[" + Math.pow(2, board[x][y].getTier()) + "]";
                } else {
                    str += "[   ]";
                }
                str+="";
            }
            str+="\n";
        }
        return str;
    }

    public void resetImmunity(){
        for(int x = 0; x < ROW; x++){
            for(int y = 0; y < COL; y++){
                board[x][y].setImmune(false);
            }
        }
    }

    public int getScore(){
        return this.score;
    }
}
