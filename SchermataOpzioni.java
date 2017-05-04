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
 * schermata di opzioni
 * @author Movioletto
 */
public class SchermataOpzioni implements Screen
{
	private static Bottone[] bottoni;
	private static Mondo mondo;
	private TestoSpecial testoSpecial;
	private static Testo[] testi;
	private Bordo bordo;
	
	private OrthographicCamera camera;
	private SpriteBatch sprite;
	private static Game game;
	private Vector3 touch;
	private Sfondo sfondo;
	private boolean ruotato;
	private boolean dispose;
	
	/**
	 * crea la schermata e tutte le sue componenti
	 * @param surf layer dove disegnare
	 */
	public SchermataOpzioni(Game game)
	{
		this.dispose=false;
        Gdx.input.setCatchBackKey(true);
		SchermataOpzioni.game=game;
		this.camera= new OrthographicCamera(Cost.SCHERMOWIDTH, Cost.SCHERMOHEIGHT);
		this.camera.position.set(Cost.SCHERMOWIDTH/2, Cost.SCHERMOHEIGHT/2, 0);
		this.touch=new Vector3();
		this.sprite= new SpriteBatch();
		this.sfondo=new Sfondo(Tipo.BG);
		this.ruotato=false;
		mondo=new Mondo();
		this.testoSpecial= new TestoSpecial(Tipo.TESTO70, "opzioni!", new Vec2(Cost.SCHERMOWIDTH/2.0f, 700.0f), mondo);
		Bottone[] botton={
							new Bottone(Tipo.EASYC, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[5].getWidth()/2.0f)-(Cost.texture[6].getWidth()/2.0f), 550.0f), mondo, 4, game),
							new Bottone(Tipo.MEDIUM, new Vec2(Cost.SCHERMOWIDTH/2.0f, 550.0f), mondo, 5, game),
							new Bottone(Tipo.HARD, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[5].getWidth()/2.0f)+(Cost.texture[6].getWidth()/2.0f), 550.0f), mondo, 6, game),
							
							new Bottone(Tipo.BONUSM, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f)-(Cost.texture[10].getWidth()/2.0f)-(Cost.texture[10].getWidth()/2.0f), 450.0f), mondo, 8, game),
							new Bottone(Tipo.BONUSPMC, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 450.0f), mondo, 9, game),
							new Bottone(Tipo.BONUSP, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f)+(Cost.texture[10].getWidth()/2.0f)+(Cost.texture[10].getWidth()/2.0f), 450.0f), mondo, 10, game),
							
							new Bottone(Tipo.FRECCIADESTRA, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f)+Cost.font100m.x*4, 370.0f), mondo, 35, game),

							new Bottone(Tipo.MUSICA, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 290.0f), mondo, 11, game),
							
							new Bottone(Tipo.MUSICA, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 210.0f), mondo, 39, game),
							
							new Bottone(Tipo.TESTO70, "indietro", new Vec2(Cost.SCHERMOWIDTH/4.0f, 170.0f), mondo, 7, game)
							};
		bottoni=botton;
		Testo[] test={
						new Testo(Tipo.TESTO70, "bonus:", new Vec2(Cost.SCHERMOWIDTH/4.0f, 470.0f)),
						new Testo(Tipo.TESTO70, "input:", new Vec2(Cost.SCHERMOWIDTH/4.0f, 390.0f)),
						new Testo(Tipo.TESTO70, "touch", new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 390.0f)),
						new Testo(Tipo.TESTO70, "musica:", new Vec2(Cost.SCHERMOWIDTH/4.0f, 310.0f)),
						new Testo(Tipo.TESTO70, "rotazione:", new Vec2(Cost.SCHERMOWIDTH/4.0f, 230.0f))
						};	
		testi=test;
		this.bordo=new Bordo(mondo);
		aggiornaTasti();
	}
	
	private static void aggiornaTasti()
	{
		Cost.caricaImpostazioni();
		if(Cost.VELOCITAINCREMENTO==500)
		{
			bottoni[0]=new Bottone(Tipo.EASYC, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[5].getWidth()/2.0f)-(Cost.texture[6].getWidth()/2.0f), 550.0f), mondo, 4, game);
			bottoni[1]=new Bottone(Tipo.MEDIUM, new Vec2(Cost.SCHERMOWIDTH/2.0f, 550.0f), mondo, 5, game);
			bottoni[2]=new Bottone(Tipo.HARD, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[5].getWidth()/2.0f)+(Cost.texture[6].getWidth()/2.0f), 550.0f), mondo, 6, game);
		}
		else if(Cost.VELOCITAINCREMENTO==400)
		{
			bottoni[0]=new Bottone(Tipo.EASY, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[5].getWidth()/2.0f)-(Cost.texture[6].getWidth()/2.0f), 550.0f), mondo, 4, game);
			bottoni[1]=new Bottone(Tipo.MEDIUMC, new Vec2(Cost.SCHERMOWIDTH/2.0f, 550.0f), mondo, 5, game);
			bottoni[2]=new Bottone(Tipo.HARD, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[5].getWidth()/2.0f)+(Cost.texture[6].getWidth()/2.0f), 550.0f), mondo, 6, game);
		}
		else
		{
			bottoni[0]=new Bottone(Tipo.EASY, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[5].getWidth()/2.0f)-(Cost.texture[6].getWidth()/2.0f), 550.0f), mondo, 4, game);
			bottoni[1]=new Bottone(Tipo.MEDIUM, new Vec2(Cost.SCHERMOWIDTH/2.0f, 550.0f), mondo, 5, game);
			bottoni[2]=new Bottone(Tipo.HARDC, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[5].getWidth()/2.0f)+(Cost.texture[6].getWidth()/2.0f), 550.0f), mondo, 6, game);
		}
		
		if(Cost.VELOCITAINCREMENTOBONUS==700)
		{
			bottoni[3]=new Bottone(Tipo.BONUSMC, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f)-(Cost.texture[10].getWidth()/2.0f)-(Cost.texture[10].getWidth()/2.0f), 450.0f), mondo, 8, game);
			bottoni[4]=new Bottone(Tipo.BONUSPM, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 450.0f), mondo, 9, game);
			bottoni[5]=new Bottone(Tipo.BONUSP, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f)+(Cost.texture[10].getWidth()/2.0f)+(Cost.texture[10].getWidth()/2.0f), 450.0f), mondo, 10, game);
		}
		else if(Cost.VELOCITAINCREMENTOBONUS==500)
		{
			bottoni[3]=new Bottone(Tipo.BONUSM, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f)-(Cost.texture[10].getWidth()/2.0f)-(Cost.texture[10].getWidth()/2.0f), 450.0f), mondo, 8, game);
			bottoni[4]=new Bottone(Tipo.BONUSPMC, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 450.0f), mondo, 9, game);
			bottoni[5]=new Bottone(Tipo.BONUSP, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f)+(Cost.texture[10].getWidth()/2.0f)+(Cost.texture[10].getWidth()/2.0f), 450.0f), mondo, 10, game);
		}
		else
		{
			bottoni[3]=new Bottone(Tipo.BONUSM, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f)-(Cost.texture[10].getWidth()/2.0f)-(Cost.texture[10].getWidth()/2.0f), 450.0f), mondo, 8, game);
			bottoni[4]=new Bottone(Tipo.BONUSPM, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 450.0f), mondo, 9, game);
			bottoni[5]=new Bottone(Tipo.BONUSPC, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f)+(Cost.texture[10].getWidth()/2.0f)+(Cost.texture[10].getWidth()/2.0f), 450.0f), mondo, 10, game);
		}
		
		if(Cost.touch)
			testi[2]= new Testo(Tipo.TESTO70, "touch", new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 390.0f));
		else if(Cost.accel)
			testi[2]= new Testo(Tipo.TESTO70, "accel", new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 390.0f));
		if(Cost.touch && Cost.accel)
			testi[2]= new Testo(Tipo.TESTO70, "mix", new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 390.0f));
			

		if(Cost.MUSICA)
			bottoni[7]=new Bottone(Tipo.MUSICAC, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 290.0f), mondo, 12, game);
		else
			bottoni[7]=new Bottone(Tipo.MUSICA, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 290.0f), mondo, 11, game);
		
		if(Cost.rotazione)
			bottoni[8]=new Bottone(Tipo.MUSICAC, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 210.0f), mondo, 40, game);
		else
			bottoni[8]=new Bottone(Tipo.MUSICA, new Vec2(Cost.SCHERMOWIDTH-(Cost.SCHERMOWIDTH/4.0f), 210.0f), mondo, 39, game);
				
	}

	/**
	 * cambia l'interfaccia grafica in base al pulsante premuto
	 * @param i pulsante premuto
	 */
	public static void cambiaBottone(int i)
	{
		Cost.salvaImpostazioni();
		aggiornaTasti();
	}

	/**
	 * cambia l'interfaccia grafica in base al pulsante premuto
	 * @param i pulsante premuto
	 */
	public static Testo getTesto(int i)
	{
		return testi[i];
	}
	
	/**
	 * aggiorno tutte le componenti
	 * @param ms lo stato del mouse
	 */
	public void update() 
	{
		if(Gdx.input.isKeyPressed(Keys.BACK))
			Cost.funzioniBottoni(7, game);
		mondo.step();
		this.testoSpecial.update();
		camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		for(Bottone b: bottoni)
			b.update(touch);
	}
	
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
		for(Bottone b: bottoni)
			b.paint(sprite);
		for(Testo t: testi)
			t.paint(sprite);
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
		testoSpecial=null;
		testi=null;
		bordo=null;
		
		camera=null;
		sprite.dispose();
		touch=null;
		sfondo=null;
	}
}