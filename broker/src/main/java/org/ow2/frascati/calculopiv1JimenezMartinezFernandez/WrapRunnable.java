package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;


public class WrapRunnable implements Runnable {

    private Object service;
    private int seed;
    private long puntos;
   /* private Comparable[][] params;*/
    private long result;

    public WrapRunnable(Object service, int seed, long puntos){
        this.service=service;
        this.seed = seed;
        this.puntos = puntos;
    }

    @Override
    public void run() {
        try {
  
                result=((Server)service).generarPuntos(puntos, seed);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getResult() {
        return result;
    }

}