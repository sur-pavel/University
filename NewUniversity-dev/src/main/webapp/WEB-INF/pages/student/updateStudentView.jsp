<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Student</title>
</head>
<body>
    <jsp:include page="../menu.jsp"></jsp:include>

    <h3>Update Student</h3>

    <form method="POST"
        action="${pageContext.request.contextPath}/updateStudent">
        <input type="hidden" name="id" value="${student.id}" />
        <table border="0">
            <tr>
                <td>ID</td>
                <td>${student.id}</td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstName"
                    value="${student.firstName}" /></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastName"
                    value="${student.lastName}" /></td>
            </tr>
            <tr>
                <td>Group Name</td>
                <td><select name="groupId">
                        <option selected value="${student.group.id}">${student.group.name}</option>
                        <c:forEach var="group" items="${groups}">
                            <option value="${group.id}">${group.name}</option>
                        </c:forEach>
                </select></td>
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