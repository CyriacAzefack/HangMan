package engine;

public class EmptyDictionaryException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public EmptyDictionaryException(String msg)
	    {
	        super(msg);
	    }

	    public EmptyDictionaryException()
	    {
	    }

	    public EmptyDictionaryException(Throwable cause)
	    {
	        super(cause);
	    }
}
