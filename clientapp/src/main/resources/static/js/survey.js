$(document).ready(function () {
  $(".dropdown-employee").select2({
    dropdownParent: $("#createSurvey"),
  });
  $(".dropdown-client").select2({
    dropdownParent: $("#createSurvey"),
  });
  $("#table-survey").DataTable({
    ajax: {
      url: "/api/survey",
      dataSrc: "",
    },
    columns: [
      {
        data: "id",
      },
      {
        data: "name",
      },
      {
        data: "employee.name",
      },
      {
        data: "client.name",
      },
      {
        data: "expired",
      },
      {
        data: "status.name",
        render: function (data, type, row) {
          let badgeClass = "";
          switch (data) {
            case "Sent":
              badgeClass = "badge bg-primary";
              break;
            case "Answered":
              badgeClass = "badge bg-warning";
              break;
            case "Reviewed":
              badgeClass = "badge bg-success";
              break;
            default:
              badgeClass = "badge bg-secondary";
              break;
          }
          return '<span class="' + badgeClass + '">' + data + "</span>";
        },
      },
    ],
  });
  $.ajax({
    url: "/api/employee/",
    method: "GET",
    dataType: "JSON",
    success: (result) => {
      $.each(result, (key, value) => {
        $(".dropdown-employee").append(
          $("<option>", {
            value: value.id,
            text: value.name,
          })
        );
      });
    },
  });
  $.ajax({
    url: "/api/client/",
    method: "GET",
    dataType: "JSON",
    success: (result) => {
      $.each(result, (key, value) => {
        $(".dropdown-client").append(
          $("<option>", {
            value: value.id,
            text: value.name,
          })
        );
      });
    },
  });
});

function defaults() {
  $("#crt-survey-employee").removeClass("is-invalid");
  $("#crt-survey-client").removeClass("is-invalid");

  $("#crt-survey-employee").select2().val("");
  $("#crt-survey-client").select2().val("");
}

function create() {
  let employee = $("#crt-survey-employee").val();
  let client = $("#crt-survey-client").val();

  var errors = 0;

  if (employee === "") {
    $("#crt-survey-employee").addClass("is-invalid");

    errors += 1;
  }

  if (client === "") {
    $("#crt-survey-client").addClass("is-invalid");

    errors += 1;
  }

  if (errors > 0) {
    return;
  }

  $.ajax({
    url: "/api/survey",
    method: "POST",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCsrfToken(),
    data: JSON.stringify({
      employee: {
        id: employee,
      },
      client: {
        id: client,
      },
    }),
    success: (result) => {
      $("#createSurvey").modal("hide");
      $(".modal-backdrop").remove();
      $("#table-survey").DataTable().ajax.reload();
      defaults();
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Survey has been sent",
        showConfirmButton: false,
        timer: 1500,
        width: 600,
      });
    },
  });
}
