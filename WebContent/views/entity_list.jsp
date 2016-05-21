<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, ph.rye.flight.model.*"%>

<!DOCTYPE html>

<html>

<head>
<jsp:include page="./_htmlhead.jsp" />
</head>

<body>
    <jsp:include page="_messages.jsp" />
    <jsp:include page="_beginscript.jsp" />
    <jsp:include page="_confirm_delete_modal.jsp" />
    <script>
    $(function() {
      $(":checkbox").on('click', function() {
        var $this = $(this);
        var btnDelete = $('button[name="BtnDelete"]');
        
        var hasChecked = false;
        $(":checkbox").each(function(index, element) {
          if ($(element).prop('checked')) {
            hasChecked = true;
            return false;
          } else {
            return true;                
          }           
        });
        
        if (hasChecked) {
            btnDelete.removeClass('disabled');              
        } else {
            btnDelete.addClass('disabled');
        }
    });

      $("button[name=BtnDelete]").on('click', function() {
        var $this = $(this);
        if ($this.hasClass('disabled')) {
          return false;
        } else {
          var $this = $(this);
          if (!$this.data("modal")) {
              var dialog = $('#confirm_delete_modal'); 
              dialog.modal();
              $('#BtnDeleteOk').on('click', function() {
                $this.data("modal", true);
                $this.trigger('click');
                dialog.modal('toggle');
              });
              $('#BtnDeleteCancel').on('click', function() {
                  $this.data("modal", null);
              });
              return false;
          }     
        }
      });

   // $(":checked").parent().parent().fadeOut(400)
    	    	
    });
    </script>
    
    <div class="container-fluid">
	<ol class="breadcrumb">
		<li><a href="Home">Home</a></li>
		<li class="active">${entityName} List</li>
	</ol>

	<h1>List of ${entityName}s</h1>
	<form method="post">
	<table class="table">
		<thead>
			<tr>
				<th>Select</th>
                <c:forEach var="column" items="${columnSet}">
                    <th>${column}</th>
                </c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${entityList eq null or entityList.isEmpty()}">
                   <tr><td colspan="${columnCount}">There are no ${entityName}s.</td></tr>
		        </c:when>
				<c:otherwise>
					<c:forEach var="entity" items="${entityList}">
						<tr>
							<td><input name="entityIds" type="checkbox" value="${entity[0]}"></td>
                            <c:forEach var="entityValue" items="${entity}">
                            <td>${entityValue}</td>
                            </c:forEach>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<button name="BtnAdd" class="btn btn-primary">Add</button>
    <button name="BtnDelete" class="btn btn-danger disabled">Delete</button>
    
    <jsp:include page="_hiddenvalues.jsp" />
    
    </form>
    </div>
</body>

</html>