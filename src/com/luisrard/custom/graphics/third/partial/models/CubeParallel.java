package com.luisrard.custom.graphics.third.partial.models;

import com.luisrard.custom.graphics.obj.ObjFileConverter;
import com.luisrard.custom.graphics.obj.ObjModel;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class CubeParallel extends CustomBufferImage {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static final double[][][] MATRIX_MOVES = new double[8][][];
    public static final double[][] TRANSLATION_MATRIX = generateTranslationMatrix(WIDTH/2, HEIGHT/2, 50);
    public static final double[][] ROTATION_20_X_MATRIX = generateRotationXMatrix(20);
    public static final double[][] ROTATION_20_Y_MATRIX = generateRotationYMatrix(20);
    public static final double[][] ESCALATION_MATRIX = generateEscalationMatrix(100, 100, 10);


    static {
        double[][] matrix = multiplyMatrices(TRANSLATION_MATRIX, ESCALATION_MATRIX, ROTATION_20_X_MATRIX, ROTATION_20_Y_MATRIX);
        MATRIX_MOVES[0] = matrix;
        MATRIX_MOVES[1] = multiplyMatrices(matrix, generateRotationXMatrix(10));
        MATRIX_MOVES[2] = multiplyMatrices(matrix, generateRotationXMatrix(45));
        MATRIX_MOVES[3] = multiplyMatrices(matrix, generateRotationXMatrix(20), generateRotationYMatrix(10));
        MATRIX_MOVES[4] = multiplyMatrices(matrix, generateRotationXMatrix(20), ROTATION_20_Y_MATRIX);
        MATRIX_MOVES[5] = multiplyMatrices(matrix, generateRotationXMatrix(10), ROTATION_20_Y_MATRIX);
        MATRIX_MOVES[6] = multiplyMatrices(matrix, ROTATION_20_Y_MATRIX);
        MATRIX_MOVES[7] = multiplyMatrices(matrix, generateRotationYMatrix(10));
    }
    private int index = 0;
    private ObjModel model;
    private double[][] matrix;
    private int escalationX = 100;
    private int escalationY = 100;
    private int rotationX = 20;
    private int rotationY = 20;
    private int rotationZ = 0;

    public CubeParallel(BufferedImage backGround){
        super(WIDTH, HEIGHT, backGround);
        matrix = MATRIX_MOVES[0];

        try {
            model = ObjFileConverter.convert("src/img/cube.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
        moveObject();
    }


    public void moveObject(){
        drawBackGround();
        if (model != null){
            setMatrixMove(MATRIX_MOVES[index]);
            drawObj(model, false);
        }
        index ++;
        if (index >= MATRIX_MOVES.length){
            index = 0;
        }
    }

    public void increaseXTranslation(int x){
        matrix[0][3] += x;
    }

    public void increaseYTranslation(int y){
        matrix[1][3] += y;
    }

    public void increaseXEscalation(int x){
        escalationX += x;
        recalculateMatrix();
    }

    public void increaseYEscalation(int y){
        escalationY += y;
        recalculateMatrix();
    }

    public void increaseXRotation(int x) {
        rotationX += x;
        recalculateMatrixRotation();
    }

    public void increaseYRotation(int y) {
        rotationY += y;
        recalculateMatrixRotation();
    }

    public void increaseZRotation(int z) {
        rotationZ += z;
        recalculateMatrixRotation();
    }

    public void increaseRotation(int x, int y, int z) {
        rotationX += x;
        rotationY += y;
        rotationZ += z;
        recalculateMatrixRotation();
    }

    private void recalculateMatrix(){
        matrix = multiplyMatrices(TRANSLATION_MATRIX,
                generateEscalationMatrix(escalationX, escalationY, 10),
                ROTATION_20_X_MATRIX,
                ROTATION_20_Y_MATRIX);
    }

    private void recalculateMatrixRotation(){
        matrix = multiplyMatrices(TRANSLATION_MATRIX,
                ESCALATION_MATRIX,
                generateRotationXMatrix(rotationX),
                generateRotationYMatrix(rotationY),
                generateRotationZMatrix(rotationZ));
    }

    public void drawObject(){
        drawBackGround();
        setMatrixMove(matrix);
        drawObj(model, false);
    }

}
