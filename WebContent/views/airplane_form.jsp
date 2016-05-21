<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*, ph.rye.flight.model.*"%>

<!DOCTYPE html>

<html>

<head>
    <jsp:include page="./_htmlhead.jsp" />

    <!-- Enable validation -->
    <script type="text/javascript" src="resources/js/validator.min.js"></script>
</head>

<body>
    <jsp:include page="_beginscript.jsp" />
    <script>
    $(function() {
        
        $('*[name=BtnTest]').on('click', function() {
            $('[name=planeMake]').val('Boeing');
            $('[name=modelName]').val('747');
            $('[name=seatingCapacity]').val('200');
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
					<label class="control-label" for="planeMake">Plane Make</label>
					<input type="text" name="planeMake" class="form-control" 
                       placeholder="Airplane Maker"
					   required/>
                    <div class="help-block with-errors"></div>
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				</div>
				
                <div class="form-group has-feedback">
					<label class="control-label" for="modelName">Model Name</label>
					<input type="text" name="modelName" class="form-control" 
                       placeholder="Model Name"
					   required/>
                    <div class="help-block with-errors"></div>
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				</div>

                <div class="form-group has-feedback">
					<label class="control-label" for="seatingCapacity">Number of Seats</label>
					<input type="number" name="seatingCapacity" class="form-control"
                       placeholder="200"
					   min="1" required /> 
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