package com.luisrard.custom.graphics;

import java.awt.*;

public class Model06RectanglePanel extends Model04BresenhamPanel {
    @Override
    public void doDraw() {
        drawRectangle(10,100,200,100, Color.RED);
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
