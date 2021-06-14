package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioCalcularPI;
import org.ow2.frascati.calculopiv1JimenezMartinezFernandez.services.ServicioComBroker;


@Scope("COMPOSITE")
public class Cliente implements Runnable, ServicioCalcularPI {

	@Reference
	private ServicioComBroker servicioComBroker;

	private InterfazGrafica interfazGrafica;

	public double recibirPuntos(long puntosTotales, long puntosCirculo, int semilla) {
		double pi = (4 * ((double) puntosCirculo) / (puntosTotales));

		return pi;
	}

	// --------------------------------------------------------------------------
	// Implementation of the Runnable interface
	// --------------------------------------------------------------------------
	public final void run() {
		// configurar la interfaz grafica
		try {
			interfazGrafica = new InterfazGrafica();
			interfazGrafica.setLocationRelativeTo(null);
			interfazGrafica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			interfazGrafica.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		configurarEventos();
	}

	private void configurarEventos() {
		interfazGrafica.getBtncalcularPI().addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int seed = Integer.parseInt(interfazGrafica.getTextFieldSemilla().getText().trim());
					int numNodos = Integer.parseInt(interfazGrafica.getTextFieldNumNodosProcesamiento().getText().trim());
					long numPuntos = Long.parseLong(interfazGrafica.getTextFieldNumPuntos().getText().trim());
					if(seed > 0 && numNodos > 0 && numPuntos > 0) {
						// TODO llamar al metodo que entrega los puntos y cuando los entregue mandar ese arreglo al metodo que calcula pi aqui (debe ser modificado para recibir los puntos)
						// Y mostrar el resultado
						System.out.println("----------------------------");
						System.out.println("seed es " + seed);
						System.out.println("npuntos es " + numPuntos);
						System.out.println("nnodos es " + numNodos);
						System.out.println("----------------------------");

						servicioComBroker.pedirPuntos(seed, numPuntos, numNodos);
					}
				}
				
			}
		);
	}

	public void updateNodosProcesamiento(int nnodos) {
		System.out.println("Ya no mas");
	}
}
