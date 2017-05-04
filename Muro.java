package it.movioletto.GravityBall3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Crea un muro orizzontale fisicamente e lo disegna
 * @author Movioletto
 */
public class Muro
{
	private float vel= 10.0f;
	private Vec2 misure;
	private Corpo c;
	private int mI;
	
	/**
	 * crea fisicamente il mondo, lo aggiunge al mondo fisico e lo disegna
	 * @param position la posizione dove creare il muro nel mondo fisico
	 * @param mI l'indice del muro, guarda Cost.IMGMURO
	 * @param mondo il mondo dove inserire il muri
	 * @param gravity la gravità che deve avere il mondo
	 */
	public Muro(Vec2 position, int mI, Mondo mondo, Vec2 gravity)
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
		this.mI=mI;
		this.misure=new Vec2(Cost.MUROWIDTH[mI], Cost.MUROHEIGHT[mI]);
		this.c= new Corpo(position, this.vel, CorpoTipo.DINAMICO, CorpoForma.RETTANGOLO, this.misure.y/2.0f, this.misure.x/2.0f, ct, gravity, Tipo.MURO, mondo);
		mondo.addCorpo(this.c);
	}
	
	/**
	 * Metodo getter dell'indice del muro
	 * @return ritorna l'indice del muro
	 */
	public int getMI()
	{
		return this.mI;
	}
	
	/**
	 * Metodo getter della posizione del corpo nel mondo
	 * @return la posizione del corpo nel mondo
	 */
	public Vec2 getPosizione()
	{
		return this.c.getPosizione();
	}
	
	/**
	 * Metodo getter del corpo del muro
	 * @return il corpo del muro
	 */
	public Corpo getCorpo()
	{
		return this.c;
	}
	
	/**
	 * disegna il muro
	 * @param surf il layer dove disegnare il muro
	 */
	public void paint(SpriteBatch sprite)
	{
		sprite.draw(Cost.textureMatrix[0][this.mI], this.c.getPosizione().x-Cost.textureMatrix[0][this.mI].getWidth()/2, this.c.getPosizione().y-Cost.textureMatrix[0][this.mI].getHeight()/2);
	}

	@Override
	public String toString()
	{
		return this.c.toString();
	}
}