package Eclipse;

import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica implements Observer {

	private PApplet app;
	private Bala bala;
	private PImage[] cañonI;
	private PImage[] interfaz;
	private Comunicacion com;
	private int pantallas;

	public Logica(PApplet app) {
		this.app = app;
		com = new Comunicacion();
		com.addObserver(this);
		new Thread(com).start();
		interfaz = new PImage[2];
		interfaz[0] = app.loadImage("../Data/inicio.png");
		interfaz[1] = app.loadImage("../Data/instrucciones.png");
		cañonI = new PImage[2];
		// cañonI[0] = app.loadImage("cañon.png");
		// cañonI[1] = app.loadImage("bola.png");
		bala = new Bala(app, cañonI);
	}

	public void update(Observable arg0, Object obj) {
		if (obj instanceof String) {
			String pos = (String) obj;
			if (pos.contains("right")) {
				if (pantallas != 3 ) {
					pantallas += 1;
					System.out.println("Se cambió de pantalla a: " + pantallas);
				} else {
					// Mueve la bala a la derecha
				}
			} else if (pos.contains("left")) {
				if (pantallas != 3 && pantallas>=1) {
					pantallas -= 1;
					System.out.println("Se cambió de pantalla a: " + pantallas);

				} else {
					// Mueve la bala a la izquierda
				}
			}

			else if (pos.contains("shoot") && pantallas == 3) {
				// Disparar
			}

			else
				System.out.println("El comando es erróneo :v");
		}
	}

	public void ejecutar() {
		bala.mover();
		pantallas();
	}

	private void pantallas() {
		switch (pantallas) {
		case 0:
			app.image(interfaz[0], 0, 0);
			break;
		case 1:
			app.image(interfaz[1], 0, 0);
			break;
		case 2:
			app.background(0, 100, 100);
			break;
		case 3:
			app.background(200, 200, 222);
			break;

		default:
			break;
		}
	}

}
