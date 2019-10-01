# Project2-Samuel-Chris-Kyle-BackEnd

# API info
_**Swagger has been integrated. You can use Swagger for a cleaner outlook on the API. host:Port/gamesgalore/swagger-ui.html#/**_
_**You cannot modify the id(primary key) of any objects directly through the api. This can only be done through the database.**_

All POST requests that require a body take an **array** of the specified object. This makes it so you can post multiple objects at once

# Login
To login you must make a POST request and pass your accountUsername and accountPassword as parameters. If the request is successful, then you will receieve a JWT in the __*Authorization*__ header. When making any authorized requests to the API endpoints, you must provide the this token in the __*Authorization*__ header preceeded by the word Bearer since represents the Bearer authentication scheme. 

Ex:
host:Port/gamesgalore/login?accountPassword=password&accountUsername=username

### Header to be sent to authroize requests
Authorization: Bearer randomtokenpreviouslyreceieved

# Create an account
To create a user account make a POST request to endpoint /accounts.
From there you need to include a Account body with a nested user body as its field/attribute and a nested role body to assign the role.
Validations are run on username, password, user first name, user last name, user email and the role MUST EXIST already in the database, 
so you are simply referencing/assigning a role.

_**confirmed password currently does nothing. It is in place in case we want server side password confirmation
   Current roles in the database are ADMIN and USER**_

Ex:
```javascript
[{
    "accountUsername": "username",
    "accountPassword": "password",
    "confirmPassword": "confirmed password",
    "accountUser": {
        "userFirstName": "first name",
        "userLastName": "last name",
        "userEmail": "email"
    },
    "genrePreferences": [{
        "genreName": "Horror"
    }],
    "platformPreferences": [{
        "platformName": "PS4"
    }]
}]
```
notes: 
_**You cannot create a user directly since we are enforcing creating an account with a user simultaneously.
Deleting a user will result in the account being deleted and the same vice versa.**_

# Update Account
To update an account make a PUT request to endpoint /accounts/{id}
To update the user you must make an explicit PUT request to the /users/{id} endpoint.

Ex:
```javascript
{
    "accountUsername": "samueldorilas2",
    "accountPassword": "Pass123123",
    "confirmPassword": null,
    "accountRole": {
        "roleId": 2
    },
    "genrePreferences": [
        {
            "genreId": 2,
            "genreName": "Actionx"
        }
    ],
    "platformPreferences": [
        {
            "platformId": 2,
            "platformName": "XBOXx"
        }
    ]
}

```
# GET Accounts
You can pass two optional params to get accounts
accountUsername and/or accountRoleName

Ex:
 host:Port/gamesgalore/accounts?accountUsername=kylereimer
 host:Port/gamesgalore/accounts?accountRoleName=ADMIN
 host:Port/gamesgalore/accounts?accountUsername=kylereimer&accountRoleName=ADMIN

### Note: There is no POST method for a USER. You can create a user when you create an account.
# Update User

Ex:
```javascript
{
    "userFirstName": "first name",
    "userLastName": "last name",
    "userEmail": "email"
}
```

# GET Users
You can pass three optional params to get users
userFirstName, userLastName and/or userEmail

Ex:
host:Port/gamesgalore/users?userFirstName=Christopher
host:Port/gamesgalore/users?userLastName=Reimer
host:Port/gamesgalore/users?userEmail=christophercoleman@outlook.com
host:Port/gamesgalore/users?userFirstName=Samuel&userLastName=Dorilas&userEmail=samueldorilas@outlook.com

# Basic PUT and POST
/roles
```javascript
[
   {
       "roleName": "USER"
   }
]
```
/platforms
```javascript
[
    {
        "platformName": "Wiiu"
    }
]
```
/genres
```javascript
[
    {
        "genreName": "Comedy"
    }
]
```
/games
```javascript
[
    {
        "gameName": "Prototype"
    }
]
```

# Basic GET
You can pass one optional parameter to get roles, platforms, genres, and games

Ex:
host:Port/gamesgalore/roles?roleName=ADMIN
host:Port/gamesgalore/platforms?platformName=XBOX
host:Port/gamesgalore/genres?genreName=Horror
host:Port/gamesgalore/games?gameName=For Honor

# Available endpoints

### Login
* /login POST

### Users
* /users GET
* /users/{id} PUT, DELETE

### Roles
* /roles GET, POST
* /roles/{id} PUT, DELETE

### Accounts
* /accounts GET, POST
* /accounts/{id} PUT, DELETE

### Games
* /games GET, POST
* /games/{id} PUT, DELETE

### Platforms
* /platforms GET, POST
* /platforms/{id} PUT, DELETE

### Genres
* /genres GET, POST
* /genres PUT, DELETE

# Security
When you first create an account, it will be granted the authority "USER". To get "ADMIN" privileges, the authority must be changed explicitly from the database. There may be future implementation that allows "ADMIN" to change a user role through an API request.

### Admin 
* /games, /platforms, /genres, /accounts, /users, /roles <br/>
GET, POST, PUT, DELETE - AUTHORITY('ADMIN')

### USER 
* /accounts, /users <br/>
GET, PUT, DELETE - AUTHORITY('USER') if the account belongs to the requestor 

### No authority required
* /login, /accounts <br/>
POST AUTHORITY(Not Required)
* /games, /platforms, /genres <br/>
GET - AUTHORITY(Not Required)