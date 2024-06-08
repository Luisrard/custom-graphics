package com.luisrard.custom.graphics.second.partial;

import java.awt.*;

/*
Algunas de las deficiencias identificadas es que se calcula cada paso aunque no se tenga que imprimir, se podria mejorar eso y dar saltos mas largos
 */
public class Model15CircleMaskPanel extends PaintPanel {
    @Override
    public void doDraw() {
        for (int i = 0, r; i <= 8; i ++){
            r = 50 + 15 * i;
            drawCircle(200,200, r,0b11, 4, Color.GREEN);
            drawCircle(200,200, r + 5,0b11110000, 8,  Color.RED);
            drawCircle(200,200, r + 10, 520, 9, Color.YELLOW);

        }
    }

    public void drawCircle(int centerX, int centerY, int radius, int mask, int maskBits, Color c){
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;

        int i = 0;
        while (x <= y) {
            if ((mask >> i & 1) == 1){
                putPixel(x + centerX, y + centerY, c);
                putPixel(-x + centerX, y + centerY, c);
                putPixel(x + centerX, -y + centerY, c);
                putPixel(-x + centerX, -y + centerY, c);
                putPixel(y + centerX, x + centerY, c);
                putPixel(-y + centerX, x + centerY, c);
                putPixel(y + centerX, -x + centerY, c);
                putPixel(-y + centerX, -x + centerY, c);
            }

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
            i ++;
            if (i >= maskBits){
                i = 1;
            }
        }
    }

    @Override
    public String getPanelName() {
        return "15 Tipo lineas circunferencias";
    }
}
