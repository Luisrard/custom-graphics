package com.luisrard.custom.graphics;

import java.awt.*;

public interface IPaintFrame {
    void drawLine(int x0, int y0, int x1, int y1, Color c);
    void putPixel(int x, int y, Color c);
}
