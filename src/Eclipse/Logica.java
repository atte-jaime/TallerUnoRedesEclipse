package Eclipse;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica implements Observer {

	private PApplet app;
	private Usuario user;
	private LeerIp ipes;
	private PImage[] cañonI;
	private PImage[] interfaz;
	private PImage[] animacion;
	private PImage pajaro;
	private Comunicacion com;
	private int pantallas = 2;
	private int contador;
	private ArrayList<Pajaro> pajaros;

	public Logica(PApplet app) {
		this.app = app;
		cargarImagenes();

		user = new Usuario(app, cañonI);
		pajaros = new ArrayList<Pajaro>();
		ipes = new LeerIp();
		ipes.addObserver(this);
		new Thread(ipes).start();

		com = new Comunicacion();
		com.addObserver(this);
		new Thread(com).start();

	}

	public void update(Observable clase, Object obj) {
		if (clase instanceof Comunicacion) {
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
		} else if (clase instanceof LeerIp) {
			// OBTENER IP'S Y CREAR ENEMIGOS :v
			String hostAdress = (String) obj;
			try {
				InetAddress ipEnvio = InetAddress.getByName(hostAdress);
				Pajaro p = new Pajaro(app, pajaro);
				pajaros.add(p);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			for (int i = 0; i < pajaros.size(); i++) {
				Pajaro temp = pajaros.get(i);
				temp.mover();
				temp.pintar();

				int xTemp = temp.getPosX();
				int yTemp = temp.getPosY();
				if (user.choque(xTemp, yTemp)) {
					pajaros.remove(temp);
				}
			}
			break;

		default:
			break;
		}
	}

	private void cargarImagenes() {
		interfaz = new PImage[2];
		cañonI = new PImage[2];
		animacion = new PImage[300];
		pajaro = new PImage();

		for (int i = 0; i < animacion.length; i++) {
			animacion[i] = app.loadImage("../Data/Animacion/animInterfaz_" + i + ".png");
		}

		interfaz[0] = app.loadImage("../Data/Interfaz/inicio.png");
		interfaz[1] = app.loadImage("../Data/Interfaz/instrucciones.png");

		cañonI[0] = app.loadImage("../Data/Interaccion/cañon.png");
		cañonI[1] = app.loadImage("../Data/Interaccion/bala.png");

		pajaro = app.loadImage("../Data/Interaccion/pajaro.png");
	}

	private void animacionJuego() {

		if (contador == 300) {
			contador = 0;
		}
		app.image(animacion[contador], 0, 0);
		if (app.frameCount % 3 == 0) {
			contador++;
		}

		app.fill(255, 40);
		app.noStroke();
		app.rect(0, 0, app.width, app.height);
		app.noFill();
	}

	private void tiempo() {
		 
	}

}
