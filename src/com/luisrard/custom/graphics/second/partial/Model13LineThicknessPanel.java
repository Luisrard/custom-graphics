package com.luisrard.custom.graphics.second.partial;

import java.awt.*;

public class Model13LineThicknessPanel extends PaintPanel {
    @Override
    public void doDraw() {
        drawLine(100,100,200,200, 10,Color.RED);
        drawLine(100,100,200,100, 10, Color.GREEN);
        drawLine(200,100, 100,200, 10, Color.BLUE);
    }

    public void drawLine(int x0, int y0, int x1, int y1, int thickness, Color c) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int x = x0;
        int y = y0;

        boolean hasXInc = false;

        int e2 = 2 * err;
        if (e2 > -dy) {
            hasXInc = true;
        }
        if (e2 < dx) {
            hasXInc = false;
        }

        while (x != x1 || y != y1) {
            for(int i = 0; i < thickness;i ++){
                if (hasXInc){
                    putPixel(x, y + i * incY, c);
                } else{
                    putPixel(x + i * incY, y, c);
                }
            }
            e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += incX;
                hasXInc = true;
            }
            if (e2 < dx) {
                err += dx;
                y += incY;
                hasXInc = false;
            }
        }
    }
    @Override
    public String getPanelName() {
        return "13 Grosor de líneas rectas";
    }
}
