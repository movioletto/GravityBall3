package it.movioletto.GravityBall3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * testo scorrevole
 * @author Movioletto
 */
public class TestoScorrevole
{
	private String testo;
	private Corpo c;
	private Vec2 misure;
	private Tipo t;
	
	/**
	 * crea, inserisce nel mondo e disegna il testo scorevole
	 * @param surf layer da disegnare
	 * @param t tipo di testo
	 * @param s testo
	 * @param posizione posizione in cui disegnare
	 * @param gravity gravità da dare al testo
	 * @param m il mondo in cui inserire il testo
	 */
	public TestoScorrevole(Tipo t, String s, Vec2 posizione, Vec2 gravity, Mondo m)
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
		this.t=t;
		if(t==Tipo.TESTO70)
			this.misure=new Vec2((2+Cost.font70m.x)*s.length(), Cost.font70m.y);
		if(t==Tipo.TESTO100)
			this.misure=new Vec2((2+Cost.font100m.x)*s.length(), Cost.font100m.y);
		if(t==Tipo.TESTO140)
			this.misure=new Vec2((2+Cost.font140m.x)*s.length(), Cost.font140m.y);
		if(t==Tipo.TESTO150)
			this.misure=new Vec2((2+Cost.font150m.x)*s.length(), Cost.font150m.y);	
		this.c= new Corpo(posizione, 0.0f, CorpoTipo.DINAMICO, CorpoForma.RETTANGOLO, this.misure.y/2.0f, this.misure.x/2.0f, ct, gravity, t, m);
		m.addCorpo(this.c);
		this.testo=s;	
	}
	
	/**
	 * cambia gravità
	 * @param gravity gravità con cui cambiare
	 */
	public void cambiaGravity(Vec2 gravity) 
	{
		this.c.setGravity(gravity);
	}
	
	/**
	 * disegna il testo
	 * @param surf
	 */
	public void paint(SpriteBatch sprite)
	{
		if(t==Tipo.TESTO70)
			Cost.font70.draw(sprite, testo, this.c.getPosizione().x-this.misure.x/2, this.c.getPosizione().y-this.misure.y/2);
		else if(t==Tipo.TESTO100)
			Cost.font100.draw(sprite, testo, this.c.getPosizione().x-this.misure.x/2, this.c.getPosizione().y-this.misure.y/2);
		else if(t==Tipo.TESTO140)
			Cost.font140.draw(sprite, testo, this.c.getPosizione().x-this.misure.x/2, this.c.getPosizione().y-this.misure.y/2);
		else if(t==Tipo.TESTO150)
			Cost.font150.draw(sprite, testo, this.c.getPosizione().x-this.misure.x/2, this.c.getPosizione().y-this.misure.y/2);
	}

	/**
	 * Metodo getter del corpo
	 * @return il corpo
	 */
	public Corpo getCorpo()
	{
		return this.c;
	}

	/**
	 * Metodo getter della posizione del corpo
	 * @return la posizione del corpo
	 */
	public Vec2 getPosizione()
	{
		return this.c.getPosizione();
	}
}