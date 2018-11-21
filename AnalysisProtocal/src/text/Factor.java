package text;

import java.util.Date;

/**
 * @author Suzumiya
 * @version Nov 21, 2018 2:41:34 PM
 * 
 */

public class Factor {
	private String center_station;
	private String telementry_station;
	private String function_code;
	private Date send_time;
	private String station_type;
	private Date F0F0;
	private String identifier;
	private String identifier_num;
	private Date receive_time;

	public Factor(String cs, String ts, String fc, Date st, 
			String stype,Date f0f0, String id, String id_n, Date dt) {
		center_station = cs;
		telementry_station = ts;
		function_code = fc;
		send_time = st;
		station_type = stype;
		F0F0 = f0f0;
		identifier = id;
		identifier_num = id_n;
		receive_time = dt;
	}

}
