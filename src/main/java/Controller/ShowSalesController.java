package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import DTO.TeacherDTO;

public class ShowSalesController implements SubController {
	DAO dao = new DAO();
	ArrayList<TeacherDTO> list = new ArrayList();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			list = dao.getTotalTuition();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/showSales.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
