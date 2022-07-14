package br.ufrj.java.julio.colecoes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Classe que analisa a frequ�ncia das palavras em um arquivo texto
 * 
 * @author J�lio Burlamaqui
 */
public class AnalisaTexto implements Serializable{
	
	private static final long serialVersionUID = -7337861264948920032L;
	
	/**
	 * ArrayList das palavras tolkenizadas de um texto
	 */
	private ArrayList<String> palavras = new ArrayList<>();

	
	/**
	 * Construtor que armazena cada palavra no ArrayList palavras. 
	 * 
	 * Usa um array de String intermedi�rio que pr�-processa as palavras e as tokeniza.
	 * Nesse caso, o pr�-processamento � a convers�o de todos os caracteres para mai�sculo.
	 * 
	 * @param path caminho do arquivo-texto
	 * @throws IOException para alertar sobre FileNotFoundException
	 */
	
	public AnalisaTexto(String path) throws IOException 
	{
		Scanner sc = new Scanner(new FileInputStream(path));
		
		while(sc.hasNext())
		{
			String[] preProcessado = sc.nextLine().toUpperCase().split(" ");
			for (String palavra : preProcessado)
				palavras.add(palavra);
		}	
		
		sc.close();
	}
	
	
	/**
	 * Construtor sobrecarregado que recebe tamb�m um arquivo de stop words.
	 * 
	 * Esse construtor tem como base o de cima, mas com a diferen�a de que tem dois la�os for.
	 * Eles comparam cada palavra do texto com cada stop word. Caso ela seja diferente de TODAS,
	 * �, ent�o, adicionada no ArrayList palavras. Para contabilizar, � usado o contador cont.
	 * 
	 * @param path caminho do arquivo-texto
	 * @param stopWords s�o as palavras mais frequentemente usadas numa l�ngua,como artigos, 
	 * preposi��es e conectivos. Podendo assim serem descartadas, j� que s�o irrelevantes.
	 * @throws IOException para alertar sobre FileNotFoundException
	 */
	
	public AnalisaTexto(String path, ArrayList<String> stopWords) throws IOException
	{
		Scanner sc = new Scanner(new FileInputStream(path));
		
		while(sc.hasNext())
		{
			String[] preProcessado = sc.nextLine().toUpperCase().split(" ");
			for (String palavra : preProcessado)
			{
				int cont = 0;
				
				for (String stopWord : stopWords)
				{
					if(!palavra.equals(stopWord))
						cont++;
					if(cont == stopWords.size())
						palavras.add(palavra);
				}
			}	
		}	
		
		sc.close();
	}
	
	
	/**
	 * Adiciona cada palavra do ArrayList palavras em um HashSet.
	 * Como o Set n�o admite palavras repetidas, cada palavra nele � �nica.
	 * 
	 * � um m�todo privado, e portanto, apenas usado internamente.
	 * 
	 * @return HashSet de palavras �nicas.
	 */
	
	private Set<String> pegarPalavrasUnicas()
	{
		Set<String> palavrasUnicas = new HashSet<String>();
		
		for (String palavra : palavras) 
			palavrasUnicas.add(palavra);
		
		return palavrasUnicas;
	}
	
	
	/**
	 * Compara cada palavra �nica com todas as palavras do texto, incrementando um contador cont
	 * a cada vez que elas forem iguais.
	 * 
	 * @return Map cuja chave s�o as palavras �nicas, obtidas pelo m�todo pegarPalavrasUnicas(),
	 * e os valores s�o suas frequ�ncias no texto.
	 */
	
	public Map<String, Integer> computarFrequencia()
	{
		Map<String, Integer> frequencia = new HashMap<String, Integer>();
		
		for (String palavraUnica : pegarPalavrasUnicas()) 
		{
			int cont = 0;
			
			for (String palavra : palavras) 
			{
				if(palavraUnica.equals(palavra))
					cont++;
			}
			frequencia.put(palavraUnica, cont++);
		}
		
		return frequencia;
	}
	
	
	/**
	 * Sobre-escrita do toString que devolve quantas palavras �nicas este objeto possui
	 */
	
	@Override
	public String toString() {
		String conteudo = "Esse objeto possui " + pegarPalavrasUnicas().size() + " palavras �nicas";
		return conteudo;
	}
}