package com.knarf.killboss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Mapa4 implements Screen {
	
	final KillBoss juego;
	
	private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 2;

    public Animation walkAnimationDerecha, walkAnimationIzquierda;
    public Texture walkSheetDerecha, walkSheetIzquierda, zackNormal, fondoImg;
    public TextureRegion[] walkFramesDerecha, walkFramesIzquierda;
    public SpriteBatch spriteBatch, spriteBatchN;
    public TextureRegion currentFrameDerecha, currentFrameIzquierda;
    public float stateTime;
    public OrthographicCamera camara;    
    public Rectangle fondoR, zackR, zackDerechaR;
    public int puntaje = 0;
    
    // Variables para activar los niveles
    public Texture cascoImg, escudoImg, espadaImg, bossImg;
    public Rectangle cascoR, escudoR, espadaR, bossR;
        
	public Mapa4(final KillBoss juego, int puntaje) {		
		this.juego = juego;
		this.puntaje = puntaje;
		
		// Configuro el sprite de zackDerecha
		this.walkSheetDerecha = new Texture(Gdx.files.internal("sprites/zackSpritePiernasD.png"));
		TextureRegion[][] tmp = TextureRegion.split(this.walkSheetDerecha, this.walkSheetDerecha.getWidth()/FRAME_COLS, this.walkSheetDerecha.getHeight()/FRAME_ROWS);
		this.walkFramesDerecha = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
		    for (int j = 0; j < FRAME_COLS; j++) {
		    	this.walkFramesDerecha[index++] = tmp[i][j];
		    }
		}
		this.walkAnimationDerecha = new Animation(0.25f, this.walkFramesDerecha);
		
		// Configuro el sprite de zackIzquierda
		this.walkSheetIzquierda = new Texture(Gdx.files.internal("sprites/zackSpritePiernasI.png"));
		TextureRegion[][] tmp2 = TextureRegion.split(this.walkSheetIzquierda, this.walkSheetIzquierda.getWidth()/FRAME_COLS, this.walkSheetIzquierda.getHeight()/FRAME_ROWS);
		this.walkFramesIzquierda = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index2 = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
		    for (int j = 0; j < FRAME_COLS; j++) {
		    	this.walkFramesIzquierda[index2++] = tmp2[i][j];
		    }
		}
		this.walkAnimationIzquierda = new Animation(0.25f, this.walkFramesIzquierda);
		
		this.spriteBatch = new SpriteBatch();
		this.spriteBatchN = new SpriteBatch();		
		
		this.stateTime = 0f;
		
		// Cargo la imagen de fondo
		this.fondoImg = new Texture(Gdx.files.internal("mapa/fondo.png"));
		
		// Cargo la imagen de zack normal
		this.zackNormal = new Texture(Gdx.files.internal("zackPiernas.png"));					
	
		// Cargo la imagen del casco
		this.cascoImg = new Texture(Gdx.files.internal("mapa/casco.png"));
		
		// Cargo la imagen del escudo
		this.escudoImg = new Texture(Gdx.files.internal("mapa/escudo.png"));
		
		// Cargo la imagen de la espada
		this.espadaImg = new Texture(Gdx.files.internal("mapa/espada.png"));
		
		// Cargo la imagen de Boss
		this.bossImg = new Texture(Gdx.files.internal("mapa/boss.png"));
		
		// Creo la cámara
		this.camara = new OrthographicCamera();
		this.camara.setToOrtho(false, 2048, 1024);
	
		
		// Creo el rectángulo para el fondo
		this.fondoR = new Rectangle();
		this.fondoR.x = 2048 / 2 - 2048 / 2;
		this.fondoR.y = 1024 / 2 - 1024 / 2;
		this.fondoR.width = 2048;
		this.fondoR.height = 1024;
		
		// Creo el rectángulo para zack normal.
		this.zackR = new Rectangle();
		this.zackR.x = 750;
		this.zackR.y = 190;
		this.zackR.width = 64;
		this.zackR.height = 128;
		
		// Creo el rectángulo para zack derecha.
		this.zackDerechaR = new Rectangle();
		this.zackDerechaR.x = 750;
		this.zackDerechaR.y = 190;
		this.zackDerechaR.width = 64;
		this.zackDerechaR.height = 128;							
		
		// Creo el rectángulo para el casco
		this.cascoR = new Rectangle();
		this.cascoR.x = 950;
		this.cascoR.y = 200;
		this.cascoR.width = 64;
		this.cascoR.height = 64;
		
		// Creo el rectángulo para el escudo
		this.escudoR = new Rectangle();
		this.escudoR.x = 1150;
		this.escudoR.y = 200;
		this.escudoR.width = 64;
		this.escudoR.height = 64;
		
		// Creo el rectángulo para la espada
		this.espadaR = new Rectangle();
		this.espadaR.x = 1350;
		this.espadaR.y = 200;
		this.espadaR.width = 64;
		this.espadaR.height = 64;
		
		// Creo el rectángulo para Boss
		this.bossR = new Rectangle();
		this.bossR.x = 1600;
		this.bossR.y = 180;
		this.bossR.width = 256;
		this.bossR.height = 256;
		
	}

	@Override
	public void render(float delta) {
		this.camara.update();
		this.spriteBatch.setProjectionMatrix(this.camara.combined);
		this.spriteBatchN.setProjectionMatrix(this.camara.combined);
		
        this.stateTime += Gdx.graphics.getDeltaTime();
        this.currentFrameDerecha = this.walkAnimationDerecha.getKeyFrame(this.stateTime, true);
        this.currentFrameIzquierda = this.walkAnimationIzquierda.getKeyFrame(this.stateTime, true);
        
        this.spriteBatch.begin();        
        this.spriteBatch.draw(this.fondoImg, this.fondoR.x, this.fondoR.y);                
        this.spriteBatch.draw(this.cascoImg, this.cascoR.x, this.cascoR.y);
        this.spriteBatch.draw(this.escudoImg, this.escudoR.x, this.escudoR.y);
        this.spriteBatch.draw(this.espadaImg, this.espadaR.x, this.espadaR.y);
        this.spriteBatch.draw(this.bossImg, this.bossR.x, this.bossR.y);        
        this.spriteBatch.end();
        
        this.juego.batch.begin();
        this.juego.texto.draw(this.juego.batch, "ESC: Salir", 2048 / 2 - 500, 1010);
        this.juego.texto.draw(this.juego.batch, "Puntaje: " + this.puntaje, 2048 / 2, 1010);
        this.juego.texto.draw(this.juego.batch, "F1: Guardar Partida", 2048 / 2 + 500, 1010);
        this.juego.batch.end();
        
        this.spriteBatchN.begin();
        this.spriteBatchN.draw(this.zackNormal, this.zackR.x, this.zackR.y);
        this.spriteBatchN.end();
		
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
        	this.zackDerecha();
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
        	this.zackIzquierda();
        }
        if ( (this.zackR.x > 2048 - 64) || (this.zackDerechaR.x > 2048 - 64)) {
        	this.zackR.x = 2048 - 64;
        	this.zackDerechaR.x = 2048 - 64;        	
        }
        if ( (this.zackR.x < 0) || (this.zackDerechaR.x < 0) ) {
        	this.zackR.x = 0;
        	this.zackDerechaR.x = 0;        	
        }
       if (!Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
    	   this.spriteBatchN.setColor(1, 1, 1, 1);
    	   this.spriteBatchN.begin();
           this.spriteBatchN.draw(this.zackNormal, this.zackR.x, this.zackR.y);
           this.spriteBatchN.end();
       }
       if ( (this.zackR.overlaps(this.cascoR)) || (this.zackDerechaR.overlaps(this.cascoR)) ) {
    	   this.juego.setScreen(new Nivel4(this.juego, this.puntaje));
    	   this.dispose();
       }
       if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
    	   Gdx.app.exit();
    	   this.dispose();
       }
       
	}

	/**
	 * Mueve el sprite hacia la derecha
	 */
	public void zackDerecha() {
		this.spriteBatchN.setColor(1, 1, 1, 0);
		this.zackR.x += 120 * Gdx.graphics.getDeltaTime();
		this.zackDerechaR.x += 120 * Gdx.graphics.getDeltaTime();
		
		this.spriteBatch.begin();		
		this.spriteBatch.draw(this.currentFrameDerecha, this.zackDerechaR.x, this.zackDerechaR.y);
		this.spriteBatch.end();
	}
	
	/**
	 * Mueve el sprite hacia la izquierda
	 */
	public void zackIzquierda() {
		this.spriteBatchN.setColor(1, 1, 1, 0);
		this.zackR.x -= 120 * Gdx.graphics.getDeltaTime();
		this.zackDerechaR.x -= 120 * Gdx.graphics.getDeltaTime();
		
		this.spriteBatch.begin();		
		this.spriteBatch.draw(this.currentFrameIzquierda, this.zackDerechaR.x, this.zackDerechaR.y);
		this.spriteBatch.end();
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
		this.walkSheetDerecha.dispose();
		this.walkSheetIzquierda.dispose();
		this.zackNormal.dispose();
		this.fondoImg.dispose();						
		this.cascoImg.dispose();
		this.escudoImg.dispose();
		this.espadaImg.dispose();
		this.bossImg.dispose();
	}

}