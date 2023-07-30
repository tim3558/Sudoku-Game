package com.company;

// Create a new frame, and add the buttons actions listeners to the frame.
public class Main {

    private static MyJFrame frame;
//    call newFrame method.
    public static void main(String[] args) {
        newFrame();
    }

//    If frame is already exist, close the frame and open a new one.
//    Else create a new frame.
    private static void newFrame(){
        if(frame != null){
            frame.dispose();
            frame = new MyJFrame(frame.getPanelUI().getDifficulty());

        }else{
            frame = new MyJFrame(4);
        }
        MyListener();

    }

//    add the buttons actions listeners to the frame.
    private static void MyListener(){
        frame.getPanelUI().getBtnNewBoard().addActionListener(e -> newFrame());
        frame.getPanelUI().getCbDifficulty().addActionListener(e -> newFrame());
    }
}
