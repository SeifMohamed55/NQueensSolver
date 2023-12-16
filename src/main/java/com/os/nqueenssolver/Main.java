/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.os.nqueenssolver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JOptionPane;

/**
 *
 * @author m
 */
public class Main {

    public static void main(String[] args) {
        var mainFrame = new GUIMain();
        mainFrame.setVisible(true);
        Utilities.centerFrameOnScreen(mainFrame);
        ThreadGroup tg = new ThreadGroup("MyThreadGroup");
        Object mutex = new Object();
        AtomicInteger noOfSol = new AtomicInteger(0);
        mainFrame.getSubmitButton().addActionListener((ActionEvent ae)
                -> {
            String boardText = mainFrame.getTextField().getText();
            try {
                int boardSize = Integer.parseInt(boardText);
                if (boardSize <= 0) {
                    throw new NumberFormatException();
                }
                // add code here
                Thread[] threads = new Thread[boardSize];
                mainFrame.dispose();
                MyTable table = new MyTable();
                for (int i = 0; i < boardSize; i++) {
                    ChessBoard cb = new ChessBoard(boardSize, "Thread " + i, table);
                    cb.setButton(0, i);                   
                    threads[i] = new Thread(tg,
                            new NQueensSolver(mutex, tg, cb, boardSize, noOfSol, table), "Thread " + i);
                    threads[i].start();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Board size must be a valid number",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
