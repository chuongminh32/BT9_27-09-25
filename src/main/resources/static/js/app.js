const GQL_ENDPOINT = '/graphql';


async function gql(query, variables = {}) {
const res = await fetch(GQL_ENDPOINT, {
method: 'POST',
headers: { 'Content-Type': 'application/json' },
body: JSON.stringify({ query, variables })
});
const json = await res.json();
if (json.errors) {
console.error(json.errors);
alert('GraphQL Error: ' + json.errors[0].message);
}
return json.data;
}


async function loadCategories() {
const q = `query { allCategories { id name } }`;
const data = await gql(q);
const sel = document.getElementById('categorySelect');
sel.innerHTML = '';
data.allCategories.forEach(c => {
const opt = document.createElement('option');
opt.value = c.id;
opt.textContent = `${c.id} - ${c.name}`;
sel.appendChild(opt);
});
}


function renderProducts(list) {
const container = document.getElementById('result');
container.innerHTML = '';
list.forEach(p => {
const col = document.createElement('div');
col.className = 'col-md-4';
col.innerHTML = `
<div class="card h-100">
<div class="card-body">
<h5 class="card-title">${p.title} (#${p.id})</h5>
<p class="card-text mb-1">Price: ${p.price}</p>
<p class="card-text mb-1">Qty: ${p.quantity ?? ''}</p>
<p class="card-text">${p.desc ?? ''}</p>
<small class="text-muted">Category: ${p.category?.name ?? ''}</small>
</div>
</div>`;
container.appendChild(col);
});
}


async function allProducts() {
const q = `query { allProducts { id title price quantity desc category { id name } } }`;
const data = await gql(q);
renderProducts(data.allProducts);
}


async function productsByPriceAsc() {
const q = `query { productsByPriceAsc { id title price quantity desc category { id name } } }`;
const data = await gql(q);
renderProducts(data.productsByPriceAsc);
}