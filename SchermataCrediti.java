package it.movioletto.GravityBall3;

import java.util.ArrayList;

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
 * Schermata dei crediti
 * @author Movioletto
 */
public class SchermataCrediti implements Screen
{	
	private ArrayList<TestoScorrevole> testi= new ArrayList<TestoScorrevole>();
	private Bottone bottoni;
	private Mondo mondo;
	private String[] testo;
	private Vec2 gravity;
	private int distanzaTraScritte=150;
	private Bordo bordo;
	private OrthographicCamera camera;
	private SpriteBatch sprite;
	private Sfondo sfondo;
	
	private Game game;
	private Vector3 touch;
	private boolean ruotato;
	private boolean dispose;
	
	/**
	 * crea la schermata con tutte le scritte
	 * @param surf il layer dove disegnare
	 */
	public SchermataCrediti(Game game)
	{
		this.dispose=false;
		this.game=game;
		this.ruotato=false;
		this.camera= new OrthographicCamera(Cost.SCHERMOWIDTH, Cost.SCHERMOHEIGHT);
		this.camera.position.set(Cost.SCHERMOWIDTH/2, Cost.SCHERMOHEIGHT/2, 0);
		this.touch=new Vector3();
		this.sprite= new SpriteBatch();
		this.sfondo=new Sfondo(Tipo.BG);
		this.mondo=new Mondo();
		this.gravity=new Vec2(0.0f, 2.0f);
		String[] testo={
				"clicca la freccia",
				"per tornare indietro",
				" ",
				" ",
				"Tanto tempo fa,",
				"in una galassia",
				"lontana lontana...",
				" ",
				" ",
				"(scusate se non",
				"c'e' l'effetto",
				"in 3d)",
				" ",
				" ",
				"ringraziamenti:",
				" ",
				"ringrazio i romani",
				"che mi hanno",
				"dato le lettere",
				" ",
				"(alfabeto romano)",
				" ",
				"ringrazio anche gli",
				"arabi che mi",
				"hanno concesso",
				"i numeri",
				" ",
				"(numeri arabi)",
				" ",
				"ringrazio photoshop",
				"che e' stato",
				"tanto paziente",
				"con me ",
				" ",
				" ",
				"statistiche:",
				" ",
				"immagini realizzate",
				"204",
				" ",
				"musiche",
				"1",
				"(il silenzio)",
				" ",
				"tastiere cambiate",
				"1",
				" ",
				"player morti",
				"+100.000",
				" ",
				"linee di codice",
				"6.255",
				" ",
				"metodi",
				"287",
				" ",
				"variabili",
				"294",
				" ",
				"classi",
				"43",
				" ",
				"caratteri",
				"182.986",
				" ",
				" ",
				"paesi dove il",
				"gioco funziona:",
				"- italia",
				"- argentina",
				"(non provato)",
				"- tutto il mondo",
				" ",
				" ",					
				"ringrazio tutti i miei",
				"familiari e i miei",
				"amici che si erano",
				"preoccupati,",
				"sono vivo,",
				"non uscivo dalla",
				"mia camera",
				"perche' stavo",
				"preparando un esame,",
				"tranquilli,",
				"l'ho finito!",
				"(almeno credo)",
				" ",
				" ",
				"scuse:",
				"scusate l'italiano",
				"usato!",
				" ",
				"scusate se il",
				"testo e' troppo",
				"lungo",
				" ",
				"scusate se il gioco",
				"fa schifo,",
				"ma mi merito 30!",
				" ",
				"fine"
						};
		this.testo=testo;
		this.bottoni=new Bottone(Tipo.FRECCIASINISTRA40, new Vec2(Cost.texture[27].getWidth()+20.0f, 150.0f), this.mondo, 7, game);
		
		creaTestoScorrevole();
		this.bordo=new Bordo(this.mondo);
	}
	
	/**
	 * cancella i testi che non si vedono più e crea quelli nuovi
	 * @param ms
	 */
	public void update() 
	{
		if(Gdx.input.isKeyPressed(Keys.BACK))
			Cost.funzioniBottoni(7, game);
		this.mondo.step();
		if(!this.testi.isEmpty())
			for(int i=0;i<this.testi.size();i++)
				if(this.testi.get(i).getPosizione().y+(Cost.texture[27].getHeight()/2.0f)>=Cost.SCHERMOHEIGHT+Cost.texture[2].getHeight()*3)
					if(eliminaTestoScorrevole(this.testi.get(i)))
						i--;
		if(!this.testi.isEmpty())
			if(this.testi.get(this.testi.size()-1).getPosizione().y>=this.distanzaTraScritte)
				creaTestoScorrevole();
		if(this.testi.isEmpty())
			Cost.funzioniBottoni(7, game);
		camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		this.bottoni.update(touch);
	}

	private void creaTestoScorrevole()
	{
		if(this.testo.length>0)
		{
			this.testi.add(new TestoScorrevole(Tipo.TESTO70, this.testo[0], new Vec2(Cost.SCHERMOWIDTH/2.0f, Cost.texture[27].getHeight()), this.gravity, this.mondo));
			String[] testo=new String[this.testo.length-1];
			for(int i=1;i<this.testo.length;i++)
				testo[i-1]=this.testo[i];
			this.testo=testo;
		}
	}

	private boolean eliminaTestoScorrevole(TestoScorrevole tes)
	{
		this.mondo.removeCorpo(tes.getCorpo());
		return this.testi.remove(tes);
	}
	
	/**
	 * disegna tutte le componenti della schermata
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

		this.bottoni.paint(sprite);
		for(TestoScorrevole t: this.testi)
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
		testi= null;
		bottoni=null;
		mondo=null;
		testo=null;
		gravity=null;
		bordo=null;
		camera=null;
		sprite.dispose();
		sfondo=null;
		touch=null;
	}
}