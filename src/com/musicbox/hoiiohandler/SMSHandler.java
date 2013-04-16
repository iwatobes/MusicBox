package com.musicbox.hoiiohandler;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.ivr.Dial;
import com.hoiio.sdk.services.IvrService;
import com.musicbox.util.AppConstants;
import com.musicbox.util.PropertyUtil;


public class SMSHandler extends HoiioHandler{

	
	public SMSHandler() {
		
		try {
			
			appId = PropertyUtil.getPropertyValue(AppConstants.MUSIC_GIFTBOX_CONFIG, AppConstants.APPLICATION_ID);
			accessCode = PropertyUtil.getPropertyValue(AppConstants.MUSIC_GIFTBOX_CONFIG, AppConstants.ACCESS_TOKEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendSMS(String[] recipient,String content)
	{
		
		
	}
	
	public static void main(String args[]) {
		
		String appId,accessCode;
		try {
			
			appId = PropertyUtil.getPropertyValue(AppConstants.MUSIC_GIFTBOX_CONFIG, AppConstants.APPLICATION_ID);
			accessCode = PropertyUtil.getPropertyValue(AppConstants.MUSIC_GIFTBOX_CONFIG, AppConstants.ACCESS_TOKEN);
			System.out.println(appId+";"+accessCode);
			System.out.println("trying to dial ");
			
			IvrService ivr = new IvrService(appId, accessCode) ;
			Dial temp = ivr.dial("+6564172064");
			String out  = temp.getSession();
			
		} 
		catch (HoiioException e) {
			e.printStackTrace();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	     
		
    }

}
