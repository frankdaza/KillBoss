package com.knarf.killboss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Instrucciones implements Screen {
	
	final KillBoss juego;
	public OrthographicCamera camara;
	public Texture instruccionesImg;
	public Rectangle instruccionesR;
	public Music instruccionesMusica;
	
	/**
	 * Método constructor
	 * @param juego
	 */
	public Instrucciones(final KillBoss juego) {
		this.juego = juego;
		
		// Cargo la imagen de instrucciones
		this.instruccionesImg = new Texture(Gdx.files.internal("menu/instrucciones.png"));
		
		// Cargo la música de las instrucciones
		this.instruccionesMusica = Gdx.audio.newMusic(Gdx.files.internal("sounds/celticIntro.mp3"));
		this.instruccionesMusica.setLooping(true);
		
		// Crea la cámara y el lote donde enfocará
		this.camara = new OrthographicCamera();
		this.camara.setToOrtho(false, 2048, 2048);
		
		// Creo el rectángulo que representará lógicamente las instrucciones
		this.instruccionesR = new Rectangle();
		this.instruccionesR.x = 2048 / 2 - 1024 / 2;
		this.instruccionesR.y = 1024 / 2 - 1024 / 2;
		this.instruccionesR.width = 1024;
		this.instruccionesR.height = 1024;
	}

	@Override
	public void render(float delta) {		
		// Actualizo la cámara
		this.camara.update();
			
        // Dibuja en la pantalla
        this.juego.batch.begin();
        // Dibuja el fondo de las instrucciones
        this.juego.batch.draw(this.instruccionesImg, this.instruccionesR.x , this.instruccionesR.y);       
        this.juego.batch.end();
		
        if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
        	this.juego.setScreen(new MenuIntro(this.juego));
        	this.dispose();
        }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// Comienza la música de fondo cuando se ejecuta el screen
		this.instruccionesMusica.play();
				
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
		this.instruccionesImg.dispose();
		this.instruccionesMusica.dispose();
	}
}