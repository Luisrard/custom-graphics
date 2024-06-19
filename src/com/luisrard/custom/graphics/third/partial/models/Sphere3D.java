package com.luisrard.custom.graphics.third.partial.models;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sphere3D extends CustomBufferImage {
    public final Color sphereColor;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private static final double SCALE = 100;
    private static final int N_POINTS = 50;

    private double x = 100, y = 20, z= 10;
    private double incX = 5, incY = 5, incZ = 5;

    private static final double[] VERTEX_CUBE = {400, 400, 0};

    private static final ArrayList<double[]> vertices = new ArrayList<>();
    static {
        double maxAngle = 2 * Math.PI;
        double incAngle = maxAngle / N_POINTS;

        for (double phi = 0; phi < maxAngle; phi += incAngle) {
            for (double theta = 0; theta < maxAngle; theta += incAngle) {
                double[] vertex = new double[4];
                vertex[0] = Math.sin(theta) * Math.cos(phi);
                vertex[1] = Math.sin(theta) * Math.sin(phi);
                vertex[2] = Math.cos(theta);
                vertex[3] = 1;
                vertices.add(vertex);
            }
        }
    }

    public Sphere3D(BufferedImage backGroundBuffer, Color c) {
        super(WIDTH, HEIGHT, backGroundBuffer);
        sphereColor = c;
    }

    public void drawSphere() {
        startPrevBuffers();

        double[][] verticesMoved = new double[vertices.size()][4];
        int i = 0;
        for (double[] vertex : vertices) {
            verticesMoved[i] = multiplyVertexWithPerspective(vertex, matrixMove);
            i++;
        }

        for (i = 0; i < N_POINTS - 1; i++) {
            for (int j = 0; j < N_POINTS; j++) {
                int index0 = i * N_POINTS + j;
                int index1 = (i + 1) * N_POINTS + j;
                int index2 = i * N_POINTS + (j + 1) % N_POINTS;
                int index3 = (i + 1) * N_POINTS + (j + 1) % N_POINTS;



                double[] p0 = verticesMoved[index0];
                double[] p1 = verticesMoved[index1];
                double[] p2 = verticesMoved[index2];
                double[] p3 = verticesMoved[index3];

                drawLine(p0, p1, sphereColor);
                drawLine(p0, p2, sphereColor);
                drawLine(p1, p3, sphereColor);
                drawLine(p2, p3, sphereColor);
            }
        }

        chargeBuffers();
    }


    public void incValues(int x, int y, int z){
        this.x += x;
        this.y += y;
        this.z += z;
    }
    public void move(){
        x += incX;
        y += incY;
        z += incZ;
        if (x > getWidth() || x < 0){
            incX *= -1;
        }
        if (y > getHeight() || y < 0){
            incY *= -1;
        }
        if (z > 500 || z < 0){
            incZ *= -1;
        }

        matrixMove = multiplyMatrices(
                generateTranslationMatrix(x, y, z),
                generateEscalationMatrix(SCALE, SCALE, SCALE),
                generateRotationXMatrix(x));
    }

}
