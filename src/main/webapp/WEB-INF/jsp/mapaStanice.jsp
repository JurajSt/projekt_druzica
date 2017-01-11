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

<div id="map"></div>
    <script type="text/javascript" src="/script.jsp"></script>
    <script type="text/javascript">

        var markerLat, markerLong, markerTit;

        markerLat = [<c:forEach items="${stanice.values()}" var="a">
                        <c:out value="${a.getSurB()}"/>,
                     </c:forEach>
        ];

        markerLong = [<c:forEach items="${stanice.values()}" var="b">
                        <c:out value="${b.getSurL()}"/>,
                      </c:forEach>
        ];

        markerTit = [<c:forEach items="${stanice.values()}" var="c">
                    "'<c:out value="${c.getNazev()}"/>'",
                 </c:forEach>
        ];

        function initMap() {
                var myLatLng = {lat: markerLat[0], lng: markerLong[0]};

                var map = new google.maps.Map(document.getElementById('map'), {
                  zoom: 4,
                  center: myLatLng
                });
                var infowindow = new google.maps.InfoWindow();
                var marker, i;
                for (i = 0; i < markerLat.length; i++) {
                            marker = new google.maps.Marker({
                                position: new google.maps.LatLng(markerLat[i], markerLong[i]),
                                map: map
                            });

                            google.maps.event.addListener(marker, 'click', (function(marker, i) {
                                            return function() {
                                                infowindow.setContent(markerTit[i]);
                                                infowindow.open(map, marker);
                                            }
                            })(marker, i));
                }
        }
    </script>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCgwQQiw9CKhWTDIgM_JK-8p5iyqkXsSe8&callback=initMap">
</script>

</body>
</html>