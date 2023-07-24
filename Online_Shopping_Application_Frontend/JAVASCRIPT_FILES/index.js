//nav bar
const menuBtn = document.querySelector(".menu-btn")
const menuBtnDivs = document.querySelectorAll(".menu-btn div")
const navigations = document.querySelector(".navigations")
menuBtn.addEventListener("click", () => {
  menuBtnDivs.forEach((div) => {
    div.classList.toggle('active')
  })
  navigations.classList.toggle('active')
})

//sliding window
// Wait for the document to be ready
document.addEventListener("DOMContentLoaded", function () {
  const slider = document.querySelector(".slider");
  const slides = document.querySelectorAll(".slide");
  const slideWidth = slides[0].offsetWidth; // Get the width of each slide

  let currentSlide = 0;

  // Function to move the slider to the next slide
  function moveToNextSlide() {
    currentSlide++;
    if (currentSlide >= slides.length) {
      currentSlide = 0;
    }
    updateSliderPosition();
  }

  // Function to update the slider position based on the current slide
  function updateSliderPosition() {
    slider.style.transform = `translateX(-${currentSlide * slideWidth}px)`;
  }

  // Automatically move to the next slide every few seconds
  const interval = 3000; // Set the time interval between slides (in milliseconds)
  setInterval(moveToNextSlide, interval);
});



//categories

document.addEventListener('DOMContentLoaded', function () {
  const categoryLinks = document.querySelectorAll('.circle-container a');

  categoryLinks.forEach(link => {
    link.addEventListener('click', function (event) {
      event.preventDefault(); 
      const category = link.getAttribute('href').split('=')[1]; // Get the category from the href attribute

      localStorage.setItem('selectedCategory', category);
      window.location.href = `/Online_Shopping_Application_Frontend/HTML_FILES/Product_Page.html`;

    });
  });
});

