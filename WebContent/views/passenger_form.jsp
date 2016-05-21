<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*, ph.rye.flight.model.*"%>

<!DOCTYPE html>

<html>

<head>
<jsp:include page="./_htmlhead.jsp" />
 
    <!-- Used for the Date -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    
    <!-- Enable validation -->
    <script type="text/javascript" src="resources/js/validator.min.js"></script>
</head>

<body>
    <jsp:include page="_beginscript.jsp" />
    <script>
    $(function() {
		$('*[name=dob]').datepicker({
		    changeMonth: true,
		    changeYear: true,
// 		    showButtonPanel: true,
 		    maxDate: new Date()
	    });
		
		$('*[name=BtnTest]').on('click', function() {
			$('[name=lastName]').val('Remulla');
            $('[name=firstName]').val('Remulla');
            $('[name=dob]').val('05/10/1979');
            $('[name=flightClass]').val('Business');
            $('[name=gender]').eq(0).prop('checked', true)
                .trigger("click");
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
                    <input  type="text" name="lastName" class="form-control" 
                        placeholder="Last Name"
                        pattern="([A-Z][a-z]*)(?: [A-Z][a-z]*)*"                         
                        required/>
                    <div class="help-block with-errors"></div>
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                </div>

				<div class="form-group has-feedback">
					<label class="control-label" for="firstName">First Name</label>
					<input type="text" name="firstName" 
					   pattern="([A-Z][a-z]*)(?: [A-Z][a-z]*)*" 
					   class="form-control" placeholder="First Name" required/>
                    <div class="help-block with-errors"></div>
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				</div>

                <div class="form-group date has-feedback">
                    <label class="control-label" for="dob">Date of Birth</label>
                    <input type="text" name="dob" required class="form-control" placeholder="MM/dd/yyyy"/>
                    <div class="help-block with-errors"></div>
                    <i class="glyphicon glyphicon-calendar form-control-feedback"></i>
                </div>
				
				<div class="form-group has-feedback">
                    <label class="control-label" for="gender">Gender</label><br>
                    <c:forEach var="gender" items="${applicationScope.genders}">
 	                <div class="radio-inline">
	                  <div class="radio"><input type="radio" name="gender" value="${gender.value}" required>${gender.value}</div>
	                </div>
                    </c:forEach>
                    <div class="help-block with-errors"></div>
                </div>
				
				<div class="form-group has-feedback">
					<label class="control-label" for="rank">Flight Class</label> 
					<select name="flightClass" required class="form-control">
                        <option selected value=""> -- select an option -- </option>
						<c:forEach var="flightClass" items="${applicationScope.flightClasses}">
                            <option value="${flightClass.key}">${flightClass.value}</option>
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
		</form>
	</div>
    
</body>

</html>