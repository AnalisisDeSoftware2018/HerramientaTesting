package modelo;

public class ComplejidadCiclomatica {

	public static int obtener(String codigo) {
		int c = 1, a;
		String[] lineas = codigo.split("\n");
		for (int i = 0; i < lineas.length; i++) {
			a = (Parser.ocurrenciasPorLinea(lineas[i], "&&") + Parser.ocurrenciasPorLinea(lineas[i], "||"))+1;
			if(a==1) {
				a--;
			}
			if (a==0 && (lineas[i].contains("if") || lineas[i].contains("while") || lineas[i].contains("for(") || 
					lineas[i].contains("for (")
					|| lineas[i].contains("case") || lineas[i].contains("catch"))) {
				a=1;
			}
			c+=a;
		}

		return c;
	}
}