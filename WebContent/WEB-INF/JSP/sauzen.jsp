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
<vdab:menu/>
	<h1>Sauzen</h1>
	<form method="post" id="sausverwijderen">
		<ul>
			<c:forEach var='saus' items='${sauzen}'>
				<li>
					<input type="checkbox" name="sausnummer" value="${saus.nummer}">
					<label>${saus.nummer}: <c:out value='${saus.naam}'/> 
						<c:if test="${!empty saus.ingredienten}">
							ingredienten: 
							<c:forEach var="ingredient" items="${saus.ingredienten}"
										varStatus="status">
								${ingredient}
								<c:if test="${!status.last}">, </c:if>
							</c:forEach>
						</c:if> 
						<img src="images/${saus.naam}.png" alt="${saus.naam}" />
					</label>
				</li>
			</c:forEach>
		</ul>
		<input type='submit' value='Aangevinkte sauzen verwijderen'
			id='verwijderknop'>
	</form>
</body>
</html>