<%@ page import="java.util.*, ph.rye.flight.model.*"%>

<!DOCTYPE html>

<html>

<head>
<jsp:include page="./_htmlhead.jsp" />
</head>

<body>
<ol class="breadcrumb">
  <li><a href="./">Home</a></li>
  <li class="active">Passenger List</li>
</ol>

	<h1>List of Passengers</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Date of Birth</th>
				<th>Gender</th>
			</tr>
		</thead>
		<tbody>
			<%
			    List<Passenger> passengerList =
			            (List<Passenger>) request.getAttribute("passengerList");
			    if (passengerList == null || passengerList.isEmpty()) {
			        out.println("There are no passengers.");
			    } else {
			        for (Passenger passenger : passengerList) {
			%>
			<tr>
				<td><%=passenger.getFirstName()%></td>
				<td><%=passenger.getLastName()%></td>
				<td><%=passenger.getDobDisp()%></td>
				<td><%=passenger.getGender()%></td>

			</tr>
			<%
			    }
			    }
			%>
			<tr>
				<td colspan="5">No flight tickets yet.</td>
			</tr>
		</tbody>

	</table>
	<button></button>
</body>

</html>