
const headers = {
    Authorization : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJSYW0iLCJzdWIiOiJKV1QgVG9rZW4iLCJ1c2VybmFtZSI6ImFqaXRAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJpYXQiOjE2OTAxMjU5NDMsImV4cCI6MTY5MDE1NTk0M30.wejTMk7duF7oWkgF4b52wgONwMxfC76602NkgDtpg7U",
    "Content-Type": "application/json",
};

fetch('http://localhost:8888/carts/view/1', {
method:"GET",
headers: headers,
})
.then((res) =>{
    if(!res.ok){
        throw new Error("Response Not Found");
    }
    return res.json();
})
.then((data) => {
    console.log(data)
    displayProduct(data);
    updateCartSummary(data);
})
.catch((error) => {
    console.error("Error Fetching...")
})

const productContainer = document.getElementById('cart-products');
const cartprice = document.getElementsByClassName("price");
const cartquantity = document.getElementsByClassName("quantity");
const carttotal = document.getElementsByClassName("total");

function displayProduct(products) {
    const productContainer = document.getElementById('productContainer');
    productContainer.innerHTML = '';
  
    products.forEach((product) => {
      const productElement = document.createElement('div');
      productElement.classList.add('product');
      productElement.setAttribute('data-product-id', product.productId);
  
      const productImage = document.createElement('img');
      productImage.src = product.image_URL;
      productElement.appendChild(productImage);
  
      const productDetails = document.createElement('div');
      productDetails.classList.add('product-details');
  
      const productName = document.createElement('div');
      productName.classList.add('product-name');
      productName.textContent = product.productName;
      productDetails.appendChild(productName);
  
      const productPrice = document.createElement('div');
      productPrice.classList.add('product-price');
      productPrice.textContent = '$' + product.price;
      productDetails.appendChild(productPrice);

      const productQuantity = document.createElement('div');
      productQuantity.classList.add('product-quantity');
  
      const decreaseButton = document.createElement('button');
      decreaseButton.classList.add('quantity-btn');
      decreaseButton.textContent = '-';
      productQuantity.appendChild(decreaseButton);

      decreaseButton.addEventListener('click', () => {
        const action = decreaseButton.textContent;
        handleQuantityChange(1, action);
        location.reload();
      });
  
      const quantityDisplay = document.createElement('div');
      quantityDisplay.textContent = product.quantity;
      productQuantity.appendChild(quantityDisplay);
  
      const increaseButton = document.createElement('button');
      increaseButton.classList.add('quantity-btn');
      increaseButton.textContent = '+';
      productQuantity.appendChild(increaseButton);

      increaseButton.addEventListener('click', () => {
        const action = increaseButton.textContent;
        handleQuantityChange(1, action);
        location.reload();
      });
  
      productDetails.appendChild(productQuantity);
  
      const removeButton = document.createElement('button');
      removeButton.classList.add('remove')
      removeButton.textContent = 'Remove';
        removeButton.addEventListener('click', () => {
          handleRemoveProduct(1,2);
        });


      productDetails.appendChild(removeButton);
      productElement.appendChild(productDetails);

      productContainer.appendChild(productElement);
    });
  }

  async function handleRemoveProduct(cartId, productId) {
    try {
      const response = await fetch(`http://localhost:8888/carts/${cartId}?productId=${productId}`, {
        method: 'DELETE',
        headers: headers,
      });
  
      if (!response.ok) {
        throw new Error('Failed to remove product.');
      }
  
      const productElement = document.querySelector(`[data-product-id="${productId}"]`);
      productElement.remove();
    } catch (error) {
      console.error('Error removing product:', error);
      // Show an error message to the user if necessary
    }
  }
 
  
  
  async function handleQuantityChange(productId, action) {
    try {
      // Calculate the new quantity based on the action
      const productElement = document.querySelector(`[data-product-id="${productId}"]`);
      const quantityDisplay = productElement.querySelector('.product-quantity div');
      const currentQuantity = parseInt(quantityDisplay.textContent, 10);
  
      let newQuantity;
      if (action === '+' && currentQuantity < 10) {
        newQuantity = currentQuantity + 1;
      } else if (action === '-' && currentQuantity > 1) {
        newQuantity = currentQuantity - 1;
      } else {
        // No change needed, return early
        return;
      }
  // Replace 'UPDATE_CART_QUANTITY_API_ENDPOINT' with the actual API endpoint to update the cart quantity
  const cartId = 1; // Replace with the actual cart ID or fetch it from the API
  const updateQuantityEndpoint = `http://localhost:8888/carts/${cartId}?productId=${productId}&quantity=${newQuantity}`;

  const response = await fetch(updateQuantityEndpoint, {
    method: 'PUT',
    headers: headers,
  });

  if (!response.ok) {
    throw new Error('Failed to update quantity.');
  }

  // Quantity updated successfully in the server, now update the UI
  quantityDisplay.textContent = newQuantity;
} catch (error) {
  console.error('Error updating quantity:', error);
  // Show an error message to the user if necessary
}
}  





// Function to calculate and update subtotal, shipping, and total values
function updateCartSummary(products) {
    let subtotal = 0;
    let shipping = 0;
  
    // Calculate subtotal and shipping based on the products
    products.forEach((product) => {
      subtotal += product.price * product.quantity;
      // Add shipping cost logic if needed
    });
  
    // Update the UI with the calculated values
    const subtotalElement = document.getElementById('subtotal');
    subtotalElement.textContent = '$' + subtotal.toFixed(2);
  
    const shippingElement = document.getElementById('shipping');
    shippingElement.textContent = "Free"
  
    const totalElement = document.getElementById('total');
    const total = subtotal + shipping;
    totalElement.textContent = '$' + total.toFixed(2);
  }
  
  
  
  
   