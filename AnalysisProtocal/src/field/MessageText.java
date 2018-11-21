package field;

import java.io.Serializable;
import java.util.ArrayList;

import common.ControlCode;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:04:40 AM
 * 
 */

public class MessageText extends ControlCode implements Serializable, Fields {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int textLen = 0;
	private static final int MAXLENGTH = 500; // 65535
	private byte[] content = new byte[MAXLENGTH];
	private static final byte START_CODE = 0x00;
	private static final byte END_CODE = 0x00;

	public MessageText(byte[] in) {
		if (START_CODE == in[0] || in[in.length - 1] == END_CODE) {
			textLen = in.length - 2;
			System.arraycopy(in, 0, content, 0, textLen);
		} else {
			// throw analysis exception
		}
	}

	@Override
	public int getFieldsLength() {
		return textLen;
	}

}
