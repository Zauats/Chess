package com.example.chess;

import com.example.chess.Figures.Figure;

import java.util.ArrayList;

public class ChessConstants {
    static public int PAWN = 2;
    static public int HORSE = 3;
    static public int OFFICER = 4;
    static public int TOUR = 5;
    static public int QUEEN = 6;
    static public int KING = 7;
    static public int EMPTY = 0;
    static public int BLACK = 1;
    static public int WHITE = -1;


    static public int FIELD[][][] = {
            {{TOUR, BLACK}, {HORSE, BLACK}, {OFFICER, BLACK}, {QUEEN, BLACK}, {KING, BLACK}, {OFFICER, BLACK}, {HORSE, BLACK}, {TOUR, BLACK}},
            {{PAWN, BLACK}, {PAWN, BLACK}, {PAWN, BLACK}, {PAWN, BLACK}, {PAWN, BLACK}, {PAWN, BLACK}, {PAWN, BLACK}, {PAWN, BLACK}},
            {{EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}},
            {{EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}},
            {{EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}},
            {{EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}, {EMPTY, EMPTY}},
            {{PAWN, WHITE}, {PAWN, WHITE}, {PAWN, WHITE}, {PAWN, WHITE}, {PAWN, WHITE}, {PAWN, WHITE}, {PAWN, WHITE}, {PAWN, WHITE}},
            {{TOUR, WHITE}, {HORSE, WHITE}, {OFFICER, WHITE}, {QUEEN, WHITE}, {KING, WHITE}, {OFFICER, WHITE}, {HORSE, WHITE}, {TOUR, WHITE}},
    };


    static public Figure[][] BOARD = new Figure[8][8];

    public static ArrayList<Figure> white = new ArrayList<>();
    public static ArrayList<Figure> black = new ArrayList<>();
    public static Figure whiteKing;
    public static Figure blackKing;

    public static void addFigure(Figure figure){

        if (figure.getColor() == WHITE){
            white.add(figure);
            if (figure.getType() == KING){
                whiteKing = figure;
            }
        }
        else if (figure.getColor() == BLACK){
            black.add(figure);
            if (figure.getType() == KING){
                blackKing = figure;
            }
        }
    }

    public static void removeFigure(Figure figure){
        ArrayList<Figure> figures = white;
        if (figure.getColor() == BLACK) {
            figures = black;
        }
        figures.remove(figure);
    }

    public static boolean isWin = false;
    public static boolean isFinish = false;


}
