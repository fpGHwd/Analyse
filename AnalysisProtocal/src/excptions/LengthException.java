package excptions;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 10:47:13 AM
 * 
 */

public class LengthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public LengthException(){
		super();
	}
	
	public LengthException(String msg){
		super(msg);
	}
	
	public LengthException(String msg, Throwable cause){
		super(msg, cause);
	}
	
	public LengthException(Throwable cause){
		super(cause);
	}
}
