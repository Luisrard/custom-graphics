package com.luisrard.custom.graphics;

import javax.swing.*;
import java.awt.*;

public class TestPanels extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestPanels(args[0]));
    }
    public TestPanels(String panel) {
        setSize(400,400);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PaintPanel jPanel;
        switch (panel){
            case "1":
                jPanel = new Model01Panel();
                break;
            case "2":
                jPanel = new Model02PanelImprovement();
                break;
            case "3":
                jPanel = new Model03DDAPanel();
                break;
            case "4":
                jPanel = new Model04PanelBresenham();
                break;
            default:
                jPanel = new PaintPanel() {
                    @Override
                    public void drawLine(int x0, int y0, int x1, int y1, Color c) {}
                };
        }
        setTitle(jPanel.getPanelName());
        add(jPanel);
        setVisible(true);
    }
}
