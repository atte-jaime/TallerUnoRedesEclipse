package Eclipse;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Clase encargada de la visualización de las ip' por medio de pájaros
 * @author jaime
 *
 */
public class Pajaro {
	private PApplet app;
	private PImage img;
	private int posX, posY;
	private int vel;
	/**
	 * Constructor de la clase
	 * @param app	para pintar
	 * @param img	imagen del pájaro
	 */
	public Pajaro(PApplet app, PImage img) {
		this.app = app;
		this.img = img;
		vel = (int) app.random(2, 4);
		posX = (int) app.random(-50, -10);
		posY = (int) app.random(20, (app.height / 2) + 100);
	}
	
	/**
	 * Método encargado de pintar al pájaro
	 */
	public void pintar() {
		app.imageMode(PApplet.CENTER);
		app.image(img, posX, posY);
		app.imageMode(PApplet.CORNER);

	}

	/**
	 * Método encargado de mover el pájaro
	 * Si el pájaro se sale del lienzo, lo setea en una nueva posición
	 */
	public void mover() {
		posX += vel;

		if (posX > app.width + 40) {
			posX = -40;
			cambioY();
		}
	}
	
	
	/**
	 * Método que se encarga de cambiar la posición en Y del pájaro
	 * cada vez que se sale del lienzo 
	 */
	private void cambioY() {
		posY = (int) app.random(20, (app.height / 2) + 100);
	}

	// MÉTODOS DE RETORNO DE LAS VARIABLES
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

}
