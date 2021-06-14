package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.util.Random;

import java.rmi.RemoteException;
import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;
import java.rmi.server.UnicastRemoteObject;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioComBroker;

@Service(Runnable.class)
public class ServerImp <T extends Comparable<? super T>> extends UnicastRemoteObject implements Server<T>, Runnable {

	@Property
	private String myServiceUri;

	@Reference(name = "suscriber")
	private ServicioComBroker servicioComBroker;

	public ServerImp() throws RemoteException{
		
	}

	public long generarPuntos(long numeros, int semilla) {

		Random r = new Random(semilla);
		long nPuntosDentroDelCirculo = 0;

		for (int i = 0; i < numeros; i++) {

			double x = r.nextDouble();
			double y = r.nextDouble();

			if ((x * x) + (y * y) <= 1) {

				nPuntosDentroDelCirculo++;
			}	
		}
		return nPuntosDentroDelCirculo;
	}

	public void run() {
		System.out.println("Me lleva la que me trajo");

		servicioComBroker.attachServicioGenerarPuntos(myServiceUri);
	}
}
