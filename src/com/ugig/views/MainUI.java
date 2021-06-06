package com.ugig.views;

import com.ugig.modles.GameCore;
import com.ugig.modles.GameGuessResult;

import javax.swing.*;
import java.awt.*;

public class MainUI {

    private static final int DEFAULT_WINDOW_WIDTH = 400;
    private static final int DEFAULT_WINDOW_HEIGHT = 300;

    private final JTextField inputField;
    private final GameCore game;
    private final JLabel resultLabel;
    private final JLabel timeLabel;
    private final DefaultListModel<String> listModel;


    public MainUI() {
        JFrame mainWindow = new JFrame("UGIG");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout layout = new SpringLayout();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - DEFAULT_WINDOW_WIDTH / 2;
        int y = screenSize.height / 2 - DEFAULT_WINDOW_HEIGHT / 2;
        mainWindow.setBounds(x, y, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        mainWindow.setMinimumSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));

        Container container = mainWindow.getContentPane();
        container.setLayout(layout);

        JLabel startLabel = new JLabel("You Guess I Guess");
        container.add(startLabel);
        layout.putConstraint(SpringLayout.NORTH, startLabel, 20, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.WEST, startLabel, 20, SpringLayout.WEST, container);

        timeLabel = new JLabel("", JLabel.CENTER);
        container.add(timeLabel);
        layout.putConstraint(SpringLayout.NORTH, timeLabel, 20, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.WEST, timeLabel, 20, SpringLayout.WEST, startLabel);
        layout.putConstraint(SpringLayout.EAST, timeLabel, -20, SpringLayout.EAST, container);


        inputField = new JTextField("1111");
        container.add(inputField);
        layout.putConstraint(SpringLayout.NORTH, inputField, 30, SpringLayout.NORTH, startLabel);
        layout.putConstraint(SpringLayout.WEST, inputField, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, inputField, -20, SpringLayout.EAST, container);


        JButton button = new JButton("Guess!");
        container.add(button);
        layout.putConstraint(SpringLayout.NORTH, button, 30, SpringLayout.NORTH, inputField);
        layout.putConstraint(SpringLayout.WEST, button, 20, SpringLayout.WEST, container);

        resultLabel = new JLabel("", JLabel.CENTER);
        container.add(resultLabel);
        layout.putConstraint(SpringLayout.NORTH, resultLabel, 40, SpringLayout.NORTH, button);
        layout.putConstraint(SpringLayout.WEST, resultLabel, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, resultLabel, -25, SpringLayout.EAST, container);


        listModel = new DefaultListModel<>();
        JList<String> historyList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(historyList);
        historyList.setModel(listModel);
        container.add(scrollPane);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 30, SpringLayout.NORTH, resultLabel);
        layout.putConstraint(SpringLayout.WEST, scrollPane, 25, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, scrollPane, -25, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane, -25, SpringLayout.SOUTH, container);

        button.addActionListener(e -> guessAction());

        game = new GameCore(30);

        mainWindow.setVisible(true);
    }

    public void guessAction() {
        GameGuessResult result = null;
        try {
            int guessNumber = Integer.parseInt(inputField.getText());
            result = game.guess(guessNumber);
        } catch (Exception e) {
            resultLabel.setText("Invalid input");
            return;
        }
        assert (result != null);
        String text;
        switch (result.getCode()) {
            case GameGuessResult.OK -> {
                text = result.toString();
                listModel.add(0, text);
                timeLabel.setText("Guess time: "+game.getGuessedTimes());
            }
            case GameGuessResult.SUCCESS -> text = "You Win!The secret number: " + result.getNumber();
            case GameGuessResult.FAIL -> text = "Game over.";
            case GameGuessResult.DUPLICATE -> text = "Already guessed: " + result;
            default -> throw new IllegalStateException("Unexpected value: " + result.getCode());
        }
        resultLabel.setText(text);

    }

}
