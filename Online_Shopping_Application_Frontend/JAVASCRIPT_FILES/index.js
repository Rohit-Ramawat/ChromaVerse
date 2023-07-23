const products_container = document.querySelector(".products-container")
const menuBtn = document.querySelector(".menu-btn")
const menuBtnDivs = document.querySelectorAll(".menu-btn div")
const navigations = document.querySelector(".navigations")

let product_items = [
    {
        product_name: "Bedroom Lamp",
        product_img: "./images/lamp-bedroom.jpg",
        product_link: "",
        product_price: "$25.00"
    },
    {
        product_name: "Vertical Shot Wooden Chair",
        product_img: "./images/vertical-shot-wooden-chair.jpg",
        product_link: "",
        product_price: "$47.00"
    },
    {
        product_name: "Clothing Stand",
        product_img: "./images/clothing-stand.jpg",
        product_link: "",
        product_price: "$132.00"
    },
    {
        product_name: "White Lamp",
        product_img: "./images/white-light-lamp.jpg",
        product_link: "",
        product_price: "$55.00"
    },
    {
        product_name: "Tripod Floor Lamp",
        product_img: "./images/tripod-floor-lamp-with-chair.jpg",
        product_link: "",
        product_price: "$28.00"
    },
    {
        product_name: "Nightstand",
        product_img: "./images/nightstand.jpg",
        product_link: "",
        product_price: "$175.00"
    },
    {
        product_name: "Floor Table Lamp",
        product_img: "./images/vector-set-floor-table-lamps.jpg",
        product_link: "",
        product_price: "$65.00"
    },
    {
        product_name: "Vertical Shot Chair",
        product_img: "./images/vertical-shot-chair-with-net.jpg",
        product_link: "",
        product_price: "$46.00"
    },
]

window.addEventListener("resize", () => {
    navigations.classList.remove("active")
    menuBtnDivs.forEach((div) => {
        div.classList.remove('active')
    })

})

menuBtn.addEventListener("click", () => {
    menuBtnDivs.forEach((div) => {
        div.classList.toggle('active')
    })
    navigations.classList.toggle('active')
})

product_items ? product_items.forEach(({ product_name, product_img, product_price, product_link }) => {
    const product_item = document.createElement('div')
    product_item.className = "product-item"

    product_item.innerHTML = `<img src="${product_img}"  alt="" class="product-img">
                <div class="product-details">
                <a href="${product_link}">     
                    <h3 class="item-name">${product_name}</h3>
                    <span class="item-price">${product_price}</span>
                <a/>
                </div>
                `
    products_container.appendChild(product_item)
}) : console.log("No item available")