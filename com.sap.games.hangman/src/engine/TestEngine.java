package engine;

public class TestEngine {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Dictionary dic = new Dictionary("Easy Words");
		dic.addWordsFromFile(
				"src/ressources/found.txt");
		
		GameEngine game = new GameEngine(dic);
		game.launch();
	}

}
