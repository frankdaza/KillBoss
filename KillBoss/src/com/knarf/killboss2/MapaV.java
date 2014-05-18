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
import com.knarf.killboss.KillBoss;

public class MapaV implements Screen {
	
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
    
    // Variables para activar los niveles
    public Texture armaduraPechoImg, armaduraManosImg, armaduraPiernasImg, cascoImg, escudoImg, espadaImg, bossImg;
    public Rectangle armaduraPechoR, armaduraManosR, armaduraPiernasR, cascoR, escudoR, espadaR, bossR;
	
	public MapaV(final KillBoss juego) {
		
		this.juego = juego;
		
		// Configuro el sprite de valentinaDerecha
		this.walkSheetDerecha = new Texture(Gdx.files.internal("sprites/valentinaSpriteDerecha.png"));
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
		this.walkSheetIzquierda = new Texture(Gdx.files.internal("sprites/valentinaSpriteIzquierda.png"));
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
		this.valentinaNormal = new Texture(Gdx.files.internal("valentinaNormal.png"));
		
		// Cargo la imagen del pecho
		this.armaduraPechoImg = new Texture(Gdx.files.internal("mapa/armaduraPecho.png"));
		
		// Cargo la imagen de la armadura de las manos
		this.armaduraManosImg = new Texture(Gdx.files.internal("mapa/armaduraManos.png"));
		
		// Cargo la imagen de la armadura de las piernas
		this.armaduraPiernasImg = new Texture(Gdx.files.internal("mapa/armaduraPiernas.png"));
		
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
		
		// Creo el rectángulo para valentina normal.
		this.valentinaR = new Rectangle();
		this.valentinaR.x = 0;
		this.valentinaR.y = 190;
		this.valentinaR.width = 64;
		this.valentinaR.height = 128;
		
		// Creo el rectángulo para valentina derecha.
		this.valentinaDerechaR = new Rectangle();
		this.valentinaDerechaR.x = 0;
		this.valentinaDerechaR.y = 190;
		this.valentinaDerechaR.width = 64;
		this.valentinaDerechaR.height = 128;
		
		// Creo el rectángulo para la armadura del pecho
		this.armaduraPechoR = new Rectangle();
		this.armaduraPechoR.x = 350;
		this.armaduraPechoR.y = 200;
		this.armaduraPechoR.width = 64;
		this.armaduraPechoR.height = 64;
		
		// Creo el rectángulo para la armadura de las manos
		this.armaduraManosR = new Rectangle();
		this.armaduraManosR.x = 550;
		this.armaduraManosR.y = 200;
		this.armaduraManosR.width = 64;
		this.armaduraManosR.height = 64;
		
		// Creo el rectángulo para la armadura de las piernas
		this.armaduraPiernasR = new Rectangle();
		this.armaduraPiernasR.x = 750;
		this.armaduraPiernasR.y = 200;
		this.armaduraPiernasR.width = 64;
		this.armaduraPiernasR.height = 64;
		
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
        this.spriteBatch.draw(this.armaduraPechoImg, this.armaduraPechoR.x, this.armaduraPechoR.y);
        this.spriteBatch.draw(this.armaduraManosImg, this.armaduraManosR.x, this.armaduraManosR.y);
        this.spriteBatch.draw(this.armaduraPiernasImg, this.armaduraPiernasR.x, this.armaduraPiernasR.y);
        this.spriteBatch.draw(this.cascoImg, this.cascoR.x, this.cascoR.y);
        this.spriteBatch.draw(this.escudoImg, this.escudoR.x, this.escudoR.y);
        this.spriteBatch.draw(this.espadaImg, this.espadaR.x, this.espadaR.y);
        this.spriteBatch.draw(this.bossImg, this.bossR.x, this.bossR.y);
        this.spriteBatch.end();
        
        this.juego.batch.begin();
        this.juego.texto.setColor(0, 0, 0, 1);
		this.juego.texto.draw(this.juego.batch, "ESC: Salir", 100, 1010);
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
       if ( (this.valentinaR.overlaps(this.armaduraPechoR)) || (this.valentinaDerechaR.overlaps(this.armaduraPechoR)) ) {
    	   this.juego.setScreen(new Nivel1V(this.juego));    	   
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
		this.armaduraPechoImg.dispose();
		this.armaduraManosImg.dispose();
		this.armaduraPiernasImg.dispose();
		this.cascoImg.dispose();
		this.escudoImg.dispose();
		this.espadaImg.dispose();
		this.bossImg.dispose();
	}
}