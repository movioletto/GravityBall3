package it.movioletto.GravityBall3;

import java.util.ArrayList;

/**
 * il corpo
 * @author Movioletto
 */
public class Corpo
{
//	variabili generali
	private Vec2 posizione;
	private float vel;
	private CorpoTipo tipo;
	private CorpoForma forma;
	private ContactListener ct;
	private Vec2 gravityPre;
	private Vec2 gravity;
	private Tipo tipoSprite;
	private Mondo mondo;
	private boolean esistenza;
	
//	variabili forma rettangolare
	private ArrayList<Vec2> spigoli= new ArrayList<Vec2>();
	private float metaAltezza;
	private float metaLarghezza;
	
//	variabili forma sferica
	private float raggio;	

	/**
	 * Crea un corpo rettangolare
	 * @param posizione posizione del corpo nel mondo 
	 * @param vel velocità del corpo
	 * @param tipo tipo di corpo <ul><li>FISSO</li><li>DINAMICO</li><li>FANTASMA</li><li>VUOTO</li></ul>
	 * @param forma forma del corpo <ul><li>RETTANGOLO</li><li>QUADRATO</li></ul>
	 * @param metaAltezza metà altezza del corpo
	 * @param metaLarghezza metà larghezza del corpo
	 * @param ct contact listener del corpo
	 * @param gravity gravità del corpo
	 * @param tipoSprite tipo di sprite del corpo
	 * @param m il mondo del corpo
	 */
	public Corpo(Vec2 posizione, float vel, CorpoTipo tipo, CorpoForma forma, float metaAltezza, float metaLarghezza, ContactListener ct, Vec2 gravity, Tipo tipoSprite, Mondo m)
	{
		this.posizione=posizione;
		this.vel=vel;
		this.tipo=tipo;
		this.forma=forma;
		this.metaAltezza=metaAltezza;
		this.metaLarghezza=metaLarghezza;
		this.spigoli.add(new Vec2(posizione.x-metaLarghezza, posizione.y-metaAltezza));
		this.spigoli.add(new Vec2(posizione.x+metaLarghezza, posizione.y-metaAltezza));
		this.spigoli.add(new Vec2(posizione.x+metaLarghezza, posizione.y+metaAltezza));
		this.spigoli.add(new Vec2(posizione.x-metaLarghezza, posizione.y+metaAltezza));
		this.ct=ct;
		this.gravity=gravity;
		this.gravityPre=gravity;
		this.tipoSprite=tipoSprite;
		this.mondo=m;
		this.esistenza=true;
	}
	
	/**
	 * Crea un corpo sferico
	 * @param posizione posizione del corpo nel mondo 
	 * @param vel velocità del corpo
	 * @param tipo tipo di corpo <ul><li>FISSO</li><li>DINAMICO</li><li>FANTASMA</li><li>VUOTO</li></ul>
	 * @param forma forma del corpo <ul><li>CERCHIO</li></ul>
	 * @param raggio raggio del corpo
	 * @param ct contact listener del corpo
	 * @param gravity gravità del corpo
	 * @param tipoSprite tipo di sprite del corpo
	 * @param m il mondo del corpo
	 */
	public Corpo(Vec2 posizione, float vel, CorpoTipo tipo, CorpoForma forma, float raggio, ContactListener ct, Vec2 gravity, Tipo tipoSprite, Mondo m)
	{
		this.posizione=posizione;
		this.vel=vel;
		this.tipo=tipo;
		this.forma=forma;
		this.ct=ct;
		this.raggio=raggio;
		this.gravity=gravity;
		this.gravityPre=gravity;
		this.tipoSprite=tipoSprite;
		this.mondo=m;
		this.esistenza=true;
	}

	/**
	 * Crea un corpo fisso
	 * @param posizione posizione del corpo nel mondo
	 * @param forma forma del corpo
	 * @param metaAltezza metà altezza del corpo
	 * @param metaLarghezza metà larghezza del corpo
	 * @param ct contact listener del corpo
	 * @param tipoSprite tipo di sprite del corpo
	 * @param m il mondo del corpo
	 */
	public Corpo(Vec2 posizione, CorpoForma forma, float metaAltezza, float metaLarghezza, ContactListener ct, Tipo tipoSprite, Mondo m)
	{
		this.posizione=posizione;
		this.tipo=CorpoTipo.FISSO;
		this.forma=forma;
		this.metaAltezza=metaAltezza;
		this.metaLarghezza=metaLarghezza;
		this.spigoli.add(new Vec2(posizione.x-metaLarghezza, posizione.y-metaAltezza));
		this.spigoli.add(new Vec2(posizione.x+metaLarghezza, posizione.y-metaAltezza));
		this.spigoli.add(new Vec2(posizione.x+metaLarghezza, posizione.y+metaAltezza));
		this.spigoli.add(new Vec2(posizione.x-metaLarghezza, posizione.y+metaAltezza));
		this.ct=ct;
		this.gravity=new Vec2(0.0f, 0.0f);
		this.gravityPre=new Vec2(0.0f, 0.0f);
		this.tipoSprite=tipoSprite;
		this.mondo=m;
		this.esistenza=true;
	}
	
	/**
	 * Crea un corpo vuoto
	 */
	public Corpo()
	{
		this.tipo=CorpoTipo.VUOTO;
	}

	/**
	 * applica la forza passata come parametro al corpo
	 * @param forza la forza da applicare
	 */
	public void applicaForza(Vec2 forza, boolean reazione)
	{
		this.posizione.x+=forza.x;
		this.posizione.y+=forza.y;
		
		if(this.forma==CorpoForma.RETTANGOLO || this.forma==CorpoForma.QUADRATO)
			spostaSpigoli(forza);
		if(this.forma==CorpoForma.CERCHIO && !reazione)
			this.mondo.gestisciCollisioni(this);
	}
	
	/**
	 * applica la gravità del corpo
	 */
	public void applicaGravita()
	{		
		this.posizione.x+=this.gravity.x;
		this.posizione.y+=this.gravity.y;
		
		if(this.forma==CorpoForma.RETTANGOLO || this.forma==CorpoForma.QUADRATO)
			spostaSpigoli(this.gravity);		
		if(this.forma==CorpoForma.CERCHIO)
			this.mondo.gestisciCollisioni(this);
	}
	
	/**
	 * sposta gli spigoli dei corpi rettangolare o quadrati
	 * @param forza forza da applicare agli spigoli
	 */
	public void spostaSpigoli(Vec2 forza)
	{
		for(Vec2 spigolo: this.spigoli)
		{
			spigolo.x+=forza.x;
			spigolo.y+=forza.y;
		}
	}

	/**
	 * richiama le reazioni del corpo dopo un contatto
	 * @param a corpo toccato
	 * @param i <ul><li>true, se il corpo ha toccato un altro corpo</li><li>false, se il corpo è stato toccato</li></ul>
	 * @param pos posizione dove è stato toccato il corpo
	 */
	public void collisioni(Corpo a, boolean i, int pos)
	{
		if(i)
			this.ct.contattoCausato(a, pos);
		else
			this.ct.contattoSubito(a, pos);
	}

	/**
	 * Metodo setter del contactlistener
	 * @param ct nuovo contactlistener
	 */
	public void addContactListener(ContactListener ct)
	{
		this.ct=ct;
	}
	
	/**
	 * Metodo getter di posizione
	 * @return posizione del corpo
	 */
	public Vec2 getPosizione()
	{
		return this.posizione;
	}
	
	/**
	 * Metodo getter della velocità del corpo
	 * @return la velocità del corpo
	 */
	public float getVel()
	{
		return this.vel;
	}

	/**
	 * Metodo setter della velocità del corpo
	 * @param vel la nuova velocità del corpo
	 */
	public void setVel(float vel)
	{
		this.vel = vel;
	}
	
	/**
	 * Metodo getter della velocità del corpo
	 * @return la velocità del corpo
	 */
	public boolean getEsiste()
	{
		return this.esistenza;
	}

	/**
	 * Metodo setter della velocità del corpo
	 * @param vel la nuova velocità del corpo
	 */
	public void setEsiste(boolean esistenza)
	{
		this.esistenza = esistenza;
	}

	/**
	 * Metodo getter del tipo di corpo
	 * @return il tipo di corpo
	 */
	public CorpoTipo getTipo()
	{
		return this.tipo;
	}

	/**
	 * Metodo getter della forma del corpo
	 * @return la forma del corpo
	 */
	public CorpoForma getForma()
	{
		return this.forma;
	}

	/**
	 * Metodo getter della gravità precedente
	 * @return la gravità precedente
	 */
	public Vec2 getGravityPre()
	{
		return this.gravityPre;
	}

	/**
	 * metodo setter della gravità precedente
	 * @param gravityPre la nuovo gravità precedente del corpo
	 */
	public void setGravityPre(Vec2 gravityPre)
	{
		this.gravityPre = gravityPre;
	}

	/**
	 * Metodo getter della gravità del corpo
	 * @return la gravità del corpo
	 */
	public Vec2 getGravity()
	{
		return this.gravity;
	}

	/**
	 * Metodo setter della gravità del corpo
	 * @param gravity la nuova gravità del corpo
	 */
	public void setGravity(Vec2 gravity)
	{
		this.gravity = gravity;
	}

	/**
	 * Metodo setter di X della gravità del corpo
	 * @param gravi valore float della gravità
	 */
	public void setGravityX(float gravi)
	{
		this.gravity.x=gravi;
	}

	/**
	 * Metodo setter di Y della gravità del corpo
	 * @param gravi valore float della gravità
	 */
	public void setGravityY(float gravi)
	{
		this.gravity.y=gravi;
	}

	/**
	 * Metodo getter del tipo di sprite del corpo
	 * @return il tipo di sprite del corpo
	 */
	public Tipo getTipoSprite()
	{
		return this.tipoSprite;
	}
	
	/**
	 * Metodo getter del mondo in cui sta il corpo
	 * @return il mondo in cui sta il corpo
	 */
	public Mondo getMondo()
	{
		return this.mondo;
	}

	/**
	 * spigoli del corpo di forma rettangolare
	 * @return l'ArrayList di vec2 degli spigoli del rettangolo
	 */
	public ArrayList<Vec2> getSpigoli()
	{
		return this.spigoli;
	}

	/**
	 * Metodo getter della misura di metà altezza del corpo rettangolare o quadratico
	 * @return la misura di metà altezza del corpo
	 */
	public float getMetaAltezza()
	{
		return this.metaAltezza;
	}

	/**
	 * Metodo getter della misura di metà larghezza del corpo rettangolare o quadratico
	 * @return la misura di metà larghezza del corpo
	 */
	public float getMetaLarghezza()
	{
		return this.metaLarghezza;
	}

	/**
	 * Metodo getter della misura del raggio di corpo sferico
	 * @return la misura del raggio del corpo
	 */
	public float getRaggio()
	{
		return this.raggio;
	}
	
//	METODI DI DEBUG

	/**
	 * metodo che disegna sullo schermo gli spigoli del corpo, MOLTO MOLTO LENTO
	 * @param color colore delle linee
	 */
//	public void disegnaSpigoli(int color)
//	{	
//		color=Color.rgb(0, 0, 0);
//	   	CanvasImage bgImage = graphics().createImage(1280, 720);
//	   	Canvas canvas = bgImage.canvas();
//	   	canvas.setStrokeColor(color);
//	   	canvas.drawLine(this.spigoli.get(0).x, this.spigoli.get(0).y, this.spigoli.get(1).x, this.spigoli.get(1).y);
//	   	canvas.drawLine(this.spigoli.get(1).x, this.spigoli.get(1).y, this.spigoli.get(2).x, this.spigoli.get(2).y);
//	   	canvas.drawLine(this.spigoli.get(2).x, this.spigoli.get(2).y, this.spigoli.get(3).x, this.spigoli.get(3).y);
//	   	canvas.drawLine(this.spigoli.get(3).x, this.spigoli.get(3).y, this.spigoli.get(0).x, this.spigoli.get(0).y);
//	   	
//		ImageLayer iLayer=graphics().createImageLayer(bgImage.snapshot());
//		graphics().rootLayer().add(iLayer);
//	}

	/**
	 * metodo che disegna sullo schermo il cerchio, MOLTO MOLTO LENTO
	 * @param color colore della circonferenza
	 */
//	public void disegnaCerchio(int color)
//	{		
//		color=Color.rgb(250, 0, 0);
//	   	CanvasImage bgImage = graphics().createImage(1280, 720);
//	   	Canvas canvas = bgImage.canvas();
//	   	canvas.setStrokeColor(color);
//	   	canvas.fillCircle(this.posizione.x, this.posizione.y, this.raggio);
//	   	
//		ImageLayer iLayer=graphics().createImageLayer(bgImage.snapshot());
//		graphics().rootLayer().add(iLayer);
//	}
	
	/**
	 * disegna centro del corpo, MOLTO MOLTO LENTO
	 */
//	public void disegnaCentro()
//	{		
//	   	CanvasImage bgImage = graphics().createImage(1280, 720);
//	   	Canvas canvas = bgImage.canvas();
//		canvas.setFillColor(Color.rgb(250, 250, 250));
//
//	   	canvas.fillCircle(this.posizione.x, this.posizione.y, 3);
//	   	
//		ImageLayer iLayer=graphics().createImageLayer(bgImage.snapshot());
//		graphics().rootLayer().add(iLayer);
//	}
	
	@Override
	public String toString()
	{
		return this.mondo.getCorpo(this)+"|"+this.tipoSprite+"|"+this.forma+"|"+this.posizione+"|";
	}
}