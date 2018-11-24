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
	private String send_time;
	private String station_type;
	private String F0F0;
	private String identifier;
	private String identifier_num;
	private String receive_time;

	public Factor(String cs, String ts, String fc, Date st, String stype, Date f0f0, String id, String id_n, Date dt) {
		setCenter_station(cs);
		setTelementry_station(ts);
		setFunction_code(fc);
		setSend_time(st);
		setStation_type(stype);
		setF0F0(f0f0);
		setIdentifier(id);
		setIdentifier_num(id_n);
		setReceive_time(dt);
	}

	public Factor(String cs, String ts, String fc, String st, String stype, String f0f0, String id, String id_n,
			Date dt) {
		setCenter_station(cs);
		setTelementry_station(ts);
		setFunction_code(fc);
		send_time = st;
		setStation_type(stype);
		F0F0 = f0f0;
		setIdentifier(id);
		setIdentifier_num(id_n);
		setReceive_time(dt);
	}

	public String getCenter_station() {
		return center_station;
	}

	public void setCenter_station(String center_station) {
		this.center_station = center_station;
	}

	public String getTelementry_station() {
		return telementry_station;
	}

	public void setTelementry_station(String telementry_station) {
		this.telementry_station = telementry_station;
	}

	public String getFunction_code() {
		return function_code;
	}

	public void setFunction_code(String function_code) {
		this.function_code = function_code;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = DateUtils.dateToString(send_time);
	}

	public String getStation_type() {
		return station_type;
	}

	public void setStation_type(String station_type) {
		this.station_type = station_type;
	}

	public String getF0F0() {
		return F0F0;
	}

	public void setF0F0(Date f0f0) {
		F0F0 = DateUtils.dateToString(f0f0);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier_num() {
		return identifier_num;
	}

	public void setIdentifier_num(String identifier_num) {
		this.identifier_num = identifier_num;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(Date receive_time) {
		this.receive_time = DateUtils.dateToString(receive_time);
	}

}
