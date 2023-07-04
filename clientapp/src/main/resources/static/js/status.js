$(document).ready(function () {
    $("#table-status").DataTable({
        ajax: {
            url: "/api/status",
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
                data: null,
                render: function (data, type, row, meta) {
                    return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateStatus" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-pen"></i>
                </button>
              </div>`;
                },
            },
        ],
    });
});

function defaults() {
    $("#crt-status-name").removeClass("is-invalid");

    $("#crt-status-name").val("");
}

function create() {
    let name = $("#crt-status-name").val().trim();

    var errors = 0;
    if (name === "") {
        $("#crt-status-name").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    $.ajax({
        url: "/api/status",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        beforSend: addCsrfToken(),
        data: JSON.stringify({
            name: name,
        }),
        success: (result) => {
            $("#createStatus").modal("hide");
            $("#table-status").DataTable().ajax.reload();
            defaults();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Status has been created",
                showConfirmButton: false,
                timer: 1500,
                width: 600,
            });
        },
    });
}

function beforeUpdate(id) {
    $.ajax({
        url: "/api/status/" + id,
        method: "GET",
        dataType: "JSON",
        success: (result) => {
            $("#upd-status-id").val(`${result.id}`);
            $("#upd-status-name").val(`${result.name}`);
        },
    });
}

function update() {
    let id = $("#upd-status-id").val();
    let name = $("#upd-status-name").val().trim();

    var errors = 0;
    if (name === "") {
        $("#upd-status-name").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    Swal.fire({
        title: "Are you sure?",
        text: "Do you want to be update this Status!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, update it!",
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/api/status/" + id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                beforSend: addCsrfToken(),
                data: JSON.stringify({
                    name: name,
                }),
                success: (result) => {
                    $("#updatestatus").modal("hide");
                    $("#table-status").DataTable().ajax.reload();
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Status has been updated",
                        showConfirmButton: false,
                        timer: 1500,
                        width: 600,
                    });
                },
            });
        }
    });
}