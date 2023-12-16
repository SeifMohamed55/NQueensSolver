/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.os.nqueenssolver;

import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;

/**
 *
 * @author m
 */
public class NQueensSolver implements Runnable {

    private final int BOARD_SIZE;
    private final ChessBoard cb;
    private final ThreadGroup group;
    private final Object mutex;
    private volatile AtomicInteger noOfSol;
    private final MyTable table;

    public NQueensSolver(Object mutex, ThreadGroup group, ChessBoard cb, int boardSize, AtomicInteger noOfSol, MyTable table) {
        BOARD_SIZE = boardSize;
        this.cb = cb;
        this.group = group;
        this.mutex = mutex;
        this.noOfSol = noOfSol;
        this.table = table;
    }

    @Override
    public void run() {
//        if (BOARD_SIZE == 1 || BOARD_SIZE == 2 || BOARD_SIZE == 3) {
//            
//            try {
//                mutex.acquire();
//            } catch (InterruptedException ex) {
//                return;
//            }
//            System.out.println("NO Solution can be found");
//             JOptionPane.showMessageDialog(cb, "NO Solution can be found",
//                    "Error", JOptionPane.INFORMATION_MESSAGE);
//            group.interrupt();
//            mutex.release();
//            return;
//        }

        if (solveNQUtil(cb, 1)) {
            if (noOfSol.compareAndSet(0, 1)) {
                table.addRow(Thread.currentThread().getName() + " is the first"
                        + " thread to find a solution!");
                JOptionPane.showMessageDialog(cb, Thread.currentThread().getName() + " is the first"
                        + " thread to find a solution!",
                        "Complete", JOptionPane.INFORMATION_MESSAGE);
            } else {
                table.addRow(Thread.currentThread().getName() + " found solution number " + noOfSol.incrementAndGet());
            }

        } else if (Thread.currentThread().isInterrupted()) {
            table.addRow(Thread.currentThread().getName() + " was interrupted");
        } else {
            table.addRow(Thread.currentThread().getName()
                    + " Couldn't Find a solution");
        }
    }

    boolean solveNQUtil(ChessBoard board, int row) {

//        if (Thread.currentThread().isInterrupted()) {
//            return false;
//        }
        if (row == BOARD_SIZE) {
//            if (Thread.currentThread().isInterrupted()) {
//                return false;
//            }

//            synchronized(mutex){
//                if (Thread.currentThread().isInterrupted()) {
//                    return false;
//                }
//                group.interrupt();
//                System.out.println(Thread.currentThread().getName() + " found a solution");
//            }
            return true;
        }

        for (int col = 0; col < BOARD_SIZE; col++) {
//            if (Thread.currentThread().isInterrupted()) {
//                return false;
//            }

            if (isSafe(board, row, col)) {
                board.setButton(row, col);
                try {
                    Utilities.delay();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return false;
                }
//                if (Thread.currentThread().isInterrupted()) {
//                    return false;
//                }
                if (solveNQUtil(board, row + 1)) {
                    return true;
                }
                try {
                    Utilities.delay();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return false;
                }
                board.emptyButton(row, col);
            }
        }
        return false;

    }

    private boolean isSafe(ChessBoard board, int row, int col) {
        int i;

        /* Check this row */
        for (i = 0; i < BOARD_SIZE; i++) {
            if (board.getButton(row, i) == 1) {
                return false;
            }
        }

        /*check column*/
        for (i = 0; i < BOARD_SIZE; i++) {
            if (board.getButton(i, col) == 1) {
                return false;
            }
        }

        for (i = 0; i < BOARD_SIZE; i++) {
            int diagonalRow = row - col + i;
            int diagonalCol = i;

            if (diagonalRow >= 0 && diagonalRow < BOARD_SIZE && diagonalCol >= 0 && diagonalCol < BOARD_SIZE) {
                if (cb.getButton(diagonalRow, diagonalCol) == 1) {
                    return false;
                }
            }
        }

        // Check secondary diagonal
        for (i = 0; i < BOARD_SIZE; i++) {
            int diagonalRow = row + col - i;
            int diagonalCol = i;

            if (diagonalRow >= 0 && diagonalRow < BOARD_SIZE && diagonalCol >= 0 && diagonalCol < BOARD_SIZE) {
                if (cb.getButton(diagonalRow, diagonalCol) == 1) {
                    return false;
                }
            }
        }

        return true;
    }

}
