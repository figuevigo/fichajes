# Fichajes

El proyecto se ha inicializado con Spring Initializr usando Spring Boot 2.7.7, Java 11, Maven.

La prueba se ha desarrollado sobre IDE Eclipse.
Una vez importado como proyecto Maven en Eclipse (u otro IDE) se puede construir y/o ejecutar. 
Se puede arrancar desde el IDE seleccionando com.jcromero.fichajes.FichajesApplication.java botón dereccho > Run As > Java Application
Alternativamente se puede iniciar desde maven "clean spring-boot:run"

Por defecto se levanta en el puerto 8080.
Se utiliza una BBDD embebida en memoria H2 que se recrea cada vez que se levanta la aplicación.
El esquema lo crea directamente Hibernate en el arranque desde las entidades JPA.

La API REST dispone de los siguientes endpoints:

* GET http://localhost:8080/api/fichajes Recupera todos los fichajes almacenados en la BBDD sin ningún tipo de filtro
* POST http://localhost:8080/api/fichajes Permite importar fichajes con el formato JSon especificado
* GET http://localhost:8080/api/fichajes/{empleado}/semana Recupera una estructura JSon con la información agregada de los fichajes de la semana en curso.
Por ejemplo http://localhost:8080/api/fichajes/222222222/semana devuelve la estructura de una Semana como la composición de 5 objetos Jornada. En este caso todas 
las horas están a null porque para la semana en curso no tiene ningún fichaje.
```
{
    "jornadasSemana": {
        "2023-01-20": {
            "fecha": "2023-01-20",
            "horaInicio": null,
            "horaInicioDescanso": null,
            "horaFinDescanso": null,
            "horaFinManhana": null,
            "horaInicioTarde": null,
            "horaFin": null,
            "correcta": false,
            "completa": false
        },
        "2023-01-19": {
            "fecha": "2023-01-19",
            "horaInicio": null,
            "horaInicioDescanso": null,
            "horaFinDescanso": null,
            "horaFinManhana": null,
            "horaInicioTarde": null,
            "horaFin": null,
            "correcta": false,
            "completa": false
        },
        "2023-01-18": {
            "fecha": "2023-01-18",
            "horaInicio": null,
            "horaInicioDescanso": null,
            "horaFinDescanso": null,
            "horaFinManhana": null,
            "horaInicioTarde": null,
            "horaFin": null,
            "correcta": false,
            "completa": false
        },
        "2023-01-17": {
            "fecha": "2023-01-17",
            "horaInicio": null,
            "horaInicioDescanso": null,
            "horaFinDescanso": null,
            "horaFinManhana": null,
            "horaInicioTarde": null,
            "horaFin": null,
            "correcta": false,
            "completa": false
        },
        "2023-01-16": {
            "fecha": "2023-01-16",
            "horaInicio": null,
            "horaInicioDescanso": null,
            "horaFinDescanso": null,
            "horaFinManhana": null,
            "horaInicioTarde": null,
            "horaFin": null,
            "correcta": false,
            "completa": false
        }
    },
    "fechaInicio": "2023-01-16T00:00:00Z",
    "fechaFin": "2023-01-20T23:59:59.999Z"
}
```
* GET http://localhost:8080/api/fichajes/{empleado}/semana/{fecha} Recupera una estructura JSon con la información agregada de los fichajes de la semana de un empleado 
que incluye la fecha pasada en formato yyyy-MM-dd.
Por ejemplo http://localhost:8080/api/fichajes/222222222/semana/2018-01-01 
```
{
    "jornadasSemana": {
        "2018-01-05": {
            "fecha": "2018-01-05",
            "horaInicio": "08:00:00",
            "horaInicioDescanso": null,
            "horaFinDescanso": "10:45:00",
            "horaFinManhana": "13:30:00",
            "horaInicioTarde": "15:00:00",
            "horaFin": "18:00:00",
            "correcta": false,
            "completa": false
        },
        "2018-01-04": {
            "fecha": "2018-01-04",
            "horaInicio": "08:00:00",
            "horaInicioDescanso": "10:30:00",
            "horaFinDescanso": "10:45:00",
            "horaFinManhana": "13:30:00",
            "horaInicioTarde": "15:00:00",
            "horaFin": "18:00:00",
            "correcta": true,
            "completa": true
        },
        "2018-01-03": {
            "fecha": "2018-01-03",
            "horaInicio": "08:00:00",
            "horaInicioDescanso": "10:30:00",
            "horaFinDescanso": "10:45:00",
            "horaFinManhana": "13:30:00",
            "horaInicioTarde": "15:00:00",
            "horaFin": "18:00:00",
            "correcta": true,
            "completa": true
        },
        "2018-01-02": {
            "fecha": "2018-01-02",
            "horaInicio": "08:00:00",
            "horaInicioDescanso": "10:30:00",
            "horaFinDescanso": "10:45:00",
            "horaFinManhana": "13:30:00",
            "horaInicioTarde": "15:00:00",
            "horaFin": "18:00:00",
            "correcta": true,
            "completa": true
        },
        "2018-01-01": {
            "fecha": "2018-01-01",
            "horaInicio": "08:00:00",
            "horaInicioDescanso": null,
            "horaFinDescanso": "10:45:00",
            "horaFinManhana": "13:30:00",
            "horaInicioTarde": "15:00:00",
            "horaFin": "18:00:00",
            "correcta": false,
            "completa": false
        }
    },
    "fechaInicio": "2018-01-01T00:00:00Z",
    "fechaFin": "2018-01-05T23:59:59.999Z"
}
```


Se ha creado un Test que actua como cliente y nos permite cargar los fichajes de los ficheros de prueba. Si levantamos la aplicación y ejecutamos el test 
com.jcromero.fichajes.client.FichajesRestClient.sendFichajesDesdeFiles como Run As > JUnit Test se cargan los dos ficheros de ejemplo facilitados.
