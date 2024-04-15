package com.luisrard.custom.graphics;

import java.awt.*;

public class Model01Panel extends PaintPanel {
    public Model01Panel() {
        super();
        drawLine(100,100,200,200,Color.RED);
        drawLine(100,100,200,100,Color.GREEN);
        drawLine(200,100, 100,200,Color.BLUE);
    }


    @Override
    public void drawLine(int x0, int y0, int x1, int y1, Color c){
        boolean inc = x1 > x0;
        float m = (y1 - y0) / (x1 - x0);
        float b = y0 - m * x0;

        for(int x = x0, y; x != x1;) {
            y = (int) (m * x + b);

            putPixel(x, y, c);

            if (inc){
                x++;
            } else {
                x--;
            }
        }
    }

    @Override
    public String getPanelName() {
        return "01 Modelo matemático (línea recta)";
    }
}
