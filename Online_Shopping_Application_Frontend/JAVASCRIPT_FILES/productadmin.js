const sideMenu = document.querySelector('aside')
const clicked = document.querySelector('.clicked')
const menuBtn = document.getElementById("menu-btn")
const closeBtn = document.getElementById('close-btn')
const mainSection = document.querySelector("#tbody0");
const darkMode = document.querySelector('.dark-mode')
const mainSection1 = document.querySelector('.new-users')
const jwtToken = JSON.parse(localStorage.getItem("jwtToken")) || null;
console.log(jwtToken)

menuBtn.addEventListener('click', () => {
    sideMenu.style.display = 'block';
})
closeBtn.addEventListener('click', () => {
    sideMenu.style.display = 'none';
})
darkMode.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode-variables')
    darkMode.querySelector('span:nth-child(1)').classList.toggle('active')
    darkMode.querySelector('span:nth-child(2)').classList.toggle('active')
})
clicked.addEventListener('click', () => {
    clicked.classList.toggle('active')
    clicked.classList.toggle('active')
})





window.addEventListener("load", () => {
    fetchAndRenderEmployees('http://localhost:8888/products');
    fetchAndRenderEmployee()
});


//showing data on DOM ↓↓
function fetchAndRenderEmployees(url) {
    fetch(url)
        .then(response => response.json())
        .then(data => displayProduct(data))
        .catch(error => console.error('Error fetching products:', error));
}


function displayProduct(data) {
    // mainSection.innerHTML = ""
    data.forEach((product) => {
        const tr = document.createElement('tr')
        const productId = document.createElement('td')
        productId.innerText = product.productId;

        const productName = document.createElement('td')
        productName.innerText = product.productName;

        const image_URL = document.createElement('img')
        // image_URL = document.createElement('img');
        image_URL.src = product.image_URL;
        image_URL.style.width = "75px";
        image_URL.style.height = "100px";

        const description = document.createElement('td')
        description.innerText = product.description;

        const brand = document.createElement('td')
        brand.innerText = product.brand;

        const quantity = document.createElement('td')
        quantity.innerText = product.quantity;

        const category = document.createElement('td')
        category.innerText = product.category.categoryName;

        const price = document.createElement('td')
        price.innerText = product.price;

        tr.append(productId, productName, image_URL, description, brand, quantity, category, price)
        mainSection.appendChild(tr)
    })
}




//add product
const Pname = document.getElementById("pname")
const Pbrand = document.getElementById("pbrand")
const Pquntity = document.getElementById("pquantity")
const Pprice = document.getElementById("pprice")
const Pcategory = document.getElementById("pcat")
const Pimg = document.getElementById('pimg')
const Pdesc = document.getElementById('pdesc')
const addpbtn = document.getElementById('addpbtn')

addpbtn.addEventListener('click', (e) => {
    e.preventDefault()

    const productToAdd = {
        productName: Pname.value,
        price: Pprice.value,
        image_URL: Pimg.value,
        description: Pdesc.value,
        brand: Pbrand.value,
        quantity: Pquntity.value,
        category: {
            categoryName: Pcategory.value
        },
    };
    console.log(productToAdd)
    const addProductUrl = 'http://localhost:8888/products';

    fetch(addProductUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `bearer ${jwtToken}`
        },
        body: JSON.stringify(productToAdd)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Request failed with status ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Product added successfully:', data);
        })
        .catch(error => {
            console.error('Error adding product:', error.message);
        });
})


//update product
const uPname = document.getElementById("upname")
const uPbrand = document.getElementById("upbrand")
const uPquntity = document.getElementById("upquantity")
const uPprice = document.getElementById("upprice")
const uPcategory = document.getElementById("upcat")
const uPimg = document.getElementById('upimg')
const uPdesc = document.getElementById('updesc')
const pId = document.getElementById('pId');
const updatepbtn = document.getElementById('upproduct'); 

updatepbtn.addEventListener('click', (e) => {
    e.preventDefault();


    const productToUpdate = {
        productId: pId.value,
        productName: uPname.value,
        price: uPprice.value,
        image_URL: uPimg.value,
        description: uPdesc.value,
        brand: uPbrand.value,
        quantity: uPquntity.value,
        category: {
            categoryName: uPcategory.value
        },
    };

    console.log(productToUpdate);

    updateProductById(productToUpdate);
});

function updateProductById(productData) {
    const updateProductUrl = `http://localhost:8888/products`;

    fetch(updateProductUrl, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `bearer ${jwtToken}`
        },
        body: JSON.stringify(productData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Request failed with status ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Product updated successfully:', data);
        })
        .catch(error => {
            console.error('Error updating product:', error.message);
        });
}


//view product
const viewProductIdInput = document.getElementById('viewProductIdInput');
const viewProductBtn = document.getElementById('viewProductBtn');
const productDetailsContainer = document.getElementById('productDetailsContainer');

viewProductBtn.addEventListener('click', (e) => {
    e.preventDefault();

    const productId = viewProductIdInput.value;

    viewProductById(productId);
});

function viewProductById(productId) {
    const viewProductUrl = `http://localhost:8888/products/${productId}`;

    fetch(viewProductUrl, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `bearer ${jwtToken}`
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Request failed with status ${response.status}`);
            }
            return response.json();
        })
        .then(data => viewProduct(data))
        .catch(error => {
            console.error('Error viewing product:', error.message);
        });
}

function viewProduct(product) {
    productDetailsContainer.innerHTML = "";

    const tr = document.createElement('tr')
    const productId = document.createElement('td')
    productId.innerText = product.productId;

    const productName = document.createElement('td')
    productName.innerText = product.productName;

    const image_URL = document.createElement('img')

    image_URL.src = product.image_URL;
    image_URL.style.width = "75px";
    image_URL.style.height = "100px";

    const description = document.createElement('td')
    description.innerText = product.description;

    const brand = document.createElement('td')
    brand.innerText = product.brand;

    const quantity = document.createElement('td')
    quantity.innerText = product.quantity;

    const category = document.createElement('td')
    category.innerText = product.category.categoryName;

    const price = document.createElement('td')
    price.innerText = product.price;

    tr.append(productId, productName, image_URL, description, brand, quantity, category, price)
    productDetailsContainer.appendChild(tr)

}



//Remove product
const removeProductIdInput = document.getElementById('removeProductIdInput');
const removeProductBtn = document.getElementById('removeProductBtn');

removeProductBtn.addEventListener('click', (e) => {
    e.preventDefault();

    const productId = removeProductIdInput.value;

    removeProductById(productId);
});

function removeProductById(productId) {
    const removeProductUrl = `http://localhost:8888/products/${productId}`;

    fetch(removeProductUrl, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `bearer ${jwtToken}`
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Request failed with status ${response.status}`);
            }
            console.log('Product removed successfully.');
        })
        .catch(error => {
            console.error('Error removing product:', error.message);
        });
}






//admin view

function fetchAndRenderEmployee(){
    fetch('http://localhost:8888/customers',{
        method:"GET",
        headers:{
          "Content-type":"application/json"
        }
      })
      .then(res => res.json())
      .then(data => console.log(data))
      .catch(error =>{
      console.log(error)
      })
    // employeesData = data;/)
    
  }
function renderCardList(cardData) {
    let cardList = `
    <h2>New Admins</h2>
      <div class="user-list">
        ${cardData
          .map((item) =>
            getCard(
              item.firstName,
              item.email
            )
          ).join('')}
      </div>
    `;
  
    mainSection1.innerHTML = cardList;
  
  }

function getCard(name,email) {
    let card = `
        <div class="user">
            <img src="/Images/profile.png" alt="">
            <h2>${name}</h2>
            <p>${email}</p>
        </div>
    `;
    return card;
  }