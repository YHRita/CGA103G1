package com.act.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.util.LocalDateTimeDeserializer;
import com.util.LocalDateTimeSerializer;

@WebServlet("/getMemOneAct")
public class GetMemOneActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json, text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        
        HttpSession actSession = req.getSession(); 
        Integer memSpecNo = (Integer) actSession.getAttribute("memSpecNo");
//        Integer memNo1 = (Integer) actSession.getAttribute("memNo1");
//        Integer memNo2 = (Integer) actSession.getAttribute("memNo2");
//        Integer memNo3 = (Integer) actSession.getAttribute("memNo3");
		
        ActService actService = new ActService();
		BufferedReader br = req.getReader();
		GsonBuilder gsonBuilder = new GsonBuilder();  
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());      
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		ActVO actfromFront = gson.fromJson(br, ActVO.class);
		ActVO actVO = new ActVO();
		Stream<ActVO> actStream = actService.getAll().stream();
		if(actStream.anyMatch(act -> act.getAct_no() == actfromFront.getAct_no())) {
			Boolean isActVoExist = actService.getAll().stream()
					.filter(act -> act.getAct_no() == actfromFront.getAct_no())
					.anyMatch(act -> act.getMen_no() == memSpecNo);
			
			if (isActVoExist) {
				actVO = actService.getAll().stream()
						.filter(act -> act.getAct_no() == actfromFront.getAct_no())
						.filter(act -> act.getMen_no() == memSpecNo)
						.findFirst().get();
				String JsonString = gson.toJson(actVO);
				res.getWriter().write(JsonString);
			}else {
				String JsonString = gson.toJson("該活動非您主辦，請查詢編號再次輸入");
				res.getWriter().write(JsonString);
			}
		}else {
			String JsonString = gson.toJson("查無此揪團活動編號");
			res.getWriter().write(JsonString);
		}
		
		
  
	}

}
