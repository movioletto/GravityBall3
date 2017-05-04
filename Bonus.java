package it.movioletto.GravityBall3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe Bonus, i bonus sono corpi sferici con una gravità che servono per far cambiare la gravità di tutti gli altri oggetti del gioco! Si comporta la classe Player o Player2
 * @author Movioletto
 */
public class Bonus
{

	private float vel= 10.0f;
	private Vec2 misure=new Vec2(Cost.textureMatrix[2][0].getWidth(), Cost.textureMatrix[2][0].getHeight());
	private Corpo c;
	private Bonus b=this;
	private int mI;
	
	/**
	 * Costruttore della classe crea il corpo e lo inserisce nel mondo 
	 * @param position Posizione del corpo nel mondo 
	 * @param mIndex Indice del bonus:<br> <ul><li>0, la gravità del corpo sarà cambiata con gravità verso l'alto</li><li>1, la gravità del corpo sarà cambiata con gravità verso destra</li><li>3, la gravità del corpo sarà cambiata con gravità verso il basso</li><li>4, la gravità del corpo sarà cambiata con gravità verso sinistra</li></ul>
	 * @param mondo Il mondo in cui aggiungere il corpo
	 * @param gravity La gravità del corpo
	 */
	public Bonus(Vec2 position, int mIndex, Mondo mondo, Vec2 gravity)
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
		this.c= new Corpo(position, vel, CorpoTipo.DINAMICO, CorpoForma.CERCHIO, misure.x/2.0f, ct, gravity, Tipo.BONUS, mondo);
		mondo.addCorpo(c);
	}
	
	/**
	 * Metodo getter dell'indice della gravità
	 * @return restituisce un intero<br> <ul><li>0, la gravità del corpo sarà cambiata con gravità verso l'alto</li><li>1, la gravità del corpo sarà cambiata con gravità verso destra</li><li>3, la gravità del corpo sarà cambiata con gravità verso il basso</li><li>4, la gravità del corpo sarà cambiata con gravità verso sinistra</li></ul>
	 */
	public int getMI()
	{
		return this.mI;
	}
	
	/**
	 * Metodo getter della posizione del corpo in questo momento nel mondo
	 * @return ritorna una posizione
	 */
	public Vec2 getPosizione()
	{
		return this.c.getPosizione();
	}
	/**
	 * Metodo getter del corpo
	 * @return ritorna il corpo
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
				if(!Cost.multi)
					switch(mI)
					{
						case 0:
							if(Campo.getGravity().x!=0)
								Campo.cambioGravity(new Vec2(0.0f, -(float)Math.abs(Campo.getGravity().x)));
							else
								Campo.cambioGravity(new Vec2(0.0f, -(float)Math.abs(Campo.getGravity().y)));
							break;
						case 1:
							if(Campo.getGravity().x!=0)
								Campo.cambioGravity(new Vec2((float)Math.abs(Campo.getGravity().x), 0.0f));
							else
								Campo.cambioGravity(new Vec2((float)Math.abs(Campo.getGravity().y), 0.0f));
							break;
						case 2:
							if(Campo.getGravity().x!=0)
								Campo.cambioGravity(new Vec2(0.0f, (float)Math.abs(Campo.getGravity().x)));
							else
								Campo.cambioGravity(new Vec2(0.0f, (float)Math.abs(Campo.getGravity().y)));
							break;
						case 3:
							if(Campo.getGravity().x!=0)
								Campo.cambioGravity(new Vec2(-(float)Math.abs(Campo.getGravity().x), 0.0f));
							else
								Campo.cambioGravity(new Vec2(-(float)Math.abs(Campo.getGravity().y), 0.0f));
							break;
					}
				else
					switch(mI)
					{
						case 0:
							if(CampoMulti.getGravity().x!=0)
								CampoMulti.cambioGravity(new Vec2(0.0f, -(float)Math.abs(CampoMulti.getGravity().x)));
							else
								CampoMulti.cambioGravity(new Vec2(0.0f, -(float)Math.abs(CampoMulti.getGravity().y)));
							break;
						case 1:
							if(CampoMulti.getGravity().x!=0)
								CampoMulti.cambioGravity(new Vec2((float)Math.abs(CampoMulti.getGravity().x), 0.0f));
							else
								CampoMulti.cambioGravity(new Vec2((float)Math.abs(CampoMulti.getGravity().y), 0.0f));
							break;
						case 2:
							if(CampoMulti.getGravity().x!=0)
								CampoMulti.cambioGravity(new Vec2(0.0f, (float)Math.abs(CampoMulti.getGravity().x)));
							else
								CampoMulti.cambioGravity(new Vec2(0.0f, (float)Math.abs(CampoMulti.getGravity().y)));
							break;
						case 3:
							if(CampoMulti.getGravity().x!=0)
								CampoMulti.cambioGravity(new Vec2(-(float)Math.abs(CampoMulti.getGravity().x), 0.0f));
							else
								CampoMulti.cambioGravity(new Vec2(-(float)Math.abs(CampoMulti.getGravity().y), 0.0f));
							break;
					}
			GestoreBonus.eliminaBonus(b);
		}
		else
			ct.reazione(c, a, pos);
	}
	
	/**
	 * Metodo che serve per disegnare il bonus sullo schermo
	 * @param surf Il layer su cui disegno!
	 */
	public void paint(SpriteBatch sprite)
	{
		sprite.draw(Cost.textureMatrix[2][this.mI], this.c.getPosizione().x-Cost.textureMatrix[2][this.mI].getWidth()/2, this.c.getPosizione().y-Cost.textureMatrix[2][this.mI].getHeight()/2);
	}

}