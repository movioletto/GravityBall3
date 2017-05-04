package it.movioletto.GravityBall3;

/**
 * Coppie di valori diversi
 * @author Movioletto
 */
public class Coppia<T, S>
{
	private T a;
	private S b;
	
	/**
	 * inizializza una coppia da due valori	
	 * @param a primo valore da aggiungere
	 * @param b secondo valore da aggiungere
	 */
	public Coppia(T a, S b)
	{
		this.a=a;
		this.b=b;
	}
	
	/**
	 * Metodo getter del primo valore della coppia
	 * @return primo valore della coppia
	 */
	public T getA()
	{
		return a;
	}
	
	/**
	 * Metodo getter del secondo valore della coppia
	 * @return secondo valore della coppia
	 */
	public S getB()
	{
		return b;
	}
	
	/**
	 * Metodo setter di entrambi i valori
	 * @param a il primo valore della coppia
	 * @param b il secondo valore della coppia
	 */
	public void set(T a, S b)
	{
		this.a=a;
		this.b=b;
	}

	/**
	 * Metodo setter del primo valore della coppia
	 * @param a il valore da dare al primo
	 */
	public void setA(T a)
	{
		this.a=a;
	}
	
	/**
	 * Metodo setter del secondo valore della coppia
	 * @param b il valore da dare al secondo
	 */
	public void setB(S b)
	{
		this.b=b;
	}
}