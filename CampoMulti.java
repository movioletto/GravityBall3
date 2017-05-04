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
 * La schermata di gioco multi player 
 * @author Movioletto
 */
public class CampoMulti implements Screen
{
	private static Player player;
	private static Player2 player2;
	private static GestoreMuri gestoreMuri;
	private static GestoreBonus gestoreBonus;
	private static GestoreBonus gestoreBonus2;
	private static Vec2 gravity;
	private static boolean pausa;
	private static boolean fine;
	private static int vincitore;
	private Bordo bordo;
	private Mondo mondo;
	private PopupFineMulti popupFine;
	private PopupPausa popupPausa;
	private Bottone bPausa;
	private Punteggio punteggio;

	private OrthographicCamera camera;
	private SpriteBatch sprite;
	private Sfondo sfondo;
	private Vector3 touch;
	private boolean ruotato;
	/**
	 * Crea il campo di gioco
	 * @param surf il layer dove disegnare tutto
	 */
	public CampoMulti(Game game)
	{
		this.camera= new OrthographicCamera(Cost.SCHERMOWIDTH, Cost.SCHERMOHEIGHT);
		this.camera.position.set(Cost.SCHERMOWIDTH/2, Cost.SCHERMOHEIGHT/2, 0);
//		this.camera.rotate(90);
		this.touch = new Vector3();
		this.sprite= new SpriteBatch();
		this.sfondo=new Sfondo(Tipo.BG);
		Cost.multi=true;
		pausa=false;
		fine=false;
		vincitore=0;
		
		switch((int)(Math.random()*Cost.IMGBONUS.length))
		{
			case 0:
				gravity= new Vec2(0.0f, -3.0f);
				break;
			case 1:
				gravity= new Vec2(3.0f, 0.0f);
				break;
			case 2:
				gravity= new Vec2(0.0f, 3.0f);
				break;
			case 3:
				gravity= new Vec2(-3.0f, 0.0f);
				break;
		}
		this.ruotato=false;
		this.mondo=new Mondo();
		this.bPausa= new Bottone(Tipo.PAUSA, new Vec2(Cost.SCHERMOWIDTH-Cost.texture[Cost.tipoIndexOf(Tipo.PAUSA)].getWidth(), 20.0f+Cost.font150m.y/2.0f), mondo, 15, game);
		this.punteggio= new Punteggio();
		player=new Player(new Vec2(Cost.SCHERMOWIDTH/2.0f, Cost.SCHERMOHEIGHT/2.0f), mondo, gravity, true);
		player2=new Player2(new Vec2(Cost.SCHERMOWIDTH/4.0f, Cost.SCHERMOHEIGHT/4.0f), mondo, gravity, true);
		gestoreBonus= new GestoreBonus(gravity, mondo);
		gestoreMuri= new GestoreMuri(new Vec2(-gravity.x, -gravity.y), mondo);
		gestoreBonus2= new GestoreBonus(gravity, mondo);
		this.bordo=new Bordo(mondo);
		this.popupFine=new PopupFineMulti(game);
		this.popupPausa=new PopupPausa(game);
	}
	
	/**
	 * aggiorna tutte le cose del campo di gioco
	 * @param ks stato della tastiera
	 * @param ms stato del mouse
	 */
	public void update() 
	{
		if(Gdx.input.isKeyPressed(Keys.BACK))
			pausa=true;
		if(Gdx.input.isKeyPressed(Keys.POWER))
			pausa=true;
		if(Gdx.input.isKeyPressed(Keys.MENU))
			pausa=true;
		if(Gdx.input.isKeyPressed(Keys.HOME))
			pausa=true;
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
			pausa=true;
		this.camera.unproject(this.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		if(!pausa && !fine)
		{
			if(Punteggio.getPunteggio()%Cost.VELOCITAINCREMENTO==0)
				incrementoGravity(1.0f);
			this.mondo.step();
			this.bPausa.update(touch);
			this.punteggio.update();
			player.update(camera);
			player2.update(camera);
			gestoreBonus.update();
			gestoreMuri.update();
			gestoreBonus2.update();
		}
		if(fine)
			this.popupFine.update(touch);
		if(pausa)
			this.popupPausa.update(touch);
//		GetInput(ks);
	}
	
	/**
	 * disegna tutti gli oggetti del campo!
	 * @param surf layer dove disegnare tutti gli oggetti del campo
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
		
		this.bPausa.paint(sprite);
		player.paint(sprite);
		player2.paint(sprite);
		gestoreBonus.paint(sprite);
		gestoreMuri.paint(sprite);
		gestoreBonus2.paint(sprite);
		this.bordo.paint(sprite);
		if(pausa)
			this.popupPausa.paint(sprite);
		if(fine)
			this.popupFine.paint(sprite);
		sprite.end();
	}
	
	/**
	 * cambio la gravità in tutti gli oggetti che hanno una gravità
	 * @param gravi gravità che sostituisco a quella vecchia
	 */
	public static void cambioGravity(Vec2 gravi)
	{
		gravity=gravi;
		player.cambioGravity(gravi);
		player2.cambioGravity(gravi);
		gestoreBonus.cambioGravity(gravi);
		gestoreMuri.cambioGravity(new Vec2(-gravi.x, -gravi.y));
		gestoreBonus2.cambioGravity(gravi);
	}

	/**
	 * vieni richiamato quando uno dei due player perde, muore schiacciato da un muro e un bordo! muahhahahaha
	 */
	public static void fineGioco()
	{
//		gravità a destra
		if(gravity.x>0.0f)
			if(player.getPosizione().x<player2.getPosizione().x)
				vincitore=2;
			else
				vincitore=1;
//		gravità a sinistra
		else if(gravity.x<0.0f)
			if(player.getPosizione().x<player2.getPosizione().x)
				vincitore=1;
			else
				vincitore=2;
//		gravità su
		else if(gravity.y<0.0f)
			if(player.getPosizione().y>player2.getPosizione().y)
				vincitore=2;
			else
				vincitore=1;
//		gravità giu
		else if(gravity.y>0.0f)
			if(player.getPosizione().y<player2.getPosizione().y)
				vincitore=2;
			else
				vincitore=1;
		fine=true;
	}

	/**
	 * viene richiamato quando viene cliccato il bottone pausa, per mettere in pausa il gioco o farlo ripartire 
	 * @param p per far partire o stoppare il gioco <ul><li>true, gioco in pausa</li><li>false, gioco attivo</li></ul>
	 */
	public static void pausa(boolean p)
	{
		pausa=p;
	}
	
	/**
	 * Metodo getter di Vincitore
	 * @return ritorna il vincitore <ul><li>1, se il vincitore è il player 1</li><li>2, se il vincitore è il player 2</li></ul>
	 */
	public static int getVincitore()
	{
		return vincitore;
	}

	/**
	 * incrementa la gravità di tutti i corpi e anche la velocità del player
	 * @param g di quanto viene incrementata la gravità
	 */
	public static void incrementoGravity(float g)
	{
		if(gravity.x>0.0f)
			gravity.x+=g;
		else if(gravity.x<0.0f)
			gravity.x-=g;
		else if(gravity.y>0.0f)
			gravity.y+=g;
		else if(gravity.y<0.0f)
			gravity.y-=g;
		cambioGravity(gravity);
		player.incrementoVelocita(g);
		player2.incrementoVelocita(g);
	}
	
	/**
	 * metodo getter della gravità del gioco
	 * @return ritorna la gravità
	 */
	public static Vec2 getGravity()
	{
		return gravity;
	}

	@Override
	public void render(float delta)
	{
		update();
		paint();
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
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{		
	}

	@Override
	public void dispose()
	{
	}	
}