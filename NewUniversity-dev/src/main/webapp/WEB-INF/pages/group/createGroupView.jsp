<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create group</title>
</head>
<body>
    <jsp:include page="../menu.jsp"></jsp:include>

    <h3>Create Group</h3>

    <form method="POST" action="${pageContext.request.contextPath}/createGroup">
        <table border="0">
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>Chair Name</td>
                <td><select name="chairId">
                        <option selected value="chairId"></option>
                        <c:forEach var="chair" items="${chairs}">
                            <option value="${chair.id}">${chair.name}</option>
                        </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Submit" /> <a href="groups">Cancel</a></td>
            </tr>
        </table>
    </form>

</body>
</body>
</html>