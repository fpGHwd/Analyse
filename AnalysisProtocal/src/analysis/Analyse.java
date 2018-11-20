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
	
	public Analyse(byte[] in){
		
	}
	
	@Test
	public static void main(String args[]) throws LengthException, ValueException{
		String test_frame = "7E7E000012345678123430002B020007140121145707F1F1"
				+ "001234567848F0F01401211457201900000526190000053923000002393812115303B078";
		byte[] result = ByteArray.stringToBytes(test_frame);
		UpFrame uf = new UpFrame(result);
		
		byte function = uf.fc.getContent()[0];
		if(function == FunctionCode.TEST_FRAME){
			TestFrame34H tf = new TestFrame34H();
		}else if(function == FunctionCode.CONSTANT_STEP_HYDRO_INFO){
			
		}else if(function == FunctionCode.REMOTE_HOUR_MESSAGE){
			
		}else if(function == FunctionCode.REMOTE_ADD_MESSAGE){
			
		}else if(function == FunctionCode.REMOTE_MANUAL_SET_NUMBER_MESSAGE){
			
		}else{
			// throw analysis exception
		}
	}
	
}
