package com.example.chess;

public class Cell {
    private int image;
    private boolean even;
    private boolean selected = false;

    public Cell(int image, boolean even) {
        this.image = image;
        this.even = even;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean getEven() {
        return even;
    }

    public void setEven(boolean even) {
        this.even = even;
    }
}
