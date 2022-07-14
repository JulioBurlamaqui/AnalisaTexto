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
 * Classe que analisa a frequência das palavras em um arquivo texto
 * 
 * @author Júlio Burlamaqui
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
	 * Usa um array de String intermediário que pré-processa as palavras e as tokeniza.
	 * Nesse caso, o pré-processamento é a conversão de todos os caracteres para maiúsculo.
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
	 * Construtor sobrecarregado que recebe também um arquivo de stop words.
	 * 
	 * Esse construtor tem como base o de cima, mas com a diferença de que tem dois laços for.
	 * Eles comparam cada palavra do texto com cada stop word. Caso ela seja diferente de TODAS,
	 * é, então, adicionada no ArrayList palavras. Para contabilizar, é usado o contador cont.
	 * 
	 * @param path caminho do arquivo-texto
	 * @param stopWords são as palavras mais frequentemente usadas numa língua,como artigos, 
	 * preposições e conectivos. Podendo assim serem descartadas, já que são irrelevantes.
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
	 * Como o Set não admite palavras repetidas, cada palavra nele é única.
	 * 
	 * É um método privado, e portanto, apenas usado internamente.
	 * 
	 * @return HashSet de palavras únicas.
	 */
	
	private Set<String> pegarPalavrasUnicas()
	{
		Set<String> palavrasUnicas = new HashSet<String>();
		
		for (String palavra : palavras) 
			palavrasUnicas.add(palavra);
		
		return palavrasUnicas;
	}
	
	
	/**
	 * Compara cada palavra única com todas as palavras do texto, incrementando um contador cont
	 * a cada vez que elas forem iguais.
	 * 
	 * @return Map cuja chave são as palavras únicas, obtidas pelo método pegarPalavrasUnicas(),
	 * e os valores são suas frequências no texto.
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
	 * Sobre-escrita do toString que devolve quantas palavras únicas este objeto possui
	 */
	
	@Override
	public String toString() {
		String conteudo = "Esse objeto possui " + pegarPalavrasUnicas().size() + " palavras únicas";
		return conteudo;
	}
}