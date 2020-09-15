function validateForm() {
  var x = document.forms["numero"]["id"].value;
  if (isNaN(parseFloat(x))) {
    alert("PONGA UN NUMERO!!!!!!!!!!!!!!!!!!");
    return false;
  }
  return true;
}