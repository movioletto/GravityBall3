package it.movioletto.GravityBall3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * soldo
 * @author Movioletto
 */
public class Soldo
{
	private float vel= 10.0f;
	private Vec2 misure=new Vec2(Cost.textureMatrix[3][0].getWidth(), Cost.textureMatrix[3][0].getHeight());
	private Corpo c;
	private Soldo s=this;
	private int mI;
	
	/**
	 * crea il soldo e lo inserisce nel mondo fisico
	 * @param position posizione nel mondo fisico
	 * @param mIndex l'indice del soldo
	 * @param mondo il mondo dove inserire il soldo
	 * @param gravity la gravità del soldo
	 */
	public Soldo(Vec2 position, int mIndex, Mondo mondo, Vec2 gravity)
	{
		ContactListener ct=new ContactListener()
		{
			public void contattoSubito(Corpo a, int pos)
			{
				bReazione(a, pos, this);
			}
			public void contattoCausato(Corpo a, int pos)
			{
				bReazione(a, pos, this);
			}
		};
		this.mI=mIndex;
		this.c= new Corpo(position, this.vel, CorpoTipo.DINAMICO, CorpoForma.CERCHIO, this.misure.x/2.0f, ct, gravity, Tipo.SOLDO, mondo);
		mondo.addCorpo(this.c);
	}
	
	/**
	 * Metodo getter dell'indice del soldo
	 * @return l'indice del soldo
	 */
	public int getMI()
	{
		return this.mI;
	}
	
	/**
	 * Metodo getter della posizione del corpo
	 * @return posizione del corpo
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
	
	private void bReazione(Corpo a,int pos, ContactListener ct)
	{
		if(pos==10)
		{
			if(a.getTipoSprite()==Tipo.PALLA)
				Punteggio.addSoldi(Cost.VALSOLDO[mI]);
			GestoreSoldi.eliminaSoldo(s);
		}
		else
			ct.reazione(c, a, pos);
	}
	
	/**
	 * disegno il soldo
	 * @param surf layer dove disegno
	 */
	public void paint(SpriteBatch sprite)
	{
		sprite.draw(Cost.textureMatrix[3][this.mI], this.c.getPosizione().x-Cost.textureMatrix[3][this.mI].getWidth(), this.c.getPosizione().y-Cost.textureMatrix[3][this.mI].getHeight());
	}
}