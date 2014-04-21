package com.knarf.killboss;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Abrir {
	
	final KillBoss juego;
	public BaseDeDatos db;

	public Abrir(final KillBoss juego) {
		this.juego = juego;
		this.db = new BaseDeDatos(this.juego);
	}
	
	public BaseDeDatos abrir() {
		try {			
			FileInputStream fileIn = new FileInputStream("BaseDeDatos.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        this.db = (BaseDeDatos) in.readObject();
	        in.close();
	        fileIn.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		return this.db;
	}
	
}
