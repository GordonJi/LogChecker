

import java.sql.Timestamp;

public class Onl_Perf_Log {

	private Timestamp start_time;
	private Timestamp end_time;
	private String duration;
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Onl_Perf_Log(Timestamp start_time, Timestamp end_time, String duration) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.duration = duration;
	}
	public Onl_Perf_Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
