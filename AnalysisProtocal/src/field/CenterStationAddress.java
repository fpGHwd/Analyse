package field;
import java.io.Serializable;

import calculate.ByteArray;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 10:59:32 AM
 * 
 */

public class CenterStationAddress implements Serializable,Fields {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int LENGTH = 1;
	private byte[] address = {0x00};
	
	public CenterStationAddress(byte[] in){
		System.arraycopy(in, 0, address, 0, LENGTH);
	}
	
	
	public boolean validateAddressRange(){
		int addressValue = ByteArray.byteArraytoInt(address);
		if(addressValue >=1 && addressValue <=255){
			return true;
		}
		return false;
	}


	@Override
	public int getFieldsLength() {
		return LENGTH;
	}

}
