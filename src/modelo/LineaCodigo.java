package modelo;

import java.util.Scanner;

public class LineaCodigo {

	private String codigo;
	private int lineasComentadas;
	private int lineasSinComentar;
	private int lineas;

	public LineaCodigo(String codigo) {
		this.codigo = codigo;
		lineasComentadas = 0;
		lineasSinComentar = 0;
		lineas = 0;
		calcularLineas();
	}

	@SuppressWarnings("resource")
	private void calcularLineas() {

		Scanner sc = new Scanner(codigo);
		String linea;
		while (sc.hasNextLine()) {
			lineas++;
			linea = sc.nextLine();
			if (linea.contains("//")) {
				lineasComentadas++;
			} else if (linea.contains("/*")) {
				if (linea.contains("*/")) {
					lineasComentadas++;
				} else {
					lineasComentadas++;
					while (sc.hasNextLine()) {//lineasComentadas++;
						lineas++;
						lineasComentadas++;
						linea = sc.nextLine();
						if(linea.contains("*/")) {
							break;
						}
					}
				}
			}else {
				lineasSinComentar++;
			}
		}

	}

	public int getLineasComentadas() {
		return lineasComentadas;
	}

	public int getLineasSinComentar() {
		return lineasSinComentar;
	}
	
	public int getLineas() {
		return lineas;
	}
}
