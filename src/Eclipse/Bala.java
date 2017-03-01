package Eclipse;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Bala {

	private PApplet app;
	private PVector pos;
	private PVector vel;
	private PImage img;
	private int velMax = 6;

	public Bala(PApplet app, PImage img, PVector pos, PVector vel) {
		this.app = app;
		this.pos = pos;
		this.vel = vel;
		this.img = img;
	}

	public void update() {
		vel.limit(velMax);
		pos.add(vel);
	}

	public void display() {
		app.imageMode(PApplet.CENTER);
		app.image(img, pos.x, pos.y);
		app.imageMode(PApplet.CORNER);
	}

	public float getX() {
		return pos.x;
	}

	public float getY() {
		return pos.y;
	}
}
