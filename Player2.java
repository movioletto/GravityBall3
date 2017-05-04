package it.movioletto.GravityBall3;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Crea il player, lo gestisce e lo disegna
 * @author Movioletto
 */
public class Player2
{
	private float vel;
	private Vec2 misure=new Vec2(Cost.texture[1].getWidth(), Cost.texture[1].getHeight());
	private Corpo c;
	private boolean multi;

	/**
	 * crea il corpo e lo inserisce nel mondo fisico
	 * @param position posizione del corpo nel mondo fisico
	 * @param surf il layer dove disegnare il player
	 * @param mondo il mondo in cui inserire il corpo
	 * @param gravity la gravità che deve avere il player
	 */
	public Player2(Vec2 position, Mondo mondo, Vec2 gravity, boolean multi)
	{
		ContactListener ct=new ContactListener()
		{
			public void contattoSubito(Corpo a, int pos)
			{
				reazione(c, a, pos);
			}
			public void contattoCausato(Corpo a, int pos)
			{
				reazione(c, a, pos);
			}
		};
		this.multi=multi;
		this.vel= 10.0f;
		this.c= new Corpo(position, this.vel, CorpoTipo.DINAMICO, CorpoForma.CERCHIO, this.misure.x/2.0f, ct, gravity, Tipo.PALLA, mondo);
		mondo.addCorpo(this.c);
	}

	/**
	 * gestisce gli spostamenti del player
	 * @param ks lo stato della tastiera
	 * @param ms lo stato del mouse
	 */
	public void update(OrthographicCamera camera) 
	{
		GetInput(camera);
	}

	/**
	 * Metodo getter della posizione del corpo
	 * @return la posizione del corpo
	 */
	public Vec2 getPosizione() 
	{
		return this.c.getPosizione();
	}

	/**
	 * cambio la gravità del player
	 * @param gravity la nuova gravità che sostituisce la vecchia
	 */
	public void cambioGravity(Vec2 gravity)
	{
		this.c.setGravity(gravity);
	}

	/**
	 * incrementa la velocità del player della quantità passata come parametro
	 * @param g la quantità che incrementa la velocità
	 */
	public void incrementoVelocita(float g)
	{
		this.vel+=g;
	}

	/**
	 * disegna il player
	 * @param surf il layer dove disegnare il player
	 */
	public void paint(SpriteBatch sprite)
	{
		sprite.draw(Cost.texture[1], this.c.getPosizione().x-Cost.texture[1].getWidth()/2, this.c.getPosizione().y-Cost.texture[1].getHeight()/2);
	}

	private void GetInput(OrthographicCamera camera) 
	{
		if(multi)
		{
			ArrayList<Vector3> touch=new ArrayList<Vector3>();
			int i=0;
			while(Gdx.input.isTouched(i))
			{
				touch.add(new Vector3());
				camera.unproject(touch.get(i).set(Gdx.input.getX(i), Gdx.input.getY(i), 0));
				i++;
			}
			i=0;
			for(Vector3 vec: touch)
				if(vec.x>Cost.SCHERMOWIDTH/2)
					i=touch.indexOf(vec);
	//		gravit� sinistra/destra
			if(this.c.getGravity().x!=0.0f)
			{
				if(Gdx.input.isTouched(i))
				{
					if(touch.get(i).x>Cost.SCHERMOWIDTH/2)
						if(touch.get(i).y>Cost.SCHERMOHEIGHT/2)
							vaiGiu(this.vel);
						else
							vaiSu(this.vel);
				}
			}
	//		gravit� su/giu
			else if(this.c.getGravity().y!=0.0f)
			{
				if(Gdx.input.isTouched(i))
				{
					if(touch.get(i).x>Cost.SCHERMOWIDTH/2)
						if(touch.get(i).x>(Cost.SCHERMOWIDTH/4)*3)
							vaiADestra(this.vel);
						else
							vaiASinistra(this.vel);
				}
			}
		}
		else
		{

		}		
	}

	private void vaiADestra(float vel)
	{
		this.c.applicaForza(new Vec2(vel, 0.0f), false);
	}

	private void vaiASinistra(float vel)
	{
		this.c.applicaForza(new Vec2(-vel, 0.0f), false);
	}
	
	private void vaiSu(float vel)
	{
		this.c.applicaForza(new Vec2(0.0f, -vel), false);
	}

	private void vaiGiu(float vel)
	{
		this.c.applicaForza(new Vec2(0.0f, vel), false);
	}
}