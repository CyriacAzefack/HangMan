package engine;

public class TestEngine {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Dictionary dic = new Dictionary("Easy Words");
		dic.addWordsFromFile(
				"src/engine/ressources/found.txt");
		
		Game game = new Game(dic);
		game.launch();
	}

}
