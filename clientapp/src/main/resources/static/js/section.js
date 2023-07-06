$(document).ready(function () {
    $("#table-section").DataTable({
        ajax: {
            url: "/api/section",
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
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateSection" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-edit"></i>
                </button>
              </div>`;
                },
            },
        ],
    });
});

function defaults() {
    $("#crt-section-name").removeClass("is-invalid");

    $("#crt-section-name").val("");
}

function create() {
    let name = $("#crt-section-name").val().trim();

    var errors = 0;
    if (name === "") {
        $("#crt-section-name").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    $.ajax({
        url: "/api/section",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: name,
        }),
        success: (result) => {
            $("#createSection").modal("hide");
            $(".modal-backdrop").remove();
            $("#table-section").DataTable().ajax.reload();
            defaults();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Section has been created",
                showConfirmButton: false,
                timer: 1500,
                width: 600,
            });
        },
    });
}

function beforeUpdate(id) {
    $.ajax({
        url: "/api/section/" + id,
        method: "GET",
        dataType: "JSON",
        success: (result) => {
            $("#upd-section-id").val(`${result.id}`);
            $("#upd-section-name").val(`${result.name}`);
        },
    });
}

function update() {
    let id = $("#upd-section-id").val();
    let name = $("#upd-section-name").val().trim();

    var errors = 0;
    if (name === "") {
        $("#upd-section-name").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    Swal.fire({
        title: "Are you sure?",
        text: "Do you want to be update this Section!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, update it!",
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/api/section/" + id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                beforSend: addCsrfToken(),
                data: JSON.stringify({
                    name: name,
                }),
                success: (result) => {
                    $("#updateSection").modal("hide");
                    $(".modal-backdrop").remove();
                    $("#table-section").DataTable().ajax.reload();
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Section has been updated",
                        showConfirmButton: false,
                        timer: 1500,
                        width: 600,
                    });
                },
            });
        }
    });
}