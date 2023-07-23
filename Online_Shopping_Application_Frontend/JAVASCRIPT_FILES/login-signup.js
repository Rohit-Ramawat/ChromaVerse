const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");
const container1 = document.querySelector(".container1");
const container2 = document.querySelector(".container2");
const sign_in_btn2 = document.querySelector("#sign-in-btn2");
const sign_up_btn2 = document.querySelector("#sign-up-btn2");
const sign_up_admin = document.querySelector("#sign-up-admin");
const sign_up_user = document.querySelector("#sign-up-user");
sign_up_btn.addEventListener("click", () => {
    container.classList.add("sign-up-mode");
});
sign_in_btn.addEventListener("click", () => {
    container.classList.remove("sign-up-mode");
});
sign_up_btn2.addEventListener("click", () => {
    container.classList.add("sign-up-mode2");
});
sign_in_btn2.addEventListener("click", () => {
    container.classList.remove("sign-up-mode2");
});
sign_up_admin.addEventListener("click", () => {
    container1.classList.add("display");
    container2.classList.remove("display");
});
sign_up_user.addEventListener("click", () => {
    container1.classList.remove("display");
    container2.classList.add("display");
});
