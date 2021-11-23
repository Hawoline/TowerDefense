package ru.hawoline.towerdefense.scene;

import ru.hawoline.towerdefense.Game;

public abstract class GameScene implements SceneMethod {
    private Game game;

    public GameScene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void update() {

    }
}
