package com.luisrard.custom.graphics.third.partial.models;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Cylinder3D extends CustomBufferImage {
    public static final Color CURVE_COLOR = Color.WHITE;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private double scale = 50;

    private double angleX = 0, angleY = 0, angleZ = 0;
    private int numPuntos = 50;

    private double[] puntoCubo = {400, 400, 0};
    private double[] puntoFuga = {400, 400, 1000};

    private static final ArrayList<double[]> vertices = new ArrayList<>();
    static {
        double anguloMaximo = 2 * Math.PI;
        int numPuntos = 50;
        double anguloIncremento = anguloMaximo / numPuntos;

        for (double alpha = 0; alpha < anguloMaximo; alpha += anguloIncremento) {
            for (double beta = 0; beta < anguloMaximo; beta += anguloIncremento) {
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
        int[][] verticesTrasladados = new int[vertices.size()][4];
        int i = 0;
        for (double [] vertex : vertices){
            verticesTrasladados[i] = multiplyVertex(vertex, matrixMove, false);
            i++;
        }

        for (i = 0; i < numPuntos - 1; i++) {
            for (int j = 0; j < numPuntos; j++) {
                int index0 = i * numPuntos + j;
                int index1 = (i + 1) * numPuntos + j;
                int index2 = i * numPuntos + (j + 1) % numPuntos;
                int index3 = (i + 1) * numPuntos + (j + 1) % numPuntos;

                Point2D p0 = applyPerspective(verticesTrasladados[index0]);
                Point2D p1 = applyPerspective(verticesTrasladados[index1]);
                Point2D p2 = applyPerspective(verticesTrasladados[index2]);
                Point2D p3 = applyPerspective(verticesTrasladados[index3]);

                drawLine((int) p0.getX(), (int) p0.getY(), (int) p1.getX(), (int) p1.getY(), CURVE_COLOR);
                drawLine((int) p0.getX(), (int) p0.getY(), (int) p2.getX(), (int) p2.getY(), CURVE_COLOR);
                drawLine((int) p1.getX(), (int) p1.getY(), (int) p3.getX(), (int) p3.getY(), CURVE_COLOR);
                drawLine((int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY(), CURVE_COLOR);
            }
        }
    }

    private Point2D applyPerspective(int [] vertex) {
        double u = -puntoFuga[2] / (vertex[2] - puntoFuga[2]);

        double px = puntoFuga[0] + (vertex[0] - puntoFuga[0]) * u;
        double py = puntoFuga[1] + (vertex[1] - puntoFuga[1]) * u;

        return new Point2D.Double(px, py);
    }

    public void incrementAngles(double angleX, double angleY, double angleZ) {
        this.angleX += angleX;
        this.angleY += angleY;
        this.angleZ += angleZ;


        matrixMove = multiplyMatrices(
                generateTranslationMatrix(puntoCubo[0], puntoCubo[1], puntoCubo[2]),
                generateEscalationMatrix(scale, scale, scale),
                generateRotationXMatrix(this.angleX),
                generateRotationYMatrix(this.angleY),
                generateRotationZMatrix(this.angleZ));
    }
}
