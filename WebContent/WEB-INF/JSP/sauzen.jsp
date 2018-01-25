<%@page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value="Sauzen" />
</c:import>
</head>
<body>
	<h1>Sauzen</h1>
	<ul class='zebra'>
		<c:forEach var='saus' items='${sauzen}'>
			<li>${saus.nummer}: <c:out value='${saus.naam}' />
				<c:if test="${!empty  saus.ingredienten}">
					ingredienten: 
					<c:forEach var="ingredient" items="${saus.ingredienten}" varStatus="status">
						${ingredient}
						<c:if test="${!status.last}">
						, 
						</c:if>
					</c:forEach>
				</c:if>
				<img src="images/${saus.naam}.png" alt="${saus.naam}"/>
<%-- 				<c:url value='/sauzen/detail.htm' var='detailURL'> --%>
<%-- 					<c:param name='id' value='${saus.nummer}' /> --%>
<%-- 				</c:url> --%>
<%-- 				&nbsp;<a href='${detailURL}'>Detail</a> --%>
			</li>
		</c:forEach>
	</ul>
</body>
</html>