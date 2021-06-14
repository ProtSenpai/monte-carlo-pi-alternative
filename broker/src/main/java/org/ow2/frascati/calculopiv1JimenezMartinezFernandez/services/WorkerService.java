package org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services;


import org.osoa.sca.annotations.Service;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.Server;


@Service
public interface WorkerService {

    Server pedirPuntos();
    
    boolean isServer();
    boolean isClient();
    int getServers();
    int getClients();

    
}