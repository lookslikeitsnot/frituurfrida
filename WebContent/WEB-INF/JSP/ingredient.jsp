<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value="Ingrediënt" />
</c:import>
</head>
<body>
	<h1>Ingrediënt Zoeker</h1>
	<form>
		<input name='ingredient' value='${param.ingredient}' type='text'
			required> <input type='submit'
			value='Toon sauzen met dit ingrediënt'>
	</form>
	<c:if test='${not empty sauzen}'>
		<h1>Deze sauzen bevatten ${param.ingredient}</h1>
		<ul class='zebra'>
			<c:forEach var='saus' items='${sauzen}'>
				<li><c:out value='${saus}' /></li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${!empty param and empty fout and empty sauzen}">
		<div class='notFound'>Er worden geen sauzen gemaakt met &laquo;${param.ingredient}&raquo;</div>
	</c:if>
	<c:if test="${!empty param and !empty fout}">
		<div class='fout'>${fout}</div>
	</c:if>
</body>
</html>