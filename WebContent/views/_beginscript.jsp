<script>
$(function() {
	
	/* Fix validator bug on safari where changing the value in select is not 
	   detected properly. */
    if (/Safari/.test(navigator.userAgent)) {
	  HTMLSelectElement.prototype.checkValidity = function checkValidity() {
	    return this.validity.valid
	  }
	}
	
	/* Handle form submission, prevent submit if button is disabled, set event
	  prperty to the name of the button on submit. */
	$("form").submit(function(event) { 
        var button = $("button[clicked=true]");
        if (button.hasClass('disabled')) {
            return false;        	
        } else {
            $("input[name='event']").val(button.attr("name"));
		}
   	});
	
	
	
	/* Detect leaving page */
	$("a").on("click", function() {
	    var url = $(this).attr("href");
	    if ($("form").isChanged()) {
            $('#unsaved_modal').modal();
            $('#BtnDirtyOk').on('click', function() {
                window.location.href = url;
            });                
            $('#BtnDirtyCancel').on('click', function() {
                var $this = $(this);
                $this.data("modal", null);
            });                
            return false;
	    }
	});
	
    $("button[name=BtnCancel]").on('click', function() {
        var $this = $(this);
        if (!$this.data("modal")) {
            var form = $("form");
            if (form.isChanged()) {
                $('#unsaved_modal').modal();
                $('#BtnDirtyOk').on('click', function() {
                	$this.data("modal", true);
                	$this.trigger('click');
                });
                $('#BtnDirtyCancel').on('click', function() {
                    $this.data("modal", null);
                });
                form.validator('destroy');
                return false;
            }
            form.validator('destroy');
        }     
    });
	

	$("form button").click(function() {
	    $("button", $(this).parents("form")).removeAttr("clicked");
	    $(this).attr("clicked", "true");
	});
	
	$.fn.extend({
		 trackChanges: function() {
		   $(":input",this).change(function() {
		      $(this.form).data("changed", true);
		   });
		 }
		 ,
		 isChanged: function() { 
		   return this.data("changed"); 
		 }
	});

	$("form").trackChanges();
	 
 	$( ".success_message" ).slideDown( 300 ).delay( 8000 ).slideUp( 400 );
/*     $( ".success_message" ).slideDown( 300 );
 *//*     $( ".success_message" ).on('mouseover', function() {
    	$(this).slideUp(400);
    });
 */    
	
});
</script>


<jsp:include page="_formdirtymodal.jsp" />
