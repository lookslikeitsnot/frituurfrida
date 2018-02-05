<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Gastenboek' />
</c:import>
</head>
<body>
	<vdab:menu />
	<h1>Gastenboek</h1>
	<c:if test="${not empty beheerder}">
		<form method="post">
			<input type="submit" name="uitloggen" value="Uitloggen" />
		</form>
	</c:if>
	<c:choose>
		<c:when test="${not empty toevoegen}">
			<form method='post' id='toevoegform'>
				<label>Naam</label> <input name="gastnaam" type="text" autofocus
					required /> <label>Bericht</label> <input name="gastentekst"
					type="text" required /> <input type="submit" value="Toevoegen" />
			</form>
		</c:when>
		<c:otherwise>
			<c:url value='' var='toevoegen'>
				<c:param name='toevoegen' value='true' />
			</c:url>
			<a href="${toevoegen}">Toevoegen</a>
		</c:otherwise>
	</c:choose>

	<form method="post">

		<dl>
			<c:forEach var="gEntry" items="${gastenboek}">
				<c:if test="${not empty beheerder}">
					<input type="checkbox" name="entryId" value="${gEntry.nummer}">

				</c:if>
				<fmt:parseDate value="${gEntry.datum}" pattern="yyyy-MM-dd"
					var="datum" type="date" />
				<dt>
					<fmt:formatDate value='${datum}' type='date' dateStyle='long' />
					: ${gEntry.gastnaam}
				</dt>
				<dd>${gEntry.gastentekst}</dd>
			</c:forEach>
		</dl>
		<c:if test="${not empty beheerder}">
			<input type="submit" name="verwijderen" value="verwijderen"/>
		</c:if>
	</form>
	${fouten}
</body>
</html>