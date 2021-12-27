# MiniMint Authentication REST API version 0.1

## How to Use

* copy application.properties from resources/templates to resources and adjust to match your mysql username and password
* create a database called minimint_authentication in mysql
* run AuthorizationApplication. By default API will be accessible on port 10001.

## User

### //server:port/user

- Register a new User.

#### Input: (post user)

```
{
    "userEmail": "test@example.com",
    "username": "tmt",
    "userPassword": "password"
}
```

- Returns a User.

#### Output:

```    
{
    "userId": 1,
    "userEmail": "test@example.com",
    "username": "tmt"
}
```

### //server:port/user/email

- Find a user by Email Address.

#### Input: (post email)

```
test@example.com
```

- Returns a User

#### Output:

```    
{
    "userId": 53,
    "userEmail": "test@example.com",
    "userName": "Testy McTestface"
}
```

### //server:port/user/id/{userId}

- Find a user by UserId

#### Input: (get)

```
get call to //server:port/user/id/52
```

- Returns a User

#### Output:

```    
{
    "userId": 52,
    "userEmail": "test@example.com",
    "username": "Testy2"
}
```

### //server:port/user/username/{username}

- Find a user by username

#### Input: (get)

```
get call to //server:port/user/username/Testy2
```

- Returns a User

#### Output:

```    
{
    "userId": 52,
    "userEmail": "test@example.com",
    "username": "Testy2"
}
```

### //server:port/user

- Update a User's username, email and password.

#### Input: (put User)

```
{
    "userId": 1,
    "userEmail": "newEmail@example.com",
    "username": "newUsername",
    "userPassword": "newPassword"
}
```

- Returns the update User.

#### Output:

```
{
    "userId": 1,
    "userEmail": "newEmail@example.com",
    "username": "newUsername"
}
```

### //server:port/user/login

- Log a User in.

#### Input: (post User)

```
{
    "username": "Testy2",
    "userEmail": "test@example.com",
    "userPassword": "password"
}
```

- Returns a User.

#### Output:

```    
{
    "userId": 52,
    "userEmail": "test@example.com",
    "username": "Testy2"
}
```

