package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.Curve3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Curve3DPanel extends MainPanel {
    private Curve3D curve3D;

    public Curve3DPanel() {
        super();
    }

    public void doMove(){
        curve3D = new Curve3D(buffer);
        new Thread(() -> {
            while (true) {
                curve3D.incrementAngles(0.01, 0.01, 0.01);
                curve3D.drawCurve();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                paint(getGraphics());
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(curve3D, 0, 0, null);
    }

}