package com.example.chess.Figures;

import android.content.Context;

import com.example.chess.GameActivity;
import com.example.chess.Move;

import java.util.ArrayList;

import static com.example.chess.ChessConstants.*;

public class Figure {
    protected int X;
    protected int Y;
    protected int type;
    protected int color;
    protected int image;
    protected int cost = 0;
    protected boolean isMotion = false;

    public Figure(int X, int Y, int type, int color, int image) {
        this.X = X;
        this.Y = Y;
        this.type = type;
        this.color = color;
        this.image = image;
    }

    protected boolean checkVertical(int x, int y){
        if (Math.abs(Y - y) <= 1){
            return true;
        }
        int isUp = 1;
        if (y > Y){
            isUp = -1;
        }
        for (int i = Y - isUp; i != y; i -= isUp){
            if (BOARD[i][X].getType() != EMPTY){
                return false;
            }
        }
        return true;
    }

    protected boolean checkHorisontal(int x, int y){
        if (Math.abs(X - x) <= 1){
            return true;
        }
        int isRight = 1;
        if (x > X){
            isRight = -1;
        }
        for (int i = X - isRight; i != x; i -= isRight){
            if (BOARD[Y][i].getType() != EMPTY){
                return false;
            }
        }
        return true;
    }

    protected boolean checkDiagonal(int x, int y){
        if (Math.abs(X - x) <= 1 || Math.abs(Y - y) <= 1){
            return true;
        }
        int isRight = 1;
        int isUp = 1;
        if (x > X){
            isRight = -1;
        }
        if (y > Y){
            isUp = -1;
        }

        int i = X - isRight;
        int j = Y - isUp;
        while (i != x | j != y){
            if (BOARD[j][i].getType() != EMPTY){
                return false;
            }
            i -= isRight;
            j -= isUp;
        }

        return  true;
    }

    public boolean isMove(int x, int y){
        return true;
    }

    public ArrayList<Move> allMoves(){
        return new ArrayList<>();
    }

    public void move(int x, int y){
        X = x;
        Y = y;
    }



    public boolean castling(int x, int y){
        return false;
    }

    public boolean isMotion(){
        return isMotion;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}