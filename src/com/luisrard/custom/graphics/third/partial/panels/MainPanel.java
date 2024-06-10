package com.luisrard.custom.graphics.third.partial.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class MainPanel extends JPanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    protected BufferedImage buffer;
    protected BufferedImage customBufferImage;

    public MainPanel(){
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        doMove();
    }

    public void setCustomBufferImage(BufferedImage customBufferImage){
        this.customBufferImage = customBufferImage;
    }

    @Override
    public void paint(Graphics g) {
        if (Objects.nonNull(customBufferImage) && Objects.nonNull(g)){
            g.drawImage(customBufferImage, 0, 0, null);
        }
    }

    public void doMove(){
    }

}
