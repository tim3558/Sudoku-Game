package com.company;

import javax.swing.*;
import java.awt.*;


// MyJFrame class, this is the frame of the game.
// All the panels will be on this frame.

public class MyJFrame extends JFrame{
    private final BoardPanel boardPanel;
    private final UIPanel panelUI;
    private final int PANEL_SIZE = Toolkit.getDefaultToolkit().getScreenSize().height / 2;

//    MyJFrame constructor.
//    Create a new panels for the board and the ui.
//    set the frame characteristics.
//    Add to the frame the buttons actions listeners.
    public MyJFrame(int difficulty){
        boardPanel = new BoardPanel(PANEL_SIZE,difficulty);
        panelUI = new UIPanel(PANEL_SIZE, difficulty);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(panelUI, BorderLayout.NORTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        panelUI.getBtnClearAll().addActionListener(e -> clearAll());
        panelUI.getBtnCheckValid().addActionListener(e -> checkAll());
        panelUI.getBtnSolve().addActionListener(e -> solveAll());

    }

//    Call for checkAll in board panel, if the sudoku is valid show the success massage, else show the failed massage.
    private void checkAll(){
        if(boardPanel.checkAll()){
            panelUI.successMassage();
        }else{
            panelUI.failMassage();
        }
    }

//    Call for solveAll in the boardPanel.
    private void solveAll(){
        boardPanel.solveAll();
    }

//    call for clearAllText in boardPanel, reset the color of the textFields and remove the massage.
    private void clearAll(){
        boardPanel.clearAllText();
        boardPanel.paintAll(Color.white);
        panelUI.clearMassage();
    }

//    @return panelUI -> return the user interface panel.
    public UIPanel getPanelUI(){
        return panelUI;
    }

}
