/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.os.nqueenssolver;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author m
 */
public class MyTable extends JFrame{
    
    private final JTable table;
    private final DefaultTableModel model;
    private final JScrollPane scrollPane;
    
    public MyTable(){
        // Create a JFrame and a JTable
        this.table = new JTable();
        // Create a DefaultTableModel with columns and 0 rows initially
        this.model = new DefaultTableModel(new Object[]{"Successfully Terminated threads"}, 0);
        // Set the model for the table
        table.setModel(model);
        table.setDefaultEditor(Object.class, null);
        // Add the table to a JScrollPane
        scrollPane = new JScrollPane(table);
        // Add the scroll pane to the frame
        this.getContentPane().add(scrollPane);

        // Set frame properties
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        this.setLocation(screenWidth - this.getWidth(), 0);
        this.setTitle("Threads output");
        this.setVisible(true);
    }
    
    public void addRow(String column1Data) {
        model.addRow(new Object[]{column1Data});
    }
    
}
