package com.knarf.killboss;

import java.util.Scanner;

public class Administrador {
	
	final KillBoss juego;
	
	public Administrador(final KillBoss juego) {
		this.juego = juego;
	}
	
	public boolean salir = true;
	public BaseDeDatos db = new BaseDeDatos(this.juego);
	
	@SuppressWarnings("resource")
	public void inicio() {
		do {			
			System.out.println("");
			System.out.println("****************************************");
			System.out.println("*	  PANEL ADMINISTRADOR KILL BOSS	   *");
			System.out.println("****************************************");
			System.out.println("");
			System.out.println("Ingrese el número de la opción deseada:");
			System.out.println("");
			System.out.println("1 - Agregar una pregunta a un nivel.");
			System.out.println("2 - Modificar una pregunta de un nivel.");
			System.out.println("3 - Eliminar una pregunta de un nivel.");
			System.out.println("4 - Agregar una respuesta a un nivel.");
			System.out.println("5 - Modificar una respuesta de un nivel.");
			System.out.println("6 - Eliminar una respuesta de un nivel.");
			System.out.println("7 - Agregar una posible respuesta a un nivel.");
			System.out.println("8 - Modificar una posible respuesta de un nivel.");
			System.out.println("9 - Eliminar una posible respuesta de un nivel.");
			System.out.println("10 - Imprimir todas las preguntas de un nivel.");
			System.out.println("11 - Imprimir todas las respuestas de un nivel.");
			System.out.println("12 - Imprimir todas las posibles respuestas de un nivel.");			
			System.out.println("13 - Salir.");
			System.out.println("");
			
			Scanner op = new Scanner(System.in);
			int num = op.nextInt();
			switch (num) {
			case 1:  this.opcion1();
					 break;
			case 2:  this.opcion2();
					 break;
			case 3:  this.opcion3();
					 break;
			case 4:	 this.opcion4();
					 break;
			case 5:  this.opcion5();
					 break;
			case 6:  this.opcion6();
					 break;
			case 7:  this.opcion7();
					 break;
			case 8:	 this.opcion8();
					 break;
			case 9:	 this.opcion9();					 
					 break;
			case 10: this.opcion10();					 
			 		 break;
			case 11: this.opcion11();					 
			 		 break;
			case 12: this.opcion12();					 
			 		 break;					 
			case 13: this.salir = false;
					 break;
			default: System.out.println("Ingrese una opción válida.");
			}			
		} 
		while (this.salir == true);		
	}
		
	/**
	 * Agrega una pregunta a un nivel.
	 */
	@SuppressWarnings("resource")
	public void opcion1() {
		System.out.println("Ingrese el nivel en el cual desea agregar una pregunta:");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		System.out.println("Ingrese la pregunta que desea agregar al nivel " + nivel + " :");
		Scanner op2 = new Scanner(System.in);
		String pregunta = op2.nextLine();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.addPregunta(nivel, pregunta);		
		Guardar tmp = new Guardar();
		tmp.guardar(this.db);
	}
	
	/**
	 * Modifica una pregunta de un nivel.
	 */
	@SuppressWarnings("resource")
	public void opcion2() {
		System.out.println("Ingrese el nivel en el cual desea modificar una pregunta:");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		System.out.println("Ingrese la pregunta que desea modificar en el nivel " + nivel + " :");
		Scanner op2 = new Scanner(System.in);
		String pregunta = op2.nextLine();
		System.out.println("Ingrese la posición de la pregunta: ");
		Scanner op3 = new Scanner(System.in);
		int posicion = op3.nextInt();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.setPregunta(nivel, posicion, pregunta);		
		Guardar tmp = new Guardar();
		tmp.guardar(this.db);
	}
	
	/**
	 * Elimina una pregunta de un nivel.
	 */
	@SuppressWarnings("resource")
	public void opcion3() {
		System.out.println("Ingrese el nivel en el cual desea eliminar la pregunta: ");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		System.out.println("Ingrese la posición de la pregunta que desea eliminar del nivel " + nivel + " :");
		Scanner op2 = new Scanner(System.in);
		int pos2 = op2.nextInt();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.dropPregunta(nivel, pos2);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
		
	/**
	 * Agrega una respuesta a un nivel seleccionado.
	 */
	@SuppressWarnings("resource")
	public void opcion4() {
		System.out.println("Ingrese el nivel en el cual desea agregar la respuesta: ");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		System.out.println("Ingrese la respuesta que desea agregar al nivel " + nivel);
		Scanner op2 = new Scanner(System.in);
		String respuesta = op2.nextLine();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.addRespuesta(nivel, respuesta);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
	
	/**
	 * Modifica una respuesta de un nivel seleccionado.
	 */
	@SuppressWarnings("resource")
	public void opcion5() {
		System.out.println("Ingrese el nivel en el cual desea modificar la respuesta: ");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		System.out.println("Ingrese la respuesta que desea modificar al nivel " + nivel);
		Scanner op2 = new Scanner(System.in);
		String respuesta = op2.nextLine();
		System.out.println("Ingrese la posición de la respuesta: ");
		Scanner op3 = new Scanner(System.in);
		int posicion = op3.nextInt();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.setRespuesta(nivel, posicion, respuesta);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
	
	/**
	 * Elimina una respuesta de un nivel seleccionado.
	 */
	@SuppressWarnings("resource")
	public void opcion6() {
		System.out.println("Ingrese el nivel en el cual desea eliminar la respuesta: ");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		System.out.println("Ingrese la posición de la respuesta que desea eliminar: ");
		Scanner op2 = new Scanner(System.in);
		int pos = op2.nextInt();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.dropRespuesta(nivel, pos);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
		
	/**
	 * Agrega una posible respuesta a un nivel seleccionado.
	 */
	@SuppressWarnings("resource")
	public void opcion7() {
		System.out.println("Ingrese el nivel en el cual desea agregar la posible respuesta: ");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		System.out.println("Ingrese la posible respuesta que desea agregar al nivel " + nivel + ": ");
		Scanner op2 = new Scanner(System.in);
		String posibleRespuesta = op2.nextLine();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.addPosibleRespuesta(nivel, posibleRespuesta);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
	
	/**
	 * Modifica una posible respuesta de un nivel seleccionado.
	 */
	@SuppressWarnings("resource")
	public void opcion8() {
		System.out.println("Ingrese el nivel en el cual desea modificar la posible respuesta: ");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		System.out.println("Ingrese la posible respuesta que desea modificar al nivel " + nivel + ": ");
		Scanner op2 = new Scanner(System.in);
		String posibleRespuesta = op2.nextLine();
		System.out.println("Ingrese la posición de la posible respuesta: ");
		Scanner op3 = new Scanner(System.in);
		int posicion = op3.nextInt();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.setPosibleRespuesta(nivel, posicion, posibleRespuesta);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
	
	/**
	 * Elimina una posible respuesta de un nivel seleccionado.
	 */
	@SuppressWarnings("resource")
	public void opcion9() {
		System.out.println("Ingrese el nivel en el cual desea eliminar la posible respuesta: ");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		System.out.println("Ingrese la posición de la posible respuesta que desea eliminar: ");
		Scanner op2 = new Scanner(System.in);
		int pos = op2.nextInt();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.dropPosibleRespuesta(nivel, pos);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
	
	/**
	 * Imprime todas las preguntas de un nivel seleccionado.
	 */
	@SuppressWarnings("resource")
	public void opcion10() {
		System.out.println("ingrese el nivel");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		Abrir tmp = new Abrir(this.juego);
		this.db = tmp.abrir();
		this.db.imprimePreguntas(nivel);
	}
	
	/**
	 * Imprime todas las respuestas de un nivel seleccionado
	 */
	@SuppressWarnings("resource")
	public void opcion11() {
		System.out.println("ingrese el nivel");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		Abrir tmp = new Abrir(this.juego);
		this.db = tmp.abrir();
		this.db.imprimeRespuestas(nivel);
	}
	
	
	/**
	 * Imprime todas las posibles respuestas de un nivel seleccionado
	 */
	@SuppressWarnings("resource")
	public void opcion12() {
		System.out.println("ingrese el nivel");
		Scanner op = new Scanner(System.in);
		int nivel = op.nextInt();
		Abrir tmp = new Abrir(this.juego);
		this.db = tmp.abrir();
		this.db.imprimePosiblesRespuestas(nivel);
	}

}