package field;

import java.io.Serializable;

import excptions.LengthException;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 1:50:00 PM
 * 
 */

public class SerialNumber implements Serializable,Fields {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int LENGTH = 2;
	private int value= 0;
	public SerialNumber(byte[] in) throws LengthException{
		if(in.length == LENGTH)
			setValue(calculate.ByteArray.byteArraytoInt(in));
		else
			throw new LengthException(this.toString());
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public int getLength() {
		return LENGTH;
	}

}
