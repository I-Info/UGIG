package com.ugig.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Difficulty extends JFrame {
    public Difficulty(JFrame jf) {
        this.setBounds(400, 200, 300, 150);
        JButton butt1 = new JButton("简单");
        JButton butt2 = new JButton("中等");
        JButton butt3 = new JButton("困难");
        Label label1 = new Label("50change");
        Label label2 = new Label("30change");
        Label label3 = new Label("15change");
        JPanel panNorth = new JPanel();
        JPanel panSouth = new JPanel();
        JFrame JF = this;

        butt1.setSize(20, 10);
        butt2.setSize(20, 10);
        butt3.setSize(20, 10);
        label1.setSize(20, 10);
        label2.setSize(20, 10);
        label3.setSize(20, 10);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // add closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                jf.setVisible(true);

            }

        });

        panSouth.add(butt1);
        panSouth.add(butt2);
        panSouth.add(butt3);
        panNorth.add(label1);
        panNorth.add(label2);
        panNorth.add(label3);

        add(panSouth, BorderLayout.SOUTH);
        add(panNorth, BorderLayout.NORTH);

        butt1.addActionListener(e -> {
            new GameView("UGIG", jf, 50, 10).setVisible(true);
            dispose();
        });
        butt2.addActionListener(e -> {
            new GameView("UGIG", jf, 30, 10).setVisible(true);
            dispose();
        });
        butt3.addActionListener(e -> {
            new GameView("UGIG", jf, 15, 10).setVisible(true);
            dispose();
        });
    }
}
