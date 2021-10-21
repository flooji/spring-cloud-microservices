# spring-microservices
An educative example of spring microservices with two services (one uses a Mongo DB), a discovery and a config server.

The repository could be expanded in the following way:
- Add a scheduler in the birthday service that will retrieve all birthday persons from the DB that celebrate their birthday today and call the notification service.
- Inside the notification service we could create an email (with a Java Email library) every time a new notification is created
- Add security to the Mongo DB and the services
- Add a Spring Cloud service gateway
- Containerize the microservices and servers, make them ready to be deployed to the cloud (pipeline, port configs etc.)
- Add a frontend

Due to lack of time, this has not been implemented yet. :smiley:

This is how an ideal Spring microservice architecture could look like:

<img src="diagram-architecture-microservice.svg" alt="Spring Microservice architecture" width="600"/>

Source: https://spring.io/microservices

## How it works

### Prerequisites
In order to run the app, make sure you have all dependencies installed (```mvn clean install```) and you have a running Mongo DB on port 27017.

**TIP**: Use a Mongo DB docker container - it's super easy (given that you have docker already installed).

**Quick Guide on using a containerized Mongo DB**:
1. Run ```docker pull mongo:latest``` to pull the latest mongo DB image (be careful when using latest - it might not be the actual LATEST image - in this case we don't care)
2. Run ```docker run -d -p 27017:27017 --name birthdaymongodb mongo:latest``` to start a container from the pulled image

To see the running container you may run ```docker container ls```. Don't forget to stop the container once you're done with ```docker container stop birthdaymongodb```.

When starting the application, follow this order:
1. Start discovery server
2. Start config server
3. Start the notification service
4. Start the birthday service

### Create multiple service instances for notification service
To demonstrate the load balancing of the discovery server, you can create multiple notification service instances.
For IntelliJ: Run  / Edit Configuration / Add new Configuration / , add a new notification service configuration with the following override parameters.
```
server.port=8081
service.instance.name=Notification Service Instance 1
```

### Try out the config server
To test the config server run:
http://localhost:8888/config-client-app.properties

To change a config property manually on the fly (during runtime):
Change the property in the config repo (you can make a fork from the existing repo and change the config repo url in the application.properties of the config server) and push your changes to the remote.
Run a POST request to the following endpoint: http://localhost:8080/actuator/refresh. (8080 is where your config client runs).

### Try out the birthday service
To try the birthday service out use either Postman 

[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/12288443-fe2fea9b-2bdd-49d2-bf53-42cf443f9507?action=collection%2Ffork&collection-url=entityId%3D12288443-fe2fea9b-2bdd-49d2-bf53-42cf443f9507%26entityType%3Dcollection%26workspaceId%3D0ec8f1ad-ac66-4e6e-9818-f47a11e49dcd)

or go to the Swagger UI of the API: http://localhost:8080/swagger-ui.html
