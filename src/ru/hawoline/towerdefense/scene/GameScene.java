package ru.hawoline.towerdefense.scene;

import ru.hawoline.towerdefense.Game;

import java.awt.*;

public abstract class GameScene implements SceneMethod {
    private Game game;

    public GameScene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
