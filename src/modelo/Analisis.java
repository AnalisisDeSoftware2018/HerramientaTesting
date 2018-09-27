package modelo;

import java.text.DecimalFormat;
import java.util.Collection;

public class Analisis {
	private String codigo;
	private int lineasMetodo;
	private int lineasComentadas;
	private int complejidad;
	private int fanIn;
	private int fanOut;
	private float longHalstead;
	private float volHalstead;
	
	public Analisis(String codigo,String archivoSeleccionado,Collection<String> listaArchivos) {
		this.codigo = codigo;
		actualizarDatosAnalisis(listaArchivos,archivoSeleccionado);
	}

	private void actualizarDatosAnalisis( Collection<String> listaArchivos, String archivoSeleccionado){
		LineaCodigo lineaCodigo = new LineaCodigo(codigo);
		lineasMetodo = lineaCodigo.getLineas();
		lineasComentadas = lineaCodigo.getLineasComentadas();
		complejidad = ComplejidadCiclomatica.obtener(codigo);
		fanIn = FanInOut.getFanIn(codigo, listaArchivos); 
		fanOut = FanInOut.getFanOut(codigo,archivoSeleccionado, listaArchivos);
		
		Halstead h = new Halstead(codigo);
		longHalstead = h.getLongitud();
		volHalstead = h.getVolumen();
	}
	
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


}
