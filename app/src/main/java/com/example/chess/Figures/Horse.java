package com.example.chess.Figures;

import com.example.chess.Move;

import java.util.ArrayList;

import static com.example.chess.ChessConstants.*;


public class Horse extends Figure {


    public Horse(int X, int Y, int type, int color, int image) {
        super(X, Y, type, color, image);
        cost = 30;
    }

    @Override
    public boolean isMove(int x, int y) {

        if((BOARD[y][x].getColor() != color) & ((Math.abs(Y - y) == 2 & Math.abs(X - x) == 1) |
                (Math.abs(Y - y) == 1 & Math.abs(X - x) == 2))){
            return true;
        }
        return false;
    }


    @Override
    public ArrayList<Move> allMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        int newCoors[][] = {{X - 2, Y - 1}, {X - 2, Y + 1}, {X + 2, Y - 1}, {X + 2, Y + 1},
                          {X - 1, Y - 2}, {X - 1, Y + 2}, {X + 1, Y - 2}, {X + 1, Y + 2}};
        for (int[] newCoor:newCoors) {
            if (newCoor[0] >= 0 & newCoor[1] >= 0 & newCoor[0] < 8 & newCoor[1] < 8){
                if (isMove(newCoor[0], newCoor[1])){
                    int cost = 0;
                    if (BOARD[newCoor[1]][newCoor[0]].getColor() != color){
                        cost = BOARD[newCoor[1]][newCoor[0]].getCost();
                    }
                    moves.add(new Move(X, Y, newCoor[0], newCoor[1], cost));
                }
            }
        }
        return moves;
    }
}
