package com.luisrard.custom.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PaintPanel extends JPanel implements IPaintFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private BufferedImage buffer;

    public PaintPanel(){
        buffer = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        setSize(WIDTH, HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, null);
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    public String getPanelName(){
        return  "Default";
    }
}
