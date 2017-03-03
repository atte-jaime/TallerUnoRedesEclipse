package Eclipse;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Usuario {
	private PApplet app;
	private PVector posObjetivo, base, dir;
	private PImage[] img;
	private ArrayList<Bala> balas;

	public Usuario(PApplet app, PImage[] imagen) {
		this.app = app;
		this.img = imagen;
		balas = new ArrayList<Bala>();
		posObjetivo = new PVector(app.width / 2, (app.height / 2) - 200);
		dir = new PVector(app.width / 2, -app.height / 2);
	}

	public void pintar() {
		base = new PVector(app.width / 2, app.height - 50);
		float angulo = dir.heading() + PApplet.PI / 2;
		for (int i = 0; i < balas.size(); i++) {
			Bala b = balas.get(i);
			b.update();
			b.display();

			if (b.getX() > app.width || b.getX() < 0 || b.getY() < 0) {
				balas.remove(b);
				System.out.println("se eliminó");
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

	public void derecha() {
		if (posObjetivo.x <= app.width + 10) {
			posObjetivo.add(new PVector(20, 0));
			dir = PVector.sub(posObjetivo, base);
			dir.normalize();
			dir.mult(6);

		}
	}

	public void izquierda() {
		if (posObjetivo.x >= 10) {
			posObjetivo.sub(new PVector(20, 0));
			dir = PVector.sub(posObjetivo, base);
			dir.normalize();
			dir.mult(6);

		}
	}

	public void disparar() {
		if (balas.size() < 1) {
			Bala b = new Bala(app, img[1], base, dir);
			balas.add(b);
		}
	}

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
