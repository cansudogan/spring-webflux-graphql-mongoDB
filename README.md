# Spring Webflux Graphql MongoDB

## GraphQL Endpoint

` http://localhost:8080/graphiql `

- Sign in with admin credentials: username: `admin`, password: `test1234` (credentials can be changed in application.properties)

- To test add the following queries into the left hand pane.

##### Get All Users
```
query
{
    getAll {
    firstName
    lastName
    userName
    password
    email
    }
}
```
<img width="1438" alt="Screen Shot 2021-08-16 at 01 13 52" src="https://user-images.githubusercontent.com/21179912/129494341-952b4b80-03b0-404f-b9ca-cf6f7544b085.png">


##### Get By UserName
```
query
{
    getByUserName(userName: "Patton Down DeHatches") {
        firstName
        lastName
        userName
        password
        email
  }
}
```
<img width="1438" alt="Screen Shot 2021-08-16 at 01 12 13" src="https://user-images.githubusercontent.com/21179912/129494326-c38fc468-b25f-451d-b815-a3615951ff94.png">

##### Generate User Token
```
mutation ($userName: String, $password: String) 
{
    authenticateUser(
        userName: $userName, 
        password: $password)
}
```
- Query variables
```
{
  "userName": "Patton Down DeHatches",
  "password": "DmFPPuM9"
}
```

<img width="1438" alt="Screen Shot 2021-08-16 at 01 11 28" src="https://user-images.githubusercontent.com/21179912/129494314-09fc134d-8fa8-423b-a9fc-9f4f01373e3c.png">

- If the userName or password does not match with database it wont generate a user token

<img width="1438" alt="Screen Shot 2021-08-16 at 01 11 42" src="https://user-images.githubusercontent.com/21179912/129494322-6f6053e4-eaa0-478b-ae09-f95749609b4d.png">
