package it.movioletto.GravityBall3;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Schermata iniziale, menù principale
 * @author Movioletto
 */
public class SchermataIniziale implements Screen
{
	private OrthographicCamera camera;
	private SpriteBatch sprite;
	private Sfondo sfondo;
	private Vector3 touch;
	private Bottone[] bottoni;
	private Mondo mondo;
	private Bordo bordo;
	private TestoSpecial testoSpecial;
	private Game game;
	private boolean ruotato;
	private boolean dispose;

	/**
	 * crea la schermata e tutte le sue componenti 
	 * @param surf il layer dove disegnare
	 */
	public SchermataIniziale(Game game)
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
		this.testoSpecial= new TestoSpecial(Tipo.TESTO150, "gravityball!", new Vec2(Cost.SCHERMOWIDTH/2.0f, 700.0f), this.mondo);
		Bottone[] botton={
							new Bottone(Tipo.TESTO70, "gioca", new Vec2(Cost.SCHERMOWIDTH/2.0f, 550.0f), this.mondo, 33, game),
							new Bottone(Tipo.TESTO70, "multigiocatore", new Vec2(Cost.SCHERMOWIDTH/2.0f, 480.0f), this.mondo, 34, game),
							new Bottone(Tipo.TESTO70, "opzioni", new Vec2(Cost.SCHERMOWIDTH/2.0f, 410.0f), this.mondo, 1, game),
							new Bottone(Tipo.TESTO70, "highscore", new Vec2(Cost.SCHERMOWIDTH/2.0f, 340.0f), this.mondo, 13, game),
							new Bottone(Tipo.TESTO70, "crediti", new Vec2(Cost.SCHERMOWIDTH/2.0f, 270.0f), this.mondo, 2, game),
							new Bottone(Tipo.TESTO70, "esci", new Vec2(Cost.SCHERMOWIDTH/2.0f, 200.0f), this.mondo, 3, game)
							};
		this.bottoni=botton;
		this.bordo=new Bordo(mondo);
		paint();
	}
	
	/**
	 * aggiorno tutte le componenti
	 * @param ms lo stato del mouse
	 */
	public void update() 
	{
		if(Gdx.input.isButtonPressed(Keys.BACK))
			Cost.funzioniBottoni(3, game);
		this.mondo.step();
		this.testoSpecial.update();
		this.camera.unproject(this.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		for(Bottone b: this.bottoni)
			b.update(touch);
	}
	
	/**
	 * disegno tutte le componenti
	 * @param surf layer dove disegnare
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
//
//		this.sprite.disableBlending();
//		this.sprite.begin();
//		this.sprite.end();

		this.sprite.enableBlending();
		this.sprite.begin();

		this.sfondo.paint(sprite);
		this.testoSpecial.paint(sprite);
		for(Bottone b: this.bottoni)
			b.paint(sprite);
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
		camera=null;
		sprite.dispose();
		sfondo=null;
		touch=null;
		bottoni=null;
		mondo=null;
		bordo=null;
		testoSpecial=null;
	}
}