package com.knarf.killboss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
//import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Bonus2 implements Screen {
	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 2;
	public int puntaje = 0;
	public Animation walkAnimationDerecha, walkAnimationIzquierda, Money;
	public Texture walkSheetDerecha, walkSheetIzquierda, zackNormal, fondoImg,
			monedas;
	public TextureRegion[] walkFramesDerecha, walkFramesIzquierda, monedaMov;
	public SpriteBatch spriteBatch, spriteBatchN;
	public TextureRegion currentFrameDerecha, currentFrameIzquierda;
	final KillBoss juego;
	public Texture fondoNive11;
	public Rectangle fondoR, pre4, pre5, zackDireccion, pre1, limite1, limite2,
			limite3, pre2, pre3;
	public OrthographicCamera camara;
	public SpriteBatch lote;
	public float stateTime;
	float unitScale = 16;
	TiledMap map;
	public OrthogonalTiledMapRenderer renderer;
	private World world;
	private Body jumper;
	public static final float PIXELS_PER_METER = 60.0f;
	private boolean jumperFacingRight;
	private Sprite jumperSprite;
	TiledMapRenderer tileMapRenderer;
	public Texture interrogacion;
	boolean salto = false;
	public int aumentoPuntaje = 0;

	/**
	 * M�todo constructor
	 */
	public Bonus2(final KillBoss juego, int puntaje) {
		this.juego = juego;

		// Cargo la imagen de fondo
		//this.fondoImg = new Texture(Gdx.files.internal("nivel1.png"));
		//this.fondoNive11 = new Texture(Gdx.files.internal("fondo.png"));
		this.zackNormal = new Texture(Gdx.files.internal("zackNormal.png"));
		this.interrogacion = new Texture(Gdx.files.internal("pregunta1.png"));
		this.zackNormal.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.walkSheetDerecha = new Texture(
				Gdx.files.internal("sprites/zackSpriteDerecha.png"));
		TextureRegion[][] tmp = TextureRegion.split(this.walkSheetDerecha,
				this.walkSheetDerecha.getWidth() / FRAME_COLS,
				this.walkSheetDerecha.getHeight() / FRAME_ROWS);
		this.walkFramesDerecha = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				this.walkFramesDerecha[index++] = tmp[i][j];
			}
		}
		this.walkAnimationDerecha = new Animation(0.125f,
				this.walkFramesDerecha);
		// ///////////////////////////////////////////////////////////////////

		// ////////////////////////////////////////////////////////////////////////////
		this.walkSheetIzquierda = new Texture(
				Gdx.files.internal("sprites/zackSpriteIzquierda.png"));
		TextureRegion[][] tmp2 = TextureRegion.split(this.walkSheetIzquierda,
				this.walkSheetIzquierda.getWidth() / FRAME_COLS,
				this.walkSheetIzquierda.getHeight() / FRAME_ROWS);
		this.walkFramesIzquierda = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index2 = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				this.walkFramesIzquierda[index2++] = tmp2[i][j];
			}
		}
		this.walkAnimationIzquierda = new Animation(0.125f,
				this.walkFramesIzquierda);

		// //////////confuramos la camara//////////////
		// map = new TmxMapLoader().load("Bonus2/Bonus1.tmx");

		// ///////////////////////////////// cargar propiedades de mapa
		// map.getTileSets();
		// map.getProperties();
		// map.dispose();
		// /////////////////////////////////////////////////////////////////////////////////////////
		//
		// camara = new OrthographicCamera();
		// camara.setToOrtho(false, 2048, 1024);
		// renderer = new OrthogonalTiledMapRenderer(map, 2);
		// renderer.getSpriteBatch().disableBlending();
		// renderer.setView(camara);
		// /////////////////////////////////////////////////////////////
		this.pre1 = new Rectangle();
		this.pre1.x = 200;
		this.pre1.y = 300;
		this.pre1.height = 50;
		this.pre1.width = 50;

		this.pre2 = new Rectangle();
		this.pre2.x = 0;
		this.pre2.y = 400;
		this.pre2.height = 50;
		this.pre2.width = 50;

		this.pre3 = new Rectangle();
		this.pre3.x = 0;
		this.pre3.y = 1024;
		this.pre3.height = 50;
		this.pre3.width = 50;

		this.pre4 = new Rectangle();
		this.pre4.x = 2048;
		this.pre4.y = 0;
		this.pre4.height = 50;
		this.pre4.width = 50;

		this.pre5 = new Rectangle();
		this.pre5.x = (2048 / 2) + 100;
		this.pre5.y = 0;
		this.pre5.height = 50;
		this.pre5.width = 50;

		// /////////////////////////////////////////////////////////////////

		this.limite1 = new Rectangle();
		// ////////////////////////////////////////////////////////////////
		this.zackDireccion = new Rectangle();
		this.zackDireccion.x = 2048 / 2;
		this.zackDireccion.y = 0;
		this.zackDireccion.width = 64;
		this.zackDireccion.height = 128;

		// ////////////////////////////////////////////////////////////////

		this.jumperSprite = new Sprite(zackNormal, (int) zackDireccion.x,
				(int) zackDireccion.y, 64, 128);
		world = new World(new Vector2(0.0f, -10.0f), true);

		BodyDef jumperBodyDef = new BodyDef();
		jumperBodyDef.type = BodyDef.BodyType.DynamicBody;
		jumperBodyDef.position.set(zackDireccion.x, zackDireccion.y);

		jumper = world.createBody(jumperBodyDef);

		PolygonShape jumperShape = new PolygonShape();
		jumperShape.setAsBox(this.zackDireccion.width / (2 * PIXELS_PER_METER),
				this.zackDireccion.height / (2 * PIXELS_PER_METER));

		this.jumper.setFixedRotation(true);

		FixtureDef jumperFixtureDef = new FixtureDef();
		jumperFixtureDef.shape = jumperShape;
		jumperFixtureDef.density = 1.0f;
		jumperFixtureDef.friction = 5.0f;

		this.jumper.createFixture(jumperFixtureDef);
		jumperShape.dispose();

		this.lote = new SpriteBatch();
		this.stateTime = 0f;
		// //////////////////////////////////////////

	}

	public void zackDerecha() {
		jumper.applyLinearImpulse(new Vector2(
				zackDireccion.x += 350 * Gdx.graphics.getDeltaTime(), 0),
				jumper.getWorldCenter(), true);
		this.lote.begin();
		this.lote.draw(currentFrameDerecha, this.zackDireccion.x,
				this.zackDireccion.y);
		if (jumperFacingRight == false) {
			jumperSprite.flip(true, false);
		}
		this.lote.end();

	}

	public void movimientoPlataformaBonus() {
		this.pre1.x += 600 * Gdx.graphics.getDeltaTime();
		this.pre2.x += 400 * Gdx.graphics.getDeltaTime();
		this.pre3.y -= 300 * Gdx.graphics.getDeltaTime();
		this.pre4.x -= 500 * Gdx.graphics.getDeltaTime();
		this.pre5.y += 300 * Gdx.graphics.getDeltaTime();
	}

	public void zackIzquierda() {
		jumper.applyLinearImpulse(new Vector2(
				zackDireccion.x -= 350 * Gdx.graphics.getDeltaTime(), 0.0f),
				jumper.getWorldCenter(), true);
		if (jumperFacingRight == false) {
			jumperSprite.flip(true, false);
		}
		this.lote.begin();
		this.lote.draw(this.currentFrameIzquierda, this.zackDireccion.x,
				this.zackDireccion.y);
		this.lote.end();
	}

	public void saltar() {
		if (Math.abs(jumper.getLinearVelocity().y) < 10) {
			jumper.applyLinearImpulse(
					new Vector2(0.6f, zackDireccion.y += 0.66f * Gdx.graphics
							.getFramesPerSecond()), jumper.getWorldCenter(),
					false);
		}

		this.lote.begin();
		this.lote.draw(currentFrameDerecha, this.zackDireccion.x,
				this.zackDireccion.y);
		this.lote.end();
	}

	public void mostrarPreg() {
		this.lote.begin();
		this.lote.draw(this.interrogacion, this.pre1.x, this.pre1.y);
		this.lote.draw(this.interrogacion, this.pre2.x, this.pre2.y);
		this.lote.draw(this.interrogacion, this.pre3.x, this.pre3.y);
		this.lote.draw(this.interrogacion, this.pre4.x, this.pre4.y);
		this.lote.draw(this.interrogacion, this.pre5.x, this.pre5.y);
		this.lote.end();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL10.GL_BLEND);
		// this.lote.setProjectionMatrix(this.camara.combined);
		// Actualizo la c�mara
		// this.camara.update();
		// this.renderer.setView(camara);
		// this.renderer.render();

		zackDireccion.y -= 700 * Gdx.graphics.getDeltaTime();
		movimientoPlataformaBonus();
		mostrarPreg();
		// /////////////////////////////////////////////////////////
		// if (this.zackDireccion.overlaps(pre1)
		// || this.zackDireccion.overlaps(pre2)
		// || this.zackDireccion.overlaps(pre3)
		// || this.zackDireccion.overlaps(pre4)
		// || this.zackDireccion.overlaps(pre5)){
		// this.puntaje = + 80;
		// }
		if (this.zackDireccion.overlaps(pre1)) {
			this.puntaje = +80;
		} else if (this.zackDireccion.overlaps(pre2)) {
			this.puntaje = +80;
		} else if (this.zackDireccion.overlaps(pre3)) {
			this.puntaje = +80;
		} else if (this.zackDireccion.overlaps(pre4)) {
			this.puntaje = +80;
		} else if (this.zackDireccion.overlaps(pre5)) {
			this.puntaje = +80;
		}

		// ////////////////////////////////////////////////////////////////////
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			salto = true;
			if (salto) {
				System.out.println("Puede saltar");
				saltar();
				if (zackDireccion.y > 200) {
					zackDireccion.y = 200 - 20;
					System.out.println("Si fue posible el salto");
					salto = false;

				}

			}

		}
		// //////////////////////////////////////////////////////////////////////

		if (!Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			this.lote.begin();
			this.lote.draw(this.zackNormal, this.zackDireccion.x,
					this.zackDireccion.y);
			this.lote.end();
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.zackDerecha();
			// camara.position.x += 3.6f + Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.zackIzquierda();
			// camara.position.x -= 3.6f + Gdx.graphics.getDeltaTime();
		}

		this.world.step(stateTime, 3, 3);
		this.stateTime += Gdx.graphics.getDeltaTime();
		this.currentFrameDerecha = this.walkAnimationDerecha.getKeyFrame(
				this.stateTime, true);
		this.currentFrameIzquierda = this.walkAnimationIzquierda.getKeyFrame(
				this.stateTime, true);

		if (zackDireccion.x < 0)
			zackDireccion.x = 0;

		if (zackDireccion.y < 0) {
			zackDireccion.y = 0;
		}

		if (zackDireccion.y > 1024 - 64)
			zackDireccion.y = 1024 - 64;

		// if (camara.position.x < Gdx.graphics.getWidth() / 2) {
		// camara.position.x = Gdx.graphics.getWidth() / 2;
		// }

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
		this.fondoImg.dispose();
		this.fondoNive11.dispose();
		this.map.dispose();

	}

}
