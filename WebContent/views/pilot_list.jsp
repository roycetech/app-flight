<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, ph.rye.flight.model.*"%>

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
    
    <div class="container-fluid">
	<ol class="breadcrumb">
		<li><a href="./">Home</a></li>
		<li class="active">Passenger List</li>
	</ol>

	<h1>List of Pilots</h1>
	<form method="post">
	<table class="table">
		<thead>
			<tr>
				<th>&nbsp;</th>
				<th>ID</th>
                <th>Last Name</th>
				<th>First Name</th>
				<th>License</th>
				<th>Rank</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${pilotList eq null or pilotList.isEmpty()}">
                   <tr><td colspan="6">There are no pilots.</td></tr>
                   <c:out value="There are no pilots."/>                                   
		        </c:when>
				<c:otherwise>
					<c:forEach var="pilot" items="${pilotList}">
						<tr>
							<td><input type="checkbox"></td>
                            <td>${pilot.id}</td>
							<td>${pilot.lastName}</td>
                            <td>${pilot.firstName}</td>
                            <td>${pilot.license}</td>
                            <td>${pilot.rank}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<button name="PilotAdd" class="btn btn-primary">Add</button>
    <button name="PilotRemove" class="btn btn-danger disabled">Remove</button>
    
    <jsp:include page="_hiddenvalues.jsp" />
    
    </form>
    </div>
</body>

</html>