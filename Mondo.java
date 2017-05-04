package it.movioletto.GravityBall3;

import java.util.ArrayList;

/**
 * il mondo fisico che gestisce tutto, contatto tra i corpi, la gravità dei corpi
 * @author Movioletto
 */
public class Mondo
{
	private ArrayList<Corpo> corpi= new ArrayList<Corpo>();
	private float vicinanza= 500f;
	
	/**
	 * Crea il mondo rastrellizzando tutti i corpi precedenti
	 */
	public Mondo()
	{
		corpi.clear();
	}
	
	/**
	 * aggiunge un corpo al mondo fisico
	 * @param corpo il corpo da aggiungere
	 */
	public void addCorpo(Corpo corpo)
	{
		boolean trovato=false;
		for(Corpo p:this.corpi)
			if(p.equals(corpo))
				trovato=true;
		if(!trovato)
			this.corpi.add(corpo);
	}
	
	/**
	 * rimuove un corpo dal mondo fisico
	 * @param corpo il corpo da rimuovere
	 */
	public void removeCorpo(Corpo corpo)
	{
		this.corpi.remove(corpo);
	}
	
	/**
	 * Metodo getter dell'indice di un corpo
	 * @param corpo il corpo da ricercare
	 * @return l'indice del corpo
	 */
	public int getCorpo(Corpo corpo)
	{
		return this.corpi.indexOf(corpo);
	}
	
	/*
	 *  restituisce se vero se non c'è nessun corpo nel mondo, altrimenti false
	 */
	/**
	 * Ritorna se il mondo è vuoto di corpi
	 * @return <ul><li>true, se il mondo è vuoto</li><li>false, se nel mondo c'è minimo un corpo</li></ul>
	 */
	public boolean isEmpty()
	{
		return this.corpi.isEmpty();
	}

	/**
	 * applica la gravità di tutti i corpi del mondo fisico
	 */
	public void step()
	{
		if(!this.corpi.isEmpty())
			for(int i=0;i<this.corpi.size();i++)
				if(!this.corpi.get(i).getEsiste())
				{
					corpi.remove(i);
					i--;
				}

		if(!this.corpi.isEmpty())
			for(int i=0;i<this.corpi.size();i++)
				if(!this.corpi.get(i).getGravity().equals(new Vec2(0.0f, 0.0f)))
					this.corpi.get(i).applicaGravita();
//			for(Corpo c:corpi)
//				if(!c.getGravity().equals(new Vec2(0.0f, 0.0f)))
//					c.applicaGravita();
	}

	/**
	 * reset della gravità
	 */
	public void resetGravity()
	{
		for(Corpo p: this.corpi)
			p.setGravity(p.getGravityPre());
	}
	
	/**
	 * setta la gravità di tutti i corpi a zero
	 */
	public void setGravityZero()
	{
		for(Corpo p: this.corpi)
			p.setGravity(new Vec2(0.0f, 0.0f));
	}
	
	/**
	 * Metodo getter della gravità del player
	 * @return la gravità del player
	 */
	public Vec2 getGravity()
	{
		for(Corpo p: this.corpi)
			if(p.getEsiste())
				if(p.getForma().equals(CorpoForma.CERCHIO))
					return p.getGravity();
		return new Vec2(0.0f, 0.0f);
	}
	
	/**
	 * cerca tutti i corpi vicini al corpo passato come parametro
	 * @param a il corpo
	 * @return ArrayList dei corpi vicini
	 */
	public ArrayList<Corpo> corpiVicini(Corpo a)
	{
		float maxL=0;
		if(a.getForma()==CorpoForma.RETTANGOLO || a.getForma()==CorpoForma.QUADRATO)
			if(a.getMetaAltezza()>=a.getMetaLarghezza())
				maxL=a.getMetaAltezza();
			else 
				maxL=a.getMetaLarghezza();
		if(a.getForma()==CorpoForma.CERCHIO)
			maxL=a.getRaggio();
		
		ArrayList<Corpo> ritorno= new ArrayList<Corpo>();

//			for(Corpo b: this.corpi)
//				if(b.getEsiste())
//					if(!b.equals(a))
//						if(b.getTipo()!=CorpoTipo.FISSO)
//							if(distanzaCentro(a,b)<=this.vicinanza+maxL)
//								ritorno.add(b);
		for(int i=0;i<this.corpi.size();i++)
			if(this.corpi.get(i).getEsiste())
				if(!this.corpi.get(i).equals(a))
					if(this.corpi.get(i).getTipo()!=CorpoTipo.FISSO)
						if(distanzaCentro(a,this.corpi.get(i))<=this.vicinanza+maxL)
							ritorno.add(this.corpi.get(i));
		return ritorno;
	}
	
	/**
	 * cerca il corpo più vicino a quello passato come parametro
	 * @param a il corpo
	 * @return il corpo trovato
	 */
	public Corpo corpiPiuVicino(Corpo a)
	{
		Corpo ritorno=new Corpo();
		float temp=1200;
//		for(Corpo b: this.corpi)
//			if(b.getEsiste())
//				if(!b.equals(a))
//					if(temp>distanzaCentro(a,b))
//					{
//						temp=distanzaCentro(a,b);
//						ritorno=b;
//					}
		for(int i=0;i<this.corpi.size();i++)
			if(this.corpi.get(i).getEsiste())
				if(!this.corpi.get(i).equals(a))
					if(temp>distanzaCentro(a,this.corpi.get(i)))
					{
						temp=distanzaCentro(a,this.corpi.get(i));
						ritorno=this.corpi.get(i);
					}
		return ritorno;			
	}
	
	/**
	 * distanza centro-centro di due corpi
	 * @param a il primo corpo
	 * @param b il secondo corpo
	 * @return la distanza tra i centri dei due corpi 
	 */
	public float distanzaCentro(Corpo a, Corpo b)
	{
		return distanzaTraDuePunti(a.getPosizione(), b.getPosizione());
	}
	
	/**
	 * distanza matematica tra due punti qualsiasi
	 * @param a il primo punto
	 * @param b il secondo punto
	 * @return la distanza tra due punti
	 */
	public float distanzaTraDuePunti(Vec2 a, Vec2 b)
	{
		if(a.x==b.x)
			if(a.y>b.y)
				return a.y-b.y;
			else
				return b.y-a.y;
		else if(a.y==b.y)
			if(a.x>b.x)
				return a.x-b.x;
			else
				return b.x-a.x;
		float dx=(float)Math.abs(a.x-b.x);
		float dy=(float)Math.abs(a.y-b.y);
		if(dx>dy)
			return (float)(dx+dy-((5/8)*dy));
		else
			return (float)(dx+dy-((5/8)*dx));
	}
	
	/**
	 * gestisce le collisioni del corpo passato come parametro
	 * @param c il corpo
	 * @return <ul><li>true, se il corpo tocca qualcosa</li><li>false, se il corpo NON tocca niente</li></ul>
	 */
	public boolean gestisciCollisioni(Corpo c)
	{
		if(controllaBordi(c))
		{
			fineGioco();
		}
//		trovo tutti i corpi vicini a c
		ArrayList<Corpo> vicinanze=corpiVicini(c);
		if(vicinanze.size()==0)
			return false;

//		tutte le collisioni con i corpi nelle vicinanze sono false
		ArrayList<Coppia<Boolean, Integer>> colli=new ArrayList<Coppia<Boolean, Integer>>();
		for(int i=0;i<vicinanze.size();i++)
			colli.add(new Coppia<Boolean, Integer>(false, 0));
		
//		algoritmo del tocco
//		for(Corpo b: vicinanze)
//			if(b.getEsiste())
//				colli.set(vicinanze.indexOf(b), collisioneTraDueCorpi(c, b));
		for(int i=0;i<vicinanze.size();i++)
			if(vicinanze.get(i).getEsiste())
				colli.set(vicinanze.indexOf(vicinanze.get(i)), collisioneTraDueCorpi(c, vicinanze.get(i)));
		
		boolean bool=colli.get(0).getA();
		for(int i=0;i<colli.size();i++)
		{
			bool|=colli.get(i).getA();
			if(colli.get(i).getA())
				collisione(c, vicinanze.get(i), colli.get(i).getB());
		}
		return bool;
	}	

	/**
	 * Collisione tra due Corpi
	 * @param a il primo corpo
	 * @param b il secondo corpo
	 * @return <ul><li>true, se i due corpi sono in contatto</li><li>false, se i due corpi NON sono in contatto</li></ul>
	 */
	public Coppia<Boolean, Integer> collisioneTraDueCorpi(Corpo a, Corpo b)
	{
//		 ENTRAMBE LE FORME SONO SFERICHE
		if(a.getForma()==CorpoForma.CERCHIO && b.getForma()==CorpoForma.CERCHIO)
			if(distanzaCentro(a, b)<=((a.getRaggio()+b.getRaggio())+1))
				return new Coppia<Boolean, Integer>(true, 10);
			else
				return new Coppia<Boolean, Integer>(false, 0);
//		 ENTRAMBE LE FORME SONO QUADRILATERI
		if(a.getForma()==CorpoForma.RETTANGOLO || a.getForma()==CorpoForma.QUADRATO && b.getForma()==CorpoForma.RETTANGOLO || b.getForma()==CorpoForma.QUADRATO)
			;
//		 UNA è UN CERCHIO E L'ALTRA UN QUADRILATERO
		if((a.getForma()==CorpoForma.RETTANGOLO || a.getForma()==CorpoForma.QUADRATO && b.getForma()==CorpoForma.CERCHIO) || (b.getForma()==CorpoForma.RETTANGOLO || b.getForma()==CorpoForma.QUADRATO && a.getForma()==CorpoForma.CERCHIO))
		{
			
			if(a.getForma()==CorpoForma.CERCHIO)
				return collisioneTraCerchioRettangolo(a,b);
			return collisioneTraCerchioRettangolo(b,a);
		}
		return new Coppia<Boolean, Integer>(false, 0);
	}

	/**
	 * Collisioni tra un cerchio ed un rettangolo
	 * @param a il corpo del cerchio
	 * @param b il corpo del rettengolo
	 * @return una coppia vero/false e indice della posizione del cerchio rispetto il rettangolo
	 *       |    |
	 *    #1 | #2 | #3          #123
	 *	-----|____|------
	 *    #4 | #5 | #6          #456
	 *	-----|____|------
	 *	  #7 | #8 | #9          #789
	 *       |    |
	 *    
	 */
	public Coppia<Boolean, Integer> collisioneTraCerchioRettangolo(Corpo /*cerchio*/a, Corpo /*rettangolo*/b)
	{
//		#123
		if(a.getPosizione().y<(b.getPosizione().y-b.getMetaAltezza()))
//			il cerchio sta in alto a sinistra del rettangolo
//			#1
			if(a.getPosizione().x<(b.getPosizione().x-b.getMetaLarghezza()))
				if(distanzaTraDuePunti(b.getSpigoli().get(0), a.getPosizione())>a.getRaggio())
					return new Coppia<Boolean, Integer>(false, 1);
				else
					return new Coppia<Boolean, Integer>(true, 1);
//			il cerchio sta in alto a destra del rettangolo
//			#3
			else if(a.getPosizione().x>(b.getPosizione().x+b.getMetaLarghezza()))
				if(distanzaTraDuePunti(b.getSpigoli().get(1), a.getPosizione())>a.getRaggio())
					return new Coppia<Boolean, Integer>(false, 3);
				else
					return new Coppia<Boolean, Integer>(true, 3);
//			il cerchio sta in alto al centro del rettangolo
//			#2
			else
				if((b.getPosizione().y-a.getPosizione().y)>(a.getRaggio()+b.getMetaAltezza()))
					return new Coppia<Boolean, Integer>(false, 2);
				else
					return new Coppia<Boolean, Integer>(true, 2);
//		il cerchio sta sotto il rettangolo
//		#789
		else if(a.getPosizione().y>(b.getPosizione().y+b.getMetaAltezza()))
//			il cerchio sta in basso a sinistra del rettangolo
//			#7
			if(a.getPosizione().x<(b.getPosizione().x-b.getMetaLarghezza()))
				if(distanzaTraDuePunti(b.getSpigoli().get(3), a.getPosizione())>a.getRaggio())
					return new Coppia<Boolean, Integer>(false, 7);
				else
					return new Coppia<Boolean, Integer>(true, 7);
//			il cerchio sta in basso a destra del rettangolo
//			#9
			else if(a.getPosizione().x>(b.getPosizione().x+b.getMetaLarghezza()))
				if(distanzaTraDuePunti(b.getSpigoli().get(2), a.getPosizione())>a.getRaggio())
					return new Coppia<Boolean, Integer>(false, 9);
				else
					return new Coppia<Boolean, Integer>(true, 9);
//			il cerchio sta in basso al centro del rettangolo
//			#8
			else
				if((a.getPosizione().y-b.getPosizione().y)>(a.getRaggio()+b.getMetaAltezza()))
					return new Coppia<Boolean, Integer>(false, 8);
				else
					return new Coppia<Boolean, Integer>(true, 8);
//		il cerchio non sta ne sopra ne sotto il rettangolo
//		#456
		else
//			il cerchio sta a sinistra del rettangolo
//			#4
			if(a.getPosizione().x<(b.getPosizione().x-b.getMetaLarghezza()))
				if((b.getPosizione().x-a.getPosizione().x)>(a.getRaggio()+b.getMetaLarghezza()))
					return new Coppia<Boolean, Integer>(false, 4);
				else
					return new Coppia<Boolean, Integer>(true, 4);
//			il cerchio sta a destra del rettangolo
//			#6
			else if(a.getPosizione().x>(b.getPosizione().x+b.getMetaLarghezza()))
				if((a.getPosizione().x-b.getPosizione().x)>(a.getRaggio()+b.getMetaLarghezza()))
					return new Coppia<Boolean, Integer>(false, 6);
				else
					return new Coppia<Boolean, Integer>(true, 6);
//			#5
			else
				return new Coppia<Boolean, Integer>(true, 5);
	}
	
	private void fineGioco()
	{
		if(!Cost.multi)
			Campo.fineGioco();
		else
			CampoMulti.fineGioco();
	}
	
	/**
	 * controlla se il corpo passato come parametro tocca i bordi
	 * @param c il corpo
	 * @return <ul><li>true, se il corpo tocca i bordi</li><li>false, se il corpo NON tocca i bordi</li></ul>
	 */
	public boolean controllaBordi(Corpo c)
	{
		if(c.getTipoSprite()==Tipo.BONUS || c.getTipoSprite()==Tipo.SOLDO) return false;
//		la gravità del corpo è a destra
		if(c.getGravity().x>0.0f)
		{
//			bordo sopra
			if((c.getPosizione().y-c.getRaggio())<Cost.texture[2].getHeight())
				c.applicaForza(new Vec2(0.0f, c.getVel()), false);
//			bordo a destra
			else if((c.getPosizione().x+c.getRaggio())>(Cost.SCHERMOWIDTH-Cost.texture[3].getWidth()))
//				if(KeyboardState.GetState().IsKeyDown(Key.RIGHT) || KeyboardState.GetState().IsKeyDown(Key.D))
//					c.applicaForza(new Vec2(-(c.getGravity().x+c.getVel()), 0.0f));
//				else
					c.applicaForza(new Vec2(-c.getGravity().x, 0.0f), false);
//			bordo sotto
			else if((c.getPosizione().y+c.getRaggio())>(Cost.SCHERMOHEIGHT-Cost.texture[2].getHeight()))
				c.applicaForza(new Vec2(0.0f, -c.getVel()), false);
//			bordo a sinistra
			else if((c.getPosizione().x-c.getRaggio())<Cost.texture[3].getWidth())
				return true;
		}
//		la gravità del corpo è a sinistra
		if(c.getGravity().x<0.0f)
		{
//			bordo sopra
			if((c.getPosizione().y-c.getRaggio())<Cost.texture[2].getHeight())
				c.applicaForza(new Vec2(0.0f, c.getVel()), false);
//			bordo a destra
			else if((c.getPosizione().x+c.getRaggio())>(Cost.SCHERMOWIDTH-Cost.texture[3].getWidth()))
				return true;
//			bordo sotto
			else if((c.getPosizione().y+c.getRaggio())>(Cost.SCHERMOHEIGHT-Cost.texture[2].getHeight()))
				c.applicaForza(new Vec2(0.0f, -c.getVel()), false);
//			bordo a sinistra
			else if((c.getPosizione().x-c.getRaggio())<Cost.texture[3].getWidth())
//				if(KeyboardState.GetState().IsKeyDown(Key.LEFT) || KeyboardState.GetState().IsKeyDown(Key.A))
//					c.applicaForza(new Vec2(-(c.getGravity().x-c.getVel()), 0.0f));
//				else
					c.applicaForza(new Vec2(-c.getGravity().x, 0.0f), false);
		}
//		la gravità del corpo è giù
		if(c.getGravity().y>0.0f)
		{
//			bordo sopra
			if((c.getPosizione().y-c.getRaggio())<Cost.texture[2].getHeight())
				return true;
//			bordo a destra
			else if((c.getPosizione().x+c.getRaggio())>(Cost.SCHERMOWIDTH-Cost.texture[3].getWidth()))
				c.applicaForza(new Vec2(-c.getVel(), 0.0f), false);
//			bordo sotto
			else if((c.getPosizione().y+c.getRaggio())>(Cost.SCHERMOHEIGHT-Cost.texture[2].getHeight()))
//				if(KeyboardState.GetState().IsKeyDown(Key.DOWN) || KeyboardState.GetState().IsKeyDown(Key.S))
//					c.applicaForza(new Vec2(0.0f, -(c.getGravity().y+c.getVel())));
//				else
					c.applicaForza(new Vec2(0.0f, -c.getGravity().y), false);
//			bordo a sinistra
			else if((c.getPosizione().x-c.getRaggio())<Cost.texture[3].getWidth())
				c.applicaForza(new Vec2(c.getVel(), 0.0f), false);
		}
//		la gravità del corpo è su
		if(c.getGravity().y<0.0f)
		{
//			bordo sopra
			if((c.getPosizione().y-c.getRaggio())<Cost.texture[2].getHeight())
//				if(KeyboardState.GetState().IsKeyDown(Key.UP) || KeyboardState.GetState().IsKeyDown(Key.W))
//					c.applicaForza(new Vec2(0.0f, -(c.getGravity().y-c.getVel())));
//				else
					c.applicaForza(new Vec2(0.0f, -c.getGravity().y), false);
//			bordo a destra
			else if((c.getPosizione().x+c.getRaggio())>(Cost.SCHERMOWIDTH-Cost.texture[3].getWidth()))
				c.applicaForza(new Vec2(-c.getVel(), 0.0f), false);
//			bordo sotto
			else if((c.getPosizione().y+c.getRaggio())>(Cost.SCHERMOHEIGHT-Cost.texture[2].getHeight()))
				return true;
//			bordo a sinistra
			else if((c.getPosizione().x-c.getRaggio())<Cost.texture[3].getWidth())
				c.applicaForza(new Vec2(c.getVel(), 0.0f), false);
		}
		return false;
	}

	private void collisione(Corpo a, Corpo b, int pos)
	{
		a.collisioni(b, true, pos);
		b.collisioni(a, false, pos);
	}	

	/**
	 * controlla se la posizione passata come parametro è sopra ad un corpo esistente
	 * @param pos posizione da controllare
	 * @return <ul><li>true, se la posizione sta sopra un corpo che esiste</li><li>false, se la posizione NON sta sopra un corpo che esiste</li></ul>
	 */
	public boolean sopraCorpo(Vec2 pos)
	{
		if(!this.corpi.isEmpty())
			for(int i=0;i<this.corpi.size();i++)
			{
				Corpo c=this.corpi.get(i);
				float gra=0.0f;
				if(c.getGravity().x!=0)
					gra=c.getGravity().y;
				else
					gra=c.getGravity().x;
				gra*=3;
				if( ( pos.x> ( c.getPosizione().x-c.getMetaLarghezza()-gra ) ) && ( pos.x< ( c.getPosizione().x + c.getMetaLarghezza()+gra ) ) )
					if( ( ( pos.y+Cost.textureMatrix[2][0].getWidth() )>( c.getPosizione().y-c.getMetaAltezza()-gra ) ) && ( ( pos.y+Cost.textureMatrix[2][0].getWidth() )<( c.getPosizione().y+c.getMetaAltezza()+gra ) ) ||  ( ( pos.y-Cost.textureMatrix[2][0].getWidth() )>( c.getPosizione().y-c.getMetaAltezza()-gra ) ) && ( ( pos.y-Cost.textureMatrix[2][0].getWidth() )<( c.getPosizione().y+c.getMetaAltezza()+gra ) ) )
						return true;
				if( ( pos.y> ( c.getPosizione().y-c.getMetaAltezza() ) ) && ( pos.y< ( c.getPosizione().y + c.getMetaAltezza() ) ) )
					if( ( ( pos.x+Cost.textureMatrix[2][0].getWidth() )>( c.getPosizione().x-c.getMetaLarghezza()-gra ) ) && ( ( pos.x+Cost.textureMatrix[2][0].getWidth() )<( c.getPosizione().x+c.getMetaLarghezza()+gra ) ) ||  ( ( pos.x-Cost.textureMatrix[2][0].getWidth() )>( c.getPosizione().x-c.getMetaLarghezza()-gra ) ) && ( ( pos.x-Cost.textureMatrix[2][0].getWidth() )<( c.getPosizione().x+c.getMetaLarghezza()+gra ) ) )
						return true;
			}
		return false;			
	}		

	/**
	 * Controlla se il cerchio, il primo corpo, entra dentro un altro corpo
	 * @param a il cerchio
	 * @param c il rettangolo
	 * @return <ul><li>true, se il cerchio sta sopra il rettangolo</li><li>false, se il cerchio NON sta sopra il rettangolo</li>
	 */
	public boolean sopraCorpo(Corpo a/*cerchio*/, Corpo c/*rettangolo*/)
	{
		Vec2 pos=a.getPosizione();
		float gra=a.getVel();
		if( ( pos.x> ( c.getPosizione().x-c.getMetaLarghezza()-gra ) ) && ( pos.x< ( c.getPosizione().x + c.getMetaLarghezza()+gra ) ) )
			if( ( ( pos.y+Cost.textureMatrix[2][0].getWidth() )>( c.getPosizione().y-c.getMetaAltezza()-gra ) ) && ( ( pos.y+Cost.textureMatrix[2][0].getWidth() )<( c.getPosizione().y+c.getMetaAltezza()+gra ) ) ||  ( ( pos.y-Cost.textureMatrix[2][0].getWidth() )>( c.getPosizione().y-c.getMetaAltezza()-gra ) ) && ( ( pos.y-Cost.textureMatrix[2][0].getWidth() )<( c.getPosizione().y+c.getMetaAltezza()+gra ) ) )
				return true;
		if( ( pos.y> ( c.getPosizione().y-c.getMetaAltezza() ) ) && ( pos.y< ( c.getPosizione().y + c.getMetaAltezza() ) ) )
			if( ( ( pos.x+Cost.textureMatrix[2][0].getWidth() )>( c.getPosizione().x-c.getMetaLarghezza()-gra ) ) && ( ( pos.x+Cost.textureMatrix[2][0].getWidth() )<( c.getPosizione().x+c.getMetaLarghezza()+gra ) ) ||  ( ( pos.x-Cost.textureMatrix[2][0].getWidth() )>( c.getPosizione().x-c.getMetaLarghezza()-gra ) ) && ( ( pos.x-Cost.textureMatrix[2][0].getWidth() )<( c.getPosizione().x+c.getMetaLarghezza()+gra ) ) )
				return true;
		return false;					
	}		

	/**
	 * Controlla se la posizione passata come parametro è dentro un corpo
	 * @param pos posizione da controllare
	 * @param c corpo
	 * @return <ul><li>true, se il cerchio sta sopra il rettangolo</li><li>false, se il cerchio NON sta sopra il rettangolo</li>
	 */
	public boolean sopraCorpo(Vec2 pos, Corpo c/*rettangolo*/)
	{
		if( ( pos.x> ( c.getPosizione().x-c.getMetaLarghezza() ) ) && ( pos.x< ( c.getPosizione().x + c.getMetaLarghezza() ) ) )
			if( ( pos.y>( c.getPosizione().y-c.getMetaAltezza() ) ) && ( pos.y<( c.getPosizione().y+c.getMetaAltezza() ) ) ||  ( pos.y>( c.getPosizione().y-c.getMetaAltezza() ) ) && ( pos.y<( c.getPosizione().y+c.getMetaAltezza() ) ) )
				return true;
		if( ( pos.y> ( c.getPosizione().y-c.getMetaAltezza() ) ) && ( pos.y< ( c.getPosizione().y + c.getMetaAltezza() ) ) )
			if( ( pos.x>( c.getPosizione().x-c.getMetaLarghezza() ) ) && ( pos.x<( c.getPosizione().x+c.getMetaLarghezza() ) ) ||  ( pos.x>( c.getPosizione().x-c.getMetaLarghezza() ) ) && ( pos.x<( c.getPosizione().x+c.getMetaLarghezza() ) ) )
				return true;
		return false;					
	}
	
	/**
	 * trova la posizione del primo corpo rispetto al secondo
	 * @param a primo corpo
	 * @param b secondo corpo
	 * @return indice di posizione
	 *       |    |
	 *    #1 | #2 | #3          #123
	 *	-----|____|------
	 *    #4 | #5 | #6          #456
	 *	-----|____|------
	 *	  #7 | #8 | #9          #789
	 *       |    |
	 *    
	 */
	public int posizioneA(Corpo /*cerchio*/a, Corpo /*rettangolo*/b)
	{
//		il cerchio sta sopra il rettangolo
//		#123
		if(a.getPosizione().y<(b.getPosizione().y-b.getMetaAltezza()))
//			il cerchio sta in alto a sinistra del rettangolo
//			#1
			if(a.getPosizione().x<(b.getPosizione().x-b.getMetaLarghezza()))
				return 1;
//			il cerchio sta in alto a destra del rettangolo
//			#3
			else if(a.getPosizione().x>(b.getPosizione().x+b.getMetaLarghezza()))
				return 3;
//			il cerchio sta in alto al centro del rettangolo
//			#2
			else
				return 2;
//		il cerchio sta sotto il rettangolo
//		#789
		else if(a.getPosizione().y>(b.getPosizione().y+b.getMetaAltezza()))
//			il cerchio sta in basso a sinistra del rettangolo
//			#7
			if(a.getPosizione().x<(b.getPosizione().x-b.getMetaLarghezza()))
				return 7;
//			il cerchio sta in basso a destra del rettangolo
//			#9
			else if(a.getPosizione().x>(b.getPosizione().x+b.getMetaLarghezza()))
				return 9;
//			il cerchio sta in basso al centro del rettangolo
//			#8
			else
				return 8;
//		il cerchio non sta ne sopra ne sotto il rettangolo
//		#456
		else
//			il cerchio sta a sinistra del rettangolo
//			#4
			if(a.getPosizione().x<(b.getPosizione().x-b.getMetaLarghezza()))
				return 4;
//			il cerchio sta a destra del rettangolo
//			#6
			else if(a.getPosizione().x>(b.getPosizione().x+b.getMetaLarghezza()))
				return 6;
//			#5
			else
				return 5;
	}
	
	/* DEBUG */
	
	/**
	 * disegna il centro di ogni corpo, MOLTO MOLTO LENTO
	 */
//	public void disegnaCentro()
//	{
//	   	CanvasImage bgImage = graphics().createImage(1280, 720);
//	   	Canvas canvas = bgImage.canvas();
//		canvas.setFillColor(Color.rgb(0, 250, 250));
//   		canvas.setStrokeColor(Color.rgb(250, 0, 250));
//	   	for(Corpo b: this.corpi)
//	   	{
//	   		canvas.fillCircle(b.getPosizione().x, b.getPosizione().y, 3);
//		   	for(Corpo a: this.corpi)
//		   		if(!a.equals(b))
//			   		canvas.drawLine(a.getPosizione().x, a.getPosizione().y, b.getPosizione().x, b.getPosizione().y);
//	   	}
//	   	
//		ImageLayer iLayer=graphics().createImageLayer(bgImage.snapshot());
//		graphics().rootLayer().add(iLayer);
//	}
	
	@Override
	public String toString()
	{
		String s="";
		for(Corpo b:corpi)
			s=b.toString()+"\n";
		return s;
	}
}