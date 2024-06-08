package com.luisrard.custom.graphics.second.partial;

import java.awt.*;

/*
El algoritmo de punto medio utiliza el concepto de punto medio entre dos puntos para trazar la línea, mientras que el algoritmo de Bresenham se basa en cálculos incrementales para determinar qué píxel debe ser seleccionado en cada paso.
 */
public class Model05MiddlePointPanel extends Model04BresenhamPanel {
    @Override
    public void doDraw() {
        drawLine(100,100,200,200,Color.RED);
        drawLine(100,100,200,100,Color.GREEN);
        drawLine(200,100, 100,201,Color.BLUE);
        drawLine(200,100, 400,200,Color.ORANGE);
        drawLine(200,100, 200,200,Color.ORANGE);
    }


    @Override
    public void drawLine(int x0, int y0, int x1, int y1, Color c) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;

        boolean dxGreater =  dx > dy;

        int maxD = dxGreater ? dx : dy;
        int minD = dxGreater ? dy : dx;
        int d = 2 * minD - maxD;
        int incrE = 2 * minD;
        int incrNE = 2 * (minD - maxD);
        int x = x0;
        int y = y0;

        putPixel(x, y, c);
        while (x != x1 || y != y1) {
            if (d <= 0) {
                d += incrE;
                if (dxGreater){
                    x += incX;
                } else {
                    y += incY;
                }
            } else {
                d += incrNE;
                x += incX;
                y += incY;
            }
            putPixel(x, y, c);
        }
    }

    @Override
    public String getPanelName() {
        return "05 Punto Medio";
    }
}
