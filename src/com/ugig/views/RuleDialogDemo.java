package com.ugig.views;
import javax.swing.*;
import java.awt.*;
public class RuleDialogDemo extends JDialog {
    public RuleDialogDemo() {
      JTextArea jTextArea = new JTextArea("这是一个猜数字的游戏，" +
                "系统会自动给你生成一个四位数字"+"\n"+"且各个位数上的数字各不相同，" +"\n"+
                "然后你输入一个四位数字，若猜中一个数字但位置错误，" +"\n"+
                "你获得1B（两个猜对则获得2B，两个以上同理），" +"\n"+
                "猜中数字且位置正确你获得1A（两个猜对则获得2A，两个以上同理）；" +"\n"+
                "若输出（1A1B，表示你猜中了一个数字位置和数字都对，"+"\n"+"另一个数字对但位置不对，剩下两个数字肯定不对）" +"\n"+

                "，然后你可以继续猜测，指导全部猜对为止。");
        jTextArea.setFont(new Font("微软雅黑", 0, 20));
        jTextArea.setLayout(new FlowLayout());
        this.add(jTextArea);
        setResizable(false);
        setVisible(true);
        setBounds(1000, 200, 650, 600);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
}