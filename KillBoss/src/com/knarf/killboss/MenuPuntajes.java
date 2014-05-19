package com.knarf.killboss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MenuPuntajes implements Screen {
	
	final KillBoss juego;
	public Texture fondoImg;
	public Rectangle fondoR;
	public OrthographicCamera camara;
	
	public MenuPuntajes(final KillBoss juego) {
		this.juego = juego;				
		
		// Cargo la imagen de fondo
		this.fondoImg = new Texture(Gdx.files.internal("fondoPuntajes.png"));
		
		// Crea la cámara y el lote donde enfocará
		this.camara = new OrthographicCamera();
		this.camara.setToOrtho(true, 2048, 1024);
		
		// Creo el rectángulo para el fondo
		this.fondoR = new Rectangle();
		this.fondoR.x = 1024;
		this.fondoR.y = 512;
		this.fondoR.width = 2048;
		this.fondoR.height = 1024;
	}

	@Override
	public void render(float delta) {
		// Le dice a la cámara que actualice sus matrices.
		camara.update();
	
        // Dibuja en la pantalla
        this.juego.batch.begin();
        // Dibuja el fondo del los puntajes
        this.juego.batch.draw(this.fondoImg, this.fondoR.x, this.fondoR.y);     
        this.juego.batch.end();	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
}