package login;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mem.model.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/frontend/signin/loginhandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	protected boolean allowUser(String mem_email, String mem_pwd) {
		
		MemService memSvc = new MemService();
	    List<MemVO> list = memSvc.getall();
	    for(MemVO mem : list) {
	    	if(mem_email.equals(mem.getMem_email()) && mem_pwd.equals(mem.getMem_pwd())) {
	    		return true;
	    	}
	    }
		return false;
    }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("Big5");
    res.setContentType("text/html; charset=Big5");
    PrintWriter out = res.getWriter();

    // 【取得使用者 帳號(account) 密碼(password)】
    String mem_email = req.getParameter("mem_email");
    String mem_pwd = req.getParameter("mem_pwd");
    System.out.println(mem_email);
    System.out.println(mem_pwd);
    MemService memSvc = new MemService();
    MemVO memVO = memSvc.login(mem_email,mem_pwd);
    MemService memService = new MemService();

    Integer memNo = memService.getall().stream()
    		.filter(vo -> vo.getMem_email().equals(mem_email))
    		.filter(vo -> vo.getMem_pwd().equals(mem_pwd))
    		.findFirst()
    		.get()
    		.getMem_no();
    
	String memSpecEmail = memService.getall().stream()
    		.filter(vo -> vo.getMem_email().equals(mem_email))
    		.filter(vo -> vo.getMem_pwd().equals(mem_pwd))
    		.findFirst()
    		.get()
    		.getMem_email();
    
    // 【檢查該帳號 , 密碼是否有效】
    if (!allowUser(mem_email, mem_pwd)) {          //【帳號 , 密碼無效時】
      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
      out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/frontend/signin/login.jsp>重新登入</A>");
      out.println("</BODY></HTML>");
    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
      HttpSession session = req.getSession();
      session.setAttribute("memVO", memVO);//*工作1: 才在session內做已經登入過的標識
      session.setAttribute("mem_email", mem_email);
      session.setAttribute("memSpecNo", memNo);
      session.setAttribute("memSpecEmail", memSpecEmail);
       try {                                                        
         String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
           res.sendRedirect(location);            
           return;
         }
       }catch (Exception ignored) { }

      res.sendRedirect(req.getContextPath()+"/frontend/homePage.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
    }
  }
}