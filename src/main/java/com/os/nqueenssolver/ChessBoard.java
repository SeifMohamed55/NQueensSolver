package com.os.nqueenssolver;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;

public class ChessBoard extends javax.swing.JFrame {

    private final int BOARD_SIZE;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares;
    private int[][] logicalBoard;
    private JPanel chessBoard;
    private final String userDir = System.getProperty("user.dir");
    private final ImageIcon icon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    private final ImageIcon img = new ImageIcon(userDir + "/images/queen.png");

    public ChessBoard(int boardSize, String title, MyTable table) {

        BOARD_SIZE = boardSize;
        try {
            chessBoardSquares = new JButton[BOARD_SIZE][BOARD_SIZE];
            logicalBoard = new int[BOARD_SIZE][BOARD_SIZE];
        } catch (OutOfMemoryError ex) {
            JOptionPane.showMessageDialog(null, "Program out of memory", "Error", JOptionPane.ERROR_MESSAGE);
            table.dispose();
            return;
        }
        setTitle(title);
        initializeGui();

        Runnable r = () -> {
            this.add(this.getGui());
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLocationByPlatform(true);

            // ensures the frame is the minimum size it needs to be
            // in order display the components within it
            this.pack();
            // ensures the minimum size is enforced.
            this.setMinimumSize(this.getSize());
            this.setVisible(true);

        };
        SwingUtilities.invokeLater(r);

    }

    // initializes the ChessBoard
    public final void initializeGui() {
        // set up the main GUI
        chessBoard = new JPanel(new GridLayout(0, BOARD_SIZE + 1));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        // Initializes Buttons
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                b.setIcon(icon);
                if ((j % 2 != 0 && i % 2 != 0) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.DARK_GRAY);
                }
                chessBoardSquares[i][j] = b;
            }
        }

        // Complicated do not discuss
        //fill the chess board
        // fill the black non-pawn piece row
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                switch (j) {
                    case 0:
                        chessBoard.add(new JLabel(""));
                    default:
                        chessBoard.add(chessBoardSquares[i][j]);
                }
            }
        }
    }


    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public void setButton(int row, int col) {
        chessBoardSquares[row][col].setIcon(img);
        logicalBoard[row][col] = 1;
    }

    public void emptyButton(int row, int col) {
        chessBoardSquares[row][col].setIcon(icon);
        logicalBoard[row][col] = 0;
    }

    public int getButton(int row, int col) {
        return logicalBoard[row][col];
    }

    public final JComponent getGui() {
        return gui;
    }

}
