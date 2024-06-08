package com.luisrard.custom.graphics.third.partial;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    protected BufferedImage buffer;
    private CubeParallel cube;

    public MainPanel(){
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        doMove();
    }

    public void doMove(){
        cube = new CubeParallel(buffer);
        new Thread(()->{
            while (true){
                cube.moveObject();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                paint(getGraphics());
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(cube, 0, 0, null);
    }

}
