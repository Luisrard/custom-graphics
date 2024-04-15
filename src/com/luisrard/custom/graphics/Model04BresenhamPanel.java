package com.luisrard.custom.graphics;

import java.awt.*;

public class Model04BresenhamPanel extends PaintPanel {

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

        int incX = 1, incY = 1;
        if (diffX < 0){
            incX = -1;
        }
        if (diffY < 0){
            incY = -1;
        }

        diffX = Math.abs(diffX);
        diffY =  Math.abs(diffY);

        boolean dxMax = diffX >  diffY;

        if(dxMax){
            int A = 2 * diffY;
            int B = A - 2 * diffX;
            int p = A - diffX;

            for (int x = x0, y = y0; x != x1; ){
                x += incX;
                if (p < 0){
                    putPixel(x, y, c);
                    p = p + A;
                } else {
                    y += incY;

                    putPixel(x, y, c);
                    p = p + B;
                }
            }
        } else {
            int A = 2 * diffX;
            int B = A - 2 * diffY;
            int p = A - diffY;

            for (int x = x0, y = y0; y != y1; ){
                y += incY;
                if (p < 0){
                    putPixel(x, y, c);
                    p = p + A;
                } else {
                    x += incX;

                    putPixel(x, y, c);
                    p = p + B;
                }
            }
        }

    }

    @Override
    public String getPanelName() {
        return "04 Algoritmo de Bresenham (línea recta)";
    }
}
