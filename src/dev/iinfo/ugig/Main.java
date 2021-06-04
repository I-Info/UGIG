package dev.iinfo.ugig;

import java.awt.*;
import javax.swing.*;

public class Main {

    private static final int DEFAULT_WINDOW_WIDTH = 400;
    private static final int DEFAULT_WINDOW_HEIGHT = 300;

    public static void main(String[] args) {
        JFrame mainWindow = new JFrame();
        mainWindow.setLayout(null);
        // Some default settings
        mainWindow.setTitle("UGIG-Game");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - DEFAULT_WINDOW_WIDTH / 2;
        int y = screenSize.height / 2 - DEFAULT_WINDOW_HEIGHT / 2;
        mainWindow.setBounds(x, y, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);

        JLabel jLabel1 = new JLabel("Hello", JLabel.CENTER);
        jLabel1.setBounds(150, 1, 100, 20);
        jLabel1.setFont(new Font("", Font.BOLD, 20));
        mainWindow.add(jLabel1);

        mainWindow.setVisible(true);
    }
}
