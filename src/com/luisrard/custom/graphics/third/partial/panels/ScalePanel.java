package com.luisrard.custom.graphics.third.partial.panels;

import com.luisrard.custom.graphics.third.partial.models.CubeParallel;

import java.util.function.Consumer;

public class ScalePanel extends MainPanel {
    private CubeParallel cube;
    private final Consumer<Character>  characterConsumer;

    private static final int MOVE_IN_Y = 5, MOVE_IN_X = 5;
    public ScalePanel(){
        super();
        characterConsumer = character -> {
            boolean print = true;
            switch (character){
                case 'w':
                case 'W':
                    cube.increaseYEscalation(MOVE_IN_Y);
                    break;
                case 's':
                case 'S':
                    cube.increaseYEscalation(-MOVE_IN_Y);
                    break;
                case 'a':
                case 'A':
                    cube.increaseXEscalation(-MOVE_IN_X);
                    break;
                case 'd':
                case 'D':
                    cube.increaseXEscalation(MOVE_IN_X);
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
        setCustomBufferImage(cube);
        cube.drawObject();
    }

    public Consumer<Character> getCharacterConsumer() {
        return this.characterConsumer;
    }
}
