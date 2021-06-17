package com.ugig.views;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
public class MainFrame extends JFrame {
    public MainFrame() {
        this.setBounds(600, 500, 400, 500);
        JFrame JF = this;
        setLayout(null);
        JMenuBar jMenuBar=new JMenuBar();
        JMenu AboutUs=new JMenu("关于我们");
        jMenuBar.add(AboutUs);
        JPanel panel1 = new JPanel();
        Label label1 = new Label("UGIG");
        panel1.setLayout(new GridLayout(2, 1));
        JButton StartBotton = new JButton("开始游戏");
        JButton RuleBotton = new JButton("游戏规则");
        StartBotton.setSize(20, 10);
        RuleBotton.setSize(20, 10);
        label1.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 30));
        StartBotton.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 30));
        RuleBotton.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 30));
        panel1.add(StartBotton, BorderLayout.NORTH);
        panel1.add(RuleBotton, BorderLayout.SOUTH);
        label1.setBounds(150, 8, 100, 100);
        panel1.setBounds(45, 100, 300, 200);
        this.add(panel1, BorderLayout.CENTER);
        this.add(label1);
        this.setJMenuBar(jMenuBar);
        setResizable(false);
        setVisible(true);
        setSize(400, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AboutUs.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                    new AboutUsDialog();

            }
            @Override
            public void menuDeselected(MenuEvent e) {
            }
            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        StartBotton.addActionListener(e -> {
            new GameView("UGIG", JF, 30).setVisible(true);
            setVisible(false);
        });
        RuleBotton.addActionListener(e -> new RuleDialog());
    }


}

