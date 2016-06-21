package engine;

public interface WordList {
	
	public int size();
	
	public int size(int wordLength);
	
	public String randomPic() throws WordNotFoundException;
	
	public String randomPic(int wordLength) throws WordNotFoundException;
	
	public void addWords(String... newWords) throws DuplicateWordException;
	
	public void removeWords(String... word) throws WordNotFoundException;
	
	public boolean isEmpty();
}
