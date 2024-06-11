package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.CubeParallel;

public class ParallelPanel extends MainPanel {
    private static final int DELAY_FRAME_MS = 100;

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
                    Thread.sleep(DELAY_FRAME_MS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                paint(getGraphics());
            }
        }).start();
    }
}
