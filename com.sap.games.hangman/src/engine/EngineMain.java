package engine;

import java.io.FileNotFoundException;

public class EngineMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Dictionary dic = new Dictionary("Easy Words");
		dic.addWordsFromFile(
				"C:/Users/I327247/workspace/com.sap.games.hangman/src/engine/ressources/found.txt");
		
		Game game = new Game(dic);
		game.launch();
	}

}
