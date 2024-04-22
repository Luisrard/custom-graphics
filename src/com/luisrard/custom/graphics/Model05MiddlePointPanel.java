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
        float x, y, dx, dy, incX = 1, incY =1, incE, incNE, p = 0;
        x = x0;
        y = y0;

        dx = x1 - x0;
        dy = y1 - y0;

        if (dx < 0) {
            dx = -dx;
            incX = -1;
        }
        if (dy < 0) {
            dy = -dy;
            incY = -1;
        }

        if (dx > dy) {
            incE = 2 * dy;
            incNE = 2 * (dy - dx);

            while (x != x1) {
                putPixel((int) x,(int) y, c);
                x = x + incX;
                if (2 * (p + dy) < dx){
                    p = p + incE;
                }
                else {
                    p = p + incNE;
                    y += incY;
                }
            }
        }
        else {
            incE = 2 * dx;
            incNE = 2 * (dx - dy);

            while (y != y1) {
                putPixel((int) x,(int) y, c);
                y = y + incY;
                if (2 * (p + dx) < dy){
                    p = p + incE;
                }
                else {
                    p = p + incNE;
                    x += incX;
                }
            }
        }
    }

    @Override
    public String getPanelName() {
        return "05 Punto Medio";
    }
}
