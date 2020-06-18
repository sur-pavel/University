<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Student</title>
</head>
<body>
    <jsp:include page="../menu.jsp"></jsp:include>

    <h3>Create Student</h3>

    <form method="POST"
        action="${pageContext.request.contextPath}/createStudent">
        <table border="0">
            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstName" /></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastName" /></td>
            </tr>
            <tr>
                <td>Group Name</td>
                <td><select name="groupId">
                        <option selected value="groupId"></option>
                        <c:forEach var="group" items="${groups}">
                            <option value="${group.id}">${group.name}</option>
                        </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Submit" />
                    <a href="students">Cancel</a></td>
            </tr>
        </table>
    </form>

</body>
</body>
</html>