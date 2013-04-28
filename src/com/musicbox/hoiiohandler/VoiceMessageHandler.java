package com.musicbox.hoiiohandler;

import java.util.logging.Logger;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.ivr.Dial;
import com.hoiio.sdk.services.IvrService;
import com.musicbox.util.AppConstants;
import com.musicbox.util.PropertyUtil;


public class VoiceMessageHandler extends HoiioHandler{

	private static final Logger log = Logger.getLogger(VoiceMessageHandler.class.getName());
	public VoiceMessageHandler() {
		
		try {
			
			appId = PropertyUtil.getPropertyValue(AppConstants.MUSIC_GIFTBOX_CONFIG, AppConstants.APPLICATION_ID);
			accessCode = PropertyUtil.getPropertyValue(AppConstants.MUSIC_GIFTBOX_CONFIG, AppConstants.ACCESS_TOKEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendVoiceMessage(String[] recipient,String content)
			throws HoiioException
	{
		for (String rep : recipient)
		{
			log.info("sending:"+appId+";"+accessCode);
			IvrService ivr = new IvrService(appId, accessCode) ;
			Dial temp = ivr.dial(rep,content, "private", "testMessage", "");
			String out  = temp.getSession();
			log.info("sending ses:"+out);
		}
	}
	
	

}
