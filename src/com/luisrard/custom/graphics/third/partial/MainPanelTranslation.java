package com.luisrard.custom.graphics.third.partial;

import java.awt.*;
import java.util.function.Consumer;

public class MainPanelTranslation extends MainPanel {
    private CubeParallel cube;
    private Consumer<Character>  characterConsumer;

    private static final int MOVE_IN_Y = 5, MOVE_IN_X = 5;
    public MainPanelTranslation(){
        super();
        characterConsumer = character -> {
            boolean print = true;
            switch (character){
                case 'w':
                case 'W':
                    cube.increaseYTranslation(-MOVE_IN_Y);
                    break;
                case 's':
                case 'S':
                    cube.increaseYTranslation(MOVE_IN_Y);
                    break;
                case 'a':
                case 'A':
                    cube.increaseXTranslation(-MOVE_IN_X);
                    break;
                case 'd':
                case 'D':
                    cube.increaseXTranslation(MOVE_IN_X);
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
