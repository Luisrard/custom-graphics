package com.luisrard.custom.graphics.second.partial;

import java.awt.*;
/*
Algunas deficiencias de este codigo es que cada desplazamiento requiere un calculo de raiz cuadrada y elevaciones al cuadrado, lo cual no es eficiente
 */
public class Model07Circle01Panel extends PaintPanel {
    @Override
    public void doDraw() {
        drawCircle(200,200, 100, Color.RED);
    }

    public void drawCircle(int xc, int yc, int r, Color c){
        int startX = xc - r;
        int endX = xc + r;

        if (startX < 0 || endX > WIDTH || yc - r < 0 || yc + r > HEIGHT){
            //TODO: Throw custom exception
            return;
        }
        double rSqr = Math.pow(r, 2);

        for (int x = startX; x <= endX; x++) {
            double y = Math.sqrt(rSqr  -  Math.pow((x - xc), 2));
            putPixel(x, yc + (int) y, c);
            putPixel(x, yc - (int) y, c);
        }
    }

    @Override
    public String getPanelName() {
        return "07 Circulo";
    }
}
