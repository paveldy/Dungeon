package ru.geekbrains.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameMap {

    public static final int CELLS_X = 32;
    public static final int CELLS_Y = 18;
    private byte[][] data;
    private TextureRegion grassTextur;

    public GameMap(TextureAtlas atlas) {
        this.grassTextur = atlas.findRegion("grass40");
        this.data = new byte[CELLS_X][CELLS_Y];

    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = 0; j < CELLS_Y; j++) {
                batch.draw(grassTextur, i * 40, j * 40);

            }

        }
    }
}
