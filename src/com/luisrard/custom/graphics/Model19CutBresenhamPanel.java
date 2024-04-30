package com.luisrard.custom.graphics;

import java.awt.*;

public class Model19CutBresenhamPanel extends PaintPanel {
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
        drawLineWithCut(100,100,300,300, rectangleCut, Color.RED);
        drawLineWithCut(100,100,300,100, rectangleCut, Color.GREEN);
        drawLineWithCut(100,300,200,100,  rectangleCut, Color.BLUE);
        drawLineWithCut(300,200, 100,200, rectangleCut, Color.ORANGE);

        drawLineWithCut(50,50, 300,300, rectangleCut, Color.WHITE);
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

    public void drawLineWithCut(int x0, int y0, int x1, int y1, int [] rectangleCut, Color c){
        int xStart = rectangleCut[0];
        int yStart = rectangleCut[1];
        int xEnd = rectangleCut[2];
        int yEnd = rectangleCut[3];

        int code0 = getCode(x0, y0, xStart, yStart, xEnd, yEnd);
        int code1 = getCode(x1, y1, xStart, yStart, xEnd, yEnd);
        boolean accept = false;

        while (true) {
            if ((code0 | code1) == 0) { // Both points Inside cut region
                accept = true;
                break;
            } else if ((code0 & code1) != 0) {  // Both points Outside cut region
                break;
            } else {
                int x, y;
                int codeOut = (code0 != 0) ? code0 : code1;

                int diffX = x1 - x0;
                int diffY = y1 - y0;
                if ((codeOut & TOP) != 0) {
                    x = x0 +  (yStart - y0) * diffX/ diffY;
                    y = yStart;
                } else if ((codeOut & BOTTOM) != 0) {
                    x = x0 + (yEnd - y0) * diffX/ diffY;
                    y = yEnd;
                } else if ((codeOut & RIGHT) != 0) {
                    y = y0 + (xEnd - x0) * diffY / diffX;
                    x = xEnd;
                } else {
                    y = y0 + (xStart - x0) * diffY / diffX;
                    x = xStart;
                }

                if (codeOut == code0) {
                    x0 = x;
                    y0 = y;
                    code0 = getCode(x0, y0, xStart, yStart, xEnd, yEnd);
                } else {
                    x1 = x;
                    y1 = y;
                    code1 = getCode(x1, y1, xStart, yStart, xEnd, yEnd);
                }
            }
        }

        if (accept) {
            drawLine(x0, y0, x1, y1, c);
        }
    }

    private int getCode(int x, int y, int xStart, int yStart, int xEnd, int yEnd){
        int code = INSIDE;
        if (x < xStart) {
            code |= LEFT;
        } else if (x > xEnd) {
            code |= RIGHT;
        }
        if (y < yStart){
            code |= TOP;
        } else if (y > yEnd) {
            code |= BOTTOM;
        }
        return code;
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color c) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int x = x0;
        int y = y0;
        while (x != x1 || y != y1) {
            putPixel(x, y, c);
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += incX;
            }
            if (e2 < dx) {
                err += dx;
                y += incY;
            }
        }
    }

    @Override
    public String getPanelName() {
        return "19 Algoritmo Recorte de líneas rectas";
    }
}
