<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*, DTO.*, DAO.*" %>
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
	ArrayList<MemberDTO> list = new ArrayList();
	list = (ArrayList<MemberDTO>) request.getAttribute("list");
%>
<section>
<h1>회원정보조회</h1>
<table>
	<tr>
		<td>수강월</td>
		<td>회원번호</td>
		<td>회원명</td>
		<td>강의명</td>
		<td>강의장소</td>
		<td>수강료</td>
		<td>등급</td>
	</tr>
	
	<%
		for(int i=0; i<list.size();i++) {
			String monthInit = list.get(i).getRegist_month();
			String month = monthInit.substring(0,4) + "년" + monthInit.substring(4,6) + "월";
			
			String tuitionInit = list.get(i).getTuition();
			String tuition = "￦" + tuitionInit.substring(0,3) + "," + tuitionInit.substring(3,6);
		%>
		<tr>
			<td><%=month %></td>
			<td><%=list.get(i).getC_no() %></td>
			<td><%=list.get(i).getC_name() %></td>
			<td><%=list.get(i).getClass_name() %></td>
			<td><%=list.get(i).getClass_area() %></td>
			<td><%=tuition %></td>
			<td><%=list.get(i).getGrade() %></td>
		</tr>
		
		<%
		}
	%>


</table>
</section>
<footer>카피라이트 어쩌고저쩌고하는 내용</footer>
</body>
</html>