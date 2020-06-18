<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group Info</title>
</head>
<body>
    <jsp:include page="../menu.jsp"></jsp:include>

    <h3>Group ${group.id}</h3>

    <b>${group.name}</b>
    <br />
    <br /> Students:
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <c:forEach items="${group.students}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>