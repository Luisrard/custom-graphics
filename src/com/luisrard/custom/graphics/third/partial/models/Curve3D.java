package com.luisrard.custom.graphics.third.partial.models;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Curve3D extends CustomBufferImage{
    public static final Color CURVE_COLOR = Color.WHITE;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    private double angleX = 0;
    private double angleY = 0;
    private double angleZ = 0;
    private double scale = 100; // Factor de escala para aumentar el tamaño de la curva

    public static final double[][] vertices;

    static {
        double tMin = 0, tMax = 2 * Math.PI;
        double stepSize = 0.1;
        vertices = new double[(int) (tMax / stepSize) + 1][4];
        int i = 0;
        for (double t = tMin; t <= tMax; t += stepSize, i++) {
            vertices[i] = new double[]{Math.cos(t), Math.sin(t), t, 1};
        }
    }

    public Curve3D(BufferedImage backGroundBuffer) {
        super(WIDTH, HEIGHT, backGroundBuffer);
    }

    public void drawCurve() {
        drawBackGround();
        if (Objects.isNull(matrixMove)){
            incrementAngles(0,0,0);
        }

        int prevX = 0, prevY = 0;
        boolean first = true;

        for (double[] vertex : vertices) {
            int[] projected = multiplyVertex(vertex, matrixMove, true);

            if (!first) {
                drawLine(prevX, prevY, projected[0], projected[1], CURVE_COLOR);
            }

            prevX = projected[0];
            prevY = projected[1];
            first = false;
        }
    }

    public void incrementAngles(double angleX, double angleY, double angleZ) {
        this.angleX += angleX;
        this.angleY += angleY;
        this.angleZ += angleZ;


        matrixMove = multiplyMatrices(
                generateTranslationMatrix((double) getWidth() / 2, (double) getHeight() / 2, 0),
                generateEscalationMatrix(scale, scale, scale),
                generateRotationXMatrix(this.angleX),
                generateRotationYMatrix(this.angleY),
                generateRotationZMatrix(this.angleZ));
    }
}
