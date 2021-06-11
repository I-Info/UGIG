package com.ugig.tests;

import com.ugig.views.GameView;

import javax.swing.*;
import java.awt.*;


public class TestView extends JFrame {
    public TestView(String title) {
        super(title);
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        JButton button = new JButton("run");
        button.addActionListener(e -> {
            new GameView("Game",this,10).setVisible(true);
            setVisible(false);
        });
        container.add(button);
    }
}
