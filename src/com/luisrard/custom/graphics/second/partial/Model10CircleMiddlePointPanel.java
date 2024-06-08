package com.luisrard.custom.graphics.second.partial;

import java.awt.*;

public class Model10CircleMiddlePointPanel extends PaintPanel {
    @Override
    public void doDraw() {
        for (int i = 0, r; i <= 8; i ++){
            r = 50 + 15 * i;
            drawCircle(200,200, r, Color.GREEN);
            drawCircle(200,200, r + 5, Color.RED);
            drawCircle(200,200, r + 10, Color.YELLOW);

        }
    }

    public void drawCircle(int centerX, int centerY, int radius, Color c){
        int x = radius;
        int y = 0;
        int decisionOver2 = 1 - x;

        while (y <= x) {
            putPixel(x + centerX, y + centerY, c);
            putPixel(y + centerX, x + centerY, c);
            putPixel(-x + centerX, y + centerY, c);
            putPixel(-y + centerX, x + centerY, c);
            putPixel(-x + centerX, -y + centerY, c);
            putPixel(-y + centerX, -x + centerY, c);
            putPixel(x + centerX, -y + centerY, c);
            putPixel(y + centerX, -x + centerY, c);
            y++;
            if (decisionOver2 <= 0) {
                decisionOver2 += 2 * y + 1;
            } else {
                x--;
                decisionOver2 += 2 * (y - x) + 1;
            }
        }
    }

    @Override
    public String getPanelName() {
        return "10 Circunferencia Punto Medio";
    }
}
