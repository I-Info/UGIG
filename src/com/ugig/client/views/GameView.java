package com.ugig.client.views;

import com.ugig.modles.GameEngine;
import com.ugig.modles.GameGuessResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * the main game window
 * only can be construct once
 * which means cannot modify after constructed
 * it will try to show previous window then dispose itself,
 * as this window is in closing
 */
public class GameView extends JFrame {

    private static final int DEFAULT_WINDOW_WIDTH = 400;
    private static final int DEFAULT_WINDOW_HEIGHT = 300;

    private final JTextField inputField;
    private final GameEngine game;
    private final JLabel resultLabel;
    private final JLabel timeLabel;
    private final DefaultListModel<String> listModel;
    private final JButton button;
    private final int maxGuessTimes;
    private final Timer gameTimer;
    private boolean startGame;
    private int remainTime;
    private final int maxTime;
    private final JLabel remainTimeLabel;


    /**
     * @param title         the window's title
     * @param previousView  previous window
     * @param maxGuessTimes set the max number of guess
     * @param maxTime       set the max guess time(s)
     *                      set <= 0 to disable timer.
     * @throws IllegalArgumentException illegal max guess time
     */
    public GameView(String title, JFrame previousView, int maxGuessTimes, int maxTime) throws IllegalArgumentException {
        super(title);
        this.remainTime = maxTime;
        this.maxTime = maxTime;
        if (maxGuessTimes < 1)
            throw new IllegalArgumentException();
        this.maxGuessTimes = maxGuessTimes;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        SpringLayout layout = new SpringLayout();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - DEFAULT_WINDOW_WIDTH / 2;
        int y = screenSize.height / 2 - DEFAULT_WINDOW_HEIGHT / 2;
        setBounds(x, y, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        setMinimumSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));

        Container container = getContentPane();
        container.setLayout(layout);

        //add closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (JOptionPane.showConfirmDialog(container, "Confirm exit?", "Exit", JOptionPane.YES_NO_OPTION)
                        == JOptionPane.YES_OPTION) {
                    previousView.setVisible(true);
                    gameTimer.stop();
                    resetGame();
                    dispose();
                }
            }
        });


        JLabel startLabel = new JLabel("You Guess I Guess");
        container.add(startLabel);
        layout.putConstraint(SpringLayout.NORTH, startLabel, 20, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.WEST, startLabel, 20, SpringLayout.WEST, container);

        timeLabel = new JLabel("", JLabel.CENTER);
        container.add(timeLabel);
        layout.putConstraint(SpringLayout.NORTH, timeLabel, 20, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.WEST, timeLabel, 20, SpringLayout.WEST, startLabel);
        layout.putConstraint(SpringLayout.EAST, timeLabel, -20, SpringLayout.EAST, container);


        inputField = new JTextField();
        container.add(inputField);
        layout.putConstraint(SpringLayout.NORTH, inputField, 30, SpringLayout.NORTH, startLabel);
        layout.putConstraint(SpringLayout.WEST, inputField, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, inputField, -20, SpringLayout.EAST, container);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER)
                    guessAction();
            }
        });

        button = new JButton("Guess!");
        button.addActionListener(e -> guessAction());
        container.add(button);
        layout.putConstraint(SpringLayout.NORTH, button, 30, SpringLayout.NORTH, inputField);
        layout.putConstraint(SpringLayout.WEST, button, 20, SpringLayout.WEST, container);

        remainTimeLabel = new JLabel();
        remainTimeLabel.setBounds(300, 200, 10, 10);
        this.add(remainTimeLabel);

        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainTime > 0) {
                    remainTimeLabel.setText("Your remainTime: " + remainTime-- + " s");
                } else {
                    resultLabel.setText("Game over. The secret number: " + game.getSecretNumber());
                    button.setEnabled(false);
                    remainTimeLabel.setText("");
                }
            }
        });

        JButton button = new JButton("Restart");
        button.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(container, "Confirm restart?", "Restart", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION)
                resetGame();
        });
        container.add(button);
        layout.putConstraint(SpringLayout.NORTH, button, 30, SpringLayout.NORTH, inputField);
        layout.putConstraint(SpringLayout.EAST, button, -20, SpringLayout.EAST, container);


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
        game = new GameEngine(maxGuessTimes);


    }

    /**
     * a basic guess action
     */
    private void guessAction() {

        if (!button.isEnabled())
            return;

        GameGuessResult result;
        try {
            int guessNumber = Integer.parseInt(inputField.getText());
            result = game.guess(guessNumber);
        } catch (Exception e) {
            resultLabel.setText("Invalid input");
            return;
        }
        inputField.setText("");
        assert (result != null);
        String text;
        switch (result.getCode()) {
            case GameGuessResult.OK -> {
                text = game.getGuessedTimes() + ". " + result;
                listModel.add(0, text);
                timeLabel.setText("Guess Remain: " + (maxGuessTimes - game.getGuessedTimes()));
                if (!startGame && maxTime > 0) {
                    startGame = true;
                    gameTimer.start();
                }
            }
            case GameGuessResult.SUCCESS -> {
                text = "You Win! The secret number: " + result.getNumber();
                timeLabel.setText("");
                gameTimer.stop();
                remainTimeLabel.setText("");
                button.setEnabled(false);
            }
            case GameGuessResult.FAIL -> {
                text = "Game over. The secret number: " + game.getSecretNumber();
                timeLabel.setText("");
                gameTimer.stop();
                remainTimeLabel.setText("");
                button.setEnabled(false);
            }
            case GameGuessResult.DUPLICATE -> text = "Already guessed: " + result;
            default -> throw new IllegalStateException("Unexpected value: " + result.getCode());
        }
        resultLabel.setText(text);
    }

    /**
     * restart the game
     */
    private void resetGame() {
        game.restart(maxGuessTimes);
        listModel.clear();
        resultLabel.setText("");
        timeLabel.setText("");
        inputField.setText("");
        button.setEnabled(true);
        gameTimer.stop();
        startGame = false;
        remainTime = maxTime;
        remainTimeLabel.setText("");


    }

}
