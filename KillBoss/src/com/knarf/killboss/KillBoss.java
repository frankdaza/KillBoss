package com.knarf.killboss;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KillBoss extends Game {
	
	public SpriteBatch batch;
	public MenuIntro menu;
	public Instrucciones instrucciones;
	public HistoriaZack historiaZack;
	public HistoriaValentina historiaValentina;
	public Mapa mapa;
	public Nivel1 nivel1;
	public BitmapFont texto;
		
	@Override
	public void create() {	
		this.batch = new SpriteBatch();	
		this.texto = new BitmapFont();
		this.menu = new MenuIntro(this);	
		this.setScreen(menu);
	}

	@Override
	public void dispose() {
		this.batch.dispose();		
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}
