function submit() {
  var code = $(".survey-code").val();
  var trElements = $("#survey-form .survey-tr");

  var trElementsTableKedua = $(trElements)
    .find(".question-tbl")
    .find(".question-tr");

  var questionArray = new Array();

  trElementsTableKedua.each(function () {
    var questionId = $(this).find(".question-id").val();
    var answer = $(this).find(".radio-answer:checked").val();

    if (answer === undefined) {
      answer = $(this).find(".textarea-answer").val();
    }

    var qar = {
      questionId: questionId,
      answer: answer,
    };
    questionArray.push(qar);
  });
  $.ajax({
    url: "/api/survey/answer/" + code, // Ganti dengan URL REST API yang sesuai
    type: "POST",
    data: JSON.stringify(questionArray),
    contentType: "application/json",
    beforeSend: addCsrfToken(),
    success: function (response) {
      window.location.href = `http://localhost:8088/survey/c/${code}/success`;
    },
    error: function (xhr, status, error) {
      // Terjadi kesalahan dalam pengiriman data survei
      console.log("Terjadi kesalahan dalam pengiriman data survei");
    },
  });
  console.log(questionArray);
}
