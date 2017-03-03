package Eclipse;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Clase encargada de la visualizaci�n de las ip' por medio de p�jaros
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
	 * @param img	imagen del p�jaro
	 */
	public Pajaro(PApplet app, PImage img) {
		this.app = app;
		this.img = img;
		vel = (int) app.random(2, 4);
		posX = (int) app.random(-50, -10);
		posY = (int) app.random(20, (app.height / 2) + 100);
	}
	
	/**
	 * M�todo encargado de pintar al p�jaro
	 */
	public void pintar() {
		app.imageMode(PApplet.CENTER);
		app.image(img, posX, posY);
		app.imageMode(PApplet.CORNER);

	}

	/**
	 * M�todo encargado de mover el p�jaro
	 * Si el p�jaro se sale del lienzo, lo setea en una nueva posici�n
	 */
	public void mover() {
		posX += vel;

		if (posX > app.width + 40) {
			posX = -40;
			cambioY();
		}
	}
	
	
	/**
	 * M�todo que se encarga de cambiar la posici�n en Y del p�jaro
	 * cada vez que se sale del lienzo 
	 */
	private void cambioY() {
		posY = (int) app.random(20, (app.height / 2) + 100);
	}

	// M�TODOS DE RETORNO DE LAS VARIABLES
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

}
