package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.Surface3D;

import java.awt.*;
import java.util.Objects;

public class Surface3DPanel extends MainPanel {
    private Surface3D surface3D;

    public Surface3DPanel() {
        super();
    }

    public void doMove(){
        surface3D = new Surface3D(buffer);
        setCustomBufferImage(surface3D);
        new Thread(() -> {
            while (true) {
                surface3D.incrementAngles(.5, .5, .5);
                surface3D.drawSuperficie();
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
