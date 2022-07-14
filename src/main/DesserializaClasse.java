package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class DesserializaClasse {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("analisaEngels.bin"));		Map<String, Integer> frequencia = (HashMap<String, Integer>) ois.readObject();
		ois.close();
		
		for (String chave : frequencia.keySet()) 
		{
			System.out.println("Chave: " + chave +
							   "\nFrequência: " + frequencia.get(chave));
			System.out.println();
		}	

	}

}
