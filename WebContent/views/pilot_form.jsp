<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*, ph.rye.flight.model.*"%>

<!DOCTYPE html>

<html>

<head>
<jsp:include page="./_htmlhead.jsp" />

<script type="text/javascript" src="resources/js/validator.min.js"></script>
</head>

<body>
    <jsp:include page="_beginscript.jsp" />
    <script>
    $(function() {        
        $('*[name=BtnTest]').on('click', function() {
            $('[name=lastName]').val('Remulla');
            $('[name=firstName]').val('Royce');
            $('[name=license]').val('1234567');
            $('[name=rank]').val('Captain');
            $('form').validator('validate')
                .data("changed", true);
            return false;
        });        
    });
    </script>


	<div class="container-fluid">

        <jsp:include page="_detailbreadcrumb.jsp" />

        <form action="" class="form col-lg-3" role="form" data-toggle="validator" method="post">
			<fieldset>
				<legend>${entityName} Form - <c:out value="${pageMode}"/></legend>

                <div class="form-group has-feedback">
                    <label class="control-label" for="lastName">Last Name</label>
                    <input type="text" name="lastName" class="form-control"
                         placeholder="Last Name" 
                         pattern="([A-Z][a-z]*)(?: [A-Z][a-z]*)*" required/>
                    <div class="help-block with-errors"></div>
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                </div>

                <div class="form-group has-feedback">
					<label class="control-label" for="firstName">First Name</label>
					<input type="text" name="firstName" class="form-control"
					     placeholder="First Name" 
                         pattern="([A-Z][a-z]*)(?: [A-Z][a-z]*)*" required/>
                    <div class="help-block with-errors"></div>
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				</div>
				
                <div class="form-group has-feedback">
					<label class="control-label" for="license">License</label>
					<input type="text" name="license" class="form-control" 
                         placeholder="7-characters" 
					     pattern="[A-Za-z0-9]{7}" required /> 
                    <div class="help-block with-errors"></div>
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				</div>

                <div class="form-group has-feedback">
					<label class="control-label" for="rank">Rank</label> 
					<select name="rank" class="form-control" required>
                        <option disabled selected value=""> -- select an option -- </option>
						<c:forEach var="rank" items="${applicationScope.pilotRanks}">
							<option value="${rank.key}">${rank.value}</option>
						</c:forEach>
					</select>
                    <div class="help-block with-errors"></div>
                    <span style="padding-right: 20px" class="glyphicon form-control-feedback" aria-hidden="true"></span>
				</div>
				
			</fieldset>


			<button name="BtnCancel" class="btn btn-default">Cancel</button>
            <button name="BtnTest" class="btn btn-default">Test Data</button>
            <button name="BtnSave" class="btn btn-primary" onclick="checkValidity()">Submit</button>

            <jsp:include page="_hiddenvalues.jsp" />
		</form>
	</div>
</body>

</html>