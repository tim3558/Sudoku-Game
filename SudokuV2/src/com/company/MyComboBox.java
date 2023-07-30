package com.company;

import javax.swing.*;
import java.awt.*;

// MyComboBox class, create a difficulty menu.
public class MyComboBox extends JComboBox {

//    MyComboBox constructor, sets the comboBox characteristics.
//    Create a string with the difficulties, and add them to the comboBox.
//    @pharm tempDifficulty -> the current difficulty of the game.
    public MyComboBox(int tempDifficulty){
        String[] difficulty = {"Easy", "Medium", "Hard", "Extreme"};
        for (String s : difficulty) {
            this.addItem(s);
        }
        this.setBackground(Color.cyan);
        this.setToolTipText("Defficulty");
        this.setSelectedIndex(getDifficultyIndex(tempDifficulty));
    }

//    @return -> for each gameDifficulty return a number.
    private int getDifficultyIndex(int tempDifficulty){
        return switch (tempDifficulty) {
            case 2 -> 0;
            case 6 -> 2;
            case 8 -> 3;
            default -> 1;
        };
    }
}
