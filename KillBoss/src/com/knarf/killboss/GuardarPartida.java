package com.knarf.killboss;

import java.util.Scanner;

public class GuardarPartida {
	
	final KillBoss juego;
	public boolean salir = true;
	public BaseDeDatos db = new BaseDeDatos(this.juego);
	public int puntaje = 0;
	public int nivel = 0;
	
	public GuardarPartida(final KillBoss juego, int puntaje, int nivel) {
		this.juego = juego;
		this.puntaje = puntaje;
		this.nivel = nivel;
	}
	
	@SuppressWarnings("resource")
	public void inicio() {
		do {			
			System.out.println("");
			System.out.println("****************************************");
			System.out.println("*	  		GUARDAR PARTIDA 	       *");
			System.out.println("****************************************");
			System.out.println("");
			System.out.println("Ingrese el número de la opción deseada:");
			System.out.println("");
			System.out.println("1 - Guardar partida.");					
			System.out.println("2 - Salir.");
			System.out.println("");
			
			Scanner op = new Scanner(System.in);
			int num = op.nextInt();
			switch (num) {
			case 1:  this.opcion1();
					 break;						
			case 2: this.salir = false;
					 break;
			default: System.out.println("Ingrese una opción válida.");
			}			
		} 
		while (this.salir == true);		
	}
	
	/**
	 * Guarda una partida
	 */
	@SuppressWarnings("resource")
	public void opcion1() {
		System.out.println("Ingrese el nombre del jugador: ");
		Scanner op = new Scanner(System.in);
		String nombre = op.nextLine();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.guardarPartida(nombre, this.puntaje, this.nivel);
		Guardar g = new Guardar();
		g.guardar(this.db);
	}
		
}