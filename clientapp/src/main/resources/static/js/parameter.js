$(document).ready(function () {
    $("#table-parameter").DataTable({
      ajax: {
        url: "/api/parameter",
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
          data: "value",
        },
        {
        
          render: function (data, type, row, meta) {
            return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateParameter" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-pen"></i>
                </button>
              </div>`;
          },
        },
      ],
    });
  });
  
  function defaults() {
    $("#crt-parameter-notes").removeClass("is-invalid");
    $("#crt-parameter-value").removeClass("is-invalid");
 
    $("#crt-parameter-notes").val("");
    $("#crt-parameter-value").val("");
    }
  
  function create() {
    let notes = $("#crt-parameter-notes").val().trim();
    let value = $("#crt-parameter-value").val().trim();
    
    var errors = 0;
    if (notes === "") {
      $("#crt-parameter-notes").addClass("is-invalid");
  
      errors += 1;
    }
  
    if (errors > 0) {
      return;
    }
  
    $.ajax({
      url: "/api/parameter",
      method: "POST",
      dataType: "JSON",
      contentType: "application/json",
      beforeSend: addCsrfToken(),
      data: JSON.stringify({
        notes: notes,
        value: value,
      }),
      success: (result) => {
        $("#createParameter").modal("hide");
        $("#table-parameter").DataTable().ajax.reload();
        defaults();
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Parameter has been created",
          showConfirmButton: false,
          timer: 1500,
          width: 600,
        });
      },
    });
  }
  
  function beforeUpdate(id) {
    $.ajax({
      url: "/api/parameter/" + id,
      method: "GET",
      dataType: "JSON",
      success: (result) => {
        $("#upd-parameter-id").val(`${result.id}`);
        $("#upd-parameter-notes").val(`${result.notes}`);
        $("#upd-parameter-value").val(`${result.value}`);
      
      },
    });
  }
  
  function update() {
    let id = $("#upd-parameter-id").val();
    let notes = $("#upd-parameter-notes").val().trim();
    let value = $("#upd-parameter-value").val().trim();
   
  
    var errors = 0;
    if (notes === "") {
      $("#upd-parameter-notes").addClass("is-invalid");
  
      errors += 1;
    }
  
    if (value === "") {
      $("#upd-parameter-value").addClass("is-invalid");
  
      errors += 1;
    }


    if (errors > 0) {
      return;
    }
  
    Swal.fire({
      title: "Are you sure?",
      text: "Do you want to be update this Parameter!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, update it!",
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          url: "/api/parameter/" + id,
          method: "PUT",
          dataType: "JSON",
          contentType: "application/json",
          beforSend: addCsrfToken(),
          data: JSON.stringify({
            notes: notes,
            value: value,
          
          }),
          success: (result) => {
            $("#updateParameter").modal("hide");
            $("#table-parameter").DataTable().ajax.reload();
            Swal.fire({
              position: "center",
              icon: "success",
              title: "Parameter has been updated",
              showConfirmButton: false,
              timer: 1500,
              width: 600,
            });
          },
        });
      }
    });
  }
  