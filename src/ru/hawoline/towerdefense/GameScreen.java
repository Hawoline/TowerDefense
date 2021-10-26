package ru.hawoline.towerdefense;

import ru.hawoline.towerdefense.listener.KeyBoardListener;
import ru.hawoline.towerdefense.listener.MyMouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;
    private Dimension size;
    private MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
        initInputs();
    }

    private void setPanelSize() {
        size = new Dimension(640, 740);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        game.getRender().render(graphics);
    }

    private void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyBoardListener = new KeyBoardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyBoardListener);

        requestFocus();
    }
}
