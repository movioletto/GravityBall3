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
 * La schermata di gioco single player, gestisce tutte le cose che servono per il gioco
 * @author Movioletto
 */
public class Campo implements Screen
{
	private static Vec2 gravity;
	private static Player player;
	private static GestoreMuri gestoreMuri;
	private static GestoreBonus gestoreBonus;
	private static GestoreSoldi gestoreSoldi;
	private static boolean pausa;
	private static boolean fine;
	private static boolean record;
	private Mondo mondo;
	private Bordo bordo;
	private Punteggio punteggio;
	private PopupFine popupFine;
	private PopupPausa popupPausa;
	private PopupNewRecord popupNewRecord;
	private Bottone bPausa;
	
	private OrthographicCamera camera;
	private SpriteBatch sprite;
	private Sfondo sfondo;
	private Vector3 touch;
	private boolean ruotato;

	/**
	 * Crea il campo da gioco, inizializza tutte le cose che servono per il gioco
	 * @param surf layer in cui disegnare tutto
	 */
	public Campo(Game game)
	{
		this.camera= new OrthographicCamera(Cost.SCHERMOWIDTH, Cost.SCHERMOHEIGHT);
		this.camera.position.set(Cost.SCHERMOWIDTH/2, Cost.SCHERMOHEIGHT/2, 0);
//		this.camera.rotate(90);
		this.touch = new Vector3();
		this.sprite= new SpriteBatch();
		this.sfondo=new Sfondo(Tipo.BG);
		Cost.multi=false;
		pausa=false;
		fine=false;
		record=false;
		
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
		player=new Player(new Vec2(Cost.SCHERMOWIDTH/2.0f, Cost.SCHERMOHEIGHT/2.0f), mondo, gravity, false);
		gestoreBonus= new GestoreBonus(gravity, mondo);
		gestoreMuri= new GestoreMuri(new Vec2(-gravity.x, -gravity.y), mondo);
		gestoreSoldi= new GestoreSoldi(gravity, mondo);
		this.bordo=new Bordo(mondo);
		this.popupFine=new PopupFine(game);
		this.popupPausa=new PopupPausa(game);
		this.popupNewRecord=new PopupNewRecord(game);
	}
	
	/**
	 * aggiorna tutto il campo da gioco
	 * @param ks lo stato della tastiera
	 * @param ms lo stato del mouse
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
		if(!pausa && !fine && !record)
		{
			if(Punteggio.getPunteggio()%Cost.VELOCITAINCREMENTO==0)
				incrementoGravity(1.0f);
			this.mondo.step();
			this.bPausa.update(touch);
			this.punteggio.update();
			player.update(camera);
			gestoreBonus.update();
			gestoreMuri.update();
			gestoreSoldi.update();
		}
		if(fine)
			this.popupFine.update(touch);
		if(pausa)
			this.popupPausa.update(touch);
		if(record)
			this.popupNewRecord.update(touch);
//		GetInput(ks);
	}
	
	/**
	 * disegno tutto il campo da gioco
	 * @param surf layer in cui disegno tutto il campo da gioco
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
		this.punteggio.paint(sprite);
		player.paint(sprite);
		gestoreBonus.paint(sprite);
		gestoreSoldi.paint(sprite);
		gestoreMuri.paint(sprite);
		this.bordo.paint(sprite);
		if(pausa)
			this.popupPausa.paint(sprite);
		if(fine)
			this.popupFine.paint(sprite);
		if(record)
			this.popupNewRecord.paint(sprite);
		sprite.end();
	}
	
	/**
	 * cambia gravità di ogni cordo del mondo fisico del gioco
	 * @param gravity gravità che sostituisce quella vecchia
	 */
	public static void cambioGravity(Vec2 gravity)
	{
		player.cambioGravity(gravity);
		gestoreBonus.cambioGravity(gravity);
		gestoreMuri.cambioGravity(new Vec2(-gravity.x, -gravity.y));
		gestoreSoldi.cambioGravity(gravity);
	}

	/**
	 * viene richiamato quando il gioco finisce, quando il player viene schiacciato e perde
	 */
	public static void fineGioco()
	{
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
	 * viene richiamato quando il record precedente viene distrutto, per far mettere il nome del nuovo detentore del record di gioco!
	 * @param r <ul><li>true, fa partire il popup del record</li><li>false, non fa niente</li></ul>
	 */
	public static void record(boolean r)
	{
		record=r;
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
	}
	
//	private void GetInput(KeyboardState ks) 
//	{
////		cambio gravità, verso su
//		if (ks.IsKeyDown(Key.K1))
//	    {
//			if(gravity.x!=0)
//				cambioGravity(new Vec2(0.0f, -(float)Math.abs(gravity.x)));
//			else
//				cambioGravity(new Vec2(0.0f, -(float)Math.abs(gravity.y)));
//	    }
////		cambio gravità, verso destra
//		else if (ks.IsKeyDown(Key.K2))
//	    {
//			if(gravity.x!=0)
//				cambioGravity(new Vec2((float)Math.abs(gravity.x), 0.0f));
//			else
//				cambioGravity(new Vec2((float)Math.abs(gravity.y), 0.0f));
//	    }
////		cambio gravità, verso giù
//		else if (ks.IsKeyDown(Key.K3))
//	    {
//			if(gravity.x!=0)
//				cambioGravity(new Vec2(0.0f, (float)Math.abs(gravity.x)));
//			else
//				cambioGravity(new Vec2(0.0f, (float)Math.abs(gravity.y)));
//	    }
////		cambio gravità, verso sinistra
//		else if (ks.IsKeyDown(Key.K4))
//	    {
//			if(gravity.x!=0)
//				cambioGravity(new Vec2(-(float)Math.abs(gravity.x), 0.0f));
//			else
//				cambioGravity(new Vec2(-(float)Math.abs(gravity.y), 0.0f));
//	    }
//	}
	
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