package com.knarf.killboss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class HistoriaZack implements Screen  {
	
	final KillBoss juego;
	public OrthographicCamera camara; 
	public Texture zackHistoriaImg;
	public Rectangle zackR;
	public Music musica;
	
	/**
	 * Método constructor
	 * @param juego
	 */
	public HistoriaZack(final KillBoss juego) {
		this.juego = juego;
		
		// Cargo la imagen de la historia de zack
		this.zackHistoriaImg = new Texture(Gdx.files.internal("menu/zackHistoria.png"));
		
		// Crea la cámara y el lote donde enfocará
		this.camara = new OrthographicCamera();
		this.camara.setToOrtho(false, 2048, 1024);
		
		// Cargo la música de fondo
		this.musica = Gdx.audio.newMusic(Gdx.files.internal("sounds/irish.mp3"));
		this.musica.setLooping(true);
		
		// // Fondo zack historia rectángulo
		this.zackR = new Rectangle();
		this.zackR.x = 2048 / 2 - 1024 / 2;
		this.zackR.y = 1024 / 2 - 1024 / 2;
		this.zackR.width = 1024;
		this.zackR.height = 1024;
		
	}
	
	@Override
	public void render(float delta) {
		// Actualizo la cámara
		this.camara.update();
					
		// Dibuja en la pantalla
		this.juego.batch.begin();
		// Dibuja el fondo de las instrucciones
		this.juego.batch.draw(this.zackHistoriaImg, this.zackR.x, this.zackR.y);       
		this.juego.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			this.juego.setScreen(new Mapa(this.juego));
			this.dispose();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		this.musica.play();
		
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
		this.zackHistoriaImg.dispose();
		this.musica.dispose();
	}

}
