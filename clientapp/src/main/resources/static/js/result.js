$(document).ready(function () {
    $("#table-result").DataTable({
        ajax: {
            url: "/api/result",
            dataSrc: "",
        },
        columns: [
            {
                data: "id",
            },
            {
                data: "score",
            },
            {
                data: "date",
            },
            {
                data: "mean",
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateResult" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-edit"></i>
                </button>
              </div>`;
                },
            },
        ],
    });
});

function defaults() {
    $("#crt-employee-score").removeClass("is-invalid");
    $("#crt-employee-date").removeClass("is-invalid");
    $("#crt-employee-mean").removeClass("is-invalid");

    $("#crt-employee-score").val("");
    $("#crt-employee-date").val("");
    $("#crt-employee-mean").val("");
}

function create() {
    let score = $("#crt-employee-score").val().trim();
    let date = $("#crt-employee-date").val().trim();
    let mean = $("#crt-employee-mean").val().trim();

    var errors = 0;
    if (score === "") {
        $("#crt-result-score").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    $.ajax({
        url: "/api/result",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        beforSend: addCsrfToken(),
        data: JSON.stringify({
            score: score,
            date: date,
            mean: mean,
        }),
        success: (result) => {
            $("#createResult").modal("hide");
            $(".modal-backdrop").remove();
            $("#table-result").DataTable().ajax.reload();
            defaults();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Result has been created",
                showConfirmButton: false,
                timer: 1500,
                width: 600,
            });
        },
    });
}

function beforeUpdate(id) {
    $.ajax({
        url: "/api/result/" + id,
        method: "GET",
        dataType: "JSON",
        success: (result) => {
            $("#upd-employee-id").val(`${result.id}`);
            $("#upd-employee-score").val(`${result.score}`);
            $("#upd-employee-date").val(`${result.date}`);
            $("#upd-employee-mean").val(`${result.mean}`);
        },
    });
}

function update() {
    let id = $("#upd-employee-id").val();
    let score = $("#upd-employee-score").val().trim();
    let date = $("#upd-employee-date").val().trim();
    let mean = $("#upd-employee-mean").val().trim();

    var errors = 0;
    if (score === "") {
        $("#upd-result-score").addClass("is-invalid");

        errors += 1;
    }

    if (date === "") {
        $("#upd-employee-date").addClass("is-invalid");

        errors += 1;
    }

    if (mean === "") {
        $("#upd-employee-mean").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    Swal.fire({
        title: "Are you sure?",
        text: "Do you want to be update this Result!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, update it!",
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/api/result/" + id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                beforSend: addCsrfToken(),
                data: JSON.stringify({
                    score: score,
                    date: date,
                    mean: mean,
                }),
                success: (result) => {
                    $("#updateResult").modal("hide");
                    $(".modal-backdrop").remove();
                    $("#table-result").DataTable().ajax.reload();
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Result has been updated",
                        showConfirmButton: false,
                        timer: 1500,
                        width: 600,
                    });
                },
            });
        }
    });
}