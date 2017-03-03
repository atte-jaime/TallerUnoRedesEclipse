package Eclipse;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Esta clase define la interacci�n de toda la aplicaci�n
 * 
 * @author jaime
 * 
 */

public class Logica implements Observer {

	private PApplet app;
	private Usuario user;
	private LeerIp ipes;
	private PImage[] ca�onI;
	private PImage[] interfaz;
	private PImage[] animacion;
	private PImage pajaro;
	private PImage tiempo;

	private Comunicacion com;
	private int pantallas = 0;
	private int contador;
	private int puntaje;
	private ArrayList<Pajaro> pajaros;
	private Cronometro crono;
	private PFont fuente;
	private PImage finish;

	/**
	 * Constructor de la clase logica
	 * 
	 * @param app
	 *            Permite utilizar la librer�a de processing para dibujar
	 */
	public Logica(PApplet app) {
		this.app = app;
		cargarImagenes();

		// The font must be located in the sketch's
		// "data" directory to load successfully
		fuente = app.createFont("../Data/Fonts/CHANGA ONE REGULAR.TTF", 18);
		app.textFont(fuente, 18);

		crono = new Cronometro(app);
		crono.start();
		user = new Usuario(app, ca�onI);
		pajaros = new ArrayList<Pajaro>();
		ipes = new LeerIp();
		ipes.addObserver(this);
		new Thread(ipes).start();

		com = new Comunicacion();
		com.addObserver(this);
		new Thread(com).start();

	}

	/**
	 * M�todo que se encarga de recibir informaci�n de las clases que observa,
	 * en este caso, son la Comunicaci�n y LecturaIp Leer la informaci�n y
	 * determina cu�l es su funci�n en la aplicaci�n
	 * 
	 */
	public void update(Observable clase, Object obj) {

		// Si los datos que recibe son provenientes de la clase Comunicaci�n
		if (clase instanceof Comunicacion) {
			if (obj instanceof String) {
				String pos = (String) obj;
				if (pos.contains("right")) {
					if (pantallas < 2) {
						//Cambio de pantalla hacia adelante
						pantallas += 1;
						if (pantallas == 2) {
							// Comienza a correr el tiempo
							crono.setCorriendo(true);
						}
						System.out.println("Se cambi� de pantalla a: " + pantallas);
					} else {
						// Mueve la bala a la derecha

						user.derecha();
					}
				} else if (pos.contains("left")) {
					if (pantallas != 2 && pantallas >= 1 && pantallas != 3) {
						//Cambio de pantallas hacia atr�s
						pantallas -= 1;
						System.out.println("Se cambi� de pantalla a: " + pantallas);

					} else {
						// Mueve la bala a la izquierda
						user.izquierda();
					}
				}

				else if (pos.contains("shoot") && pantallas == 2) {
					// Disparar
					user.disparar();
				}

				else if (pos.contains("shoot") && pantallas == 3) {
					reset();
				}

				else
					System.out.println("El comando es err�neo :v");
			}
		}
		
		// Si los datos que recibe son provenientes de la clase LeerIp
		else if (clase instanceof LeerIp) {
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
	
	/**
	 * 
	 * M�todo que se encarga de ejecutar todo el c�digo
	 */
	public void ejecutar() {
		user.pintar();
		pantallas();
	}
	
	/**
	 * M�todo que se encarga de la distribuci�n y pintado de las pantallas
	 */
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
			for (int i = 0; i < pajaros.size(); i++) {
				Pajaro temp = pajaros.get(i);
				temp.mover();
				temp.pintar();

				int xTemp = temp.getPosX();
				int yTemp = temp.getPosY();
				if (user.choque(xTemp, yTemp)) {
					pajaros.remove(temp);
					puntaje += 1;
				}
			}
			user.pintar();
			tiempoYpuntaje();

			break;

		case 3:
			// PANTALLA DE FINALIZAR Y REINICIAR
			app.image(finish, 0, 0);
			app.textAlign(PApplet.CENTER, PApplet.CENTER);
			app.fill(255);
			app.textSize(40);
			app.text("x " + puntaje, (app.width / 2) + 70, app.height / 2);
			app.noFill();

			break;

		default:
			break;
		}
	}
	
	/**
	 * M�todo encargado de cargar TODAS las im�genes que se utilizan en la APP
	 */
	private void cargarImagenes() {
		interfaz = new PImage[2];
		ca�onI = new PImage[2];
		animacion = new PImage[300];
		pajaro = new PImage();
		tiempo = new PImage();
		finish = new PImage();

		for (int i = 0; i < animacion.length; i++) {
			animacion[i] = app.loadImage("../Data/Animacion/animInterfaz_" + i + ".png");
		}

		interfaz[0] = app.loadImage("../Data/Interfaz/inicio.png");
		interfaz[1] = app.loadImage("../Data/Interfaz/instrucciones.png");
		finish = app.loadImage("../Data/Interfaz/finish.png");

		ca�onI[0] = app.loadImage("../Data/Interaccion/ca�on.png");
		ca�onI[1] = app.loadImage("../Data/Interaccion/bala.png");

		pajaro = app.loadImage("../Data/Interaccion/pajaro.png");
		tiempo = app.loadImage("../Data/Interaccion/tiempoYpuntaje.png");
	}
	
	/**
	 * M�todo encargado de la animaci�n de fondo de la interacci�n
	 */
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
	
	/**
	 * Metodo encargado del c�lculo de tiempo y su pintado
	 * Adem�s, tambi�n determina las acciones cuando el tiempo llega a tal punto
	 */
	private void tiempoYpuntaje() {
		int min = crono.getMin();
		int sec = crono.getSec();

		if (min >= 1) {
			pantallas = 3;
			crono.setCorriendo(false);
		}
		app.image(tiempo, 0, 0);
		app.textAlign(PApplet.CENTER, PApplet.CENTER);
		app.fill(255);
		app.textSize(18);
		app.text("x " + puntaje, 82, 35);
		app.text(min + ":" + sec, 433, 35);
		app.noFill();

	}
	
	/**
	 * Metodo encargado de reiniciar todas las variables del juego
	 */
	private void reset() {
		pantallas = 0;
		pajaros.removeAll(pajaros);
		puntaje = 0;
		crono.setMillis(0);
		crono.setMin(0);
		crono.setSec(0);
	}

}
