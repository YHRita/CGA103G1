package login;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

import javax.servlet.annotation.WebServlet;

@WebServlet("/backend/emp/EmpLogin")
public class EmpLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	protected boolean allowUser(String emp_acc, String emp_pwd) {
		
		EmpService empSvc = new EmpService();
	    List<EmpVO> list = empSvc.getAll();
	    for(EmpVO emp : list) {
	    	if(emp_acc.equals(emp.getEmp_acc()) && emp_pwd.equals(emp.getEmp_pwd())) {
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
    String emp_acc = req.getParameter("emp_acc");
    String emp_pwd = req.getParameter("emp_pwd");
    EmpService emp = new EmpService();
    EmpVO empVO = emp.login(emp_acc, emp_pwd);
    // 【檢查該帳號 , 密碼是否有效】
    if (!allowUser(emp_acc, emp_pwd)) {          //【帳號 , 密碼無效時】
      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
      out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/backend/emp/Emplogin.jsp>重新登入</A>");
      out.println("</BODY></HTML>");
    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
      HttpSession session = req.getSession();
      session.setAttribute("emp_acc", emp_acc);   //*工作1: 才在session內做已經登入過的標識
      session.setAttribute("empVO", empVO);
       try {                                                        
         String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
           res.sendRedirect(location);            
           return;
         }
       }catch (Exception ignored) { }

      res.sendRedirect(req.getContextPath()+"/backend/mem/listMemPage.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
    }
  }
}