const resetPassForm = document.getElementById("resetPasswordForm");
resetPassForm.addEventListener("submit", function(event){
    event.preventDefault();
    window.location.href="index.html";
})