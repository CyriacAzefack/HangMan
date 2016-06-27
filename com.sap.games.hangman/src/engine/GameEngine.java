package engine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * An instance of Hangman Game
 * @author I327247
 *
 */
public class GameEngine {
	
	/**
	 * Number max of trials
	 */
	private static final int NB_TRIALS_MAX = 10;
	
	/**
	 * Dictionary of words
	 */
	private WordList wordList;
	
	
	
	
	public GameEngine(WordList wordList) throws EmptyDictionaryException {
		if (wordList.isEmpty())
			throw new EmptyDictionaryException();
		this.wordList = wordList;
	}
	
	public void launch() throws WordNotFoundException {
		//Word to guess
		String word = randomPic();
		
		//length of the word
		int n = word.length();
		
		//Guessed String
		StringBuilder guessed = new StringBuilder();
		while(guessed.length() < n)
			guessed.append('_');
		
		//number of letters guessed
		int nbGuessed = 0;
		int trialsLeft = NB_TRIALS_MAX;
		ArrayList<String> lettersUsed = new ArrayList<String>();
		Scanner s = new Scanner(System.in);
		do {
			
			System.out.println("Letters already tested : " + lettersUsed);
			System.out.println("Chances left : " + trialsLeft);
			System.out.println("Guess a letter : " + guessed);
			
			
			char letter = s.nextLine().charAt(0);
			String letStr = letter+"";
			letStr = letStr.toUpperCase();
			
			if (!word.contains(letStr )) {
				trialsLeft --;
				if (!lettersUsed.contains(letStr)){
					lettersUsed.add(letStr);
				}
			}
			else {
				ArrayList<Integer> positions = getPositions(letter, word);
				 
				for (int p : positions) {
					guessed.setCharAt(p, letter);
					nbGuessed++;
				}
			}
			
			
		} while(nbGuessed < n && trialsLeft > 0);
		
		if (trialsLeft > 0)
			System.out.println("You find the word : " + word);
		else 
			System.out.println("You LOOOOOOOOOOOOOSE!!! the word was :" + word);
		System.out.println("THE END!!!!!!!!!");
		
		s.close();
		
	}
	
	public String randomPic() throws WordNotFoundException {
		return wordList.randomPic(8);
	}
	
	public ArrayList<Integer> getPositions(char letter, String word) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i=0; i<word.length(); i++){
			if (word.charAt(i) == letter)
				result.add(i);
		}
		return result;
	}
	
	
}
