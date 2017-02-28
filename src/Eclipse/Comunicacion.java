package Eclipse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable {

	private final int PORT = 6000;
	private DatagramSocket s;
	private String command = "";

	public Comunicacion() {

		try {
			s = new DatagramSocket(PORT);
			System.out.println("Socket abierto y esperando");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		while (true) {
			DatagramPacket p = recibir();
			if (p != null) {
				String mensaje = new String(p.getData(), 0, p.getLength());
				command = mensaje;
				System.out.println("Se recibió la siguiente información: " + command);
				setChanged();
				notifyObservers(command);
				clearChanged();
			} else
				System.out.println("No se ha recibido información");
		}

	}

	public String getCommand() {
		return this.command;
	}

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
