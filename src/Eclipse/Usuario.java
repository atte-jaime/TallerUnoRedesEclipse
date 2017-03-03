package Eclipse;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Clase creada para el manejo del usuario en la aplicaci�n
 * @author jaime
 *
 */
public class Usuario {
	private PApplet app;
	private PVector posObjetivo, base, dir;
	private PImage[] img;
	private ArrayList<Bala> balas;

	/**
	 * Constructor de la clase
	 * @param app	PApplet para poder pintar la im�genes, etc.
	 * @param imagen arreglo de im�genes que recibe las im�genes del ca��n y la bala
	 */
	public Usuario(PApplet app, PImage[] imagen) {
		this.app = app;
		this.img = imagen;
		balas = new ArrayList<Bala>();
		posObjetivo = new PVector(app.width / 2, (app.height / 2) - 200);  // vector referencia de objetivo
		dir = new PVector(app.width / 2, -app.height / 2); // vector que determina la direcci�n del disparo
	}
	
	/**
	 * M�todo encargado de pintar el ca��n
	 */
	public void pintar() {
		base = new PVector(app.width / 2, app.height - 50); // vector referencia de base para disparar
		float angulo = dir.heading() + PApplet.PI / 2; // �gulo en el cual gira el ca�on
		for (int i = 0; i < balas.size(); i++) {
			Bala b = balas.get(i);
			b.update();
			b.display();

			if (b.getX() > app.width || b.getX() < 0 || b.getY() < 0) {
				balas.remove(b);
				System.out.println("se elimin�");
			}
		}
		
		app.pushMatrix();
		app.translate(base.x, base.y);
		app.rotate(angulo);
		app.imageMode(PApplet.CENTER);
		app.image(img[0], -10, -40);
		app.imageMode(PApplet.CORNER);
		app.popMatrix();
		app.fill(0);
		// REFERENCIA DE BASE
		//app.ellipse(base.x, base.y, 30, 30);
		// REFERENCIA DE TIRO
		//app.ellipse(posObjetivo.x, posObjetivo.y, 20, 20);
	}
	
	/**
	 * M�todo encargado del movimiento hacia la derecha del ca��n
	 */
	public void derecha() {
		if (posObjetivo.x <= app.width + 10) {
			posObjetivo.add(new PVector(20, 0));
			dir = PVector.sub(posObjetivo, base);
			dir.normalize();
			dir.mult(6);

		}
	}
	
	/**
	 * M�todo encargado del movimiento hacia la izquierda del ca��n
	 */
	public void izquierda() {
		if (posObjetivo.x >= 10) {
			posObjetivo.sub(new PVector(20, 0));
			dir = PVector.sub(posObjetivo, base);
			dir.normalize();
			dir.mult(6);

		}
	}
	
	/**
	 * M�todo encargado del disparo
	 */
	public void disparar() {
		if (balas.size() < 1) {
			Bala b = new Bala(app, img[1], base, dir);
			balas.add(b);
		}
	}

	/**
	 * M�todo que determina la colisi�n entre la bala y el objetivo
	 * @param x posici�n en x del objetivo
	 * @param y posici�n en y del objetivo
	 * @return	retorna verdadero si los objetos colisionan, de lo contrario, retorna falso
	 */
	public boolean choque(int x, int y) {
		boolean temp = false;

		for (int i = 0; i< balas.size(); i++) {
			Bala bala = balas.get(i);
			if (PApplet.dist(x, y, bala.getX(), bala.getY()) < 50) {
				balas.remove(bala);
				temp = true;
			} else
				temp = false;
		}

		return temp;

	}

}
