$(document).ready(function () {
    $("#table-answer").DataTable({
        ajax: {
            url: "/api/answer",
            dataSrc: "",
        },
        columns: [
            {
                data: "id",
            },
            {
                data: "rating",
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateAnswer" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-pen"></i>
                </button>
              </div>`;
                },
            },
        ],
    });
});

function defaults() {
    $("#crt-answer-rating").removeClass("is-invalid");

    $("#crt-answer-rating").val("");
}

function create() {
    let rating = $("#crt-answer-rating").val().trim();

    var errors = 0;
    if (rating === "") {
        $("#crt-answer-rating").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    $.ajax({
        url: "/api/answer",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        beforSend: addCsrfToken(),
        data: JSON.stringify({
            rating: rating,
        }),
        success: (result) => {
            $("#createAnswer").modal("hide");
            $("#table-answer").DataTable().ajax.reload();
            defaults();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Answer has been created",
                showConfirmButton: false,
                timer: 1500,
                width: 600,
            });
        },
    });
}

function beforeUpdate(id) {
    $.ajax({
        url: "/api/answer/" + id,
        method: "GET",
        dataType: "JSON",
        success: (result) => {
            $("#upd-answer-id").val(`${result.id}`);
            $("#upd-answer-rating").val(`${result.rating}`);
        },
    });
}

function update() {
    let id = $("#upd-answer-id").val();
    let rating = $("#upd-answer-rating").val().trim();

    var errors = 0;
    if (rating === "") {
        $("#upd-answer-rating").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    Swal.fire({
        title: "Are you sure?",
        text: "Do you want to be update this Answer!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, update it!",
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/api/answer/" + id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                beforSend: addCsrfToken(),
                data: JSON.stringify({
                    rating: rating,
                }),
                success: (result) => {
                    $("#updateAnswer").modal("hide");
                    $("#table-answer").DataTable().ajax.reload();
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Answer has been updated",
                        showConfirmButton: false,
                        timer: 1500,
                        width: 600,
                    });
                },
            });
        }
    });
}