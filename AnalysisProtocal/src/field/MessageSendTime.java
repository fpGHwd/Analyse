package field;

import java.io.Serializable;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:20:07 PM
 * 
 */

public class MessageSendTime implements Fields , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int LENGTH = 6;
	private byte[] content = new byte[LENGTH];

	@Override
	public int getLength() {
		return LENGTH;
	}
}
