// const baseServerURL = `http://localhost:8888`;
// const userRegisterURL = `${baseServerURL}/customers`;




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




const firstname = document.querySelector(".firstname")
const lastname = document.querySelector(".lastname")
const mobileno = document.querySelector(".mobileno")
const email = document.querySelector(".email")
const password = document.querySelector(".password")
const signupbtn = document.querySelector('.signupbtn')
const signinbtn = document.querySelector('.signinbtn')

signupbtn.addEventListener('click', (e) => {
    e.preventDefault()
    let userObj={
        firstName: firstname.value,
        lastName: lastname.value,
        email: email.value,
        password: password.value,
        mobileNumber: mobileno.value,
      }
      console.log(userObj)
      fetch('http://localhost:8888/customers',{
        method:"POST",
        headers:{
          "Content-type":"application/json"
        },
        body:JSON.stringify(userObj)
      })
      .then(res => res.json())
      .then(data => console.log(data))
      .catch(error =>{
console.log(error)
      })
})

signinbtn.addEventListener("click", loginUser);
  
function loginUser(e){
    e.preventDefault()
    const loginObj = {
        email: email.value,
        password: password.value,
      };
    
      // Encode the credentials for Basic Authentication
      const encodedCredentials = btoa(`${loginObj.email}:${loginObj.password}`);
    
      // Set up the fetch options with the Basic Authentication header
      const options = {
        method: "GET",
        headers: {
            "Content-type":"application/json",
          "Authorization": `Basic ${encodedCredentials}`,
        },
      };
    
      // Send the GET request to the /signIn endpoint
      fetch('http://localhost:8888/signIn', options)
        .then(res => {
          if (!res.ok) {
            throw new Error(`Request failed with status ${res.status}`);
          }
          return res.json();
        })
        .then(data => {
          // Handle the response data (customer details)
          console.log(data);
          // Further process the response data as needed
        })
        .catch(error => {
          // Handle any errors that occurred during the request
          console.error('Error during login:', error.message);
          // You can take appropriate actions to handle the error
        });
}  
  