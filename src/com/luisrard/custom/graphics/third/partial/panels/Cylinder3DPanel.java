package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.Cylinder3D;

public class Cylinder3DPanel extends MainPanel {
    private static final int DELAY_FRAME_MS = 10;
    private Cylinder3D cylinder3D;

    public Cylinder3DPanel() {
        super();
    }

    public void doMove(){
        cylinder3D = new Cylinder3D(buffer);
        setCustomBufferImage(cylinder3D);
        new Thread(() -> {
            while (true) {
                cylinder3D.incrementAngles(0.5, 0.5, 0);
                cylinder3D.drawCylinder();
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
