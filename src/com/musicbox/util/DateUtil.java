package com.musicbox.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;

public class DateUtil {
	
	private static Log logger = LogFactory.getLog(DateUtil.class);
	public static final String DATE_PATTERN_YYYY = "yyyy";
	public static final String DATE_PATTERN_YYYYMM = "yyyyMM";
	public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_PATTERN_DDMMYYYY_SLASH = "dd/MM/yyyy";
	public static final String DATE_PATTERN_MMYYYY_SLASH = "MM/yyyy";
	public static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_PATTERN_YYYYMMDD_DASH = "yyyy-MM-dd";
	public static final String DATE_PATTERN_DDMMYYYY_DASH = "dd-MM-yyyy";
	public static final String DATE_PATTERN_DDMMMYYYY_DASH = "dd-MMM-yyyy";
	public static final String DATE_PATTERN_MMDDYYYY_SLASH = "MM/dd/yyyy"; 
	
	
	private DateUtil() {
	}
	
	public static DateUtil getInstance(){
		return new DateUtil();
	}
	
	public boolean validate(String chkDate){
		boolean chkSt = true;
		String dates[] = new String[3];
		if(!GenericValidator.isBlankOrNull(chkDate)){
			if(chkDate.length() == 4){
				dates[0] = chkDate;
				chkSt = !chkYr(dates[0]);
			}
			else if(chkDate.length() == 6){
				dates[0] = chkDate.substring(0, 4);
				dates[1] = chkDate.substring(4, chkDate.length());
				chkSt = !(chkYr(dates[0]) || chkMth(dates[1]));
			}
			else if(chkDate.length() == 8){
				dates[0] = chkDate.substring(0, 4);
				dates[1] = chkDate.substring(4, 6);
				dates[2] = chkDate.substring(6, chkDate.length());
				chkSt = !(chkYr(dates[0]) || chkMth(dates[1]) || chkDay(dates[1],dates[2])|| leapYear(chkDate));
			}
			else {
				chkSt = false;
			}
		}
		return chkSt;
	}
	private boolean leapYear(String dateStr){
		int year = Integer.parseInt(dateStr.substring(0,4)); 
		int month = Integer.parseInt(dateStr.substring(4,6));
		int day = Integer.parseInt(dateStr.substring(6,dateStr.length()));
		boolean isLeap = false;
		logger.info("year"+year+"month="+month+"day"+day);
		if (month == 2) { 
			isLeap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
			if (day > 29 || (day==29 && !isLeap)) {
				isLeap=true;
			}else {
				isLeap=false;
			}
			
		} 
		logger.info("error in the date "+isLeap);
		return isLeap;	
	}
	public Date getDate(String date){
		Date retDate = null;
		if(!GenericValidator.isBlankOrNull(date)){
			if(date.length() == 4){
				retDate = formatStringToDate(date,DATE_PATTERN_YYYY);
			}
			if(date.length() == 6){
				retDate	= formatStringToDate(date,DATE_PATTERN_YYYYMM);
			}
			if(date.length() == 8){
				retDate = formatStringToDate(date,DATE_PATTERN_YYYYMMDD);
			}
		}
		return retDate;
	}
	
	public boolean before(String before, String after){
		if(GenericValidator.isBlankOrNull(before) || GenericValidator.isBlankOrNull(after)){
			return false;
		}
		return actualDifference(getDate(before), getDate(after)) > 0;	
	}
	
	public boolean beforeSubscribFile(String before, String after){
		if(GenericValidator.isBlankOrNull(before) || GenericValidator.isBlankOrNull(after)){
			return false;
		}
		return actualDifference(getDate(before), getDate(after)) >= 0;	
	}
	
	public boolean isEarlierthanToday(Date date) {
		if (date == null)
			return false;
        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();
        gc1.setTime(date);
        gc2.setTime(new Date());
        return gc1.before(gc2);
    }
	
	public static boolean isLaterThanToday(Date date) {
		if (date == null)
			return false;

		Date today = formatStringToDate(formatDateToString(new Date(), DATE_PATTERN_DDMMYYYY_SLASH),
				DATE_PATTERN_DDMMYYYY_SLASH);
		return date.after(today);
	}
	
	public static Date getPeriodFrom(Date date, int months){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1 - months;
		if(month == 0){ 
			month = 1;
		}
		if(months < 0){
			year = year - 1;
			month = month + 12;
		}
		month = month - 1;
		cal.set(year, month, 1);
		return cal.getTime();
	}
	
	public static Date getPeriodTo(Date date, int months){
		Date fromDate = getPeriodFrom(date, months);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1 + months;
		if(month > 12){
			year = year + 1;
			month = month - 12;
		}
		month = month - 1;
		cal.set(year, month, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	
	public long actualDifference(Date before, Date after) {
		if (before == null || after == null)
			return 0;
        GregorianCalendar gcBefore = new GregorianCalendar();
        GregorianCalendar gcAfter = new GregorianCalendar();
        gcBefore.setTime(before);
        gcAfter.setTime(after);
        long millies = gcAfter.getTimeInMillis() - gcBefore.getTimeInMillis();
		return millies;
    }
	
	public static boolean isQuarter(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.getCurrentDate());
		int month = calendar.get(Calendar.MONTH) + 1;
		return month == 4 || month == 7 || month == 10 || month == 1;
	}
	
	public static boolean isAnnual(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.getCurrentDate());
		int month = calendar.get(Calendar.MONTH) + 1;
		return month == 10;
	}
	
	private boolean chkYr(String val){
		return Integer.parseInt(val) < 1000 || Integer.parseInt(val) > 2999;
	}
	
	private boolean chkMth(String val){
		return Integer.parseInt(val) < 1 || Integer.parseInt(val) > 12;
	}
	
	private boolean chkDay(String mnth, String day){
		if(mnth.equals("04") || mnth.equals("06") || mnth.equals("09") || mnth.equals("11") ){
			return Integer.parseInt(day) < 1 || Integer.parseInt(day) > 30;
		} else {
			return Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31;
		}
	}
	
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}
	
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(new Date(System.currentTimeMillis()).getTime());
	}
	
	public static String changeFormat(String dateString, String fromFormat, String toFormat ) {
		String retStr = StringUtils.EMPTY;
		if(dateString != null) {
			try {
				SimpleDateFormat sdfFrom = new SimpleDateFormat(fromFormat);
				SimpleDateFormat sdfTo = new SimpleDateFormat(toFormat);
				sdfFrom.setLenient(false);
				sdfTo.setLenient(false);
				retStr = sdfTo.format(sdfFrom.parse(dateString));
			}catch (ParseException e) {
				logger.fatal(DateUtil.class.getName() + " changeFormat ", e);
			}
		}
		return retStr; 
	}

	public static String formatDateToString(Date date, String pattern) {
		String retStr = StringUtils.EMPTY;
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			sdf.setLenient(false);
			retStr = sdf.format(date);
		}
		return retStr;
	}

	public static Date formatStringToDate(String dateStr, String pattern) {		
		Date retDate = null;
		if(!GenericValidator.isBlankOrNull(dateStr)){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				sdf.setLenient(false);
				retDate = sdf.parse(dateStr);
			}
		    catch (ParseException e) {
		    	logger.fatal(DateUtil.class.getName() + " formatStringToDate ", e);
		    }
		}
	    return retDate;
	}
	
	public static Timestamp formatStringToTimestamp(String dateStr, String pattern) {		
		Timestamp retTs = null;
		if(!GenericValidator.isBlankOrNull(dateStr)){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				sdf.setLenient(false);
				retTs = new Timestamp(sdf.parse(dateStr).getTime());
			}
		    catch (ParseException e) {
		    	logger.fatal(DateUtil.class.getName() + " formatStringToTimestamp ",e);
		    }
		}
	    return retTs;
	}
	
	public static String formatDBtoForm(String dateStr){
		if(!GenericValidator.isBlankOrNull(dateStr)){
			if(dateStr.length() == 8){
				dateStr = DateUtil.formatDateToString(DateUtil.formatStringToDate(dateStr,
						DateUtil.DATE_PATTERN_YYYYMMDD), DateUtil.DATE_PATTERN_DDMMYYYY_SLASH);
			}
			else if(dateStr.length() == 6){
				dateStr = DateUtil.formatDateToString(DateUtil.formatStringToDate(dateStr,
						DateUtil.DATE_PATTERN_YYYYMM), DateUtil.DATE_PATTERN_MMYYYY_SLASH);
			}
		}
		return dateStr;
	}
	
	public static String changeDateTOSlashFormat(String date){
		
		String[] date1Split=date.split(" ");
		String dateP1=date1Split[0];
		//logger.info("dateP1 "+dateP1);
		String[] dateP1Split = dateP1.split("-");
		String pedYear=dateP1Split[0];
		String pedMonth=dateP1Split[1];
		String pedDay=dateP1Split[2];
		//logger.info("year "+pedYear+" month "+pedMonth+" day "+pedDay);
		String dateSlashFotmat = pedDay+"/"+pedMonth+"/"+pedYear;
		//logger.info("final format..."+dateSlashFotmat);
		return dateSlashFotmat;
	}
	
	public static String addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        
        String retDate = formatDateToString(cal.getTime(), DateUtil.DATE_PATTERN_DDMMYYYY_SLASH);
        
        //return cal.getTime();
        return retDate;
    }
	
	public static int differenceInMonths(Date fromDate, Date toDate)
    {
        double difference =0;
 
        try{
            Calendar cal1=new GregorianCalendar();
            //Date time1=new Date(calString1);
 
            cal1.setTime(fromDate);
 
            Calendar cal2=new GregorianCalendar();
            //Date time2=new Date(calString2);
            cal2.setTime(toDate);
 
            long time1Millis=cal1.getTimeInMillis();
            long time2Millis= cal2.getTimeInMillis();
 
            double d1=((double)time1Millis)/(1000*60*60*24);
            double d2=((double)time2Millis)/(1000*60*60*24);            
            difference=Math.round(Math.abs((d1-d2)/30));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error Occurred");
        }                   
        return Integer.parseInt(""+difference);
        
    }
	public static String substractMonth(int month ){
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MONTH, -month);
        String retDate = formatDateToString(cal.getTime(), DateUtil.DATE_PATTERN_DDMMYYYY_SLASH);
        return retDate;	    
	}	
}
