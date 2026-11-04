// js/update.js
// When the user clicks one of the languages in the list,
// show "You chose <language>" in the paragraph with id "selection".
// Do not change index.html.

var ul = document.getElementById("languages");
var selection = document.getElementById("selection");

ul.onclick = function(event) {
  var listItem = event.target.closest("li");
  if (!listItem) {
    return;
  }
  selection.textContent = "You chose " + listItem.textContent.trim();
};
