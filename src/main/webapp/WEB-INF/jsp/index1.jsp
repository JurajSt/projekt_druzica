<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
</head>
<body>

<h1>Zoznam Staníc</h1>

<table>
<tr><th>Názov</th><th>X</th><th>Y</th><th>Z</th><th>B</th><th>L</th><th>H</th><th>Zmazať</th></tr>
<c:forEach items="${stanice.values()}" var="a">
    <tr><td><c:out value="${a.getNazev()}"/></td><td><c:out value="${a.getSurX()}"/></td><td><c:out value="${a.getSurY()}"/></td><td><c:out value="${a.getSurZ()}"/></td><td><c:out value="${a.getSurB()}"/></td><td><c:out value="${a.getSurL()}"/></td><td><c:out value="${a.getSurH()}"/></td><td><a href="/deleteStanica/${a.getNazev()}">zmazať</a></tr>
</c:forEach>
</table>
<table>
<form action="/createStanica" method="POST" ><br>
<tr><th>Názov stanice: <input type="text" name="nazevStanice"></th><th><input type="submit" value="Create"></th></tr>
<tr><th>Súradnica X: <input type="text" name="surStaniceX"></th><th>Súradnica B: <input type="text" name="surStaniceB"></th></tr>
<tr><th>Súradnica Y: <input type="text" name="surStaniceY"></th><th>Súradnica L: <input type="text" name="surStaniceL"></th></tr>
<tr><th>Súradnica Z: <input type="text" name="surStaniceZ"></th><th>Súradnica H: <input type="text" name="surStaniceH"></th></tr>

</form>
</table>
<form method="POST" enctype="multipart/form-data" action="/uploadFile" >
		<table>
			<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
			<tr><td></td><td><input type="submit" value="Upload" /></td></tr>
		</table>
</form>

<form action="/showMapStations" method="GET" ><br>
Zobrazenie stanic na mape <input type="submit" value="Mapa">
</form>


<form action="/createDruzica" method="GET" ><br>
Výpočet polohy družíc pre načítanú navigačnú správu: <input type="submit" value="Výpočet">
</form>
</body>
</html>