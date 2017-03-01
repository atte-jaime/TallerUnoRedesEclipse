package Eclipse;

import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica implements Observer {

	private PApplet app;
	private Usuario user;
	private PImage[] cañonI;
	private PImage[] interfaz;
	private PImage[] animacion;
	private Comunicacion com;
	private int pantallas = 0;
	private int contador;
	public Logica(PApplet app) {
		this.app = app;
		com = new Comunicacion();
		com.addObserver(this);
		new Thread(com).start();
		cargarImagenes();

		user = new Usuario(app, cañonI);
	}

	public void update(Observable arg0, Object obj) {
		if (obj instanceof String) {
			String pos = (String) obj;
			if (pos.contains("right")) {
				if (pantallas != 2) {
					pantallas += 1;
					System.out.println("Se cambió de pantalla a: " + pantallas);
				} else {
					// Mueve la bala a la derecha

					user.derecha();
				}
			} else if (pos.contains("left")) {
				if (pantallas != 2 && pantallas >= 1) {
					pantallas -= 1;
					System.out.println("Se cambió de pantalla a: " + pantallas);

				} else {
					// Mueve la bala a la izquierda
					user.izquierda();
				}
			}

			else if (pos.contains("shoot") && pantallas == 2) {
				// Disparar
				user.disparar();
			}

			else
				System.out.println("El comando es erróneo :v");
		}
	}

	public void ejecutar() {
		user.pintar();
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
			animacionJuego();
			user.pintar();
			break;

		default:
			break;
		}
	}

	private void cargarImagenes() {
		interfaz = new PImage[2];
		cañonI = new PImage[2];
		animacion = new PImage[300];
		
		for (int i = 0; i < animacion.length; i++) {
			animacion[i] = app.loadImage("../Data/Animacion/animInterfaz_"+i+".png");
		}

		interfaz[0] = app.loadImage("../Data/Interfaz/inicio.png");
		interfaz[1] = app.loadImage("../Data/Interfaz/instrucciones.png");

		cañonI[0] = app.loadImage("../Data/Interaccion/cañon.png");
		cañonI[1] = app.loadImage("../Data/Interaccion/bala.png");
		
		
	}

	private void animacionJuego() {
		
		if (contador==300) {
			contador=0;
		}
		app.image(animacion[contador], 0, 0);
		if (app.frameCount%3==0) {
			contador ++;
		}
		
		app.fill(255,40);
		app.noStroke();
		app.rect(0, 0, app.width, app.height);
		app.noFill();
	}

}
