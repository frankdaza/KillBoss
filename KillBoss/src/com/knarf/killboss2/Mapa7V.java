package com.knarf.killboss2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.knarf.killboss.GuardarPartida;
import com.knarf.killboss.KillBoss;

public class Mapa7V implements Screen {
	
	final KillBoss juego;
	
	private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 2;

    public Animation walkAnimationDerecha, walkAnimationIzquierda;
    public Texture walkSheetDerecha, walkSheetIzquierda, valentinaNormal, fondoImg;
    public TextureRegion[] walkFramesDerecha, walkFramesIzquierda;
    public SpriteBatch spriteBatch, spriteBatchN;
    public TextureRegion currentFrameDerecha, currentFrameIzquierda;
    public float stateTime;
    public OrthographicCamera camara;    
    public Rectangle fondoR, valentinaR, valentinaDerechaR;
    public int puntaje;
    public int vidas;    
    public Texture bossImg;
    public Rectangle bossR;
        
	public Mapa7V(final KillBoss juego, int puntaje, int vidas) {		
		this.juego = juego;
		this.puntaje = puntaje;
		this.vidas = vidas;
		
		// Configuro el sprite de valentinaDerecha
		this.walkSheetDerecha = new Texture(Gdx.files.internal("sprites/valentinaSpriteArmaduraD.png"));
		TextureRegion[][] tmp = TextureRegion.split(this.walkSheetDerecha, this.walkSheetDerecha.getWidth()/FRAME_COLS, this.walkSheetDerecha.getHeight()/FRAME_ROWS);
		this.walkFramesDerecha = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
		    for (int j = 0; j < FRAME_COLS; j++) {
		    	this.walkFramesDerecha[index++] = tmp[i][j];
		    }
		}
		this.walkAnimationDerecha = new Animation(0.25f, this.walkFramesDerecha);
		
		// Configuro el sprite de valentinaIzquierda
		this.walkSheetIzquierda = new Texture(Gdx.files.internal("sprites/valentinaSpriteArmaduraI.png"));
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
		
		// Cargo la imagen de valentina normal
		this.valentinaNormal = new Texture(Gdx.files.internal("valentinaArmadura.png"));						
		
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
		
		// Creo el rectángulo para valentina normal.
		this.valentinaR = new Rectangle();
		this.valentinaR.x = 1350;
		this.valentinaR.y = 190;
		this.valentinaR.width = 64;
		this.valentinaR.height = 128;
		
		// Creo el rectángulo para valentina derecha.
		this.valentinaDerechaR = new Rectangle();
		this.valentinaDerechaR.x = 1350;
		this.valentinaDerechaR.y = 190;
		this.valentinaDerechaR.width = 64;
		this.valentinaDerechaR.height = 128;								
		
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
        this.spriteBatch.draw(this.bossImg, this.bossR.x, this.bossR.y);        
        this.spriteBatch.end();
        
        this.juego.batch.begin();
        this.juego.texto.draw(this.juego.batch, "ESC: Salir", 2048 / 2 - 500, 1010);
        this.juego.texto.draw(this.juego.batch, "Puntaje: " + this.puntaje, 2048 / 2, 1010);
        this.juego.texto.draw(this.juego.batch, "F1: Guardar Partida", 2048 / 2 + 500, 1010);
        this.juego.batch.end();
        
        this.spriteBatchN.begin();
        this.spriteBatchN.draw(this.valentinaNormal, this.valentinaR.x, this.valentinaR.y);
        this.spriteBatchN.end();
		
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
        	this.valentinaDerecha();
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
        	this.valentinaIzquierda();
        }
        if ( (this.valentinaR.x > 2048 - 64) || (this.valentinaDerechaR.x > 2048 - 64)) {
        	this.valentinaR.x = 2048 - 64;
        	this.valentinaDerechaR.x = 2048 - 64;        	
        }
        if ( (this.valentinaR.x < 0) || (this.valentinaDerechaR.x < 0) ) {
        	this.valentinaR.x = 0;
        	this.valentinaDerechaR.x = 0;        	
        }
       if (!Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
    	   this.spriteBatchN.setColor(1, 1, 1, 1);
    	   this.spriteBatchN.begin();
           this.spriteBatchN.draw(this.valentinaNormal, this.valentinaR.x, this.valentinaR.y);
           this.spriteBatchN.end();
       }
       if ( (this.valentinaR.overlaps(this.bossR)) || (this.valentinaDerechaR.overlaps(this.bossR)) ) {
    	   this.juego.setScreen(new NivelFinalV(this.juego, this.puntaje, this.vidas));
    	   this.dispose();
       }
       if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
    	   Gdx.app.exit();
    	   this.dispose();
       }
       if (Gdx.input.isKeyPressed(Keys.F1)) {
    	   GuardarPartida partida = new GuardarPartida(this.juego, this.puntaje, 7, 2);
    	   partida.inicio();
       }
       
	}

	/**
	 * Mueve el sprite hacia la derecha
	 */
	public void valentinaDerecha() {
		this.spriteBatchN.setColor(1, 1, 1, 0);
		this.valentinaR.x += 120 * Gdx.graphics.getDeltaTime();
		this.valentinaDerechaR.x += 120 * Gdx.graphics.getDeltaTime();
		
		this.spriteBatch.begin();		
		this.spriteBatch.draw(this.currentFrameDerecha, this.valentinaDerechaR.x, this.valentinaDerechaR.y);
		this.spriteBatch.end();
	}
	
	/**
	 * Mueve el sprite hacia la izquierda
	 */
	public void valentinaIzquierda() {
		this.spriteBatchN.setColor(1, 1, 1, 0);
		this.valentinaR.x -= 120 * Gdx.graphics.getDeltaTime();
		this.valentinaDerechaR.x -= 120 * Gdx.graphics.getDeltaTime();
		
		this.spriteBatch.begin();		
		this.spriteBatch.draw(this.currentFrameIzquierda, this.valentinaDerechaR.x, this.valentinaDerechaR.y);
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
		this.valentinaNormal.dispose();
		this.fondoImg.dispose();								
		this.bossImg.dispose();
	}
}