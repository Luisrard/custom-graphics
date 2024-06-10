package com.luisrard.custom.graphics.third.partial.models;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Curve3D extends CustomBufferImage{
    public static final Color CURVE_COLOR = Color.WHITE;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    private double angleX = 0;
    private double angleY = 0;
    private double angleZ = 0;
    private double scale = 10; // Factor de escala para aumentar el tamaño de la curva
    public Curve3D(BufferedImage backGroundBuffer) {
        super(WIDTH, HEIGHT, backGroundBuffer);
    }

    private double[] rotatePoint(double x, double y, double z) {
        double cosX = Math.cos(angleX), sinX = Math.sin(angleX);
        double cosY = Math.cos(angleY), sinY = Math.sin(angleY);
        double cosZ = Math.cos(angleZ), sinZ = Math.sin(angleZ);

        // Rotate around X axis
        double y1 = y * cosX - z * sinX;
        double z1 = y * sinX + z * cosX;
        y = y1;
        z = z1;

        // Rotate around Y axis
        double x1 = x * cosY + z * sinY;
        z1 = -x * sinY + z * cosY;
        x = x1;
        z = z1;

        // Rotate around Z axis
        x1 = x * cosZ - y * sinZ;
        y1 = x * sinZ + y * cosZ;
        x = x1;
        y = y1;

        return new double[]{x, y, z};
    }

    public void drawCurve() {
        drawBackGround();

        double tMin = 0, tMax = 2 * Math.PI;
        double dt = 0.01;
        double prevX = 0, prevY = 0;
        boolean first = true;

        for (double t = tMin; t <= tMax; t += dt) {
            double x = Math.cos(t);
            double y = Math.sin(t);
            double z = t;

            double[] rotated = rotatePoint(x * scale, y * scale, z * scale);
            int[] projected = projectPoint(rotated[0], rotated[1], rotated[2]);

            if (!first) {
                drawLine((int) prevX, (int) prevY, projected[0], projected[1], CURVE_COLOR);
            }

            prevX = projected[0];
            prevY = projected[1];
            first = false;
        }
    }

    private int[] projectPoint(double x, double y, double z) {
        double scalePerspective = 1000 / (z + 1000); // Simple perspective projection
        int px = (int) (x * scale * scalePerspective + getWidth() / 2);
        int py = (int) (y * scale * scalePerspective + getHeight() / 2);
        return new int[]{px, py};
    }

    public void incrementAngles(double angleX, double angleY, double angleZ) {
        this.angleX += angleX;
        this.angleY += angleY;
        this.angleZ += angleZ;
    }
}
