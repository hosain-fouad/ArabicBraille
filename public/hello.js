$(document).ready(function() {
  $( ".translate" ).click(function() {
    var x = encodeURIComponent($('.translate-input').val());
    $.ajax({
      url: "http://localhost:8090/translateEncoded?input="+x
    }).then(function(data) {
      $('.translate-output').html(data).text();
    });
  });

});
