$(document).ready(function () {
  $("#table-history").DataTable({
    ajax: {
      url: "/api/history",
      dataSrc: "",
    },
    columns: [
      {
        data: "id",
      },
      {
        data: "notes",
      },
      {
        data: "date",
      },
      {
        data: "employee.name",
      },
      {
        data: "status.name",
      },
      {
        data: null,
        render: function (data, type, row, meta) {
          return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateEmployee" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-edit"></i>
                </button>
              </div>`;
        },
      },
    ],
  });
});

function defaults() {
  $("#crt-employee-name").removeClass("is-invalid");
  $("#crt-employee-email").removeClass("is-invalid");
  $("#crt-employee-phone").removeClass("is-invalid");
  $("#crt-employee-jobPosition").removeClass("is-invalid");

  $("#crt-employee-name").val("");
  $("#crt-employee-email").val("");
  $("#crt-employee-phone").val("");
  $("#crt-employee-jobPosition").val("");
}

function create() {
  let name = $("#crt-employee-name").val().trim();
  let email = $("#crt-employee-email").val().trim();
  let phone = $("#crt-employee-phone").val().trim();
  let jobPosition = $("#crt-employee-jobPosition").val().trim();

  var errors = 0;
  if (name === "") {
    $("#crt-employee-name").addClass("is-invalid");

    errors += 1;
  }

  if (errors > 0) {
    return;
  }

  $.ajax({
    url: "/api/employee",
    method: "POST",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCsrfToken(),
    data: JSON.stringify({
      name: name,
      email: email,
      phone: phone,
      jobPosition: jobPosition,
    }),
    success: (result) => {
      $("#createEmployee").modal("hide");
      $(".modal-backdrop").remove();
      $("#table-employee").DataTable().ajax.reload();
      defaults();
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Employee has been created",
        showConfirmButton: false,
        timer: 1500,
        width: 600,
      });
    },
  });
}

function beforeUpdate(id) {
  $.ajax({
    url: "/api/employee/" + id,
    method: "GET",
    dataType: "JSON",
    success: (result) => {
      $("#upd-employee-id").val(`${result.id}`);
      $("#upd-employee-name").val(`${result.name}`);
      $("#upd-employee-email").val(`${result.email}`);
      $("#upd-employee-phone").val(`${result.phone}`);
      $("#upd-employee-jobPosition").val(`${result.jobPosition}`);
    },
  });
}

function update() {
  let id = $("#upd-employee-id").val();
  let name = $("#upd-employee-name").val().trim();
  let email = $("#upd-employee-email").val().trim();
  let phone = $("#upd-employee-phone").val().trim();
  let jobPosition = $("#upd-employee-jobPosition").val().trim();

  var errors = 0;
  if (name === "") {
    $("#upd-employee-name").addClass("is-invalid");

    errors += 1;
  }

  if (email === "") {
    $("#upd-employee-email").addClass("is-invalid");

    errors += 1;
  }

  if (phone === "") {
    $("#upd-employee-phone").addClass("is-invalid");

    errors += 1;
  }

  if (jobPosition === "") {
    $("#upd-employee-jobPosition").addClass("is-invalid");

    errors += 1;
  }

  if (errors > 0) {
    return;
  }

  Swal.fire({
    title: "Are you sure?",
    text: "Do you want to be update this Employee!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, update it!",
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        url: "/api/employee/" + id,
        method: "PUT",
        dataType: "JSON",
        contentType: "application/json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
          name: name,
          email: email,
          phone: phone,
          jobPosition: jobPosition,
        }),
        success: (result) => {
          $("#updateEmployee").modal("hide");
          $(".modal-backdrop").remove();
          $("#table-employee").DataTable().ajax.reload();
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Employee has been updated",
            showConfirmButton: false,
            timer: 1500,
            width: 600,
          });
        },
      });
    }
  });
}