

import java.sql.Timestamp;

public class Wp_Log {

	private Timestamp proc_time;
	private String msg;
	public Timestamp getProc_time() {
		return proc_time;
	}
	public void setProc_time(Timestamp proc_time) {
		this.proc_time = proc_time;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Wp_Log(Timestamp proc_time, String msg) {
		super();
		this.proc_time = proc_time;
		this.msg = msg;
	}
	public Wp_Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
