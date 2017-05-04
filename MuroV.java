package it.movioletto.GravityBall3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Crea fisicamente un muro verticale e lo inserisce nel mondo
 * @author Movioletto
 */
public class MuroV
{
	private float vel= 10.0f;
	private Vec2 misure;
	private Corpo c;
	private int mI;
	
	/**
	 * Crea il muro e lo inserisce nel mondo fisico
	 * @param position la posizione dove posizionare il muro nel mondo fisico
	 * @param mI l'indice del muro, vedi Cost.IMGMURIV
	 * @param mondo il mondo dove aggiungere il muro
	 * @param gravity la gravità da dare al muro
	 */
	public MuroV(Vec2 position, int mI, Mondo mondo, Vec2 gravity)
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
		this.misure=new Vec2(Cost.MUROVWIDTH[mI], Cost.MUROVHEIGHT[mI]);
		this.c= new Corpo(position, this.vel, CorpoTipo.DINAMICO, CorpoForma.RETTANGOLO, this.misure.y/2.0f, this.misure.x/2.0f, ct, gravity, Tipo.MUROV, mondo);
		mondo.addCorpo(this.c);
	}

	/**
	 * Metodo getter dell'indice del muro
	 * @return l'indice del muro
	 */
	public int getMI()
	{
		return this.mI;
	}

	/**
	 * Metodo getter della posizione del corpo nel mondo fisico
	 * @return la posizione del corpo
	 */
	public Vec2 getPosizione()
	{
		return this.c.getPosizione();
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
	 * disegna il muro
	 * @param surf il layer dove disegna il muro
	 */
	public void paint(SpriteBatch sprite)
	{
		sprite.draw(Cost.textureMatrix[1][this.mI], this.c.getPosizione().x-Cost.textureMatrix[1][this.mI].getWidth()/2, this.c.getPosizione().y-Cost.textureMatrix[1][this.mI].getHeight()/2);
	}
	
	@Override
	public String toString()
	{
		return this.c.toString();
	}
}