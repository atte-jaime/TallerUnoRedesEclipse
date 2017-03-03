package Eclipse;

import processing.core.PApplet;

/**
 * Clase principal para la ejecuci�n de la aplicaci�n
 * @author jaime
 *
 */
public class Main extends PApplet{

	public static void main(String[] args) {
		PApplet.main("Eclipse.Main");
	}
	
	public void settings() {
		size(500,700);
	}
	
	Logica log;
	
	public void setup() {
		log = new Logica(this);
	}
	
	public void draw() {
		background(255);
		log.ejecutar();
	}
	
	
	

}
