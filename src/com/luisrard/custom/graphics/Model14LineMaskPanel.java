package com.luisrard.custom.graphics;

import java.awt.*;

public class Model14LineMaskPanel extends PaintPanel {
    @Override
    public void doDraw() {
        drawLine(100,100,200,200, 0b11, 4,Color.RED);
        drawLine(100,100,200,100, 0b11110000, 8, Color.GREEN);
        drawLine(200,100, 100,200, 520, 9,Color.BLUE);
        drawLine(100,200,200,200, 990, 10,Color.RED);
    }

    public void drawLine(int x0, int y0, int x1, int y1, int mask, int maskBits, Color c) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int x = x0;
        int y = y0;

        int i = 1;
        while (x != x1 || y != y1) {
            if ((mask >> i & 1) == 1){
                putPixel(x, y, c);
            }
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += incX;
            }
            if (e2 < dx) {
                err += dx;
                y += incY;
            }
            i ++;
            if (i > maskBits){
                i = 1;
            }
        }
    }
    @Override
    public String getPanelName() {
        return "14 Tipo de líneas rectas";
    }
}
