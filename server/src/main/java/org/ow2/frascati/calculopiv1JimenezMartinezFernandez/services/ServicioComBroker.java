package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;

import org.osoa.sca.annotations.Service;


@Service
public interface ServicioComBroker {
    void attachServicioGenerarPuntos(String serverUri);
    void enviarPuntosACliente(long puntosRetorno);
    void detachServicioGenerarPuntos(String serverUri);
}
