package Eclipse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;

/**
 * Clase encargada de la comunicaci�n entre el dispositivo (celular) y la APP
 * @author jaime
 *
 */
public class Comunicacion extends Observable implements Runnable {

	private final int PORT = 6000;
	private DatagramSocket s;
	private String command = "";

	/**
	 * Contructor de la clase
	 */
	public Comunicacion() {

		try {
			//SE CREA INICIALIZA EL SOCKET
			s = new DatagramSocket(PORT);
			System.out.println("Socket abierto y esperando");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * M�todo encargado de recibir a informaci�n desde el celular, y mandarla a la L�gica
	 */
	public void run() {
		while (true) {
			//SE RECIBE EL PAQUETE
			DatagramPacket p = recibir();
			if (p != null) {
				String mensaje = new String(p.getData(), 0, p.getLength());
				command = mensaje;
				System.out.println("Se recibi� la siguiente informaci�n: " + command);
				
				// SE NOTIFICA A LA L�GICA QUE HUBO UN CAMBIO Y ENVIA LOS DATOS RECIBIDOS
				setChanged();
				notifyObservers(command);
				clearChanged();
			} else
				System.out.println("No se ha recibido informaci�n");
		}

	}	
	
	/**
	 * Clase encargada de recibir paquetes de datos
	 * @return	retorna un un paquete de datos si recibe algo, de lo contrario, retorna null
	 */
	private DatagramPacket recibir() {

		try {
			byte[] paquete = new byte[1024];
			DatagramPacket p = new DatagramPacket(paquete, paquete.length);
			s.receive(p);
			return p;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
