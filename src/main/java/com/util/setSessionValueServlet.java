package com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/setSessionValue")
public class setSessionValueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json, text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Integer memNo1 = 1;
        Integer memNo2 = 2;
        Integer memNo3 = 3;
        session.setAttribute("memNo1", memNo1);
        session.setAttribute("memNo2", memNo2);
        session.setAttribute("memNo3", memNo3);
        session.setAttribute("memNoEmail1", "Q123434565@gmail.com");
        session.setAttribute("memNoEmail2", "A138775451@gmail.com");
        session.setAttribute("memNoEmail3", "D129242193@gmail.com");
        String memNoEmail1 = (String) session.getAttribute("memNoEmail1");
        String memNoEmail2 = (String) session.getAttribute("memNoEmail2");
        String memNoEmail3 = (String) session.getAttribute("memNoEmail3");
        List<String> memNoEmaiList = new ArrayList<String>();
        memNoEmaiList.add(memNoEmail1);
        memNoEmaiList.add(memNoEmail2);
        memNoEmaiList.add(memNoEmail3);
        Gson gson = new Gson();
        String actNoJson = gson.toJson(memNoEmaiList);
        res.getWriter().write(actNoJson);

	}
}
