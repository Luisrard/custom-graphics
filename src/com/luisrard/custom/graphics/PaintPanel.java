package com.luisrard.custom.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel implements IPaintFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private BufferedImage buffer;

    public PaintPanel(){
        buffer = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        setSize(WIDTH, HEIGHT);
        doDraw();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void drawLine(int x0, int y0, int x1, int y1, Color c) {
        throw new RuntimeException("Method unimplemented");
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    public void doDraw(){
        putPixel(100,100, Color.RED);
    }

    public String getPanelName(){
        return  "Default";
    }
}
