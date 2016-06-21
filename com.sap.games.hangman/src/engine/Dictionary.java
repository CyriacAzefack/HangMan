package engine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import utils.FileManager;

public class Dictionary implements WordList{
	
	//private final static String OUTPUT_PATH = "C:\Users\I327247\workspace\com.sap.games.hangman\src\engine\ressources\Dictionaries";
	/**
	 * The Name of the dictionary
	 */
	private String name;
	
	/**
	 * List of the words in the dictionary <br/>
	 * <Length of the word, <List of words>>
	 */
	private HashMap<Integer, List<String>> wordsMap;
	
	
	/**
	 * Create a empty dictionary
	 * @param name
	 */
	public Dictionary(String name) {
		this.name = name;
		this.wordsMap = new HashMap<Integer, List<String>>();
	}
	
	/**
	 * Create a new dictionary of words from the file
	 * @param name
	 * 		Name of the dictionary
	 * @param file
	 * 		File to fill the dictionary with
	 * @throws FileNotFoundException 
	 * @throws DuplicateWordException 
	 */
	public Dictionary(String name, String filePath) throws FileNotFoundException, DuplicateWordException {
		this(name);
		
		addWordsFromFile(filePath);
		
		save();
	}
	
	
	
	/**
	 * @return The number of words in the dictionary
	 */
	@Override
	public int size() {
		int size = 0;
	
		for(Integer key: wordsMap.keySet()) {
			size += size(key);	
		}
		
		return size;
	}
	
	/**
	 * @param wordLength
	 * 		The length of the words
	 * @return The number of words with that length in the dictionary
	 */
	public int size(int wordLength) {
		if (!wordsMap.containsKey(wordLength))
			return 0;
		return this.wordsMap.get(wordLength).size();
	}
	
	/**
	 * @return a random word from the dictionary
	 * @throws WordNotFoundException 
	 */
	public String randomPic() throws WordNotFoundException {
		String randWord;
		int randLength;
		int randIndex;
		Set<Integer> set = wordsMap.keySet();
		
		if (wordsMap.isEmpty())
			throw new WordNotFoundException("Empty dictionary");
		
		while(set.size() > 0) {
			Random rand = new Random();
			
			randIndex = rand.nextInt(set.size());
			
			randLength = (int) wordsMap.keySet().toArray()[randIndex];
			
			try {
				randWord = randomPic(randLength);
				
				return randWord;
			} catch (WordNotFoundException ex) {
				set.remove(randIndex);
			}
			
		}
		return null;
		
	}

	/**
	 * Pic a random word in the dictionary with a specific number of characters
	 * @param wordLength
	 * 			Length of the random word
	 * @return a random word from  the dictionary with that length
	 * @throws WordNotFoundException 
	 */
	public String randomPic(int wordLength) throws WordNotFoundException {
		int randIndex;
		Random rand = new Random();
		
		int nbWords = size(wordLength);
		
		if (nbWords < 1)
			throw new WordNotFoundException("No Words found with '"+wordLength+"' characters");
		randIndex = rand.nextInt(nbWords);
		
		return wordsMap.get(wordLength).get(randIndex);
	}
	
	/**
	 * Add new Words to the dictionary
	 * @param newWords
	 * 			list of words to add (no duplicates)
	 */
	
	public void addWords(String... newWords) throws DuplicateWordException{
		for(String word : newWords) {
			addSingleWord(word);
		}
	}
	
	/**
	 * Add a word in the dictionary
	 * @param word
	 * 		word to add
	 * @throws DuplicateWordException
	 */
	private void addSingleWord(String word) throws DuplicateWordException {
		word = word.trim().toUpperCase();
		int l = word.length();
		List<String> siblingList;
		
		if (contains(word))
			throw new DuplicateWordException("'"+word+"' is already in the dictionary");
		
		//Check if the map already contains a word with the same size
		if (this.wordsMap.containsKey(l)) {
			siblingList = wordsMap.get(l);
			siblingList.add(word);
			
			wordsMap.replace(l, siblingList);
		}
		else {
			siblingList = new ArrayList<String>();
			siblingList.add(word);
			wordsMap.put(l, siblingList);
		}
	}
	
	@Override
	public void removeWords(String... word) throws WordNotFoundException {
		throw new WordNotFoundException("Not implemented yet...");
	}
	
	/**
	 * Add every line in the file as a new word in the dictionary
	 * @param filePath
	 * 		Full path of the file to read
	 * @throws FileNotFoundException 
	 * @throws DuplicateWordException 
	 */
	public void addWordsFromFile(String filePath) throws FileNotFoundException, DuplicateWordException {
		ArrayList<String> list = FileManager.readFile(filePath);
		String[] array = new String[list.size()];
		addWords(list.toArray(array));
	}
	
	/**
	 * 
	 * @param word
	 * @return true if the dictionary contains this word, false otherwise
	 */
	private boolean contains(String word) {
		int l = word.length();
		
		if (!wordsMap.containsKey(l))
			return false;
		return wordsMap.get(word.length()).contains(word);
	}
	
	/**
	 * Save the dictionary
	 */
	public void save() {
		System.out.println(wordsMap);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isEmpty() {
		return wordsMap.isEmpty();
	}


}
