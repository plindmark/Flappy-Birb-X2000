package se.yrgo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.game.sprites.Birb;
import se.yrgo.game.sprites.Obstacle;

public class JumpyBirb implements Screen {
    private JumpyBirbGame game;
    private SpriteBatch batch;
    private Birb birb;
    private Texture gameBackground;
    private Array<Obstacle> obstacles;
    private int score;
    private final int spaceBetweenObstacles = 500;
    private final int numberOfObstacles = 4;

    public JumpyBirb() {

    }

    public JumpyBirb(JumpyBirbGame game) {
        this.game = game;
        batch = new SpriteBatch();
        birb = new Birb();
        birb.create();
        gameBackground = new Texture(Gdx.files.internal("NewBackground.png"));
        obstacles = new Array<Obstacle>();

        for (int i = 1; i <= numberOfObstacles; i++) {
            obstacles.add(new Obstacle(i * spaceBetweenObstacles + Obstacle.width));
        }
    }

    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        handleUserInput();

        batch.begin();
        batch.draw(gameBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (Obstacle obstacle : obstacles) {
            batch.draw(obstacle.getImageTopObstacle(), obstacle.getTopObstaclePosition().x, obstacle.getTopObstaclePosition().y);
            batch.draw(obstacle.getImageBotObstacle(), obstacle.getBotObstaclePosition().x, obstacle.getBotObstaclePosition().y);
            obstacle.update();

            if (obstacle.getTopObstaclePosition().x == birb.getBirbPosition().x - 200) {
                score++;
            }

            if (obstacle.getTopObstaclePosition().x < -obstacle.getTopObstaclePosition().width) {
                obstacle.generateObstacleStartAndGapPosition((obstacle.getTopObstaclePosition().x - 200) + (Obstacle.width + spaceBetweenObstacles * numberOfObstacles));
            }

            if (hitsGround(birb) ||
                    birb.getBirbPosition().overlaps(obstacle.getTopObstaclePosition()) ||
                    birb.getBirbPosition().overlaps(obstacle.getBotObstaclePosition())) {

                birb.playDeathSound();
                game.setScreen(new EndMenuScreen(game, score));
            }
        }

        batch.draw(birb.getBirbImage(), birb.getBirbPosition().x, birb.getBirbPosition().y);
        birb.update();

        game.getSpritebatch().begin();
        game.getFont().draw(game.getSpritebatch(), Integer.toString(score), 100, 700);
        game.getSpritebatch().end();

        batch.end();
    }

    private void handleUserInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched()) {
            birb.jump();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched()) {
            birb.initiateGravity();
        }
    }

    private static boolean hitsGround(Birb birb) {
        return birb.getBirbPosition().y < -20;
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        birb.dispose();
        for (Obstacle obstacle : obstacles) {
            obstacle.dispose();
        }
    }
}
