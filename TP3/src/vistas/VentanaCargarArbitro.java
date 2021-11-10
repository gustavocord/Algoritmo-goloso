package vistas;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.Color;

public class VentanaCargarArbitro extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nombreArbitro;
	private JSpinner idArbitro;
	private JButton btnCargar, btnCancelar;
	
	public VentanaCargarArbitro() {
		setTitle("Cargar Arbitro");
		getContentPane().setForeground(new Color(250, 128, 114));
		setBounds(100, 100, 600, 250);
		getContentPane().setLayout(null);
		
		JLabel cartelNombre = new JLabel("Nombre:");
		cartelNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		cartelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		cartelNombre.setBounds(39, 30, 110, 50);
		getContentPane().add(cartelNombre);
		
		nombreArbitro = new JTextField();
		nombreArbitro.setBounds(167, 45, 385, 25);
		getContentPane().add(nombreArbitro);
		nombreArbitro.setColumns(10);
		
		idArbitro = new JSpinner();
		idArbitro.setModel(new SpinnerNumberModel(1, 1, null, 1));
		idArbitro.setBounds(167, 118, 385, 25);
		getContentPane().add(idArbitro);
		
		JLabel cartelID = new JLabel("ID Arbitro:");
		cartelID.setHorizontalAlignment(SwingConstants.CENTER);
		cartelID.setFont(new Font("Tahoma", Font.BOLD, 16));
		cartelID.setBounds(57, 103, 110, 50);
		getContentPane().add(cartelID);
		
		btnCargar = new JButton("Cargar");
		btnCargar.setBounds(210, 164, 110, 45);
		getContentPane().add(btnCargar);
		
		btnCancelar  = new JButton("Cancelar");
		btnCancelar.setBounds(380, 164, 110, 45);
		getContentPane().add(btnCancelar);
		this.setVisible(false);//inicializa invisible
	}
	public JTextField getNombreArbitro() {
		return nombreArbitro;
	}
	public JSpinner getIdArbitro() {
		return idArbitro;
	}
	public JButton getBtnCargar() {
		return btnCargar;
	}
	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void mostrar(boolean b) {
		this.setVisible(b);
	}
	
}
