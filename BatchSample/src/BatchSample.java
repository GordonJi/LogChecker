/**
 * 1. import java.sql
 * 2. load and register the driver -->
 * 
 * 
 * */

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLBoundPortType;

import sun.util.logging.resources.logging;

import java.awt.List;
import java.math.*;

public class BatchSample {

	public static void main(String[] args) {
		
		String Driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String USER = "ji";
		String pass = "1234";
		
		String selectSql = "select * from wp_log";
		
		Logger logger = Logger.getLogger(BatchSample.class.getName());
		
		Connection conn = null;
		try {
			Class.forName(Driver);		
			conn = DriverManager.getConnection(url,USER,pass);
			PreparedStatement st = conn.prepareStatement(selectSql);
			ResultSet rs =  st.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getTimestamp(1));
				System.out.println(editType(rs.getString(2)).getAction());
				System.out.println(editType(rs.getString(2)).getIo_cd());
				System.out.println(editType(rs.getString(2)).getUsr_id());
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}	
	}
	
	public static Work_Log editType(String message) {
		
		// generate the instance of Work_Log
		Work_Log wLog = new Work_Log();
		ArrayList<String> wColList = new ArrayList<>();
		
		
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(message);
		while(m.find()){
//			String[] msgString = m.group(1).split("\\n");
//			wLog.setAction(msgString[0]);
//			wLog.setIo_cd(msgString[1]);
//			wLog.setUsr_id(msgString[2]);	
			wColList.add(m.group(1));
		}
	/*	String[] msgString = message.split("(?+)");
		for (String string : msgString) {
			System.out.println(string);
		}
		wLog.setAction(msgString[0]);
		wLog.setIo_cd(msgString[1]);
		wLog.setUsr_id(msgString[2]);*/
		wLog.setAction(wColList.get(0));
		wLog.setIo_cd(wColList.get(1));
		wLog.setUsr_id(wColList.get(2));
		return wLog;
	}
}
