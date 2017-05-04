package it.movioletto.GravityBall3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Classe Bottone, crea e disegna i vari bottoni del gioco!
 * @author Movioletto
 */
public class Bottone
{
	private Tipo t;
	private String testo;
	private Vec2 posizione;
	private Corpo c;
	private Vec2 misure;
	private Mondo m;
//	private boolean sopra;
	private int funzione;
	private Game game;
	
	/**
	 * Crea il Bottone di testo fisicamente nel mondo e lo disegna
	 * @param surf layer dove disegnare il bottone
	 * @param t tipo del bottone: <br> <ul><li>Tipo.TESTO, testo grande 40x40 pixel</li><li>Tipo.TESTO70, testo grande 70x70 pixel</li></ul>
	 * @param s testo del bottone
	 * @param posizione posizione del corpo nel mondo fisico (posizione nella finestra)
	 * @param m mondo in cui inserisco il bottone
	 * @param funzione funzione del bottone da 1 a 34 (leggi la classe Cost)
	 */
	public Bottone(Tipo t, String s, Vec2 posizione, Mondo m, int funzione, Game game)
	{
		ContactListener ct=new ContactListener()
		{
			public void contattoSubito(Corpo a, int pos)
			{
			}
			public void contattoCausato(Corpo a, int pos)
			{
			}
		};
		this.game=game;
//		this.sopra=false;
		this.t=t;
		this.funzione=funzione;
		this.m=m;
		if(t==Tipo.TESTO70)
			this.misure=new Vec2((2+Cost.font70m.x)*s.length(), Cost.font70m.y);
		if(t==Tipo.TESTO100)
			this.misure=new Vec2((2+Cost.font100m.x)*s.length(), Cost.font100m.y);
		if(t==Tipo.TESTO140)
			this.misure=new Vec2((2+Cost.font140m.x)*s.length(), Cost.font140m.y);
		if(t==Tipo.TESTO150)
			this.misure=new Vec2((2+Cost.font150m.x)*s.length(), Cost.font150m.y);
		this.c= new Corpo(new Vec2(posizione.x, posizione.y), 0.0f, CorpoTipo.FISSO, CorpoForma.RETTANGOLO, misure.y/2.0f, misure.x/2.0f, ct, new Vec2(0.0f, 0.0f), t, m);
		m.addCorpo(c);
		this.testo=s;
		this.posizione=new Vec2(posizione.x-this.misure.x/2, posizione.y);	
	}
	
	/**
	 * Crea un bottone da un'immagine fisicamente nel mondo e lo disegna
	 * @param surf layer dove disegnare il bottone
	 * @param t il tipo del bottone (vedi l'ENUM Tipo)
	 * @param posizione posizione del corpo nel mondo fisico (posizione nella finestra)
	 * @param m mondo in cui inserisco il bottone
	 * @param funzione funzione del bottone da 1 a 34 (leggi la classe Cost)
	 */
	public Bottone(Tipo t, Vec2 posizione, Mondo m, int funzione, Game game)
	{
		ContactListener ct=new ContactListener()
		{
			public void contattoSubito(Corpo a, int pos)
			{
			}
			public void contattoCausato(Corpo a, int pos)
			{
			}
		};
		this.game=game;
//		this.sopra=false;
		this.t=t;
		this.funzione=funzione;
		this.m=m;
		this.misure=new Vec2(Cost.texture[Cost.tipoIndexOf(t)].getWidth(), Cost.texture[Cost.tipoIndexOf(t)].getHeight());
		this.c= new Corpo(new Vec2(posizione.x, posizione.y), 0.0f, CorpoTipo.FANTASMA, CorpoForma.RETTANGOLO, misure.y/2.0f, misure.x/2.0f, ct, new Vec2(0.0f, 0.0f), t, m);
		m.addCorpo(c);	
		this.posizione=new Vec2(posizione.x-this.misure.x/2, posizione.y-this.misure.y/2);		
	}
	
	/**
	 * funzione che viene richiamata dalla funzione update della schermata, controlla se il mouse sta sopra al pulsante e se viene cliccato 
	 * @param ms lo stato del mouse
	 */
	public void update(Vector3 touch) 
	{
		if(Gdx.input.justTouched())
			if(this.m.sopraCorpo(new Vec2(touch.x, touch.y),this.c))
				Cost.funzioniBottoni(this.funzione, this.game);
////		mouse sopra al bottone
//		if(this.t==Tipo.TESTO || this.t==Tipo.TESTO70)
//			if(this.m.sopraCorpo(ms.whereIsMouse(), this.c))
//			{
//				if(!this.sopra)
//				{
//					this.c.applicaForza(new Vec2(15.0f, 0.0f));
//					this.sopra=true;
//				}
//			}
//			else
//			{
//				if(this.sopra)
//				{
//					this.c.applicaForza(new Vec2(-15.0f, 0.0f));
//					this.sopra=false;
//				}
//			}
////		bottone cliccato
//		if(ms.isButtonDown(0))
//			if(this.m.sopraCorpo(ms.whereIsButtonDown(),this.c))
//				Cost.funzioniBottoni(this.funzione);
	}
	
	/**
	 * disegna il bottone sullo schermo
	 * @param surf il layer su cui viene disegnato il bottone
	 */
	public void paint(SpriteBatch sprite)
	{
		if(t==Tipo.TESTO70)
			Cost.font70.draw(sprite, testo, this.posizione.x, this.posizione.y);
		else if(t==Tipo.TESTO100)
			Cost.font100.draw(sprite, testo, this.posizione.x, this.posizione.y);
		else if(t==Tipo.TESTO140)
			Cost.font140.draw(sprite, testo, this.posizione.x, this.posizione.y);
		else if(t==Tipo.TESTO150)
			Cost.font150.draw(sprite, testo, this.posizione.x, this.posizione.y);
		else
			sprite.draw(Cost.texture[Cost.tipoIndexOf(t)], this.posizione.x, this.posizione.y);
	}
	
}