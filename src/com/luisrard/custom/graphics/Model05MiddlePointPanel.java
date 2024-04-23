package com.luisrard.custom.graphics;

import java.awt.*;

public class Model05MiddlePointPanel extends Model04BresenhamPanel {
    @Override
    public void doDraw() {
        drawLine(100,100,200,200,Color.RED);
        drawLine(100,100,200,100,Color.GREEN);
        drawLine(200,100, 100,200,Color.BLUE);
    }


    @Override
    public void drawLine(int x0, int y0, int x1, int y1, Color c) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        int d = 2 * dy - dx;
        int incrE = 2 * dy;
        int incrNE = 2 * (dy - dx);
        int x = x0;
        int y = y0;

        putPixel(x, y, c);

        while (x < x1) {
            if (d <= 0) {
                d += incrE;
                x++;
            } else {
                d += incrNE;
                x++;
                y++;
            }
            putPixel(x, y, c);
        }
    }

    @Override
    public String getPanelName() {
        return "05 Punto Medio";
    }
}
