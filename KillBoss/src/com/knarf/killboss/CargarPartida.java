package com.knarf.killboss;

import java.util.Scanner;

public class CargarPartida {
	
	final KillBoss juego;
	public boolean salir = true;
	public BaseDeDatos db = new BaseDeDatos(this.juego);	
	
	public CargarPartida(final KillBoss juego) {
		this.juego = juego;		
	}
	
	@SuppressWarnings("resource")
	public void inicio() {
		do {			
			System.out.println("");
			System.out.println("****************************************");
			System.out.println("*	  		CARGAR PARTIDA  	       *");
			System.out.println("****************************************");
			System.out.println("");
			System.out.println("Ingrese el número de la opción deseada:");
			System.out.println("");
			System.out.println("1 - Cargar partida.");
			System.out.println("2 - Imprimir partidas.");
			System.out.println("3 - Borrar partidas.");
			System.out.println("4 - Salir.");
			System.out.println("");
			
			Scanner op = new Scanner(System.in);
			int num = op.nextInt();
			switch (num) {
			case 1:  this.opcion1();
					 break;			
			case 2:	 this.opcion2();
					 break;
			case 3:	 this.opcion3();
					 break;
			case 4: this.salir = false;
					 break;
			default: System.out.println("Ingrese una opción válida.");
			}			
		} 
		while (this.salir == true);		
	}
	
	/**
	 * Cargar una partida.
	 */
	@SuppressWarnings("resource")
	public void opcion1() {
		System.out.println("Ingrese el nombre del jugador: ");
		Scanner op = new Scanner(System.in);
		String nombre = op.nextLine();
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.cargarPartida(nombre);
		this.salir = false;
	}
	
	/**
	 * Imprime las partidas.
	 */
	public void opcion2() {
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.imprimirPartidas();
	}
	
	/**
	 * Borra todas las partidas guardadas.
	 */
	public void opcion3() {
		Abrir a = new Abrir(this.juego);
		this.db = a.abrir();
		this.db.borrarPartidas();
		Guardar tmp = new Guardar();
		tmp.guardar(this.db);
	}
		
}


















