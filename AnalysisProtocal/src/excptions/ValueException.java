package excptions;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 2:23:33 PM
 * 
 */

public class ValueException extends Exception {
	
	public ValueException(){
		super();
	}
	
	public ValueException(String msg){
		super(msg);
	}
	
	public ValueException(String msg, Throwable cause){
		super(msg, cause);
	}
	
	public ValueException(Throwable cause){
		super(cause);
	}
}
