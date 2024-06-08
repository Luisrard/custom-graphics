package com.luisrard.custom.graphics.second.partial;

import java.awt.*;

public interface IPaintFullFrame extends IPaintFrame {
    default void drawLine(int x0, int y0, int x1, int y1, Color c) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int x = x0;
        int y = y0;
        while (x != x1 || y != y1) {
            putPixel(x, y0, c);
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


    default void drawRectangle(int x, int y, int width, int height, Color c){
        drawLine(x, y, x + width, y, c);
        drawLine(x, y + height, x + width, y + height, c);
        drawLine(x, y, x, y + height, c);
        drawLine(x + width, y, x + width, y + height, c);
    }

    default void drawCircle(int centerX, int centerY, int radius, Color c){
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;

        while (x <= y) {
            putPixel(x + centerX, y + centerY, c);
            putPixel(-x + centerX, y + centerY, c);
            putPixel(x + centerX, -y + centerY, c);
            putPixel(-x + centerX, -y + centerY, c);
            putPixel(y + centerX, x + centerY, c);
            putPixel(-y + centerX, x + centerY, c);
            putPixel(y + centerX, -x + centerY, c);
            putPixel(-y + centerX, -x + centerY, c);

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    default void drawEllipse(int a, int b, int centerX, int centerY, Color c) {
        int x = 0;
        int y = b;
        long a2 = a * a;
        long b2 = b * b;
        long d = 4 * b2 - 4 * a * b2 + a2;

        while (2 * b2 * x <= 2 * a2 * y) {
            putPixel(x + centerX, y + centerY, c);
            putPixel(-x + centerX, y + centerY, c);
            putPixel(x + centerX, -y + centerY, c);
            putPixel(-x + centerX, -y + centerY, c);

            if (d < 0) {
                x++;
                d = d + 4 * b2 * (2 * x + 3);
            } else {
                x++;
                y--;
                d = d + 4 * b2 * (2 * x + 3) + 4 * a2 * (-2 * y + 2);
            }
        }

        d = b2 * (2 * x + 1) * (2 * x + 1) + 4 * a2 * (y - 1) * (y - 1) - 4 * a2 * b2;

        while (y >= 0) {
            putPixel(x + centerX, y + centerY, c);
            putPixel(-x + centerX, y + centerY, c);
            putPixel(x + centerX, -y + centerY, c);
            putPixel(-x + centerX, -y + centerY, c);

            if (d < 0) {
                y--;
                x++;
                d = d + 4 * b2 * (2 * x + 2) + 4 * a2 * (-2 * y + 3);
            } else {
                y--;
                d = d + 4 * a2 * (-2 * y + 3);
            }
        }
    }
}
