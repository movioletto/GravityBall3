package it.movioletto.GravityBall3;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Gestisce tutti i soldi
 * @author Movioletto
 */
public class GestoreSoldi
{
	private static ArrayList<Soldo> soldi= new ArrayList<Soldo>();
	private static Mondo m;
	private Vec2 gravity;
	private int cicliMassimi= 100;
	private int ciclo;
	
	/**
	 * Inizializza il gestore, crea il primo soldo e lo disegna
	 * @param gravity la gravità di tutti i soldi
	 * @param mondo il mondo in cui creo tutti i soldi
	 * @param surf il layer dove disegno tutti i soldi
	 */
	public GestoreSoldi(Vec2 gravity, Mondo mondo)
	{
		this.gravity=gravity;
		m=mondo;
		this.ciclo=0;
		destroy();
		creaSoldo();
			
	}

	private void creaSoldo()
	{		
		Vec2 v=new Vec2((float)(Math.random()*(Cost.SCHERMOWIDTH-(Cost.texture[3].getWidth()*2)))+Cost.texture[3].getWidth(), (float)(Math.random()*(Cost.SCHERMOHEIGHT-(Cost.texture[2].getHeight()*2)))+Cost.texture[2].getHeight());
		while(m.sopraCorpo(v))
			v=new Vec2((float)(Math.random()*(Cost.SCHERMOWIDTH-(Cost.texture[3].getWidth()*2)))+Cost.texture[3].getWidth(), (float)(Math.random()*(Cost.SCHERMOHEIGHT-(Cost.texture[2].getHeight()*2)))+Cost.texture[2].getHeight());
		soldi.add(new Soldo(v, Cost.STATSOLDO[(int)(Math.random()*Cost.STATSOLDO.length)],  m, this.gravity));
	}

	/**
	 * elimina il soldo passato come parametro dal mondo e dall'ArrayList
	 * @param s il soldo da cancellare
	 * @return ritorna <ul><li>true, se la cancellazione va a buon fine</li><li>false, se la cancellaione NON va a buon fine</li></ul>
	 */
	public static boolean eliminaSoldo(Soldo s)
	{
//		m.removeCorpo(s.getCorpo());
		s.getCorpo().setEsiste(false);
		return soldi.remove(s);
	}
	
	/**
	 * cambio la gravità di tutti i soldi
	 * @param gravity la nuova gravità che sostituisce la vecchia
	 */
	public void cambioGravity(Vec2 gravity)
	{
		this.gravity=gravity;
//		la gravità va su o giù
		if(!soldi.isEmpty())
			for(Soldo s: soldi)
				s.getCorpo().setGravity(gravity);
	}

	/**
	 * gestisce tutti i soldi che ci sono, se i soldi non si vedono li cancella, ne crea altri se è passato tot tempo
	 */
	public void update() 
	{
//		la gravità va giù
		if(this.gravity.y>0.0f)
		{
//			controllo se il muroV è fuori dai bordi e li cancello
			if(!soldi.isEmpty())
				for(int i=0;i<soldi.size();i++)
					if(soldi.get(i).getPosizione().y<=0.0f)
						if(eliminaSoldo(soldi.get(i)))
							i--;
		}
//		la gravità va su
		else if(this.gravity.y<0.0f)
		{
//			controllo se il muroV è fuori dai bordi e li cancello
			if(!soldi.isEmpty())
				for(int i=0;i<soldi.size();i++)
					if(soldi.get(i).getPosizione().y>=Cost.SCHERMOHEIGHT)
						if(eliminaSoldo(soldi.get(i)))
							i--;
		}
//		la gravità va a destra
		else if(this.gravity.x>0.0f)
		{
//			controllo se il muro è fuori dai bordi e li cancello
			if(!soldi.isEmpty())
				for(int i=0;i<soldi.size();i++)
					if(soldi.get(i).getPosizione().x<=0.0f)
						if(eliminaSoldo(soldi.get(i)))
							i--;
		}
//		la gravità va a sinistra
		else if(this.gravity.x<0.0f)
		{
//			controllo se il muro è fuori dai bordi e li cancello
			if(!soldi.isEmpty())
				for(int i=0;i<soldi.size();i++)
					if(soldi.get(i).getPosizione().x>=Cost.SCHERMOWIDTH)
						if(eliminaSoldo(soldi.get(i)))
							i--;
		}
//		creo altri bonus
		if(this.ciclo>=this.cicliMassimi)
		{
			creaSoldo();
			this.ciclo=0;
		}
		else
			this.ciclo++;
	}
	
	/**
	 * disegna tutti i soldi che esistono
	 * @param surf il layer dove disegno tutti i soldi
	 */
	public void paint(SpriteBatch sprite)
	{
		if(!soldi.isEmpty())
			for(Soldo s: soldi)
				s.paint(sprite);
	}
	
	/**
	 * distrugge tutti i soldi che ci sono!
	 */
	public void destroy()
	{
		soldi.clear();
	}
}