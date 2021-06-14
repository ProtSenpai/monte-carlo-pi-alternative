package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;

import org.osoa.sca.annotations.Service;


@Service
public interface ServicioComBroker {
    long pedirPuntos(String uriCliente, int seed, long numPuntos);
    void attachServicioGenerarPuntos(String serverUri);
    void enviarPuntosACliente(long puntosRetorno);
    void detachServicioGenerarPuntos(String serverUri);
}
