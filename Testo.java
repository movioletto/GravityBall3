package it.movioletto.GravityBall3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * testo semplice
 * @author Movioletto
 */
public class Testo
{
	private String testo;
	private Vec2 posizione;
	private Vec2 misure;
	private Tipo t;
	
	/**
	 * crea il testo e lo disegna
	 * @param surf layer da disegnare
	 * @param t tipo di testo <ul><li>Tipo.TESTO70, testo grande</li><li>Tipo.TESTO, testo piccolo</li></ul>
	 * @param s testo da disegnare
	 * @param posizione posizione dove disegnare il testo
	 */
	public Testo( Tipo t, String s, Vec2 posizione)
	{
		this.testo=s;
		this.t=t;
		if(t==Tipo.TESTO70)
			this.misure=new Vec2((2+Cost.font70m.x)*s.length(), Cost.font70m.y);
		if(t==Tipo.TESTO100)
			this.misure=new Vec2((2+Cost.font100m.x)*s.length(), Cost.font100m.y);
		if(t==Tipo.TESTO140)
			this.misure=new Vec2((2+Cost.font140m.x)*s.length(), Cost.font140m.y);
		if(t==Tipo.TESTO150)
			this.misure=new Vec2((2+Cost.font150m.x)*s.length(), Cost.font150m.y);
		this.posizione=new Vec2(posizione.x-this.misure.x/2, posizione.y);	
	}
	
	/**
	 * per cambiare la stringa del testo
	 * @param s stringa da cambiare
	 */
	public void cambiaStringa(String s) 
	{
		this.posizione=new Vec2(posizione.x+this.misure.x/2, posizione.y+this.misure.y/2);	
		this.testo=s;
		if(t==Tipo.TESTO70)
			this.misure=new Vec2((2+Cost.font70m.x)*s.length(), Cost.font70m.y);
		if(t==Tipo.TESTO100)
			this.misure=new Vec2((2+Cost.font100m.x)*s.length(), Cost.font100m.y);
		if(t==Tipo.TESTO140)
			this.misure=new Vec2((2+Cost.font140m.x)*s.length(), Cost.font140m.y);
		if(t==Tipo.TESTO150)
			this.misure=new Vec2((2+Cost.font150m.x)*s.length(), Cost.font150m.y);
		this.posizione=new Vec2(posizione.x-this.misure.x/2, posizione.y);	
	}
	
	/**
	 * Metodo getter del testo
	 * @return il testo
	 */
	public String getString() 
	{
		return this.testo;
	}
	
	/**
	 * disegna il testo
	 * @param surf layer da disegnare
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
	}
}