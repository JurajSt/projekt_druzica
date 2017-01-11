<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
</head>
<body>

<div id="map"></div>
<script type="text/javascript">
    var markerLat, markerLong;
    markerLat = [
        <c:forEach var="s" items="${coorAll}">
            <c:out value="${myItem[1]}"/>,
        </c:forEach>;
    markerLong = [
      <c:forEach var="s" items="${coorAll}">
          <c:out value="${myItem[2]}"/>,
      </c:forEach>;

    function initialize() {
        var map;
        var initlatlng = new google.maps.LatLng(markerLat[0],markerLong[0]);
        var mapOptions = {
            zoom: 6,
            center: new google.maps.LatLng(10,9),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById('map'), mapOptions);
        var infowindow = new google.maps.InfoWindow();
        var marker, i;

        for (i = 0; i < markerLat.length; i++) {
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(markerLat[i], markerLong[i]),
                map: map
            });

            google.maps.event.addListener(marker, 'click', (function(marker, i) {
                return function() {
                    infowindow.setContent(markers[i]);
                    infowindow.open(map, marker);
                }
            })(marker, i));
        }
    }

    google.maps.event.addDomListener(window, 'load', initialize);
</script>

<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCgwQQiw9CKhWTDIgM_JK-8p5iyqkXsSe8&callback=initialize">
</script>

</body>
</html>