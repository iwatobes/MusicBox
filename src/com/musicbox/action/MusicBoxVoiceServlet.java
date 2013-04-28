package com.musicbox.action;
import java.util.Arrays;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;


import com.musicbox.hoiiohandler.VoiceMessageHandler;
import com.musicbox.util.StringUtils;

@SuppressWarnings("serial")
public class MusicBoxVoiceServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(MusicBoxVoiceServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.sendRedirect("/index.jsp");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		log.info("IP Address of request:"+req.getRemoteAddr());
		String userIpAddress = req.getHeader("X-Forwarded-For");
		if (userIpAddress != null)
			log.info("A proxy may have been used. Here is the Ip from header :"+userIpAddress);
		String voiceContent = req.getParameter("voiceContent");
		log.info("voice content:"+voiceContent);
		
		String[] list = req.getParameterValues("p_vs");
		boolean[] validate = new boolean[list.length];
		if (validate.length>0)
		Arrays.fill(validate, true);  
		boolean allValid = true;
		log.info("size:"+list.length);
		int count = 0;
		for (String s: list)
		{
			if (s!=null)
			{
			log.info("hp no:"+s);
			if (!StringUtils.isValidPhoneNumber(s))
				{
					log.info("invalid hp no:"+s);
					allValid = false;
					validate[count] = false;
				}
			}
			else allValid =false;
			count++;
		}
		if (allValid)
		{
			VoiceMessageHandler handler = new VoiceMessageHandler();
			try {
				handler.sendVoiceMessage(list,voiceContent);
			} catch (Exception e) {
				log.info("sending log:"+e.getMessage());
			}
			req.setAttribute("result", "true");
		}
		else
		{
			req.setAttribute("result", "false");
		}
		req.setAttribute("listhp", list);
		req.setAttribute("listresult", validate);
		 RequestDispatcher rd = getServletContext().getRequestDispatcher("/result.jsp");
		 try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		/*resp.getWriter().println("voice content:"+voiceContent);
		for (String s: list)
		{
			if (s!=null)
				resp.getWriter().println("hp:"+s);
			
		}*/
		

		
	}
	
	
}
