package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.CubePerspective;

public class PerspectivePanel extends MainPanel {
    private static final int DELAY_FRAME_MS = 100;
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
                    Thread.sleep(DELAY_FRAME_MS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                paint(getGraphics());
            }
        }).start();
    }

}
