package ozeas.gabriel;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class AnalisadorTextoTest {
	
	private AnalisadorTexto analisador;
	private BufferedReader textoA;
	private BufferedReader textoB;
	
	@Before
	public void setUp() throws Exception {
		analisador = new AnalisadorTexto();
		File fileA = new File("TextoA.txt");
		textoA = new BufferedReader(new FileReader(fileA));
		
		File fileB = new File("TextoB.txt");
		textoB = new BufferedReader(new FileReader(fileB));
	}
	
	@Test
	public void totalNumerosBonitos() throws Exception {
		String numeros = "d ttt dd dddbbb ";
		assertEquals(1, analisador.totalNumerosBonitos(numeros));
		numeros = "d ttt dd dddbbb tlqkfws"; 
		assertEquals(1, analisador.totalNumerosBonitos(numeros));
	}
	
	@Test
	public void transformarUnidade() throws Exception {
		assertEquals(new BigInteger("0"), analisador.valorNumerico("d"));
		assertEquals(new BigInteger("1"), analisador.valorNumerico("b"));
		assertEquals(new BigInteger("19"), analisador.valorNumerico("t"));
	}
	
	@Test
	public void transformarDecimal() throws Exception {
		assertEquals(new BigInteger("0"), analisador.valorNumerico("dd"));
		assertEquals(new BigInteger("20"), analisador.valorNumerico("db"));
		assertEquals(new BigInteger("40"), analisador.valorNumerico("dl"));
		assertEquals(new BigInteger("42"), analisador.valorNumerico("ll"));
		assertEquals(new BigInteger("42"), analisador.valorNumerico("ll"));
		assertEquals(new BigInteger("399"), analisador.valorNumerico("tt"));
	}
	
	@Test
	public void transformarCentesimo() throws Exception {
		assertEquals(new BigInteger("0"), analisador.valorNumerico("ddd"));
		assertEquals(new BigInteger("7999"), analisador.valorNumerico("ttt"));
	}
	
	@Test
	public void transformarExemploGoogle() throws Exception {
		assertEquals(new BigInteger("946961659"), analisador.valorNumerico("tlqkfws"));
	}
	
	@Test
	public void ordenarVocabulario() throws Exception {
		String texto = "b d c q h l";
		assertEquals("d b l h q c ", analisador.ordenarPalavras(texto));
		
		texto = "b d c q h b l";
		assertEquals("d b l h q c ", analisador.ordenarPalavras(texto));
		
		texto = "b b b c";
		assertEquals("b c ", analisador.ordenarPalavras(texto));
		
		texto = "hqc blh wqc  wdb    ";
		assertEquals("blh hqc wdb wqc ", analisador.ordenarPalavras(texto));
	}

	
	
	@Test
	public void contandoVerbosPrimeiraPessoa() throws Exception {
		String verbo = "zaaaaaz";
		assertEquals(1, analisador.totalVerbos(verbo));
		
		verbo = "pasaaas";
		assertEquals(1, analisador.totalVerbos(verbo));
		
		verbo = "hasaaas jasaaap";
		assertEquals(2, analisador.totalVerbos(verbo));
		
		verbo = "aasaaass jsdfghh httttth";
		assertEquals(3, analisador.totalVerbos(verbo));
		
		verbo = "pnhjfep zsdfghh jttttth";
		assertEquals(3, analisador.totalVerbos(verbo));
	}
	
	@Test
	public void contandoNenhumVerbosPrimeiraPessoa() throws Exception {
		String verbo = "zaaz";
		assertEquals(0, analisador.totalVerbos(verbo));
		
		verbo = "pasaaasaaa";
		assertEquals(0, analisador.totalVerbos(verbo));
		
		verbo = "hasaaaa jaaap";
		assertEquals(0, analisador.totalVerbos(verbo));
	}
	
	@Test
	public void contandoVerbos() throws Exception {
		String verbo = "aaaaaaz";
		assertEquals(1, analisador.totalVerbos(verbo));
		
		verbo = "aasaaas";
		assertEquals(1, analisador.totalVerbos(verbo));
		
		verbo = "aasaaas aasaaap";
		assertEquals(2, analisador.totalVerbos(verbo));
		
		verbo = "aasaaass asdfghh tttttth";
		assertEquals(3, analisador.totalVerbos(verbo));
		
		verbo = "bnhjfep asdfghh tttttth";
		assertEquals(3, analisador.totalVerbos(verbo));
	}
	
	
	
	@Test
	public void contandoNenhumVerbo() throws Exception {
		String verbo = "qwertyui";
		assertEquals(0, analisador.totalVerbos(verbo));
		
		verbo = "ghjfgp";
		assertEquals(0, analisador.totalVerbos(verbo));
	}
	
	@Test
	public void contandoPreposicao() throws Exception {
		String preposicao = "aaaa";
		assertEquals(1, analisador.totalPreposicoes(preposicao));
		
		preposicao = "aaaa aaaa";
		assertEquals(2, analisador.totalPreposicoes(preposicao));
		
		preposicao = "aaaaz bvcx";
		assertEquals(1, analisador.totalPreposicoes(preposicao));
	}
	
	@Test
	public void contandoNenhumaPreposicao() throws Exception {
		String preposicao = "aaaaa";
		assertEquals(0, analisador.totalPreposicoes(preposicao));
		
		preposicao = "aaaaa aaa";
		assertEquals(0, analisador.totalPreposicoes(preposicao));
		
		preposicao = "aaaas";
		assertEquals(0, analisador.totalPreposicoes(preposicao));
		
		preposicao = "aaat";
		assertEquals(0, analisador.totalPreposicoes(preposicao));
		
		preposicao = "atcd";
		assertEquals(0, analisador.totalPreposicoes(preposicao));
	}
	
	@Test
	public void contandoPreposicoesDoTextoA() throws Exception {
		assertEquals(56, analisador.totalPreposicoes(textoA.readLine()));
	}
	
	@Test
	public void contandoPreposicoesDoTextoB() throws Exception {
		System.out.println("Existem " + analisador.totalPreposicoes(textoB.readLine()) + " preposições no Texto B.");
	}
	
	@Test
	public void contandoVerbosDoTextoA() throws Exception {
		assertEquals(52, analisador.totalVerbos(textoA.readLine()));
	}
	
	@Test
	public void contandoVerbosDoTextoB() throws Exception {
		System.out.println("Há " + analisador.totalVerbos(textoB.readLine()) + " verbos no Texto B." );
	}
	
	@Test
	public void contantoVerbosPrimeiroTextoA() throws Exception {
		assertEquals(17, analisador.totalVerbosPrimeiraPessoa(textoA.readLine()));
	}
	
	@Test
	public void contantoVerbosPrimeiroTextoB() throws Exception {
		System.out.println("Há " + analisador.totalVerbosPrimeiraPessoa(textoB.readLine()) + " verbos em primeira pessoa no Texto B.");
	}
	
	@Test
	public void ordenarTextoA() throws Exception {
		String ordenado = analisador.ordenarPalavras(textoA.readLine());
		File vocabularioA = new File("VocabularioA.txt");
		String ordemCerta = new BufferedReader(new FileReader(vocabularioA)).readLine();
		assertEquals(ordemCerta, ordenado);
	}
	
	@Test
	public void ordenarTextoB() throws Exception {
		String ordenado = analisador.ordenarPalavras(textoB.readLine());
		System.out.println("Vocabulario:");
		System.out.println(ordenado);
	}
	
	@Test
	public void totalNumerosBonitosTextoA() throws Exception {
		assertEquals(72, analisador.totalNumerosBonitos(textoA.readLine()));
	}
	
	@Test
	public void totalNumerosBonitosTextoB() throws Exception {
		int total = analisador.totalNumerosBonitos(textoB.readLine());
		System.out.println("No Texto B, há " + total + " números bonitos distintos (atenção!).");
	}
}
