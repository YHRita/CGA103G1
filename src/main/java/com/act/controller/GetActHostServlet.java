package com.act.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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

@WebServlet("/getActHost")
public class GetActHostServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession();
        Integer memSpecNo = (Integer) session.getAttribute("memSpecNo");
//        Integer memNo1 = (Integer) session.getAttribute("memNo1");
//        Integer memNo2 = (Integer) session.getAttribute("memNo2");
//        Integer memNo3 = (Integer) session.getAttribute("memNo3");
        ActService actService = new ActService();
		List<ActVO> actList = actService.getHostAct(memSpecNo);		
        GsonBuilder gsonBuilder = new GsonBuilder();  
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());      
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		// Java物件 轉成 JSON 字串
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		if(actList.size() != 0) {			
			String personJsonString = gson.toJson(actList);
			res.getWriter().write(personJsonString);
		}else {
			String personJsonString = gson.toJson("您目前無任何主辦活動");
			res.getWriter().write(personJsonString);
		}
	}

}
