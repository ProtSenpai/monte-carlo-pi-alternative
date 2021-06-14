package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.osoa.sca.annotations.Service;


@Service
public interface ServicioGenerarPuntos <T extends Comparable<? super T>> extends Remote {
    long generarPuntos(long puntos, int semilla) throws RemoteException;
}
