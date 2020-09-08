order-service

spring boot rest api 

After the starting application the api automatically imports relational data to database for creating order. 

This application has dev, test and prod eviroment configuration file.
dev and test eviroment uses h2 in memory database.
prod enviroment configured for mysql 8.0.21

How to run this application?

  Docker
  
    - $ docker build -f DockerFile -t orderservice .
    
    - $ docker run -p 8080:8080 orderservice
    
  Or run as a typically java application
and post your json order request data to http://localhost:8080/order/create-order
  
example order request:

{
	"customerId":"1",
	"basketId":"1",
	"deliveryAddress":"Rüzgar Sokak. Kadıköy/Istanbul",
	"deliveryDate":"2021-01-01T10:15:30+01:00"
}

example response : 

{
    "status": "OK",
    "data": {
        "id": 2,
        "customerId": 1,
        "deliveryAddress": "Rüzgar Sokak. Kadıköy/Istanbul",
        "deliveryDate": "2021-01-01T10:15:30+01:00",
        "lineItems": [
            {
                "id": 1,
                "productId": 1,
                "amount": 3,
                "price": 30.0
            },
            {
                "id": 2,
                "productId": 2,
                "amount": 2,
                "price": 40.0
            }
        ],
        "orderTotal": 70.0,
        "orderStatus": "INITIAL"
    },
    "errMsg": null
}
