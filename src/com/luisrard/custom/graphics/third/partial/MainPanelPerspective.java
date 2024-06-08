package com.luisrard.custom.graphics.third.partial;

import java.awt.*;

public class MainPanelPerspective extends MainPanel {
    private CubePerspective cube;

    public MainPanelPerspective(){
        super();
    }

    public void doMove(){
        cube = new CubePerspective(buffer);
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
