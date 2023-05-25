document.addEventListener('DOMContentLoaded', () => {
  const productList = document.getElementById('product-list');
  const detailsSection = document.getElementById('details-section');
  const nameInput = document.getElementById('name-input');
  const priceInput = document.getElementById('price-input');
  const addButton = document.getElementById('add-button');
  const searchButton = document.getElementById('search-button');

   // ダミーデータ
   const products = [
    { id: 1, name: 'Product 1', price: 10 },
    { id: 2, name: 'Product 2', price: 20 },
    { id: 3, name: 'Product 3', price: 30 }
  ];

  // リストの表示
  function renderProductList() {
    productList.innerHTML = '';
    fetch('/products')
    .then(res => { //RestControllerから受け取った値->res(成功/200 失敗/400)
      if(res.status === 400) {
        console.log('no')
      } else {
        //.jsonは非同期処理(.jsonの受け取り)
        res.json()
        .then(data => { //.jsonで受け取った値->data
          data.forEach(product => {
            const li = document.createElement('li');
            li.textContent = `${product.id} - ${product.name} - ${product.price}`;
            li.addEventListener('click', () => showProductDetails(product));
            productList.appendChild(li);
            console.log(`id = ${product.id}`)
          });
        })
      }
  });
  }


// 詳細情報の表示
  function showProductDetails(product) {
    detailsSection.innerHTML = `
          <h2>Product Details</h2>
          <input type="text" id="edit-name-input" value="${product.name}">
          <input type="number" id="edit-price-input" value="${product.price}">
          <button id="update-button">Update</button>
          <button id="delete-button">Delete</button>
        `;

        const updateButton = document.getElementById('update-button');
        const deleteButton = document.getElementById('delete-button');

        updateButton.addEventListener('click', () => updateProduct(product.id));
        deleteButton.addEventListener('click', () => deleteProduct(product.id));

        detailsSection.style.display = 'block';
      }

      // 商品の更新
      function updateProduct(productId) {
        const name = document.getElementById('edit-name-input').value;
        const price = document.getElementById('edit-price-input').value;

        const data = {id: productId, name: name, price: price};
        console.log(data);

        fetch('/product', {
           method: 'PUT',
           headers: {
             'Content-Type': 'application/json',
            },
          body: JSON.stringify(data),
        })
        .then((response) => {
          if (response.ok) {
            console.log('PUT request processed');
            renderProductList();
          } else {
            console.error('PUT request failed');
          }
        })
        .catch((error) => {
          console.error('Error:', error);
        });
        hideDetailsSection();
      }

      // 商品の削除
      function deleteProduct(productId) {
        const name = document.getElementById('edit-name-input').value;
        const price = document.getElementById('edit-price-input').value;

        const data = {id: productId, name: name, price: price};
        console.log(data);

       fetch('/product', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
         },
       body: JSON.stringify(data),
     })
         .then((response) => {
           if (response.ok) {
             console.log('DELETE request processed');
             renderProductList();
           } else {
             console.error('DELETE request failed');
           }
         })
         .catch((error) => {
           console.error('Error:', error);
         });

        hideDetailsSection();
      }

      // 詳細情報の非表示化
      function hideDetailsSection() {
        detailsSection.style.display = 'none';
      }

      // 新規商品の追加
      addButton.addEventListener('click', () => {
        const name = nameInput.value;
        const price = priceInput.value;

        const data = {id: 1, name: name, price: price};
        console.log(data);
        
        fetch('/product', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(data),
        })
        .then((response) => {
          if (response.ok) {
            console.log('POST request processed');
            renderProductList();
          } else {
            console.error('POST request failed');
          }
        })
        .catch((error) => {
          console.error('Error:', error);
        });

        // 入力フィールドをクリアする
        nameInput.value = '';
        priceInput.value = '';
      });

      //商品の検索
      searchButton.addEventListener('click', () =>{
        const searchId = document.getElementById('id-search').value
        fetch(`/product?searchId=${searchId}`)
        .then(res => { //RestControllerから受け取った値->res(成功/200 失敗/400)
          if(res.status === 400) {
            console.log('no')
          } else {
            res.json()
            .then(data => {
              console.log(data);
              showProductDetails(data)
            })
          }
      });
      })


      // 初期表示
      renderProductList();
    });

