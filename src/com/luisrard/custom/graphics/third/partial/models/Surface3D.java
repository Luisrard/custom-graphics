package com.luisrard.custom.graphics.third.partial.models;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

/* paraboloide hiperbolico
 */
public class Surface3D extends CustomBufferImage{
    public static final Color CURVE_COLOR = Color.WHITE;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private static final double scale = 1.0;
    private static final double[] puntoFuga = {400, 400, 1000};
    private static final ArrayList<double[]> vertices = new ArrayList<>();
    static {
        double a = 50;
        double b = 50;
        double c = 15.0;
        for (double u = -3; u <= 3; u += 0.1) {
            for (double v = -3; v <= 3; v += 0.1) {
                double[] vertice = new double[4];
                vertice[0] = a * u;
                vertice[1] = b * v;
                vertice[2] = c * (u * u - v * v);
                vertice[3] = 1;
                vertices.add(vertice);
            }
        }
    }

    private double angleX = 0;
    private double angleY = 0;
    private double angleZ = 0;

    public Surface3D(BufferedImage backGroundBuffer) {
        super(WIDTH, HEIGHT, backGroundBuffer);

        
    }

    public void drawSurface(){
        drawBackGround();
        
        int [] previousVertex = null;

        for (double[] vertex : vertices) {
            int[] vertexApplied = multiplyVertex(vertex, matrixMove, false);

            vertexApplied = projectPoint(vertexApplied);

            if (Objects.isNull(previousVertex)){
                previousVertex = vertexApplied;
            } else {
                drawLine(previousVertex[0], previousVertex[1], vertexApplied[0], vertexApplied[1], CURVE_COLOR);
                previousVertex = vertexApplied;
            }
        }
    }

    private int[] projectPoint(int[] vertex) {
        double u = -puntoFuga[2] / (vertex[2] - puntoFuga[2]);

        int px = (int) (puntoFuga[0] + (vertex[0] - puntoFuga[0]) * u);
        int py = (int) (puntoFuga[1] + (vertex[1] - puntoFuga[1]) * u);

        return new int[]{px, py};
    }

    public void incrementAngles(double angleX, double angleY, double angleZ) {
        this.angleX += angleX;
        this.angleY += angleY;
        this.angleZ += angleZ;



        matrixMove = multiplyMatrices(
                generateTranslationMatrix(400, 400, 0),
                generateEscalationMatrix(scale, scale, scale),
                generateRotationXMatrix(this.angleX),
                generateRotationYMatrix(this.angleY),
                generateRotationZMatrix(this.angleZ));
    }
}
