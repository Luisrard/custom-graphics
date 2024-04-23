package com.luisrard.custom.graphics;

import java.awt.*;

public class Model11CircleBresenhamPanel extends PaintPanel {
    static final byte [] xyArray = {1,-1};

    @Override
    public void doDraw() {
        for (int i = 0, r; i <= 8; i ++){
            r = 50 + 15 * i;
            drawCircle(200,200, r, Color.GREEN);
            drawCircle(200,200, r + 5, Color.RED);
            drawCircle(200,200, r + 10, Color.YELLOW);

        }
    }

    public void drawCircle(int centerX, int centerY, int radius, Color c){
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;

        while (x <= y) {
            putPixel(x + centerX, y + centerY, c);
            putPixel(-x + centerX, y + centerY, c);
            putPixel(x + centerX, -y + centerY, c);
            putPixel(-x + centerX, -y + centerY, c);
            putPixel(y + centerX, x + centerY, c);
            putPixel(-y + centerX, x + centerY, c);
            putPixel(y + centerX, -x + centerY, c);
            putPixel(-y + centerX, -x + centerY, c);

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    @Override
    public String getPanelName() {
        return "11 Circunferencia Bresenham";
    }
}
