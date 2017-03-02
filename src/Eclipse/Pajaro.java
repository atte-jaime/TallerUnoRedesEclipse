package Eclipse;

import processing.core.PApplet;
import processing.core.PImage;

public class Pajaro {
	private PApplet app;
	private PImage img;
	private int posX, posY;
	private int vel;
	
	public Pajaro(PApplet app, PImage img) {
		this.app=app;
		this.img=img;
		vel = (int) app.random(2,4);
		posX = (int) app.random(-50,-10);
		posY = (int) app.random(20,(app.height/2)+100);
	}
	
	public void pintar(){
		app.imageMode(PApplet.CENTER);
		app.image(img, posX, posY);
		app.imageMode(PApplet.CORNER);

	}
	
	public void mover(){
		posX += vel;
		
		if (posX>app.width+40) {
			posX=-40;
		}
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}



}
