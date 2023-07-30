package com.company;

import javax.swing.*;
import java.awt.*;

// MyJButton class, this is the basic custom JButton.
public class MyButton extends JButton {

//    @pharm name -> The string that will be the text of the button.
//    @pharm size -> The Width of the frame.
    public MyButton(String name, int size){
        this.setText(name);
        this.setPreferredSize(new Dimension(size / 5, 25));
        this.setFocusable(false);
    }

}
