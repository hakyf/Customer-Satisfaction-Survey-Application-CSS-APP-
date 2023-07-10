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
  $("#crt-survey-name").removeClass("is-invalid");
  $("#crt-survey-employee").removeClass("is-invalid");
  $("#crt-survey-client").removeClass("is-invalid");
  $("#crt-survey-expired").removeClass("is-invalid");

  $("#crt-survey-name").val("");
  $("#crt-survey-employee").val("");
  $("#crt-survey-client").val("");
  $("#crt-survey-expired").val("");
}

function create() {
  let name = $("#crt-survey-name").val().trim();
  let employee = $("#crt-survey-employee").val().trim();
  let client = $("#crt-survey-client").val().trim();
  let expired = $("#crt-survey-expired").val().trim();

  var errors = 0;
  if (name === "") {
    $("#crt-survey-name").addClass("is-invalid");

    errors += 1;
  }

  if (employee === "") {
    $("#crt-survey-employee").addClass("is-invalid");

    errors += 1;
  }

  if (client === "") {
    $("#crt-survey-client").addClass("is-invalid");

    errors += 1;
  }

  if (expired === "") {
    $("#crt-survey-expired").addClass("is-invalid");

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
      name: name,
      employee: {
        id: employee,
      },
      client: {
        id: client,
      },
      expired: expired,
      status: {
        id: 1,
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
        title: "survey has been created",
        showConfirmButton: false,
        timer: 1500,
        width: 600,
      });
    },
  });
}

function beforeUpdate(id) {
  $.ajax({
    url: "/api/survey/" + id,
    method: "GET",
    dataType: "JSON",
    success: (result) => {
      $("#upd-survey-id").val(`${result.id}`);
      $("#upd-survey-name").val(`${result.name}`);
    },
  });
}

function update() {
  let id = $("#upd-survey-id").val();
  let name = $("#upd-survey-name").val().trim();

  var errors = 0;
  if (name === "") {
    $("#upd-survey-name").addClass("is-invalid");

    errors += 1;
  }

  if (errors > 0) {
    return;
  }

  Swal.fire({
    title: "Are you sure?",
    text: "Do you want to be update this survey!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, update it!",
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        url: "/api/survey/" + id,
        method: "PUT",
        dataType: "JSON",
        contentType: "application/json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
          name: name,
        }),
        success: (result) => {
          $("#updateSurvey").modal("hide");
          $(".modal-backdrop").remove();
          $("#table-survey").DataTable().ajax.reload();
          Swal.fire({
            position: "center",
            icon: "success",
            title: "survey has been updated",
            showConfirmButton: false,
            timer: 1500,
            width: 600,
          });
        },
      });
    }
  });
}
