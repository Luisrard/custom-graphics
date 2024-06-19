package com.luisrard.custom.graphics.third.partial.models;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Cylinder3D extends CustomBufferImage {
    public static final Color CURVE_COLOR = Color.WHITE;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private static final double SCALE = 50;
    private static final int N_POINTS = 50;

    private double angleX = 0, angleY = 0, angleZ = 0;

    private static final double[] VERTEX_CUBE = {400, 400, 0};
    private static final double[] VERTEX_VANISH_POINT = {400, 400, 1000};

    private static final ArrayList<double[]> vertices = new ArrayList<>();
    static {
        double maxAngle = 2 * Math.PI;
        double incAngle = maxAngle / N_POINTS;

        for (double alpha = 0; alpha < maxAngle; alpha += incAngle) {
            for (double beta = 0; beta < maxAngle; beta += incAngle) {
                double[] vertex = new double[4];
                vertex[0] = (2 + Math.cos(alpha)) * Math.cos(beta);
                vertex[1] = (2 + Math.cos(alpha)) * Math.sin(beta);
                vertex[2] = alpha;
                vertex[3] = 1;
                vertices.add(vertex);
            }
        }
    }

    public Cylinder3D(BufferedImage backGroundBuffer) {
        super(WIDTH, HEIGHT, backGroundBuffer);
    }

    public void drawCylinder() {
        startPrevBuffers();
        double[][] verticesMoved = new double[vertices.size()][4];
        int i = 0;
        for (double [] vertex : vertices){
            verticesMoved[i] = multiplyVertexWithPerspective(vertex, matrixMove);
            i++;
        }

        for (i = 0; i < N_POINTS - 1; i++) {
            for (int j = 0; j < N_POINTS; j++) {
                int index0 = i * N_POINTS + j;
                int index1 = (i + 1) * N_POINTS + j;
                int index2 = i * N_POINTS + (j + 1) % N_POINTS;
                int index3 = (i + 1) * N_POINTS + (j + 1) % N_POINTS;


                double[][] vertices = {verticesMoved[index0], verticesMoved[index1],
                        verticesMoved[index2], verticesMoved[index3]};
                drawVertices(vertices, CURVE_COLOR);
            }
        }
        chargeBuffers();
    }

    public void incrementAngles(double angleX, double angleY, double angleZ) {
        this.angleX += angleX;
        this.angleY += angleY;
        this.angleZ += angleZ;


        matrixMove = multiplyMatrices(
                generateTranslationMatrix(VERTEX_CUBE[0], VERTEX_CUBE[1], VERTEX_CUBE[2]),
                generateEscalationMatrix(SCALE, SCALE, SCALE),
                generateRotationXMatrix(this.angleX),
                generateRotationYMatrix(this.angleY),
                generateRotationZMatrix(this.angleZ));
    }
}
