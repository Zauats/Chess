package com.example.chess.Figures;

import com.example.chess.Move;

import java.util.ArrayList;

import static com.example.chess.ChessConstants.*;

public class Pawn extends Figure {

    private int upIsBlack = 1;
    public Pawn(int X, int Y, int type, int color, int image) {
        super(X, Y, type, color, image);
        if (Y == 6 & color == WHITE || Y == 1 & color == BLACK){
            upIsBlack = -1;
        }
        cost = 10;
    }

    @Override
    public boolean isMove(int x, int y) {

        int enemyColor = BLACK;
        if (color == BLACK) enemyColor = WHITE;
        int maxCells = 2;
        if (Y < 6 & Y > 1) maxCells = 1;

        if ((BOARD[y][x].getType() == EMPTY) & ((Y - y) * BOARD[Y][X].getColor() * upIsBlack <= maxCells & (Y - y) * BOARD[Y][X].getColor() * upIsBlack >= 0) & (x == X)){
            if (!checkVertical(x, y)) return false;
            return true;
        }
        else if((BOARD[y][x].getColor() == enemyColor) & (Y - y == BOARD[Y][X].getColor() * upIsBlack) & (Math.abs(X - x) == 1)){
            if(!super.isMove(x, y)) return false;
            return true;
        }



        return false;
    }

    @Override
    public ArrayList<Move> allMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        for (int y = Y - 2; y <= Y + 2; y++){
            if (y >= 0 & y <= 7){
                if (isMove(X, y)){
                    if (y == 7 | y == 0)
                        moves.add(new Move(X, Y, X, y, 8));
                    else
                        moves.add(new Move(X, Y, X, y, 0));
                }
            }
        }

        int coors[][] = {{X - 1, Y - 1}, {X + 1, Y - 1}, {X - 1, Y + 1}, {X + 1, Y + 1}};
        for (int coor[]:coors){
            if (coor[0] >= 0 & coor[1] >= 0 & coor[0] < 8 & coor[1] < 8){
                if (isMove(coor[0], coor[1])){
                    int cost = BOARD[coor[1]][coor[0]].getCost();
                    if (coor[1] == 0 | coor[1] == 7)
                        cost += 8;
                    moves.add(new Move(X, Y, coor[0], coor[1], cost));
                }
            }
        }
        return moves;
    }
}
