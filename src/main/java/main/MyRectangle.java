package main;

public class MyRectangle {
    public Rectangle rect;
    public boolean hover;
    public boolean selected;

    public MyRectangle(Rectangle r, boolean hover) {
        this.rect = r;
        this.hover = hover;
        this.selected = false;
    }

    public MyRectangle(Rectangle r) {
        this.rect = r;
        this.hover = false;
        this.selected = false;
    }

    public void select(boolean state) {
        this.selected = state;
    }
}	
