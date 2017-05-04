package it.movioletto.GravityBall3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Schermata che spiega i tasti del single player
 * @author Movioletto
 */
public class SchermataInizializzazione implements Screen
{
	private OrthographicCamera camera;
	private SpriteBatch sprite;
	private BitmapFont font100;
	private Game game;
	private float delt;
	private boolean ruotato;
	private boolean dispose;
	
	/**
	 * creo schermata e tutti i suoi componenti
	 * @param surf layer dove disegnare
	 */
	public SchermataInizializzazione(Game game)
	{
		this.dispose=false;
		this.delt=0;
		this.game=game;
		this.ruotato=false;
		this.camera= new OrthographicCamera(Cost.SCHERMOWIDTH, Cost.SCHERMOHEIGHT);
		this.camera.position.set(Cost.SCHERMOWIDTH/2, Cost.SCHERMOHEIGHT/2, 0);
		this.sprite= new SpriteBatch();
		this.font100 = new BitmapFont(Gdx.files.internal("font/font100.fnt"), Gdx.files.internal("font/font100B.png"), false);
	}
	
	/**
	 * aggiorna tutte le componenti
	 * @param ms lo stato del mouse
	 */
	public void update(float delta) 
	{
		Gdx.app.log("CARICO", "CARICO IMMAGINI");
		
		if(delt>=delta)
		{
			if(Cost.caricaImmagini())
				game.setScreen(new SchermataIniziale(game));
		}
		else
			delt++;
		
	}
	
	/**
	 * disegno tutte le componenti
	 * @param surf il layer dove disegnare
	 */
	public void paint()
	{
		Gdx.app.log("disegno", "disegno CARICANDO");
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(Cost.rotazione && !this.ruotato)
		{
			this.camera.rotate(180);
			this.ruotato=true;
		}
		else if(!Cost.rotazione && this.ruotato)
		{
			this.camera.rotate(180);
			this.ruotato=false;
		}
		camera.update();
		sprite.setProjectionMatrix(camera.combined);

		sprite.enableBlending();
		sprite.begin();

		font100.draw(sprite, "caricamento!", Cost.SCHERMOWIDTH/2-Cost.font100m.x*6, Cost.SCHERMOHEIGHT/2-Cost.font100m.y/2);
		sprite.end();
	}		

	@Override
	public void render(float delta)
	{
		if(!dispose)
		{
			paint();
			update(delta);
		}
	}

	@Override
	public void resize(int width, int height)
	{
		
	}

	@Override
	public void show()
	{
		paint();
	}

	@Override
	public void hide()
	{
		dispose();
	}

	@Override
	public void pause()
	{
		dispose();
	}

	@Override
	public void resume()
	{
		
	}

	@Override
	public void dispose()
	{
		dispose=true;
		camera=null;
		sprite.dispose();
		font100=null;
	}
}