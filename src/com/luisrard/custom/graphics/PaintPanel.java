package com.luisrard.custom.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel implements IPaintFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    private BufferedImage buffer;

    public PaintPanel(){
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        setSize(WIDTH, HEIGHT);
        doDraw();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, null);
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
