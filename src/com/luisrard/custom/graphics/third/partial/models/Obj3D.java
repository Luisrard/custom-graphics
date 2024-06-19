package com.luisrard.custom.graphics.third.partial.models;

import com.luisrard.custom.graphics.obj.ObjFileConverter;
import com.luisrard.custom.graphics.obj.ObjModel;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Obj3D extends CustomBufferImage {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private ObjModel model;
    private double[][] matrix;
    private int incTranslationX = 5;
    private int incTranslationY = 5;
    private int incTranslationZ = 10;
    private int incEscalationX = 10, incEscalationY = 5;

    private int incRotationX = 5, incRotationY = 5, incRotationZ = 5;
    private int traslationX = 100;
    private int traslationY = 100;
    private int traslationZ = 100;
    private int escalationX = 100;
    private int escalationY = 100;
    private int rotationX = 20;
    private int rotationY = 20;
    private int rotationZ = 0;

    public Obj3D(BufferedImage backGround, String filePath){
        super(WIDTH, HEIGHT, backGround);

        try {
            model = ObjFileConverter.convert(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recalculateMatrix();
        drawObject();
    }


    public void increaseXTranslation(int x){
        traslationX += x;
    }

    public void increaseYTranslation(int y){
        traslationY += y;
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
        recalculateMatrix();
    }

    public void increaseYRotation(int y) {
        rotationY += y;
        recalculateMatrix();
    }

    public void increaseZRotation(int z) {
        rotationZ += z;
        recalculateMatrix();
    }

    public void increaseRotation(int x, int y, int z) {
        rotationX += x;
        rotationY += y;
        rotationZ += z;
        recalculateMatrix();
    }

    public void move(){
        traslationX += incTranslationX;
        traslationY += incTranslationY;
        traslationZ += incTranslationZ;

        escalationX += incEscalationX;
        escalationY += incEscalationY;

        rotationX += incRotationX;
        rotationY += incRotationY;
        rotationZ += incRotationZ;

        recalculateMatrix();

        if (traslationX > getWidth() || traslationX < 0){
            incTranslationX *= -1;
            incRotationX *= -1;
        }
        if (traslationY > getHeight() || traslationY < 0){
            incTranslationY *= -1;
        }
        if (traslationZ > 500 || traslationZ < 0){
            incTranslationZ *= -1;
            incRotationZ *= -1;
        }

        if (escalationX > 200 || escalationX < 0){
            incEscalationX *= -1;
        }

        if (escalationY > 200 || escalationY < 0){
            incRotationY *= -1;
            incEscalationY *= -1;
        }
    }
    private void recalculateMatrix(){
        matrix = multiplyMatrices(generateTranslationMatrix(traslationX, traslationY, traslationZ),
                generateEscalationMatrix(escalationX, escalationY, 10),
                generateRotationXMatrix(rotationX),
                generateRotationYMatrix(rotationY),
                generateRotationZMatrix(rotationZ));
    }

    public void drawObject(){
        drawBackGround();
        setMatrixMove(matrix);
        drawObj(model, true);
    }

}
