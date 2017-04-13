package com.greatmooc.util;

import java.sql.Timestamp;
import java.util.Date;

public class Time {
	
	public static Timestamp getTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
}
