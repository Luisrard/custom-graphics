package com.luisrard.custom.graphics.third.partial.models;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/* paraboloide hiperbolico
 */
public class Surface3D extends CustomBufferImage{
    public static final Color CURVE_COLOR = Color.WHITE;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private double escala = 1.0;

    private double a = 50;
    private double b = 50;
    private double c = 15.0;
    private ArrayList<double[]> vertices = new ArrayList<>();
    private double[] puntoCubo = {400, 400, 0};
    private double[] puntoFuga = {400, 400, 1000};

    private double angleX = 0;
    private double angleY = 0;
    private double angleZ = 0;

    public Surface3D(BufferedImage backGroundBuffer) {
        super(WIDTH, HEIGHT, backGroundBuffer);

        for (double u = -3; u <= 3; u += 0.1) { 
            for (double v = -3; v <= 3; v += 0.1) { 
                double[] vertice = new double[3];
                vertice[0] = a * u;
                vertice[1] = b * v;
                vertice[2] = c * (u * u - v * v);
                vertices.add(vertice);
            }
        }
    }

    public void drawSuperficie(){
        drawBackGround();

        double[][] verticesTrasladados = new double[vertices.size()][3];
        for (int i = 0; i < vertices.size(); i++) {
            double[] vertice = vertices.get(i);
            vertice = rotarX(vertice, angleX);
            vertice = rotarY(vertice, angleY);
            vertice = rotarZ(vertice, angleZ);
            verticesTrasladados[i] = vertice;
        }

        for (int i = 0; i < vertices.size(); i++) {
            double[] v = verticesTrasladados[i];
            double[] trasladado = {
                    (v[0] * escala) + puntoCubo[0],
                    (v[1] * escala) + puntoCubo[1],
                    (v[2] * escala) + puntoCubo[2]
            };
            verticesTrasladados[i] = trasladado;
        }

        for (int i = 0; i < vertices.size() - 1; i++) {
            double x0 = verticesTrasladados[i][0];
            double y0 = verticesTrasladados[i][1];
            double z0 = verticesTrasladados[i][2];

            double x1 = verticesTrasladados[i + 1][0];
            double y1 = verticesTrasladados[i + 1][1];
            double z1 = verticesTrasladados[i + 1][2];

            int[] p1 = projectPoint(x0, y0, z0);
            int[] p2 = projectPoint(x1, y1, z1);

            drawLine(p1[0], p1[1], p2[0], p2[1], CURVE_COLOR);
        }
    }

    private int[] projectPoint(double x, double y, double z) {
        double u = -puntoFuga[2] / (z - puntoFuga[2]);

        int px = (int) (puntoFuga[0] + (x - puntoFuga[0]) * u);
        int py = (int) (puntoFuga[1] + (y - puntoFuga[1]) * u);

        return new int[]{px, py};
    }

    private double[] rotarX(double[] point, double angle) {
        double[] result = new double[3];
        result[0] = point[0];
        result[1] = point[1] * Math.cos(Math.toRadians(angle)) - point[2] * Math.sin(Math.toRadians(angle));
        result[2] = point[1] * Math.sin(Math.toRadians(angle)) + point[2] * Math.cos(Math.toRadians(angle));
        return result;
    }

    private double[] rotarY(double[] point, double angle) {
        double[] result = new double[3];
        result[0] = point[0] * Math.cos(Math.toRadians(angle)) + point[2] * Math.sin(Math.toRadians(angle));
        result[1] = point[1];
        result[2] = -point[0] * Math.sin(Math.toRadians(angle)) + point[2] * Math.cos(Math.toRadians(angle));
        return result;
    }

    private double[] rotarZ(double[] point, double angle) {
        double[] result = new double[3];
        result[0] = point[0] * Math.cos(Math.toRadians(angle)) - point[1] * Math.sin(Math.toRadians(angle));
        result[1] = point[0] * Math.sin(Math.toRadians(angle)) + point[1] * Math.cos(Math.toRadians(angle));
        result[2] = point[2];
        return result;
    }

    public void incrementAngles(double angleX, double angleY, double angleZ) {
        this.angleX += angleX;
        this.angleY += angleY;
        this.angleZ += angleZ;
    }
}
