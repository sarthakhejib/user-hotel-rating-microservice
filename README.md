This project have mainly 3 micorservices: **UserSevice, HotelSevice and RatingSevice**. Below are the details:


**UserService:**

UserService have all the CRUD endpoints. This stores User details like UserId, Name and Email. Further,
UserService have other functionalities like it interacts with RatingService and HotelService for fetching Ratings, a particular User has given to a Hotel.


**HotelService:**

HotelService has Create, Get and Get All endpoints. This stores Hotel details like HotelId, Name, About, Location. 

**RatingService:**

RatingService has Create, Get and Get All endpoints. This stores Rating details like RatingId, UserId, HotelId, Ratings(Out of 10), Feedback and Hotel.  


**Microservice Pattern Involved in this Project:**

**_1. Database per Service:_**

There are different databases for different microservices. Each microservice has different tables as well.

**_2. Service Discovery/Registry:_**

We have used Eureka for Service Registry. The ServiceRegistry microservice is registered as a Eureka Server and all other micoroservices as registered as Eureka Client.
We can see status of all the microservice and their respective instances. Check Below image 👇🏻 :

![image](https://github.com/user-attachments/assets/3067a6a2-350e-4c72-a00b-a7bf069c10cc)

**_3. API Gateway Pattern:_**

API Gateway is also integrated in this project. This helps in Centeralizing all the HTTP request targeted to any microservice. That means to get data from any microservice we don't need to 
remember the ports or the URLs.

**_4. Config Server:_**

Config Server helps to Externalize any common config which is used by microservices. We can externalize the configuration to GitHub, Hashicorp etc. We have use GitHub(microservice-config) repository.
ConfigServer microservice is registered as a Config Server while all the other microservice are Config Client.

**_5. Microservice Communication:_**

We have used RestTemplate as well as Feign Client to communicate from one microservice to other by calling their respective endpoints.

**_6. Fault Tolerance:_**

For Fault Tolerance we have used Resilience4j in the project. Which helps to configure the project with:
Circuit Breaker, Rate Limiter, Retry etc. All these functionalies have been implemeneted. 


Thanks for reading ❤️ !!
