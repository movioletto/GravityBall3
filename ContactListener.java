package it.movioletto.GravityBall3;


/**
 * viene chiamato quando c'è una contatto tra due corpi
 * @author Movioletto
 */
public abstract class ContactListener
{
	public abstract void contattoCausato(Corpo a, int pos);
	public abstract void contattoSubito(Corpo a, int pos);
	
	/**
	 * reazione tra due corpi che si toccano
	 * @param c corpo che tocca l'oggetto
	 * @param a corpo che subisce il tocco
	 * @param pos posizione del corpo c rispetto il corpo a
	 *
	 *       |    |
	 *    #1 | #2 | #3          #123
	 *	-----|____|------
	 *    #4 | #5 | #6          #456
	 *	-----|____|------
	 *	  #7 | #8 | #9          #789
	 *       |    |
	 *
	 */
	public void reazione(Corpo c /* corpo che lo richiama */, Corpo a, int pos)
	{
//		la gravità del corpo è a destra
		if(c.getGravity().x>0.0f)
			switch(pos)
			{
				case 1:
					c.applicaForza(a.getGravity(), true);
					break;
				case 2:
					c.applicaForza(new Vec2(0.0f, -c.getVel()), true);
					break;
				case 3:
					c.applicaForza(new Vec2(0.0f, -c.getVel()), true);
					break;
				case 4:
					c.applicaForza(a.getGravity(), true);
					break;
				case 5:
					c.applicaForza(new Vec2(-(Cost.textureMatrix[2][0].getWidth()/2/*+a.getGravity().x*/), 0.0f), true);
					c.applicaGravita();
					break;
				case 6:
					c.applicaForza(new Vec2(c.getVel(), 0.0f), true);
					break;
				case 7:
					c.applicaForza(a.getGravity(), true);
					break;
				case 8:
					c.applicaForza(new Vec2(0.0f, c.getVel()), true);
					break;
				case 9:
					c.applicaForza(new Vec2(0.0f, c.getVel()), true);
					break;
			}
//		la gravità del corpo è a sinistra
		if(c.getGravity().x<0.0f)
			switch(pos)
			{
				case 1:
					c.applicaForza(new Vec2(0.0f, -c.getVel()), true);
					break;
				case 2:
					c.applicaForza(new Vec2(0.0f, -c.getVel()), true);
					break;
				case 3:
					c.applicaForza(a.getGravity(), true);
					break;
				case 4:
					c.applicaForza(new Vec2(-c.getVel(), 0.0f), true);
					break;
				case 5:
					c.applicaForza(new Vec2(Cost.textureMatrix[2][0].getWidth()/2/*+a.getGravity().x*/, 0.0f), true);
					c.applicaGravita();
					break;
				case 6:
					c.applicaForza(a.getGravity(), true);
					break;
				case 7:
					c.applicaForza(new Vec2(0.0f, c.getVel()), true);
					break;
				case 8:
					c.applicaForza(new Vec2(0.0f, c.getVel()), true);
					break;
				case 9:
					c.applicaForza(a.getGravity(), true);
					break;
			}
//		la gravità del corpo è giù
		if(c.getGravity().y>0.0f)
			switch(pos)
			{
				case 1:
					c.applicaForza(a.getGravity(), true);
					break;
				case 2:
					c.applicaForza(a.getGravity(), true);
					break;
				case 3:
					c.applicaForza(a.getGravity(), true);
					break;
				case 4:
					c.applicaForza(new Vec2(-c.getVel(), 0.0f), true);
					break;
				case 5:
					c.applicaForza(new Vec2(0.0f, -(Cost.textureMatrix[2][0].getWidth()/2/*+a.getGravity().y*/)), true);
					c.applicaGravita();
					break;
				case 6:
					c.applicaForza(new Vec2(c.getVel(), 0.0f), true);
					break;
				case 7:
					c.applicaForza(new Vec2(-c.getVel(), 0.0f), true);
					break;
				case 8:
					c.applicaForza(new Vec2(0.0f, c.getVel()), true);
					break;
				case 9:
					c.applicaForza(new Vec2(c.getVel(), 0.0f), true);
					break;
			}
//		la gravità del corpo è su
		if(c.getGravity().y<0.0f)
			switch(pos)
			{
				case 1:
					c.applicaForza(new Vec2(-c.getVel(), 0.0f), true);
					break;
				case 2:
					c.applicaForza(new Vec2(0.0f, -c.getVel()), true);
					break;
				case 3:
					c.applicaForza(new Vec2(c.getVel(), 0.0f), true);
					break;
				case 4:
					c.applicaForza(new Vec2(-c.getVel(), 0.0f), true);
					break;
				case 5:
					c.applicaForza(new Vec2(0.0f, Cost.textureMatrix[2][0].getWidth()/2/*+a.getGravity().y*/), true);
					c.applicaGravita();
					break;
				case 6:
					c.applicaForza(new Vec2(c.getVel(), 0.0f), true);
					break;
				case 7:
					c.applicaForza(a.getGravity(), true);
					break;
				case 8:
					c.applicaForza(a.getGravity(), true);
					break;
				case 9:
					c.applicaForza(a.getGravity(), true);
					break;
			}
	}
}