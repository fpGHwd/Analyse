package field;

import java.io.Serializable;

import org.junit.Test;

import excptions.LengthException;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:00:20 AM
 * 
 */

public class RemoteStationAddress implements Serializable, Fields {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final byte code = 0x00; // TODO 附录A
	public static final int LENGTH = 5 + 1;
	private static byte[] address = { 0x00, 0x00, 0x00, 0x00, 0x00 };

	public RemoteStationAddress(byte[] in) throws LengthException {
		if (code == in[0])
			System.arraycopy(in, 1, address, 0, in.length - 1);
	}

	@Test
	public void test() throws LengthException {
		byte[] ta = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		new RemoteStationAddress(ta);
	}

	@Override
	public int getFieldsLength() {
		return LENGTH;
	}
}
