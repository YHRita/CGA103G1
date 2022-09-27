package com.act.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.act_rp.model.ActRpService;
import com.act_rp.model.ActRpVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.util.LocalDateTimeDeserializer;
import com.util.LocalDateTimeSerializer;



@WebServlet("/getAllAct")
public class GetAllActServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        ActRpService actRpService = new ActRpService();
        List<ActRpVO> actNoRpList = actRpService.getall();
        ActService actService = new ActService();
        List<ActVO> actList = actService.getAll();
        boolean flag = false;
        for (int i = 0, j = 0; i < actNoRpList.size() && j < actNoRpList.size(); i++, j++) {
        	if (flag) {
				i--;
			}
        	if ((actList.get(i).getAct_no() == actNoRpList.get(j).getAct_no())
        			&& actNoRpList.get(j).getAct_rp_status() == 1) {
        		actList.remove(i);
        		flag = true;
        	}else {
				flag = false;
			}				
		}
		
        GsonBuilder gsonBuilder = new GsonBuilder();  
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());      
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		// Java物件 轉成 JSON 字串
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		String personJsonString = gson.toJson(actList);
		res.getWriter().write(personJsonString);
	}

}
