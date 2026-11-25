## Spring security JWT
User creation & login together with JWT token bearer.

### Program flow
- Create user on endpoint: "/register".
  - username and encoded password are stored in 'user' table.
- Login user on endpoint: "/login".
  - login using JSON object with username and password.
- Hit "/product" endpoint to show that JWT is working on logged in user.
  - in Postman, the JWT token is stored in variable jwtToken.

## Database connection
MySQL, JPA, hibernate.<br>
'user' table created where user id, username and encrypted/hashed
password is stored. 

User can create a new user hitting the '/register' API.
User can login using username & password hitting the '/login' API.

<span style="color:white">––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––</span>

## Security Configuration
- Custom securityFilterChain in WebSecurityConfig allowing everyone to hit
"/register" and "/login" API's.

- CustomUserDetailsService defines how a user is fetched from DB.<br>
  CustomUserDetailsService is using UserDetail defining a users details.

- BCryptPasswordEncoder @Bean

- AuthenticationProvider:<br> 
  Authentication using CustomUserDetailsService & CustomUserDetails.
  DaoAuthenticationProvider is doing the actual validation for username & password.
  BCryptPasswordEncoder encodes and validates password.

<span style="color:white">––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––</span>

## Login flow
### classes:
- UserController
- UserService
- WebSecurityConfig
- CustomUserDetailsService

Credentials passing through AuthenticationFilter on to
ProviderManager of type AuthenticationManager (polymorphism).
ProviderManager/AuthenticationManager iterates through providers
to find a provider that support the current type of login procedure.
ProviderManager finds the correct AuthenticationProvider
suitable for current login procedure, for example username & password.
AuthenticationProvider is implemented and Spring Boot catches this
specific login procedure.

authenticationConfiguration.getAuthenticationManager() returns a ProviderManager
containing the DaoAuthenticationProvider which matches the hashed password with
PasswordEncoder using matches() and User Details Service fetches UserDetail from
database with loadByUsername().

<span style="color:white">––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––</span>

## JWT - Jason Web Token
When a user is validated and logged in, the user is returned a JWT token.

JWT token is created with header, payload and secret key/signature in JwtService.

User authentication can be done using the JWT token.

<b>JWT secret</b><br>
Generate secret key in terminal:
````
 openssl rand -base64 32
````

<span style="color:lightseagreen">
The secret key is set as environment variable.
</span>
<br>
SecretKey is fetched in application.properties:

````
jwt.secret=${JWT_SECRET}
````


### Automate JWT token in Postman
An environment is created in Postman where a variable ```jwtToken``` is storing the token.
A script (scripts tab) in "/login" endpoint:
````
const token = pm.response.text().trim();

pm.environment.set("jwtToken", token);

console.log("Saved token:", pm.environment.get("jwtToken"));
````
assigns the variabel the returned JWT token.

In "/product" endpoint under authorization -> Bearer Token the variable ``` {{jwtToken}} ``` is used.


Login using JSON:
```
{
    "username": "*****",
    "password": "*****"
}
```


Alternatively:
In Postman (or the like), login with JSON object as "/login" API takes @RequestBody User user,
not as basic auth.
Copy returned token, hit "/product" API. Paste in Header Key = Authorization, Value = Bearer token.


### JwtAuthenticationFilter
JwtAuthenticationFilter added to be used before Username&UsernamePasswordAuthenticationFilter.class filter:
In WebSecurityConfig and SecurityFilterChain:
```
.addFilterBefore(jwtAuthenticationFilter,
    UsernamePasswordAuthenticationFilter.class);
```

Add filter JwtAuthenticationFilter in package 'config'. JwtAuthenticationFilter
makes Spring Security check for JWT authentication instead of asking for
username and password for every request.

<span style="color:white">––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––</span>

<br><br>
<span style="color:#E76F00"> Color </span>
<br><br>
<span style="color:orange">Color</span>
<br><br>
🔴🟠🟡🟢🔵🟣🟤⚫⚪






