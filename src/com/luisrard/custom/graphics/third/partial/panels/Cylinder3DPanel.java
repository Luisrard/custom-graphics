package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.Cylinder3D;

public class Cylinder3DPanel extends MainPanel {
    private Cylinder3D cylinder3D;

    public Cylinder3DPanel() {
        super();
    }

    public void doMove(){
        cylinder3D = new Cylinder3D(buffer);
        setCustomBufferImage(cylinder3D);
        new Thread(() -> {
            while (true) {
                cylinder3D.incrementAngles(0.01, 0.01, 0.01);
                cylinder3D.drawCylinder();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                paint(getGraphics());
            }
        }).start();
    }

}
