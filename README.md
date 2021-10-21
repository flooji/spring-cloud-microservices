# spring-microservices

## How it works
When running the services, make sure to start the discovery server first. 

### Try out the config server
To test the config server run:
http://localhost:8888/config-client-app.properties

To change a config property manually on the fly (during runtime):
Change the property in the config repo and push your changes to the remote.
Run a POST request to the following endpoint: http://localhost:8080/actuator/refresh. (8080 is where your config client runs).

### Try the birthday service out
To try the birthday service out use either Postman 

[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/12288443-fe2fea9b-2bdd-49d2-bf53-42cf443f9507?action=collection%2Ffork&collection-url=entityId%3D12288443-fe2fea9b-2bdd-49d2-bf53-42cf443f9507%26entityType%3Dcollection%26workspaceId%3D0ec8f1ad-ac66-4e6e-9818-f47a11e49dcd)

or go to the Swagger UI of the API: http://localhost:8080/swagger-ui.html
