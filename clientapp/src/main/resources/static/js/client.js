$(document).ready(function () {
    $("#table-client").DataTable({
        ajax: {
            url: "/api/client",
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
                data: "email",
            },
            {
                data: "location",
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#updateClient" onclick = beforeUpdate(${row.id})>
                  <i class="fa-sharp fa-solid fa-pen"></i>
                </button>
              </div>`;
                },
            },
        ],
    });
});

function defaults() {
    $("#crt-client-name").removeClass("is-invalid");
    $("#crt-client-email").removeClass("is-invalid");
    $("#crt-client-location").removeClass("is-invalid");

    $("#crt-client-name").val("");
    $("#crt-client-email").val("");
    $("#crt-client-location").val("");
}

function create() {
    let name = $("#crt-client-name").val().trim();
    let email = $("#crt-client-email").val().trim();
    let location = $("#crt-client-location").val().trim();

    var errors = 0;
    if (name === "") {
        $("#crt-client-name").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    $.ajax({
        url: "/api/client",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        beforSend: addCsrfToken(),
        data: JSON.stringify({
            name: name,
            email: email,
            location: location,
        }),
        success: (result) => {
            $("#createClient").modal("hide");
            $("#table-client").DataTable().ajax.reload();
            defaults();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Client has been created",
                showConfirmButton: false,
                timer: 1500,
                width: 600,
            });
        },
    });
}

function beforeUpdate(id) {
    $.ajax({
        url: "/api/client/" + id,
        method: "GET",
        dataType: "JSON",
        success: (result) => {
            $("#upd-client-id").val(`${result.id}`);
            $("#upd-client-name").val(`${result.name}`);
            $("#upd-client-email").val(`${result.email}`);
            $("#upd-client-location").val(`${result.location}`);
        },
    });
}

function update() {
    let id = $("#upd-client-id").val();
    let name = $("#upd-client-name").val().trim();
    let email = $("#upd-client-email").val().trim();
    let location = $("#upd-client-location").val().trim();

    var errors = 0;
    if (name === "") {
        $("#upd-client-name").addClass("is-invalid");

        errors += 1;
    }

    if (code === "") {
        $("#upd-client-email").addClass("is-invalid");

        errors += 1;
    }

    if (expired === "") {
        $("#upd-client-location").addClass("is-invalid");

        errors += 1;
    }

    if (errors > 0) {
        return;
    }

    Swal.fire({
        title: "Are you sure?",
        text: "Do you want to be update this Client!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, update it!",
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/api/client/" + id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                beforSend: addCsrfToken(),
                data: JSON.stringify({
                    name: name,
                    email: email,
                    location: location,
                }),
                success: (result) => {
                    $("#updateClient").modal("hide");
                    $("#table-client").DataTable().ajax.reload();
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Client has been updated",
                        showConfirmButton: false,
                        timer: 1500,
                        width: 600,
                    });
                },
            });
        }
    });
}