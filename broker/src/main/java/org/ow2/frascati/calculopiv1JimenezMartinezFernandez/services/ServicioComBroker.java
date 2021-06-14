package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;

import org.osoa.sca.annotations.Service;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.Server;


@Service
public interface ServicioComBroker {
    void attachServicioGenerarPuntos(String serverUri);
    void detachServicioGenerarPuntos(String serverUri);
    void attachClient(String serverUri);
    void detachClient(String serverUri);
}
