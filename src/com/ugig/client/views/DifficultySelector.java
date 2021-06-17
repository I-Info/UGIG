package com.ugig.client.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DifficultySelector extends JFrame {
    public DifficultySelector(JFrame jf) {
        setSize(300, 150);
        setLocationRelativeTo(jf);
        JButton butt1 = new JButton("简单");
        JButton butt2 = new JButton("中等");
        JButton butt3 = new JButton("困难");
        Label label1 = new Label("50chances");
        Label label2 = new Label("30chances");
        Label label3 = new Label("15chances");
        JPanel panNorth = new JPanel();
        JPanel panSouth = new JPanel();

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
                dispose();
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
            new GameView("UGIG", jf, 50, 600).setVisible(true);
            dispose();
        });
        butt2.addActionListener(e -> {
            new GameView("UGIG", jf, 30, 600).setVisible(true);
            dispose();
        });
        butt3.addActionListener(e -> {
            new GameView("UGIG", jf, 15, 600).setVisible(true);
            dispose();
        });
    }
}
