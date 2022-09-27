package com.ac.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ac.model.AcServiceImpl;
import com.ac.model.AcVO;
import com.ac_pic.model.AcPicService;
import com.ac_pic.model.AcPicVO;
import com.mem.model.MemService;



@WebServlet("/getOneAc")
public class GetOneAcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Integer memNo1 = (Integer) session.getAttribute("memNo1");
		AcServiceImpl acServiceImpl = new AcServiceImpl();
		AcVO acVO = new AcVO();
		AcPicService acPicService = new AcPicService();
//		MemService memService = new MemService();
		List<AcPicVO> acPicVO = new LinkedList<AcPicVO>();
//		Boolean isMemAc = acServiceImpl.getAll().stream().anyMatch(ac -> ac.getMem_no() == memNo1);
//		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//		request.setAttribute("errorMsgs", errorMsgs);

		if("acInner".equals(action)) {
			try {
				Integer acNo = new Integer(request.getParameter("acNo"));
				acVO = acServiceImpl.getOneAcById(acNo);
				acPicVO = acPicService.getOneByAcNo(acNo);
				byte[] acImage = acPicVO.stream().findFirst().get().getAc_pic_img();
				request.setAttribute("acVO", acVO);
				request.setAttribute("acImage", acImage);
				request.getRequestDispatcher("/frontend/ac/acDetailPage.jsp")
				.forward(request, response);
				return;
			}catch(Exception ex) {
				request.getRequestDispatcher("/frontend/ac/acCardPage.jsp")
				.forward(request, response);
				return;
			}
		}
	}
}
