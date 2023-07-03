$(document).ready(function () {
    $("#table-question").DataTable({
      ajax: {
        url: "/api/question",
        dataSrc: "",
      },
      columns: [
        {
          data: "id",
        },
        {
          data: "body",
        },
        {
          data: null,
          render: function (data, type, row, meta) {
            return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#detailQuestion" onclick = findById(${row.id})>
                  <i class="fa-solid fa-circle-info"></i> Detail
                </button>
  
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateQuestion" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-pen"></i> Update
                </button>
              </div>`;
          },
        },
      ],
    });
  });
  
  function defaults() {
    $("#crt-question-body").removeClass("is-invalid");

    $("#crt-question-body").val("");
  }
  
  function create() {
    let body = $("#crt-question-body").val().trim();
  
    var errors = 0;
    if (body === "") {
      $("#crt-question-body").addClass("is-invalid");
  
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
      }),
      success: (result) => {
        $("#createQuestion").modal("hide");
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
  
  function findById(id) {
    $.ajax({
      url: "/api/question/" + id,
      method: "GET",
      dataType: "JSON",
      success: (result) => {
        $("#question-id").text(`${result.id}`);
        $("#question-body").text(`${result.body}`);
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
      },
    });
  }
  
  function update() {
    let id = $("#upd-question-id").val();
    let body = $("#upd-question-body").val().trim();
  
    var errors = 0;
    if (body === "") {
      $("#upd-question-body").addClass("is-invalid");
  
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
          }),
          success: (result) => {
            $("#updatequestion").modal("hide");
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