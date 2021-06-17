package com.ugig.client.views;

import javax.swing.*;
import java.awt.*;

public class AboutUsDialog extends JFrame {
    public AboutUsDialog(Component component) {
        JTextArea jTextArea = new JTextArea("""
                \bCopyright(C)2021
                \bUGIG项目组版权所有
                \bMIT Licence
                \bhttps://github.com/I-Info/UGIG
                """);
        jTextArea.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 15));
        jTextArea.setEditable(false);
        jTextArea.setSize(300, 400);
        add(jTextArea);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(component);
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
