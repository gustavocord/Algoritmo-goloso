package vistas;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import logica.Partido;

@SuppressWarnings("serial")
public class FechaPanel extends JPanel {
	private JTable _tabla;
	private JLabel _cartelFecha;
	private final Color _COLOR_CARTEL_FECHA= new Color (223,149,233);
	private final Color _COLOR_BORDE_CARTEL_FECHA=new Color (32,150,216);
	private final Color _CABECERA_COLOR= new Color(32,150,205);
	private JScrollPane _scroll;

	public FechaPanel(String fecha) {
		this.setLayout(null);
		
		this._tabla = new JTable();
		this.setBounds(0, 0, 785, 470);
		propiedadesATabla();
		
		this._scroll = new JScrollPane(_tabla); //creo un scroll pane y asocio a la tabla con el scroll
		this._scroll.setBounds(0, 42, 785, 428);//las dimensiones del area donde se encuentra el scroll
		this.add(_scroll);
		
		
		this._cartelFecha = new JLabel("Fecha: " + fecha);
		propiedadesDeCartel();
		add(_cartelFecha);
		

	}
	public void cargarFechaEnTabla(ArrayList<Partido> partidos) {
		String [][] matrizDePartidos = new String[partidos.size()][3]; //cada elem va a tener 3 datos
		for(int i= 0; i<matrizDePartidos.length;i++) {
			Partido partido= partidos.get(i);
			if(partido.getArbitro()!=null) {
				matrizDePartidos[i] = new String[] { partido.getEquipoLocal().getNombre(), partido.getEquipoVisitante().getNombre(), partido.getArbitro().getNombre()};
			}
			else {
				matrizDePartidos[i] = new String[] { partido.getEquipoLocal().getNombre(), partido.getEquipoVisitante().getNombre(), null};
			}
			
		}
		_tabla.setModel(new DefaultTableModel(
				matrizDePartidos,
				new String[] {
					"Equipo Local", "Equipo Visitante", "Arbitro"
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class
				};
				
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
		propiedadesATabla();

		
	}
	private void propiedadesATabla() {
		this._tabla.setFillsViewportHeight(true);
		this._tabla.setBounds(0, 42, 785, 428);
		this._tabla.setRowHeight(50);//alto de las filas
		
		
		/*Alinear tabla */
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i= 0; i< _tabla.getColumnCount() ;i++ ) { 
			_tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}
		
		/*para que no se puedan seleccionar las celdas*/
		this._tabla.setCellSelectionEnabled(false);
		/* propiedades de diseño*/
		this._tabla.setForeground(new Color(0,0,0));
		this._tabla.setFont(new Font("Tahoma", Font.BOLD, 16));
		this._tabla.setBackground(new Color(248, 239, 190));
		propiedadesDeCabecera();

	}
	private void propiedadesDeCabecera() {
		this._tabla.getTableHeader().setReorderingAllowed(false); //esto es para bloquear las columnas
		this._tabla.getTableHeader().setPreferredSize(new Dimension(0, 50));//las dimensiones de la cabecera
		this._tabla.getTableHeader().setBackground(_CABECERA_COLOR);
		this._tabla.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16) );
		this._tabla.getTableHeader().setForeground(new Color(255,255,255));
	}
	private void propiedadesDeCartel() {
		this._cartelFecha.setForeground(Color.WHITE);
		this._cartelFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
		this._cartelFecha.setHorizontalAlignment(SwingConstants.CENTER);

		this._cartelFecha.setBounds(0, 0, 785, 42);
		this._cartelFecha.setOpaque(true);
		this._cartelFecha.setBackground(this._COLOR_CARTEL_FECHA);
		this._cartelFecha.setBorder(new LineBorder(this._COLOR_BORDE_CARTEL_FECHA, 6));
	}
	public void mostrar(boolean visibilidad) {
		this.setVisible(visibilidad);
	}
}
