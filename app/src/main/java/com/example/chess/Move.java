package com.example.chess;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.chess.Figures.Figure;

import java.util.ArrayList;

import static com.example.chess.ChessConstants.*;

public class Move {
    private int X;
    private int Y;
    private int x;
    private int y;
    private int cost;
    private Figure tempFigure;
    private int nowMove;

    public Move(int X, int Y, int x, int y, int cost) {
        this.X = X;
        this.Y = Y;
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.tempFigure = BOARD[y][x];
        this.nowMove = BOARD[Y][X].getColor();
    }

    public void moveForward(){
        BOARD[y][x] = BOARD[Y][X];
        BOARD[Y][X] = new Figure(X, Y, EMPTY, EMPTY, EMPTY);
        BOARD[y][x].move(x, y);
        if (tempFigure.getColor() == nowMove * -1)
            removeFigure(tempFigure);
    }

    public void moveBack(){
        BOARD[Y][X] = BOARD[y][x];
        BOARD[y][x] = tempFigure;
        BOARD[Y][X].setX(X);
        BOARD[Y][X].setY(Y);
        if (tempFigure.getColor() == nowMove * - 1)
            addFigure(tempFigure);
    }

    public boolean isCheck() {
        ArrayList<Figure> figures = white;
        Figure king = blackKing;
        if (nowMove == WHITE) {
            figures = black;
            king = whiteKing;
        }

        for (Figure figure : figures) {
            if (figure.isMove(king.getX(), king.getY())) {
                return true;
            }
        }
        return false;
    }

    public void moveImage(AdapterView<?> adapterView){
        ImageView image;
        View field = adapterView.getChildAt(Y * 8 + X);;
        image = field.findViewById(R.id.image);
        image.setImageDrawable(null);
        field = adapterView.getChildAt(y * 8 + x);;
        image = field.findViewById(R.id.image);
        image.setImageResource(BOARD[y][x].getImage());
    }

   @Override
   public String toString(){
        return "X: " + X + " -> " + x + "     |     " + "cost: " + cost +
                "\n                                                            Y: " + Y + " -> " + y + "     |     ";
   }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost += cost;
    }
}
