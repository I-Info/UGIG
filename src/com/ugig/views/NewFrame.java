package com.ugig.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
public class NewFrame extends JFrame implements ActionListener {
    public NewFrame() {
        setLayout(null);
        Container container = getContentPane();
        JPanel panel1 = new JPanel();
        Label label1 = new Label("UGIG");
        panel1.setLayout(new GridLayout(2, 1));
        JButton StartBotton = new JButton("开始游戏");
        JButton RuleBotton = new JButton("游戏规则");
        StartBotton.setSize(20, 10);
        RuleBotton.setSize(20, 10);
        label1.setFont(new java.awt.Font("微软雅黑", 0, 30));
        StartBotton.setFont(new java.awt.Font("微软雅黑", 0, 30));
        RuleBotton.setFont(new java.awt.Font("微软雅黑", 0, 30));
        panel1.add(StartBotton, BorderLayout.NORTH);
        panel1.add(RuleBotton, BorderLayout.SOUTH);
        label1.setBounds(150, 8, 100, 100);
        panel1.setBounds(50, 100, 300, 200);
        this.add(panel1, BorderLayout.CENTER);
        this.add(label1);
        setResizable(false);
        setVisible(true);
        setSize(400, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        StartBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainUI();
                setVisible(false);
            }
        });
        RuleBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RuleDialogDemo();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
    class RuleDialogDemo  extends JDialog{
        public RuleDialogDemo(){
            JTextArea jTextArea=new JTextArea("");
            setVisible(true);
            setBounds(100,500,100,100);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }


    public static void main(String[] args) {
        new NewFrame();
    }
}

