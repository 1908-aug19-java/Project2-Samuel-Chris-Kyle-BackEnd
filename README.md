# Project2-Samuel-Chris-Kyle-BackEnd

# API info
_**You cannot modify the id(primary key) of any objects directly through the api. This can only be done through the database.**_

All POST requests take an **array** of the specified object. This makes it so you can post multiple objects at once

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
    "accountRole": {
        "roleName": "Role"
    }
}]
```
notes: 
_**You cannot create a user directly since we are enforcing creating an account with a user simultaneously.
Deleting a user will result in the account being deleted and the same vice versa.**_

# Update Account
To update an account make a PUT request to endpoint /accounts/{id}
From there you include ONLY the contents of the account object and/or the nested role object. To update the user you must make an explicit PUT request
to the /users/{id} endpoint.

Only the fields added will be updated. So you can choose to omit unnecessary fields from the request body
Ex:
```javascript
{
    "accountUsername": "username",
    "accountPassword": "password",
    "confirmPassword": "confirmed password",
    "accountRole": {
        "roleName": "Role"
    }
}
```
Or
```javascript
{ 
    "accountRole": {
        "roleId": 2
    }
}
```
# GET Accounts
You can pass two optional params to get accounts
accountUsername and/or accountRoleName

Ex:
 host/gamesgalore/accounts?accountUsername=kylereimer
 host/gamesgalore/accounts?accountRoleName=ADMIN
 host/gamesgalore/accounts?accountUsername=kylereimer&accountRoleName=ADMIN

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
host/gamesgalore/users?userFirstName=Christopher
host/gamesgalore/users?userLastName=Reimer
host/gamesgalore/users?userEmail=christophercoleman@outlook.com
host/gamesgalore/users?userFirstName=Samuel&userLastName=Dorilas&userEmail=samueldorilas@outlook.com

# Create and Update Role
```javascript
[{
    "roleName": "USER"
}]
```
# Get Role
You can pass one optional parameter to get roles

Ex:
host:8080/gamesgalore/roles?roleName=ADMIN

# Available endpoints
### Users
* /users GET
* /users/{id} PUT, DELETE

### Roles
* /roles GET, POST
* /roles/{id} PUT, DELETE

### Accounts
* /accounts GET, POST
* /accounts/{id} PUT, DELETE
