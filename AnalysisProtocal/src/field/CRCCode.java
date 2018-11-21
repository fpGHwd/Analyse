package field;
import java.io.Serializable;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:05:44 AM
 * 
 */

public class CRCCode implements Serializable, Fields {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int LENGTH=2;
	public byte[] content = new byte[LENGTH];
	
	public CRCCode(byte[] in){
		System.arraycopy(in, 0, content, 0, LENGTH);
	}

	@Override
	public int getFieldsLength() {
		return LENGTH;
	}

}
