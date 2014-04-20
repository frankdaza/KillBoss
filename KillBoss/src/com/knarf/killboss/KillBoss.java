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
	public Mapa2 mapa2;
	public Mapa3 mapa3;
	public Mapa4 mapa4;
	public Mapa5 mapa5;
	public Mapa7 mapa7;
	public Nivel1 nivel1;
	public Nivel2 nivel2;
	public Nivel3 nivel3;
	public Nivel4 nivel4;
	public Nivel5 nivel5;
	public NivelFinal nivelFinal;
	public GameOver gameOver;	
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
