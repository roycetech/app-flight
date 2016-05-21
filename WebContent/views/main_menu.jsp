<!DOCTYPE html>

<html>

<head>
<jsp:include page="./_htmlhead.jsp" />
</head>

<body>
	<ol class="breadcrumb">
		<li class="active">Home</li>
	</ol>

	<div class="container-fluid">

		<h1>Airline Application</h1>

		<div class="list-group">
			<a href="flights" class="list-group-item">Flights<span class="badge">${flightsCount} </span></a> 
			<a href="passengers" class="list-group-item">Passengers<span class="badge">${passengersCount} </span></a>
            <a href="pilots" class="list-group-item">Pilots<span class="badge">${pilotsCount} </span></a> 
            <a href="airplanes" class="list-group-item">Airplanes<span class="badge">${airplanesCount} </span></a> 
		</div>

	</div>

</body>
</html>