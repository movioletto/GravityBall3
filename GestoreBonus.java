package it.movioletto.GravityBall3;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Gestisce tutti i bonus del mondo fisico
 * @author Movioletto
 */
public class GestoreBonus
{
	private static ArrayList<Bonus> bonus= new ArrayList<Bonus>();
	private static Mondo m;
	private Vec2 gravity;
	private int cicliMassimi= Cost.VELOCITAINCREMENTOBONUS;
	private int ciclo;
	
	/**
	 * Inizializza il gestore, crea il primo bonus e disegna tutti i bonus
	 * @param gravity la gravità di tutti i bonus
	 * @param mondo il mondo in cui aggiungere tutti i bonus
	 * @param surf il layer dove disegnare i bonus
	 */
	public GestoreBonus(Vec2 gravity, Mondo mondo)
	{
		this.gravity=gravity;
		m=mondo;
		this.ciclo=0;
		destroy();
		creaBonus();
			
	}

	private void creaBonus()
	{
		int k=0;
		if(this.gravity.x>0.0f)
			k=1;
		else if(this.gravity.x<0.0f)
			k=3;
		else if(this.gravity.y>0.0f)
			k=2;
		else if(this.gravity.y<0.0f)
			k=0;
		int i=(int)(Math.random()*Cost.IMGBONUS.length);
		while(i==k)
			i=(int)(Math.random()*Cost.IMGBONUS.length);
		
		Vec2 v=new Vec2((float)(Math.random()*(Cost.SCHERMOWIDTH-(Cost.texture[3].getWidth()*2)))+Cost.texture[3].getWidth(), (float)(Math.random()*(Cost.SCHERMOHEIGHT-(Cost.texture[2].getHeight()*2)))+Cost.texture[2].getHeight());
		while(m.sopraCorpo(v))
			v=new Vec2((float)(Math.random()*(Cost.SCHERMOWIDTH-(Cost.texture[3].getWidth()*2)))+Cost.texture[3].getWidth(), (float)(Math.random()*(Cost.SCHERMOHEIGHT-(Cost.texture[2].getHeight()*2)))+Cost.texture[2].getHeight());
		bonus.add(new Bonus(v, i,  m, gravity));
	}

	/**
	 * elimina il bonus passato come parametro dall'arrayList dei bonus e dal mondo fisico, lo anniente, NON ESISTE PIù MUHAHHAHAH
	 * @param b il bonus da cancellare
	 * @return ritorna <ul><li>true, se l'eliminazione è andata a buon fine</li><li>false, se l'eliminazione NON è andata a buon fine</li></ul>
	 */
	public static boolean eliminaBonus(Bonus b)
	{
//		m.removeCorpo(b.getCorpo());
		b.getCorpo().setEsiste(false);
		return bonus.remove(b);
	}
	
	/**
	 * cambia la gravità di tutti i bonus del mondo fisico che esistono
	 * @param gravity la nuova gravità che andrà a sostituire quella vecchia
	 */
	public void cambioGravity(Vec2 gravity)
	{
		this.gravity=gravity;
		if(!bonus.isEmpty())
			for(Bonus b: bonus)
				b.getCorpo().setGravity(gravity);
	}

	/**
	 * gestisce i bonus nel mondo fisico, cancella quelli che non si vedono a schermo, e crea un bonus ogni tot di tempo
	 */
	public void update() 
	{
//		la gravità va giù
		if(this.gravity.y>0.0f)
		{
//			controllo se il muroV è fuori dai bordi e li cancello
			if(!bonus.isEmpty())
				for(int i=0;i<bonus.size();i++)
					if(bonus.get(i).getPosizione().y<=0.0f)
						if(eliminaBonus(bonus.get(i)))
							i--;
		}
//		la gravità va su
		else if(this.gravity.y<0.0f)
		{
//			controllo se il muroV è fuori dai bordi e li cancello
			if(!bonus.isEmpty())
				for(int i=0;i<bonus.size();i++)
					if(bonus.get(i).getPosizione().y>=Cost.SCHERMOHEIGHT)
						if(eliminaBonus(bonus.get(i)))
							i--;
		}
//		la gravità va a destra
		else if(this.gravity.x>0.0f)
		{
//			controllo se il muro è fuori dai bordi e li cancello
			if(!bonus.isEmpty())
				for(int i=0;i<bonus.size();i++)
					if(bonus.get(i).getPosizione().x<=0.0f)
						if(eliminaBonus(bonus.get(i)))
							i--;
		}
//		la gravità va a sinistra
		else if(this.gravity.x<0.0f)
		{
//			controllo se il muro è fuori dai bordi e li cancello
			if(!bonus.isEmpty())
				for(int i=0;i<bonus.size();i++)
					if(bonus.get(i).getPosizione().x>=Cost.SCHERMOWIDTH)
						if(eliminaBonus(bonus.get(i)))
							i--;
		}
//		creo altri bonus
		if(this.ciclo>=this.cicliMassimi)
		{
			creaBonus();
			this.ciclo=0;
		}
		else
			this.ciclo++;
	}
	
	/**
	 * disegna tutti i bonus che ci sono nel mondo fisico
	 * @param surf il layer dove disegnare i bonus
	 */
	public void paint(SpriteBatch sprite)
	{
		if(!bonus.isEmpty())
			for(Bonus b: bonus)
				b.paint(sprite);
	}
	
	/**
	 * distrugge tutti i bonus
	 */
	public void destroy()
	{
		bonus.clear();
	}
	
}