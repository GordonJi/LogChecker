import java.sql.Timestamp;

public class Work_Log {

	private Timestamp proc_time;
	private String action, io_cd, usr_id;
	public Timestamp getProc_time() {
		return proc_time;
	}
	public void setProc_time(Timestamp proc_time) {
		this.proc_time = proc_time;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getIo_cd() {
		return io_cd;
	}
	public void setIo_cd(String io_cd) {
		this.io_cd = io_cd;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public Work_Log(Timestamp proc_time, String action, String io_cd, String usr_id) {
		super();
		this.proc_time = proc_time;
		this.action = action;
		this.io_cd = io_cd;
		this.usr_id = usr_id;
	}
	public Work_Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
