package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

// Set my custom panel to be the grid of the sudoku game.
//
// @pharm BOX_SIZE -> The size of one box on the grid.
// @pharm ROW_SIZE -> The size of the grid.
// @pharm MAX -> The highest number in the game.
// @pharm MIN -> The lowest number in the game.
// @pharm DIFF -> The difficulty of the game.
// @pharm boxesPanel -> Panels array that contain all the panels in the grid, each box panel is 3x3.
// @pharm labels -> Labels arr that contain all the labels that on the panels, will represent all the numbers that cannot be changed.
// @pharm textFields -> Text Fields array that contain all the text fields on the labels, will represent all the field that the user will fill.
// @pharm sudokuFunc -> The sudoku function, control solving, and checking.

public class BoardPanel extends JPanel {

    private final int BOX_SIZE = 3;
    private final int ROW_SIZE = 9;
    private final int MAX = 9;
    private final int MIN = 1;
    private final int DIFF;

    JPanel[][] boxesPanel;
    JLabel[][] labels;
    JTextField[][] textFields;
    SudokuFunc sudokuFunc;

//    BoardPanel constructor.
//    Working in this order:
//    1. create a new boxesPanel 3x3 array, that will be populated with labels or textFields.
//    2. create a new labels and text fields 9x9 arrays.
//    3. Create a new sudokuFunc object.
//    4. Sets the big board panel characteristics.
//    5. Add the 3x3 boxesPanel to the big panel.
//    6. Populate the boxesPanel with labels or textFields.
    public BoardPanel(int size, int diff){
        this.DIFF = diff;
        boxesPanel = new JPanel[BOX_SIZE][BOX_SIZE];
        labels = new JLabel[ROW_SIZE][ROW_SIZE];
        textFields = new JTextField[ROW_SIZE][ROW_SIZE];

        sudokuFunc = new SudokuFunc(ROW_SIZE, MAX, MIN);

        this.setBackground(Color.white);
        this.setBorder(new LineBorder(Color.BLACK));
        this.setLayout(new GridLayout(BOX_SIZE,BOX_SIZE,0,0));
        this.setPreferredSize(new Dimension(size , size-(size / 5)));
        populateWithBoxesPanels();
        populateWithLabelsOrTexts();

    }

//    Paint all the textFields in the same color.
    public void paintAll(Color color){
        for(int row = 0; row < ROW_SIZE; row++){
            for(int col = 0; col < ROW_SIZE; col++){
                if(textFields[row][col] != null){
                    textFields[row][col].setBackground(color);
                }
            }
        }
    }

//    Add to all if the textFields the current values of the sudoku (shows to the user the solution).
    public void solveAll(){
        for(int row = 0; row < ROW_SIZE; row++){
            for(int col = 0; col < ROW_SIZE; col++){
                if(textFields[row][col] != null){
                    textFields[row][col].setText(String.valueOf(sudokuFunc.getValue(row,col)));
                }
            }
        }
    }

//    For every box panel, add 3x3 panels.
    private void populateWithBoxesPanels(){
        for(int row = 0; row < BOX_SIZE; row++){
            for(int col = 0; col < BOX_SIZE; col++){
                boxesPanel[row][col] = new JPanel();
                boxesPanel[row][col].setBackground(Color.GRAY);
                boxesPanel[row][col].setBorder(new LineBorder(Color.BLACK));
                boxesPanel[row][col].setLayout(new GridLayout(BOX_SIZE,BOX_SIZE,BOX_SIZE,BOX_SIZE));
                this.add(boxesPanel[row][col]);
            }
        }
    }

//    By the difficulty, generate some number of labels and some number of textFields to the boxesPanel.
    private void populateWithLabelsOrTexts(){
        for(int row = 0; row < ROW_SIZE; row++){
            for(int col = 0; col < ROW_SIZE; col++){
                if(geneRand(MAX,MIN) > DIFF){
                    boxesPanel[(row/BOX_SIZE)][(col/BOX_SIZE)].add((labels[row][col] = populateWithLabel()));
                    labels[row][col].setText(String.valueOf(sudokuFunc.getValue(row,col)));

                }else{
                    boxesPanel[row/BOX_SIZE][col/BOX_SIZE].add((textFields[row][col] = populateWithTexts()));
                }
            }
        }
    }

//    Set one label characteristics.
//    @return -> The label after styling.
    private JLabel populateWithLabel(){
        JLabel label = new JLabel();
        label.setBackground(Color.lightGray);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        return label;
    }

//    set one textField characteristics.
//    @return -> The textField after styling.
    private JTextField populateWithTexts(){
        JTextField temp = new JTextField();
        temp.setBackground(Color.white);
        temp.setHorizontalAlignment(JTextField.CENTER);
        return temp;
    }

//    Call checkSudoku function in SudokuFunc class to check if the sudoku is valid, and color in red the first invalid cell.
//    @return -> False if the sudoku is invalid, true otherwise.
    public boolean checkAll(){
        SudokuFunc tempSudoku = new SudokuFunc(saveCurrentBoardValues());
        int[] tempError = tempSudoku.checkSudoku();
        if(tempError != null){
            textFields[tempError[0]][tempError[1]].setBackground(Color.RED);
            return false;
        }
        return true;
    }

//    Save the current sudoku state into int 9x9 array.
//    @return tempArr -> The array with the current state.
    private int[][] saveCurrentBoardValues(){
        int[][] tempArr = new int[ROW_SIZE][ROW_SIZE];
        for(int row = 0; row < ROW_SIZE; row++){
            for(int col = 0; col < ROW_SIZE; col++){
                if(textFields[row][col] != null){
                    textFields[row][col].setBackground(Color.WHITE);
                    if(isNumber(textFields[row][col].getText())){
                        tempArr[row][col] = Integer.parseInt(textFields[row][col].getText());
                    }else{
                        tempArr[row][col] = 0;
                    }
                }else{
                    tempArr[row][col] = Integer.parseInt(labels[row][col].getText());
                }
            }
        }
        return tempArr;
    }

//    Check validation of the current string, if he is a number between 1 to 9.
//    return -> true if the string is a number between 1 to 9, false otherwise.
    private boolean isNumber(String tempStr){
        if(tempStr == null){
            return false;
        }
        try{
            int tempInt = Integer.parseInt(tempStr);
            if(tempInt < 1 || tempInt > 9){
                return false;
            }
        }catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }

//    Clear all the text that inside the textFields.
    public void clearAllText(){
        for(int row = 0; row < ROW_SIZE; row++){
            for(int col = 0; col < ROW_SIZE; col++){
                if(textFields[row][col] != null){
                    textFields[row][col].setText("");
                }
            }
        }
    }

//    Generate random number between max number to min number.
//    @pharm max -> The maximum number.
//    @pharm min -> The minimum number.
    private int geneRand(int max, int min){
        Random random = new Random();
        return random.nextInt(max)+min;
    }
}
