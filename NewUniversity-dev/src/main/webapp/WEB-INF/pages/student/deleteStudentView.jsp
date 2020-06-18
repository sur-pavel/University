<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Student</title>
</head>
<body>
    <jsp:include page="../menu.jsp"></jsp:include>

    <h3>Delete Student</h3>

    <form method="POST"
        action="${pageContext.request.contextPath}/deleteStudent">
        <input type="hidden" name="id" value="${student.id}" /> <input
            type="hidden" name="firstName" value="${student.firstName}" />
        <input type="hidden" name="lastName" value="${student.lastName}" />
        <table border="0">
            <tr>
                <td>ID: </td>
                <td><b>${student.id}</b></td>
            </tr>
            <tr>
                <td>First Name: </td>
                <td><b>${student.firstName}</b></td>
            </tr>
            <tr>
                <td>Last Name: </td>
                <td><b>${student.lastName}</b></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Submit" />
                    <a
                    href="${pageContext.request.contextPath}/students">Cancel</a>
                </td>
            </tr>
        </table>
    </form>


</body>
</html>