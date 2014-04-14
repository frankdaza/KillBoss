package com.knarf.killboss;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Abrir {
	
	public BaseDeDatos db = new BaseDeDatos();

	public BaseDeDatos abrir() {
		try {			
			FileInputStream fileIn = new FileInputStream("/home/frank/Programming/KillBoss/BaseDeDatos.ser");
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
