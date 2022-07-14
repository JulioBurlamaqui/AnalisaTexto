package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.ufrj.java.julio.colecoes.AnalisaTexto;

public class Main {
	public static void main(String[] args) throws IOException {
		Map<String, Integer> frequencia = new HashMap<String, Integer>();
		ArrayList<String> stopWords = new ArrayList<String>();
		Scanner sc = new Scanner(new FileInputStream("stopwords_br.txt"));
		
		while(sc.hasNext()) 
		{
			String stopWord = sc.nextLine().toUpperCase();
			stopWords.add(stopWord);
		}
		try {
			AnalisaTexto texto = new AnalisaTexto("texticulo.txt", stopWords);
			frequencia = texto.computarFrequencia();
			for (String chave : frequencia.keySet()) 
			{
				System.out.println("Chave: " + chave +
								   "\nFrequência: " + frequencia.get(chave));
				System.out.println();
			}
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
	
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("analisaEngels.bin"));
		oos.writeObject(frequencia);
		oos.close();	
	}
}
