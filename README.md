# spring-microservices

## How it works
When running the services, make sure to start the discovery server first. 

To test the config server run:
http://localhost:8888/config-client-app.properties

To change a config property manually on the fly (during runtime):
Change the property in the config repo and push your changes to the remote.
Run a POST request to the following endpoint: http://localhost:8080/actuator/refresh. (8080 is where your config client runs).
