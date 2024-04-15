package com.luisrard.custom.graphics;

import java.awt.*;

public class Model02ImprovementPanel extends PaintPanel {
    @Override
    public void doDraw() {
        drawLine(100,100,200,200,Color.RED);
        drawLine(100,100,200,100,Color.GREEN);
        drawLine(200,100, 100,200,Color.BLUE);
    }

    @Override
    public void drawLine(int x0, int y0, int x1, int y1, Color c) {
        int diffX = x1 - x0;
        int diffY = y1 - y0;

        if (diffX == 0 && diffY == 0){
            putPixel(x0,y0,c);
            return;
        }
        boolean inc;
        float m;
        float b;
        if(diffX > diffY) {
            inc = x1 > x0;
            m = (float) diffY / diffX;
            b = y0 - m * x0;

            for(int x = x0, y; x != x1;) {
                y = (int) (m * x + b);

                putPixel(x, y, c);

                if (inc){
                    x++;
                } else {
                    x--;
                }
            }
        } else{
            inc = y1 > y0;
            m = (float) diffX / diffY;
            b = x0 - m * y0;

            for(int y = y0, x; y != y1;) {
                x = (int) (m * y + b);

                putPixel(x, y, c);

                if (inc){
                    y++;
                } else {
                    y--;
                }
            }
        }


    }

    @Override
    public String getPanelName() {
        return "02 Modelo matemático mejorado (línea recta)";
    }
}
