package Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import DTO.TeacherDTO;

public class ShowTeacherController implements SubController {
	ArrayList<TeacherDTO> list = new ArrayList();
	DAO dao = new DAO();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			list = dao.getTeacherList();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/showTeacher.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
