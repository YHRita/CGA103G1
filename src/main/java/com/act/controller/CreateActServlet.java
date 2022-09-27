package com.act.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ac.model.AcServiceImpl;
import com.ac.model.AcVO;
import com.ac_pic.model.AcPicVO;
import com.act.model.ActService;
import com.act.model.ActVO;
import com.act_participant.model.ActParticipantService;
import com.act_participant.model.ActParticipantVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.util.LocalDateTimeDeserializer;
import com.util.LocalDateTimeSerializer;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher;

@WebServlet("/createAct")
public class CreateActServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
	    req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json, text/html; charset=UTF-8");
	    res.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession();
        
        Integer memSpecNo = (Integer) session.getAttribute("memSpecNo");
//        Integer memNo1 = (Integer) session.getAttribute("memNo1");
//        Integer memNo2 = (Integer) session.getAttribute("memNo2");
//        Integer memNo3 = (Integer) session.getAttribute("memNo3");

        ActService actService = new ActService();
		BufferedReader br = req.getReader();
		
		Gson gson = new Gson();
		GsonBuilder gsonBuilder = new GsonBuilder();  
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());      
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();
        ActVO actVO = gson.fromJson(br, ActVO.class);
        
        actVO.setMen_no(memSpecNo);
        actVO.setAct_status(0);
        actVO.setAct_rate_sum(0);
        actVO.setEval_sum(0);
        actVO.setAct_current_count(1);

        // 創建活動，回傳MySQL AI 編號
        Integer actNo = actService.createAct(actVO);
        // 設定活動編號參數，給UploadActImageServlet使用
        session.setAttribute("afterInsertActNO", actNo);
        
        // 創建活動同時，亦是活動參與者 +1
        ActParticipantService actParticipantService = new ActParticipantService();
	    LocalDateTime currentTime = LocalDateTime.now();
	    ActParticipantVO actParticipantVO = new ActParticipantVO();
	    actParticipantVO.setEnroll_status(0);
	    actParticipantService.addActParticipant(actNo, memSpecNo, currentTime);
        
	    // 回傳至Browser 確認創建成功及資料
	    String json = gson.toJson(actVO);
	    PrintWriter pw = res.getWriter();
	    pw.print(json);
  
	}

}
