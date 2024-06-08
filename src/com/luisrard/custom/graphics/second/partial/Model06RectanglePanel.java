package com.luisrard.custom.graphics.second.partial;

import java.awt.*;

public class Model06RectanglePanel extends Model04BresenhamPanel {
    @Override
    public void doDraw() {
        for (int i = 0; i < 100; i += 10){
            drawRectangle(10 + i,100 + i,200,100, Color.RED);
        }
    }

    public void drawRectangle(int x, int y, int width, int height, Color c){
        drawLine(x, y, x + width, y, c);
        drawLine(x, y + height, x + width, y + height, c);
        drawLine(x, y, x, y + height, c);
        drawLine(x + width, y, x + width, y + height, c);
    }

    @Override
    public String getPanelName() {
        return "06 Rectangulo";
    }
}
