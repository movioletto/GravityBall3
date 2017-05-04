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
public class Player
{
	private float vel;
	private Vec2 misure=new Vec2(Cost.texture[0].getWidth(), Cost.texture[0].getHeight());
	private Corpo c;
	private boolean multi;

	/**
	 * crea il corpo e lo inserisce nel mondo fisico
	 * @param position posizione del corpo nel mondo fisico
	 * @param surf il layer dove disegnare il player
	 * @param mondo il mondo in cui inserire il corpo
	 * @param gravity la gravit√† che deve avere il player
	 */
	public Player(Vec2 position, Mondo mondo, Vec2 gravity, boolean multi)
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
	 * Metodo getter della posizione del corpo
	 * @return la posizione del corpo
	 */
	public Vec2 getPosizione() 
	{
		return this.c.getPosizione();
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
	 * cambio la gravit√† del player
	 * @param gravity la nuova gravit√† che sostituisce la vecchia
	 */
	public void cambioGravity(Vec2 gravity)
	{
		this.c.setGravity(gravity);
	}

	/**
	 * incrementa la velocit√† del player della quantit√† passata come parametro
	 * @param g la quantit√† che incrementa la velocit√†
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
		sprite.draw(Cost.texture[0], this.c.getPosizione().x-Cost.texture[0].getWidth()/2, this.c.getPosizione().y-Cost.texture[0].getHeight()/2);
	}

	private void GetInput(OrthographicCamera camera) 
	{
		
//		if (ks.IsKeyDown(Key.RIGHT))
//	    {
//	    	vaiADestra(this.vel);
//	    }
//		else if (ks.IsKeyDown(Key.LEFT))
//	    {
//	    	vaiASinistra(this.vel);
//	    }
//		else if (ks.IsKeyDown(Key.UP))
//	    {
//	    	vaiSu(this.vel);
//	    }
//		else if (ks.IsKeyDown(Key.DOWN))
//	    {
//	    	vaiGiu(this.vel);
//	    }

////		gravit√† sinistra/destra
//		if(this.c.getGravity().x!=0.0f)
//		{
//			if(ms.isButtonDown(0))
//				vaiGiu(this.vel);
//			else if(ms.isButtonDown(2))
//				vaiSu(this.vel);
//		}
////		gravit√† su/giu
//		else if(this.c.getGravity().y!=0.0f)
//		{
//			if(ms.isButtonDown(0))
//				vaiASinistra(this.vel);
//			else if(ms.isButtonDown(2))
//				vaiADestra(this.vel);
//		}

		
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
				if(vec.x<Cost.SCHERMOWIDTH/2)
					i=touch.indexOf(vec);
	//		gravit‡ sinistra/destra
			if(this.c.getGravity().x!=0.0f)
			{
				if(Gdx.input.isTouched(i))
					if(touch.get(i).x<Cost.SCHERMOWIDTH/2)
						if(touch.get(i).y>Cost.SCHERMOHEIGHT/2)
							vaiGiu(this.vel);
						else
							vaiSu(this.vel);
			}
	//		gravit‡ su/giu
			else if(this.c.getGravity().y!=0.0f)
			{
				if(Gdx.input.isTouched(i))
					if(touch.get(i).x<Cost.SCHERMOWIDTH/2)
						if(touch.get(i).x>Cost.SCHERMOWIDTH/4)
							vaiADestra(this.vel);
						else
							vaiASinistra(this.vel);
			}
		}
		else
		{
			if(Cost.touch)
			{
				ArrayList<Vector3> touch=new ArrayList<Vector3>();
				int i=0;
				while(Gdx.input.isTouched(i))
				{
					touch.add(new Vector3());
					camera.unproject(touch.get(i).set(Gdx.input.getX(i), Gdx.input.getY(i), 0));
//					Gdx.app.log("touch", i+" "+touch.get(i).toString());
					i++;
				}
				if(touch.size()!=0)
					i=touch.size()-1;
	//			i--;
		//		gravit‡ sinistra/destra
				if(this.c.getGravity().x!=0.0f)
				{
					if(Gdx.input.isTouched(i))
					{
						if(touch.size()!=0)
							if(touch.get(i).y>Cost.SCHERMOHEIGHT/2)
								vaiGiu(this.vel);
							else
								vaiSu(this.vel);
					}
				}
		//		gravit‡ su/giu
				else if(this.c.getGravity().y!=0.0f)
				{
					if(Gdx.input.isTouched(i))
					{
						if(touch.size()!=0)
							if(touch.get(i).x>Cost.SCHERMOWIDTH/2)
								vaiADestra(this.vel);
							else
								vaiASinistra(this.vel);
					}
				}
			}
			if(Cost.accel)
			{
				if(Cost.rotazione)
				{
					//		gravit‡ sinistra/destra
					if(this.c.getGravity().x!=0.0f)
					{
						if(Gdx.input.getAccelerometerX()>1)
							vaiGiu((this.vel/6)*Math.abs(Gdx.input.getAccelerometerX()));
						if(Gdx.input.getAccelerometerX()<-1)
							vaiSu(-(this.vel/6)*Gdx.input.getAccelerometerX());
					}
			//		gravit‡ su/giu
					else if(this.c.getGravity().y!=0.0f)
					{
						if(Gdx.input.getAccelerometerY()<-1)
							vaiADestra(-(this.vel/6)*Gdx.input.getAccelerometerY());
						if(Gdx.input.getAccelerometerY()>1)
							vaiASinistra((this.vel/6)*Math.abs(Gdx.input.getAccelerometerY()));
					}
				}
				else
				{
					//		gravit‡ sinistra/destra
					if(this.c.getGravity().x!=0.0f)
					{
						if(Gdx.input.getAccelerometerX()>1)
							vaiSu((this.vel/6)*Gdx.input.getAccelerometerX());
						if(Gdx.input.getAccelerometerX()<-1)
							vaiGiu((this.vel/6)*Math.abs(Gdx.input.getAccelerometerX()));
					}
			//		gravit‡ su/giu
					else if(this.c.getGravity().y!=0.0f)
					{
						if(Gdx.input.getAccelerometerY()<-1)
							vaiASinistra((this.vel/6)*Math.abs(Gdx.input.getAccelerometerY()));
						if(Gdx.input.getAccelerometerY()>1)
							vaiADestra((this.vel/6)*Gdx.input.getAccelerometerY());
					}
				}
			}
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