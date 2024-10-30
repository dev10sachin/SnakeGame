package com.bank;

import javax.swing.JFrame;

public class Snakegame extends JFrame {
    Snakegame(){
        super("Snake Game");
        add(new Board());     //call from board
        pack();         //refresh of frame after changes
        setSize(1280,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable( true);
        
       }
    
    
    public static void main(String[] args){
    new Snakegame();




    }
    
}
