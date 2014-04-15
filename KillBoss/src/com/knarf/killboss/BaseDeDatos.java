package com.knarf.killboss;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseDeDatos implements Serializable {

	
	
	/**
	 * ID único de serialización
	 */
	private static final long serialVersionUID = 3740245451311275036L;
	
	public ArrayList<String> nivel1P = new ArrayList<String>(), nivel2P = new ArrayList<String>(), nivel3P = new ArrayList<String>(), nivel4P = new ArrayList<String>(), nivel5P = new ArrayList<String>(), nivel6P = new ArrayList<String>();
	public ArrayList<String> nivel1R = new ArrayList<String>(), nivel2R = new ArrayList<String>(), nivel3R = new ArrayList<String>(), nivel4R = new ArrayList<String>(), nivel5R = new ArrayList<String>(), nivel6R = new ArrayList<String>();
	public ArrayList<String> nivel1PR = new ArrayList<String>(), nivel2PR = new ArrayList<String>(), nivel3PR = new ArrayList<String>(), nivel4PR = new ArrayList<String>(), nivel5PR = new ArrayList<String>(), nivel6PR = new ArrayList<String>();
		
	/**
	 * Agrega una pregunta al nivel seleccionado.
	 * @param nivel
	 * @param pregunta
	 */
	public void addPregunta(int nivel, String pregunta) {
		switch (nivel) {
		case 1:  this.nivel1P.add(pregunta);
				 break;
		case 2:  this.nivel2P.add(pregunta);
		 		 break;
		case 3:  this.nivel3P.add(pregunta);
		 		 break;
		case 4:  this.nivel4P.add(pregunta);
		 		 break;
		case 5:  this.nivel5P.add(pregunta);
				 break;
		case 6:  this.nivel6P.add(pregunta);
		 		 break;
		}
	}
	
	/**
	 * Modifica una pregunta en el nivel seleccionado.
	 * @param nivel
	 * @param posicion
	 * @param pregunta
	 */
	public void setPregunta(int nivel, int posicion, String pregunta) {
		switch (nivel) {
		case 1:  this.nivel1P.set(posicion, pregunta);
				 break;
		case 2:  this.nivel2P.set(posicion, pregunta);
		 		 break;
		case 3:  this.nivel3P.set(posicion, pregunta);
		 		 break;
		case 4:  this.nivel4P.set(posicion, pregunta);
		 		 break;
		case 5:  this.nivel5P.set(posicion, pregunta);
				 break;
		case 6:  this.nivel6P.set(posicion, pregunta);
		 		 break;
		}
	}
	
	/**
	 * Elimina una pregunta de un nivel seleccionado dada una posición.
	 * @param nivel
	 * @param posicion
	 */
	public void dropPregunta(int nivel, int posicion) {
		switch (nivel) {
		case 1:  this.nivel1P.remove(posicion);
				 break;
		case 2:  this.nivel1P.remove(posicion);
		 		 break;
		case 3:  this.nivel1P.remove(posicion);
		 		 break;
		case 4:  this.nivel1P.remove(posicion);
		 		 break;
		case 5:  this.nivel1P.remove(posicion);
				 break;
		case 6:  this.nivel1P.remove(posicion);
		 		 break;		
		}
	}	
	
	/**
	 * Agrega una respuesta al nivel seleccionado.
	 * @param nivel
	 * @param Respuesta
	 */
	public void addRespuesta(int nivel, String respuesta) {
		switch (nivel) {
		case 1:  this.nivel1R.add(respuesta);
				 break;
		case 2:  this.nivel2R.add(respuesta);
				 break;
		case 3:  this.nivel3R.add(respuesta);
		 		 break;
		case 4:  this.nivel4R.add(respuesta);
		 		 break;
		case 5:  this.nivel5R.add(respuesta);
		 		 break;
		case 6:  this.nivel6R.add(respuesta);
		 		 break;
		}
	}
	
	/**
	 * Modifica una respuesta en el nivel seleccionado.
	 * @param nivel
	 * @param posicion
	 * @param respuesta
	 */
	public void setRespuesta(int nivel, int posicion, String respuesta) {
		switch (nivel) {
		case 1:  this.nivel1R.set(posicion, respuesta);
				 break;
		case 2:  this.nivel2R.set(posicion, respuesta);
				 break;
		case 3:  this.nivel3R.set(posicion, respuesta);
		 		 break;
		case 4:  this.nivel4R.set(posicion, respuesta);
		 		 break;
		case 5:  this.nivel5R.set(posicion, respuesta);
		 		 break;
		case 6:  this.nivel6R.set(posicion, respuesta);
		 		 break;
		}
	}
	
	/**
	 * Elimina una respuesta de un nivel seleccionado.
	 */
	public void dropRespuesta(int nivel, int posicion) {
		switch (nivel) {
		case 1:  this.nivel1R.remove(posicion);
				 break;
		case 2:  this.nivel2R.remove(posicion);
		 		 break;
		case 3:  this.nivel3R.remove(posicion);
		 		 break;
		case 4:  this.nivel4R.remove(posicion);
		 		 break;
		case 5:  this.nivel5R.remove(posicion);
		 	 	 break;
		case 6:  this.nivel6R.remove(posicion);
		 		 break;
		}
	}
		
	/**
	 * Agrega una posible respuesta a un nivel seleccionado.
	 * @param nivel
	 * @param posibleRespuesta
	 */
	public void addPosibleRespuesta(int nivel, String posibleRespuesta) {
		switch (nivel) {
		case 1:  this.nivel1PR.add(posibleRespuesta);
				 break;
		case 2:  this.nivel2PR.add(posibleRespuesta);
		 		 break;
		case 3:  this.nivel3PR.add(posibleRespuesta);
		 		 break;
		case 4:  this.nivel4PR.add(posibleRespuesta);
		 		 break;
		case 5:  this.nivel5PR.add(posibleRespuesta);
		 		 break;
		case 6:  this.nivel6PR.add(posibleRespuesta);
		 		 break;
		}
	}	
	
	/**
	 * Modifica una posible respuesta en un nivel seleccionado.
	 * @param nivel
	 * @param posicion
	 * @param posibleRespuesta
	 */
	public void setPosibleRespuesta(int nivel, int posicion, String posibleRespuesta) {
		switch (nivel) {
		case 1:  this.nivel1PR.set(posicion, posibleRespuesta);
				 break;
		case 2:  this.nivel2PR.set(posicion, posibleRespuesta);
		 		 break;
		case 3:  this.nivel3PR.set(posicion, posibleRespuesta);
		 		 break;
		case 4:  this.nivel4PR.set(posicion, posibleRespuesta);
		 		 break;
		case 5:  this.nivel5PR.set(posicion, posibleRespuesta);
		 		 break;
		case 6:  this.nivel6PR.set(posicion, posibleRespuesta);
		 		 break;
		}
	}
	
	/**
	 * Elimina una posible respuesta de un nivel seleccionado.
	 */
	public void dropPosibleRespuesta(int nivel, int posicion) {
		switch (nivel) {
		case 1:  this.nivel1PR.remove(posicion);
				 break;
		case 2:  this.nivel2PR.remove(posicion);
		 		 break;
		case 3:  this.nivel3PR.remove(posicion);
		 		 break;
		case 4:  this.nivel4PR.remove(posicion);
		 		 break;
		case 5:  this.nivel5PR.remove(posicion);
		 	 	 break;
		case 6:  this.nivel6PR.remove(posicion);
		 		 break;
		}
	}
			
	/**
	 * Imprime todas las preguntas de un nivel seleccionado.
	 * @param nivel
	 */
	public void imprimePreguntas(int nivel) {
		System.out.println("");
		System.out.println("*************************************");
		System.out.println("* Preguntas del nivel " + nivel + " *");
		System.out.println("*************************************");
		int tmp = 0;
		switch (nivel) {
		case 1:  for(String i : this.nivel1P) {
				 	System.out.println(tmp + " " + i + "\n");
				 	tmp++;
				 }
				 break;
		case 2:  for(String i : this.nivel2P) {
					 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 3:  for(String i : this.nivel3P) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 4:  for(String i : this.nivel4P) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 5:  for(String i : this.nivel5P) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 6:  for(String i : this.nivel6P) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		}
	}
		
	/**
	 * Imprime todas las respuestas de un nivel seleccionado.
	 * @param nivel
	 */
	public void imprimeRespuestas(int nivel) {
		System.out.println("");
		System.out.println("*************************************");
		System.out.println("* Respuestas del nivel " + nivel + " *");
		System.out.println("*************************************");
		int tmp = 0;
		switch (nivel) {
		case 1:  for(String i : this.nivel1R) {
				 	System.out.println(tmp + " " + i + "\n");
				 	tmp++;
				 }
				 break;
		case 2:  for(String i : this.nivel2R) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 3:  for(String i : this.nivel3R) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }		
		 		 break;
		case 4:  for(String i : this.nivel4R) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 5:  for(String i : this.nivel5R) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 6:  for(String i : this.nivel6R) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		}
	}
		
	/**
	 * Imprime todas las posibles respuestas de un nivel seleccionado.
	 * @param nivel
	 */
	public void imprimePosiblesRespuestas(int nivel) {
		System.out.println("");
		System.out.println("*************************************");
		System.out.println("* Posibles Respuestas del nivel " + nivel + " *");
		System.out.println("*************************************");
		int tmp = 0;
		switch (nivel) {
		case 1:  for(String i : this.nivel1PR) {
				 	System.out.println(tmp + " " + i + "\n");
				 	tmp++;
				 }
				 break;
		case 2:  for(String i : this.nivel2PR) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 3:  for(String i : this.nivel3PR) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }		
		 		 break;
		case 4:  for(String i : this.nivel4PR) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 5:  for(String i : this.nivel5PR) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		case 6:  for(String i : this.nivel6PR) {
		 			 System.out.println(tmp + " " + i + "\n");
		 			 tmp++;
		 		 }
		 		 break;
		}
	}
		
	/**
	 * Retorna una pregunta de un nivel y posición seleccionada.
	 * @param nivel
	 * @param posicion
	 * @return
	 */
	public String getPregunta(int nivel, int posicion) {
		// Pregunta a retornar
		String tmp = null;		
		switch (nivel) {
		case 1:  tmp = this.nivel1P.get(posicion);
				 break;
		case 2:  tmp = this.nivel2P.get(posicion);
		 		 break;
		case 3:  tmp = this.nivel3P.get(posicion);
		 		 break;
		case 4:  tmp = this.nivel4P.get(posicion);
		 		 break;
		case 5:  tmp = this.nivel5P.get(posicion);
		 		 break;
		case 6:  tmp = this.nivel6P.get(posicion);
		 		 break;
		}
		return tmp;
	}
		
	/**
	 * Retorna una respuesta de un nivel y posición seleccionada.
	 * @param nivel
	 * @param posicion
	 * @return
	 */
	public String getRespuesta(int nivel, int posicion) {
		// Respuesta a retornar
		String tmp = null;		
		switch (nivel) {
		case 1:  tmp = this.nivel1R.get(posicion);
				 break;
		case 2:  tmp = this.nivel2R.get(posicion);
		 		 break;
		case 3:  tmp = this.nivel3R.get(posicion);
		 		 break;
		case 4:  tmp = this.nivel4R.get(posicion);
		 		 break;
		case 5:  tmp = this.nivel5R.get(posicion);
		 		 break;
		case 6:  tmp = this.nivel6R.get(posicion);
		 		 break;
		}
		return tmp;
	}
		
	/**
	 * Retorna una posible respuesta de un nivel y posición seleccionada.
	 * @param nivel
	 * @param posicion
	 * @return
	 */
	public String getPosibleRespuesta(int nivel, int posicion) {
		// Posible respuesta a retornar
		String tmp = null;		
		switch (nivel) {
		case 1:  tmp = this.nivel1PR.get(posicion);
				 break;
		case 2:  tmp = this.nivel2PR.get(posicion);
		 		 break;
		case 3:  tmp = this.nivel3PR.get(posicion);
		 		 break;
		case 4:  tmp = this.nivel4PR.get(posicion);
		 		 break;
		case 5:  tmp = this.nivel5PR.get(posicion);
		 		 break;
		case 6:  tmp = this.nivel6PR.get(posicion);
		 		 break;
		}
		return tmp;
	}	
	
	/**
	 * Retorna el tamaño del array preguntas de un nivel seleccionado.
	 * @param nivel
	 * @return
	 */
	public int getPreguntasSize(int nivel) {
		// Tamaño del array preguntas
		int tmp = 0;
		switch (nivel) {
		case 1:  tmp = this.nivel1P.size();
				 break;
		case 2:  tmp = this.nivel2P.size();
		 		 break;
		case 3:  tmp = this.nivel3P.size();
		 		 break;
		case 4:  tmp = this.nivel4P.size();
		 		 break;
		case 5:  tmp = this.nivel5P.size();
		 		 break;
		case 6:  tmp = this.nivel6P.size();
		 		 break;
		}
		return tmp;
	}
		
	/**
	 * Retorna el tamaño del array respuestas de un nivel seleccionado.
	 * @param nivel
	 * @return
	 */
	public int getRespuestasSize(int nivel) {
		// Tamaño del array respuestas
		int tmp = 0;
		switch (nivel) {
		case 1:  tmp = this.nivel1R.size();
				 break;
		case 2:  tmp = this.nivel2R.size();
		 		 break;
		case 3:  tmp = this.nivel3R.size();
		 		 break;
		case 4:  tmp = this.nivel4R.size();
		 		 break;
		case 5:  tmp = this.nivel5R.size();
		 		 break;
		case 6:  tmp = this.nivel6R.size();
		 		 break;
		}
		return tmp;
	}
		
	/**
	 * Retorna el tamaño del array preguntas de un nivel seleccionado.
	 * @param nivel
	 * @return
	 */
	public int getPosiblesRespuestasSize(int nivel) {
		// Tamaño del array posibles respuestas
		int tmp = 0;
		switch (nivel) {
		case 1:  tmp = this.nivel1PR.size();
				 break;
		case 2:  tmp = this.nivel2PR.size();
		 		 break;
		case 3:  tmp = this.nivel3PR.size();
		 		 break;
		case 4:  tmp = this.nivel4PR.size();
		 		 break;
		case 5:  tmp = this.nivel5PR.size();
		 		 break;
		case 6:  tmp = this.nivel6PR.size();
		 		 break;
		}
		return tmp;
	}
}