package it.movioletto.GravityBall3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * lo sfondo delle schermate
 * @author Movioletto
 */
public class Sfondo
{
	private Vec2 position;
	private Tipo t;
	
	/**
	 * crea lo sfondo da un immagine
	 * @param t il tipo dell'immagine
	 */
	public Sfondo( Tipo t)
	{
		this.t=t;
		this.position= new Vec2(0,0);
	}

	/**
	 * disegno lo sfondo
	 * @param surf layer dove disegno
	 */
	public void paint(SpriteBatch sprite)
	{
		sprite.draw(Cost.texture[Cost.tipoIndexOf(t)], this.position.x, this.position.y);
		Cost.font70.draw(sprite, "memory: " + Gdx.app.getJavaHeap() + ", " + Gdx.app.getNativeHeap(), 30, 700);
	}
}