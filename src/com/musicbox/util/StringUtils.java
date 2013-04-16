package com.musicbox.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;


public class StringUtils extends org.apache.commons.lang.StringUtils {
	
	private static Log logger = LogFactory.getLog(StringUtils.class);
	/**
     * This converts a name from remove_and_hump to removeAndHump
     * @param data
     * @return
     */
	public static String removeAndHump(String data)
	{
		boolean hump = false;
		StringBuffer buffer = new StringBuffer();
		for(int x = 0; x < data.length(); x++)
		{
			if(data.charAt(x) == '_')
			{
				hump = true;
			}
			else if(hump)
			{
				buffer.append(Character.toUpperCase(data.charAt(x)));
				hump = false;
			}
			else
			{
				buffer.append(data.charAt(x));
			}
		}
		return buffer.toString();
	}
	
	/**
	 * This converts a name from removeHump to remove_hump
	 * @param data
	 * @return
	 */
	public static String removeHump(String data)
	{
		StringBuffer buffer = new StringBuffer();
		for(int x = 0; x < data.length(); x++)
		{
			char current = data.charAt(x);
			
			if(Character.isUpperCase(current) && x != 0)
			{
				buffer.append(Character.toLowerCase(current));
				buffer.append("_");
			}
			else
			{
				if(x == 0) current = Character.toLowerCase(current); 
				buffer.append(current);
			}
		}
		return buffer.toString();
	}
	
	/**
	 * Returns the String for making a SQL SELECT IN clause.
	 * eg: SELECT * FROM table WHERE pk IN (?,?,?,?)
	 * 
	 * @param param
	 * @return
	 */
	public static String getParameters(int numParams)
	{
		String questionMarks[] = new String[numParams];
		for(int x = 0; x < questionMarks.length; x++)
		{
			questionMarks[x] = "?";
		}
		
		return join(questionMarks);
	}
	
	/**
	 * Returns the String for making a SQL SELECT IN clause.
	 * eg: SELECT * FROM table WHERE pk IN (?,?,?,?)
	 * 
	 * @param param
	 * @return
	 */
	public static String getParameters(Object param[])
	{
		return getParameters(param.length);
	}
	
	/**
	 * Returns the String for making a SQL SELECT IN clause.
	 * eg: SELECT * FROM table WHERE pk IN (?,?,?,?)
	 * 
	 * @param param
	 * @return
	 */
	public static String getParameters(Collection param)
	{
		return getParameters(param.size());
	}
	
	/**
	 * Convert string into integer and return the total sum.
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int addValue(String str1, String str2) {
		int x=0;
		int y=0;
		
		if (str1 != null)
			x = new Integer(str1).intValue();
		if (str2 != null)
			y = new Integer(str2).intValue();
		
		return x+y;
	}
	
	
	public static boolean contains(String[] strList, String searchStr) {
		for (int x=0; x< strList.length; x++) {
			if (strList[x].equals(searchStr))
				return true;
		}
		return false;
	}
	
	public static boolean contains(List strList, String searchStr) {
		for (int x=0; x< strList.size(); x++) {
			if (strList.get(x) instanceof String) {
				if (strList.get(x).equals(searchStr))
					return true;
			} else {
				break;
			}
		}
		return false;
	}
	
	public static String addDecimalPoint(String str, int intLength, int fracLength) {
		String result = null;
		
		if (isNotBlank(str))
			result = new String(str);
		
		if (isNotBlank(result) && (result.length() == intLength + fracLength))
			result = str.substring(0, intLength) + "." + str.substring(intLength);
		
		return result;		
	}
	
	public static double parseDouble(String s)
    {
        double d = 0.0;
        if(!GenericValidator.isBlankOrNull(s))
            try
            {
                d = Double.parseDouble(s);
            }
            catch(NumberFormatException ex)
            {
            	logger.fatal((new StringBuilder()).append(StringUtils.class.getName() + " NumberFormatException parseDouble "), ex);
            }
        return d;
    }
	
	public static boolean validateDecimalPos(String s, int len, int pos)
	{
		boolean validate = true;
		if(GenericValidator.isBlankOrNull(s) || len < pos) return validate;
		if(s.contains(".")){
			String left = s.substring(0, s.indexOf("."));
			validate = left.length() <= len - pos;
			String right = s.substring(s.indexOf(".")+1, s.length());
			validate = validate && right.length() <= pos;
		}else{
			validate = s.length() <= len - pos;
		}
		return validate;
	}

        public static boolean validateNumeric(String val){
		try {
            Integer.parseInt(val);
            return true;
        } catch (Exception e) {
            return false;
        }
	}
	public static double parseDouble(String value, int integerLen, int fractionalLen)
			throws Exception {
		double dValue = 0.0;

		if (StringUtils.isBlank(value)) {
			dValue = 0.0;
		}
		else {
			value = addDecimalPoint(value, integerLen, fractionalLen);
			try {
				dValue = Double.parseDouble(value);
			}
			catch (NumberFormatException e) {
				throw e;
			}
		}

		return dValue;
	}

    public static long parseLong(String s)
    {
        long l = 0;
        if(!GenericValidator.isBlankOrNull(s))
            try
            {
                l = Long.parseLong(s);
            }
            catch(NumberFormatException ex)
            {
            	logger.fatal((new StringBuilder()).append(StringUtils.class.getName() + " NumberFormatException parseLong "), ex);
            }
        return l;
    }
    
    public static void upperCaseTrim(Object obj){
		try {
			 BeanInfo bi = Introspector.getBeanInfo(obj.getClass());
		     MethodDescriptor[] mds = bi.getMethodDescriptors();
		     Class[] parameterTypes = new Class[] {String.class};
		     Field field = null;
		     String name = StringUtils.EMPTY;
		     String setterName = StringUtils.EMPTY;
		     Method getter = null;
		     Method setter = null;
		     Class[] setTypes = null;
		     Class setType = null;
		     String value = StringUtils.EMPTY;
		     for (int a = 0; a < mds.length; a++) {
		    	 name = mds[a].getName();		    	 
		         String param = name.substring(3, name.length());
		         setterName = "set" + param;
		         if (name.startsWith("get")){
		        	 try {
			        	 getter = mds[a].getMethod();
				    	 setter = obj.getClass().getMethod(setterName, parameterTypes);
				    	 setTypes = setter.getParameterTypes();
				    	 if (setTypes != null && setTypes.length == 1) setType = setTypes[0];
				    	 if (setType != null && setType.getName().equalsIgnoreCase("java.lang.String")){
				    		 value = (String) getter.invoke(obj, null);
				    		 if(value != null){
				    			 value = value.toUpperCase().trim();
				    			 setter.invoke(obj, new Object[] {value});
				    		 }
				    	 }
		        	 }
		             catch (Exception ex) {}
		         }
		     }
		}
		catch (Exception ex) {}
    }
}
