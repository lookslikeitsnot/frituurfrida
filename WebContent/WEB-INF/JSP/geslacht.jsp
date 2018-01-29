<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Geslacht' />
</c:import>
<style>
<c:if test="${gebruikersgeslacht =='meisjes'}">
body {background-color: pink;}</c:if>
<c:if test="${gebruikersgeslacht =='jongens'}">
body {background-color: blue;}</c:if>
</style>
</head>
<body>
	<h1>Geslacht</h1>
	<form method='post'>
		<input type='submit' name='gebruikersgeslacht' value='meisjes'> <input
			type='submit' name='gebruikersgeslacht' value='jongens'>
	</form>
</body>
</html>