$(document).ready(function () {
  $("#table-question").DataTable({
    ajax: {
      url: "/api/question",
      dataSrc: "",
    },
    columns: [
      {
        data: null,
        render: function (data, type, row, meta) {
          var index = meta.row + 1;
          return index;
        },
      },
      {
        data: "body",
      },
      {
        data: null,
        render: function (data, type, row, meta) {
          return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateQuestion" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-edit"></i>
                </button>
              </div>`;
        },
      },
    ],
  });
  $.ajax({
    url: "/api/section/",
    method: "GET",
    dataType: "JSON",
    success: (result) => {
      $.each(result, (key, value) => {
        $(".dropdown-section").append(
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
  $("#crt-question-body").removeClass("is-invalid");
  $("#crt-question-section").removeClass("is-invalid");

  $("#crt-question-body").val("");
  $("#crt-question-section").val("");
}

function create() {
  let body = $("#crt-question-body").val().trim();
  let section = $("#crt-question-section").val();

  var errors = 0;
  if (body === "") {
    $("#crt-question-body").addClass("is-invalid");

    errors += 1;
  }

  if (section === "") {
    $("#crt-question-section").addClass("is-invalid");

    errors += 1;
  }

  if (errors > 0) {
    return;
  }

  $.ajax({
    url: "/api/question",
    method: "POST",
    dataType: "JSON",
    contentType: "application/json",
    beforSend: addCsrfToken(),
    data: JSON.stringify({
      body: body,
      section: {
        id: section,
      },
    }),
    success: (result) => {
      $("#createQuestion").modal("hide");
      $(".modal-backdrop").remove();
      $("#table-question").DataTable().ajax.reload();
      defaults();
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Question has been created",
        showConfirmButton: false,
        timer: 1500,
        width: 600,
      });
    },
  });
}

function beforeUpdate(id) {
  $.ajax({
    url: "/api/question/" + id,
    method: "GET",
    dataType: "JSON",
    success: (result) => {
      $("#upd-question-id").val(`${result.id}`);
      $("#upd-question-body").val(`${result.body}`);
      $("#upd-question-section").val(`${result.section.id}`);
    },
  });
}

function update() {
  let id = $("#upd-question-id").val();
  let body = $("#upd-question-body").val().trim();
  let section = $("#upd-question-section").val();

  var errors = 0;
  if (body === "") {
    $("#upd-question-body").addClass("is-invalid");

    errors += 1;
  }

  if (section === "") {
    $("#upd-question-section").addClass("is-invalid");

    errors += 1;
  }

  if (errors > 0) {
    return;
  }

  Swal.fire({
    title: "Are you sure?",
    text: "Do you want to be update this Question!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, update it!",
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        url: "/api/question/" + id,
        method: "PUT",
        dataType: "JSON",
        contentType: "application/json",
        beforSend: addCsrfToken(),
        data: JSON.stringify({
          body: body,
          section: {
            id: section,
          },
        }),
        success: (result) => {
          $("#updateQuestion").modal("hide");
          $(".modal-backdrop").remove();
          $("#table-question").DataTable().ajax.reload();
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Question has been updated",
            showConfirmButton: false,
            timer: 1500,
            width: 600,
          });
        },
      });
    }
  });
}
