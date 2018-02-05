<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Saus Raden' />
</c:import>
</head>
<body>
<vdab:menu/>
	<h1>Saus Raden</h1>
	<div class="te_raden">Te raden saus: ${sausRaden.gevonden}</div>
	<form method='post'>
		<c:choose>
			<c:when
				test="${sausRaden.pogingen < 10 && sausRaden.gevonden!=sausRaden.teVinden}">
				<input type="text" name="userChar" maxlength="1" autofocus>
				<input type="submit" name="raden" value="Raden">
			</c:when>
			<c:when test="${sausRaden.pogingen < 10 && sausRaden.gevonden==sausRaden.teVinden}">
				U bent gewonnen, de saus was ${sausRaden.gevonden}
			</c:when>
			<c:otherwise>
				U bent verloren, de saus was ${sausRaden.teVinden}
			</c:otherwise>
		</c:choose>

		<input type="submit" name="nieuwspel" value="Nieuw spel">
	</form>
	<img src="images/${sausRaden.pogingen}.png"
		title="${sausRaden.pogingen}" />
</body>
</html>