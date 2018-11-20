package frames;

import java.io.Serializable;

import common.ControlCode;
import excptions.LengthException;
import excptions.ValueException;
import field.CRCCode;
import field.CenterStationAddress;
import field.FrameOrientationLength;
import field.FrameStartCode;
import field.FunctionCode;
import field.MessagePackageSumNumber;
import field.MessageText;
import field.Password;
import field.RemoteStationAddress;

/**
 * @author Suzumiya
 * @version Nov 20, 2018 10:26:00 AM
 * 
 */

public class UpFrame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FrameStartCode fsc;
	private CenterStationAddress csa;
	private RemoteStationAddress rsa;
	private Password psw;
	public FunctionCode fc;
	private FrameOrientationLength fo;
	// private MessagePackageSumNumber mpsn;
	private MessageText mt;
	private CRCCode cc;

	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		System.arraycopy(src, begin, bs, 0, count);
		return bs;
	}

	public UpFrame(byte[] in) throws ValueException, LengthException {
		int pos = 0;
		fsc = new FrameStartCode(subBytes(in, pos, fsc.LENGTH));
		pos += fsc.LENGTH;
		csa = new CenterStationAddress(subBytes(in, pos, csa.LENGTH));
		pos += csa.LENGTH;
		rsa = new RemoteStationAddress(subBytes(in, pos, rsa.LENGTH));
		pos += rsa.LENGTH;
		psw = new Password(subBytes(in, pos, psw.LENGTH));
		pos += psw.LENGTH;
		fc = new FunctionCode(subBytes(in, pos, fc.LENGTH));
		pos += fc.LENGTH;
		fo = new FrameOrientationLength(subBytes(in, pos, fo.LENGTH));
		pos += fo.LENGTH;
		int tempLength = in.length - pos  - cc.LENGTH;
		mt = new MessageText(subBytes(in, pos, tempLength));
		pos += tempLength;
		cc = new CRCCode(subBytes(in, pos, cc.LENGTH));
		pos += cc.LENGTH;
		
		
	}

}
