package com.ac_pic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ac.model.AcServiceImpl;
import com.ac.model.AcVO;
import com.ac_pic.model.AcPicService;
import com.ac_pic.model.AcPicVO;

@WebServlet("/uploadAcImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UploadAcImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        res.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    res.setCharacterEncoding("UTF-8");
	    System.out.println("Fetch -> UploadAcImageServlet");
	    
	    InputStream is = req.getInputStream();
		byte[] image = org.apache.commons.io.IOUtils.toByteArray(is);
		
		HttpSession session = req.getSession();
		Integer acNo = (Integer) session.getAttribute("afterCreateAcNo");
		
	    AcPicVO acPicVO = new AcPicVO();
	    acPicVO.setAc_pic_img(image);
	    acPicVO.setAc_no(acNo);	 
		AcPicService acPicService =  new AcPicService();
		acPicService.insertImage(acPicVO);
	}

}
