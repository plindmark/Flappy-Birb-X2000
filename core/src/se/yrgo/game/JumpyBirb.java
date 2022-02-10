package se.yrgo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.game.sprites.Birb;

public class JumpyBirb extends ApplicationAdapter {
	private SpriteBatch batch;
	private Birb birb;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		birb = new Birb();
		birb.create();

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();
		batch.draw(birb.getImg(), birb.getPosition().x, birb.getPosition().y);

		birb.update();
		birb.jump();

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
