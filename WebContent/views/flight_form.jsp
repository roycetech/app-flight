<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*, ph.rye.flight.model.*"%>

<!DOCTYPE html>

<html>

<head>
    <jsp:include page="./_htmlhead.jsp" />

    <!-- Used for the Date -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <!-- Time add-on to the Date -->
    <script type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></script>
    <link type="text/css" href="resources/css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
    
    <!-- Enable validation -->
    <script type="text/javascript" src="resources/js/validator.min.js"></script>
        
</head>

<body>
    <jsp:include page="_beginscript.jsp" />    
    <script src="resources/js/flight_form.js"></script>
	<div class="container-fluid">

        <jsp:include page="_detailbreadcrumb.jsp" />

        <form action="" role="form" data-toggle="validator" method="post">
			<div class="col-lg-3">
			<fieldset class="form">
				<legend>${entityName} Form - <c:out value="${pageMode}"/></legend>

                <div class="form-group has-feedback">
                    <label class="control-label" for="flightOrigin">Flight Origin</label> 
                    <select name="flightOrigin" class="form-control" required>
                        <option disabled selected value=""> -- select an option -- </option>
                        <c:forEach var="airport" items="${airports}">
                            <option value="${airport.key}">${airport.value}</option>
                        </c:forEach>
                    </select>
                    <div class="help-block with-errors"></div>
                    <span style="padding-right: 20px" class="glyphicon form-control-feedback" aria-hidden="true"></span>
                </div>

                <div class="form-group has-feedback">
                    <label class="control-label" for="flightDestination">Flight Destination</label> 
                    <select name="flightDestination" class="form-control" required>
                        <option disabled selected value=""> -- select an option -- </option>
                        <c:forEach var="airport" items="${airports}">
                            <option value="${airport.key}">${airport.value}</option>
                        </c:forEach>
                    </select>
                    <div class="help-block with-errors"></div>
                    <span style="padding-right: 20px" class="glyphicon form-control-feedback" aria-hidden="true"></span>
                </div>

                <div class="form-group has-feedback">
					<label class="control-label" for="price">Price</label>
					<input type="number" name="price"
					   class="form-control" min="0.00" required/>
                    <div class="help-block with-errors"></div>
                    <span style="padding-right: 20px" class="glyphicon form-control-feedback" aria-hidden="true"></span>
				</div>

                <div class="form-group date has-feedback">
                    <label class="control-label" for="flightTime">Flight Time</label>
				    <input type="datetime" name="flightTime" class="form-control" required/>
                    <div class="help-block with-errors"></div>
                    <i class="glyphicon glyphicon-calendar form-control-feedback"></i>
				</div>
				
                <div class="form-group has-feedback">
                    <label class="control-label" for="airplane">Airplane</label> 
                    <select name="airplane" class="form-control" required>
                        <option disabled selected value=""> -- select an airplane -- </option>
                        <c:forEach var="airplane" items="${airplaneList}">
                            <option value="${airplane.id}">${airplane.nameDisp}</option>
                        </c:forEach>
                    </select>
                    <div class="help-block with-errors"></div>
                    <span style="padding-right: 20px" class="glyphicon form-control-feedback" aria-hidden="true"></span>
                </div>                
			</fieldset>

            <button name="BtnCancel" class="btn btn-default">Cancel</button>
            <button name="BtnTest" class="btn btn-default">Test Data</button>
            <button name="BtnSave" type="submit" class="btn btn-primary" onclick="checkValidity()">Submit</button>

            <jsp:include page="_hiddenvalues.jsp" />

			</div>


            <div class="col-lg-6">
            <label class="control-label">Pilots</label> 
            <table id="pilotTable" class="table col-lg-6">
                <thead>
                    <tr>
                        <th>Select</th>                    
                        <c:forEach var="column" items="${columnSet}">
                            <th>${column}</th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>                
                        <c:forEach var="pilot" items="${entityList}">
                        <tr>
                            <td class="text-center"><input name="pilotIds" type="checkbox" value="${pilot[0]}"></td>
                            <c:forEach var="i" begin="0" end="4">
                            <td>${pilot[i]}</td>
                            </c:forEach>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
            </div>
		</form>
	</div>
	
</body>

</html>



