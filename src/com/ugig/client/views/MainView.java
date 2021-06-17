package com.ugig.client.views;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class MainView extends JFrame {
    public MainView() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 250, 400, 500);

        setLayout(null);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu AboutUs = new JMenu("关于我们");
        jMenuBar.add(AboutUs);
        JPanel panel1 = new JPanel();
        Label label1 = new Label("UGIG");
        panel1.setLayout(new GridLayout(2, 1));
        JButton StartButton = new JButton("开始游戏");
        JButton RuleButton = new JButton("游戏规则");
        StartButton.setSize(20, 10);
        RuleButton.setSize(20, 10);
        label1.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 30));
        StartButton.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 30));
        RuleButton.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 30));
        panel1.add(StartButton, BorderLayout.NORTH);
        panel1.add(RuleButton, BorderLayout.SOUTH);
        label1.setBounds(150, 8, 100, 100);
        panel1.setBounds(45, 100, 300, 200);
        this.add(panel1, BorderLayout.CENTER);
        this.add(label1);
        this.setJMenuBar(jMenuBar);
        setResizable(false);
        setVisible(true);
        setSize(400, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JFrame jf = this;
        AboutUs.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                new AboutUsDialog(jf);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        StartButton.addActionListener(e -> {
            new DifficultySelector(this).setVisible(true);
            setVisible(false);
        });
        RuleButton.addActionListener(e -> new RuleDialog(this));
    }
}

