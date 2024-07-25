# Mini Documentation

* Only the backend server (API) for now

## Part I
---
### `/books`

- Params : `category , lang , year , page , size`

#### Client

![d-000](https://github.com/user-attachments/assets/b9ad7359-dfd2-4e04-97f1-b67b06353962)

![d-001](https://github.com/user-attachments/assets/8de5a441-883f-4017-9736-af2cb6867ba3)

- Exceeding page limit

![d-002](https://github.com/user-attachments/assets/e627f421-72d8-49ad-bfd1-7937d3df4b33)

#### API

* Example : `/books?year=2000&page=2&size=10`
![d-003](https://github.com/user-attachments/assets/332c67a3-bf66-4151-a6d6-7b709b477837)

### `/authors`

`/authors/{author-ID}`

### `/search`

- Params : `keyword , type , size , page`

#### Client
![d-004](https://github.com/user-attachments/assets/4670f140-841d-4c49-af49-952a90cd382d)

#### API

* Example : Size = 10 and no type is specified, the API divides the result into 3 parts, resulting in an approximate final size of ~10
![d-005](https://github.com/user-attachments/assets/e25b8310-56bc-4f2f-9386-dfbc9c881f90)

---

## Part II

### `/login`

![d-006](https://github.com/user-attachments/assets/8ba84631-3ffe-41f4-8062-0981c31af055)

* If the credentials were correct, the server sends an `Authorization Token` to the client's browser, granting him access to the admin dashboard
* The Logout button delete the website token
* From a security point of view, the user should be able to know the token lifetime (in some applications, that can be necessary) but cannot impose a new lifetime by modifying it, here we allow that for debugging purposes

![d-007](https://github.com/user-attachments/assets/be46d42f-969f-488f-a37b-a6af2695019e)
* Adding a Book
![d-008](https://github.com/user-attachments/assets/7c7da15b-7303-4609-b1c0-0d4fac8b8dab)
![d-009](https://github.com/user-attachments/assets/2d56df93-2c79-4e67-9f38-8f8c5a63814c)

```bash
curl -X POST \
-H "Authorization: XXXX" \
-H "Content-Type: application/json" \
-d '{
"idBook":null,
"idCategory":5,
"idAuthor":726,
"rating":1,
"price":150,
"bookimage":null,
"title": "Ktab 1059",
"publicationYear":2010,
"nbPages":101,
"language":"Urdu"
}' \
http://localhost:8080/REST_API_FIRST_war_exploded/api/v1/books/add
```

* Deleting an Author and his books

![d-010](https://github.com/user-attachments/assets/4f8d3eea-00fd-485d-a615-eca7eea79453)

![d-011](https://github.com/user-attachments/assets/77fabeb4-2dc0-44da-b5d8-3da0d2f5491c)

```bash
curl -X POST \
-H "Authorization: XXXX" \
-H "Content-Type: application/json" \
-d '26' \
http://localhost:8080/REST_API_FIRST_war_exploded/api/v1/authors/delete
```
