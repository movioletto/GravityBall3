package it.movioletto.GravityBall3;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe Bordo, crea e disegna il bordo del gioco!
 * @author Movioletto
 */
public class Bordo
{
	private Vec2 misureB1=new Vec2(Cost.texture[2].getWidth(), Cost.texture[2].getHeight());
	private Vec2 misureB2=new Vec2(Cost.texture[3].getWidth(), Cost.texture[3].getHeight());
	private ArrayList<Corpo> c= new ArrayList<Corpo>();

	/**
	 * Crea fisicamente il bordo nel mondo
	 * @param surf il layer dove disegnare il bordo
	 * @param mondo il mondo in cui inserisco i bordi
	 */
	public Bordo(Mondo mondo)
	{
		ContactListener ct=new ContactListener()
		{
			public void contattoSubito(Corpo a, int pos)
			{
//				a.applicaForza(c.gravity, false);
//				contattoSubitoA(a, c);
			}
			public void contattoCausato(Corpo a, int pos)
			{
//				a.applicaForza(c.gravity, false);
//				contattoCausatoA(a,c);
			}
		};
//		BORDO SUPERIRORE
		this.c.add(new Corpo(new Vec2(misureB1.x/2.0f, misureB1.y/2.0f), CorpoForma.RETTANGOLO, misureB1.x/2.0f, misureB1.y/2.0f, ct, Tipo.BORDO1, mondo));
//		BORDO A DESTRA
		this.c.add(new Corpo(new Vec2(Cost.SCHERMOWIDTH-misureB2.x/2.0f, misureB2.y/2.0f), CorpoForma.RETTANGOLO, misureB2.x/2.0f, misureB2.y/2.0f, ct, Tipo.BORDO2, mondo));
//		BORDO INFERIORE
		this.c.add(new Corpo(new Vec2(misureB1.x/2.0f, Cost.SCHERMOHEIGHT-misureB1.y/2.0f), CorpoForma.RETTANGOLO, misureB1.x/2.0f, misureB1.y/2.0f, ct, Tipo.BORDO1, mondo));
//		BORDO A SINISTRA
		this.c.add(new Corpo(new Vec2(misureB2.x/2.0f, misureB2.y/2.0f), CorpoForma.RETTANGOLO, misureB2.x/2.0f, misureB2.y/2.0f, ct, Tipo.BORDO2, mondo));
		for(Corpo a:c)
			mondo.addCorpo(a);
		
	}

	/**
	 * Disegna il bordo sullo schermo
	 * @param surf il layer dove disegnare il bordo
	 */
	public void paint(SpriteBatch sprite)
	{
		sprite.draw(Cost.texture[2], 0, 705);/*.get(0).paint(surf, c.get(0).getPosizione());*/
		sprite.draw(Cost.texture[3], 1009, 0);/*.get(1).paint(surf, c.get(1).getPosizione());*/
		sprite.draw(Cost.texture[2], 0, 0);/*.get(0).paint(surf, c.get(2).getPosizione());*/
		sprite.draw(Cost.texture[3], 0, 0);/*.get(1).paint(surf, c.get(3).getPosizione());*/
	}
}