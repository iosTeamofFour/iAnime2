package com.nemesiss.dev.ianime.Model.Model.Drafting;

public class HintPoint extends Point {

    public RGB Color;

    public HintPoint(int x, int y, RGB color) {
        super(x, y);
        Color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof HintPoint && super.equals(obj)) {
            HintPoint right = (HintPoint)obj;
            return Color.equals(right.Color);
        }

        return false;
    }
}
