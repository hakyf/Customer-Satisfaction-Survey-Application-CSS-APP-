$(document).ready(function () {
    $("#table-role").DataTable({
        ajax: {
            url: "/api/role",
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
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateRole" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-pen"></i>
                </button>
              </div>`;
                },
            },
        ],
    });
});

function defaults() {
    $("#crt-role-name").removeClass("is-invalid");

    $("#crt-role-name").val("");
}

function create() {
    let name = $("#crt-role-name").val().trim();

    var errors = 0;
    if (name === "") {
        $("#crt-role-name").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    $.ajax({
        url: "/api/role",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        beforSend: addCsrfToken(),
        data: JSON.stringify({
            name: name,
        }),
        success: (result) => {
            $("#createRole").modal("hide");
            $("#table-role").DataTable().ajax.reload();
            defaults();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Role has been created",
                showConfirmButton: false,
                timer: 1500,
                width: 600,
            });
        },
    });
}

function beforeUpdate(id) {
    $.ajax({
        url: "/api/role/" + id,
        method: "GET",
        dataType: "JSON",
        success: (result) => {
            $("#upd-role-id").val(`${result.id}`);
            $("#upd-role-name").val(`${result.name}`);
        },
    });
}

function update() {
    let id = $("#upd-role-id").val();
    let name = $("#upd-role-name").val().trim();

    var errors = 0;
    if (name === "") {
        $("#upd-role-name").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    Swal.fire({
        title: "Are you sure?",
        text: "Do you want to be update this Role!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, update it!",
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/api/role/" + id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                beforSend: addCsrfToken(),
                data: JSON.stringify({
                    name: name,
                }),
                success: (result) => {
                    $("#updaterole").modal("hide");
                    $("#table-role").DataTable().ajax.reload();
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Role has been updated",
                        showConfirmButton: false,
                        timer: 1500,
                        width: 600,
                    });
                },
            });
        }
    });
}