package Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import DTO.MemberDTO;

public class ShowMemberController implements SubController {
	DAO dao = new DAO();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ArrayList<MemberDTO> list = new ArrayList();
			list = dao.getMemberList();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/showMember.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
