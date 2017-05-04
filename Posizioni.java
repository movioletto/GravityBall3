package it.movioletto.GravityBall3;

import java.util.ArrayList;

/**
 * ragruppa più coppie di valori
 * @author Movioletto
 */
public class Posizioni
{
	private ArrayList<Coppia<Float, Integer>> posizioni= new ArrayList<Coppia<Float, Integer>>();
	
	/**
	 * aggiunge posizioni
	 * @param posizione posizioni da aggiungere
	 */
	public Posizioni(Coppia<Float, Integer> ... posizione)
	{
		for(Coppia<Float, Integer> pos: posizione)
			this.posizioni.add(pos);
	}

	/**
	 * Metodo getter di posizioni
	 * @param num la posizione da ritornare
	 * @return la posizione desiderata
	 */
	public Coppia<Float, Integer> getPosizioni(int num)
	{
			return this.posizioni.get(num);
	}

	/**
	 * Metodo getter del numero di posizioni
	 * @return il numero di posizioni
	 */
	public int getSize()
	{
		return this.posizioni.size();
	}
}