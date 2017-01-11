<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
</head>
<body>

<h1>Zoznam Družíc</h1>

<table>
<tr><th>ID</th><th>Názov</th><th>X</th><th>Y</th><th>Z</th><th>B</th><th>L</th><th>H</th><th>Čas</th></tr>
<c:forEach items="${druzice.values()}" var="b">
    <tr><td><c:out value="${b.getID()}"/></td><td><c:out value="${b.getNazev()}"/></td><td><c:out value="${b.getSurX()}"/></td><td><c:out value="${b.getSurY()}"/></td><td><c:out value="${b.getSurZ()}"/></td><td><c:out value="${b.getSurB()}"/></td><td><c:out value="${b.getSurL()}"/></td><td><c:out value="${b.getSurH()}"/></td><td><c:out value="${b.getCas()}"/></td><td><a href="/deleteDruzica/${b.getID()}">zmazať</a></tr>
</c:forEach>
</table>


</body>
</html>