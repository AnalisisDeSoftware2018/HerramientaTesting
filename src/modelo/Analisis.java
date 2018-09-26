package modelo;

import java.text.DecimalFormat;
import java.util.Collection;

import javax.swing.JList;

public class Analisis {
	private String codigo;
	private int lineasMetodo;
	private int lineasComentadas;
	private int complejidad;
	private int fanIn;
	private int fanOut;
	private float longHalstead;
	private float volHalstead;
	

	public int getLineasMetodo() {
		return lineasMetodo;
	}
	public int getLineasComentadas() {
		return lineasComentadas;
	}
	public String getPorcentajeLineasComentadas() {
		DecimalFormat df = new DecimalFormat("0.00");
		return String.valueOf(df.format(((float) lineasComentadas / lineasMetodo) * 100) + "%");
	}
	public int getComplejidad() {
		return complejidad;
	}
	public int getFanIn() {
		return fanIn;
	}
	public int getFanOut() {
		return fanOut;
	}
	public float getLongHalstead() {
		return longHalstead;
	}
	public float getVolHalstead() {
		return volHalstead;
	}

	public Analisis(String codigo, JList<String> listaMetodos, Collection<String> listaArchivos) {
		super();
		this.codigo = codigo;
		actualizarDatosAnalisis(listaMetodos, listaArchivos);
	}

	private void actualizarDatosAnalisis(JList<String> listaMetodos, Collection<String>listaArchivos){
		LineaCodigo lineaCodigo = new LineaCodigo(codigo);
		lineasMetodo = lineaCodigo.getLineas();
		lineasComentadas = lineaCodigo.getLineasComentadas();
		complejidad = ComplejidadCiclomatica.obtener(codigo);
//		fanIn = FanInOut.getFanIn(codigo, listaMetodos); 
//		fanOut = FanInOut.getFanOut(codigo, listaMetodos, listaArchivos);
		
//		Halstead h = new Halstead(codigo); // falta
//		longHalstead = h.getLongitud();
//		volHalstead = h.getVolumen();
	}
}
