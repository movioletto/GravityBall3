package it.movioletto.GravityBall3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Testo con gravità diversa per ogni lettera
 * @author Movioletto
 */
public class TestoSpecial
{
	private String testo;
	private Corpo[] c;
	
	/**
	 * crea, inserisce nel mondo fisico e disegna il testo speciale
	 * @param surf layer da disegnare
	 * @param t tipo di testo
	 * @param s testo da scrivere
	 * @param posizione posizione del testo
	 * @param m mondo in cui inserire il corpo
	 */
	public TestoSpecial( Tipo t, String s, Vec2 posizione, Mondo m)
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
		this.c=new Corpo[s.length()];
		Vec2 misure= new Vec2((2+Cost.font150m.x)*s.length(), Cost.font150m.y);
		posizione=new Vec2(posizione.x-(misure.x/2.0f)+(Cost.font150m.x/2.0f), posizione.y-(misure.y/2.0f)+(Cost.font150m.y/2.0f)); 
		for(int i=0;i<this.c.length;i++)
		{
			this.c[i]= new Corpo(new Vec2(posizione.x+((2+Cost.font150m.x)*i), posizione.y), 0.0f, CorpoTipo.DINAMICO, CorpoForma.RETTANGOLO, (2+Cost.font150m.y)/2.0f, (2+Cost.font150m.x)/2.0f, ct, new Vec2(0.0f, -(float)(Math.random()*5)), t, m);
			m.addCorpo(this.c[i]);
		}
		this.testo=s;	
	}
	
	/**
	 * non fa cadere le lettere giù dal bordo
	 */
	public void update() 
	{
		for(int i=0;i<this.c.length;i++)
			if(this.c[i].getPosizione().y<=Cost.texture[2].getHeight()*3+Cost.font150m.y+10)//>=(Cost.SCHERMOHEIGHT-Cost.BORDO1HEIGHT-(Cost.TEXTHEIGHT/2.0f)))
				this.c[i].setGravityY(0);
	}
	
	/**
	 * disegna il testo
	 * @param surf layer in cui disegnare
	 */
	public void paint(SpriteBatch sprite)
	{
		for(int i=0;i<this.c.length;i++)
			Cost.font150.draw(sprite, String.valueOf(this.testo.charAt(i)), this.c[i].getPosizione().x-(Cost.font150m.x/2.0f), this.c[i].getPosizione().y-(Cost.font150m.y/2.0f));
	}
}