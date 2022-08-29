package Controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	HashMap<String, SubController> map;
	
	@Override
	public void init() throws ServletException {
		map = new HashMap();
		map.put("/showTeacher.do", new ShowTeacherController());
		map.put("/register.do", new RegisterController());
		map.put("/showMember.do", new ShowMemberController());
		map.put("/showSales.do", new ShowSalesController());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String url = req.getRequestURI();
		SubController sub = map.get(url);
		sub.execute(req, resp);
	}

	
	
}
