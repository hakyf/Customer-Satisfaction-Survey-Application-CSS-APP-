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
                data: "status.name",
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
    $("#crt-survey-name").removeClass("is-invalid");

    $("#crt-survey-name").val("");
}

function create() {
    let name = $("#crt-survey-name").val().trim();

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
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: name,
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