package com.ugig.client.views;

import javax.swing.*;
import java.awt.*;

public class RuleDialog extends JDialog {
    public RuleDialog(Component component) {
        JTextArea jTextArea = new JTextArea("""
                这是一个猜数字的游戏，系统会自动给你生成一个四位数字
                且各个位数上的数字各不相同，
                然后你输入一个四位数字，若猜中一个数字但位置错误，
                你获得1B（两个猜对则获得2B，两个以上同理），
                猜中数字且位置正确你获得1A（两个猜对则获得2A，两个以上同理）；
                若输出（1A1B，表示你猜中了一个数字位置和数字都对，
                另一个数字对但位置不对，剩下两个数字肯定不对）
                ，然后你可以继续猜测，指导全部猜对为止。""");
        jTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jTextArea.setLayout(new FlowLayout());
        jTextArea.setEditable(false);
        this.add(jTextArea);
        setResizable(false);
        setVisible(true);
        setSize(650, 400);
        setLocationRelativeTo(component);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}