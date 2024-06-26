package com.luisrard.custom.graphics.third.partial.models;

import com.luisrard.custom.graphics.obj.Face;
import com.luisrard.custom.graphics.obj.ObjModel;
import com.luisrard.custom.graphics.obj.TextureCoordinate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;


public class CustomBufferImage extends BufferedImage {
    private static final int DISTANCE = 1000; // Observer distance
    protected final int width;
    protected final int height;
    private static final double[] VERTEX_VANISH_POINT = {400, 400, 1000};
    protected double[][] matrixMove;

    protected double[][] zBuffer;
    protected double[][] zBufferPrev;
    protected Color[][] colorBuffer;
    protected Color[][] colorBufferPrev;
    protected final BufferedImage backGroundBuffer;


    public CustomBufferImage(int width, int height, BufferedImage backGroundBuffer) {
        this(width, height, backGroundBuffer, BufferedImage.TYPE_INT_ARGB);
    }

    public CustomBufferImage(int width, int height, BufferedImage backGroundBuffer, int type) {
        super(width, height, type);

        this.width = width;
        this.height = height;
        this.backGroundBuffer = backGroundBuffer;
    }

    public void startPrevBuffers(){
        zBufferPrev = new double[width][height];
        for (int x = 0; x < getWidth(); x ++) {
            for (int y = 0; y < getHeight(); y++) {
                zBufferPrev[x][y] = Integer.MAX_VALUE;
            }
        }
        colorBufferPrev = new Color[width][height];
    }

    protected void chargeBuffers(){
        zBuffer = zBufferPrev;
        colorBuffer = colorBufferPrev;
    }

    public Color[][] getColorBuffer() {
        return colorBuffer;
    }

    public double[][] getZBuffer() {
        return zBuffer;
    }

    public void setMatrixMove(double[][] matrixMove){
        this.matrixMove = matrixMove;
    }

    public void drawAxisObj(ObjModel objModel, boolean perspective){
        startPrevBuffers();

        HashMap<Integer, int[]> vertexMap = new HashMap<>(objModel.getVertices().size());
        for (Face face : objModel.getFaces()) {
            List<Integer> vertexIndices = face.getVertexIndices();
            int n = vertexIndices.size();
            int[][] vertices = new int[n][4];
            int[] xPoints = new int[n];
            int[] yPoints = new int[n];
            int[] rgb = new int[n];


            for (int i = 0; i < vertexIndices.size(); i ++) {
                int index = vertexIndices.get(i);

                if (!vertexMap.containsKey(index)){
                    float[] array = objModel.getVertices().get(index).toArray();
                    vertexMap.put(index, multiplyVertex(array, matrixMove, perspective));
                }

                vertices[i] = vertexMap.get(index);
                xPoints[i] = vertices[i][0];
                yPoints[i] = vertices[i][1];

                if (!face.getTextureCoordinateIndices().isEmpty()) {
                    int texCoordIndex = face.getTextureCoordinateIndices().get(i);
                    TextureCoordinate texCoord = objModel.getTextureCoordinates().get(texCoordIndex);
                    rgb [i] = (int) (texCoord.getU() * texCoord.getV() * 255) % 255;
                }
            }
            drawAxis(vertices, new Color(rgb[0], rgb[1], rgb[2]));
        }
    }

    private void drawAxis(int[][] vertices, Color color) {
        if (vertices.length == 3){
            drawLine(vertices[0], vertices[1], color);
            drawLine(vertices[1], vertices[2], color);
            drawLine(vertices[2], vertices[0], color);
        } else {
            drawLine(vertices[0], vertices[1], color);
            drawLine(vertices[1], vertices[2], color);
            drawLine(vertices[2], vertices[3], color);
            drawLine(vertices[3], vertices[0], color);
        }
    }

    public void drawObj(ObjModel objModel, boolean perspective){
        startPrevBuffers();

        HashMap<Integer, int[]> vertexMap = new HashMap<>(objModel.getVertices().size());
        for (Face face : objModel.getFaces()) {
            List<Integer> vertexIndices = face.getVertexIndices();
            int n = vertexIndices.size();
            int[][] vertices = new int[n][4];
            int[] xPoints = new int[n];
            int[] yPoints = new int[n];
            int[] rgb = new int[n];


            for (int i = 0; i < vertexIndices.size(); i ++) {
                int index = vertexIndices.get(i);

                if (!vertexMap.containsKey(index)){
                    float[] array = objModel.getVertices().get(index).toArray();
                    vertexMap.put(index, multiplyVertex(array, matrixMove, perspective));
                }

                vertices[i] = vertexMap.get(index);
                xPoints[i] = vertices[i][0];
                yPoints[i] = vertices[i][1];

                if (!face.getTextureCoordinateIndices().isEmpty()) {
                    int texCoordIndex = face.getTextureCoordinateIndices().get(i);
                    TextureCoordinate texCoord = objModel.getTextureCoordinates().get(texCoordIndex);
                    rgb [i] = (int) (texCoord.getU() * texCoord.getV() * 255) % 255;
                }
            }
            drawVertices(vertices, new Color(rgb[0], rgb[1], rgb[2]));
//            if (textureImage != null && !face.getTextureCoordinateIndices().isEmpty()) {
//                getGraphics().drawImage(textureImage, xPoints[0], yPoints[0], xPoints[1], yPoints[1], uPoints[0], vPoints[0], uPoints[1], vPoints[1], null);
//            }
        }

        chargeBuffers();
    }

    public void drawVertices(int[][] vertices, Color color) {
        if (vertices.length == 3){
            draw3Vertices(vertices, color);
        } else {
            int[][] vertices1 = new int[3][4];
            System.arraycopy(vertices, 0, vertices1, 0, 3);
            draw3Vertices(vertices1, color);
            System.arraycopy(vertices, 1, vertices1, 0, 3);
            draw3Vertices(vertices1, color);
        }
    }

    protected void drawVertices(double[][] vertices, Color color) {
        if (vertices.length == 3){
            draw3Vertices(vertices, color);
        } else {
            double[][] vertices1 = new double[3][4];
            System.arraycopy(vertices, 0, vertices1, 0, 3);
            draw3Vertices(vertices1, color);
            System.arraycopy(vertices, 1, vertices1, 0, 3);
            draw3Vertices(vertices1, color);
        }
    }

    private void draw3Vertices(double[][] vertices, Color c) {
        int x0 = (int) vertices[0][0];
        int x1 = (int) vertices[1][0];
        int x2 = (int) vertices[2][0];
        int y0 = (int) vertices[0][1];
        int y1 = (int) vertices[1][1];
        int y2 = (int) vertices[2][1];
        double z0 = vertices[0][2];
        double z1 = vertices[1][2];
        double z2 = vertices[2][2];


        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int dz = (int) Math.abs(z1 - z0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;
        double incZ = z0 < z1 ? 1 : -1;
        int err = dx - dy;
        int x = x0;
        int y = y0;
        double z = z0;

        int totalIterations = Math.max(dx,dy);
        incZ *= (totalIterations > 1 ? (double) dz / (totalIterations) : dz);
        while (true) {
            drawLine(x2, y2, z2, x, y, z, c);

            if (x == x1 && y == y1) {
                break;
            }

            int e2 = 2 * err;
            z += incZ;
            if (e2 > -dy) {
                err -= dy;
                x += incX;
            }
            if (e2 < dx) {
                err += dx;
                y += incY;
            }
        }
    }


    private void draw3Vertices(int[][] vertices, Color c) {
        int x0 = vertices[0][0];
        int x1 = vertices[1][0];
        int x2 = vertices[2][0];
        int y0 = vertices[0][1];
        int y1 = vertices[1][1];
        int y2 = vertices[2][1];
        int z0 = vertices[0][2];
        int z1 = vertices[1][2];
        int z2 = vertices[2][2];


        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int dz = Math.abs(z1 - z0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;
        double incZ = z0 < z1 ? 1 : -1;
        int err = dx - dy;
        int x = x0;
        int y = y0;
        double z = z0;

        int totalIterations = Math.max(dx,dy);
        incZ *= (totalIterations > 1 ? (double) dz / (totalIterations) : dz);
        while (true) {
            drawLine(x2, y2, z2, x, y, z, c);

            if (x == x1 && y == y1) {
                break;
            }

            int e2 = 2 * err;
            z += incZ;
            if (e2 > -dy) {
                err -= dy;
                x += incX;
            }
            if (e2 < dx) {
                err += dx;
                y += incY;
            }
        }
    }

    protected void drawLine(int x0, int y0, double z0, int x1, int y1, double z1, Color c) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        double dz = Math.abs(z1 - z0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;
        double incZ = z0 < z1 ? 1 : -1;
        int err = dx - dy;
        int x = x0;
        int y = y0;
        double z = z0;

        int totalIterations = Math.max(dx,dy);

        incZ *= (totalIterations > 1 ? dz / (totalIterations) : dz);

        while (true) {
            putPixel(x, y, z, c);

            if (x == x1 && y == y1) {
                break;
            }

            int e2 = 2 * err;
            z += incZ;
            if (e2 > -dy) {
                err -= dy;
                x += incX;
            }
            if (e2 < dx) {
                err += dx;
                y += incY;
            }
        }
    }

    public void putPixel(int x, int y, double z, Color c) {
        if (x < 0 || x > width - 1 || y < 0 || y > height - 1){
            return;
        }
        if (z < zBufferPrev[x][y]){
            zBufferPrev[x][y] = z;
            colorBufferPrev[x][y] = c;
            this.setRGB(x, y, c.getRGB());
        }
    }

    public void putPixel(int x, int y, Color c) {
        if (x < 0 || x > width - 1 || y < 0 || y > height - 1){
            return;
        }
        this.setRGB(x, y, c.getRGB());
    }

    protected void drawLine(double[] vertex0, double[] vertex1, Color c) {
        drawLine((int) vertex0[0], (int) vertex0[1], vertex0[2] , (int) vertex1[0], (int) vertex1[1], vertex1[2] ,c);
    }


    protected void drawLine(int[] vertex0, int[] vertex1, Color c) {
        drawLine(vertex0[0], vertex0[1], vertex1[0], vertex1[1], c);
    }

    protected void drawLine(int x0, int y0, int x1, int y1, Color c) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int incX = x0 < x1 ? 1 : -1;
        int incY = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int x = x0;
        int y = y0;
        while (true) {
            putPixel(x, y, c);

            if (x == x1 && y == y1) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += incX;
            }
            if (e2 < dx) {
                err += dx;
                y += incY;
            }
        }
    }

    public double [] multiplyVertexWithPerspective(double[] vertex, double [][] matrix){
        if (vertex == null || matrix == null){
            return null;
        }
        int length = matrix.length;
        double [] appliedOperationsVertex = new double[vertex.length];
        for (int colum = 0; colum < length; colum++) {
            double sum = 0;
            for (int row = 0; row < length; row++){
                sum += vertex[row] * matrix[colum][row];
            }
            appliedOperationsVertex[colum] =  sum;
        }

        return applyPerspective(appliedOperationsVertex);
    }

    public static int [] multiplyVertex(double[] vertex, double [][] matrix, boolean perspective){
        if (vertex == null || matrix == null){
            return null;
        }
        int length = matrix.length;
        double [] appliedOperationsVertex = new double[vertex.length];
        for (int colum = 0; colum < length; colum++) {
            double sum = 0;
            for (int row = 0; row < length; row++){
                sum += vertex[row] * matrix[colum][row];
            }
            appliedOperationsVertex[colum] =  sum;
        }

        int [] newVertex = new int[vertex.length];
        double scalePerspective = DISTANCE / (appliedOperationsVertex[3] + DISTANCE);
        for (int i = 0; i < 4; i++) {
            if (perspective){
                newVertex[i] = (int) (appliedOperationsVertex[i] * scalePerspective);
            } else {
                newVertex[i] = (int) appliedOperationsVertex[i];
            }
        }
        return newVertex;
    }


    public static int [] multiplyVertex(float[] vertex, double [][] matrix, boolean perspective){
        if (vertex == null || matrix == null){
            return null;
        }
        int length = matrix.length;
        double [] appliedOperationsVertex = new double[vertex.length];
        for (int colum = 0; colum < length; colum++) {
            double sum = 0;
            for (int row = 0; row < length; row++){
                sum += vertex[row] * matrix[colum][row];
            }
            appliedOperationsVertex[colum] =  sum;
        }

        int [] newVertex = new int[vertex.length];
        double scalePerspective = DISTANCE / (appliedOperationsVertex[3] + DISTANCE);
        for (int i = 0; i < 4; i++) {
            if (perspective){
                newVertex[i] = (int) (appliedOperationsVertex[i] * scalePerspective);
            } else {
                newVertex[i] = (int) appliedOperationsVertex[i];
            }
        }
        return newVertex;
    }

    public static double [][] multiplyMatrices(double [][] ... matrix) {
        if (matrix.length == 0){
            return null;
        } else if (matrix.length == 1){
            for (double [][]  _matrix : matrix){
                return _matrix;
            }
        } else{
            double [][]  matrixResult = null;
            for (double [][]  _matrix : matrix){
                if (matrixResult == null){
                    matrixResult = _matrix;
                }else {
                    matrixResult = multiply2Matrices(matrixResult, _matrix);
                }
            }
            return  matrixResult;
        }
        return null;
    }
    public static double [][] multiply2Matrices(double[][] matrix1, double[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        if (cols1 != rows2) {
            throw new IllegalArgumentException("Number of columns in matrix1 must be equal to number of rows in matrix2.");
        }

        double[][] result = new double[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    public static double [][] addMatrices(double [][] ... matrix) {
        if (matrix.length == 0){
            return null;
        } else if (matrix.length == 1){
            for (double [][]  _matrix : matrix){
                return _matrix;
            }
        } else{
            double [][]  matrixResult = null;
            for (double [][]  _matrix : matrix){
                if (matrixResult == null){
                    matrixResult = _matrix;
                }else {
                    matrixResult = add2Matrices(matrixResult, _matrix);
                }
            }
            return  matrixResult;
        }
        return null;
    }

    public static double[][] add2Matrices(double[][] matrix1, double[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        if (rows1 != rows2 || cols1 != cols2) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }

        double[][] result = new double[rows1][cols1];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return result;
    }

    public static double [][] generateRotationXMatrix(double angle){
        double radians = Math.toRadians(angle);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        return new double[][]{
                {1, 0, 0, 0},
                {0, cos, sin, 0},
                {0, -sin, cos, 0},
                {0, 0, 0, 1},
        };
    }

    public static double [][] generateRotationYMatrix(double angle){
        double radians = Math.toRadians(angle);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        return new double[][]{
                {cos, 0, sin, 0},
                {0, 1, 0, 0},
                {-sin, 0, cos, 0},
                {0, 0, 0, 1},
        };
    }

    public static double [][] generateRotationZMatrix(double angle){
        double radians = Math.toRadians(angle);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        return new double[][]{
                {cos, -sin, 0, 0},
                {sin, cos, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
        };
    }

    public static double [][] generateTranslationMatrix(double dx, double dy, double dz){
        return new double[][]{
                {1, 0, 0, dx},
                {0, 1, 0, dy},
                {0, 0, 1, dz},
                {0, 0, 0, 1}
        };
    }

    public static double [][] generateEscalationMatrix(double sx, double sy, double sz){
        return new double[][]{
                {sx, 0, 0, 0},
                {0, sy, 0, 0},
                {0, 0, sz, 0},
                {0, 0, 0, 1}
        };
    }

    public static double[][] generatePerspectiveProjectionMatrix(double fov, double aspectRatio, double near, double far) {
        double f = 1.0 / Math.tan(Math.toRadians(fov) / 2.0);
        return new double[][]{
                {f / aspectRatio, 0, 0, 0},
                {0, f, 0, 0},
                {0, 0, (far + near) / (near - far), (2 * far * near) / (near - far)},
                {0, 0, -1, 0}
        };
    }

    protected double[] applyPerspective(double[] vertex) {
        double u = -VERTEX_VANISH_POINT[2] / (vertex[2] - VERTEX_VANISH_POINT[2]);

        double px = VERTEX_VANISH_POINT[0] + (vertex[0] - VERTEX_VANISH_POINT[0]) * u;
        double py = VERTEX_VANISH_POINT[1] + (vertex[1] - VERTEX_VANISH_POINT[1]) * u;
        double pz = vertex[2] * u;

        return new double[]{px,  py, pz};
    }

    protected void drawBackGround(){
        getGraphics().drawImage(backGroundBuffer, 0, 0, null);
    }
}