package org.ow2.frascati.calculopiv1JimenezMartinezFernandez;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.UUID;


public class InterfazGrafica extends JFrame {

	private JPanel contentPane;
	private JTextField semillaTextField, numPuntosTextField,
			numNodosProcesamientoTextField;
	private JButton calcularPIButton, elegirArchivoCSVButton;

	/**
	 * Create the frame.
	 */
	public InterfazGrafica() {
		setTitle("Calcular PI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel seedLabel = new JLabel("Semilla");
		seedLabel.setBounds(10, 20, 249, 14);
		contentPane.add(seedLabel);

		semillaTextField = new JTextField();
		semillaTextField.setBounds(280, 20, 62, 20);
		contentPane.add(semillaTextField);
		semillaTextField.setColumns(10);

		JLabel numPuntosLabel = new JLabel("Numero de puntos");
		numPuntosLabel.setBounds(10, 50, 249, 14);
		contentPane.add(numPuntosLabel);

		numPuntosTextField = new JTextField();
		numPuntosTextField.setBounds(280, 50, 62, 20);
		contentPane.add(numPuntosTextField);
		numPuntosTextField.setColumns(10);

        JLabel numPuntosLabelX = new JLabel("Numero de nodos de procesamiento");
		numPuntosLabelX.setBounds(10, 80, 249, 14);
		contentPane.add(numPuntosLabelX);

		numNodosProcesamientoTextField = new JTextField();
		numNodosProcesamientoTextField.setBounds(280, 80, 62, 20);
		contentPane.add(numNodosProcesamientoTextField);
		numNodosProcesamientoTextField.setColumns(10);

		calcularPIButton = new JButton("Enviar parametros");
		calcularPIButton.setBounds(10, 110, 160, 23);
		contentPane.add(calcularPIButton);

		elegirArchivoCSVButton = new JButton("Elegir archivo");
		elegirArchivoCSVButton.setBounds(180, 110, 160, 23);
		contentPane.add(elegirArchivoCSVButton);
	}

	public JTextField getTextFieldSemilla() {
		return semillaTextField;
	}

	public JTextField getTextFieldNumPuntos() {
		return numPuntosTextField;
	}

	public JTextField getTextFieldNumNodosProcesamiento() {
		return numNodosProcesamientoTextField;
	}

	public JButton getBtncalcularPI() {
		return calcularPIButton;
	}

	public JButton getBtnElegirArchivo() {
		return elegirArchivoCSVButton;
	}
}