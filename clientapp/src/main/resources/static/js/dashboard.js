$(document).ready(function () {
  $.ajax({
    url: "/api/employee",
    method: "GET",
    dataType: "JSON",
    success: function (data) {
      $("#employee-count").text(`${data.length}`);
    },
  });

  $.ajax({
    url: "/api/client",
    method: "GET",
    dataType: "JSON",
    success: function (data) {
      $("#client-count").text(`${data.length}`);
    },
  });

  $.ajax({
    url: "/api/survey",
    method: "GET",
    dataType: "JSON",
    success: function (data) {
      $("#survey-count").text(`${data.length}`);
    },
  });

  $.ajax({
    url: "/api/result",
    method: "GET",
    dataType: "JSON",
    success: function (data) {
      $("#result-count").text(`${data.length}`);
    },
  });
});
