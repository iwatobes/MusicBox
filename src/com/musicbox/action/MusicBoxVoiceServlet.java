package com.musicbox.action;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.http.*;


import com.musicbox.hoiiohandler.VoiceMessageHandler;

@SuppressWarnings("serial")
public class MusicBoxVoiceServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(MusicBoxVoiceServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		String voiceContent = req.getParameter("voiceContent");
		log.info("voice content:"+voiceContent);
		
		String[] list = req.getParameterValues("p_scnt");
		log.info("size:"+list.length);
		for (String s: list)
		{
			if (s!=null)
			log.info("hp no:"+s);
			
		}
		
		VoiceMessageHandler handler = new VoiceMessageHandler();
		try {
			handler.sendVoiceMessage(list,voiceContent);
		} catch (Exception e) {
			log.info("sending log:"+e.getMessage());
		}
		resp.getWriter().println("content:"+voiceContent);
	}
}
