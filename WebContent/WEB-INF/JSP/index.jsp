<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<!doctype html>
<html>
<head>
<link rel='icon' href='images/favicon.ico'>
<title>Frituur Frida</title>
</head>
<body>
	<h1>Vandaag zijn we ${openGesloten}</h1>
	<img src="images/${openGesloten}.png" alt="${openGesloten}"/>
	<dl>
		<dt>Straatnaam</dt>
		<dd>${adres.straat}</dd>
		<dt>Huisnummer</dt>
		<dd>${adres.huisNr}</dd>
		<dt>Te</dt>
		<dd>${adres.gemeente.postCode}</dd>
		<dd>${adres.gemeente.naam}</dd>
		
	</dl>
	
</body>
</html>