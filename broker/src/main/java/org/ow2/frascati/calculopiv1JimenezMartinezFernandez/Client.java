package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.osoa.sca.annotations.Service;


@Service
public interface Client <T extends Comparable<? super T>> extends Remote {
    double recibirPuntos(long puntosTotales, long puntosCirculo, int semilla) throws RemoteException;
}
