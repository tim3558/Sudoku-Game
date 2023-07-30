package com.company;

import java.util.Random;

// SudokuFunc class, generate a new sudoku, check for valid sudoku and reset the sudoku.
// @pharm sudokuValues -> 2d array that contain all of the solved sudoku values.
// @pharm size -> The size of row / col of the 2d array.
// @pharm  max -> The highest number that can be place on the sudoku.
// @pharm min -> The lowest number that can be place on the sudoku.

public class SudokuFunc {

    private final int[][] sudokuValues;
    private final int size;
    private final int max;
    private final int min;

//    SudokuFunc constructor, call when want to create a new sudoku from 0.
//    Create a new sudokuValues 9x9 array, sets all the values of the array to 0, and then solve the sudoku.
//    @pharm size -> The size of row / col of the 2d array.
//    @pharm  max -> The highest number that can be place on the sudoku.
//    @pharm min -> The lowest number that can be place on the sudoku.
    public SudokuFunc(int size, int max, int min){
        this.size = size;
        this.max = max;
        this.min = min;
        sudokuValues = new int[size][size];
        resetSudokuValues();
        generateSudokuValues();
    }

//    SudokuFunc constructor, call when already got sudokuValues array, and want to validate the array.
//    @pharm sudokuValues -> The 9x9 sudoku array that pass from the board.
    public SudokuFunc(int[][] sudokuValues){
        this.sudokuValues = sudokuValues;
        size = sudokuValues.length;
        max = sudokuValues.length;
        min = 1;
    }

//    Check if the sudoku is valid, by passing every cell and checking if the cell is 0, or call isValid method.
//    @return tempError -> If the sudoku is not valid, tempError[0] is the row number that the problem has found, tempError[1] is the col number that the problem has found.
//    @return null -> If the sudoku is valid.
    public int[] checkSudoku(){
        int[] tempError = new int[2];
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                int temp = sudokuValues[row][col];
                if(temp == 0){
                    tempError[0] = row;
                    tempError[1] = col;
                    return tempError;
                }else if(!isValid(temp , row, col)){
                    tempError[0] = row;
                    tempError[1] = col;
                    return tempError;
                }
            }
        }
        return null;
    }

//    @pharm row -> The row number that we w ant to get the value.
//    @phar, col -> the col number that we want to get the value.
//    @return sudokuValues[row][col] -> The value in the wanted row,col.
    public int getValue(int row, int col){
        return sudokuValues[row][col];
    }

//    A recursive trackBack Algo to generate a new fully solved sudoku.
//    Work in this order:
//    1. generate a new random number in the range of 1 - 9;
//    2. loop through the 9x9 array, and checks if the current cell is 0 (free to use), if not return false.
//    3. loop through 1 - 9, and checks if one of them is valid in the current cell, if not return false.
//    4. set the valid number in the current cell.
//    5. call generateSudokuValues to fill the next cells, if the method returns true, also return true.
//    6. if false is return then sudoku cannot be valid in this way, and sets the current value to 0.
//    7. return false.
//    7. return true if the loops has ended (sudoku has been solved).
    private boolean generateSudokuValues(){
        int value = generateNum();
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                if(sudokuValues[row][col] == 0){
                    for(int i = 0; i<size; i++){
                        value = (value < max) ? value+1 : 1;
                        if(isValid(value, row, col)){
                            sudokuValues[row][col] = value;
                            if(generateSudokuValues()){
                                return true;
                            }else{
                                sudokuValues[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

//    Check if the value in the current position on the grid.
//    Loop trough the 9x9 array and check if the number has found in the same row, or in the same col, or in the same box.
//    @pharm value -> The current value that we want to check.
//    @pharm row -> The row number that wa want to check.
//    @pharm col -. the column number that we want to check in.
//    @return true -> If the current values is valid in the current position.
    public boolean isValid(int value, int row, int col){
        int box = (int)Math.sqrt(size);
        int boxRow = (row / box) * box;
        int boxCol = (col / box) * box;
        for(int i = 0; i < size; i++){
            if( (sudokuValues[row][i] == value && i != col) || (sudokuValues[i][col] == value && i != row )){
                return false;
            }
        }

        for(int i = 0; i < box; i++){
            for(int j = 0; j < box; j++){
                if(sudokuValues[boxRow + i][boxCol + j] == value && (boxRow+i != row) && (boxCol+j != col)){
                    return false;
                }
            }
        }
        return true;
    }

//    Set all the values in the 9x9 array to 0.
    private void resetSudokuValues(){
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                sudokuValues[row][col] = 0;
            }
        }
    }

//    Generate a random number between min - max.
    private int generateNum(){
        Random rand = new Random();
        return rand.nextInt(max)+min;
    }
}