package field;

import java.io.Serializable;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:01:45 AM
 * 
 */

public class FrameOrientationLength implements Serializable,Fields {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final byte[] UP = new byte[] { 0x00, 0x00 };
	private static final  byte[] DOWN = new byte[] { 0x10, 0x00 };
	public static final int LENGTH = 2;
	private static final int MINFRAMELEN = 1;
	private static final int MAXFRAMELEN = 65535;
	
	private byte[] content = new byte[LENGTH];
	
	public FrameOrientationLength(byte[] in){
		System.arraycopy(in, 0, content, 0, LENGTH);
	}

	@Override
	public int getFieldsLength() {
		return LENGTH;
	}
	
//	public boolean
}
