package com.musicbox.action;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MusicBoxSMSServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(MusicBoxSMSServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		String smsContent = req.getParameter("smsContent");
		log.info("content:"+smsContent);
		
		String[] list = req.getParameterValues("p_scnt");
		log.info("size:"+list.length);
		for (String s: list)
		{
			if (s!=null)
			log.info("hp no:"+s);
		}
		
		
		resp.getWriter().println("content:"+smsContent);
	}
}
