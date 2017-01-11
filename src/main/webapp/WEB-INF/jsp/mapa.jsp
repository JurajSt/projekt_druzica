<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<style>
#map {
        height: 100%;
      }
html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
</style>
<head>
</head>
<body>

<c:forEach var="s" items="${stanice.values()}">
    <c:out value="${s.getSurB()}"/>
</c:forEach>


<div id="map"></div>
    <script type="text/javascript">
        var markerLat, markerLong;
        markerLat = [
            <c:forEach var="s" items="${stanice.values()}">
                <c:out value="${s.getSurB()}"/>,
            </c:forEach>;
        markerLong = [
          <c:forEach var="s" items="${stanice.values()}">
              <c:out value="${s.getSurB()}"/>,
          </c:forEach>;
        function initMap() {
            var myLatLng = {lat: markerLat[0], lng: markerLong[0]};

            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 4,
                center: myLatLng
                });

                var marker = new google.maps.Marker({
                    position: myLatLng,
                    map: map,
                    title: 'Hello World!'
                });
             }
    </script>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCgwQQiw9CKhWTDIgM_JK-8p5iyqkXsSe8&callback=initMap">
</script>

</body>
</html>