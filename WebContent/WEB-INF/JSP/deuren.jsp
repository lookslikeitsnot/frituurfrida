<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='DeurSpel' />
</c:import>
</head>
<body>
	<h1>Zoek de friet</h1>
	<form method='post'>
	<c:forEach var="deur" items="${deurSpel.deuren}" varStatus="status">
		<button type='submit' name='deurnummer' value='${deur.value.nummer}' >
		<img src="images/${deur.value.image}.png"/>
		</button>
	</c:forEach>
	<br>
	<input type="submit" name="nieuwspel" value="Nieuw spel">
	</form>
</body>
</html>