package it.movioletto.GravityBall3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * crea, aggiorna e disegna il punteggio
 * @author Movioletto
 */
public class Punteggio
{
	private static int punteggio;
	private static int soldi;
	
	/**
	 * crea il punteggio e lo disegna
	 * @param surf il layer dove disegna il punteggio
	 */
	public Punteggio()
	{
		punteggio=0;
		soldi=0;		
	}

	/**
	 * Metodo getter di punteggio
	 * @return il punteggio
	 */
	public static int getPunteggio()
	{
		return punteggio;
	}
	
	/**
	 * Metodo getter di soldi
	 * @return i soldi
	 */
	public static int getSoldi()
	{
		return soldi;
	}
	
	/**
	 * Metodo getter del punteggio finale ( punteggio + soldi )
	 * @return il punteggio finale (punteggio+soldi)
	 */
	public static int getPunteggioFinale()
	{
		return soldi+punteggio;
	}
	
	/**
	 * aggiunge i soldi
	 * @param s i soldi
	 */
	public static void addSoldi(int s)
	{
		soldi+=s;
	}
	
	/**
	 * aggiorna il punteggio
	 */
	public void update() 
	{
		punteggio++;
	}
	
	/**
	 * disegna il punteggio
	 * @param surf il layer dove disegno il punteggio
	 */
	public void paint(SpriteBatch sprite)
	{
		Cost.font150.draw(sprite, "punti:"+punteggio, 30.0f, 710);
		Cost.font150.draw(sprite, "soldi:"+soldi, 30.0f, 50.0f+Cost.font150m.y/2.0f);
	}
}