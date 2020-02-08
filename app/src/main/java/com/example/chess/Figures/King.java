package com.example.chess.Figures;

import com.example.chess.Move;

import java.util.ArrayList;

import static com.example.chess.ChessConstants.*;


public class King extends Figure {
    private boolean isMotion = false;

    public King(int X, int Y, int type, int color, int image) {
        super(X, Y, type, color, image);
        cost = 1000000;
    }

    @Override
    public boolean isMove(int x, int y) {

        if ((BOARD[y][x].getColor() != color) & (Math.abs(Y - y) <= 1 & Math.abs(X - x) <= 1)){
            isMotion = true;
            return true;
        }
        if (castling(x, y)){
            isMotion = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean castling(int x, int y){
        if (!isMotion){
            if (BOARD[y][x].getType() == EMPTY){
                if (x == 1){
                    if (BOARD[y][x - 1].getType() == TOUR & !BOARD[y][x - 1].isMotion()){
                        if (checkHorisontal(x - 1, y)){
                            return true;
                        }
                    }
                }
                else if(x == 6){
                    if (BOARD[y][x + 1].getType() == TOUR & !BOARD[y][x + 1].isMotion()){
                        if (checkHorisontal(x + 1, y)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<Move> allMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        for (int y = Y - 1; y <= Y + 1; y++){
            for (int x = X - 1; x <= X + 1; x++){
                if (x >= 0 & y >= 0 & x <= 7 & y <= 7) {
                    if ((BOARD[y][x].getColor() != color)) {
                        if (isMove(x, y)) {
                            cost = BOARD[y][x].getCost();
                            moves.add(new Move(X, Y, x, y, cost - 1));
                        }
                    }
                }
            }
        }
        if (castling(1, Y)){
            moves.add(new Move(X, Y, 1, Y, cost + 1));
        }
        if (castling(6, Y)){
            moves.add(new Move(X, Y, 6, Y, cost + 1));
        }
        return moves;
    }
}
