package Eclipse;

import processing.core.PApplet;
/**
 * Clase encargada del manejo del tiempo que hereda de Thread
 * @author jaime
 *
 */
public class Cronometro extends Thread {
	private PApplet app;
	private int millis;
	private int sec;
	private int min;
	private boolean corriendo;
	
	/**
	 * Constructor de la clase
	 * @param app 
	 */
	public Cronometro(PApplet app) {
		this.app = app;
	}
	
	/**
	 * Método utilizado para calcular el tiempo
	 */
	public void run() {

		while (true) {
			if (corriendo) {

				millis++;

				if (millis / 1000 == 1) {
					sec += 1;
					millis = 0;
				} else

				if (sec / 60 == 1) {
					min += 1;
					sec = 0;
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	//MÉTODOS DE REOTRNO Y SETEO DE LAS VARIABLES
	
	public int getMillis() {
		return millis;
	}

	public int getSec() {
		return sec;
	}

	public int getMin() {
		return min;
	}

	public boolean isCorriendo() {
		return corriendo;
	}

	public void setMillis(int millis) {
		this.millis = millis;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setCorriendo(boolean corriendo) {
		this.corriendo = corriendo;
	}

}