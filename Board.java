package com.bank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.ImageIcon;


import javax.swing.JPanel;//small components that makes up a frame of window.

public class Board extends JPanel implements ActionListener{
    public Image apple;
    public Image dot;
    public Image head;
    public int dots ;
    public final int MAX_dots = 76800;
    public final int Random_pos = 70;
    public final int  unit_size = 10;
    public final int  x[] = new int[MAX_dots];
    public final int  y[] = new int[MAX_dots];
    public int apple_x;
    public int apple_y;
    public Timer timer;
    public boolean leftdir= false;
    public boolean rightdir = true;
    public boolean updir = false;
    public boolean downdir = false;
    public boolean running;

    Board(){
        addKeyListener(new Tadapter());
        setBackground(Color.RED);
        setFocusable(true);
        loadimages();
        initimages();
        locateapp();
        timer = new Timer(100 , this);
        timer.start();
    }
    public class Tadapter extends KeyAdapter {
    @Override    
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if ( key == KeyEvent.VK_LEFT && (!rightdir)){
                leftdir = true;
                updir = false;
                downdir = false;
             }
             if ( key == KeyEvent.VK_RIGHT && (!leftdir)){
                rightdir = true;
                updir = false;
                downdir = false;


            }
            if (key == KeyEvent.VK_UP && (!downdir)){
                leftdir = false;
                updir = true;
                rightdir = false;


            }
            if ( key == KeyEvent.VK_DOWN && (!updir)){
                leftdir = false;
                rightdir = false;
                downdir = true;
             }
            
        }


    }
    public void checkcollision(){
        for (int i= dots ; i>0 ;i--){
            if(i>4 && x[0] ==x[i] && y[0] == y[i]){
                running = false;  

            }

        }

        }
    
    public void move(){
        for(int i = dots ; i>0 ; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(leftdir){
            x[0] = x[0] - unit_size;
         }
         if(rightdir){
            x[0] = x[0] + unit_size;
         }
         if(updir){
            y[0] = y[0] - unit_size; 
         }
         if(downdir){
            y[0] = y[0] + unit_size;
         } 
       // x[0] += unit_size;
       // y[0] += unit_size;
    }
    public void checkapple(){
        
        if (x[0]==apple_x &&  y[0] == apple_y){
            dots++;
            locateapp();

        }
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        move();
        checkapple();
        repaint();
    }

    public void loadimages(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/bank/apple.png"));
        apple = i1.getImage();
          
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("com/bank/dot.png"));
        dot= i2.getImage();
    }
         
    public void initimages(){
          dots = 3; // for making the snake on the frame.
          for (int i =0 ;i<dots ;i++){
               y[i]=50; //
               x[i] =50-i*unit_size;

          }
        }// for showing dots on frame
        public void locateapp(){
        int r = (int)(Math.random()*Random_pos);
        apple_x = r * unit_size;// random generates vlaue from 0 to 1 
        r= (int)(Math.random()*Random_pos);
        apple_y= r * unit_size;
    }
    @Override
          public void paintComponent(Graphics g){
            super.paintComponent(g);
            draw(g); 
          }
           public void draw(Graphics g){
            g.drawImage(apple , apple_x , apple_y, this);           
            for (int i = 0 ; i<dots ;i++){
                if (i==0){
                    g.drawImage(dot , x[i], y[i], this);
                }
                else{
                    g.drawImage(dot, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
           }

        
        }

    
    
