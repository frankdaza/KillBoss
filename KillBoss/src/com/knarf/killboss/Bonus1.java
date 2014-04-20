package com.knarf.killboss;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Bonus1 implements Screen {
	
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
	
	// Puntaje
	public int puntaje = 0;
	
	public Texture pocimaImg;	
	public Array<Rectangle> pocimas = new Array<Rectangle>();	
	public long tiempoUltimaPocima;	
	public int numeroPocimas = 50;
	public int numeroPocimasJugador = 10;
	public Rectangle pocimaR;
	
	public Bonus1(final KillBoss juego, int puntaje) {
		this.juego = juego;
		this.puntaje = puntaje;
			
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
		this.fondoImg = new Texture(Gdx.files.internal("fondoNiveles.png"));
		
		// Cargo la imagen de la pocima
		this.pocimaImg = new Texture(Gdx.files.internal("pocima.png"));
		
		
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
		
		// Crel el rectángulo para la pocima
		this.pocimaR = new Rectangle();	
		this.pocimaR.x = 1024;
		this.pocimaR.y = 1024 - 160;
		this.pocimaR.width = 256;
		this.pocimaR.height = 256;
		
		// Creo la lluvia de llamas en el cielo
		this.lluviaPocimas();
											
	}
	
	/**
	 * Inicia la lluvia de llamas.
	 */
	public void lluviaPocimas() {
		Rectangle p  = new Rectangle();		
		p.x = MathUtils.random(0, 2048 - 64);
		p.y = 1024;
		p.width = 64;
		p.height = 64;		
		this.pocimas.add(p);
		this.tiempoUltimaPocima = TimeUtils.nanoTime();				
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
		this.juego.texto.draw(this.juego.batch, "Nivel Bonus", 2048 / 2 - 500, 1010);
		this.juego.texto.draw(this.juego.batch, "Cantidad Pocimas: " + this.numeroPocimasJugador, 2048 / 2 + 100, 1010);
		this.juego.texto.draw(this.juego.batch, "Puntaje: " + this.puntaje, 2048 / 2 + 500, 1010);		
		// Dibuja las llamas
		for (Rectangle p : this.pocimas) {
			this.juego.batch.draw(this.pocimaImg, p.x, p.y);			
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
        // Verifica si necesitamos crear mas llamas
        if (TimeUtils.nanoTime() - this.tiempoUltimaPocima > 1000000000 ) {
        	this.lluviaPocimas();
        }
        // 	move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.
        Iterator<Rectangle> iter = this.pocimas.iterator();
        while (iter.hasNext()) {
        	Rectangle p2 = iter.next();
        	p2.y -= 1200 * Gdx.graphics.getDeltaTime();
        	if (p2.y + 64 < 0 ) {
        		iter.remove();
        		this.numeroPocimas -= 1;
        	}
        	if (p2.overlaps(this.zackR) || p2.overlaps(this.zackDerechaR)) {        		        		
        		iter.remove();
        		this.numeroPocimas -= 1;
        		this.numeroPocimasJugador -= 1;
        	}
        }       
        if (this.numeroPocimasJugador == 0) {        	        	  
        	System.out.println("Ganaste un poder de pócima!");
        	this.juego.setScreen(new GanastePechera(this.juego, this.puntaje));
        }
        if (this.numeroPocimas == 0) {
        	this.juego.setScreen(new Mapa2(this.juego, this.puntaje));
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
		
		this.juego.batch.begin();		
		this.juego.batch.draw(this.currentFrameIzquierda, this.zackDerechaR.x, this.zackDerechaR.y);
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
		this.walkSheetDerecha.dispose();
		this.walkSheetIzquierda.dispose();
		this.zackNormal.dispose();
		this.fondoImg.dispose();				
	}

}