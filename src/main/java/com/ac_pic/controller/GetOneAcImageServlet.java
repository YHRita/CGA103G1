package com.ac_pic.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ac_pic.model.AcPicService;
import com.ac_pic.model.AcPicVO;

@WebServlet("/getOneAcImage")
public class GetOneAcImageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
	@SuppressWarnings("removal")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		resp.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		System.out.println("acCarPage Request -> GetOneAcImageServlet");
		 if("acImgList".equals(action)) {
				AcPicService acPicService = new AcPicService();
				Integer ac_no = new Integer(req.getParameter("acNo").trim());
				List<AcPicVO> list = acPicService.getOneByAcNo(ac_no);
				byte[] imgArray = list.stream().findFirst().get().getAc_pic_img();
				ServletOutputStream out = resp.getOutputStream();
				out.write(imgArray);
				out.close();					

		 	}
	}
}
