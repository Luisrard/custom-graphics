package com.luisrard.custom.graphics;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Model17ScanLinePanel extends PaintPanel {

    @Override
    public void doDraw() {
        int[] coordinatesTriangle = {100, 100, 300, 400, 500, 200};
        drawPolygon(coordinatesTriangle, Color.GREEN);
        scanLineFill(coordinatesTriangle, Color.RED);
        int[] coordinatesSecondFigure = {400, 400, 600, 400, 500, 200, 300, 200};
        drawPolygon(coordinatesSecondFigure, Color.BLUE);
        scanLineFill(coordinatesSecondFigure, Color.WHITE);
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

    public void fillLineLimits(int x0, int y0, int x1, int y1, HashSet<Integer>[] limits) {
        int dy = Math.abs(y1 - y0);
        if (dy == 0){
            return;
        }
        int dx = Math.abs(x1 - x0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int x = x0;
        int y = y0;

        while (x != x1 || y != y1) {
            if(limits[y] == null){
                limits[y] = new HashSet<>();
            }
            limits[y].add(x);

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

    private void scanLineFill(int[] coordinates, Color fillColor) {
        int length = coordinates.length;
        if (length < 6 && length % 2 != 0) {
            return;
        }
        int minY = coordinates[1];
        int maxY = coordinates[1];
        for (int i = 3; i < length; i += 2){
            minY = Math.min(coordinates[i], minY);
            maxY = Math.max(coordinates[i], maxY);
        }

        HashSet<Integer>[] limits = new HashSet[HEIGHT];

        for (int i = 2; i < length; i += 2){
            fillLineLimits(coordinates[i - 2], coordinates[i - 1], coordinates[i], coordinates[i + 1], limits);
        }
        fillLineLimits(coordinates[length - 2], coordinates[length - 1], coordinates[0], coordinates[1], limits);


        for (int y = minY + 1; y < maxY; y++) {
            HashSet<Integer> xLimits = limits[y];
            if (xLimits != null){
                if(xLimits.size() == 1){
                    //TODO
                    break;
                }
                List<Integer> xLimitsArray = xLimits.stream().sorted().collect(Collectors.toList());

                if (xLimitsArray.size() % 2 == 0) {
                    for (int i = 0; i < xLimitsArray.size(); i += 2) {
                        for (int x = xLimitsArray.get(i) + 1; x < xLimitsArray.get(i + 1); x++) {
                            putPixel(x, y, fillColor);
                        }
                    }
                } else {
                    for (int i = 1; i < xLimitsArray.size(); i ++) {
                        for (int x = xLimitsArray.get(i - 1) + 1; x < xLimitsArray.get(i); x++) {
                            putPixel(x, y, fillColor);
                        }
                    }
                }
            }
        }
    }


    @Override
    public String getPanelName() {
        return "17 Relleno de figuras Scan-Line";
    }
}
