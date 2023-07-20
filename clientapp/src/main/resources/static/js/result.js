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
          let statusName = row.status ? row.status.name : "";
          let isReviewed = statusName === "Reviewed";

          let iconClass = isReviewed
            ? "fa-sharp fa-solid fa-check-circle"
            : "fa-sharp fa-solid fa-info-circle";

          let btnClass = isReviewed ? "btn btn-success" : "btn btn-primary";

          return `  
            <div class="d-flex align-items-center justify-content-center gap-3">
              <a href="/result/${row.id}" type="button" class="${btnClass}">
                <i class="${iconClass}"></i>
              </a>
            </div>`;
        },
      },
    ],
  });
});
