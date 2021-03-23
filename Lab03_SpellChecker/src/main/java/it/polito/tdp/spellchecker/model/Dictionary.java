package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	
	List<String> dizionario = new LinkedList<String>();
	
	/**
	 * carica il dizionario nella variabile dizionario a seconda della language scelta
	 * @param language
	 */
	public void loadDictionary (String language) {
		dizionario.clear();
		try {
			FileReader fr = new FileReader("English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word=br.readLine()) != null) {
				word=word.replace("\n", "");
				dizionario.add(word);
			}
			br.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File non trovato");
		} catch (IOException ioe) {
			System.out.println("Errore nella lettura del file");
		} 
	}

	/**
	 * valuta quali parole dell'input sono corrette
	 * @param inputTextList
	 * @return
	 */
	public List<RichWord> spellCheckText (List<String> inputTextList) {
		List<RichWord> res = new ArrayList<RichWord>();
		for (String s: inputTextList) {
			RichWord rw = new RichWord(s, spellTextWord(s));
			res.add(rw);
		}
		return res;
	}
	
	/**
	 * valuta se una word è corretta (presente nel dizionario)
	 * @param word
	 * @return
	 */
	public boolean spellTextWord (String word) {
		boolean res = false;
		for (String s: dizionario) {
			if (word.equalsIgnoreCase(s)) {
				res = true;
				break;
			}
		}
		return res;
	}
}
