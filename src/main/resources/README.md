
## 🧪 Test nhanh GraphQL
- Endpoint: `http://localhost:8080/graphql`
- Dùng Postman/Altair:
```graphql
# 1) Sản phẩm giá tăng dần
query { productsByPriceAsc { id title price } }

# 2) Sản phẩm theo category
query { productsByCategory(categoryId: 1) { id title price category { id name } } }

# 3) CRUD ví dụ
mutation { createUser(input:{fullname:"New User", email:"new@ex.com", password:"123", phone:"090", categoryIds:[1,2]}){ id fullname email categories{ id name } } }
```
http://localhost:8080/graphiql?path=/graphql
query {
productsByPriceAsc { id title price category { id name } }
}

query {
productsByCategory(categoryId: 2) { id title price category { name } }
}
mutation {
createUser(fullname:"David", email:"david@mail.com", password:"123", phone:"0909") { id fullname email }
}