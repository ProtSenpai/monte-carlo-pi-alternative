package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.osoa.sca.annotations.Service;

@Service
public interface Server <T extends Comparable<? super T>>extends Remote {


	public long generarPuntos(long numeros, int semilla) throws RemoteException;

}
