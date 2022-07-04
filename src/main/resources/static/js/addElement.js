$(function() {
  $('#plus').click(function() {
   if( $('#selectAuthor').length  ){
    // get the last DIV which ID starts with ^= "selectAuthor"
    let $option = $('select[id^="selectAuthor"]:last');
    // Read the Number from that DIV's ID (i.e: 3 from "selectAuthor3")
    // And increment that number by 1
    let num = parseInt( $option.prop("id").match(/\d+/g), 10 ) +1;

        let $author = $('<div th:object="${author}" class="w3-input"> <option th:each="author : ${authorsfromController}" th:value="${author.authorId}" th:text="${author.authorName()}"></option><button type="button" onclick="return this.parentNode.parentNode.remove();" class="w3-button w3-circle w3-red" id="close">x</button></div>').append($('#selectAuthor').clone().prop('id', 'selectAuthor'+num ));
        $('.row').append($author);
        console.log("if");
    }else{
    console.log("else");
       let $author = $('<div th:object="${author}" class="w3-input"> <option th:each="author : ${authorsfromController}" th:value="${author.authorId}" th:text="${author.authorName()}"></option><button type="button" onclick="return this.parentNode.remove();" class="w3-button w3-circle w3-red" id="close">x</button></div>');
       $('.row').append($author);
    }
  });
});
