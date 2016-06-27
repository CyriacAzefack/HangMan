package ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import engine.Dictionary;
import engine.DuplicateWordException;
import engine.EmptyDictionaryException;
import engine.GameEngine;
import engine.WordNotFoundException;
import utils.FileManager;

public class Game implements Runnable{
	
	
	public static final String INPUT_PATH = FileManager.ressourceRootPath();
	private static final int WIDTH = 800, HEIGHT = 600;
	
	private Thread thread;
	
	private boolean running = false;
	
	private GameEngine gameEngine;
	
	private Window window;
	
	private String word;
	

	
	public Game(Dictionary dic) throws EmptyDictionaryException {
		System.out.println("HANGMAN GAME!!");
		gameEngine = new GameEngine(dic);
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		try {
			this.word = gameEngine.randomPic();
			System.out.println("########## " + word + " ############# ");
		} catch (WordNotFoundException e) {
			e.printStackTrace();
		}
		window = new Window("HangMan Game", WIDTH, HEIGHT, this);
	}
	
	/**
	 * Ends the game
	 * @param win 
	 * 		True if the player wins and false otherwhise
	 */
	public synchronized void stop(boolean win){
		if (win) {
			JOptionPane.showMessageDialog(null, "CONGRATULATIONS!!!\n You find the word '"+word+"'");
		}
		else {
			JOptionPane.showMessageDialog(null, "YOU LOST!!!\n You didn't find the word '"+word+"'");
		}
		
		stop();
	}
	
	private synchronized void stop() {
		window.dispose();
		try {
			
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public String getWord() {
		return word;
	}
	
	public ArrayList<Integer> play(char letter) {
		return gameEngine.getPositions(letter, word);
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, DuplicateWordException, EmptyDictionaryException {
		Dictionary dic = new Dictionary("Easy Words");
		dic.addWordsFromFile(INPUT_PATH+ "/found.txt");
		
		Game g = new Game(dic);
		g.start();
	}

	


}
