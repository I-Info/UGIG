package GUI_UGIG;

import dev.iinfo.ugig.modles.GameCore;
import dev.iinfo.ugig.modles.GameGuessResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author qtl2021
 */
public class UGIG_StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("UGIG");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(null);

        JLabel label1 = new JLabel();
        label1.setBounds(900,100,600,400);
        label1.setFont(new Font("微软雅黑",Font.BOLD,80));
        label1.setText("Let's Start!");

        JLabel label2 = new JLabel();
        label2.setBounds(600,300,1500,200);
        label2.setFont(new Font("微软雅黑",Font.BOLD,80));
        label2.setText("It's a four digit integer: ");

        JTextField field = new JTextField();
        field.setBounds(1600,360,200,100);
        field.setFont(new Font("微软雅黑",Font.BOLD,60));

        JButton button = new JButton();
        button.setBackground(Color.WHITE);
        button.setBounds(900,500,400,200);
        button.setFont(new Font("微软雅黑",Font.BOLD,80));
        button.setText("I guess");

        JLabel label3 = new JLabel("",JLabel.CENTER);
        label3.setBounds(100,600,2000,400);
        label3.setFont(new Font("微软雅黑",Font.BOLD,60));

        JLabel label4 = new JLabel("",JLabel.CENTER);
        label4.setBounds(700,400,2000,400);
        label4.setFont(new Font("微软雅黑",Font.BOLD,60));

        JList list = new JList();
        JScrollPane scrollPane = new JScrollPane(list);
        DefaultListModel listModel = new DefaultListModel();
        list.setFont(new Font("微软雅黑",Font.BOLD,60));
        scrollPane.setBounds(500,900,1200,300);

        frame.add(label1);
        frame.add(label2);
        frame.add(field);
        frame.add(button);
        frame.add(label3);
        frame.add(label4);
        frame.add(scrollPane);

        field.setFocusable(true);

        frame.setBounds(550,350,2200,1500);
        frame.setResizable(false);
        frame.setVisible(true);

        GameCore gameCore = new GameCore(1000);

        class MyMouseListener extends MouseAdapter{
            @Override
            public void mousePressed(MouseEvent m) {

                try {
                    GameGuessResult result = gameCore.guess(Integer.parseInt(field.getText()));
                    label3.setText(result.toString());

                    if (result.getCode() == GameGuessResult.DUPLICATE){
                        label3.setText("Already guessed"+"   "+result.toString());
                    }
                    if (result.getCode() == GameGuessResult.OK) {
                        listModel.add(0,result.toString());
                        label4.setText("Guess time: " + gameCore.getGuessedTimes());
                    }
                    if (result.getCode() == GameGuessResult.SUCCESS) {
                        label3.setText("You win!");
                    }
                    field.setText("");
                    button.setFocusable(false);
                }
                catch (Exception e){
                    label3.setText("illegal input");
                    field.setText("");
                    button.setFocusable(false);
                }
            }
        }
        list.setModel(listModel);
        button.addMouseListener(new MyMouseListener());

        class MyKeyListener extends KeyAdapter{
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER) {
                    try {
                        GameGuessResult result = gameCore.guess(Integer.parseInt(field.getText()));
                        label3.setText(result.toString());

                        if (result.getCode() == GameGuessResult.DUPLICATE) {
                            label3.setText("Already guessed"+"   "+result.toString());
                        }
                        if (result.getCode() == GameGuessResult.OK) {
                            listModel.add(0,result.toString());
                            label4.setText("Guess time: " + gameCore.getGuessedTimes());
                        }
                        if (result.getCode() == GameGuessResult.SUCCESS) {
                            label3.setText("You win!");
                        }
                        field.setText("");
                    } catch (Exception h) {
                        label3.setText("illegal input");
                        field.setText("");
                    }
                }
            }
        }
        list.setModel(listModel);
        field.addKeyListener(new MyKeyListener());
    }
}

