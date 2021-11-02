package vistas;

import java.awt.Font;
import java.util.ArrayList;
 
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.MainController;

public class JPanelMostrarDatos extends JPanel {

	private DefaultTableModel model;


	private MainController controller = new MainController();
	
	public JPanelMostrarDatos() {
		
		JScrollPane scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(39, 123, 213, 141);
		scrollPaneTabla.setBackground(null);
		add(scrollPaneTabla);
		
		model = new DefaultTableModel();
		model.addColumn("Equipo");
		model.addColumn("Arbitro");

		JTable tabla = new JTable();
		tabla.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneTabla.setViewportView(tabla);
		tabla.setEnabled(false);
		tabla.setModel(model);
		
		cargarDatos(model); //LLandando a esta funcion se cargan los datos en una tabla con scroll, siempre y cuando exista la misma cantidad de equipos y arbitros
	}
	
	private void cargarDatos(DefaultTableModel model) {
		
		ArrayList<Integer> arbitros  = controller.getArbitros();
		ArrayList<String> equipos = controller.getEquipos();
		
		
		for(int i=0 ; i<arbitros.size() ; i++) {

			model.addRow(new String[] {String.valueOf(i+1), 
					String.valueOf(arbitros.get(i)),
					String.valueOf(equipos.get(i))});
		}
	}

}
