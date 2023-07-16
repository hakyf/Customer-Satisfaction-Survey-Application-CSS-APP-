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
        data: "date",
      },
      {
        data: "score",
      },
      {
        data: "mean",
      },
      {
        data: null,
        render: function (data, type, row, meta) {
          return `  
              <div class="d-flex align-items-center justify-content-center gap-3">
                <a href="/result/${row.id}" type="button" class="btn btn-success">
                  <i class="fa-sharp fa-solid fa-info-circle"></i>
                </a>
              </div>`;
        },
      },
    ],
  });
});
