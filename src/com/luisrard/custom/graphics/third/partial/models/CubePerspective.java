package com.luisrard.custom.graphics.third.partial.models;

import com.luisrard.custom.graphics.obj.ObjFileConverter;
import com.luisrard.custom.graphics.obj.ObjModel;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class CubePerspective extends CustomBufferImage {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static final double[][][] matrixMoves = new double[8][][];
    static {
        double[][] translationMatrix = generateTranslationMatrix(WIDTH/2, HEIGHT/2, 5);
        double[][] escalationMatrix = generateEscalationMatrix(200, 200, 100);
        double[][] matrix = multiplyMatrices(translationMatrix, escalationMatrix);
        matrixMoves[0] = matrix;
        matrixMoves[1] = multiplyMatrices(matrix, generateRotationXMatrix(10));
        matrixMoves[2] = multiplyMatrices(matrix);
        matrixMoves[3] = multiplyMatrices(matrix, generateRotationXMatrix(20), generateRotationYMatrix(10));
        matrixMoves[4] = multiplyMatrices(matrix, generateRotationXMatrix(20), generateRotationYMatrix(20));
        matrixMoves[5] = multiplyMatrices(matrix, generateRotationXMatrix(10), generateRotationYMatrix(20));
        matrixMoves[6] = multiplyMatrices(matrix, generateRotationYMatrix(20));
        matrixMoves[7] = multiplyMatrices(matrix, generateRotationYMatrix(10));
    }
    private int index = 0;
    private ObjModel model;

    public CubePerspective(BufferedImage backGroundBuffer){
        super(WIDTH, HEIGHT, backGroundBuffer);

        try {
            model = ObjFileConverter.convert("src/img/cube.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
        moveObject();
    }


    public void moveObject(){
        drawBackGround();
        doDraw();
        index ++;
        if (index >= matrixMoves.length){
            index = 0;
        }
    }

    public void doDraw(){
        if (model != null){
            setMatrixMove(matrixMoves[index]);
            drawObj(model, true);
        }
    }
}
