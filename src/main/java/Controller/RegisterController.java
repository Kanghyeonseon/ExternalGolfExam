package Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import DTO.ClassDTO;
import DTO.TeacherDTO;

public class RegisterController implements SubController{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String flag = req.getParameter("flag");
			DAO dao = new DAO();
			ClassDTO dto = new ClassDTO();
			if(flag==null) {
				ArrayList<TeacherDTO> list = new ArrayList();
				list = dao.getTeacherList();
				req.setAttribute("list", list);
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			} else {
				
				String regist_month = req.getParameter("regist_month");
				String c_no = req.getParameter("c_no");
				String class_area = req.getParameter("class_area");
				String teacher_code = req.getParameter("teacher_code");
				String tuition = req.getParameter("tuition");
				
				dto.setRegist_month(regist_month);
				dto.setC_no(c_no);
				dto.setClass_area(class_area);
				dto.setTeacher_code(teacher_code);
				dto.setTuition(tuition);
				
				dao.register(dto);
				
				
				
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
