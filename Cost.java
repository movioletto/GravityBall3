package it.movioletto.GravityBall3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import it.movioletto.GravityBall3.Coppia;

/**
 * Tutte le costanti del gioco, variabili importanti!
 * @author Movioletto
 */
public class Cost
{
	public static Animation[] animazioni;

	public static Texture[] muri;
	public static Texture[] muriv;
	public static Texture[] bonus;
	public static Texture[] soldi;

	public static BitmapFont font32;
	public static BitmapFont font50;
	public static BitmapFont font70;
	public static BitmapFont font100;
	public static BitmapFont font140;
	public static BitmapFont font150;

	public static Vec2 font70m= new Vec2(36,32);
	public static Vec2 font100m= new Vec2(52,45);
	public static Vec2 font140m= new Vec2(72,64);
	public static Vec2 font150m= new Vec2(77,69);
	
	public static String[] testi={
		"gioco!",
		"multiplayer",
		"opzioni",
		"highscore",
		"crediti"
									};
	
	/**
	 * Costante, array di tipi, ci sono molti tipi ma non tutti, vedi l'ENUM Tipo
	 */
	public static final Tipo[] TIPI = { 
										/* 0 */	Tipo.PALLA,
										/* 1 */	Tipo.PALLA2,
										/* 2 */	Tipo.BORDO1,
										/* 3 */	Tipo.BORDO2,
										/* 4 */	Tipo.EASY,
										/* 5 */	Tipo.EASYC,
										/* 6 */	Tipo.MEDIUM,
										/* 7 */	Tipo.MEDIUMC,
										/* 8 */	Tipo.HARD,
										/* 9 */	Tipo.HARDC,
										/* 10 */Tipo.BONUSM,
										/* 11 */Tipo.BONUSMC,
										/* 12 */Tipo.BONUSPM,
										/* 13 */Tipo.BONUSPMC,
										/* 14 */Tipo.BONUSP,
										/* 15 */Tipo.BONUSPC,
										/* 16 */Tipo.MUSICA,
										/* 17 */Tipo.MUSICAC,
										/* 18 */Tipo.POPUP400X250,
										/* 19 */Tipo.POPUP750X310,
										/* 20 */Tipo.TOTALE,
										/* 21 */Tipo.FRECCIASU40,
										/* 22 */Tipo.FRECCIASU,
										/* 23 */Tipo.FRECCIADESTRA40,
										/* 24 */Tipo.FRECCIADESTRA,
										/* 25 */Tipo.FRECCIAGIU40,
										/* 26 */Tipo.FRECCIAGIU,
										/* 27 */Tipo.FRECCIASINISTRA40,
										/* 28 */Tipo.FRECCIASINISTRA,
										/* 29 */Tipo.BG,
										/* 30 */Tipo.ISTRUZIONI1,
										/* 31 */Tipo.ISTRUZIONI2,
										/* 32 */Tipo.SFONDOPOPUP,
										/* 32 */Tipo.PAUSA,
										/* 33 */Tipo.TOUCH,
										/* 34 */Tipo.TOUCHC,
										/* 35 */Tipo.ACCEL,
										/* 36 */Tipo.ACCELC,
										/* 37 */Tipo.MIX,
										/* 38 */Tipo.MIXC,
										/* 39 */Tipo.EASYH,
										/* 40 */Tipo.EASYHC,
										/* 41 */Tipo.MEDIUMH,
										/* 42 */Tipo.MEDIUMHC,
										/* 43 */Tipo.HARDH,
										/* 44 */Tipo.HARDHC,
										/* 43 */Tipo.TOUCHSG,
										/* 44 */Tipo.TOUCHSD,
										/* 43 */Tipo.MIXSG,
										/* 44 */Tipo.MIXSD
										};
	
	public static Texture[] texture= new Texture[TIPI.length];
	
	/**
	 * Costante, array di strighe, tutti i percorsi delle immagini dei tipi dell'array precedente
	 */
	public static final String[] IMG = {
										 "ball.png",
										 "ball2.png",
										 "bordo1.png",
										 "bordo2.png",
										 "bottoni/easy.png",
										 "bottoni/easyC.png",
										 "bottoni/medium.png",
										 "bottoni/mediumC.png",
										 "bottoni/hard.png",
										 "bottoni/hardC.png",
										 "bottoni/-bonus.png",
										 "bottoni/-bonusC.png",
										 "bottoni/+-bonus.png",
										 "bottoni/+-bonusC.png",
										 "bottoni/+bonus.png",
										 "bottoni/+bonusC.png",
										 "bottoni/check.png",
										 "bottoni/checkC.png",
										 "popup472x295.png",
										 "popup878x364.png",
										 "tot.png",
										 "40frecciaSu.png",
										 "frecciaSu.png",
										 "40frecciaDestra.png",
										 "frecciaDestra.png",
										 "40frecciaGiu.png",
										 "frecciaGiu.png",
										 "40frecciaSinistra.png",
										 "frecciaSinistra.png",
										 "bg.png",
										 "istruzioni1new.png",
										 "istruzioni2.png",
										 "sfondoPopup.png",
										 "bottoni/pausa.png",
										 "bottoni/touch.png",
										 "bottoni/touchC.png",
										 "bottoni/accel.png",
										 "bottoni/accelC.png",
										 "bottoni/mix.png",
										 "bottoni/mixC.png",
										 "bottoni/easyH.png",
										 "bottoni/easyHC.png",
										 "bottoni/mediumH.png",
										 "bottoni/mediumHC.png",
										 "bottoni/hardH.png",
										 "bottoni/hardHC.png",
										 "touchSG.gif",
										 "touchSD.gif",
										 "mixSG.png",
										 "mixSD.png"
										 };

	/**
	 * Costante, array di stringhe, tutti i percorsi delle immagini dei muri orizzontali
	 */
	public static final String[] IMGMURO =  {
											 "muri/muro.png",
											 "muri/muro2.png",
											 "muri/muro3.png",
											 "muri/muro4.png",
											 "muri/muro5.png"
											  };

	/**
	 * Costante, array di stringhe, tutti i percorsi delle immagini dei muri verticali
	 */
	public static final String[] IMGMUROV =  {
												"muri/muroV.png",
												"muri/muroV2.png",
												"muri/muroV3.png",
												"muri/muroV4.png",
												"muri/muroV5.png"
												};

	/**
	 * Costante, array di stringhe, tutti i percorsi delle immagini dei bonus
	 */
	public static final String[] IMGBONUS =  {
												"bonus/bonus1.png",
												"bonus/bonus2.png",
												"bonus/bonus3.png",
												"bonus/bonus4.png" 
												 };

	/**
	 * Costante, array di stringhe, tutti i percorsi delle immagini dei soldi
	 */
	public static final String[] IMGSOLDO =  {
												"soldi/soldo1.png",
												"soldi/soldo2.png",
												"soldi/soldo3.png",
												"soldi/soldo4.png",
												"soldi/soldo5.png",
												"soldi/soldo6.png",
												"soldi/soldo7.png"
												};

	/**
	 * Costante, array di tipi, tutti i tipi con pi� immagini
	 */
	public static final Tipo[] TIPIMATRIX = { 
												Tipo.MURO,
												Tipo.MUROV,
												Tipo.BONUS,
												Tipo.SOLDO
												};

	/**
	 * Costante, matrice di stringhe, tutti i percorsi dei tipi con pi� immagini
	 */
	public static Texture[][] textureMatrix;

	/**
	 * Costante, array di interi, le possibilità di uscita dei soldi
	 */
	public static final int[] STATSOLDO = { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6 };
	
	/**
	 * Costante, array di interi, valore dei soldi
	 */
	public static final int[] VALSOLDO = { 5, 10, 20, 50, 100, 200, 500 };
	
	/**
	 * Costante, array delle larghezze dei muri orizzontali
	 */
	public static final float[] MUROWIDTH = { 300.0f, 250.0f, 200.0f, 150.0f, 100.0f };
	
	/**
	 * Costante, array delle altezza dei muri orizzontali
	 */
	public static final float[] MUROHEIGHT = { 25.0f, 25.0f, 25.0f, 25.0f, 25.0f };
	
	/**
	 * Costante, array delle larghezze dei muri verticali
	 */
	public static final float[] MUROVWIDTH = { 25.0f, 25.0f, 25.0f, 25.0f, 25.0f };
	
	/**
	 * Costante, array delle altezze dei muri verticali
	 */
	public static final float[] MUROVHEIGHT = { 300.0f, 250.0f, 200.0f, 150.0f, 100.0f};
	
	/**
	 * Variabile, velocità con cui la gravità aumenta
	 */
	public static int VELOCITAINCREMENTO = 500;
	/**
	 * Variabile, velocità con cui i bonus si creano
	 */
	public static int VELOCITAINCREMENTOBONUS = 500;
	/**
	 * Costante, larghezza dello schermo
	 */
	public static final float SCHERMOWIDTH = 1024.0f;
	/**
	 * Costante, l'altezza dello schermo
	 */
	public static final float SCHERMOHEIGHT = 720.0f;
	/**
	 * Variabile, musica nel gioco
	 */
	public static boolean MUSICA=false;
	/**
	 * Variabile, musica nel gioco
	 */
	public static boolean touch=true;
	/**
	 * Variabile, musica nel gioco
	 */
	public static boolean accel=false;

	/**
	 * Variabile, musica nel gioco
	 */
	public static boolean multi=false;
	
	/**
	 * Variabile, musica nel gioco
	 */
	public static boolean rotazione=false;
	
	public static final char[] lettere={
		'a',
		'b',
		'c',
		'd',
		'e',
		'f',
		'g',
		'h',
		'i',
		'j',
		'k',
		'l',
		'm',
		'n',
		'o',
		'p',
		'q',
		'r',
		's',
		't',
		'u',
		'v',
		'w',
		'x',
		'y',
		'z'
									};
	public static String[][] highscoreNomiEasy={
		{ "aaaet", "bbbet", "cccet", "dddet", "eeeet" },
		{ "aaaea", "bbbea", "cccea", "dddea", "eeeea" },
		{ "aaaem", "bbbem", "cccem", "dddem", "eeeem" }
											};
	public static String[][] highscoreNomiMedium={
		{ "aaamt", "bbbmt", "cccmt", "dddmt", "eeemt" },
		{ "aaama", "bbbma", "cccma", "dddma", "eeema" },
		{ "aaamm", "bbbmm", "cccmm", "dddmm", "eeemm" }
											};
	public static String[][] highscoreNomiHard={
		{ "aaaht", "bbbht", "cccht", "dddht", "eeeht" },
		{ "aaaha", "bbbha", "cccha", "dddha", "eeeha" },
		{ "aaahm", "bbbhm", "ccchm", "dddhm", "eeehm" }
											};
	public static int[][] highscorePuntiEasy={
		{ 5, 4, 3, 2, 1 },
		{ 10, 9, 8, 7, 6 },
		{ 15, 14, 13, 12, 11 }
											};
	public static int[][] highscorePuntiMedium={
		{ 20, 19, 18, 17, 16 },
		{ 25, 24, 23, 22, 21 },
		{ 30, 29, 28, 27, 26 }
											};
	public static int[][] highscorePuntiHard={
		{ 35, 34, 33, 32, 31 },
		{ 40, 39, 38, 37, 36 },
		{ 45, 44, 43, 42, 41 }
											};
	public static String[] nomi= {"aaaaa", "bbbbb", "ccccc"};
	public static int[] punteggi = {0, 1, 2};
	public final static String file = ".gravityball";

	@SuppressWarnings("unchecked")
	private static final Posizioni MURIDEFAULT[]= {
//		!|--150--|...200...|-----300-----|...200...|--150--|!
		new Posizioni(new Coppia<Float, Integer>(MUROWIDTH[3]/2.0f, 3), new Coppia<Float, Integer>(SCHERMOWIDTH/2.0f, 0), new Coppia<Float, Integer>(SCHERMOWIDTH-(MUROWIDTH[3]/2.0f), 3)),
//		!|-----300-----||-----300-----|....250....|--150--|!
		new Posizioni(new Coppia<Float, Integer>(MUROWIDTH[0]/2.0f, 0), new Coppia<Float, Integer>(MUROWIDTH[0]+(MUROWIDTH[0]/2.0f), 0), new Coppia<Float, Integer>(SCHERMOWIDTH-(MUROWIDTH[3]/2.0f), 3)),
//		!|--150--|....250....|-----300-----||-----300-----|!
		new Posizioni(new Coppia<Float, Integer>(MUROWIDTH[3]/2.0f, 3), new Coppia<Float, Integer>(SCHERMOWIDTH-(MUROWIDTH[0]+(MUROWIDTH[0]/2.0f)), 0), new Coppia<Float, Integer>(SCHERMOWIDTH-(MUROWIDTH[0]/2.0f), 0)),
//		!.100.|----250----|..150..|---200---|.100.|-100-|.100.!
		new Posizioni(new Coppia<Float, Integer>(100.0f+(MUROWIDTH[1]/2.0f), 1), new Coppia<Float, Integer>((SCHERMOWIDTH/2.0f)+(MUROWIDTH[2]/2.0f), 2), new Coppia<Float, Integer>(SCHERMOWIDTH-(MUROWIDTH[4]/2.0f)-100.0f, 4)),
//		!.100.|-100-|.100.|---200--|..150..|----250----|.100.!
		new Posizioni(new Coppia<Float, Integer>(100.0f+(MUROWIDTH[4]/2.0f), 4), new Coppia<Float, Integer>((SCHERMOWIDTH/2.0f)-(MUROWIDTH[2]/2.0f), 2), new Coppia<Float, Integer>(SCHERMOWIDTH-(MUROWIDTH[1]/2.0f)-100.0f, 1)),
//		!..150..|---200---|.....300.....|---200--|..150..!
		new Posizioni(new Coppia<Float, Integer>(150.0f+(MUROWIDTH[2]/2.0f), 2), new Coppia<Float, Integer>((SCHERMOWIDTH)-(MUROWIDTH[2]/2.0f)-150, 2))
//		!.100.|-100-|.100.|-100-|.100.|-100-|.100.|-100-|.100.|-100-|!
//		new Posizioni(new Coppia<Float, Integer>(100.0f+(MUROWIDTH[4]/2.0f), 4), new Coppia<Float, Integer>((SCHERMOWIDTH/2.0f)-(MUROWIDTH[4]/2.0f)-100, 4), new Coppia<Float, Integer>((SCHERMOWIDTH/2.0f)+(MUROWIDTH[4]/2.0f), 4),new Coppia<Float, Integer>((SCHERMOWIDTH)-(MUROWIDTH[4]/2.0f)-200, 4),new Coppia<Float, Integer>((SCHERMOWIDTH)-(MUROWIDTH[4]/2.0f), 4)),
//		!|-100-|.100.|-100-|.100.|-100-|.100.|-100-|.100.|-100-|.100.|!
//		new Posizioni(new Coppia<Float, Integer>((MUROWIDTH[4]/2.0f), 4), new Coppia<Float, Integer>(200.0f+(MUROWIDTH[4]/2.0f), 4), new Coppia<Float, Integer>((SCHERMOWIDTH/2.0f)-(MUROWIDTH[4]/2.0f), 4),new Coppia<Float, Integer>((SCHERMOWIDTH/2.0f)+100.0f+(MUROWIDTH[4]/2.0f), 4),new Coppia<Float, Integer>((SCHERMOWIDTH)-(MUROWIDTH[4]/2.0f)-100.0f, 4)),
//													new Posizioni((SCHERMOWIDTH/2.0f)/2.0f, (SCHERMOWIDTH/2.0f)+((SCHERMOWIDTH/2.0f)/2.0f))
													};

	@SuppressWarnings("unchecked")
	private static final Posizioni MURIVDEFAULT[]= {
//		!|-100-|..150..|---200---|..150..|-100-|!
		new Posizioni(new Coppia<Float, Integer>((MUROVHEIGHT[4]/2.0f), 4), new Coppia<Float, Integer>((SCHERMOHEIGHT/2.0f), 2), new Coppia<Float, Integer>(SCHERMOHEIGHT-(MUROVHEIGHT[4]/2.0f), 4)),
//		!|---200---||---200---|..150..|--150--|!
		new Posizioni(new Coppia<Float, Integer>((MUROVHEIGHT[2]/2.0f), 2), new Coppia<Float, Integer>((SCHERMOHEIGHT/2.0f)-(MUROVHEIGHT[2]/2.0f), 2), new Coppia<Float, Integer>(SCHERMOHEIGHT-(MUROVHEIGHT[3]/2.0f), 3)),
//		!|--150--|..150..|---200---|---200---|!
		new Posizioni(new Coppia<Float, Integer>((MUROVHEIGHT[3]/2.0f), 3), new Coppia<Float, Integer>((SCHERMOHEIGHT/2.0f)+(MUROVHEIGHT[2]/2.0f), 2), new Coppia<Float, Integer>(SCHERMOHEIGHT-(MUROVHEIGHT[2]/2.0f), 2)),
//		!.100.|-----300-----|.100.|-100-|.100.!
		new Posizioni(new Coppia<Float, Integer>(100.0f+(MUROVHEIGHT[0]/2.0f), 0), new Coppia<Float, Integer>(SCHERMOHEIGHT-(MUROVHEIGHT[4]/2.0f)-100.0f, 4)),
//		!.100.|-100-|.100.|-----300-----|.100.!
		new Posizioni(new Coppia<Float, Integer>(100.0f+(MUROVHEIGHT[4]/2.0f), 4), new Coppia<Float, Integer>(SCHERMOHEIGHT-(MUROVHEIGHT[0]/2.0f)-100.0f, 0)),
//		!.100.|--150--|...200...|---150---|.100.!
		new Posizioni(new Coppia<Float, Integer>(100.0f+(MUROVHEIGHT[3]/2.0f), 3), new Coppia<Float, Integer>(SCHERMOHEIGHT-(MUROVHEIGHT[3]/2.0f)-100.0f, 3))
													};
	
	/**
	 * Metodo getter delle posizioni dei muri orizzontali di default
	 * @return array di posizioni
	 */
	public static Posizioni[] getMuriDefault()
	{
		return MURIDEFAULT;
	}
	
	/**
	 * Metodo getter delle posizioni dei muri verticali di default
	 * @return array di posizioni
	 */
	public static Posizioni[] getMuriVDefault()
	{
		return MURIVDEFAULT;
	}

	/**
	 * Metodo indexOf dell'array dei tipi
	 * @param t il tipo da cercare
	 * @return l'indice del tipo passato per parametro
	 */
	public static int tipoIndexOf(Tipo t)
	{
		for(int i=0;i<TIPI.length;i++)
			if(TIPI[i]==t)
				return i;
		return 0;
	}

	/**
	 * Metodo indexOf dell'array dei tipi
	 * @param t il tipo da cercare
	 * @return l'indice del tipo passato per parametro
	 */
	public static int lettereIndexOf(char c)
	{
		for(int i=0;i<lettere.length;i++)
			if(lettere[i]==c)
				return i;
		return 0;
	}
	
	/**
	 * Funzioni di tutti i bottoni che ci sono!
	 * @param i indice della funzione
	 */
	public static void funzioniBottoni(int i, Game game)
	{
		switch(i)
		{
			case 0:
//				gioca
				game.setScreen(new Campo(game));
				break;
			case 1:
//				opzioni
				game.setScreen(new SchermataOpzioni(game));
				break;
			case 2:
//				credits
				game.setScreen(new SchermataCrediti(game));
				break;
			case 3:
//				esci
				System.exit(0);
				break;
			case 4:
//				velocità facile
				VELOCITAINCREMENTO = 500;
				SchermataOpzioni.cambiaBottone(i);
				break;
			case 5:
//				velocità media
				VELOCITAINCREMENTO = 400;
				SchermataOpzioni.cambiaBottone(i);
				break;
			case 6:
//				velocità difficile
				VELOCITAINCREMENTO = 300;
				SchermataOpzioni.cambiaBottone(i);
				break;
			case 7:
//				schermata iniziale
				game.setScreen(new SchermataIniziale(game));
				break;
			case 8:
//				bonus -
				VELOCITAINCREMENTOBONUS = 700;
				SchermataOpzioni.cambiaBottone(i);
				break;
			case 9:
//				bonus +-
				VELOCITAINCREMENTOBONUS = 500;
				SchermataOpzioni.cambiaBottone(i);
				break;
			case 10:
//				bonus +
				VELOCITAINCREMENTOBONUS = 300;
				SchermataOpzioni.cambiaBottone(i);
				break;
			case 11:
//				musica on
				MUSICA = true;
				SchermataOpzioni.cambiaBottone(i);
				break;
			case 12:
//				musica off
				MUSICA = false;
				SchermataOpzioni.cambiaBottone(i);
				break;
			case 13:
//				highscore
				game.setScreen(new SchermataHighScore(game));
				break;
			case 14:
//				blocco partita
				if(!multi)
					Campo.pausa(false);
				else
					CampoMulti.pausa(false);
				break;
			case 15:
//				continua partita
				if(!multi)
					Campo.pausa(true);
				else
					CampoMulti.pausa(true);
				break;
			case 16:
//				freccia su 1
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 17:
//				freccia su 2
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 18:
//				freccia su 3
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 19:
//				freccia su 4
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 20:
//				freccia su 5
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 21:
//				freccia giu 1
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 22:
//				freccia giu 2
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 23:
//				freccia giu 3
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 24:
//				freccia giu 4
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 25:
//				freccia giu 5
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 26:
//				salva highscore
				PopupNewRecord.cambiaBottone(i, game);
				break;
			case 27:
//				popup nuovo record
				if(Cost.VELOCITAINCREMENTO==500)
					if(Cost.verificaPuntiEasy(Punteggio.getPunteggioFinale()))
							Campo.record(true);
					else
						game.setScreen(new SchermataIniziale(game));
				if(Cost.VELOCITAINCREMENTO==400)
					if(Cost.verificaPuntiMedium(Punteggio.getPunteggioFinale()))
						Campo.record(true);
					else
						game.setScreen(new SchermataIniziale(game));
				if(Cost.VELOCITAINCREMENTO==300)
					if(Cost.verificaPuntiHard(Punteggio.getPunteggioFinale()))
						Campo.record(true);
					else
						game.setScreen(new SchermataIniziale(game));
				break;
			case 28:
//				highScore facile
				SchermataHighScore.diffi=0;
				SchermataHighScore.cambiaBottone(i);
				break;
			case 29:
//				highScore medio
				SchermataHighScore.diffi=1;
				SchermataHighScore.cambiaBottone(i);
				break;
			case 30:
//				highScore difficile
				SchermataHighScore.diffi=2;
				SchermataHighScore.cambiaBottone(i);
				break;
			case 31:
//				cancella dati
				cancellaStorage();
				break;
			case 32:
//				multigiocatore
				game.setScreen(new CampoMulti(game));
				break;
			case 33:
//				istruzioni1
				game.setScreen(new SchermataIstruzioni1(game));
				break;
			case 34:
//				istruzioni2
				game.setScreen(new SchermataIstruzioni2(game));
				break;
			case 35:
//				cambio input
				if(SchermataOpzioni.getTesto(2).getString().equals("touch"))
//				if(touch)
				{
					touch=false;
					accel=true;
					SchermataOpzioni.cambiaBottone(36);
				}
				else if(SchermataOpzioni.getTesto(2).getString().equals("accel"))
//				else if(accel)
				{
					touch=true;
					accel=true;
					SchermataOpzioni.cambiaBottone(35);
				}
				else if(SchermataOpzioni.getTesto(2).getString().equals("mix"))
//				else if(accel)
				{
					touch=true;
					accel=false;
					SchermataOpzioni.cambiaBottone(35);
				}
				break;
			case 36:
//				highscore touch
				SchermataHighScore.input=0;
				SchermataHighScore.cambiaBottone(i);
				break;
			case 37:
//				highscore accel
				SchermataHighScore.input=1;
				SchermataHighScore.cambiaBottone(i);
				break;
			case 38:
//				highscore mix
				SchermataHighScore.input=2;
				SchermataHighScore.cambiaBottone(i);
				break;
			case 39:
//				rotazione on
				rotazione = true;
				SchermataOpzioni.cambiaBottone(i);
				break;
			case 40:
//				rotazione off
				rotazione = false;
				SchermataOpzioni.cambiaBottone(i);
				break;
		}
	}

	public static int trovaInput()
	{
		if(Cost.accel && Cost.touch)
			return 2;
		else if(Cost.accel && !Cost.touch)
			return 1;
		else if(!Cost.accel && Cost.touch)
			return 0;
		return 0;
	}
	
	public static boolean verificaPuntiEasy(int punti)
	{
		int input=trovaInput();
		for (int i = 0; i < 5; i++)
			if (Cost.highscorePuntiEasy[input][i] < punti)
				return true;
		return false;		
	}

	public static boolean verificaPuntiMedium(int punti)
	{
		int input=trovaInput();
		for (int i = 0; i < 5; i++)
			if (Cost.highscorePuntiMedium[input][i] < punti)
				return true;
		return false;		
	}

	public static boolean verificaPuntiHard(int punti)
	{
		int input=trovaInput();
		for (int i = 0; i < 5; i++)
			if (Cost.highscorePuntiHard[input][i] < punti)
				return true;
		return false;		
	}

	public static boolean aggiungiPuntoEasy(int input, String nome, int punti)
	{
		for (int i = 0; i < 5; i++)
		{
			if (Cost.highscorePuntiEasy[input][i] < punti)
			{
				for (int j = 4; j > i; j--)
				{
					Cost.highscorePuntiEasy[input][j] = Cost.highscorePuntiEasy[input][j-1];
					Cost.highscoreNomiEasy[input][j] = Cost.highscoreNomiEasy[input][j-1];
				}
				Cost.highscorePuntiEasy[input][i] = punti;
				Cost.highscoreNomiEasy[input][i] = nome;
				return true;
			}
		}
		return false;		
	}

	public static boolean aggiungiPuntoMedium(int input, String nome, int punti)
	{
		for (int i = 0; i < 5; i++)
		{
			if (Cost.highscorePuntiMedium[input][i] < punti)
			{
				for (int j = 4; j > i; j--)
				{
					Cost.highscorePuntiMedium[input][j] = Cost.highscorePuntiMedium[input][j-1];
					Cost.highscoreNomiMedium[input][j] = Cost.highscoreNomiMedium[input][j-1];
				}
				Cost.highscorePuntiMedium[input][i] = punti;
				Cost.highscoreNomiMedium[input][i] = nome;
				return true;
			}
		}
		return false;		
	}

	public static boolean aggiungiPuntoHard(int input, String nome, int punti)
	{
		for (int i = 0; i < 5; i++)
		{
			if (Cost.highscorePuntiHard[input][i] < punti)
			{
				for (int j = 4; j > i; j--)
				{
					Cost.highscorePuntiHard[input][j] = Cost.highscorePuntiHard[input][j-1];
					Cost.highscoreNomiHard[input][j] = Cost.highscoreNomiHard[input][j-1];
				}
				Cost.highscorePuntiHard[input][i] = punti;
				Cost.highscoreNomiHard[input][i] = nome;
				return true;
			}
		}
		return false;		
	}
	
	/**
	 * Metodo che resetta lo storage degli highscore
	 */
	public static void cancellaStorage()
	{
		String[][] highscoreNomiEas={
			{ "aaaet", "bbbet", "cccet", "dddet", "eeeet" },
			{ "aaaea", "bbbea", "cccea", "dddea", "eeeea" },
			{ "aaaem", "bbbem", "cccem", "dddem", "eeeem" }
												};
		String[][] highscoreNomiMediu={
			{ "aaamt", "bbbmt", "cccmt", "dddmt", "eeemt" },
			{ "aaama", "bbbma", "cccma", "dddma", "eeema" },
			{ "aaamm", "bbbmm", "cccmm", "dddmm", "eeemm" }
												};
		String[][] highscoreNomiHar={
			{ "aaaht", "bbbht", "cccht", "dddht", "eeeht" },
			{ "aaaha", "bbbha", "cccha", "dddha", "eeeha" },
			{ "aaahm", "bbbhm", "ccchm", "dddhm", "eeehm" }
												};
		int[][] highscorePuntiEas={
			{ 5, 4, 3, 2, 1 },
			{ 10, 9, 8, 7, 6 },
			{ 15, 14, 13, 12, 11 }
												};
		int[][] highscorePuntiMediu={
				{ 20, 19, 18, 17, 16 },
				{ 25, 24, 23, 22, 21 },
				{ 30, 29, 28, 27, 26 }
												};
		int[][] highscorePuntiHar={
				{ 35, 34, 33, 32, 31 },
				{ 40, 39, 38, 37, 36 },
				{ 45, 44, 43, 42, 41 }
									};		

		highscoreNomiEasy= highscoreNomiEas;
		highscoreNomiMedium=highscoreNomiMediu;
		highscoreNomiHard=highscoreNomiHar;
		highscorePuntiEasy=highscorePuntiEas;
		highscorePuntiMedium=highscorePuntiMediu;
		highscorePuntiHard=highscorePuntiHar;
			
		salvaImpostazioni();
		SchermataHighScore.cambiaBottone(28);
	}

	public static Texture caricaTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static TextureRegion caricaTextureRegion(String file) {
		return new TextureRegion(new Texture(Gdx.files.internal(file)));
	}

	public static boolean caricaImmagini()
	{
		Gdx.app.log("carico immagini", "carico immagini");
		Texture.setEnforcePotImages(false);
		for(int i=0;i<TIPI.length;i++)
			texture[i]= caricaTexture(IMG[i]);

		Texture[] muro = {
				caricaTexture(IMGMURO[0]),
				caricaTexture(IMGMURO[1]),
				caricaTexture(IMGMURO[2]),
				caricaTexture(IMGMURO[3]),
				caricaTexture(IMGMURO[4])
						  };
		muri=muro;

		Texture[] murov = {
				caricaTexture(IMGMUROV[0]),
				caricaTexture(IMGMUROV[1]),
				caricaTexture(IMGMUROV[2]),
				caricaTexture(IMGMUROV[3]),
				caricaTexture(IMGMUROV[4])
						  };
		muriv=murov;

		Texture[] bonuss = {
				caricaTexture(IMGBONUS[0]),
				caricaTexture(IMGBONUS[1]),
				caricaTexture(IMGBONUS[2]),
				caricaTexture(IMGBONUS[3])
						  };
		bonus=bonuss;

		Texture[] soldo = {
				caricaTexture(IMGSOLDO[0]),
				caricaTexture(IMGSOLDO[1]),
				caricaTexture(IMGSOLDO[2]),
				caricaTexture(IMGSOLDO[3]),
				caricaTexture(IMGSOLDO[4]),
				caricaTexture(IMGSOLDO[5]),
				caricaTexture(IMGSOLDO[6])
						  };
		soldi=soldo;
		Texture[][] textureMatri={
					muri,
					muriv,
					bonus,
					soldi
									};
		textureMatrix= textureMatri;
		
		font32 = new BitmapFont(Gdx.files.internal("font/GravityBall.fnt"), Gdx.files.internal("font/GravityBall.png"), false);
		font50 = new BitmapFont(Gdx.files.internal("font/GravityBall50.fnt"), Gdx.files.internal("font/GravityBall50.png"), false);
		font70 = new BitmapFont(Gdx.files.internal("font/GravityBall70.fnt"), Gdx.files.internal("font/GravityBall70B.png"), false);
		font100 = new BitmapFont(Gdx.files.internal("font/font100.fnt"), Gdx.files.internal("font/font100B.png"), false);
		font140 = new BitmapFont(Gdx.files.internal("font/font140.fnt"), Gdx.files.internal("font/font140B.png"), false);
		font150 = new BitmapFont(Gdx.files.internal("font/font150.fnt"), Gdx.files.internal("font/font150B.png"), false);

		TextureRegion[] touchSD=new TextureRegion[16];
		for(int i=0; i<touchSD.length;i++)
			touchSD[i]= caricaTextureRegion("animazioni/touchSD"+i+".png");

		TextureRegion[] touchSG=new TextureRegion[16];
		for(int i=0; i<touchSG.length;i++)
			touchSG[i]= caricaTextureRegion("animazioni/touchSG"+i+".png");

		TextureRegion[] accelSG=new TextureRegion[21];
		for(int i=0; i<accelSG.length;i++)
			accelSG[i]= caricaTextureRegion("animazioni/accelSG"+i+".png");
		
		TextureRegion[] accelSD=new TextureRegion[21];
		for(int i=0; i<accelSD.length;i++)
			accelSD[i]= caricaTextureRegion("animazioni/accelSD"+i+".png");

		TextureRegion[] istPlayer1=new TextureRegion[32];
		for(int i=0; i<istPlayer1.length;i++)
			istPlayer1[i]= caricaTextureRegion("animazioni/istPlayer1"+i+".png");
		
		TextureRegion[] istPlayer2=new TextureRegion[32];
		for(int i=0; i<istPlayer2.length;i++)
			istPlayer2[i]= caricaTextureRegion("animazioni/istPlayer2"+i+".png");
//
//		Animation[] anim= {
//				new Animation(0.3f, touchSD),
//				new Animation(0.3f, touchSG),
//				new Animation(0.3f, accelSD),
//				new Animation(0.3f, accelSG),
//				new Animation(0.3f, istPlayer1),
//				new Animation(0.3f, istPlayer2)
//		};
		
		Animation[] anim= {				
				new Animation(0.3f, touchSD),
				new Animation(0.3f, touchSG),
				new Animation(0.3f, accelSD),
				new Animation(0.3f, accelSG),
				new Animation(0.3f, istPlayer1),
				new Animation(0.3f, istPlayer2)
		};
//		Animation anim = com.holidaystudios.tools.GifDecoder.loadGIFAnimation(Animation.LOOP, Gdx.files.internal(".gif").read());
		animazioni=anim;
		
		Gdx.app.log("carico immagini", "carico immagini1");
		caricaImpostazioni();
		Gdx.app.log("carico immagini", "carico immagini2");
		
		return true;
	}

	public static void caricaImpostazioni()
	{
		try
		{
			if(Gdx.files.external(file).exists())
			{
				FileHandle filehandle = Gdx.files.external(file);
				String[] strings = filehandle.readString().split("\n");

				if(strings.length==16)
				{
					Cost.VELOCITAINCREMENTO= Integer.parseInt(strings[0]);
					Cost.VELOCITAINCREMENTOBONUS= Integer.parseInt(strings[1]);
		
					Cost.touch= Boolean.parseBoolean(strings[2]);
					Cost.accel= Boolean.parseBoolean(strings[3]);
		
					Cost.MUSICA= Boolean.parseBoolean(strings[4]);
					
					for(int k=0;k<Cost.highscoreNomiEasy.length;k++)
					{
						String[] highscoreEasy=strings[5+k].split(":");
						for(int i=0;i<Cost.highscoreNomiEasy[0].length;i++)
						{
							Cost.highscoreNomiEasy[k][i]=highscoreEasy[i].substring(0, 5);
							Cost.highscorePuntiEasy[k][i]=Integer.parseInt(highscoreEasy[i].substring(5));
						}
					}
					
					for(int k=0;k<Cost.highscoreNomiMedium.length;k++)
					{
						String[] highscoreMedium=strings[8+k].split(":");
						for(int i=0;i<Cost.highscoreNomiMedium[0].length;i++)
						{
							Cost.highscoreNomiMedium[k][i]=highscoreMedium[i].substring(0, 5);
							Cost.highscorePuntiMedium[k][i]=Integer.parseInt(highscoreMedium[i].substring(5));
						}
					}
					
					for(int k=0;k<Cost.highscoreNomiHard.length;k++)
					{
						String[] highscoreHard=strings[11+k].split(":");
						for(int i=0;i<Cost.highscoreNomiHard[0].length;i++)
						{
							Cost.highscoreNomiHard[k][i]=highscoreHard[i].substring(0, 5);
							Cost.highscorePuntiHard[k][i]=Integer.parseInt(highscoreHard[i].substring(5));
						}
					}
					
					Cost.rotazione= Boolean.parseBoolean(strings[14]);
				}
				else
					Cost.salvaImpostazioni();
			}
			else
				Cost.salvaImpostazioni();
		}catch(Throwable e)
		{
		}
	}

	public static void salvaImpostazioni()
	{
		try
		{
			FileHandle filehandle = Gdx.files.external(file);

			filehandle.writeString(String.valueOf(Cost.VELOCITAINCREMENTO)+"\n", false);
			filehandle.writeString(String.valueOf(Cost.VELOCITAINCREMENTOBONUS)+"\n", true);

			filehandle.writeString(String.valueOf(Cost.touch)+"\n", true);
			filehandle.writeString(String.valueOf(Cost.accel)+"\n", true);

			filehandle.writeString(String.valueOf(Cost.MUSICA)+"\n", true);
			
			String he="";
			for(int i=0;i<Cost.highscoreNomiEasy.length;i++)
			{
				for(int k=0;k<Cost.highscoreNomiEasy[0].length;k++)
					he+=Cost.highscoreNomiEasy[i][k]+Cost.highscorePuntiEasy[i][k]+":";
				he+="\n";
			}

			String hm="";
			for(int i=0;i<Cost.highscoreNomiMedium.length;i++)
			{
				for(int k=0;k<Cost.highscoreNomiMedium[0].length;k++)
					hm+=Cost.highscoreNomiMedium[i][k]+Cost.highscorePuntiMedium[i][k]+":";
				hm+="\n";
			}

			String hh="";
			for(int i=0;i<Cost.highscoreNomiHard.length;i++)
			{
				for(int k=0;k<Cost.highscoreNomiHard[0].length;k++)
					hh+=Cost.highscoreNomiHard[i][k]+Cost.highscorePuntiHard[i][k]+":";
				hh+="\n";
			}
				
			filehandle.writeString(he, true);
			filehandle.writeString(hm, true);
			filehandle.writeString(hh, true);

			filehandle.writeString(String.valueOf(Cost.rotazione)+"\n", true);
			filehandle.writeString("FINE", true);
//			filehandle.writeString(String.valueOf(Cost.punteggi[0])+"\n", true);
//			filehandle.writeString(String.valueOf(Cost.punteggi[1])+"\n", true);
//			filehandle.writeString(String.valueOf(Cost.punteggi[2])+"\n", true);
		} catch (Throwable e) {
		}
	}
}