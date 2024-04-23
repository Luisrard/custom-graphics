package com.luisrard.custom.graphics;

import java.awt.*;

public class Model03DDAPanel extends PaintPanel {
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

        int steps = Math.max(Math.abs(diffX), Math.abs(diffY));

        float xInc = (float) diffX / steps;
        float yInc = (float) diffY / steps;

        float x = x0;
        float y = y0;

        for (int k = 0; k < steps; k++){
            putPixel((int)x, (int)y, c);
            x = x + xInc;
            y = y + yInc;
        }
    }

    @Override
    public String getPanelName() {
        return "03 Algoritmo DDA (línea recta)";
    }
}
