package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.CustomBufferImage;
import com.luisrard.custom.graphics.third.partial.models.Cylinder3D;
import com.luisrard.custom.graphics.third.partial.models.Obj3D;
import com.luisrard.custom.graphics.third.partial.models.Sphere3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.function.Consumer;

public class Project3DPanel extends MainPanel {
    private static final int DELAY_FRAME_MS = 50;
    private static final int DELAY_REFRESH_MS = 10;
    private final Consumer<Character> characterConsumer;

    private CustomBufferImage [] models;

    private boolean sphereStop, cylinderStop, cubeStop, oculosStop;

    private Sphere3D sphere3D;

    private Cylinder3D cylinder3D;

    private Obj3D cube3D;

    private Obj3D oculos3D;

    private BufferedImage animationBuffer;

    private CustomBufferImage background;

    private boolean refreshView = false;


    public Project3DPanel() {
        super();
        setDoubleBuffered(true);
        background = new CustomBufferImage(getWidth(), getHeight(), buffer, BufferedImage.TYPE_INT_RGB);
        int[][] vertices = new int[][]{{500, 0, 1}, {1000, 800, 1}, {0, 800, 1}};
        background.startPrevBuffers();
        background.drawVertices(vertices, new Color(130, 28, 182, 255));

        characterConsumer = character -> {
            boolean print = true;
            switch (character) {
                case '1':
                    sphereStop = !sphereStop;
                    break;
                case '2':
                    cubeStop = !cubeStop;
                    break;
                case '3':
                    cylinderStop = !cylinderStop;
                    break;
                case '4':
                    oculosStop = !oculosStop;
                    break;
                default:
                    break;
            }
        };
    }

    public void doMove(){
        setDoubleBuffered(true);
        cylinder3D = new Cylinder3D(buffer);
        sphere3D = new Sphere3D(buffer);
        cube3D = new Obj3D(buffer, "src/img/cube.obj");
        oculos3D = new Obj3D(buffer, "src/img/oculos.obj");
        oculos3D.increaseXTranslation(600);

        models = new CustomBufferImage[] {cylinder3D, sphere3D, cube3D, oculos3D};

        startThreadsAnimations();
    }

    private void startThreadsAnimations() {
        new Thread(() -> {
            while (true) {
                if (!cylinderStop) {
                    cylinder3D.incrementAngles(0.5, 0.5, 0);
                    cylinder3D.drawCylinder();
                    refreshView = true;
                }
                try {
                    Thread.sleep(DELAY_FRAME_MS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (!sphereStop){
                    sphere3D.move();
                    sphere3D.drawSphere();
                    refreshView = true;
                }
                try {
                    Thread.sleep(DELAY_FRAME_MS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (!cubeStop) {
                    cube3D.move();
                    cube3D.drawObject();
                    refreshView = true;
                }
                try {
                    Thread.sleep(DELAY_FRAME_MS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (!oculosStop) {
                    oculos3D.move();
                    oculos3D.drawObject();
                    refreshView = true;
                }
                try {
                    Thread.sleep(DELAY_FRAME_MS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (refreshView){
                    updateAnimationBuffer();
                    paint(getGraphics());
                    refreshView = false;
                } else{
                    try {
                        Thread.sleep(DELAY_REFRESH_MS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    private void updateAnimationBuffer() {
        if (Objects.nonNull(models)){
            for (CustomBufferImage model : models) {
                if (Objects.isNull(model.getZBuffer())) {
                    return;
                }
            }
            BufferedImage animationBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

            double [][][] zBuffers = new double[models.length][getWidth()][getHeight()];
            Color [][][] colors = new Color[models.length][getWidth()][getHeight()];

            for (int i = 0; i < models.length; i ++){
                zBuffers[i] = models[i].getZBuffer();
                colors[i] = models[i].getColorBuffer();
            }


            for (int x = 0; x < getWidth(); x ++){
                for (int y = 0; y < getHeight(); y ++){
                    int min = (int) zBuffers[0][x][y];
                    Color color = colors[0][x][y];
                    for (int i = 0; i < zBuffers.length; i ++){
                        int piv = (int) zBuffers[i][x][y];
                        if (piv < min){
                            min = piv;
                            color = colors[i][x][y];
                        }
                    }
                    if (Objects.nonNull(color)) {
                        animationBuffer.setRGB(x, y, color.getRGB());
                    }
                }
            }

            this.animationBuffer = animationBuffer;
        }
    }

    public Consumer<Character> getCharacterConsumer() {
        return this.characterConsumer;
    }

    @Override
    public void paint(Graphics g) {
        if (Objects.nonNull(g) && Objects.nonNull(background) && Objects.nonNull(animationBuffer)){
            g.drawImage(animationBuffer, 0, 0, null);
        }
    }
}
