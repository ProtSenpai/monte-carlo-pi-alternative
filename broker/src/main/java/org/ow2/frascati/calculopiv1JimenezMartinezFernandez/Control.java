package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.osoa.sca.annotations.Service;
import org.osoa.sca.annotations.Reference;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioComBroker;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.WorkerService;
import org.osoa.sca.annotations.Property;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


@Service(Runnable.class)
public class Control<T extends Comparable<? super T>> implements Runnable{

    @Property
	private long blockSize;

    @Reference(name="workers")
    private WorkerService server;

    public Control(){
		
	}

    
	@Override
	public void run() {

        Scanner scanner=new Scanner(System.in);
		System.out.println("Digite los puntos");
		long puntos=scanner.nextLong();
		System.out.println("Digite la semilla");
		int seed=scanner.nextInt();

		prueba(seed, puntos);

        
	}

    public void prueba(int seed, long puntos){

		long size = puntos;
        System.out.println("waiting for sorter ...");
		while (!server.isServer());
		System.out.println("... sorter connected");
		// Etapa 1: sorting
		long start = System.currentTimeMillis();
		long init=0;
	    long end= blockSize;
		ArrayList<WrapRunnable> runners=new ArrayList();
        int threads=(int)Math.ceil(size/(blockSize*1.0));
		System.out.println("threads: "+threads);
		Random r = new Random(seed);
		ExecutorService executor = Executors.newFixedThreadPool(threads);
		while (threads-->0) {

			if(end>size){
				end=size;
			}

            WrapRunnable runner=new WrapRunnable(server.pedirPuntos(),r.nextInt(2000),blockSize);
			try {
				Thread.sleep(1000);
				
			} catch (Exception e) {
				//TODO: handle exception
			}
            runners.add(runner);
	    	executor.execute(runner);
			init=end;
			end+=blockSize;

		}
        executor.shutdown();
		while (!executor.isTerminated()); 
		long puntosT = 0;
		for(int i = 0; i<runners.size();i++){

			puntosT += runners.get(i).getResult();

		}
        
		long find = (System.currentTimeMillis()-start)/1000;
		System.out.println("los valores son" + runners.get(0).getResult()+ "   " + puntosT);
		System.out.println("Tiempo total: "+(find/60)+" mn con "+(find%60)+" segundos");	
		double pi = recibirPuntos(puntos, puntosT);
		System.out.println("el valor pi es: " +  pi);
    }

	public double recibirPuntos(long puntosTotales, long puntosCirculo) {
		double pi = (4 * ((double) puntosCirculo) / (puntosTotales));

		return pi;
	}

}