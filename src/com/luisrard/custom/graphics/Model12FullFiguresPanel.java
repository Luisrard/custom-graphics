package com.luisrard.custom.graphics;

import java.awt.*;

public class Model12FullFiguresPanel extends PaintPanel implements  IPaintFullFrame {
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
    public String getPanelName() {
        return "Modelo de Figuras";
    }
}
