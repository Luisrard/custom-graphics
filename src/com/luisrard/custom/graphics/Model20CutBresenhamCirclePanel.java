package com.luisrard.custom.graphics;

import java.awt.*;

public class Model20CutBresenhamCirclePanel extends PaintPanel {
    private static final int INSIDE = 0; // 0000
    private static final int LEFT = 1;   // 0001
    private static final int RIGHT = 2;  // 0010
    private static final int BOTTOM = 4; // 0100
    private static final int TOP = 8;    // 1000

    @Override
    public void doDraw() {
//        int [] rectangleCut = new int[]{0, 0, WIDTH, HEIGHT};
        int [] rectangleCut = new int[]{100, 100, 250, 250};

        fillRectangle(rectangleCut, Color.GRAY);
        drawCircleWithCut(100,100,50, rectangleCut, Color.RED);
        drawCircleWithCut(150,150,50, rectangleCut, Color.GREEN);
        drawCircleWithCut(150,200,50, rectangleCut, Color.BLUE);
        drawCircleWithCut(300,200, 50, rectangleCut, Color.ORANGE);

        drawCircleWithCut(50,50, 50, rectangleCut, Color.WHITE);
    }

    public void fillRectangle(int [] rectangle, Color c){
        fillRectangle(rectangle[0], rectangle[1], rectangle[2], rectangle[3], c);
    }
    public void fillRectangle(int x0, int y0, int x1, int y1, Color c){
        for(int x = x0; x <= x1; x++){
            for (int y = y0; y <= y1; y++){
                putPixel(x, y, c);
            }
        }
    }

    public void drawCircleWithCut(int centerX, int centerY, int radius, int [] rectangleCut, Color c){
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;

        while (x <= y) {
            drawCirclePoints(centerX, centerY, x, y, rectangleCut, c);

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    private void drawCirclePoints(int centerX, int centerY, int x, int y, int [] rectangleCut, Color c) {
        int code = getCode(centerX + x, centerY + y, rectangleCut);
        if (code == INSIDE) {
            putPixel(centerX + x, centerY + y, c);
        }
        code = getCode(centerX - x, centerY + y, rectangleCut);
        if (code == INSIDE) {
            putPixel(centerX - x, centerY + y, c);
        }
        code = getCode(centerX + x, centerY - y, rectangleCut);
        if (code == INSIDE) {
            putPixel(centerX + x, centerY - y, c);
        }
        code = getCode(centerX - x, centerY - y, rectangleCut);
        if (code == INSIDE) {
            putPixel(centerX - x, centerY - y, c);
        }
        code = getCode(centerX + y, centerY + x, rectangleCut);
        if (code == INSIDE) {
            putPixel(centerX + y, centerY + x, c);
        }
        code = getCode(centerX - y, centerY + x, rectangleCut);
        if (code == INSIDE) {
            putPixel(centerX - y, centerY + x, c);
        }
        code = getCode(centerX + y, centerY - x, rectangleCut);
        if (code == INSIDE) {
            putPixel(centerX + y, centerY - x, c);
        }
        code = getCode(centerX - y, centerY - x, rectangleCut);
        if (code == INSIDE) {
            putPixel(centerX - y, centerY - x, c);
        }
    }

    private int getCode(int x, int y, int [] rectangleCut){
        if (x < rectangleCut[0]) {
            return LEFT;
        } else if (x > rectangleCut[2]) {
            return RIGHT;
        }
        if (y < rectangleCut[1]){
            return TOP;
        } else if (y > rectangleCut[3]) {
            return BOTTOM;
        }
        return INSIDE;
    }

    @Override
    public String getPanelName() {
        return "20 Recorte de circunferencias";
    }
}
