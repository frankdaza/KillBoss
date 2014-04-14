package com.knarf.killboss;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class JuegoNivel1 implements Screen {
	
	final KillBoss juego;
	
	// Variables de los sprites
	private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 2;

    public Animation walkAnimationDerecha, walkAnimationIzquierda;
    public Texture walkSheetDerecha, walkSheetIzquierda, zackNormal;
    public TextureRegion[] walkFramesDerecha, walkFramesIzquierda;
    public SpriteBatch spriteBatchN;
    public TextureRegion currentFrameDerecha, currentFrameIzquierda;
    public float stateTime;
    
    // Variables del fondo, cámara y rectángulos
	public OrthographicCamera camara;
	public Texture fondoImg;
	public Rectangle fondoR, zackR, zackDerechaR;
	
	// Vidas del jugador
	public int vidas = 3;
	
	// Pregunta, respuesta, posible respuesta
	public String pregunta, respuesta, posibleRespuesta;
	
	// Pregunta al azar
	String q = this.pregunta();
	
	// Base de datos
	public BaseDeDatos db = new BaseDeDatos();
	
	
	public JuegoNivel1(final KillBoss juego) {
		this.juego = juego;
		
		// Configuro el sprite de zackDerecha
		this.walkSheetDerecha = new Texture(Gdx.files.internal("sprites/zackSpriteDerecha.png"));
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
		this.walkSheetIzquierda = new Texture(Gdx.files.internal("sprites/zackSpriteIzquierda.png"));
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
		
		this.stateTime = 0f;
		
		// Cargo la imagen de zack normal
		this.zackNormal = new Texture(Gdx.files.internal("zackNormal.png"));
				
		
		// Cargo la imagen del fondo
		this.fondoImg = new Texture(Gdx.files.internal("nivel1/fondo.png"));
		
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
		this.zackR.x = 0;
		this.zackR.y = 190;
		this.zackR.width = 64;
		this.zackR.height = 128;
		
		// Creo el rectángulo para zack derecha.
		this.zackDerechaR = new Rectangle();
		this.zackDerechaR.x = 0;
		this.zackDerechaR.y = 190;
		this.zackDerechaR.width = 64;
		this.zackDerechaR.height = 128;
										
	}

	@Override
	public void render(float delta) {	
		this.camara.update();
		this.juego.texto.setScale(2, 2);
		this.spriteBatchN.setProjectionMatrix(this.camara.combined);			
		
		this.stateTime += Gdx.graphics.getDeltaTime();
        this.currentFrameDerecha = this.walkAnimationDerecha.getKeyFrame(this.stateTime, true);
        this.currentFrameIzquierda = this.walkAnimationIzquierda.getKeyFrame(this.stateTime, true);
             
		this.juego.batch.begin();
		this.juego.batch.draw(this.fondoImg, this.fondoR.x, this.fondoR.y);
		this.juego.texto.setColor(0, 0, 0, 1);
		this.juego.texto.draw(this.juego.batch, "Vidas: " + this.vidas, 0, 1024);
		this.juego.texto.draw(this.juego.batch, this.q, 2048 / 2 - 100, 1024 / 2 + 100);
		
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
		
	}
	
	/**
	 * Mueve el sprite hacia la derecha
	 */
	public void zackDerecha() {
		this.spriteBatchN.setColor(1, 1, 1, 0);
		this.zackR.x += 120 * Gdx.graphics.getDeltaTime();
		this.zackDerechaR.x += 120 * Gdx.graphics.getDeltaTime();
		
		this.juego.batch.begin();		
		this.juego.batch.draw(this.currentFrameDerecha, this.zackDerechaR.x, this.zackDerechaR.y);
		this.juego.batch.end();
	}	
	
	/**
	 * Mueve el sprite hacia la izquierda
	 */
	public void zackIzquierda() {
		this.spriteBatchN.setColor(1, 1, 1, 0);
		this.zackR.x -= 120 * Gdx.graphics.getDeltaTime();
		this.zackDerechaR.x -= 120 * Gdx.graphics.getDeltaTime();
		
		this.juego.batch.begin();		
		this.juego.batch.draw(this.currentFrameIzquierda, this.zackDerechaR.x, this.zackDerechaR.y);
		this.juego.batch.end();
	}
	
	/**
	 * Retorna una pregunta al azar de las base de datos
	 * @return
	 */
	public String pregunta() {	
		Abrir a = new Abrir();
		this.db = a.abrir();
		// Tamaño array pregunta
		int tmp = this.db.getPreguntasSize(1);
		// Creo número aleatorio
		Random rand = new Random();
		int tmp2 = rand.nextInt(tmp);
		// Pregunta al azar del array preguntas
		String tmp3 = this.db.getPregunta(1, tmp2);		
		return tmp3;
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
