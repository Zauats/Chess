package com.example.chess;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChessFieldAdapter extends BaseAdapter {
    private List<Cell> cells = new ArrayList<>();
    private LayoutInflater inflater;

    public ChessFieldAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void addCell(Cell phone){
        cells.add(phone);
    }

    @Override
    public int getCount() {
        return cells.size();
    }

    public void removeItem(int i){
        Cell item = cells.get(i);
        item.setSelected(true);
        this.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return cells.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View resultView;
        if (convertView != null){
            resultView = convertView;
        }else{
            resultView = inflater.inflate(R.layout.cell_item, null);
            Cell currentCell = (Cell)getItem(position);
            resultView.setBackgroundColor(Color.rgb(181, 135, 99));
            if (currentCell.getEven()){
                resultView.setBackgroundColor(Color.rgb(241, 217, 181));
            }

            ImageView image = resultView.findViewById(R.id.image);
            image.setMaxHeight(image.getWidth());
            if (currentCell.getImage() != 0){
                image.setImageResource(currentCell.getImage());
            }


        }
        return resultView;
    }
}
