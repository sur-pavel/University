<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Info</title>
</head>
<body>
    <jsp:include page="../menu.jsp"></jsp:include>

    <h3>Student ${student.id}</h3>

    First name:
    <b>${student.firstName}</b>
    <br /> Last name:
    <b>${student.lastName}</b>
    <br />
</body>
</html>