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
        drawBackGround();
        int[][] verticesMoved = new int[vertices.size()][4];
        int i = 0;
        for (double [] vertex : vertices){
            verticesMoved[i] = multiplyVertex(vertex, matrixMove, false);
            i++;
        }

        for (i = 0; i < N_POINTS - 1; i++) {
            for (int j = 0; j < N_POINTS; j++) {
                int index0 = i * N_POINTS + j;
                int index1 = (i + 1) * N_POINTS + j;
                int index2 = i * N_POINTS + (j + 1) % N_POINTS;
                int index3 = (i + 1) * N_POINTS + (j + 1) % N_POINTS;

                int[] p0 = applyPerspective(verticesMoved[index0]);
                int[] p1 = applyPerspective(verticesMoved[index1]);
                int[] p2 = applyPerspective(verticesMoved[index2]);
                int[] p3 = applyPerspective(verticesMoved[index3]);

                drawLine(p0, p1, CURVE_COLOR);
                drawLine(p0, p2, CURVE_COLOR);
                drawLine(p1, p3, CURVE_COLOR);
                drawLine(p2, p3, CURVE_COLOR);
            }
        }
    }

    private int[] applyPerspective(int [] vertex) {
        double u = -VERTEX_VANISH_POINT[2] / (vertex[2] - VERTEX_VANISH_POINT[2]);

        double px = VERTEX_VANISH_POINT[0] + (vertex[0] - VERTEX_VANISH_POINT[0]) * u;
        double py = VERTEX_VANISH_POINT[1] + (vertex[1] - VERTEX_VANISH_POINT[1]) * u;

        return new int[]{(int) px, (int) py};
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
