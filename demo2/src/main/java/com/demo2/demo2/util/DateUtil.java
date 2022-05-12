package com.demo2.demo2.util;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    public static final SimpleDateFormat responseDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static String responseDateTime(Date date){
		return responseDateTimeFormat.format(date);
	}
	
	public static String responseDateTime(Timestamp timestamp){
		return responseDateTimeFormat.format(timestamp);
	}
}
