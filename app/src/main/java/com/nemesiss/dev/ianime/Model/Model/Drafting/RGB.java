package com.nemesiss.dev.ianime.Model.Model.Drafting;

public class RGB {
    public int R;
    public int G;
    public int B;

    public RGB(int r, int g, int b) {
        R = r;
        G = g;
        B = b;
    }

    public int ToSystemColor() {
        int SystemColor = 0;
        SystemColor |= R << 16;
        SystemColor |= G << 8;
        SystemColor |= B;
        return SystemColor;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RGB) {
           RGB right = (RGB) obj;
           return R == right.R && G == right.G && B == right.B;
        }
        return false;
    }
}
