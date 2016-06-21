package engine;

public class WordNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public WordNotFoundException(String msg)
	    {
	        super(msg);
	    }

	    public WordNotFoundException()
	    {
	    }

	    public WordNotFoundException(Throwable cause)
	    {
	        super(cause);
	    }
}
