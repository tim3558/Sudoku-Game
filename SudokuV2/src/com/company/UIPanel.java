package com.company;

import javax.swing.*;
import java.awt.*;

// UIPanel class, that will be the user interface panel.
// On it Will be the buttons, the score and any massages.
// @pharm btnNewBoard -> Create a new board button.
// @pharm btnSolve -> Solve the sudoku button.
// @pharm btnCheckValid -> Check if the current state of the sudoku is valid button.
// @pharm btnClearAll -> Clear all the values in the textFields button.
// @pharm cbDifficulty -> ComboBox for choosing the difficulty.
// @pharm massage -> Show to the user if the sudoku is valid or not after clicking the btnCheckValid.

public class UIPanel extends JPanel {
    private final MyButton btnNewBoard;
    private final MyButton btnSolve;
    private final MyButton btnCheckValid;
    private final MyButton btnClearAll;
    private final MyComboBox cbDifficulty;
    private final JLabel massage;

//    UIPanel constructor.
//    Create all of the user interface components and add them to the panel.
//    Sets the UI Panel characteristics.
    public UIPanel(int size, int difficulty){
        btnNewBoard = new MyButton("New Game", size);
        btnSolve = new MyButton("Solve", size);
        btnCheckValid = new MyButton("Check", size);
        btnClearAll = new MyButton("Clear", size);
        cbDifficulty = new MyComboBox(difficulty);
        massage = new JLabel();

        this.setBackground(new Color(80,210,170));
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(size, size/6));

        this.add(cbDifficulty);
        this.add(btnNewBoard);
        this.add(btnCheckValid);
        this.add(btnSolve);
        this.add(btnClearAll);

        this.add(massage);
    }

//    return -> The current difficulty of the game.
    public int getDifficulty(){
        return switch (cbDifficulty.getSelectedIndex()) {
            case 0 -> 2;
            case 2 -> 6;
            case 3 -> 8;
            default -> 4;
        };
    }

//    Sets the success massage characteristics.
    public void successMassage(){
        massage.setText("Sudoku is valid :)");
        massage.setFont(new Font("Stencil", Font.BOLD, 35));
        massage.setForeground(new Color(50,150,50));
    }
//    Sets the failed massage characteristics.
    public void failMassage(){
        massage.setText("Sudoku is not valid :(");
        massage.setFont(new Font("Stencil", Font.BOLD, 35));
        massage.setForeground(new Color(230,50,50));
    }

//    Set the massage visible.
    public void clearMassage(){
        massage.setVisible(false);
    }

//    @return cbDifficulty -> return the combo box difficulty.
    public MyComboBox getCbDifficulty(){
        return cbDifficulty;
    }

//    @return btnNewBoard -> return the new board button.
    public JButton getBtnNewBoard(){
        return btnNewBoard;
    }

//    @return btnCheckValid -> return the check valid button.
    public JButton getBtnCheckValid(){
        return btnCheckValid;
    }

//    @return btnClearAll -> return the clear all button.
    public JButton getBtnClearAll(){
        return btnClearAll;
    }

//    @return btnSolve -> return the solve button.
    public JButton getBtnSolve(){
        return btnSolve;
    }


}
