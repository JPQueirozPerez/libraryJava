    function getBooks(){
var els = document.getElementsByClassName("title");
Array.from(els).forEach((el) => {
    console.log("hi");
    console.log(getElementsByClassName("title"));
    fetch("http://openlibrary.org/search.json?q="+document.getElementsByClassName("title").value)
    .then(a=>a.json())
    .then(response=>{
    for(let i=0; i<10; i++){
    document.getElementsByClassName("cover").innerHTML+="<img src='http://covers.openlibrary.org/b/isbn/"+response.docs[i].isbn[0]+"-M.jpg'>";
    }});
    console.log(getElementsByClassName("title").value);
    console.log("bye");
});

}