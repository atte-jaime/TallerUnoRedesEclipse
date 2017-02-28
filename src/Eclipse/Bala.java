package Eclipse;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Bala {
	private PApplet app;
	private PVector pos;
	private PImage[] img;
	private String comando = "";

	public Bala(PApplet app, PImage[] imagen) {
		this.app = app;
		this.img = imagen;
		pos = new PVector(0, 0);
	}

	public void mover() {
		switch (comando) {
		case "shoot":
			// DISPARAR
			break;

		case "right":
			// MOVER DERECHA
			break;

		case "left":
			// MOVER DERECHA
			break;

		default:
			if (!comando.equals("")) {
				System.out.println(comando + " is not a valid command");
			}
			break;
		}
	}

	public void pintar() {
		app.image(img[0], app.width / 2, (app.height / 2) + 200);
	}

	public String getComando() {
		return comando;
	}

	public float getPosX() {
		return pos.x;
	}

	public float getPosY() {
		return pos.y;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

}
