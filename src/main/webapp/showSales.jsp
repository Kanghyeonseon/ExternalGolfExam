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
		<td>강의명</td>
		<td>강사명</td>
		<td>총매출</td>
	</tr>
	<%
		for(int i=0; i<list.size(); i++) {
			String tuitionInit = list.get(i).getTuition();
			String tuition = "￦" + tuitionInit.substring(0,3) + "," + tuitionInit.substring(3,6);
		%>
		<tr>
			<td><%=list.get(i).getTeacher_code() %></td>
			<td><%=list.get(i).getClass_name() %></td>
			<td><%=list.get(i).getTeacher_name() %></td>
			<td><%=tuition %></td>
		</tr>
		<%
		}
	%>

</table>

</section>
<footer>카피라이트 어쩌고저쩌고하는 내용</footer>
</body>
</html>