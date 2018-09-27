package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modelo.Analisis;
import modelo.Parser;

public class HerramientaTestingInterfaz extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField txfPath;
	private JButton btnBuscar;
	private JTextArea textAreaMetodo; 
	private JList<String> listaClases;
	private JList<String> listaMetodos;
	private Map<String,String> clasesMap;
	private Parser parser;
	private JLabel lbLineasTotales;
	private JLabel lbComentarios;
	private JLabel lbPorcentaje;
	private JLabel lbComplejidad;
	private JLabel lbFanIn;
	private JLabel lbFanOut;
	private JLabel lbLongitud;
	private JLabel lbVolumen;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HerramientaTestingInterfaz frame = new HerramientaTestingInterfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HerramientaTestingInterfaz() {
		setTitle("Herramienta de testing");
		setResizable(Boolean.FALSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		getContentPane().setLayout(null);
		
		txfPath = new JTextField();
		txfPath.setBounds(12, 23, 783, 19);
		getContentPane().add(txfPath);
		txfPath.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(789, 23, 97, 18);
		getContentPane().add(btnBuscar);
		
		JLabel lblMtodos = new JLabel("Métodos");
		lblMtodos.setBounds(12, 203, 97, 15);
		getContentPane().add(lblMtodos);
		
		textAreaMetodo = new JTextArea();
		textAreaMetodo.setBounds(12, 0, 485, 280);
		
		JScrollPane scrollCodigoMetodo = new JScrollPane();
		scrollCodigoMetodo.setBounds(12, 363, 874, 280);
		scrollCodigoMetodo.setViewportView(textAreaMetodo);
		getContentPane().add(scrollCodigoMetodo);
		
		JLabel lblClases = new JLabel("Clases");
		lblClases.setBounds(12, 67, 70, 15);
		getContentPane().add(lblClases);
		
		listaClases = new JList<>();
		listaClases.setBounds(0, 0, 478, 100);
		listaClases.addListSelectionListener(e->cargarMetodos());
		listaClases.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneClases = new JScrollPane();
		scrollPaneClases.setBounds(12, 94, 381, 100);
		scrollPaneClases.setViewportView(listaClases);
		getContentPane().add(scrollPaneClases);
		
		listaMetodos = new JList<>();
		listaMetodos.setBounds(0, 0, 478, 100);
		listaMetodos.addListSelectionListener(e->cargarCodigoMetodo());
		listaMetodos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneMetodos = new JScrollPane();
		scrollPaneMetodos.setBounds(12, 230, 381, 100);
		scrollPaneMetodos.setViewportView(listaMetodos);
		getContentPane().add(scrollPaneMetodos);
		
		JLabel lblCdigo = new JLabel("Código ");
		lblCdigo.setBounds(12, 342, 70, 15);
		getContentPane().add(lblCdigo);
				
		JPanel panelAnalisis = new JPanel();
		panelAnalisis.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)),
				"An\u00E1lisis del m\u00E9todo", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		((TitledBorder) panelAnalisis.getBorder())
				.setTitleFont(((TitledBorder) panelAnalisis.getBorder()).getTitleFont().deriveFont(Font.BOLD));
		panelAnalisis.setBounds(417, 96, 469, 234);
		getContentPane().add(panelAnalisis);
		panelAnalisis.setLayout(null);
		
		JLabel lblLneasDeCdigo = new JLabel("Líneas de código totales");
		lblLneasDeCdigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLneasDeCdigo.setBounds(-11, 42, 254, 14);
		panelAnalisis.add(lblLneasDeCdigo);

		lbLineasTotales = new JLabel("-");
		lbLineasTotales.setForeground(Color.BLUE);
		lbLineasTotales.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbLineasTotales.setHorizontalAlignment(SwingConstants.CENTER);
		lbLineasTotales.setBounds(-11, 56, 254, 14);
		panelAnalisis.add(lbLineasTotales);

		JLabel lblLneasDeCdigo_1 = new JLabel("Líneas de código comentadas");
		lblLneasDeCdigo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLneasDeCdigo_1.setBounds(232, 42, 237, 14);
		panelAnalisis.add(lblLneasDeCdigo_1);

		lbComentarios = new JLabel("-");
		lbComentarios.setHorizontalAlignment(SwingConstants.CENTER);
		lbComentarios.setForeground(Color.BLUE);
		lbComentarios.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbComentarios.setBounds(215, 56, 254, 14);
		panelAnalisis.add(lbComentarios);

		JLabel lblPorcentajeDeLneas = new JLabel("Porcentaje de líneas de código comentadas");
		lblPorcentajeDeLneas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcentajeDeLneas.setBounds(215, 92, 254, 15);
		panelAnalisis.add(lblPorcentajeDeLneas);

		lbPorcentaje = new JLabel("-");
		lbPorcentaje.setHorizontalAlignment(SwingConstants.CENTER);
		lbPorcentaje.setForeground(Color.BLUE);
		lbPorcentaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbPorcentaje.setBounds(215, 106, 254, 14);
		panelAnalisis.add(lbPorcentaje);

		JLabel lblComplejidadCiclomtica = new JLabel("Complejidad ciclomática");
		lblComplejidadCiclomtica.setHorizontalAlignment(SwingConstants.CENTER);
		lblComplejidadCiclomtica.setBounds(-11, 92, 254, 14);
		panelAnalisis.add(lblComplejidadCiclomtica);

		lbComplejidad = new JLabel("-");
		lbComplejidad.setHorizontalAlignment(SwingConstants.CENTER);
		lbComplejidad.setForeground(Color.BLUE);
		lbComplejidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbComplejidad.setBounds(-11, 106, 254, 14);
		panelAnalisis.add(lbComplejidad);

		JLabel lblFanIn = new JLabel("Fan in");
		lblFanIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanIn.setBounds(-11, 194, 254, 14);
		panelAnalisis.add(lblFanIn);

		lbFanIn = new JLabel("-");
		lbFanIn.setHorizontalAlignment(SwingConstants.CENTER);
		lbFanIn.setForeground(Color.BLUE);
		lbFanIn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbFanIn.setBounds(-11, 208, 254, 14);
		panelAnalisis.add(lbFanIn);

		JLabel lblFanOut = new JLabel("Fan out");
		lblFanOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanOut.setBounds(215, 194, 254, 14);
		panelAnalisis.add(lblFanOut);

		lbFanOut = new JLabel("-");
		lbFanOut.setHorizontalAlignment(SwingConstants.CENTER);
		lbFanOut.setForeground(Color.BLUE);
		lbFanOut.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbFanOut.setBounds(215, 208, 254, 14);
		panelAnalisis.add(lbFanOut);

		JLabel lblHalsteadLongitud = new JLabel("Halstead longitud");
		lblHalsteadLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblHalsteadLongitud.setBounds(215, 140, 254, 14);
		panelAnalisis.add(lblHalsteadLongitud);

		lbLongitud = new JLabel("-");
		lbLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		lbLongitud.setForeground(Color.BLUE);
		lbLongitud.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbLongitud.setBounds(215, 154, 254, 14);
		panelAnalisis.add(lbLongitud);

		JLabel lblHalsteadVolumen = new JLabel("Halstead volumen");
		lblHalsteadVolumen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHalsteadVolumen.setBounds(-11, 141, 254, 14);
		panelAnalisis.add(lblHalsteadVolumen);

		lbVolumen = new JLabel("-");
		lbVolumen.setHorizontalAlignment(SwingConstants.CENTER);
		lbVolumen.setForeground(Color.BLUE);
		lbVolumen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbVolumen.setBounds(-11, 155, 254, 14);
		panelAnalisis.add(lbVolumen);
		
		btnBuscar.addActionListener(e->openChooserFile());
	}

	private void cargarReporte(String codigo) {
		Analisis a = new Analisis(codigo, clasesMap.get(listaClases.getSelectedValue()), clasesMap.values());
		lbLineasTotales.setText(String.valueOf(a.getLineasMetodo()));
		lbComentarios.setText(String.valueOf(a.getLineasComentadas()));
		lbPorcentaje.setText(a.getPorcentajeLineasComentadas());
		lbComplejidad.setText(String.valueOf(a.getComplejidad()));
		lbFanIn.setText(String.valueOf(a.getFanIn()));
		lbFanOut.setText(String.valueOf(a.getFanOut()));
		lbLongitud.setText(String.valueOf(a.getLongHalstead()));
		lbVolumen.setText(String.valueOf(a.getVolHalstead()));	
	}

	private void cargarCodigoMetodo() {
		String codigo = parser.codigoMetodo(listaMetodos.getSelectedValue());
		textAreaMetodo.setText(codigo);
		cargarReporte(codigo);
	}

	private void cargarMetodos() {
		parser = new Parser(clasesMap.get(listaClases.getSelectedValue()));
		actualizarLista(listaMetodos,listToArray(parser.getMetodos()));
	}

	private void openChooserFile() {

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			txfPath.setText(chooser.getSelectedFile().getPath());
			listaMetodos.removeAll();
			// Buscamos todos los archivos .java en la carpeta
			// seleccionada
			File[] archivos = chooser.getSelectedFile().listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".java");
				}
			});

			// Obtenemos la ruta de cada archivo y actualizamos la
			// lista.
			// Si la carpeta no contiene ningun archivo .java, mostramos
			// un mensaje de error.
			clasesMap = new HashMap<>();
			if (archivos.length > 0) {
				String path;
				for (int i = 0; i < archivos.length; i++) {
					path = archivos[i].getPath();
					clasesMap.put(path.substring(path.lastIndexOf("/")+1,path.lastIndexOf(".java")), path);
				}
				actualizarLista(listaClases,mapToArray(clasesMap));
			} else {
				JOptionPane.showMessageDialog(this,
						"La carpeta seleccionada no contiene archivos .java", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private static void actualizarLista(JList<String> lista, String[] elementos) {
		lista.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = elementos;
				
			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	private String[] mapToArray(Map<String, String> map) {
		String[] array =  new String[map.size()];
		int i=0;
		for (String key:map.keySet()) {
			array[i++] = key;
			
		}
		return array;
	}
	
	private String[] listToArray(List<String> list) {
		String[] array =  new String[list.size()];
		int i=0;
		for (String key:list) {
			array[i++] = key;
			
		}
		return array;
	}
}
