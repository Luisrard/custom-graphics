package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.CubeParallel;

public class RotationAutomaticPanel extends MainPanel {
    private CubeParallel cube;

    private static final int MOVE_IN_Y = 5, MOVE_IN_X = 5, MOVE_IN_Z = 5;
    public RotationAutomaticPanel(){
        super();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                cube.increaseRotation(MOVE_IN_X, MOVE_IN_Y, MOVE_IN_Z);
                cube.drawObject();
                paint(getGraphics());
            }
        }).start();

    }

    @Override
    public void doMove() {
        cube = new CubeParallel(buffer);
        setCustomBufferImage(cube);
        cube.drawObject();
    }
}
