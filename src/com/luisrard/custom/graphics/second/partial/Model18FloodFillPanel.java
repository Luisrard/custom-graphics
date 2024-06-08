package com.luisrard.custom.graphics.second.partial;

import java.awt.*;

public class Model18FloodFillPanel extends PaintPanel {
    private Color [][] pixels;
    @Override
    public void doDraw() {
        pixels = new Color[WIDTH][HEIGHT];
        int[] coordinatesTriangle = {100, 100, 300, 400, 500, 200};
        drawPolygon(coordinatesTriangle, Color.GREEN);
        floodFill(200, 200, Color.RED, Color.GREEN);
    }

    @Override
    public void putPixel(int x, int y, Color c) {
        super.putPixel(x, y, c);
        pixels[x][y] = c;
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

    public void drawPolygon(int[] coordinates, Color c){
        int length = coordinates.length;
        if (length >= 6 && length % 2 == 0){
            for (int i = 2; i < length; i += 2){
                drawLine(coordinates[i - 2], coordinates[i - 1], coordinates[i], coordinates[i + 1], c);
            }
            drawLine(coordinates[length - 2], coordinates[length - 1], coordinates[0], coordinates[1], c);
        }
    }

    private void floodFill(int x, int y, Color fillColor, Color limitColor) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return;
        }

        if (pixels[x][y] != null && (pixels[x][y] == limitColor || pixels[x][y] == fillColor)) {
            return;
        }

        putPixel(x,y, fillColor);

        floodFill(x + 1, y, fillColor, limitColor);
        floodFill(x - 1, y, fillColor, limitColor);
        floodFill(x, y + 1, fillColor, limitColor);
        floodFill(x, y - 1, fillColor, limitColor);
    }


    @Override
    public String getPanelName() {
        return "18 Relleno de figuras Flood Fill";
    }
}
