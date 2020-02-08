package com.example.chess;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.chess.Figures.Figure;

import java.util.ArrayList;
import java.util.Random;

import static com.example.chess.ChessConstants.*;

public class MashineIntelligence {

    Context context;

    public MashineIntelligence(Context context) {
        this.context = context;
    }


    public ArrayList<Move> allMoves(int color) {
        ArrayList<Figure> figures = white;
        if (color == BLACK) {
            figures = black;
        }
        ArrayList<Move> moves= new ArrayList<>();
        for (Figure figure:figures) {
            for (Move move:figure.allMoves()) {
                move.moveForward();
                if (!move.isCheck()){
                    moves.add(move);
                }
                move.moveBack();
            }
        }
        return  moves;
    }

    public Move theBestMove2(int color){
        ArrayList<Move> moves = allMoves(color);
        ArrayList<Move> bestMoves = new ArrayList<>();

        Move bestMove = new Move(0, 0, 0, 0, -100000000);
        int num = 1;
        for (Move move:moves) {
            if (move.getX() == 4 & move.getY() == 0) {
                move.moveForward();

                Move move2 = theBestMove(color * -1);
                move.setCost(-move2.getCost());
                String message = move.toString() + num + " Действие";

                move.moveBack();
                if (bestMove.getCost() < move.getCost()) {
                    bestMove = move;
                }
                num++;
            }
        }

        for (Move move:moves) {
            if (move.getCost() == bestMove.getCost()){
                bestMoves.add(move);
            }
        }

        if (bestMoves.size() != 0)
            bestMove = bestMoves.get(rnd(0, bestMoves.size() - 1));
//        if (bestMove.getCost() < -1000){
//            Toast toast = Toast.makeText(context,
//                    Integer.toString(bestMove.getCost()), Toast.LENGTH_SHORT);
//            toast.show();
//        }
        Log.v("myMove", bestMove.toString() + "Лучший");
        return bestMove;
    }

    public Move theBestMove3(int color, Move move){
        int num = 1;
        move.moveForward();

        Move move2 = theBestMove(color * -1);
        move.setCost(-move2.getCost());
        String message = move.toString() + num + " Действие";
        Log.v("myMove", message);
        Log.v("myMove", move2.toString() + " Противодействие");

        move.moveBack();
        num++;

//        if (bestMove.getCost() < -1000){
//            Toast toast = Toast.makeText(context,
//                    Integer.toString(bestMove.getCost()), Toast.LENGTH_SHORT);
//            toast.show();
//        }
        Log.v("myMove", move.toString() + "Лучший");
        return move;
    }


    public Move theBestMove(int color){
        ArrayList<Move> bestMoves= new ArrayList<>();
        ArrayList<Move> moves = allMoves(color);

        Move theBest = new Move(0, 0, 0, 0, -2);
        for (Move move:moves) {
            if (move.getCost() > theBest.getCost()){
                theBest = move;
            }
        }

        for (Move move:moves) {
            if (move.getCost() == theBest.getCost()){
                bestMoves.add(move);
            }
        }

        if (bestMoves.size() != 0)
            theBest = bestMoves.get(rnd(0, bestMoves.size() - 1));


        return theBest;
    }

    public int rnd(int min, int max){
        max -= min;
        return (int)(Math.random() * ++max) + min;
    }
}
