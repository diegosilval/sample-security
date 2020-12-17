# Ejemplo de JWT

## Iniciando sesion

```
curl --location --request POST 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"Juan",
    "password":"Perez"
}'
```

Obtiene un token. Ese token estará en la cabecera de peticiones

## Invocando peticiones
### Petición no asegurada
```
curl --location --request GET 'http://localhost:8080/hola/1'
```
### Petición asegurada
```
curl --location --request GET 'http://localhost:8080/auth/secure' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJ2YXNzbGF0YW0iLCJzdWIiOiJKdWFuOlBlcmV6IiwiYXV0aG9yaXRpZXMiOlsiVVNFUjEiLCJVU0VSMiJdLCJpYXQiOjE2MDgxNjk3MTIsImV4cCI6MTYwODE3MDMxMn0.pDAV8uLs6jf1G1aa9SuzFP1q7xcCLQ82miAykS6wYi0m4YV8wgfIFufXupZ1zHv12DiVJb62i1nqdKv4BM0-_Q' \
--header 'Cookie: JSESSIONID=11C36DF3F6D684F8809964083AB2CB27'
```