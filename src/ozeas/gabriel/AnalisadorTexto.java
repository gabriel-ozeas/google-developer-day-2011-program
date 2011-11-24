package ozeas.gabriel;

import java.math.BigInteger;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalisadorTexto {
	final char[] ordem = new char[]{'d','b','l','h','q','c','n','v','p','m','k','z','g','r','s','w','j','x','f','t'};
	
	public int totalNumerosBonitos(String texto) {
		TreeSet<BigInteger> numeros = new TreeSet<BigInteger>();
		
		String[] palavras = texto.split(" ");
		for (String palavra : palavras) {
			if (!("".equals(palavra.trim()))) {
				BigInteger numero = valorNumerico(palavra);
				if (numero.compareTo(new BigInteger("504851")) >= 0) {
					if (numero.mod(new BigInteger("5")).compareTo(new BigInteger("0")) == 0) {
						numeros.add(valorNumerico(palavra));
					}
				}
			}
		}
		return numeros.size();
	}
	
	
	public BigInteger valorNumerico(String palavra) {
		long base = 1;
		char[] characteres = palavra.toCharArray();
		BigInteger numero = new BigInteger("0");
		for (char c : characteres) {
			BigInteger soma = new BigInteger("" + (base * indiceLetra(c)));
			numero = numero.add(soma);
			base = base * 20;
		}
		return numero;
	}
	
	private int indiceLetra(char letra) {
		for (int i = 0; i < ordem.length; i++) {
			if (letra == ordem[i])
				return i;
		}
		throw new IllegalArgumentException("Letra invalida");
	}
	
	public String ordenarPalavras(String texto) {
		String[] palavras = texto.split(" ");
		TreeSet<String> ordenadas = new TreeSet<String>(new GooglonComparator());
		for (String palavra : palavras) {
			if (!("".equals(palavra.trim())))
			ordenadas.add(palavra);
		}
		
		String textoOrdenado = "";
		for (String palavra : ordenadas) {
			textoOrdenado += palavra + " ";
		}
		
		return textoOrdenado;
	}
	
	
	public int totalPreposicoes(String texto) {
		String padrao = "^(([\\w&&[^t]]{3})([^zsphjt]){1})$";
		return this.totalComparacoes(texto, padrao);
	}
	
	public int totalVerbos(String texto) {
		String padrao = "^([\\w]{6,})([zsphj]){1}$";
		return this.totalComparacoes(texto, padrao);
	}
	
	public int totalVerbosPrimeiraPessoa(String texto) {
		String padrao = "^([zsphj]){1}([\\w]{5,})([zsphj]){1}$";
		return this.totalComparacoes(texto, padrao);
	}
	
	public int totalComparacoes(String texto, String padrao) {
		Pattern pattern = Pattern.compile(padrao);
		String[] palavras = texto.split(" ");
		int total = 0;
		for (String palavra : palavras) {
			Matcher matcher = pattern.matcher(palavra);
			while(matcher.find()) {
				total++;
			}
		}
		return total;
	}
	
}
