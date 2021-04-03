you just need to create a DataBase with name "jwt" (you don't need to create any tables in it)

Then open your mysqlworkbench or php/admin and you will see a users table created under jwt database;
Add your username and password to the "users" column in the db. Becuase you need already user details to perform authentication

Then start the server and use postman to:

Make a post request to localhost:8082/authenticate and pass in the form-data "username" and "password" stored in the users table 

{
"username":"justice",
"password": "justice"
}

//replace all above "justice" with the  details saved in db

When you hit sent a jwt-token will be generated for you.

Next:

copy the token and make a get request to:
localhost:8082 OR Localhost:8082/home By passing in an Authorization Bearer token in the headers. 

You can contact me here for more guide: https://tutorialscamp.com/ https://successismoney.com
