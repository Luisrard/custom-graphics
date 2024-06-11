package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.Curve3D;

public class Curve3DPanel extends MainPanel {
    private static final int DELAY_FRAME_MS = 10;
    private Curve3D curve3D;

    public Curve3DPanel() {
        super();
    }

    public void doMove(){
        curve3D = new Curve3D(buffer);
        setCustomBufferImage(curve3D);
        new Thread(() -> {
            while (true) {
                curve3D.incrementAngles(1, 1, 1);
                curve3D.drawCurve();
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
