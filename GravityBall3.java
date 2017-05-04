package it.movioletto.GravityBall3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

//public class GravityBall3 implements ApplicationListener {
//	private OrthographicCamera camera;
//	private SpriteBatch batch;
//	private Texture texture;
//	private Sprite sprite;
//	
//	@Override
//	public void create() {		
//		float w = Gdx.graphics.getWidth();
//		float h = Gdx.graphics.getHeight();
//		
//		camera = new OrthographicCamera(1, h/w);
//		batch = new SpriteBatch();
//		
//		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
//		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		
//		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
//		
//		sprite = new Sprite(region);
//		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
//		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
//		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
//	}
//
//	@Override
//	public void dispose() {
//		batch.dispose();
//		texture.dispose();
//	}
//
//	@Override
//	public void render() {		
//		Gdx.gl.glClearColor(1, 1, 1, 1);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		
//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//		sprite.draw(batch);
//		batch.end();
//	}
//
//	@Override
//	public void resize(int width, int height) {
//	}
//
//	@Override
//	public void pause() {
//	}
//
//	@Override
//	public void resume() {
//	}
//}

public class GravityBall3 extends Game
{

	boolean firstTimeCreate = true;
	private static int schermata=0;
	public static Game game;
	
	public static int getSchermata()
	{
		return schermata;
	}

	public static void setSchermata(int schermata)
	{
		GravityBall3.schermata = schermata;
	}

	@Override
	public void create()
	{
		game=this;
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);

		setScreen(new SchermataInizializzazione(this));
	}

	@Override
	public void render()
	{
		super.render();
	}

	@Override
	public void dispose()
	{
		super.dispose();
		getScreen().dispose();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}
	
	@Override
	public void resume()
	{
	}
}
