package it.movioletto.GravityBall3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Schermata popup nuovo record
 * @author Movioletto
 */
public class PopupNewRecord
{
	private static Testo[] testi;
	private static Tipo t;
	private static Vec2 misureLocali;
	private Bottone[] bottoni;
	private Mondo mondo;
	private Sfondo sfondo;
	private Immagine img;
	
	/**
	 * creo lo schermata con tutti i suoi componenti
	 * @param surf il layer dove disegno tutti i componenti
	 */
	public PopupNewRecord(Game game)
	{
		this.sfondo=new Sfondo(Tipo.SFONDOPOPUP);
		this.img=new Immagine(new Vec2(Cost.SCHERMOWIDTH/2.0f, Cost.SCHERMOHEIGHT/2.0f), Tipo.POPUP750X310);
		this.mondo=new Mondo();
		t=Tipo.POPUP750X310;
		misureLocali=new Vec2((Cost.SCHERMOWIDTH/2.0f)+(Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f), Cost.SCHERMOHEIGHT/2.0f+(Cost.texture[Cost.tipoIndexOf(t)].getHeight()/2.0f));
		Bottone[] botton={
				new Bottone(Tipo.FRECCIASU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-70.0f), this.mondo, 16, game),
				new Bottone(Tipo.FRECCIASU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x-Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-70.0f), this.mondo, 17, game),
				new Bottone(Tipo.FRECCIASU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f, misureLocali.y-Cost.font150m.y-70.0f), this.mondo, 18, game),
				new Bottone(Tipo.FRECCIASU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x+Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-70.0f), this.mondo, 19, game),
				new Bottone(Tipo.FRECCIASU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-70.0f), this.mondo, 20, game),
				
				new Bottone(Tipo.FRECCIAGIU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-100.0f), this.mondo, 21, game),
				new Bottone(Tipo.FRECCIAGIU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x-Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-100.0f), this.mondo, 22, game),
				new Bottone(Tipo.FRECCIAGIU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-100.0f), this.mondo, 23, game),
				new Bottone(Tipo.FRECCIAGIU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x+Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-100.0f), this.mondo, 24, game),
				new Bottone(Tipo.FRECCIAGIU40, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-100.0f), this.mondo, 25, game),
				
				new Bottone(Tipo.TESTO100, "salva", new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()+(Cost.font100m.x*5)/2.0f+20, misureLocali.y-Cost.texture[Cost.tipoIndexOf(t)].getHeight()+Cost.font150m.y/2.0f+20), this.mondo, 26, game)
							};
		this.bottoni=botton;
		Testo[] test={
				new Testo(Tipo.TESTO150, "NEW RECORD", new Vec2(misureLocali.x-(Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f)-5.0f, misureLocali.y-20.0f)),

				new Testo(Tipo.TESTO100, "a", new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20)),
				new Testo(Tipo.TESTO100, "a", new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x-Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20)),
				new Testo(Tipo.TESTO100, "a", new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20)),
				new Testo(Tipo.TESTO100, "a", new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x+Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20)),
				new Testo(Tipo.TESTO100, "a", new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20)),
				
						};
		testi=test;
	}

	/**
	 * cambia delle cose della schermata in base al bottone che premi
	 * @param i il bottone premuto
	 */
	public static void cambiaBottone(int i, Game game)
	{
		String letteraSucc="";
		switch(i)
		{
		case 16:
			if(Cost.lettereIndexOf(testi[1].getString().charAt(0))+1>=(Cost.lettere.length)) letteraSucc=String.valueOf(Cost.lettere[0]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[1].getString().charAt(0))+1]);
			testi[1]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 17:
			if(Cost.lettereIndexOf(testi[2].getString().charAt(0))+1>=(Cost.lettere.length)) letteraSucc=String.valueOf(Cost.lettere[0]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[2].getString().charAt(0))+1]);
			testi[2]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x-Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 18:
			if(Cost.lettereIndexOf(testi[3].getString().charAt(0))+1>=(Cost.lettere.length)) letteraSucc=String.valueOf(Cost.lettere[0]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[3].getString().charAt(0))+1]);
			testi[3]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 19:
			if(Cost.lettereIndexOf(testi[4].getString().charAt(0))+1>=(Cost.lettere.length)) letteraSucc=String.valueOf(Cost.lettere[0]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[4].getString().charAt(0))+1]);
			testi[4]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x+Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 20:
			if(Cost.lettereIndexOf(testi[5].getString().charAt(0))+1>=(Cost.lettere.length)) letteraSucc=String.valueOf(Cost.lettere[0]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[5].getString().charAt(0))+1]);
			testi[5]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 21:
			if(Cost.lettereIndexOf(testi[1].getString().charAt(0))-1<0) letteraSucc=String.valueOf(Cost.lettere[Cost.lettere.length-1]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[1].getString().charAt(0))-1]);
			testi[1]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 22:
			if(Cost.lettereIndexOf(testi[2].getString().charAt(0))-1<0) letteraSucc=String.valueOf(Cost.lettere[Cost.lettere.length-1]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[2].getString().charAt(0))-1]);
			testi[2]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f-Cost.font100m.x-Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 23:
			if(Cost.lettereIndexOf(testi[3].getString().charAt(0))-1<0) letteraSucc=String.valueOf(Cost.lettere[Cost.lettere.length-1]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[3].getString().charAt(0))-1]);
			testi[3]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 24:
			if(Cost.lettereIndexOf(testi[4].getString().charAt(0))-1<0) letteraSucc=String.valueOf(Cost.lettere[Cost.lettere.length-1]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[4].getString().charAt(0))-1]);
			testi[4]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x+Cost.font100m.x/2.0f, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 25:
			if(Cost.lettereIndexOf(testi[5].getString().charAt(0))-1<0) letteraSucc=String.valueOf(Cost.lettere[Cost.lettere.length-1]);
			else letteraSucc=String.valueOf(Cost.lettere[Cost.lettereIndexOf(testi[5].getString().charAt(0))-1]);
			testi[5]=new Testo(Tipo.TESTO100, letteraSucc, new Vec2(misureLocali.x-Cost.texture[Cost.tipoIndexOf(t)].getWidth()/2.0f+Cost.font100m.x*3, misureLocali.y-Cost.font150m.y-Cost.font100m.y*2-20));
			break;
		case 26:
			if(Cost.VELOCITAINCREMENTO==500)
			{
				Cost.aggiungiPuntoEasy(Cost.trovaInput(), testi[1].getString()+testi[2].getString()+testi[3].getString()+testi[4].getString()+testi[5].getString(), Punteggio.getPunteggioFinale());
				Cost.salvaImpostazioni();
			}
			if(Cost.VELOCITAINCREMENTO==400)
			{
				Cost.aggiungiPuntoMedium(Cost.trovaInput(), testi[1].getString()+testi[2].getString()+testi[3].getString()+testi[4].getString()+testi[5].getString(), Punteggio.getPunteggioFinale());
				Cost.salvaImpostazioni();
			}
			if(Cost.VELOCITAINCREMENTO==300)
			{
				Cost.aggiungiPuntoHard(Cost.trovaInput(), testi[1].getString()+testi[2].getString()+testi[3].getString()+testi[4].getString()+testi[5].getString(), Punteggio.getPunteggioFinale());
				Cost.salvaImpostazioni();
			}
			game.setScreen(new SchermataIniziale(game));
			break;
		}
	}
	
	/**
	 * aggiorna tutte le componenti nel popup
	 * @param ms lo stato del mouse
	 */
	public void update(Vector3 touch) 
	{
		mondo.step();
		for(Bottone b: bottoni)
			b.update(touch);		
	}
	
	/**
	 * disegna le componenti del popup sullo schermo
	 * @param surf il layer su cui disegno
	 */
	public void paint(SpriteBatch sprite)
	{
		sfondo.paint(sprite);
		img.paint(sprite);
		for(Bottone b: bottoni)
			b.paint(sprite);
		for(Testo t: testi)
			t.paint(sprite);
	}
}