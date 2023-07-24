// ----------------------product----------------------

document.addEventListener('DOMContentLoaded', function () {
    // Fetch the selected category from local storage
    const selectedCategory = localStorage.getItem('selectedCategory');

    if (selectedCategory) {

        fetch(`http://localhost:8888/products/filter?category=${selectedCategory}`)
            .then(response => response.json())
            .then(products => {
                products.forEach(product => displayProduct(product));
            })
            .catch(error => console.error('Error fetching products:', error));
        // Clear the saved category from local storage if you have used it for your purpose
        localStorage.removeItem('selectedCategory');
    } else {
        fetch('http://localhost:8888/products')
            .then(response => response.json())
            .then(products => {
                products.forEach(product => displayProduct(product));
            })
            .catch(error => console.error('Error fetching products:', error));
    }
});



const productContainer = document.getElementById('productContainer');

function displayProduct(product) {

    const productDiv = document.createElement('div');
    productDiv.className = 'product-info';

    const productName = document.createElement('h1');
    productName.textContent = product.productName;

    const productPrice = document.createElement('p');
    productPrice.className = 'product-price';
    productPrice.textContent = `${'\u20B9'}${product.price}`;

    const productDescription = document.createElement('p');
    productDescription.className = 'product-description';
    productDescription.textContent = product.description;

    const productBrand = document.createElement('p');
    productBrand.textContent = `Brand: ${product.brand}`;
    const productImage = document.createElement('img');
    productImage.src = product.image_URL

    const addToCartButton = document.createElement('button');
    addToCartButton.textContent = 'Add to Cart';
    addToCartButton.addEventListener('click', () => addToCart(product));

    productDiv.appendChild(productImage);
    productDiv.appendChild(productName);
    productDiv.appendChild(productPrice);
    productDiv.appendChild(productBrand);
    productDiv.appendChild(productDescription);
    productDiv.appendChild(addToCartButton);

    productContainer.appendChild(productDiv);
}


//add to cart
const jwtToken = JSON.parse(localStorage.getItem("jwtToken")) || null;

function addToCart(product) {
    if (jwtToken) {
        const cart = JSON.parse(localStorage.getItem("cart"));

        fetch(`http://localhost:8888/carts/${cart.cartId}?productId=${product.productId}&quantity=1`, {
            method: 'POST',
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
            .then(data => {
                alert('Product added successfully:', data);
            })
            .catch(error => {
                console.error('Error adding product:', error.message);
            });
    } else {
        alert("Please Login First");
    }
}



//-------------filter---------------

//-------------filter-category-checkbox---------------
// Fetch categories from the backend API and populate checkboxes dynamically
function fetchCategoriesAndPopulateCheckboxes() {
    fetch('http://localhost:8888/products/category')
        .then(response => response.json())
        .then(data => {
            const categoryCheckBox = document.getElementById('categoryCheckBox');
            categoryCheckBox.innerHTML = ''; // Clear previous checkboxes

            data.forEach(category => {
                const checkboxLabel = document.createElement('label');
                checkboxLabel.innerHTML = `
            <input type="checkbox" name="categoryCheckbox" value="${category.categoryName}">
            ${category.categoryName}
          `;

                categoryCheckBox.appendChild(checkboxLabel);
            });
        })
        .catch(error => console.error('Error fetching categories:', error));
}

// Fetch categories and populate checkboxes when the page loads
document.addEventListener('DOMContentLoaded', () => {
    fetchCategoriesAndPopulateCheckboxes();
});


//-------------filter-brand-checkbox---------------
function populateCheckboxes(data, containerId, nameAttr) {
    const container = document.getElementById(containerId);

    container.innerHTML = ''; // Clear previous checkboxes

    data.forEach(item => {
        const checkboxLabel = document.createElement('label');
        checkboxLabel.innerHTML = `
        <input type="checkbox" name="${nameAttr}" value="${item}">
        ${item}
      `;

        container.appendChild(checkboxLabel);
    });
}

// Function to fetch brands data from the backend API
function fetchBrands() {
    fetch('http://localhost:8888/products/brand')
        .then(response => response.json())
        .then(data => {
            // Populate brand checkboxes with the fetched data
            populateCheckboxes(data, 'brandCheckBox', 'brandCheckbox');
        })
        .catch(error => console.error('Error fetching brands:', error));
}

// Fetch brands and populate checkboxes when the page loads
document.addEventListener('DOMContentLoaded', () => {
    fetchBrands();
});



// ---------main-filter--------------

const applyButton = document.getElementById('applyButton');
applyButton.addEventListener('click', applyFilter);

// Event listener for the sort select element
document.getElementById('sortPrice').addEventListener('change', applyFilter);

function applyFilter() {
    const selectedCategories = Array.from(document.querySelectorAll('input[name="categoryCheckbox"]:checked')).map(checkbox => checkbox.value);
    const selectedBrands = Array.from(document.querySelectorAll('input[name="brandCheckbox"]:checked')).map(checkbox => checkbox.value);
    const minPrice = parseFloat(document.getElementById('filterMinPrice').value);
    const maxPrice = parseFloat(document.getElementById('filterMaxPrice').value);

    // Build the API URL with selected filters and sorting
    let apiUrl = 'http://localhost:8888/products/filter?';

    if (selectedCategories.length > 0) {
        apiUrl += `category=${selectedCategories.join(',')}&`;
    }

    if (selectedBrands.length > 0) {
        apiUrl += `brand=${selectedBrands.join(',')}&`;
    }

    if (!isNaN(minPrice)) {
        apiUrl += `minPrice=${minPrice}&`;
    }

    if (!isNaN(maxPrice)) {
        apiUrl += `maxPrice=${maxPrice}&`;
    }

    // Get the selected sort order from a dropdown or any other UI element
    const sortSelect = document.getElementById('sortPrice');
    const selectedOption = sortSelect.value;
    let sortParameter = '';
    if (selectedOption === 'asc') {
        sortParameter = 'asc';
    } else if (selectedOption === 'desc') {
        sortParameter = 'desc';
    }

    apiUrl += `sortOrder=${sortParameter}`;

    // Remove the trailing '&' from the URL
    //  apiUrl = apiUrl.slice(0, -1);

    // Fetch filtered and sorted products and update the display
    fetch(apiUrl)
        .then(response => response.json())
        .then(products => {
            productContainer.innerHTML = "";
            products.forEach(product => displayProduct(product));
        })
        .catch(error => console.error('Error fetching filtered products:', error));
}




const clearButton = document.getElementById('clearButton');
clearButton.addEventListener('click', () => {
    // Clear all checkboxes and input fields
    document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => checkbox.checked = false);
    document.getElementById('filterMinPrice').value = '';
    document.getElementById('filterMaxPrice').value = '';

    fetch('http://localhost:8888/products')
        .then(response => response.json())
        .then(products => {
            productContainer.innerHTML = "";
            products.forEach(product => displayProduct(product));
        })
        .catch(error => console.error('Error fetching products:', error));
});


















