package Eclipse;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Clase encargada de la lectura de ip's conectadas a una misma red
 * @author jaime
 *
 */
public class LeerIp extends Observable implements Runnable {

	private InetAddress ip;
	private String ipTemp;
	private ArrayList<String> direcciones;
	
	/**
	 * Constructor de la clase
	 */
	public LeerIp() {

		try {
			ip = InetAddress.getLocalHost();
			ipTemp = ip.getHostAddress();
			ipTemp = ipTemp.substring(0, ipTemp.lastIndexOf("."));
			direcciones = new ArrayList<String>();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Método encargado de la lectura de ip' para determinar si es accesible o no
	 * Además, notifica a la lógica cuando encuentra una ip nueva que es Reachable y envía la ip por medio de un string
	 */
	public void run() {
		boolean nuevo = false;

		while (true) {
			for (int i = 2; i < 55; i++) {
				String host = ipTemp + "." + i;

				try {
					InetAddress adress2 = InetAddress.getByName(host);
					if (adress2.isReachable(350)) {
						System.out.println(adress2 + " is Reachable");

						nuevo = true;

						for (int j = 0; j < direcciones.size(); j++) {
							if (i == j) {
								nuevo = false;
							}
						}

						if (nuevo) {
							direcciones.add(new String());
							setChanged();
							notifyObservers(host);
							clearChanged();
							System.out.println("Funciona");
						}
					} else {
						System.out.println(host + " is NOT REACHABLE");
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

}
