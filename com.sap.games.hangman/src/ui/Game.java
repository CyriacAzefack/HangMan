package ui;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable{
	
	private static final int WIDTH = 800, HEIGHT = 600;
	
	private Thread thread;
	
	private boolean running = false;
	
	public Game() {
		System.out.println("HANGMAN GAME!!");
		new Window("HangMan Game", WIDTH, HEIGHT, this);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}


}
