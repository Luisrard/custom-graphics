package com.luisrard.custom.graphics;

import java.awt.*;

public class Model08Circle02PolarPanel extends PaintPanel {
    @Override
    public void doDraw() {
        drawCircle(200,200, 100, Color.GREEN);
    }

    public void drawCircle(int xc, int yc, int r, Color c){
        int startX = xc - r;
        int endX = xc + r;

        if (startX < 0 || endX > WIDTH || yc - r < 0 || yc + r > HEIGHT){
            //TODO: Throw custom exception
            return;
        }

        int x, y;
        for (double t = 0; t < 2 * Math.PI; t+=.01) {
            x = xc + (int) (r * Math.sin(t));
            y = yc + (int) (r * Math.cos(t));

            putPixel(x, y, c);
            putPixel(y, x, c);
        }
    }

    @Override
    public String getPanelName() {
        return "08 Circulo Coordenadas Polares";
    }
}
