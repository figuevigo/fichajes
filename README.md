# Fichajes

El proyecto se ha inicializado con Spring Initializr usando Spring Boot 2.7.7, Java 11, Maven.

La prueba se ha desarrollado sobre IDE Eclipse.
Una vez importaod en Eclipse se puede ejecutar 
Se puede arrancar desde el IDE seleccionando com.jcromero.fichajes.FichajesApplication.java botón dereccho > Run As > Java Application
Alternativamente se puede iniciar desde maven "clean spring-boot:run"

Por defecto se levnata en el puerto 8080.
Se utiliza una BBDD embebida en memoria H2 que se recrea cada vez que se levanta la aplicación.
El esquema lo crea directamente Hibernate en el arranque desde las entidades JPA.

La API REST dispone de los siguientes endpoints:

* GET http://localhost:8080/api/fichajes Recupera todos los fichajes almacenados en la BBDD sin ningún tipo de filtro
* POST http://localhost:8080/api/fichajes Permite importar fichajes con el formato JSon especificado
* GET http://localhost:8080/api/fichajes/{empleado}/semana Recupera una estructura JSon con la información agregada de lso fichajes de la semana en curso.
Por ejemplo http://localhost:8080/api/fichajes/222222222/semana 
* GET http://localhost:8080/api/fichajes/{empleado}/semana/{fecha} Recupera una estructura JSon con la información agregada de lso fichajes de la semana 
que incluye la fecha pasada en formato yyyy-MM-dd


Se ha creado un Test que actua como cliente y nos permite cargar los fichajes de los ficheros de prueba. Si levantamos la aplicación y ejecutamos el test 
com.jcromero.fichajes.client.FichajesRestClient.sendFichajesDesdeFiles como Run As > JUnit Test se cargan los dos ficheros de ejemplo facilitados.
