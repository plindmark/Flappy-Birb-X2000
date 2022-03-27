package se.yrgo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    private final JumpyBirbGame game;
    private final Texture startBackground, imageEasy, imageMedium, imageHard;
    private final Rectangle positionEasyImage, positionMediumImage, positionHardImage;
    private final Vector2 touchImage;
    public static String difficulty = "No difficulty set";

    public MainMenuScreen(JumpyBirbGame game) {
        this.game = game;
        this.startBackground = new Texture(Gdx.files.internal("NewBackground.png"));
        this.imageEasy = new Texture(Gdx.files.internal("EASY.png"));
        this.imageMedium = new Texture(Gdx.files.internal("MEDIUM.png"));
        this.imageHard = new Texture(Gdx.files.internal("HARD.png"));
        this.positionEasyImage = new Rectangle(50, 250, imageEasy.getWidth(), imageEasy.getHeight());
        this.positionMediumImage = new Rectangle(300, 250, imageMedium.getWidth(), imageMedium.getHeight());
        this.positionHardImage = new Rectangle(550, 250, imageHard.getWidth(), imageHard.getHeight());
        this.touchImage = new Vector2();
        Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("man-is-he-mega.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f);
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        game.getSpritebatch().begin();
        game.getSpritebatch().draw(startBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getFont().draw(game.getSpritebatch(), "Jumpy Birb X2000", 300, 700);
        drawDifficultyImages();
        game.getFont().draw(game.getSpritebatch(), "Press space to begin", 300, 100);
        chooseDifficulty();
        game.getFont().draw(game.getSpritebatch(), difficulty, 300, 200);
        game.getSpritebatch().end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new JumpyBirb(game));
            dispose();
        }
    }

    public void chooseDifficulty() {
        if (Gdx.input.justTouched()) {
            touchImage.set(Gdx.input.getX(), Gdx.input.getY());
            if (positionEasyImage.contains(touchImage.x, touchImage.y - positionEasyImage.getHeight() / 2)) {
                JumpyBirb.spaceBetweenObstacles = 700;
                difficulty = "EASY";
            } else if (positionMediumImage.contains(touchImage.x, touchImage.y - positionMediumImage.getHeight() / 2)) {
                JumpyBirb.spaceBetweenObstacles = 600;
                difficulty = "MEDIUM";
            } else if (positionHardImage.contains(touchImage.x, touchImage.y - positionHardImage.getHeight() / 2)) {
                JumpyBirb.spaceBetweenObstacles = 500;
                difficulty = "HARD";
            }
        }
    }

    public void drawDifficultyImages() {
        game.getFont().draw(game.getSpritebatch(), "EASY", 125, 500);
        game.getFont().draw(game.getSpritebatch(), "MEDIUM", 375, 500);
        game.getFont().draw(game.getSpritebatch(), "HARD", 625, 500);
        game.getSpritebatch().draw(imageEasy, positionEasyImage.x, positionEasyImage.y);
        game.getSpritebatch().draw(imageMedium, positionMediumImage.x, positionMediumImage.y);
        game.getSpritebatch().draw(imageHard, positionHardImage.x, positionHardImage.y);
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
        startBackground.dispose();
    }
}
