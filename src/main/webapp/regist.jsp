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
<section>
<h1>수강신청</h1>
	<form name="frm" action="/register.do" method="post">
		<table>
			<tr>
				<td>수강월</td>
				<td><input type="text" name="regist_month"> 2022년03월 예)202203</td>
			</tr>
			<tr>
				<td>회원명</td>
				<td>
					<select name="c_name" onchange="fn_name();">
						<option value="">회원명</option>
						<option value="10001">홍길동</option>
						<option value="10002">장발장</option>
						<option value="10003">임꺽정</option>
						<option value="20001">성춘향</option>
						<option value="20002">이몽룡</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>회원번호</td>
				<td><input type="text" name="c_no" readonly></td>
			</tr>
			<tr>
				<td>강의장소</td>
				<td>
					<select name="class_area">
						<option value="">강의장소</option>
						<option value="서울본원">서울본원</option>
						<option value="성남분원">성남분원</option>
						<option value="대전분원">대전분원</option>
						<option value="부산분원">부산분원</option>
						<option value="대구분원">대구분원</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>강의명</td>
				<td>
					<select name="teacher_code" onchange="fn_teacher();">
						<option value="">강의신청</option>
						<option value="100">초급반</option>
						<option value="200">중급반</option>
						<option value="300">고급반</option>
						<option value="400">심화반</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>수강료</td>
				<td><input type="text" id="tuition" name="tuition" value="">원</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit" onclick="fn_submit(); return false;">수강신청</button>
					<button type="button" onclick="fn_reset();">다시쓰기</button>
				</td>
			</tr>
		</table>
		<input type="hidden" name="flag" value="true">
	</form>
</section>

<%
	ArrayList<TeacherDTO> list = (ArrayList<TeacherDTO>) request.getAttribute("list");
%>

<script>
	function fn_submit() {
		let frm = document.frm;
		if(frm.regist_month.value=="") {
			alert("수강월을 입력해주세요!");
			frm.regist_month.focus();
			return false;
		}
		if(frm.c_no.value=="") {
			alert('회원명을 선택해주세요!');
			frm.c_no.focus();
			return false;
		}
		if(frm.class_area.value=="") {
			alert('강의장소를 선택해주세요!')
			frm.class_area.focus();
			return false;
		}
		if(frm.teacher_code.value==""){
			alert('강의명을 선택해주세요.');
			frm.teacher_code.focus();
			return false;
		}
		alert('수강신청이 정상적으로 완료되었습니다.');
		frm.submit();
	}
	
	function fn_reset() {
		alert('리셋합니다.');
		location = '/register.do';
	}
	
	function fn_name() {
		let frm = document.frm;
		frm.c_no.value = frm.c_name.value;
		var c_no = frm.c_no.value;
		var tuition = frm.tuition.value;
		if(c_no>=20000) {
			tuition = tuition / 2;
			frm.tuition.value = tuition; 
		} else {
			fn_teacher();
		}
	}
	
	function fn_teacher() {
		let frm = document.frm;
		var teacher_code = frm.teacher_code.value;
		var tuition = 0;
		 
		let hashmap = new Map();
		<%
		for(int i=0; i<list.size(); i++) {
		%>
			hashmap.set('<%=list.get(i).getTeacher_code()%>','<%=list.get(i).getClass_price()%>');
		<% 
		}
		%>
		frm.tuition.value = hashmap.get(teacher_code);
	}
</script>

<footer>카피라이트 어쩌고저쩌고하는 내용</footer>
</body>
</html>