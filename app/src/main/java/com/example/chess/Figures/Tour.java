package com.example.chess.Figures;

import com.example.chess.Move;

import java.util.ArrayList;

import static com.example.chess.ChessConstants.*;


public class Tour extends Figure {

    private boolean isMotion = false;

    public Tour(int X, int Y, int type, int color, int image) {
        super(X, Y, type, color, image);
        cost = 50;
    }

    @Override
    public boolean isMove(int x, int y) {

        if ((BOARD[y][x].getColor() != color) & (X == x | Y == y)){
            if (!checkVertical(x, y)) return false;
            if (!checkHorisontal(x, y)) return false;
            isMotion = true;
            return true;
        }
        return false;
    }

    public boolean isMotion(){
        return isMotion;
    }

    @Override
    public ArrayList<Move> allMoves(){
        ArrayList<Move> moves = new ArrayList<>();

        int x = X;
        while (x > 0){
            x--;
            if (BOARD[Y][x].getColor() != color){
                cost = BOARD[Y][x].getCost();
                moves.add(new Move(X, Y, x, Y, cost));
                if (BOARD[Y][x].getColor() == color * -1){
                    break;
                }
            }
            else {
                break;
            }
        }
        x = X;
        while (x < 7){
            x++;
            if (BOARD[Y][x].getColor() != color){
                cost = BOARD[Y][x].getCost();
                moves.add(new Move(X, Y, x, Y, cost));
                if (BOARD[Y][x].getColor() == color * -1){
                    break;
                }
            }
            else {
                break;
            }
        }
        int y = Y;
        while (y < 7){
            y++;
            if (BOARD[y][X].getColor() != color){
                cost = BOARD[y][X].getCost();
                moves.add(new Move(X, Y, X, y, cost));
                if (BOARD[y][X].getColor() == color * -1){
                    break;
                }
            }
            else {
                break;
            }
        }
        y = Y;
        while (y > 0){
            y--;
            if (BOARD[y][X].getColor() != color){
                cost = BOARD[y][X].getCost();
                moves.add(new Move(X, Y, X, y, cost));
                if (BOARD[y][X].getColor() == color * -1){
                    break;
                }
            }
            else {
                break;
            }
        }

        return moves;
    }

}
