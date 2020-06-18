<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chairs</title>
</head>
<body>
    <jsp:include page="../menu.jsp"></jsp:include>
    <h3>Chair List</h3>

    <a href="createChair">Create Chair</a>
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
        </tr>
        <c:forEach items="${chairs}" var="chair">
            <tr>
                <td>${chair.id}</td>
                <td>${chair.name}</td>
                <td><a href="?id=${chair.id}">View</a></td>
                <td><a href="updateChair?id=${chair.id}">Update</a></td>
                <td><a href="deleteChair?id=${chair.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>