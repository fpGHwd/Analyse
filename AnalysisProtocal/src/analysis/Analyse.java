package analysis;

import java.io.Serializable;

import org.junit.Test;

import MessageText.TestFrame34H;
import calculate.ByteArray;
import excptions.LengthException;
import excptions.ValueException;
import field.FunctionCode;
import frames.UpFrame;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 4:24:53 PM
 * 
 */

public class Analyse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Analyse(byte[] in) {

	}

	@Test
	public void test() {
		System.out.println("OK");
	}

	@Test
	public static void main(String args[]) throws LengthException, ValueException {
		String test_frame = "7E7E000012345678123430002B020007140121145707F1F1"
				+ "001234567848F0F01401211457201900000526190000053923000002393812115303B078";
		byte[] result = ByteArray.stringToBytes(test_frame);
		byte[] test = {0x7E, 0x7E, 0x00,0x00, 0x12, 0x34,0x56,0x78,0x12,0x34,0x30,0x00
			,0x2B, 0x02, 0x00,0x07,0x14, 0x01, 0x21,0x14,0x57,0x07,(byte) 0xF1,(byte) 0xF1,
				0x00,0x12,0x34,0x56,0x78,0x48,(byte) 0xF0,(byte) 0xF0,0x14,0x01,0x21,0x14,0x57
				,0x20,0x19,0x00,0x00,0x05,0x26,0x19,0x00,0x00,0x05,0x39,0x23
				,0x00,0x00,0x02,0x39,0x38,0x12,0x11,0x53,0x03,(byte) 0xB0,0x78};
		UpFrame uf = new UpFrame(result);

		byte fc = uf.fc.getContent()[0];

		switch (fc) {
		case FunctionCode.TEST_FRAME:
		case FunctionCode.CONSTANT_STEP_HYDRO_INFO:
		case FunctionCode.REMOTE_HOUR_MESSAGE:
		case FunctionCode.REMOTE_ADD_MESSAGE:
		case FunctionCode.REMOTE_MANUAL_SET_NUMBER_MESSAGE:
			;
		default:
			break;
		}
	}

}
