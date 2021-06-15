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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import org.osoa.sca.annotations.Property;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;
import java.util.Arrays;


@Service(Runnable.class)
public class Control<T extends Comparable<? super T>> implements Runnable{

    @Property
	private long blockSize;
	private static GUI gui;
    @Reference(name="workers")
    private WorkerService server;
	private static double PI = 0;

    public Control(){
		
	}
    
	@Override
	public void run() {
		System.out.println("CLIENT: Started");
        try {
            if(gui == null) {
                gui = new GUI();
                gui.setLocationRelativeTo(null);
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gui.setVisible(true);
                configureEvents();
            }
        } catch (Exception e) {
            System.out.println("Se produjo una excepcion al configurar la interfaz grafica");
            e.printStackTrace();
        }
	}

    public void prueba(int seed, long puntos){

		long size = puntos;
        System.out.println("waiting for sorter ...");
		while (!server.isServer());
		System.out.println("... sorter connected");
		// Etapa 1: sorting
		long start = System.currentTimeMillis();
		ArrayList<WrapRunnable> runners=new ArrayList<WrapRunnable>();
        int threads=(int)Math.ceil(size/(blockSize*1.0));
		System.out.println("threads: "+threads);
		Random r = new Random(seed);
		ExecutorService executor = Executors.newFixedThreadPool(threads);
		while (threads-->0) {


            WrapRunnable runner=new WrapRunnable(server.pedirPuntos(),r.nextInt(),blockSize);
			/*try {
				Thread.sleep(1000);
				
			} catch (Exception e) {
				//TODO: handle exception
			}*/
            runners.add(runner);
	    	executor.execute(runner);
			

		}
        executor.shutdown();
		while (!executor.isTerminated()); 
		long puntosT = 0;
		for(int i = 0; i<runners.size();i++){

			puntosT += runners.get(i).getResult();

		}
        
		long find = (System.currentTimeMillis()-start);
		System.out.println("los valores son" + runners.get(0).getResult()+ "   " + puntosT);
		System.out.println("  " + find);
		//System.out.println("Tiempo total: "+(find/60)+" mn con "+(find%60)+" segundos");	
		double pi = recibirPuntos(puntos, puntosT);
		System.out.println("el valor pi es: " +  pi);
    }

	public double recibirPuntos(long puntosTotales, long puntosCirculo) {
		double pi = (4 * ((double) puntosCirculo) / (puntosTotales));
		PI = pi;
		return pi;
	}

	private void configureEvents() {
		gui.getBtncalcularPI().addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					final int seed = Integer.parseInt(gui.getTextFieldSemilla().getText().trim());
					long numPuntos = Long.parseLong(gui.getTextFieldNumPuntos().getText().trim());
					if(seed > 0&& numPuntos > 0) {
						System.out.println("----------------------------");
						System.out.println("seed es " + seed);
						System.out.println("npuntos es " + numPuntos);
						System.out.println("----------------------------");

                        prueba(seed, numPuntos);
					}
				}
			}
		);
		gui.getBtnElegirArchivo().addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					readCsv();
				}
			}
		);
	}

	public void readCsv() {
/*		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("csv/csv/.csv")));
		} catch (Exception e) {}
		BufferedReader br = null;
*/
      try {
		BufferedReader br =new BufferedReader(new FileReader("csv/input.csv"));
         String line = br.readLine();
		 double[] pis = new double[5];
		 String[] confs = new String[5];
		 int i = 0;
         while (null!=line) {
            String [] fields = line.split(";");
			String confName = fields[0];
			int seed = Integer.parseInt(fields[1]);
			long ptos = Long.parseLong(fields[2]);
			pis[i] = prueba(seed, puntos);
			confs[i] = confName;
            System.out.println(Arrays.toString(fields));
			i++;
            line = br.readLine();
         }
		 BufferedWriter bw =  new BufferedWriter(new FileWriter("csv/output.csv"));
		 for(int j = 0; j < 5; j++) {
			 bw.write(confs[i] + ";" + pis[i] + "\n");
		 }
      } catch (Exception e) {
         
      }
	}
}