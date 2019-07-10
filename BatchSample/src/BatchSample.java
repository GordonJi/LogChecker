/**
 * 1. import java.sql
 * 2. load and register the driver -->
 * 
 * 
 * */

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
		ArrayList<Timestamp> procTimeList = new ArrayList<>();
		try {
			Class.forName(Driver);		
			conn = DriverManager.getConnection(url,USER,pass);
			PreparedStatement st = conn.prepareStatement(selectSql);
			ResultSet rs =  st.executeQuery();
			
			
			while (rs.next()) {
				procTimeList.add(rs.getTimestamp(1));
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}	
		
		timeTaken(procTimeList.get(0), procTimeList.get(1));
		
		ArrayList<Work_Log> aList = new ArrayList<Work_Log>();
		
		for (int i = 0 ; i < 10 ; i++) {
			aList.add(new Work_Log(Timestamp.valueOf("2019-03-02 22:33:22.333"), "22222", "33333", "wsde", "feed", "3333"));
		}
		
		HashMap<String, Object> multiMap = setPerfLogParam(aList);
		
		ArrayList<Work_Log> a = (ArrayList<Work_Log>) multiMap.get("WorkList");
		
		ArrayList<String> b = (ArrayList<String>) multiMap.get("ssnList");
		
		
		for (Work_Log w : a) {
			System.out.println(w.toString());	
		}
		
		for (String s : b) {
			System.out.println(s);
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
		wLog.setAction(wColList.get(0).substring(wColList.get(0).indexOf(":") +1));
		wLog.setIo_cd(wColList.get(1).substring(wColList.get(1).indexOf(":") +1));
		wLog.setUsr_id(wColList.get(2).substring(wColList.get(2).indexOf(":") +1));
		
		wLog.setProcess_id(wColList.get(3).substring(wColList.get(3).indexOf(":") +1, wColList.get(3).indexOf(" ")));
		wLog.setPower(wColList.get(3).substring(wColList.get(3).lastIndexOf(":")+1));
		
		return wLog;
	}
	
	
	public static String timeTaken(Timestamp startStamp, Timestamp endStamp) {
		
		long diff =  endStamp.getTime() - startStamp.getTime();
		
		long diffDays = diff / (24*60*60*1000);
		long diffHours = diff / (60*60*1000);
		long diffMins =  diff / (60*1000);		
		long sec = diff/1000;
		System.out.println("millistake : " + diff);
		System.out.println("sectaken : " + sec);
		System.out.println("minstaken : " + diffMins);
		System.out.println("hourstaken : " + diffHours);
		System.out.println("daystaken : " + diffDays);
		
		Date d = new Date(sec);
		
		Calendar cal = Calendar.getInstance();		
		cal.setTimeInMillis(diff);	
		System.out.println(cal.getTime());
		
		return "";
	}
		
	public static HashMap<String, Object> setPerfLogParam(ArrayList<Work_Log> list){
		
		HashMap<String, Object> map = new HashMap<>();
		
		
		
		ArrayList<Work_Log> perfLogList = new ArrayList<>();				
		ArrayList<String> sessionList = new ArrayList<>();

		
		for (Work_Log wLog : list) {
			perfLogList.add(wLog);
			sessionList.add(wLog.getProcess_id());
		}

		map.put("WorkList", perfLogList);
		map.put("ssnList", sessionList);
		
		return map;
	}
	
	
	/**
	 *  JAVA BATCH STRUCTURE 
	 *   package com.mkyong.jdbc.preparestatement;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class BatchUpdate {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/test", "postgres", "password");
             PreparedStatement psDDL = conn.prepareStatement(SQL_CREATE);
             PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT);
             PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE)) {

            // commit all or rollback all, if any errors
            conn.setAutoCommit(false); // default true

            psDDL.execute();

            // Run list of insert commands
            psInsert.setString(1, "mkyong");
            psInsert.setBigDecimal(2, new BigDecimal(10));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.addBatch();

            psInsert.setString(1, "kungfu");
            psInsert.setBigDecimal(2, new BigDecimal(20));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.addBatch();

            psInsert.setString(1, "james");
            psInsert.setBigDecimal(2, new BigDecimal(30));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.addBatch();

            int[] rows = psInsert.executeBatch();

            System.out.println(Arrays.toString(rows));

            // Run list of update commands
            psUpdate.setBigDecimal(1, new BigDecimal(999.99));
            psUpdate.setString(2, "mkyong");
            psUpdate.addBatch();

            psUpdate.setBigDecimal(1, new BigDecimal(888.88));
            psUpdate.setString(2, "james");
            psUpdate.addBatch();

            int[] rows2 = psUpdate.executeBatch();

            System.out.println(Arrays.toString(rows2));

            conn.commit();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static final String SQL_INSERT = "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) VALUES (?,?,?)";

    private static final String SQL_UPDATE = "UPDATE EMPLOYEE SET SALARY=? WHERE NAME=?";

    private static final String SQL_CREATE = "CREATE TABLE EMPLOYEE"
            + "("
            + " ID serial,"
            + " NAME varchar(100) NOT NULL,"
            + " SALARY numeric(15, 2) NOT NULL,"
            + " CREATED_DATE timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,"
            + " PRIMARY KEY (ID)"
            + ")";

}


	 * 
	 * 
	 * 
	 * */
	
	
	
}
