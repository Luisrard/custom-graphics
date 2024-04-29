package com.luisrard.custom.graphics;

import javax.swing.*;

public class TestPanels extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestPanels(args[0]));
    }
    public TestPanels(String panel) {
        setSize(PaintPanel.WIDTH,PaintPanel.HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PaintPanel jPanel;
        switch (panel){
            case "1":
                jPanel = new Model01Panel();
                break;
            case "2":
                jPanel = new Model02ImprovementPanel();
                break;
            case "3":
                jPanel = new Model03DDAPanel();
                break;
            case "4":
                jPanel = new Model04BresenhamPanel();
                break;
            case "5":
                jPanel = new Model05MiddlePointPanel();
                break;
            case "6":
                jPanel = new Model06RectanglePanel();
                break;
            case "7":
                jPanel = new Model07Circle01Panel();
                break;
            case "8":
                jPanel = new Model08Circle02PolarPanel();
                break;
            case "9":
                jPanel = new Model09Circle03PolarSymmetricPanel();
                break;
            case "10":
                jPanel = new Model10CircleMiddlePointPanel();
                break;
            case "11":
                jPanel = new Model11CircleBresenhamPanel();
                break;
            case "12":
                jPanel = new Model12FullFiguresPanel();
                break;
            case "13":
                jPanel = new Model13LineThicknessPanel();
                break;
            case "14":
                jPanel = new Model14LineMaskPanel();
                break;
            case "15":
                jPanel = new Model15CircleMaskPanel();
                break;
            case "16":
                jPanel = new Model16CircleThicknessPanel();
                break;
            case "17":
                jPanel = new Model17ScanLinePanel();
                break;
            case "18":
                jPanel = new Model18FloodFillPanel();
                break;
            case "19":
                jPanel = new Model19CutBresenhamPanel();
                break;
            default:
                jPanel = new PaintPanel();
        }
        setTitle(jPanel.getPanelName());
        add(jPanel);
        setVisible(true);
    }
}
