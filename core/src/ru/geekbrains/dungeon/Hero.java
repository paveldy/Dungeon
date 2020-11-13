package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Vector2 position;
    private Vector2 velocity;
    private TextureRegion texture;
    private ProjectileController projectileController;
    private boolean mode;
    private float angle;
    private float a;
    private float speed;
    private boolean direction;
    private Projectile projectile;

    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(20, 20);
        this.speed = 40.0f;
        this.direction = true;
        this.velocity = new Vector2(1 * speed, 0);
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
        this.mode = false;
        this.angle = 0.0f;
        this.a = 0.0f;

    }
        // опредяляю в каую сторону мне надо двигаться
    public void updateAngel(float angle) {
        a = angle % 360;
        if (a == 0 || a == -0) {
            velocity.set(1 * speed, 0);
        }
        if (a == 180 || a == -180) {
            velocity.set(-1 * speed, 0);
        }
        if (a == 90 || a == -270) {
            velocity.set(0, 1 * speed);
        }
        if (a == 270 || a == -90) {
            velocity.set(0, -1 * speed);
        }
    }

    public void update(float dt) {
        //+++++++++++++++++++++++++++++
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (direction) angle -= 90.0f;
            if (!direction) angle += 90.0f;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (direction) angle += 90.0f;
            if (!direction) angle -= 90.0f;
        }
        updateAngel(angle);

        //++++++++++++++++++++++++++++++
        // ходит по клеткам, но без привязки к GameMap
        // не смог понять как сделать, чтобы вылетало две пули , но первая вылетала из то же точки, что и вторая(т.е. из
        // самого танка)

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)/*Gdx.input.isKeyPressed(Input.Keys.W)*/) {
            direction = true;
//            position.mulAdd(velocity, dt); // for Gdx.input.isKeyPressed(Input.Keys.W) плавный ход
            position.add(velocity);
            checkCoordinat(position);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)  /*Gdx.input.isKeyPressed(Input.Keys.S)*/) {
            direction = false;
//            position.mulAdd(velocity, -dt); // for Gdx.input.isKeyPressed(Input.Keys.S)  плавный ход
            position.mulAdd(velocity,-1);
            checkCoordinat(position);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            if (mode == false) {
                mode = true;
            } else if (mode == true) {
                mode = false;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && mode == true) {
            projectileController.activate(position.x, position.y, velocity.x * 5, velocity.y * 5);
            projectileController.activate(position.x + velocity.x * 0.4f, position.y + velocity.y * 0.4f, velocity.x * 5, velocity.y * 5);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && mode == false) {
            projectileController.activate(position.x, position.y, velocity.x * 5, velocity.y * 5);
        }

    }

    public void checkCoordinat(Vector2 position) {
        if (position.x > 1280) {
            position.x = 1280;
        }
        if (position.x < 0) {
            position.x = 0;
        }
        if (position.y > 720) {
            position.y = 720;
        }
        if (position.y < 0) {
            position.y = 0;
        }

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 20, position.y - 20, 20, 20, 40, 40, 1, 1, angle);
    }
}
