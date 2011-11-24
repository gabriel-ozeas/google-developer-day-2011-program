package ozeas.gabriel;

import java.util.Comparator;

public class GooglonComparator implements Comparator<String> {
	final char[] ordem = new char[]{'d','b','l','h','q','c','n','v','p','m','k','z','g','r','s','w','j','x','f','t'};
	
	@Override
	public int compare(String palavra1, String palavra2) {
		char[] caracteres1 = palavra1.toCharArray();
		char[] caracteres2 = palavra2.toCharArray();
		
		int tamanhoMenor = caracteres1.length <= caracteres2.length ? caracteres1.length : caracteres2.length;
		for (int i = 0; i < tamanhoMenor; i++) {
			int indiceA = indiceLetra(caracteres1[i]);
			int indiceB = indiceLetra(caracteres2[i]);
			if (indiceA < indiceB) {
				return -1;
			} else if (indiceA > indiceB) {
				return 1;
			}
		}
		
		if (caracteres1.length > tamanhoMenor) {
			return 1;
		} else if (caracteres2.length > tamanhoMenor){
			return -1;
		}
		return 0;
	}
	
	private int indiceLetra(char letra) {
		for (int i = 0; i < ordem.length; i++) {
			if (letra == ordem[i])
				return i;
		}
		throw new IllegalArgumentException("Letra invalida");
	}
}
