package it.movioletto.GravityBall3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Crea e disegna un'immagine sullo schermo
 * @author Movioletto
 */
public class ImmagineAnimata
{
	private Vec2 position;
	private int index;
	private float stateTime;
	
	/**
	 * crea l'immagine
	 * @param position la posizione dell'immagine sullo schermo ( dove la disegno )
	 * @param t quale immagine devo disegnare
	 */
	public ImmagineAnimata(Vec2 position, int i)
	{
		this.stateTime=0f;
		this.index=i;
		this.position=new Vec2(position.x-Cost.animazioni[i].getKeyFrame(0).getRegionWidth()/2, position.y-Cost.animazioni[i].getKeyFrame(0).getRegionHeight()/2);
	}

	public void paint(SpriteBatch sprite)
	{
        stateTime += Gdx.graphics.getDeltaTime(); 
        sprite.draw(Cost.animazioni[index].getKeyFrame(stateTime, true), this.position.x, this.position.y);
	}
}