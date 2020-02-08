package com.example.chess.Figures;

import com.example.chess.Move;

import java.util.ArrayList;

import static com.example.chess.ChessConstants.*;


public class Queen extends Figure {

    public Queen(int X, int Y, int type, int color, int image) {
        super(X, Y, type, color, image);
        cost = 90;
    }

    @Override
    public boolean isMove(int x, int y) {
        if (X == x | Y == y){
            if (!checkHorisontal(x, y)) return false;
            if (!checkVertical(x, y)) return false;
        }
        if (Math.abs(X - x) ==  Math.abs(Y - y)){
            if (!checkDiagonal(x, y)) return false;
        }
        if ((BOARD[y][x].getColor() != color) & ((Math.abs(X - x) ==  Math.abs(Y - y)) |
                (X == x | Y == y))){
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

        x = X;
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
        y = Y;
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
