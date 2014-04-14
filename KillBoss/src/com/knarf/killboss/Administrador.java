package com.knarf.killboss;

import java.util.Scanner;



public class Administrador {
	
	public boolean salir = true;
	public BaseDeDatos db = new BaseDeDatos();
	
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
			System.out.println("2 - Eliminar una pregunta de un nivel.");
			System.out.println("3 - Agregar una respuesta a un nivel.");
			System.out.println("4 - Eliminar una respuesta de un nivel.");
			System.out.println("5 - Agregar una posible respuesta a un nivel.");
			System.out.println("6 - Eliminar una posible respuesta de un nivel.");
			System.out.println("7 - Imprime todas las preguntas de un nivel.");
			System.out.println("8 - Imprime todas las respuestas de un nivel.");
			System.out.println("9 - Imprime todas las posibles respuestas de un nivel.");			
			System.out.println("10 - Salir.");
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
			case 6:  System.out.println("6 - Eliminar una posible respuesta de un nivel.");
					 break;
			case 7:  this.opcion7();
					 break;
			case 8:	 this.opcion8();
					 break;
			case 9:	 this.opcion9();					 
					 break;
			case 10: this.salir = false;
					 break;
			default: System.out.println("Ingrese una opción válida.");
			}			
		} 
		while (this.salir == true);		
	}
	
	
	/**
	 * Agrega una pregunta a un nivel
	 */
	@SuppressWarnings("resource")
	public void opcion1() {
		System.out.println("Ingrese el nivel en el cual desea agregar una pregunta:");
		Scanner op1 = new Scanner(System.in);
		int nivelx1 = op1.nextInt();
		System.out.println("Ingrese la pregunta que desea agregar al nivel " + nivelx1 + " :");
		Scanner op11 = new Scanner(System.in);
		String preguntax1 = op11.nextLine();
		Abrir a = new Abrir();
		this.db = a.abrir();
		this.db.addPregunta(nivelx1, preguntax1);		
		Guardar tmp = new Guardar();
		tmp.guardar(this.db);
	}
	
	
	/**
	 * Elimina una pregunta de un nivel
	 */
	@SuppressWarnings("resource")
	public void opcion2() {
		System.out.println("Ingrese el nivel en el cual desea eliminar la pregunta: ");
		Scanner op2 = new Scanner(System.in);
		int nivelx2 = op2.nextInt();
		System.out.println("Ingrese la posición de la pregunta que desea eliminar del nivel " + nivelx2 + " :");
		Scanner op22 = new Scanner(System.in);
		int pos2 = op22.nextInt();
		Abrir a = new Abrir();
		this.db = a.abrir();
		this.db.dropPregunta(nivelx2, pos2);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
	
	
	/**
	 * Agrega una respuesta a un nivel seleccionado 
	 */
	@SuppressWarnings("resource")
	public void opcion3() {
		System.out.println("Ingrese el nivel en el cual desea agregar la respuesta: ");
		Scanner op3 = new Scanner(System.in);
		int nivelx3 = op3.nextInt();
		System.out.println("Ingrese la respuesta que desea agregar al nivel " + nivelx3);
		Scanner op33 = new Scanner(System.in);
		String respuesta3 = op33.nextLine();
		Abrir a = new Abrir();
		this.db = a.abrir();
		this.db.addRespuesta(nivelx3, respuesta3);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
	
	
	/**
	 * Elimina una respuesta de un nivel seleccionado
	 */
	@SuppressWarnings("resource")
	public void opcion4() {
		System.out.println("Ingrese el nivel en el cual desea eliminar la respuesta: ");
		Scanner op4 = new Scanner(System.in);
		int nivelx4 = op4.nextInt();
		System.out.println("Ingrese la posición de la respuesta que desea eliminar: ");
		Scanner op44 = new Scanner(System.in);
		int pos4 = op44.nextInt();
		Abrir a = new Abrir();
		this.db = a.abrir();
		this.db.dropRespuesta(nivelx4, pos4);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
	
	
	/**
	 * Agrega una posible respuesta a un nivel seleccionado
	 */
	@SuppressWarnings("resource")
	public void opcion5() {
		System.out.println("Ingrese el nivel en el cual desea agregar la posible respuesta: ");
		Scanner op5 = new Scanner(System.in);
		int nivelx5 = op5.nextInt();
		System.out.println("Ingrese la posible respuesta que desea agregar al nivel " + nivelx5 + ": ");
		Scanner op55 = new Scanner(System.in);
		String posibleRespuesta = op55.nextLine();
		Abrir a = new Abrir();
		this.db = a.abrir();
		this.db.addPosibleRespuesta(nivelx5, posibleRespuesta);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
	
	/**
	 * Imprime todas las preguntas de un nivel seleccionado
	 */
	@SuppressWarnings("resource")
	public void opcion7() {
		System.out.println("ingrese el nivel");
		Scanner op7 = new Scanner(System.in);
		int nivelx7 = op7.nextInt();
		Abrir tmp = new Abrir();
		this.db = tmp.abrir();
		this.db.imprimePreguntas(nivelx7);
	}
	
	/**
	 * Imprime todas las respuestas de un nivel seleccionado
	 */
	@SuppressWarnings("resource")
	public void opcion8() {
		System.out.println("ingrese el nivel");
		Scanner op8 = new Scanner(System.in);
		int nivelx8 = op8.nextInt();
		Abrir tmp = new Abrir();
		this.db = tmp.abrir();
		this.db.imprimeRespuestas(nivelx8);
	}
	
	
	/**
	 * Imprime todas las posibles respuestas de un nivel seleccionado
	 */
	@SuppressWarnings("resource")
	public void opcion9() {
		System.out.println("ingrese el nivel");
		Scanner op9 = new Scanner(System.in);
		int nivelx9 = op9.nextInt();
		Abrir tmp = new Abrir();
		this.db = tmp.abrir();
		this.db.imprimePosiblesRespuestas(nivelx9);
	}

}