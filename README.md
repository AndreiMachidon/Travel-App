# Travel-App

***This is the backend service for a travel application built during a Hackathon***.

## Authentication and Security

The Security of the application uses ***Spring Security*** and ***Jwt authentication***. 
It uses a ***role-based authentication*** model for accessibility to endpoints.

### POST 
/registerNewUser -> create a new account
<br />
<br />
/registerNewUsers -> endpoint for creating multiple accounts at once(mostly for testing)
<br />
<br />
/authenticate -> login the user ( it creates automatically a Jwt Token which will need to be sent for future requests)

## Travel app endpoints

### POST
/hotels/search -> search for hotels based on criteria like location, start date, end date, number of persons and hotel's availability)
<br />
<br />
/hotels/add -> adds hotel to the database (accessible only to admin)
<br /><br />
/hotels/book/{id} -> books an hotel which the user chose
<br />
<br />
/flights/search -> search for flights based on date and maximum price of the ticket




