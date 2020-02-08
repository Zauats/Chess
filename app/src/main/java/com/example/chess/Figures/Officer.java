package com.example.chess.Figures;

import com.example.chess.Move;

import java.util.ArrayList;

import static com.example.chess.ChessConstants.*;



public class Officer extends Figure {

    public Officer(int X, int Y, int type, int color, int image) {
        super(X, Y, type, color, image);
        cost = 30;
    }

    @Override
    public boolean isMove(int x, int y) {

        if ((BOARD[y][x].getColor() != color) & (Math.abs(X - x) ==  Math.abs(Y - y))){
            if (!checkDiagonal(x, y)) return false;
            if(!super.isMove(x, y)) return false;
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Move> allMoves(){
        ArrayList<Move> moves = new ArrayList<>();

        int x = X;
        int y = Y;
        while (x > 0 & y > 0){
            x--;
            y--;
            if (BOARD[y][x].getColor() != color){
                cost = BOARD[y][x].getCost();
                moves.add(new Move(X, Y, x, y, cost));
                if (BOARD[y][x].getColor() == color * -1){
                    break;
                }
            }
            else {
                break;
            }
        }

        x = X;
        y = Y;
        while (x > 0 & y < 7){
            x--;
            y++;
            if (BOARD[y][x].getColor() != color){
                cost = BOARD[y][x].getCost();
                moves.add(new Move(X, Y, x, y, cost));
                if (BOARD[y][x].getColor() == color * -1){
                    break;
                }
            }
            else {
                break;
            }
        }

        x = X;
        y = Y;
        while (x < 7 & y < 7){
            x++;
            y++;
            if (BOARD[y][x].getColor() != color){
                cost = BOARD[y][x].getCost();
                moves.add(new Move(X, Y, x, y, cost));
                if (BOARD[y][x].getColor() == color * -1){
                    break;
                }
            }
            else {
                break;
            }
        }

        x = X;
        y = Y;
        while (x < 7 & y > 0){
            x++;
            y--;
            if (BOARD[y][x].getColor() != color){
                cost = BOARD[y][x].getCost();
                moves.add(new Move(X, Y, x, y, cost));
                if (BOARD[y][x].getColor() == color * -1){
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
