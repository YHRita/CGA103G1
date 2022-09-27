package com.act_pic.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act_pic.model.ActPicService;
import com.act_pic.model.ActPicVO;

@WebServlet("/getOneActPic")
public class GetOneActPicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("removal")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg, image/jpg, image/png, */*");
		String action = req.getParameter("action");	
		
		if("actImg".equals(action)) {
			ActPicService actPicService = new ActPicService();
			Integer act_no = new Integer(req.getParameter("actNo").trim());
			List<ActPicVO> list = actPicService.getOneActPic(act_no);
			byte[] actImg = list.stream().findFirst().get().getAct_pic();
			ServletOutputStream sos = res.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(sos);
			bos.write(actImg);
			bos.close();
	 	}
	}

}
