$(document).ready(function () {
  $("#table-result").DataTable({
    ajax: {
      url: "/api/result",
      dataSrc: "",
    },
    columns: [
      {
        data: null,
        render: function (data, type, row, meta) {
          var index = meta.row + 1;
          return index;
        },
      },
      {
        data: "client.name",
      },
      {
        data: "employee.name",
      },
      {
        data: "employee.jobPosition",
      },
      {
        data: "result.date",
      },
      {
        data: "result.score",
      },
      {
        data: "result.mean",
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
