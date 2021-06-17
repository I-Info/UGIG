package com.ugig.views;
import javax.swing.*;
public class AboutUsDialog extends JFrame {
    public AboutUsDialog(){
        JTextArea jTextArea=new JTextArea("开发者:"+"UGIG项目组"+"\n"+"日期"+":"+"2021.6");
        jTextArea.setFont(new java.awt.Font("微软雅黑",0,20));
        jTextArea.setEditable(false);
        jTextArea.setSize(100,100);
        add(jTextArea);
        setResizable(false);
        setVisible(true);
        setBounds(300,300,200,200);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
    public static void main(String[] args) {
        new AboutUsDialog();
    }
}
