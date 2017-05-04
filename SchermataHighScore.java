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
 * Schermata dei punteggi
 * @author Movioletto
 */
public class SchermataHighScore implements Screen
{
	private static Bottone[] bottoni;
	private static Testo[] testi;
	private static Mondo mondo;
	private TestoSpecial testoSpecial;
	private Bordo bordo;
	private static Game game;

	private OrthographicCamera camera;
	private SpriteBatch sprite;
	private Sfondo sfondo;
	private Vector3 touch;
	private boolean ruotato;

	public static int diffi;
	public static int input;
	private boolean dispose;
	
	/**
	 * crea la schermata e tutte le sue componenti
	 * @param surf il layer dove disegnare
	 */
	public SchermataHighScore(Game game)
	{
		this.dispose=false;
		this.camera= new OrthographicCamera(Cost.SCHERMOWIDTH, Cost.SCHERMOHEIGHT);
		this.camera.position.set(Cost.SCHERMOWIDTH/2, Cost.SCHERMOHEIGHT/2, 0);
		this.touch = new Vector3();
		this.sprite= new SpriteBatch();
		this.sfondo=new Sfondo(Tipo.BG);
		SchermataHighScore.game=game;
		this.ruotato=false;
		diffi=0;
		input=0;
		mondo=new Mondo();
		this.testoSpecial= new TestoSpecial(Tipo.TESTO150, "highscore!", new Vec2(Cost.SCHERMOWIDTH/2.0f, 700.0f), mondo);
		Bottone[] botton={
				new Bottone(Tipo.EASYHC, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[5].getWidth()/2.0f)-(Cost.texture[6].getWidth()/2.0f), 570.0f), mondo, 28, game),
				new Bottone(Tipo.MEDIUMH, new Vec2(Cost.SCHERMOWIDTH/2.0f, 570.0f), mondo, 29, game),
				new Bottone(Tipo.HARDH, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[5].getWidth()/2.0f)+(Cost.texture[6].getWidth()/2.0f), 570.0f), mondo, 30, game),
				
				new Bottone(Tipo.TOUCHC, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[35].getWidth()/2.0f)-(Cost.texture[35].getWidth()/2.0f), 500.0f), mondo, 36, game),
				new Bottone(Tipo.ACCEL, new Vec2(Cost.SCHERMOWIDTH/2.0f, 500.0f), mondo, 37, game),
				new Bottone(Tipo.MIX, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[35].getWidth()/2.0f)+(Cost.texture[37].getWidth()/2.0f), 500.0f), mondo, 38, game),

							new Bottone(Tipo.TESTO70, "indietro", new Vec2(Cost.SCHERMOWIDTH/4.0f, 170.0f), mondo, 7, game),
							new Bottone(Tipo.TESTO70, "reset", new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 170.0f), mondo, 31, game)
							};
		bottoni=botton;
		Testo[] test={
				new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][0], new Vec2(Cost.SCHERMOWIDTH/4.0f, 440.0f)),
				new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][1], new Vec2(Cost.SCHERMOWIDTH/4.0f, 400.0f)),
				new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][2], new Vec2(Cost.SCHERMOWIDTH/4.0f, 360.0f)),
				new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][3], new Vec2(Cost.SCHERMOWIDTH/4.0f, 320.0f)),
				new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][4], new Vec2(Cost.SCHERMOWIDTH/4.0f, 280.0f)),
				
				new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][0]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 440.0f)),
				new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][1]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 400.0f)),
				new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][2]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 360.0f)),
				new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][3]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 320.0f)),
				new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][4]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 280.0f)),
						};	
		testi=test;
		this.bordo=new Bordo(mondo);
	}
	
	/**
	 * cambia lo stato delle componenti in base al bottone schiacciato
	 * @param i bottone schiacciato
	 */
	public static void cambiaBottone(int i)
	{
		aggiornaTasti();
	}

	private static void aggiornaTasti()
	{
		Cost.caricaImpostazioni();
		if(diffi==0)
		{
			bottoni[0]=new Bottone(Tipo.EASYHC, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[5].getWidth()/2.0f)-(Cost.texture[6].getWidth()/2.0f), 570.0f), mondo, 28, game);
			bottoni[1]=new Bottone(Tipo.MEDIUMH, new Vec2(Cost.SCHERMOWIDTH/2.0f, 570.0f), mondo, 29, game);
			bottoni[2]=new Bottone(Tipo.HARDH, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[5].getWidth()/2.0f)+(Cost.texture[6].getWidth()/2.0f), 570.0f), mondo, 30, game);
		}
		else if(diffi==1)
		{
			bottoni[0]=new Bottone(Tipo.EASYH, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[5].getWidth()/2.0f)-(Cost.texture[6].getWidth()/2.0f), 570.0f), mondo, 28, game);
			bottoni[1]=new Bottone(Tipo.MEDIUMHC, new Vec2(Cost.SCHERMOWIDTH/2.0f, 570.0f), mondo, 29, game);
			bottoni[2]=new Bottone(Tipo.HARDH, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[5].getWidth()/2.0f)+(Cost.texture[6].getWidth()/2.0f), 570.0f), mondo, 30, game);
		}
		else
		{
			bottoni[0]=new Bottone(Tipo.EASYH, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[5].getWidth()/2.0f)-(Cost.texture[6].getWidth()/2.0f), 570.0f), mondo, 28, game);
			bottoni[1]=new Bottone(Tipo.MEDIUMH, new Vec2(Cost.SCHERMOWIDTH/2.0f, 570.0f), mondo, 29, game);
			bottoni[2]=new Bottone(Tipo.HARDHC, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[5].getWidth()/2.0f)+(Cost.texture[6].getWidth()/2.0f), 570.0f), mondo, 30, game);
		}
		
		if(input==0)
		{
			bottoni[3]=new Bottone(Tipo.TOUCHC, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[35].getWidth()/2.0f)-(Cost.texture[35].getWidth()/2.0f), 500.0f), mondo, 36, game);
			bottoni[4]=new Bottone(Tipo.ACCEL, new Vec2(Cost.SCHERMOWIDTH/2.0f, 500.0f), mondo, 37, game);
			bottoni[5]=new Bottone(Tipo.MIX, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[35].getWidth()/2.0f)+(Cost.texture[37].getWidth()/2.0f), 500.0f), mondo, 38, game);
		}
		else if(input==1)
		{
			bottoni[3]=new Bottone(Tipo.TOUCH, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[35].getWidth()/2.0f)-(Cost.texture[35].getWidth()/2.0f), 500.0f), mondo, 36, game);
			bottoni[4]=new Bottone(Tipo.ACCELC, new Vec2(Cost.SCHERMOWIDTH/2.0f, 500.0f), mondo, 37, game);
			bottoni[5]=new Bottone(Tipo.MIX, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[35].getWidth()/2.0f)+(Cost.texture[37].getWidth()/2.0f), 500.0f), mondo, 38, game);
		}
		else
		{
			bottoni[3]=new Bottone(Tipo.TOUCH, new Vec2(Cost.SCHERMOWIDTH/2.0f-(Cost.texture[35].getWidth()/2.0f)-(Cost.texture[35].getWidth()/2.0f), 500.0f), mondo, 36, game);
			bottoni[4]=new Bottone(Tipo.ACCEL, new Vec2(Cost.SCHERMOWIDTH/2.0f, 500.0f), mondo, 37, game);
			bottoni[5]=new Bottone(Tipo.MIXC, new Vec2(Cost.SCHERMOWIDTH/2.0f+(Cost.texture[35].getWidth()/2.0f)+(Cost.texture[37].getWidth()/2.0f), 500.0f), mondo, 38, game);
		}
		
		if(diffi==0)
		{
			testi[0]=new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][0], new Vec2(Cost.SCHERMOWIDTH/4.0f, 440.0f));
			testi[1]=new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][1], new Vec2(Cost.SCHERMOWIDTH/4.0f, 400.0f));
			testi[2]=new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][2], new Vec2(Cost.SCHERMOWIDTH/4.0f, 360.0f));
			testi[3]=new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][3], new Vec2(Cost.SCHERMOWIDTH/4.0f, 320.0f));
			testi[4]=new Testo(Tipo.TESTO70, Cost.highscoreNomiEasy[input][4], new Vec2(Cost.SCHERMOWIDTH/4.0f, 280.0f));
			
			testi[5]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][0]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 440.0f));
			testi[6]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][1]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 400.0f));
			testi[7]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][2]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 360.0f));
			testi[8]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][3]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 320.0f));
			testi[9]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiEasy[input][4]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 280.0f));
		}
		else if(diffi==1)
		{
			testi[0]=new Testo(Tipo.TESTO70, Cost.highscoreNomiMedium[input][0], new Vec2(Cost.SCHERMOWIDTH/4.0f, 440.0f));
			testi[1]=new Testo(Tipo.TESTO70, Cost.highscoreNomiMedium[input][1], new Vec2(Cost.SCHERMOWIDTH/4.0f, 400.0f));
			testi[2]=new Testo(Tipo.TESTO70, Cost.highscoreNomiMedium[input][2], new Vec2(Cost.SCHERMOWIDTH/4.0f, 360.0f));
			testi[3]=new Testo(Tipo.TESTO70, Cost.highscoreNomiMedium[input][3], new Vec2(Cost.SCHERMOWIDTH/4.0f, 320.0f));
			testi[4]=new Testo(Tipo.TESTO70, Cost.highscoreNomiMedium[input][4], new Vec2(Cost.SCHERMOWIDTH/4.0f, 280.0f));
			
			testi[5]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiMedium[input][0]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 440.0f));
			testi[6]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiMedium[input][1]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 400.0f));
			testi[7]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiMedium[input][2]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 360.0f));
			testi[8]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiMedium[input][3]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 320.0f));
			testi[9]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiMedium[input][4]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 280.0f));
		}
		else
		{
			testi[0]=new Testo(Tipo.TESTO70, Cost.highscoreNomiHard[input][0], new Vec2(Cost.SCHERMOWIDTH/4.0f, 440.0f));
			testi[1]=new Testo(Tipo.TESTO70, Cost.highscoreNomiHard[input][1], new Vec2(Cost.SCHERMOWIDTH/4.0f, 400.0f));
			testi[2]=new Testo(Tipo.TESTO70, Cost.highscoreNomiHard[input][2], new Vec2(Cost.SCHERMOWIDTH/4.0f, 360.0f));
			testi[3]=new Testo(Tipo.TESTO70, Cost.highscoreNomiHard[input][3], new Vec2(Cost.SCHERMOWIDTH/4.0f, 320.0f));
			testi[4]=new Testo(Tipo.TESTO70, Cost.highscoreNomiHard[input][4], new Vec2(Cost.SCHERMOWIDTH/4.0f, 280.0f));
			
			testi[5]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiHard[input][0]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 440.0f));
			testi[6]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiHard[input][1]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 400.0f));
			testi[7]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiHard[input][2]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 360.0f));
			testi[8]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiHard[input][3]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 320.0f));
			testi[9]=new Testo(Tipo.TESTO70, String.valueOf(Cost.highscorePuntiHard[input][4]), new Vec2((Cost.SCHERMOWIDTH/4.0f)*3, 280.0f));
		}				
	}
	/**
	 * aggiorna tutte le componenti
	 * @param ms lo stato del mouse
	 */
	public void update() 
	{
		if(Gdx.input.isKeyPressed(Keys.BACK))
			Cost.funzioniBottoni(7, game);
		this.camera.unproject(this.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		mondo.step();
		this.testoSpecial.update();
		for(Bottone b: bottoni)
			b.update(touch);
	}
	
	/**
	 * disegna tutte le componenti
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
		bordo.paint(sprite);
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
		testi=null;
		mondo=null;
		testoSpecial=null;
		bordo=null;

		camera=null;
		sprite.dispose();
		sfondo=null;
		touch=null;
	}
	
}