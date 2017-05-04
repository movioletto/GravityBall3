package it.movioletto.GravityBall3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


/**
 * Schermata di popup di fine partita multiplayer
 * @author Movioletto
 */
public class PopupFineMulti 
{
	private Bottone bottoni;
	private Mondo mondo;
	private Testo[] testi;
	private Sfondo sfondo;
	private Immagine img;
	private Tipo t;
	private Vec2 misureLocali;

	/**
	 * creo la schermata
	 * @param surf il layer dove disegno
	 */
	public PopupFineMulti(Game game)
	{
		this.sfondo=new Sfondo(Tipo.SFONDOPOPUP);
		this.img=new Immagine(new Vec2(Cost.SCHERMOWIDTH/2.0f, Cost.SCHERMOHEIGHT/2.0f), Tipo.POPUP750X310);
		this.mondo=new Mondo();
		this.t=Tipo.POPUP750X310;
		this.misureLocali=new Vec2((Cost.SCHERMOWIDTH/2.0f)+(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f), Cost.SCHERMOHEIGHT/2.0f+(Cost.texture[Cost.tipoIndexOf(this.t)].getHeight()/2.0f));
		this.bottoni= new Bottone(Tipo.TESTO100, "ok", new Vec2(this.misureLocali.x-20.0f-Cost.font150m.x, this.misureLocali.y-Cost.texture[Cost.tipoIndexOf(this.t)].getHeight()+Cost.font150m.y), this.mondo, 7, game);
		Testo[] test={
				new Testo(Tipo.TESTO150, "player", new Vec2(this.misureLocali.x-(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f)-10.0f, this.misureLocali.y-(Cost.font150m.y/2.0f)-20.0f)),
				new Testo(Tipo.TESTO150, String.valueOf(CampoMulti.getVincitore()), new Vec2(this.misureLocali.x-(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f)-10.0f, this.misureLocali.y-(Cost.font150m.y/2.0f)*2-Cost.font150m.y-10.0f)),
				new Testo(Tipo.TESTO150, "vince!", new Vec2(this.misureLocali.x-(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f)+20.0f, this.misureLocali.y-(Cost.font150m.y/2.0f)*3-Cost.font150m.y*2)),
						};
		this.testi=test;
	}

	/**
	 * aggiorna tutti i componenti della schermata
	 * @param ms lo stato del mouse 
	 */
	public void update(Vector3 touch) 
	{
		this.mondo.step();
		this.bottoni.update(touch);
		if(this.testi[1].getString().equals("0"))
			this.testi[1]=new Testo(Tipo.TESTO150, String.valueOf(CampoMulti.getVincitore()), new Vec2(this.misureLocali.x-(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f)-10.0f, this.misureLocali.y-(Cost.font150m.y/2.0f)*2-Cost.font150m.y-10.0f));
		
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
	}		
}