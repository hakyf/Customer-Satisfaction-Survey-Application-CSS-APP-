$(document).ready(function () {
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
                data: "code",
            },
            {
                data: "expired",
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateSurvey" onclick = beforeUpdate(${row.id})>
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
    $("#crt-employee-code").removeClass("is-invalid");
    $("#crt-employee-expired").removeClass("is-invalid");

    $("#crt-employee-name").val("");
    $("#crt-employee-code").val("");
    $("#crt-employee-expired").val("");
}

function create() {
    let name = $("#crt-employee-name").val().trim();
    let code = $("#crt-employee-code").val().trim();
    let expired = $("#crt-employee-expired").val().trim();

    var errors = 0;
    if (name === "") {
        $("#crt-survey-name").addClass("is-invalid");

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
        beforSend: addCsrfToken(),
        data: JSON.stringify({
            name: name,
            code: code,
            expired: expired,
        }),
        success: (result) => {
            $("#createSurvey").modal("hide");
            $(".modal-backdrop").remove();
            $("#table-survey").DataTable().ajax.reload();
            defaults();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Survey has been created",
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
            $("#upd-employee-id").val(`${result.id}`);
            $("#upd-employee-name").val(`${result.name}`);
            $("#upd-employee-code").val(`${result.code}`);
            $("#upd-employee-expired").val(`${result.expired}`);
        },
    });
}

function update() {
    let id = $("#upd-employee-id").val();
    let name = $("#upd-employee-name").val().trim();
    let code = $("#upd-employee-code").val().trim();
    let expired = $("#upd-employee-expired").val().trim();

    var errors = 0;
    if (name === "") {
        $("#upd-survey-name").addClass("is-invalid");

        errors += 1;
    }

    if (code === "") {
        $("#upd-employee-code").addClass("is-invalid");

        errors += 1;
    }

    if (expired === "") {
        $("#upd-employee-expired").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    Swal.fire({
        title: "Are you sure?",
        text: "Do you want to be update this Survey!",
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
                beforSend: addCsrfToken(),
                data: JSON.stringify({
                    name: name,
                    code: code,
                    expired: expired,
                }),
                success: (result) => {
                    $("#updateSurvey").modal("hide");
                    $(".modal-backdrop").remove();
                    $("#table-survey").DataTable().ajax.reload();
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Survey has been updated",
                        showConfirmButton: false,
                        timer: 1500,
                        width: 600,
                    });
                },
            });
        }
    });
}