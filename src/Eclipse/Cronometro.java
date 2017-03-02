package Eclipse;

import processing.core.PApplet;

public class Cronometro {
	
	/*
	 * CÛdigo proporcionado y creado por Camilo Montoya
	 */
	
	
	// Objeto para contabilizar tiempo y que no se vea afectado por un	frame rate
	// Declaraci√≥n de las variables que ejecutaran el tiempo
	int comenzar = 0, parar = 0;
	boolean reproducir = false;
	
	PApplet app;
	public Cronometro(PApplet app){
		this.app=app;
	}

	// Si el reloj comienza
	public void empezar() {
		comenzar = app.millis();
		reproducir = true;
	}

	// Si el reloj se detiene
	public void detener() {
		parar = app.millis();
		reproducir = false;
	}

	// Reproducira el tiempo que se este pasando
	public int timepoReproducido() {
		int tiempo;
		if (reproducir) {
			tiempo = (app.millis() - comenzar);
		} else {
			tiempo = (parar - comenzar);
		}
		return tiempo;
	}

	// Retorna los segundos reproducidos
	public int second() {
		return (timepoReproducido() / 1000) % 60;
	}

	// Retorna los minutos reproducidos
	public int minute() {
		return (timepoReproducido() / (1000 * 60)) % 60;
	}
}

