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

  $('*[name=BtnSelectPilots]')
      .on(
          'click',
          function() {
            if ($("#pilotTable > tbody > tr").length >= 9) {
              $(this).prop("disabled", true);
            }

            var newRow = "<tr><td><input name='pilot_select' type='checkbox'></td>\
          <td></td><td></td><td></td>\
          <td></td>\
          <td></td>\
          <td><button name=\"pilot_lov\" type=\"button\" class=\"btn btn-default\">\
          <span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span>\
          </button>\
          </a></td></tr>";

            var lastTr = $("#pilotTable > tbody tr:last");
            if (lastTr.length > 0) {
              lastTr.after(newRow);
            } else {
              $("#pilotTable > tbody").append(newRow);
            }

            $("button[name=pilot_lov]").on('click', function() {
              $("#lov_modal").modal();
            });

            $("select[name=pilot]").on(
                'change',
                function() {
                  var select = $(this);

                  if (select.val()) {
                    var url = './rest/pilots/' + select.val();

                    $.ajax(url, {
                      dataType : 'json',
                      success : function(data) {
                        select.parent().next().text(data['lastName']).next()
                            .text(data['firstName']).next().text(
                                data['license']).next().text(data['rank']);
                      }
                    });
                  } else {
                    select.parent().next().text(null).next().text(null).next()
                        .text(null).next().text(null);
                  }
                });

            return false;
          });
});