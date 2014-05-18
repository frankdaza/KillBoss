package com.knarf.killboss;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class JuegoNivelFinal implements Screen {
	
	final KillBoss juego;
	
	// Variables de los sprites
	private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 2;

    public Animation walkAnimationDerecha, walkAnimationIzquierda;
    public Texture walkSheetDerecha, walkSheetIzquierda, zackNormal;
    public TextureRegion[] walkFramesDerecha, walkFramesIzquierda;
    public SpriteBatch spriteBatchN, poderBatch;
    public TextureRegion currentFrameDerecha, currentFrameIzquierda;
    public float stateTime;
    
    // Variables del fondo, cámara y rectángulos
	public OrthographicCamera camara;
	public Texture fondoImg;
	public Rectangle fondoR, zackR, zackDerechaR;
	
	// Sonido cuando pierde y gana una pregunta
	public Sound auch, gana;
	
	// Sonido del dragon vuelo y grito
	public Music dragonGrito, dragonVuelo;
	
	// Vidas del jugador
	public int vidasZack;	
	
	// Vidas del dragón Boss
	public int vidasBoss = 80;
	
	// Puntaje
	public int puntaje;
	
	public Texture bossImg, llamaImg, poderImg;
	public Sound boss;
	public Array<Rectangle> llamas = new Array<Rectangle>();	
	public long tiempoUltimaLlama;	
	public long numeroLlamas = 400;
	public Rectangle bossR, poderR;
	
	public JuegoNivelFinal(final KillBoss juego, int puntaje, int vidas) {
		this.juego = juego;
		this.puntaje = puntaje;
		this.vidasZack = vidas + 1;
			
		// Configuro el sprite de zackDerecha
		this.walkSheetDerecha = new Texture(Gdx.files.internal("sprites/zackSpriteCascoD.png"));
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
		this.walkSheetIzquierda = new Texture(Gdx.files.internal("sprites/zackSpriteCascoI.png"));
		TextureRegion[][] tmp2 = TextureRegion.split(this.walkSheetIzquierda, this.walkSheetIzquierda.getWidth()/FRAME_COLS, this.walkSheetIzquierda.getHeight()/FRAME_ROWS);
		this.walkFramesIzquierda = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index2 = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
		    for (int j = 0; j < FRAME_COLS; j++) {
		    	this.walkFramesIzquierda[index2++] = tmp2[i][j];
		    }
		}
		this.walkAnimationIzquierda = new Animation(0.25f, this.walkFramesIzquierda);
				
		this.spriteBatchN = new SpriteBatch();	
		this.poderBatch = new SpriteBatch();
		
		this.stateTime = 0f;
		
		// Cargo la imagen de zack normal
		this.zackNormal = new Texture(Gdx.files.internal("zackArmadura.png"));
						
		// Cargo la imagen del fondo
		this.fondoImg = new Texture(Gdx.files.internal("fondoNiveles.png"));
		
		// Cargo la imagen de boss
		this.bossImg = new Texture(Gdx.files.internal("mapa/boss.png"));
		
		// Cargo la imagen de la llama
		this.llamaImg = new Texture(Gdx.files.internal("llama.png"));
		
		// Cargo la imagen del poder de Zack
		this.poderImg = new Texture(Gdx.files.internal("poder.png"));
		
		// Configuro el sonido cuando pierde una pregunta
		this.auch = Gdx.audio.newSound(Gdx.files.internal("sounds/auch.mp3"));
		
		// Configuro el sonido cuando gana una pregunta
		this.gana = Gdx.audio.newSound(Gdx.files.internal("sounds/gana.mp3"));
		
		// Cargo el sonido del grito del dragón
		this.dragonGrito = Gdx.audio.newMusic(Gdx.files.internal("sounds/dragonGrito.mp3"));
		this.dragonGrito.setLooping(true);
		this.dragonGrito.play();
		this.dragonGrito.setVolume((float) .3);
		
		// Cargo el sonido del vuelo del dragón
		this.dragonVuelo = Gdx.audio.newMusic(Gdx.files.internal("sounds/dragonVuelo.mp3"));
		this.dragonVuelo.setLooping(true);
		this.dragonVuelo.play();
		
		
		// Creo la cámara del juego
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
		this.zackR.x = 1024;
		this.zackR.y = 190;
		this.zackR.width = 64;
		this.zackR.height = 128;
		
		// Creo el rectángulo para zack derecha.
		this.zackDerechaR = new Rectangle();
		this.zackDerechaR.x = 1024;
		this.zackDerechaR.y = 190;
		this.zackDerechaR.width = 64;
		this.zackDerechaR.height = 128;
		
		// Creo el rectángulo para Boss
		this.bossR = new Rectangle();	
		this.bossR.x = 1024;
		this.bossR.y = 1024 - 160;
		this.bossR.width = 256;
		this.bossR.height = 256;
		
		// Creo el rectángulo para el poder de Zack
		this.poderR = new Rectangle();
		this.poderR.x = 1024;
		this.poderR.y = 254;
		this.poderR.width = 64;
		this.poderR.height = 64;
		
		// Creo la lluvia de llamas en el cielo
		this.lluviaLlamas();
											
	}
	
	/**
	 * Inicia la lluvia de llamas.
	 */
	public void lluviaLlamas() {
		Rectangle llama = new Rectangle();		
		llama.x = MathUtils.random(0, 2048 - 64);
		llama.y = 1024;
		llama.width = 64;
		llama.height = 64;		
		this.llamas.add(llama);
		this.tiempoUltimaLlama = TimeUtils.nanoTime();				
	}

	@Override
	public void render(float delta) {	
		this.camara.update();
		this.juego.texto.setScale((float) 1.5);
		
		this.spriteBatchN.setProjectionMatrix(this.camara.combined);
		
		
		this.stateTime += Gdx.graphics.getDeltaTime();
        this.currentFrameDerecha = this.walkAnimationDerecha.getKeyFrame(this.stateTime, true);
        this.currentFrameIzquierda = this.walkAnimationIzquierda.getKeyFrame(this.stateTime, true);
             
		this.juego.batch.begin();
		this.juego.batch.draw(this.fondoImg, this.fondoR.x, this.fondoR.y);
		this.juego.texto.setColor(0, 0, 0, 1);
		this.juego.texto.draw(this.juego.batch, "ESC: Salir", 100, 1010);
		this.juego.texto.draw(this.juego.batch, "Nivel Final - Pelea con boss", 2048 / 2 - 500, 1010);
		this.juego.texto.draw(this.juego.batch, "Vidas: " + this.vidasZack, 2048 / 2, 1010);
		this.juego.texto.draw(this.juego.batch, "Puntaje: " + this.puntaje, 2048 / 2 + 500, 1010);
		this.juego.texto.draw(this.juego.batch, "Vidas Boss: " + this.vidasBoss, 1024, 512);
		this.juego.batch.draw(this.bossImg, this.bossR.x, this.bossR.y);
		if (this.bossR.overlaps(this.poderR)) {
        	this.dragonGrito.play();
        	this.vidasBoss = this.vidasBoss - 1;
        }
		// Dibuja las llamas
		for (Rectangle llama : this.llamas) {
			this.juego.batch.draw(this.llamaImg, llama.x, llama.y);			
		}	
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
        if (!Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
     	   this.spriteBatchN.setColor(1, 1, 1, 1);
     	   this.spriteBatchN.begin();
            this.spriteBatchN.draw(this.zackNormal, this.zackR.x, this.zackR.y);
            this.spriteBatchN.end();
        }
        if (this.zackDerechaR.x > 2048 - 64) {
        	this.zackDerechaR.x = 2048 - 64;
        	this.zackR.x = 2048 - 64;
        	this.poderR.x = 2048 - 64;
        }
        if (this.zackDerechaR.x < 0) {
        	this.zackDerechaR.x = 0;
        	this.zackR.x = 0;
        	this.poderR.x = 0;
        }
        // Verifica si necesitamos crear mas llamas
        if (TimeUtils.nanoTime() - this.tiempoUltimaLlama > 100000000 ) {
        	this.lluviaLlamas();
        }
        // 	move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.
        Iterator<Rectangle> iter = this.llamas.iterator();
        while (iter.hasNext()) {
        	Rectangle llama = iter.next();
        	llama.y -= 700 * Gdx.graphics.getDeltaTime();
        	if (llama.y + 64 < 0 ) {
        		iter.remove();
        		this.numeroLlamas -= 1;
        	}
        	if (llama.overlaps(this.zackR) || llama.overlaps(this.zackDerechaR)) {
        		this.puntaje -= 100;
        		this.vidasZack -= 1;
        		this.auch.play((float) .3);
        		iter.remove();
        		this.numeroLlamas -= 1;
        	}
        }
        if (this.vidasZack <= 0 || this.numeroLlamas <= 0) {
        	this.juego.setScreen(new GameOver(this.juego));
        	this.dispose();
        }        
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
        	this.poderZack();        	
        }       
        if (this.poderR.y >= 1024 - 128) {
        	this.poderR.y = 254;
        }
        if (this.bossR.x < 0) {
        	this.bossR.x = 0;
        }
        if (this.bossR.x > 2048 - 128) {
        	this.bossR.x = 2048 - 128;
        }        
        if (this.vidasBoss <= 0) {
        	this.juego.setScreen(new ZackGana(this.juego, this.puntaje, this.vidasZack));
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
		this.zackR.x += 500 * Gdx.graphics.getDeltaTime();
		this.zackDerechaR.x += 500 * Gdx.graphics.getDeltaTime();
		this.poderR.x += 500 * Gdx.graphics.getDeltaTime();
		this.bossR.x += 1400 * Gdx.graphics.getDeltaTime();
		
		this.juego.batch.begin();		
		this.juego.batch.draw(this.currentFrameDerecha, this.zackDerechaR.x, this.zackDerechaR.y);
		this.juego.batch.end();
	}	
	
	/**
	 * Mueve el sprite hacia la izquierda
	 */
	public void zackIzquierda() {
		this.spriteBatchN.setColor(1, 1, 1, 0);
		this.zackR.x -= 500 * Gdx.graphics.getDeltaTime();
		this.zackDerechaR.x -= 500 * Gdx.graphics.getDeltaTime();
		this.poderR.x -= 500 * Gdx.graphics.getDeltaTime();
		this.bossR.x -= 1400 * Gdx.graphics.getDeltaTime();
		
		this.juego.batch.begin();		
		this.juego.batch.draw(this.currentFrameIzquierda, this.zackDerechaR.x, this.zackDerechaR.y);
		this.juego.batch.end();
	}
	
	/**
	 * Crea el poder de Zack y lo visualiza
	 */
	public void poderZack() {				
		this.poderR.y += 500 * Gdx.graphics.getDeltaTime();
		this.poderBatch.begin();
		this.poderBatch.draw(this.poderImg, this.poderR.x, this.poderR.y);
		this.poderBatch.end();		
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
		this.auch.dispose();
		this.gana.dispose();
		this.dragonGrito.dispose();
		this.dragonVuelo.dispose();
		this.bossImg.dispose();
		this.llamaImg.dispose();
	}
}