<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Statistieken' />
</c:import>
</head>
<body>
	<h1>Statistiek</h1>
	<c:forEach var="url" items='${statistiek}'>
	${url.key} : ${url.value} <br>
	</c:forEach>
</body>
</html>