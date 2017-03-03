package Eclipse;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Clase Bala
 * @author jaime
 *
 */
public class Bala {

	private PApplet app;
	private PVector pos;
	private PVector vel;
	private PImage img;
	private int velMax = 6;
	
	/**
	 * Constructor de la clase
	 * @param app	para pintar las im�genes
	 * @param img	recibe imagen de la bala
	 * @param pos	recibe la posici�n inicial de la bala
	 * @param vel 	recibe la direcci�n en la cual sale disparada la bala
	 */
	public Bala(PApplet app, PImage img, PVector pos, PVector vel) {
		this.app = app;
		this.pos = pos;
		this.vel = vel;
		this.img = img;
	}
	
	/**
	 * M�todo encargado de realizar los c�lculos del movimiento del vector
	 */
	public void update() {
		vel.limit(velMax);
		pos.add(vel);
	}

	/**
	 * M�todo que dibuja la bala
	 */
	public void display() {
		app.imageMode(PApplet.CENTER);
		app.image(img, pos.x, pos.y);
		app.imageMode(PApplet.CORNER);
	}

	// M�TODOS DE RETORNO Y SETEO DE LAS VARIABLES
	
	public float getX() {
		return pos.x;
	}

	public float getY() {
		return pos.y;
	}
	
	public void setX(float x){
		pos.x=x;
	}
	
	public void setY(float y){
		pos.y=y;
	}
}
