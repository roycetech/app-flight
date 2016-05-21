$("button[name=BtnCancel]").on('click', function() {
  var $this = $(this);
  if (!$this.data("modal")) {
    if ($("form").isChanged()) {
      $('#unsaved_modal').modal();
      $('#BtnDirtyOk').on('click', function() {
        $this.data("modal", true);
        $this.trigger('click');
      });
      $('#BtnDirtyCancel').on('click', function() {
        $this.data("modal", null);
      });
    }
    $("form").validator('destroy');
    return false;
  }
});
