  document.addEventListener('click',  function getBooks(){

  // If the clicked element doesn't have the right selector, bail
  	if (!event.target.matches('.coverButton')) return;

  	// Don't follow the link
    	event.preventDefault();

var els = document.getElementsByClassName("title");
Array.from(els).forEach((el) => {
    fetch("http://openlibrary.org/search.json?q="+el.innerText)
    .then(a=>a.json())
    .then(response=>{
    for(let i=0; i<10; i++){
    document.getElementsByClassName("cover").innerHTML+="<img src='http://covers.openlibrary.org/b/isbn/"+response.docs[i].isbn[0]+"-M.jpg'>";
    }});
});

});