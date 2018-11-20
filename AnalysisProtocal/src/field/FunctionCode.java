package field;
import java.io.Serializable;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:01:08 AM
 * 
 */

public class FunctionCode implements Serializable,Fields {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final byte TEST_FRAME = 0x30;
	public static final byte CONSTANT_STEP_HYDRO_INFO = 0x31;
	public static final byte REMOTE_ADD_MESSAGE = 0x33;
	public static final byte REMOTE_HOUR_MESSAGE = 0x34;
	public static final byte REMOTE_MANUAL_SET_NUMBER_MESSAGE = 0x35;
	

	public static final int LENGTH=1;
	private static byte[] content = new byte[LENGTH];
	
	public FunctionCode(byte[] in){
		System.arraycopy(in, 0, content, 0, LENGTH);
	}
	
	@Override
	public int getLength() {
		return LENGTH;
	}
	
	public byte[] getContent(){
		return content;
	}

}
