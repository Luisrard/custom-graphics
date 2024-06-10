package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.CubePerspective;

import java.awt.*;

public class PerspectivePanel extends MainPanel {
    private CubePerspective cube;

    public PerspectivePanel(){
        super();
    }

    public void doMove(){
        cube = new CubePerspective(buffer);
        setCustomBufferImage(cube);
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

}
