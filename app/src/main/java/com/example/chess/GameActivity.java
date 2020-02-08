package com.example.chess;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.chess.Figures.Figure;
import com.example.chess.Figures.Horse;
import com.example.chess.Figures.King;
import com.example.chess.Figures.Officer;
import com.example.chess.Figures.Pawn;
import com.example.chess.Figures.Queen;
import com.example.chess.Figures.Tour;

import static com.example.chess.ChessConstants.*;

import java.lang.reflect.Field;

public class GameActivity extends BaseActivity {
    MashineIntelligence logic = new MashineIntelligence(this);
    GridView chessField;
    int X = -1;
    int Y = -1;
    View lastField;
    int lastColor;
    int nowMove = WHITE;
    int myColor = WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isWin = false;
        isFinish = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        white.clear();
        black.clear();

        if (myColor == BLACK) {
            int[][] field0 = FIELD[0];
            int[][] field1 = FIELD[1];
            FIELD[0] = FIELD[7];
            FIELD[1] = FIELD[6];
            FIELD[6] = field1;
            FIELD[7] = field0;
        }

        chessField = findViewById(R.id.chessField);
        final ChessFieldAdapter adapter = makeField();
        chessField.setAdapter(adapter);

        Toast toast = Toast.makeText(getApplicationContext(),
                "Ща буит мясо", Toast.LENGTH_SHORT);
        toast.show();

        chessField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               moveFigureClick(adapterView, view, i, l);
            }
        });
    }

    public void moveFigureClick(AdapterView<?> adapterView, final View view, int i, long l) {

        if (X == -1){
            Y = i / 8;
            X = i - Y * 8;
            if ((BOARD[Y][X].getType() != EMPTY) & (BOARD[Y][X].getColor() == nowMove))
            {
                lastColor = getBackgroundColor(view);
                view.setBackgroundColor(Color.rgb(0, 255, 0));
                lastField = view;
            }
            else{
                X = -1; Y = -1;
            }
        }
        else{
            boolean isMat = true;
            int y = i / 8;
            int x = i - y * 8;

            boolean check = moveFigure(X, Y, x, y, adapterView, true);
            lastField.setBackgroundColor(lastColor);
            X = -1; Y = -1;
            if (check){
                Move theBest = logic.theBestMove(nowMove);
                if (theBest.getCost() != -2){
                    moveFigure(theBest.getX(), theBest.getY(), theBest.getx(), theBest.gety(), adapterView, false);
                    theBest = logic.theBestMove(nowMove * -1);
                    if (theBest.getCost() == -2){
                        isFinish = true;

                        finish();
                    }
                }
                else{
                    isFinish = true;
                    isWin = true;
                    finish();
                }



            }
        }
    }

    private boolean moveFigure(int X, int Y, int x, int y, AdapterView<?> adapterView, boolean isHuman){
        Move move = new Move(X, Y, x, y, 0);
        boolean isCheck = false;
        boolean castling = BOARD[Y][X].castling(x, y);
        if (BOARD[Y][X].isMove(x, y)) {
            Move tourMove = new Move(0,0,0,0,0);
            if (castling){
                if (x == 6)
                    tourMove = new Move(x + 1, y, x - 1, y, 0);
                else if (x == 1){
                    tourMove = new Move(x - 1, y, x + 1, y, 0);
                }
                tourMove.moveForward();
            }
            move.moveForward();

            isCheck = move.isCheck();
            if (isCheck){
                move.moveBack();
                if (castling) tourMove.moveBack();
                return false;
            }
            else{
                move.moveImage(adapterView);
                if (castling) tourMove.moveImage(adapterView);
                nowMove *= -1;
                if ((y == 0 | y == 7) & BOARD[y][x].getType() == PAWN){
                    if (isHuman) changeFigure(x, y, adapterView);
                    else {
                        View view = adapterView.getChildAt(y * 8 + x);;
                        ImageView image = view.findViewById(R.id.image);
                        Figure newFIgure = new Queen(x, y, QUEEN, nowMove * -1, whichFigure(x, y, nowMove * -1, QUEEN));
                        removeFigure(BOARD[y][x]);
                        addFigure(newFIgure);
                        BOARD[y][x] = newFIgure;
                        BOARD[y][x].setImage(whichFigure(x, y, nowMove * -1, BOARD[y][x].getType()));
                        image.setImageResource(BOARD[y][x].getImage());

                    }
                }
                return true;
            }
        }
        return false;
    }




    private void changeFigure(final int x, final int y, AdapterView<?> adapterView) {
        String[] figures ={ getString(R.string.tour), getString(R.string.hourse),
                getString(R.string.officer), getString(R.string.queen)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выбeрите фигуру");
        View view = adapterView.getChildAt(y * 8 + x);;
        final ImageView image = view.findViewById(R.id.image);
        builder.setItems(figures, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                Figure newFIgure = null;
                if (item == 0){
                    newFIgure = new Tour(x, y, TOUR, nowMove, whichFigure(x, y, nowMove * -1, TOUR));
                }
                else if (item == 1){
                    newFIgure = new Horse(x, y, HORSE, nowMove, whichFigure(x, y, nowMove * -1, HORSE));
                }
                else if (item == 2){
                    newFIgure = new Officer(x, y, OFFICER, nowMove, whichFigure(x, y, nowMove * -1, OFFICER));
                }
                else if (item == 3){
                    newFIgure = new Queen(x, y, QUEEN, nowMove, whichFigure(x, y, nowMove * -1, QUEEN));
                }
                removeFigure(BOARD[y][x]);
                addFigure(newFIgure);
                BOARD[y][x] = newFIgure;
                BOARD[y][x].setImage(whichFigure(x, y, nowMove, BOARD[y][x].getType()));
                image.setImageResource(BOARD[y][x].getImage());
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }


    public static int getBackgroundColor(View view) {
        Drawable drawable = view.getBackground();
        if (drawable instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            if (Build.VERSION.SDK_INT >= 11) {
                return colorDrawable.getColor();
            }
            try { Field field = colorDrawable.getClass().getDeclaredField("mState");
                field.setAccessible(true);
                Object object = field.get(colorDrawable);
                field = object.getClass().getDeclaredField("mUseColor");
                field.setAccessible(true);
                return field.getInt(object);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    ChessFieldAdapter makeField() {
        ChessFieldAdapter chessAdapter = new ChessFieldAdapter(this);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                boolean bool = false;
                if ((i % 2 == 0) & (j % 2 == 0)){
                    bool = true;
                }
                if ((i % 2 == 1) & (j % 2 == 1)){
                    bool = true;
                }
                Figure figure;
                int image = whichFigure(j, i, FIELD[i][j][1], FIELD[i][j][0]);
                if (FIELD[i][j][0] == PAWN)
                    figure = new Pawn(j, i, PAWN, FIELD[i][j][1], image);
                else if(FIELD[i][j][0] == TOUR)
                    figure = new Tour(j, i, TOUR, FIELD[i][j][1], image);
                else if(FIELD[i][j][0] == HORSE)
                    figure = new Horse(j, i, HORSE, FIELD[i][j][1], image);
                else if(FIELD[i][j][0] == OFFICER)
                    figure = new Officer(j, i, OFFICER, FIELD[i][j][1], image);
                else if(FIELD[i][j][0] == QUEEN)
                    figure = new Queen(j, i, QUEEN, FIELD[i][j][1], image);
                else if(FIELD[i][j][0] == KING)
                    figure = new King(j, i, KING, FIELD[i][j][1], image);
                else
                    figure = new Figure(j, i, EMPTY, EMPTY, EMPTY);
                BOARD[i][j] = figure;
                addFigure(figure);

                Cell cell = new Cell(image, bool);
                chessAdapter.addCell(cell);
            }
        }
        return chessAdapter;
    }

    private int whichFigure(int x, int y, int color, int type){
        int image = EMPTY;

        if (type == PAWN){
            if (color == BLACK)
                image = R.drawable.black_pawn;
            else
                image = R.drawable.white_pawn;
        }
        else if (type == TOUR){
            if (color == BLACK)
                image = R.drawable.black_tour;
            else
                image = R.drawable.white_tour;
        }
        else if (type == HORSE){
            if (color == BLACK)
                image = R.drawable.black_horse;
            else
                image = R.drawable.white_horse;
        }
        else if (type == OFFICER){
            if (color == BLACK)
                image = R.drawable.black_officer;
            else
                image = R.drawable.white_officer;
        }
        else if (type == KING){
            if (color == BLACK)
                image = R.drawable.black_king;
            else
                image = R.drawable.white_king;
        }
        else if (type == QUEEN){
            if (color == BLACK)
                image = R.drawable.black_queen;
            else
                image = R.drawable.white_queen;
        }
        return image;
    }

}
