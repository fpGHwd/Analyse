package field;

import java.io.Serializable;

import common.ControlCode;
import excptions.LengthException;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:03:50 AM
 * 
 */

public class MessagePackageSumNumber extends ControlCode implements Serializable, Fields {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int LENGTH = 2 + 1;
	private static final int MINPACKAGENUM = 1;
	private static final int MAXPACKAGENUM = 4095;
	private byte[] content = new byte[LENGTH];
	private byte code;
	private int num = 0;
	private int index = 0;

	public MessagePackageSumNumber(byte[] in) {
		code = in[0];
		System.arraycopy(in, 1, content, 0, in.length-1);
	}

	private boolean validateMEC() {
		if (code == ETX || code == ETB || code == ENQ 
				|| code == ACK || code == EOT || code == ESC) {
			return true;
		}
		return false;
	}

	private boolean isUpFrame() {
		if (code == ETX || code == ETB) {
			return true;
		}
		return false;
	}

	private boolean isDownFrame() {
		if (code == ENQ || code == ACK || code == EOT || code == ESC) {
			return true;
		}
		return false;
	}

	public boolean validateMPSN(){
		return false;
	}

	@Override
	public int getLength() {
		return LENGTH;
	}
}
