package field;
import java.io.Serializable;
import java.util.Arrays;

import excptions.LengthException;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 10:58:57 AM
 * 
 */

public class FrameStartCode implements Serializable, Fields{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int LENGTH = 2;
	private final byte[] CORRECT= {0x7e, 0x7e};
	private byte[] content = new byte[LENGTH];
	
	public FrameStartCode(byte[] in) throws LengthException{
		if(in.length != LENGTH){
			throw new LengthException(this.toString());
		}else{
			System.arraycopy(in, 0, content, 0, LENGTH);
		}
	}
	
	@Override
	public int getLength() {
		return LENGTH;
	}
	
	public boolean validateStartCode(){
		return Arrays.equals(CORRECT, content);
	}
	
}
