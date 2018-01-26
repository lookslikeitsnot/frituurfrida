<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Frituur Linda' />
</c:import>
</head>
<body>
	<h1>Vandaag zijn we ${openGesloten}</h1>
	<img src="images/${openGesloten}.png" alt="${openGesloten}"/>
	<address>
		Kom eens langs op ${adres.straat} ${adres.huisNr}, 
		${adres.gemeente.postCode} ${adres.gemeente.naam}
		Bel de helpdesk op <a href="tel:${initParam.telefoonnummer}">${initParam.telefoonnummer}</a>
	</address>
	
</body>
</html>