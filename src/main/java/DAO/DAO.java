package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ClassDTO;
import DTO.MemberDTO;
import DTO.TeacherDTO;


public class DAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe","system","system");
		return con;
	}
	
	// 강사리스트 가져오기
	public ArrayList<TeacherDTO> getTeacherList() {
		ArrayList<TeacherDTO> list = new ArrayList();
		TeacherDTO dto = null;
		try {
			Connection conn = getConnection();
			pstmt = conn.prepareStatement("select * from tbl_teacher_202201");
			rs = pstmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					dto = new TeacherDTO();
					dto.setTeacher_code(rs.getString(1));
					dto.setTeacher_name(rs.getString(2));
					dto.setClass_name(rs.getString(3));
					dto.setClass_price(rs.getInt(4));
					dto.setTeacher_regist_date(rs.getString(5));
					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn!=null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return list;
		
	}
	
	// 회원 리스트 가져오기
	public ArrayList<MemberDTO> getMemberList() {
		ArrayList<MemberDTO> list = new ArrayList();
		MemberDTO dto = null;
		try {
			Connection conn = getConnection();
			pstmt = conn.prepareStatement("select regist_month, M.c_no, M.c_name, T.class_name, C.class_area, C.tuition, M.grade  from tbl_member_202201 M  inner join tbl_class_202201 C on c.c_no = m.c_no inner join tbl_teacher_202201 T on T.teacher_code = c.teacher_code");
			rs = pstmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					dto = new MemberDTO();
					dto.setRegist_month(rs.getString(1));
					dto.setC_no(rs.getString(2));
					dto.setC_name(rs.getString(3));
					dto.setClass_name(rs.getString(4));
					dto.setClass_area(rs.getString(5));
					dto.setTuition(rs.getString(6));
					dto.setGrade(rs.getString(7));
					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn!=null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return list;
	}
	
	// 수강신청
	public boolean register(ClassDTO dto) {
		try {
			Connection conn = getConnection();
			pstmt = conn.prepareStatement("insert into tbl_class_202201 values(?,?,?,?,?)");
			pstmt.setString(1, dto.getRegist_month());
			pstmt.setString(2, dto.getC_no());
			pstmt.setString(3, dto.getClass_area());
			pstmt.setString(4, dto.getTuition());
			pstmt.setString(5, dto.getTeacher_code());
			int result = pstmt.executeUpdate();
			if(result>0) {
				System.out.println("insert 성공!");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn!=null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return false;
		
	}
	
	// 매출현황 가져오기
	public ArrayList<TeacherDTO> getTotalTuition() {
		ArrayList<TeacherDTO> list = new ArrayList();
		TeacherDTO dto = null;
		try {
			Connection conn = getConnection();
			pstmt = conn.prepareStatement
					("select T.teacher_code, T.class_name, T.teacher_name ,sum(C.tuition) "
							+ "from tbl_teacher_202201 T ,tbl_class_202201 C "
							+ "where C.teacher_code = T.teacher_code "
							+ "group by T.teacher_code, T.class_name, T.teacher_name");
			rs = pstmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					dto = new TeacherDTO();
					dto.setTeacher_code(rs.getString(1));
					dto.setClass_name(rs.getString(2));
					dto.setTeacher_name(rs.getString(3));
					dto.setTuition(rs.getString(4));
					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			if(conn!=null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return list;
	}
	
	
}
