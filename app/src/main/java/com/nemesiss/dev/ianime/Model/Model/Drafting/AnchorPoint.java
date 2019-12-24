package com.nemesiss.dev.ianime.Model.Model.Drafting;

public class AnchorPoint extends Point {

    public RGB Color;

    public AnchorPoint(int x, int y, RGB color) {
        super(x, y);
        Color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AnchorPoint && super.equals(obj)) {
            AnchorPoint right = (AnchorPoint) obj;
            return right.Color.equals(Color);
        }
        return false;
    }
}
