<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*, DTO.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/common.css">
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
<header>골프장 회원 관리 프로그램</header>
<%@include file="/resources/nav.jsp"  %>
<%
	ArrayList<TeacherDTO> list = new ArrayList();
	list = (ArrayList<TeacherDTO>) request.getAttribute("list");
%>
<section>
<h1>강사조회</h1>
<table>
	<tr>
		<td>강사코드</td>
		<td>강사명</td>
		<td>강의명</td>
		<td>수강료</td>
		<td>강사자격취득일</td>
	</tr>
	<%
		for(int i=0; i<list.size(); i++) {
			String teacher_code = list.get(i).getTeacher_code();
			String teacher_name = list.get(i).getTeacher_name();
			String class_name = list.get(i).getClass_name();
			String price = list.get(i).getClass_price() + "";
			String class_price ="￦" + price.substring(0,3) + "," + price.substring(3,6);
			String date = list.get(i).getTeacher_regist_date();
			String teacher_regist_date = date.substring(0,4) + "년" + date.substring(4,6) + "월" + date.substring(6,8) + "일";
		%>
		<tr>
			<td><%=teacher_code %></td>
			<td><%=teacher_name %></td>
			<td><%=class_name %></td>
			<td><%=class_price %></td>
			<td><%=teacher_regist_date %></td>
		</tr>
		<%
		}
	%>

</table>

</section>
<footer>카피라이트 어쩌고저쩌고하는 내용</footer>
</body>
</html>