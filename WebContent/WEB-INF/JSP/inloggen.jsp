<%@page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces='true'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value="Sauzen" />
</c:import>
</head>
<body>
	<vdab:menu />
	<h1>Inloggen</h1>
	<form method="post">
		<input type="password" name="pwd" required autofocus/>
		<input type="submit" value="OK"/>
	</form>
</body>
</html>