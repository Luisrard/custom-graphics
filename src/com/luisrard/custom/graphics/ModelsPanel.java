package com.luisrard.custom.graphics;

import java.awt.*;

public class ModelsPanel extends PaintPanel {
    @Override
    public void doDraw() {
        drawLine(0,0, 599,599, Color.white);

        drawCircle(200,200, 100, Color.GREEN);
        drawRectangle(100,100, 100,200, Color.GREEN);

        for (int i = 0; i < 100; i += 10){
            drawEllipse(50,100,100  + i ,100 + i, Color.RED);
            drawEllipse(50,100,100 + i,100 + i, Color.RED);
        }
    }

    @Override
    public void drawLine(int x0, int y0, int x1, int y1, Color c){
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


    public void drawRectangle(int x, int y, int width, int height, Color c){
        drawLine(x, y, x + width, y, c);
        drawLine(x, y + height, x + width, y + height, c);
        drawLine(x, y, x, y + height, c);
        drawLine(x + width, y, x + width, y + height, c);
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

    public void drawEllipse(int a, int b, int centerX, int centerY, Color c) {
        int x = 0;
        int y = b;
        long a2 = a * a;
        long b2 = b * b;
        long d = 4 * b2 - 4 * a * b2 + a2;

        while (2 * b2 * x <= 2 * a2 * y) {
            putPixel(x + centerX, y + centerY, c);
            putPixel(-x + centerX, y + centerY, c);
            putPixel(x + centerX, -y + centerY, c);
            putPixel(-x + centerX, -y + centerY, c);

            if (d < 0) {
                x++;
                d = d + 4 * b2 * (2 * x + 3);
            } else {
                x++;
                y--;
                d = d + 4 * b2 * (2 * x + 3) + 4 * a2 * (-2 * y + 2);
            }
        }

        d = b2 * (2 * x + 1) * (2 * x + 1) + 4 * a2 * (y - 1) * (y - 1) - 4 * a2 * b2;

        while (y >= 0) {
            putPixel(x + centerX, y + centerY, c);
            putPixel(-x + centerX, y + centerY, c);
            putPixel(x + centerX, -y + centerY, c);
            putPixel(-x + centerX, -y + centerY, c);

            if (d < 0) {
                y--;
                x++;
                d = d + 4 * b2 * (2 * x + 2) + 4 * a2 * (-2 * y + 3);
            } else {
                y--;
                d = d + 4 * a2 * (-2 * y + 3);
            }
        }
    }

    @Override
    public String getPanelName() {
        return "Modelo de Figuras";
    }
}
