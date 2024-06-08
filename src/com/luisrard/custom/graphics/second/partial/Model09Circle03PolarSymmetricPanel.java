package com.luisrard.custom.graphics.second.partial;

import java.awt.*;

/*
Una ventaja que tiene este codigo es que se reduce en un cuarto la cantidad de operaciones para imprimir un circulo
 */
public class Model09Circle03PolarSymmetricPanel extends PaintPanel {
    static final byte [] xyArray = {1,-1};

    @Override
    public void doDraw() {
        for (int i = 0, r; i <= 8; i ++){
            r = 50 + 15 * i;
            drawCircle(200,200, r, Color.GREEN);
            drawCircle(200,200, r + 5, Color.RED);
            drawCircle(200,200, r + 10, Color.YELLOW);

        }
    }

    public void drawCircle(int xc, int yc, int r, Color c){
        int startX = xc - r;
        int endX = xc + r;

        if (startX < 0 || endX > WIDTH || yc - r < 0 || yc + r > HEIGHT){
            //TODO: Throw custom exception
            return;
        }

        int x, y;
        double fourthOfPI = Math.PI / 4;
        for (double t = Math.PI / 2; t >= fourthOfPI; t-=.01) {
            int xPiv = (int) (r * Math.sin(t));
            int yPiv = (int) (r * Math.cos(t));

            for(byte xi = 0; xi < 2; xi ++){
                for (byte yi = 0; yi < 2; yi ++){
                    x = xc + xPiv * xyArray[xi];
                    y = yc + yPiv * xyArray[yi];

                    putPixel(x, y, c);
                    putPixel(y, x, c);
                }
            }
        }
    }

    @Override
    public String getPanelName() {
        return "09 Simetría de ocho lados";
    }
}
