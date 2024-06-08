package com.luisrard.custom.graphics.third.partial;


import com.luisrard.custom.graphics.second.partial.PaintPanel;

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
                jPanel = new MainPanel();
                break;
            case "02 Proyección de perspectiva" :
                jPanel = new MainPanelPerspective();
                break;
            case "03 Traslación 3D" :
                MainPanelTranslation mainPanelTraslation = new MainPanelTranslation();
                characterConsumer = mainPanelTraslation.getCharacterConsumer();
                jPanel = mainPanelTraslation;
                break;
            case "04 Escalamiento 3D" :
                MainPanelScale mainPanelScale = new MainPanelScale();
                characterConsumer = mainPanelScale.getCharacterConsumer();
                jPanel = mainPanelScale;
                break;
            case "05 Rotación 3D" :
                MainPanelRotation mainPanelRotation = new MainPanelRotation();
                characterConsumer = mainPanelRotation.getCharacterConsumer();
                jPanel = mainPanelRotation;
                break;
            case "06 Rotación 3D automática" :
                jPanel = new MainPanelRotationAutomatic();
                break;
            default:
                jPanel = new JPanel();
        }


        setTitle(panelName);
        add(jPanel);
        setVisible(true);
    }
}
