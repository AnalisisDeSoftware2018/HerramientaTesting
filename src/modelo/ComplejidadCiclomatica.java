package modelo;

public class ComplejidadCiclomatica {

	public static int obtener(String codigo) {
		int c = 1;
		String[] lineas = codigo.split("\\n");
		for (int i = 0; i < lineas.length; i++) {
			c += (Parser.ocurrenciasPorLinea(lineas[i], "&&") + Parser.ocurrenciasPorLinea(lineas[i], "||"));
			if (lineas[i].contains("if") || lineas[i].contains("while") || lineas[i].contains("for")
					|| lineas[i].contains("case") || lineas[i].contains("catch")) {
				c++;
			}
		}

		return c;
	}
}