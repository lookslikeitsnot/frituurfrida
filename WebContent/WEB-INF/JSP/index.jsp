<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<fmt:setBundle basename='resourceBundles.teksten' />
<!doctype html>
<html>
<head>
<fmt:message key='naam' var="msgNaam"/>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value="${msgNaam}" />
</c:import>
</head>
<body>
	<fmt:message key='${openGesloten}' var="msgOC"/>
	<h1>
		<fmt:message key='vandaag'>
			<fmt:param value="${msgOC}" />
		</fmt:message>
	</h1>
	<img src="images/${openGesloten}<fmt:message key='imgLan'/>.png"
		alt="<fmt:message key='${openGesloten}'/>" />
	<address>
		<fmt:message key='komLangs'>
			<fmt:param value='${adres.straat}' />
			<fmt:param value='${adres.huisNr}' />
			<fmt:param value='${adres.gemeente.postCode}' />
			<fmt:param value='${adres.gemeente.naam}' />
		</fmt:message>
		<fmt:message key='belHelpdesk'>
			<fmt:param
				value='<a
			href="tel:${initParam.telefoonnummer}">${initParam.telefoonnummer}</a>' />
		</fmt:message>
	</address>

</body>
</html>