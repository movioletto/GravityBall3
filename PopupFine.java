package it.movioletto.GravityBall3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Schermata di popup quando finisce la partita singleplayer
 * @author Movioletto
 */
public class PopupFine
{
	private Bottone bottoni;
	private Mondo mondo;
	private Testo[] testi;
	private Sfondo sfondo;
	private Immagine img;
	private Immagine tot;
	private Tipo t;
	private Vec2 misureLocali;
	
	/**
	 * creo la schermata
	 * @param surf il layer dove disegno
	 */
	public PopupFine(Game game)
	{
		this.sfondo=new Sfondo(Tipo.SFONDOPOPUP);
		this.img=new Immagine(new Vec2(Cost.SCHERMOWIDTH/2.0f, Cost.SCHERMOHEIGHT/2.0f), Tipo.POPUP750X310);
		this.mondo=new Mondo();
		this.t=Tipo.POPUP750X310;
		this.misureLocali=new Vec2((Cost.SCHERMOWIDTH/2.0f)+(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f), Cost.SCHERMOHEIGHT/2.0f+(Cost.texture[Cost.tipoIndexOf(this.t)].getHeight()/2.0f));
		this.bottoni= new Bottone(Tipo.TESTO100, "ok", new Vec2(this.misureLocali.x-Cost.font100m.x-10, this.misureLocali.y-Cost.texture[Cost.tipoIndexOf(this.t)].getHeight()+Cost.font100m.y+10), this.mondo, 27, game);
		
		Testo[] test={
				new Testo(Tipo.TESTO150, "HAI PERSO!", new Vec2(this.misureLocali.x-(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f)+20, this.misureLocali.y-(Cost.font150m.y/2.0f)+20)),
				new Testo(Tipo.TESTO100, "punti", new Vec2(this.misureLocali.x-Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()+15+(Cost.font100m.x*5)/2.0f, this.misureLocali.y-Cost.font150m.y-40.0f)),
				new Testo(Tipo.TESTO100, "soldi", new Vec2(this.misureLocali.x-Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()+15+(Cost.font100m.x*5)/2.0f, this.misureLocali.y-Cost.font150m.y-Cost.font100m.y-50.0f)),
				new Testo(Tipo.TESTO100, String.valueOf(Punteggio.getPunteggio()), new Vec2(this.misureLocali.x+40.0f-Cost.font100m.x*5-Cost.font100m.x-Cost.font100m.x/2-20, this.misureLocali.y-Cost.font150m.y-62.0f)),
				new Testo(Tipo.TESTO100, String.valueOf(Punteggio.getSoldi()), new Vec2(this.misureLocali.x+40.0f-Cost.font100m.x*5-Cost.font100m.x-Cost.font100m.x/2-20, this.misureLocali.y-Cost.font150m.y-Cost.font100m.y-72.0f)),
				new Testo(Tipo.TESTO100, "+", new Vec2(this.misureLocali.x-Cost.font100m.x/2-10, this.misureLocali.y-Cost.font150m.y-40.0f)),
				new Testo(Tipo.TESTO100, "=", new Vec2(this.misureLocali.x-Cost.font100m.x/2-10, this.misureLocali.y-Cost.font150m.y-Cost.font100m.y-50.0f)),
				new Testo(Tipo.TESTO100, String.valueOf(Punteggio.getPunteggioFinale()), new Vec2(this.misureLocali.x+40.0f-Cost.font100m.x*5-Cost.font100m.x-Cost.font100m.x/2-20, this.misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-95.0f))
						};
		this.testi=test;
		this.tot=new Immagine(new Vec2(this.misureLocali.x+45.0f-Cost.font100m.x*5-Cost.font100m.x-Cost.font100m.x/2-20, this.misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-60.0f), Tipo.TOTALE);
	}
	
	/**
	 * aggiorna tutti i componenti della schermata
	 * @param ms lo stato del mouse 
	 */
	public void update(Vector3 touch) 
	{
		this.mondo.step();
		this.bottoni.update(touch);
		if(this.testi[3].getString().equals("0"))
		{
			this.testi[3].cambiaStringa(aggiungiZeri(Punteggio.getPunteggio()));
			this.testi[4].cambiaStringa(aggiungiZeri(Punteggio.getSoldi()));
			this.testi[7].cambiaStringa(aggiungiZeri(Punteggio.getPunteggioFinale()));
		}
		
	}
	
	/**
	 * disegna tutti i componenti sullo schermo
	 * @param surf il layer dove disegnare i componenti
	 */
	public void paint(SpriteBatch sprite)
	{
		this.sfondo.paint(sprite);
		this.img.paint(sprite);
		this.bottoni.paint(sprite);
		for(Testo t: this.testi)
			t.paint(sprite);
		this.tot.paint(sprite);
	}
	
	private String aggiungiZeri(int j)
	{
		String s="";
		int k=9-String.valueOf(j).length();
		for(int i=0;i<k;i++)
			s+="0";
		return s+String.valueOf(j);
	}
}