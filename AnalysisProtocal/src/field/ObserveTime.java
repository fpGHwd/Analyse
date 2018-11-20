package field;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 1:55:27 PM
 * 
 */

public class ObserveTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final byte code = 0x00;
	private static final int LENGTH = 5 + 1;
	
	private byte[] content = new byte[LENGTH];
	

}
