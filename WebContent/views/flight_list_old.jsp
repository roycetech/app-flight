<%@ page import="java.util.*, ph.rye.flight.model.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<jsp:include page="./_htmlhead.jsp" />
</head>

<body>
    <jsp:include page="_beginscript.jsp" />
    <script>
    $(function() {
        $(":checkbox").on('click', function() {
            var $this = $(this);
            var btnRemove = $('button[name="PilotRemove"]');
            if ($(":checkbox").prop('checked')) {
                btnRemove.removeClass('disabled');              
            } else {
                btnRemove.addClass('disabled');
            }
        });
    });   
    </script>

	<ol class="breadcrumb">
	  <li><a href="./">Home</a></li>
	  <li class="active">Flight List</li>
	</ol>

	<h1>List of Flights</h1>
    <form method="post">
    <table class="table">
        <thead>
            <tr>
                <th>&nbsp;</th>
                <th>ID</th>
                <th>From</th>
                <th>To</th>
                <th>Time</th>
                <th>Price</th>
                <th>Airplane</th>
                <th>Seating</th>
                <th>Number of Pilots</th>
                <th>Pilot Name</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${flightList eq null or flightList.isEmpty()}">
                   <tr><td colspan="10">There are no flights.</td></tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="flight" items="${flightList}">
                    <c:set var="plane" scope="page" value="${flight.planeDetail}"/>
                        <tr>
                            <td><input name="selected" type="checkbox"></td>
                            <td>${flight.id}</td>
                            <td>${flight.flightOrigin}</td>
                            <td>${flight.flightDestination}</td>
                            <td>${flight.flightTime}</td>
                            <td>${flight.price}</td>
                            <td>${plane.nameDisp}</td>
                            <td>${plane.seatCount}</td>
                            <td>${plane.pilotCount}</td>
                            <td>${plane.pilotNames}</td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    
    <button name="FlightAdd" class="btn btn-primary">Add</button>
    <button name="FlightRemove" class="btn btn-danger disabled">Remove</button>
    
    <jsp:include page="_hiddenvalues.jsp" />
    
    </form>
</body>

</html>