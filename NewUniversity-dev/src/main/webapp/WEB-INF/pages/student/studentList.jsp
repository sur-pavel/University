<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Students</title>
</head>
<body>
    <jsp:include page="../menu.jsp"></jsp:include>
    <h3>Student List</h3>

    <a href="createStudent">Create Student</a>
    <br/>
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td><a href="?id=${student.id}">View</a></td>
                <td><a href="updateStudent?id=${student.id}">Update</a></td>
                <td><a href="deleteStudent?id=${student.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>