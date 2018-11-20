package common;
import java.io.Serializable;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:21:55 AM
 * 
 */

public class LinkStatue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// send a package with no reply
	public static final int M1=1;
	// send a package with reply
	public static final int M2=2;
	// send packages with a reply
	public static final int M3=3;
	// enquire with response
	public static final int M4=4;
}
