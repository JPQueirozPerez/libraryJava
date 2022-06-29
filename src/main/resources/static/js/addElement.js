$(function() {
  $('#plus').click(function() {
     // get the last DIV which ID starts with ^= "klon"
      let $option = $('select[id^="selectAuthor"]:last');

      // Read the Number from that DIV's ID (i.e: 3 from "klon3")
      // And increment that number by 1
      var num = parseInt( $option.prop("id").match(/\d+/g), 10 ) +1;
    console.log("click");
    var $author = $('<div th:object="${author}" class="w3-input"><option th:each="author : ${authorsFromController}" th:value="${author.authorId}" th:text="${author.authorName()}"></option> </div>').append($('#selectAuthor').clone().prop('id', 'selectAuthor'+num ));
    console.log($('select').prop("id"));
    $('.row').append($author);
  });
});

