package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import org.osoa.sca.annotations.Service;
import org.osoa.sca.annotations.Reference;


@Service(Runnable.class)
public class Manager implements Runnable{

    @Reference
    private Runnable server;

    @Override
    public void run() {
        server.run();       
    }
    
}
