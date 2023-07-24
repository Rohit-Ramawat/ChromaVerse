const logout1 = document.querySelector('#logout');
logout1.addEventListener('click', (e) => {
  e.preventDefault();
  console.log("out")
  localStorage.removeItem('jwtToken');
  alert("Logout successfull");
  window.location.href = "index.html";
});