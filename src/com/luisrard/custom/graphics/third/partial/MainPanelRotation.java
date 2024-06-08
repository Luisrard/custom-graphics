package com.luisrard.custom.graphics.third.partial;

import java.awt.*;
import java.util.function.Consumer;

public class MainPanelRotation extends MainPanel {
    private CubeParallel cube;
    private Consumer<Character>  characterConsumer;

    private static final int MOVE_IN_Y = 5, MOVE_IN_X = 5, MOVE_IN_Z = 5;
    public MainPanelRotation(){
        super();
        characterConsumer = character -> {
            boolean print = true;
            switch (character){
                case 'w':
                case 'W':
                    cube.increaseXRotation(-MOVE_IN_X);
                    break;
                case 's':
                case 'S':
                    cube.increaseXRotation(MOVE_IN_X);
                    break;
                case 'a':
                case 'A':
                    cube.increaseYRotation(-MOVE_IN_Y);
                    break;
                case 'd':
                case 'D':
                    cube.increaseYRotation(MOVE_IN_Y);
                    break;
                case 'q':
                case 'Q':
                    cube.increaseZRotation(MOVE_IN_Z);
                    break;
                case 'e':
                case 'E':
                    cube.increaseZRotation(MOVE_IN_Z);
                    break;
                default:
                    print = false;
            }
            if (print){
                cube.drawObject();
                paint(getGraphics());
            }
        };
    }

    @Override
    public void doMove() {
        cube = new CubeParallel(buffer);
        cube.drawObject();
    }

    public Consumer<Character> getCharacterConsumer() {
        return this.characterConsumer;
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(cube, 0, 0, null);
    }
}
