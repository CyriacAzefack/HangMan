package engine;

public class DuplicateWordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public DuplicateWordException(String msg)
	    {
	        super(msg);
	    }

	    public DuplicateWordException()
	    {
	    }

	    public DuplicateWordException(Throwable cause)
	    {
	        super(cause);
	    }
}
