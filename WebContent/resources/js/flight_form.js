$(function() {

  $('*[name=flightTime]').datetimepicker({
    showButtonPanel : true,
    minDate : new Date(),
    stepMinute : 5
  });

  $('*[name=BtnTest]').on('click', function() {
    $('[name=flightOrigin]')[0].selectedIndex = 1;
    $('[name=flightDestination]')[0].selectedIndex = 2;
    $('[name=price]').val('400');
    var now = new Date();
    var month = now.getMonth() + 1;
    if (month < 10) {
      month = '0' + month;
    }
    var day = now.getDate() + 1;
    if (day < 10) {
      day = '0' + day;
    }

    var dateStr = month + "/" + day + "/" + now.getFullYear() + " 00:00";

    $('[name=flightTime]').val(dateStr);
    $('[name=airplane]')[0].selectedIndex = 1;
    $('form').validator('validate').data("changed", true);
    return false;
  });

});
