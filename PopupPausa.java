package it.movioletto.GravityBall3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Schermata di popup pausa
 * @author Movioletto
 */
public class PopupPausa
{
	private Bottone[] bottoni;
	private Mondo mondo;
	private Testo testi;
	private Sfondo sfondo;
	private Immagine img;
	private Vec2 misureLocali;
	private Tipo t;

	/**
	 * Crea la schermata e tutte le sue componenti
	 * @param surf il layer dove disegno
	 */
	public PopupPausa(Game game)
	{
		this.sfondo=new Sfondo(Tipo.SFONDOPOPUP);
		this.mondo=new Mondo();
		this.img=new Immagine(new Vec2(Cost.SCHERMOWIDTH/2.0f, Cost.SCHERMOHEIGHT/2.0f), Tipo.POPUP400X250);
		this.t=Tipo.POPUP400X250;
		this.misureLocali=new Vec2((Cost.SCHERMOWIDTH/2.0f)+(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f), Cost.SCHERMOHEIGHT/2.0f+(Cost.texture[Cost.tipoIndexOf(this.t)].getHeight()/2.0f));
		Bottone[] botton={
							new Bottone(Tipo.TESTO100, "continua", new Vec2(this.misureLocali.x-(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f), this.misureLocali.y-90.0f-(Cost.font100m.y/2.0f)), mondo, 14, game),
							new Bottone(Tipo.TESTO100, "menu'", new Vec2(this.misureLocali.x-(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f), this.misureLocali.y-110.0f-(Cost.font100m.y/2.0f)*3), mondo, 7, game),
							new Bottone(Tipo.TESTO100, "esci", new Vec2(this.misureLocali.x-(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f), this.misureLocali.y-130.0f-(Cost.font100m.y/2.0f)*5), mondo, 3, game)
							};
		this.bottoni=botton;
		this.testi=new Testo(Tipo.TESTO150, "pausa", new Vec2(this.misureLocali.x-(Cost.texture[Cost.tipoIndexOf(this.t)].getWidth()/2.0f), this.misureLocali.y-10));	
	}
	
	/**
	 * aggiorna tutte le componenti del popup
	 * @param ms lo stato del mouse
	 */
	public void update(Vector3 touch) 
	{
		this.mondo.step();
		for(Bottone b: this.bottoni)
			b.update(touch);
	}
	
	/**
	 * disegna tutte le componenti del popup
	 * @param surf il layer dove disegno
	 */
	public void paint(SpriteBatch sprite)
	{
		this.sfondo.paint(sprite);
		this.img.paint(sprite);
		for(Bottone b: this.bottoni)
			b.paint(sprite);
		this.testi.paint(sprite);
	}		
}