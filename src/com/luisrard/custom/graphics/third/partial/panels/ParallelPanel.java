package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.CubeParallel;

public class ParallelPanel extends MainPanel {

    private CubeParallel cube;

    public ParallelPanel(){
        super();
    }

    public void doMove(){
        cube = new CubeParallel(buffer);
        setCustomBufferImage(cube);
        new Thread(() -> {
            while (true) {
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
