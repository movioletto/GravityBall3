package it.movioletto.GravityBall3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * schermata istruzioni multiplayer
 * @author Movioletto
 */
public class SchermataIstruzioni2 implements Screen
{
	private Bottone bottoni;
//	private Immagine istruzioni;
	private Mondo mondo;
	private Bordo bordo;
	private Testo[] testi;

	private OrthographicCamera camera;
	private SpriteBatch sprite;
	private Sfondo sfondo;
	private Vector3 touch;
	private Game game;

	private ImmagineAnimata ist1;
	private ImmagineAnimata ist2;
	private boolean ruotato;
	private boolean dispose;
		
	/**
	 * creo schermata e tutti i suoi componenti
	 * @param surf layer dove disegnare
	 */
	public SchermataIstruzioni2(Game game)
	{
		this.dispose=false;
		this.game=game;
		this.camera= new OrthographicCamera(Cost.SCHERMOWIDTH, Cost.SCHERMOHEIGHT);
		this.camera.position.set(Cost.SCHERMOWIDTH/2, Cost.SCHERMOHEIGHT/2, 0);
		this.touch = new Vector3();
		this.sprite= new SpriteBatch();
		this.sfondo=new Sfondo(Tipo.BG);
		this.ruotato=false;
		this.mondo=new Mondo();
//		this.istruzioni=new Immagine(new Vec2(Cost.SCHERMOWIDTH/2, Cost.SCHERMOHEIGHT/2), Tipo.ISTRUZIONI1);
		
		Testo[] test={
			new Testo(Tipo.TESTO140, "istruzioni:", new Vec2(Cost.SCHERMOWIDTH/2, 700)),
			new Testo(Tipo.TESTO100, "scopo del gioco:", new Vec2(Cost.SCHERMOWIDTH/2-50, 620)),
			new Testo(Tipo.TESTO70, "non farti schiacciare", new Vec2(Cost.SCHERMOWIDTH/2, 560)),
			new Testo(Tipo.TESTO70, "dai muri e batti", new Vec2(Cost.SCHERMOWIDTH/2+15, 520)),
			new Testo(Tipo.TESTO70, "il tuo avversario!", new Vec2(Cost.SCHERMOWIDTH/2+30, 480)),
		};
		
		testi=test;
		this.ist1=new ImmagineAnimata(new Vec2(Cost.SCHERMOWIDTH/4, Cost.SCHERMOHEIGHT/4+50), 4);
		this.ist2=new ImmagineAnimata(new Vec2((Cost.SCHERMOWIDTH/4)*3, Cost.SCHERMOHEIGHT/4+50), 5);
		
		this.bottoni=new Bottone(Tipo.TESTO70, "gioca!", new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 75.0f), this.mondo, 32, game);
		this.bordo=new Bordo(this.mondo);
		paint();
	}
	
	/**
	 * aggiorna tutte le componenti
	 * @param ms lo stato del mouse
	 */
	public void update() 
	{
		if(Gdx.input.isKeyPressed(Keys.BACK))
			Cost.funzioniBottoni(7, game);
		this.mondo.step();
		this.camera.unproject(this.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		this.bottoni.update(touch);
	}
	
	/**
	 * disegno tutte le componenti
	 * @param surf il layer dove disegnare
	 */
	public void paint()
	{
		GLCommon gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
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
		this.camera.update();
		this.sprite.setProjectionMatrix(camera.combined);

		this.sprite.enableBlending();
		this.sprite.begin();

		this.sfondo.paint(sprite);
//		this.istruzioni.paint(sprite);
		
		for(Testo t:testi)
			t.paint(sprite);
		
		this.ist1.paint(sprite);
		this.ist2.paint(sprite);

		this.bottoni.paint(sprite);
		this.bordo.paint(sprite);
		sprite.end();
	}	

	@Override
	public void render(float delta)
	{
		if(!dispose)
		{
			update();
			paint();
		}
	}

	@Override
	public void resize(int width, int height)
	{
		
	}

	@Override
	public void show()
	{
		
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
		bottoni=null;
		mondo=null;
		bordo=null;
		testi=null;

		camera=null;
		sprite.dispose();
		sfondo=null;
		touch=null;

		ist1=null;
		ist2=null;
	}
			
}