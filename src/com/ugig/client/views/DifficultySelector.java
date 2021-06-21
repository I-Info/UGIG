package com.ugig.client.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DifficultySelector extends JFrame {
    boolean flag = false;
    int gameChanges = 50;
    int gameRemainTime;

    public DifficultySelector(JFrame jf) {
        setLayout(null);
        setResizable(false);
        setBounds(500, 600, 450, 400);
        setLocationRelativeTo(jf);
        JButton jButton = new JButton("继续游戏");
        JRadioButton radioButton1 = new JRadioButton("是");
        JRadioButton radioButton2 = new JRadioButton("否");
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        radioButton2.setSelected(true);
        ButtonGroup group1 = new ButtonGroup();
        ButtonGroup group2 = new ButtonGroup();
        JPanel jPanel2 = new JPanel();
        JLabel jLabel1 = new JLabel("时间选择");
        JLabel jLabel2 = new JLabel("是否加入时间限制");
        JRadioButton button1 = new JRadioButton("300秒");
        JRadioButton button2 = new JRadioButton("600秒");
        JRadioButton button3 = new JRadioButton("900秒");
        group1.add(button1);
        group1.add(button2);
        group1.add(button3);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        JPanel jPanel1 = new JPanel();
        JRadioButton butt1 = new JRadioButton("50次");
        JRadioButton butt2 = new JRadioButton("30次");
        JRadioButton butt3 = new JRadioButton("15次");
        butt1.setSelected(true);
        group2.add(butt1);
        group2.add(butt2);
        group2.add(butt3);
        JLabel label2 = new JLabel("次数选择");
        JPanel panNorth = new JPanel();
        JPanel panSouth = new JPanel();
        jPanel1.setBounds(0, 300, 200, 100);
        jPanel2.setBounds(50, 150, 100, 100);
        panNorth.setBounds(0, 0, 200, 50);
        panSouth.setBounds(0, 25, 200, 50);
        radioButton1.setBounds(500, 500, 20, 10);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jLabel1.setBounds(70, 250, 60, 50);
        jLabel2.setBounds(50, 100, 100, 50);
        jButton.setBounds(250, 150, 100, 50);
        // add closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                jf.setVisible(true);
                dispose();
            }
        });
        jPanel1.add(button1);
        jPanel1.add(button2);
        jPanel1.add(button3);
        panSouth.add(butt1);
        panSouth.add(butt2);
        panSouth.add(butt3);
        panNorth.add(label2);
        jPanel2.add(radioButton1);
        jPanel2.add(radioButton2);
        add(jLabel1);
        add(panSouth);
        add(panNorth);
        add(jPanel1);
        add(jPanel2);
        add(jLabel2);
        add(jButton);
        radioButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag) {
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button1.setSelected(false);
                    gameRemainTime = 0;
                }
                flag = false;
            }
        });
        radioButton1.addActionListener(e -> {
            if (!flag) {
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button1.setSelected(true);
                gameRemainTime = 300;
            }
            flag = true;
        });
        button1.addActionListener(e -> {
            if (flag) {
                gameRemainTime = 300;
            }

        });
        button2.addActionListener(e -> {
            if (flag) {
                gameRemainTime = 600;
            }
        });
        button3.addActionListener(e -> {
            if (flag) {
                gameRemainTime = 900;
            }

        });

        butt1.addActionListener(e -> {
            gameChanges = 50;
        });
        butt2.addActionListener(e -> {
            gameChanges = 30;
        });
        butt3.addActionListener(e -> {
            gameChanges = 15;

        });
        jButton.addActionListener(e -> {
            new GameView("UGIG", jf, gameChanges, gameRemainTime).setVisible(true);
            dispose();
        });
    }
}
