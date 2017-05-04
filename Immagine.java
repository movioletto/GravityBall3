package it.movioletto.GravityBall3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Crea e disegna un'immagine sullo schermo
 * @author Movioletto
 */
public class Immagine
{
	private Vec2 position;
	private Tipo t;
	
	/**
	 * crea l'immagine
	 * @param position la posizione dell'immagine sullo schermo ( dove la disegno )
	 * @param t quale immagine devo disegnare
	 */
	public Immagine(Vec2 position, Tipo t)
	{
		this.t=t;
		this.position=new Vec2(position.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2, position.y-Cost.texture[Cost.tipoIndexOf(t)].getHeight()/2);
	}

	public void paint(SpriteBatch sprite)
	{
		sprite.draw(Cost.texture[Cost.tipoIndexOf(t)], this.position.x, this.position.y);
	}
}