package com.knarf.killboss;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "KillBoss - La amenaza de los ingenieros";
		cfg.width = 2048;
		cfg.height = 1024;
		
		new LwjglApplication(new KillBoss(), cfg);
	}	
}

