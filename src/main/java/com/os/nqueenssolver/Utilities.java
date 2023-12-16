/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.os.nqueenssolver;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author m
 */
public class Utilities {

    public static void centerFrameOnScreen(JFrame frame) {
        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the center position
        int centerX = (int) (screenSize.getWidth() - frame.getWidth()) / 2;
        int centerY = (int) (screenSize.getHeight() - frame.getHeight()) / 2;

        // Set the frame location
        frame.setLocation(centerX, centerY);
    }

    public static void moveFrameToLeftTop(JFrame frame) {
        frame.setLocation(0, 0);
    }


    public static void moveFrameToRightTop(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int rightX = (int) (screenSize.getWidth() - frame.getWidth());
        frame.setLocation(rightX, 0);
    }

    public static void moveFrameToLeftBottom(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int bottomY = (int) (screenSize.getHeight() - frame.getHeight());
        frame.setLocation(0, bottomY);
    }


    public static void moveFrameToRightBottom(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int rightX = (int) (screenSize.getWidth() - frame.getWidth());
        int bottomY = (int) (screenSize.getHeight() - frame.getHeight());
        frame.setLocation(rightX, bottomY);
    }

    public static void delay() throws InterruptedException {
        
        Thread.sleep(600);
       
    }
}
