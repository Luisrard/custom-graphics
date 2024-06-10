package com.luisrard.custom.graphics.third.partial.models;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cylinder3D extends CustomBufferImage {
    public static final Color CURVE_COLOR = Color.WHITE;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private double angleX = 0, angleY = 0, angleZ = 0;
    private double moveX = 0, moveY = 0, moveZ = 0;

    public Cylinder3D(BufferedImage backGroundBuffer) {
        super(WIDTH, HEIGHT, backGroundBuffer);
    }

    public void drawCylinder() {
        drawBackGround();
        double t, phi;
        double x, y, z;
        int screenX, screenY;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (t = -Math.PI; t <= Math.PI; t += 0.1) {
            for (phi = 0; phi <= 2 * Math.PI; phi += 0.1) {
                x = (2 + Math.cos(t)) * Math.cos(phi);
                y = (2 + Math.cos(t)) * Math.sin(phi);
                z = t;

                // Rotación en el eje X
                double tempY = y * Math.cos(angleX) - z * Math.sin(angleX);
                double tempZ = y * Math.sin(angleX) + z * Math.cos(angleX);
                y = tempY;
                z = tempZ;

                // Rotación en el eje Y
                double tempX = x * Math.cos(angleY) - z * Math.sin(angleY);
                tempZ = x * Math.sin(angleY) + z * Math.cos(angleY);
                x = tempX;
                z = tempZ;

                // Rotación en el eje Z
                tempX = x * Math.cos(angleZ) - y * Math.sin(angleZ);
                tempY = x * Math.sin(angleZ) + y * Math.cos(angleZ);
                x = tempX;
                y = tempY;

                // Traslación
                x += moveX;
                y += moveY;
                z += moveZ;

                // Proyección en 2D
                screenX = (int) (centerX + x * 100);
                screenY = (int) (centerY - y * 100); // Invertir y para coordenadas de pantalla

                putPixel(screenX, screenY, CURVE_COLOR);
            }
        }
    }

    public void incrementAngles(double angleX, double angleY, double angleZ) {
        this.angleX += angleX;
        this.angleY += angleY;
        this.angleZ += angleZ;
    }
}
