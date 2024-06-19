package com.luisrard.custom.graphics.third.partial;


import com.luisrard.custom.graphics.second.partial.PaintPanel;
import com.luisrard.custom.graphics.third.partial.panels.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.function.Consumer;


public class MainFrame extends JFrame {
    private Consumer<Character> characterConsumer;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame(args[0]));
    }
    public MainFrame(String panelName) {
        setSize(PaintPanel.WIDTH, PaintPanel.HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jPanel;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (Objects.nonNull(characterConsumer)){
                    characterConsumer.accept(e.getKeyChar());
                }
            }
        });

        switch (panelName){
            case "01 Proyección paralela":
                jPanel = new ParallelPanel();
                break;
            case "02 Proyección de perspectiva" :
                jPanel = new PerspectivePanel();
                break;
            case "03 Traslación 3D" :
                TranslationPanel mainPanelTraslation = new TranslationPanel();
                characterConsumer = mainPanelTraslation.getCharacterConsumer();
                jPanel = mainPanelTraslation;
                break;
            case "04 Escalamiento 3D" :
                ScalePanel mainPanelScale = new ScalePanel();
                characterConsumer = mainPanelScale.getCharacterConsumer();
                jPanel = mainPanelScale;
                break;
            case "05 Rotación 3D" :
                RotationPanel mainPanelRotation = new RotationPanel();
                characterConsumer = mainPanelRotation.getCharacterConsumer();
                jPanel = mainPanelRotation;
                break;
            case "06 Rotación 3D automática" :
                jPanel = new RotationAutomaticPanel();
                break;
            case "07 Curva explicita 3D" :
                jPanel = new Curve3DPanel();
                break;
            case "08 Superficie 3D" :
                jPanel = new Surface3DPanel();
                break;
            case "09 Cilindro 3D" :
                jPanel = new Cylinder3DPanel();
                break;
            case "Proyecto Final":
                Project3DPanel project3DPanel = new Project3DPanel();
                characterConsumer = project3DPanel.getCharacterConsumer();
                jPanel = project3DPanel;
                break;
            default:
                jPanel = new JPanel();
        }


        setTitle(panelName);
        add(jPanel);
        setVisible(true);
    }
}
