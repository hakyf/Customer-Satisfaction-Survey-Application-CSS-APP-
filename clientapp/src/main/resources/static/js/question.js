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
              <div class="d-flex align-items-center gap-3">
                <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#detailQuestion" onclick = findById(${row.id})>
                  <i class="fa-solid fa-circle-info"></i> Detail
                </button>
  
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateQuestion" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-pen"></i> Update
                </button>
                
                <button class="btn btn-danger" onclick="deleteQuestion(${row.id})"><i class="fa-sharp fa-solid fa-trash"></i> Delete</button>
              </div>`;
          },
        },
      ],
    });
  });
  
  function defaults() {
    $("#crt-question-name").removeClass("is-invalid");
  
    $("#crt-question-name").val("");
  }
  
  function create() {
    let name = $("#crt-question-name").val().trim();
  
    var errors = 0;
    if (name === "") {
      $("#crt-question-name").addClass("is-invalid");
  
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
        name: name,
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
        $("#question-name").text(`${result.name}`);
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
        $("#upd-question-name").val(`${result.name}`);
      },
    });
  }
  
  function update() {
    let id = $("#upd-question-id").val();
    let name = $("#upd-question-name").val().trim();
  
    var errors = 0;
    if (name === "") {
      $("#upd-question-name").addClass("is-invalid");
  
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
            name: name,
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
  
  function deletequestion(id) {
    Swal.fire({
      title: "Are you sure?",
      text: "Do you want to be delete this Question!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!",
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          url: "/api/question/" + id,
          method: "DELETE",
          dataType: "JSON",
          beforSend: addCsrfToken(),
          success: (result) => {
            $("#table-question").DataTable().ajax.reload();
            Swal.fire({
              position: "center",
              icon: "success",
              title: "Question has been deleted",
              showConfirmButton: false,
              timer: 1500,
              width: 600,
            });
          },
        });
      }
    });
  }
  