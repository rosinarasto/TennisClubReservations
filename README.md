# TennisClubReservations
Build and run
Download maven and run ```mvn clean install spring-boot:run```

which runs the whole application and the web server is accessible on ```http://localhost:8080```

# How to activate data initialization
To trigger data initialization you must change the app configuration in ```/src/main/resources/application.properties```; set the ```data.init.enabled``` to true. You might also set the ```spring.jpa.hibernate.ddl-auto``` to create in order to delete all data before starting the application and initialize the app with new data.
