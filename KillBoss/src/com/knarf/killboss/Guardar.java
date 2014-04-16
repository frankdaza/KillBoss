package com.knarf.killboss;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Guardar {
	
	public void guardar(BaseDeDatos db) {
		try {			
			FileOutputStream fileOut =
			new FileOutputStream("BaseDeDatos.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(db);
			out.close();
			fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}

}
