package com.luisrard.custom.graphics;

import java.awt.*;

public class Model16CircleThicknessPanel extends PaintPanel {
    @Override
    public void doDraw() {
        for (int i = 0, r; i <= 5; i ++){
            r = 50 + 30 * i;
            drawCircle(300,300, r,7, Color.GREEN);
            drawCircle(300,300, r + 10,2,  Color.RED);
            drawCircle(300,300, r + 20, 5, Color.YELLOW);

        }
    }

    public void drawCircle(int centerX, int centerY, int radius, int thickness, Color c){
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;

        while (x <= y) {
            putPixels(x, y, centerX, centerY, thickness, c);

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
        for(int i = 0; i < thickness - 1; i ++){
            putPixels(x, y, centerX, centerY, thickness, c);

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    private void putPixels(int x, int y, int centerX, int centerY, int thickness, Color c){
        for (int i = 0; i < thickness; i++) {
            putPixel(x + centerX, (y + i) + centerY, c);
            putPixel(-x + centerX, (y + i) + centerY, c);
            putPixel(x + centerX, -(y + i) + centerY, c);
            putPixel(-x + centerX, -(y + i) + centerY, c);
            putPixel((y + i) + centerX, x + centerY, c);
            putPixel(-(y + i) + centerX, x + centerY, c);
            putPixel((y + i) + centerX, -x + centerY, c);
            putPixel(-(y + i) + centerX, -x + centerY, c);
        }
    }

    @Override
    public String getPanelName() {
        return "16 Grosor circunferencias";
    }
}
