package field;

import java.io.Serializable;

import excptions.LengthException;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 11:00:38 AM
 * 
 */

public class Password implements Serializable,Fields {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int LENGTH = 2;
	private byte[] password = new byte[LENGTH];
	
	public Password(byte[] in) throws LengthException{
		if(in.length != LENGTH)
			throw new LengthException(this.toString());
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	@Override
	public int getLength() {
		return LENGTH;
	}

}
