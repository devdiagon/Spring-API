# SPRING - API

El siguiente proyecto contiene un entorno desarrollado con Angular y Nginx para el frontend, mientras que para el backend se implementaron 2 microservicios con SpringBoot usando la base de datos de MySQL. Todo el entorno está dockerizado y se lo ejecuta mediante Docker Compose.

### Consideraciones

Al levantar el siguiente proyecto, es necesario considerar tener libres los puertos para cada servicio:

| Servicio         | Puerto local | Descripción                         |
| ---------------- | ------------ | ----------------------------------- |
| Frontend (Nginx) | `8083`       | Angular frontend                    |
| API Productos    | `8081`       | API Spring Boot de productos        |
| API Categorías   | `8082`       | API Spring Boot de categorías       |
| MySQL DB         | `3306`       | Base de datos MySQL                 |

*Importante mencionar que internamente en Docker se crea una red llamada "api-net"*

### Levantar el entorno

Se ubica en la ruta raiz del proyecto y en la terminal se ejecuta:

```bash
docker compose up -d
````

Tardará un tiempo hasta que se obtengan las imágenes para cada servicio y se lo levante completamente.

### Acceso a la aplicación

Al levantar la aplicación, se podrá evidenciar su funcionalidad a través de las siguientes rutas:

* Frontend: http://localhost:8083
* API Productos: http://localhost:8081/api/products
* API Categorías: http://localhost:8082/api/categories

***

Finalmente si se desea acabar con la ejecución actual se puede usar el comando:

```bash
docker compose down
````
