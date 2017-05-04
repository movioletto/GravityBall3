package it.movioletto.GravityBall3;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Gestisce tutti i muri del mondo fisico, quelli verticali e quelli orizzontali
 * @author Movioletto
 */
public class GestoreMuri
{
	private ArrayList<Muro> muri= new ArrayList<Muro>();
	private ArrayList<MuroV> muriV= new ArrayList<MuroV>();
	private Vec2 gravity;
	private Mondo m;
	private float distanzaFraMuri= 200.0f;
	private float distanzaFraMuriV= 150.0f;
	
	/**
	 * Inizializza il gestore, crea i primi muri e li disegna
	 * @param gravity la gravità di tutti i muri
	 * @param mondo il mondo in cui aggiungere i muri
	 * @param surf il layer dove disegnare i muri
	 */
	public GestoreMuri(Vec2 gravity, Mondo mondo)
	{
		this.gravity=gravity;
		this.m=mondo;
		destroy();
//		la gravità va su o giù
		if(this.gravity.x==0.0f)
			creaMuro();
//		la gravità va a destra o a sinistra
		else
			creaMuro();
	}

	private void creaMuro()
	{
		int i=(int)(Math.random()*Cost.getMuriDefault().length);
//		la gravità va giù
		if(this.gravity.y>0.0f)
		{
			for(int k=0;k<Cost.getMuriDefault()[i].getSize();k++)
				this.muri.add(new Muro(new Vec2(Cost.getMuriDefault()[i].getPosizioni(k).getA(), -70.0f), Cost.getMuriDefault()[i].getPosizioni(k).getB(),  this.m, this.gravity));
		}
//		la gravità va su
		else if(this.gravity.y<0.0f)
		{
			for(int k=0;k<Cost.getMuriDefault()[i].getSize();k++)
				this.muri.add(new Muro(new Vec2(Cost.getMuriDefault()[i].getPosizioni(k).getA(), Cost.SCHERMOHEIGHT+70.0f), Cost.getMuriDefault()[i].getPosizioni(k).getB(),  this.m, this.gravity));
		}
//		la gravità va a destra
		else if(this.gravity.x>0.0f)
		{
			for(int k=0;k<Cost.getMuriVDefault()[i].getSize();k++)
				this.muriV.add(new MuroV(new Vec2(-70.0f, Cost.getMuriVDefault()[i].getPosizioni(k).getA()), Cost.getMuriVDefault()[i].getPosizioni(k).getB(),  this.m, this.gravity));
		}
//		la gravità va a sinistra
		else if(this.gravity.x<0.0f)
		{
			for(int k=0;k<Cost.getMuriVDefault()[i].getSize();k++)
				this.muriV.add(new MuroV(new Vec2(Cost.SCHERMOWIDTH+70.0f, Cost.getMuriVDefault()[i].getPosizioni(k).getA()), Cost.getMuriVDefault()[i].getPosizioni(k).getB(),  this.m, this.gravity));
		}
	}

	private boolean eliminaMuro(Muro m)
	{
//		this.m.removeCorpo(m.getCorpo());
		m.getCorpo().setEsiste(false);
		return this.muri.remove(m);
	}
	
	private boolean eliminaMuroV(MuroV m)
	{
//		this.m.removeCorpo(m.getCorpo());
		m.getCorpo().setEsiste(false);
		return this.muriV.remove(m);
	}
	
	/**
	 * cambio la gravità di tutti i muri che esistono
	 * @param gravity la nuova gravità che sostituisco a tutti i muri, orizzontali che verticali
	 */
	public void cambioGravity(Vec2 gravity)
	{
		this.gravity=gravity;
//		la gravità va su o giù
		if(!this.muri.isEmpty())
			for(Muro m: this.muri)
				m.getCorpo().setGravity(gravity);
//		la gravità va a destra o a sinistra
		if(!this.muriV.isEmpty())
			for(MuroV m: this.muriV)
				m.getCorpo().setGravity(gravity);
	}

	/**
	 * gestisce tutti i muri, li distrugge se non si vedono più, li crea se la distanza tra due muri è quella giusta
	 */
	public void update() 
	{
//		la gravità va giù
		if(this.gravity.y>0.0f)
		{
//			controllo se il muroV è fuori dai bordi e li cancello
			if(!this.muriV.isEmpty())
				for(int i=0;i<this.muriV.size();i++)
					if(this.muriV.get(i).getPosizione().y-(Cost.MUROVHEIGHT[this.muriV.get(i).getMI()]/2.0f)>=Cost.SCHERMOHEIGHT)
						if(eliminaMuroV(this.muriV.get(i)))
							i--;
//			controllo se il muro è fuori dai bordi e li cancello
			if(!this.muri.isEmpty())
				for(int i=0;i<this.muri.size();i++)
					if(this.muri.get(i).getPosizione().y>=Cost.SCHERMOHEIGHT)
						if(eliminaMuro(this.muri.get(i)))
							i--;
//			creo altri muri
			if(!this.muri.isEmpty())
			{
				if(this.muri.get(this.muri.size()-1).getPosizione().y>=distanzaFraMuri)
					creaMuro();
			}
			else 
				creaMuro();
		}
//		la gravità va su
		else if(this.gravity.y<0.0f)
		{
//			controllo se il muroV è fuori dai bordi e li cancello
			if(!this.muriV.isEmpty())
				for(int i=0;i<this.muriV.size();i++)
					if(this.muriV.get(i).getPosizione().y+(Cost.MUROVHEIGHT[this.muriV.get(i).getMI()]/2.0f)<=0.0f)
						if(eliminaMuroV(this.muriV.get(i)))
							i--;
//			controllo se il muro è fuori dai bordi e li cancello
			if(!this.muri.isEmpty())
				for(int i=0;i<this.muri.size();i++)
					if(this.muri.get(i).getPosizione().y<=0.0f)
						if(eliminaMuro(this.muri.get(i)))
							i--;
//			creo altri muri
			if(!this.muri.isEmpty())
			{
				if(this.muri.get(this.muri.size()-1).getPosizione().y<=Cost.SCHERMOHEIGHT-distanzaFraMuri)
					creaMuro();
			}
			else
				creaMuro();
		}
//		la gravità va a destra
		else if(this.gravity.x>0.0f)
		{
//			controllo se il muro è fuori dai bordi e li cancello
			if(!this.muri.isEmpty())
				for(int i=0;i<this.muri.size();i++)
					if(this.muri.get(i).getPosizione().x-(Cost.MUROWIDTH[this.muri.get(i).getMI()]/2.0f)>=Cost.SCHERMOWIDTH)
						if(eliminaMuro(this.muri.get(i)))
							i--;
//			controllo se il muroV è fuori dai bordi e li cancello
			if(!this.muriV.isEmpty())
				for(int i=0;i<this.muriV.size();i++)
					if(this.muriV.get(i).getPosizione().x>=Cost.SCHERMOWIDTH)
						if(eliminaMuroV(this.muriV.get(i)))
							i--;
//			creo altri muri
			if(!this.muriV.isEmpty())
			{
				if(this.muriV.get(this.muriV.size()-1).getPosizione().x>=distanzaFraMuriV)
					creaMuro();
			}
			else
				creaMuro();
				
		}
//		la gravità va a sinistra
		else if(this.gravity.x<0.0f)
		{
//			controllo se il muro è fuori dai bordi e li cancello
			if(!this.muri.isEmpty())
				for(int i=0;i<this.muri.size();i++)
					if(this.muri.get(i).getPosizione().x+(Cost.MUROWIDTH[this.muri.get(i).getMI()]/2.0f)<=0.0f)
						if(eliminaMuro(this.muri.get(i)))
							i--;
//			controllo se il muroV è fuori dai bordi e li cancello
			if(!this.muriV.isEmpty())
				for(int i=0;i<this.muriV.size();i++)
					if(this.muriV.get(i).getPosizione().x<=0.0f)
						if(eliminaMuroV(this.muriV.get(i)))
							i--;
//			creo altri muri
			if(!this.muriV.isEmpty())
			{
				if(this.muriV.get(this.muriV.size()-1).getPosizione().x<=Cost.SCHERMOWIDTH-distanzaFraMuriV)
					creaMuro();
			}
			else
				creaMuro();
		}
	}
	
	/**
	 * disegnat tutti i muri sullo schermo
	 * @param surf il layer dove disegno i muri
	 */
	public void paint(SpriteBatch sprite)
	{
//		la gravità va su o giù
			if(!this.muri.isEmpty())
				for(Muro m: this.muri)
					m.paint(sprite);
//		la gravità va a destra o a sinistra
			if(!this.muriV.isEmpty())
				for(MuroV m: this.muriV)
					m.paint(sprite);
	}
	
	/**
	 * distrugge tutti i muri che ci sono!
	 */
	public void destroy()
	{
		this.muri.clear();
		this.muriV.clear();
	}
}