package com.luisrard.custom.graphics.second.partial;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Model17ScanLinePanel extends PaintPanel {
    public static Map<String, int[]> characterVertices = new HashMap<>();
    @Override
    public void doDraw() {
        characterVertices.put("A", new int[]{10, 40, 20, 0, 30, 40, 22, 40, 20, 16, 18, 40});
        characterVertices.put("B", new int[]{10, 40, 10, 0, 25, 0, 25, 15, 30, 15, 30, 40});
        characterVertices.put("C", new int[]{10, 40, 10, 0, 30, 0, 30, 10, 25, 10, 25, 5, 15, 5, 15, 35, 25, 35, 25, 30, 30, 30, 30, 40});
        characterVertices.put("D", new int[]{10, 40, 10, 0, 20, 0, 25, 5, 25, 35, 20, 40});
        characterVertices.put("E", new int[]{10, 40, 10, 0, 30, 0, 30, 4, 14, 4, 14, 18, 30, 18, 30, 22, 14, 22, 14, 36, 30, 36, 30, 40});
        characterVertices.put("F", new int[]{10, 40, 10, 0, 30, 0, 30, 4, 14, 4, 14, 18, 30, 18, 30, 22, 14, 22, 14, 40});
        characterVertices.put("G", new int[]{10, 40, 10, 0, 30, 0, 30, 10, 25, 10, 25, 5, 15, 5, 15, 25, 20, 25, 20, 20, 30, 20, 30, 35, 25, 35, 25, 30, 20, 30, 20, 40, 10, 40});
        characterVertices.put("H", new int[]{10, 40, 10, 0, 16, 0, 16, 16, 24, 16, 24, 0, 30, 0, 30, 40, 24, 40, 24, 24, 16, 24 ,16, 40});
        characterVertices.put("I", new int[]{10, 40, 10, 0, 20, 0, 20, 40});
        characterVertices.put("J", new int[]{10, 40, 25, 40, 30, 35, 30, 0, 20, 0, 20, 30, 10, 30});
        characterVertices.put("K", new int[]{10, 40, 10, 0, 30, 0, 15, 20, 30, 40});
        characterVertices.put("L", new int[]{10, 40, 10, 0, 20, 0, 20, 30, 30, 30, 30, 40, 10, 40});
        characterVertices.put("M", new int[]{10, 40, 10, 0, 20, 10, 30, 0, 30, 40, 25, 40, 25, 20, 15, 20, 15, 40});
        characterVertices.put("N", new int[]{10, 40, 10, 0, 15, 0, 25, 35, 25, 0, 30, 0, 30, 40, 25, 40, 15, 5, 15, 40});
        characterVertices.put("O", new int[]{10, 40, 10, 0, 30, 0, 30, 40});
        characterVertices.put("P", new int[]{10, 40, 10, 0, 30, 0, 30, 20, 15, 20, 15, 40});
        characterVertices.put("Q", new int[]{10, 40, 10, 0, 28, 0, 28, 36, 32, 36, 32, 40});
        characterVertices.put("R", new int[]{10, 40, 10, 0, 30, 0, 30, 20, 15, 20, 30, 40});
        characterVertices.put("S", new int[]{10, 0, 30, 0, 30, 8, 26, 8, 26, 4, 14, 4, 14, 24, 30, 24, 30, 40, 10, 40, 10, 32, 14, 32, 14, 36, 26, 36, 26, 20 , 10, 20});
        characterVertices.put("T", new int[]{10, 0, 30, 0, 30, 4, 22, 4, 22, 40, 18, 40, 18, 4, 10, 4});
        characterVertices.put("U", new int[]{10, 0, 14, 0, 14, 30, 20, 36, 26, 30, 26, 0, 30, 0, 30, 30, 20, 40, 10, 30});
        characterVertices.put("V", new int[]{10, 0, 30, 0, 20, 40});
        characterVertices.put("W", new int[]{10, 0, 14, 0, 16, 8, 20, 0, 22, 0, 24, 8, 26, 0, 30, 0, 24, 40, 22, 40, 20 , 32, 18, 40, 16, 40});
        characterVertices.put("X", new int[]{10, 40, 30, 0, 10, 0, 30, 40, 10, 40});
        characterVertices.put("Y", new int[]{10, 0, 30, 0, 22, 20, 22, 40, 18, 40, 18, 20});
        characterVertices.put("Z", new int[]{10, 0, 30, 0 , 30, 4, 14, 36, 30, 36, 30, 40, 10, 40 , 10, 36, 26, 4, 10, 4});

        characterVertices.put("0", new int[]{10, 40, 10, 0, 30, 0, 30, 40, 10, 40});
        characterVertices.put("1", new int[]{10, 40, 10, 0, 20, 0, 20, 40});
        characterVertices.put("2", new int[]{10, 0, 30, 0, 30, 20, 10, 20, 10, 40, 30, 40});
        characterVertices.put("3", new int[]{10, 0, 30, 0, 22, 12, 30, 12, 30, 40, 14, 40 , 10, 36, 24, 24, 18, 12});
        characterVertices.put("4", new int[]{30, 0, 30, 40, 26, 40, 26, 20, 10, 20});
        characterVertices.put("5", new int[]{30, 0, 10, 0, 10, 20, 30, 20, 30, 40, 10, 40});
        characterVertices.put("6", new int[]{30, 0, 10, 0, 10, 40, 30, 40, 30, 20, 20, 20});
        characterVertices.put("7", new int[]{10, 0, 30, 0, 30, 40});
        characterVertices.put("8", new int[]{13, 0, 27, 0, 27, 20, 30, 20, 30, 40, 10, 40 , 10, 20, 13, 20});
        characterVertices.put("9", new int[]{10,0, 30, 0, 30, 40, 10, 40, 20, 20, 10, 20});

        int[] vertices = {14, 10, 22, 10, 22, 14, 26, 14, 26, 22, 22, 22, 22, 26, 14, 26, 14, 22, 10, 22, 10, 14, 14, 14};

        scanLineFill(vertices, Color.GREEN);
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
            if(limits[y0] == null){
                limits[y0] = new HashSet<>();
            }
            limits[y0].add(x0);
            limits[y0].add(x1);
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


        for (int y = minY; y <= maxY; y++) {
            HashSet<Integer> xLimits = limits[y];
            if (xLimits != null){
                List<Integer> xLimitsArray = xLimits.stream().sorted().collect(Collectors.toList());
                if(xLimits.size() == 1){
                    putPixel(xLimitsArray.get(0), y, fillColor);
                    continue;
                }

                if (xLimitsArray.size() % 2 == 0) {
                    for (int i = 0; i < xLimitsArray.size(); i += 2) {
                        for (int x = xLimitsArray.get(i); x <= xLimitsArray.get(i + 1); x++) {
                            putPixel(x, y, fillColor);
                        }
                    }
                } else {
                    for (int i = 1; i < xLimitsArray.size(); i ++) {
                        for (int x = xLimitsArray.get(i - 1); x <= xLimitsArray.get(i); x++) {
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
