package com.knarf.killboss;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.knarf.killboss2.HistoriaValentina;
import com.knarf.killboss2.Mapa2V;
import com.knarf.killboss2.Mapa3V;
import com.knarf.killboss2.Mapa4V;
import com.knarf.killboss2.Mapa5V;
import com.knarf.killboss2.Mapa6V;
import com.knarf.killboss2.Mapa7V;

public class MenuIntro implements Screen {
	
	final KillBoss juego;
		
	public OrthographicCamera camara;
	public Rectangle fondoRectangulo, zackR, valentinaR;
	public Texture fondoImg, zackImg, valentinaImg;
	public Music menuMusica;
	public int selector, selector2;
	public static int puntaje = 0;
	public static int nivel = 0;
	public static int zackValentina = 0;
	
	/**
	 * Método constructor
	 * @param juego
	 */
	public MenuIntro(final KillBoss juego) {
		// Variable del juego
		this.juego = juego;		
				
		
		// Cargo las imágenes del fondo y de los personajes.
		this.fondoImg = new Texture(Gdx.files.internal("menu/menuIntro.png"));
		this.zackImg = new Texture(Gdx.files.internal("zack.png"));		
		this.valentinaImg = new Texture(Gdx.files.internal("valentina.png"));		
		
		// Cargo la música de fondo
		this.menuMusica = Gdx.audio.newMusic(Gdx.files.internal("sounds/celticIntro.mp3"));
		this.menuMusica.setLooping(true);
		
		// Crea la cámara y el lote donde enfocará
		this.camara = new OrthographicCamera();
		this.camara.setToOrtho(false, 2048, 1024);
		
		// Creo los rectángulos que representarán lógicamente
		// al fondo del menú y a los personajes
		
		// Fondo menu rectángulo
		this.fondoRectangulo = new Rectangle();
		this.fondoRectangulo.x = 2048 / 2 - 1024 / 2;
		this.fondoRectangulo.y = 1024 / 2 - 1024 / 2;
		this.fondoRectangulo.width = 1024;
		this.fondoRectangulo.height = 1024;
		
		// Fondo zack rectángulo
		this.zackR = new Rectangle();
		this.zackR.x = 2048 / 2 - 256;
		this.zackR.y = 1024 / 2 - 512 / 3;
		this.zackR.width = 128;
		this.zackR.height = 256;
		
		// Fondo valentina rectángulo
		this.valentinaR = new Rectangle();
		this.valentinaR.x = 2048 / 2 + 128;
		this.valentinaR.y = 1024 / 2 -512 / 3;
		this.valentinaR.width = 128;
		this.valentinaR.height = 256;
	
		
	}

	
	@Override
	public void render(float delta) {		
		// Le dice a la cámara que actualice sus matrices.
		camara.update();
	
        // Dibuja en la pantalla
        this.juego.batch.begin();
        // Dibuja el fondo del menu
        this.juego.batch.draw(this.fondoImg, this.fondoRectangulo.x, this.fondoRectangulo.y);
        // Dibuja a zack
        this.juego.batch.draw(this.zackImg, this.zackR.x, this.zackR.y);
        // Dibuja a valentina
        this.juego.batch.draw(this.valentinaImg, this.valentinaR.x, this.valentinaR.y);
        this.juego.batch.end();
        
        if (MenuIntro.zackValentina != 0) {
        	if (MenuIntro.zackValentina == 1) {
        		switch (MenuIntro.nivel) {
        		case 2:	 this.juego.setScreen(new Mapa2(this.juego, MenuIntro.puntaje, 3));
        				 break;
        		case 3:	 this.juego.setScreen(new Mapa3(this.juego, MenuIntro.puntaje, 3));
				 		 break;
        		case 4:	 this.juego.setScreen(new Mapa4(this.juego, MenuIntro.puntaje, 3));
				 		 break;
        		case 5:	 this.juego.setScreen(new Mapa5(this.juego, MenuIntro.puntaje, 3));
				 		 break;
        		case 6:	 this.juego.setScreen(new Mapa6(this.juego, MenuIntro.puntaje, 3));
				 		 break;        		
        		case 7:	 this.juego.setScreen(new Mapa7(this.juego, MenuIntro.puntaje, 3));
		 		 		 break;
        		} // switch (MenuIntro.nivel)
        	} // if (MenuIntro.zackValentina == 1)
        	if (MenuIntro.zackValentina == 2) {
        		switch (MenuIntro.nivel) {
        		case 2:	 this.juego.setScreen(new Mapa2V(this.juego, MenuIntro.puntaje, 3));
        				 break;
        		case 3:	 this.juego.setScreen(new Mapa3V(this.juego, MenuIntro.puntaje, 3));
				 		 break;
        		case 4:	 this.juego.setScreen(new Mapa4V(this.juego, MenuIntro.puntaje, 3));
				 		 break;
        		case 5:	 this.juego.setScreen(new Mapa5V(this.juego, MenuIntro.puntaje, 3));
				 		 break;
        		case 6:	 this.juego.setScreen(new Mapa6V(this.juego, MenuIntro.puntaje, 3));
				 		 break;        		
        		case 7:	 this.juego.setScreen(new Mapa7V(this.juego, MenuIntro.puntaje, 3));
		 		 		 break;
        		} // switch (MenuIntro.nivel)
        	} // if (MenuIntro.zackValentina == 2)
        }       
        
        jugador();
	}
	
	/**
	 * Identifica las selección del jugador por parte del usuario
	 * usando el teclado como entrada 
	 */
	public void jugador(){
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.zackImg = new Texture(Gdx.files.internal("zack2.png"));
			this.valentinaImg = new Texture(Gdx.files.internal("valentina.png"));
			this.selector = 1;						
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.valentinaImg = new Texture(Gdx.files.internal("valentina2.png"));
			this.zackImg = new Texture(Gdx.files.internal("zack.png"));
			this.selector = 2;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.fondoImg = new Texture(Gdx.files.internal("menu/menuIntroInstrucciones.png"));
			this.selector2 = 1;
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			this.fondoImg = new Texture(Gdx.files.internal("menu/menuIntroJugar.png"));
			this.selector2 = 2;
		}		
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			if (this.selector == 1 && this.selector2 == 2) {
				this.juego.setScreen(new HistoriaZack(this.juego));
				this.dispose();				
			}
			if (this.selector == 2 && this.selector2 == 2) {
				this.juego.setScreen(new HistoriaValentina(this.juego));
				this.dispose();				
			}
			if (this.selector2 == 1) {
				this.juego.setScreen(new Instrucciones(this.juego));
				this.dispose();
			}
		}
		if (Gdx.input.isKeyPressed(Keys.F10)) {
			Administrador admin = new Administrador(this.juego);			
			admin.inicio();
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		if (Gdx.input.isKeyPressed(Keys.F2)) {
			CargarPartida partida = new CargarPartida(this.juego);			
			partida.inicio();			
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// Comienza la música de fondo cuando se ejecuta el screen
		this.menuMusica.play();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		this.fondoImg.dispose();
		this.zackImg.dispose();
		this.valentinaImg.dispose();
		this.menuMusica.dispose();
	}
}