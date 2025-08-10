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

***

## Delploy en Railway

La siguiente aplicación se encuentra cargada gracias a **Railway**, un servicio de host en la nube que permite crear varios servicios web de forma rápida y segura. El link para acceder a la aplicación es:

[LINK]

### Procedimiento para despliegue en Railway

Para ello se separaron en dos repositorios distintos los microservicios de productos y categorías, respectivamente:

**Products:** https://github.com/devdiagon/Product-API

**Categories:** https://github.com/devdiagon/Category-API

1. Crear una base de datos MySQL en Railway, se lo genera con un solo clic 
2. Levantar el servicio de la API de categorías en base al respostiorio de GitHub
   
Para este paso es crucial suministrar las varaibles de entorno: ``DB_DATABASE``, ``DB_HOST``, ``DB_PASSWORD``, ``DB_PORT``, ``DB_USER``. Esto en base a la configuración dada por Railway.

3. Levantar el servicio de la API de productos en base al repositorio de GitHub

Para este paso es crucial suministrar las mismas variables de entorno: ``DB_DATABASE``, ``DB_HOST``, ``DB_PASSWORD``, ``DB_PORT``, ``DB_USER``, adiconaolmente la variable ``CATEGORIES_URL``, que será la URL del servicio desplegado de la API de categorías dado por Railway.

4. Levantar el servicio del Frontend, para ello se lo puede iniciar con la [imagen v0.0.2 de docker hub](https://hub.docker.com/repository/docker/fredericktm/app-front/general), donde se le tiene que especificar las variables de entorno en el arranque: ``CATEGORIES_SERVICE_URL``, ``PRODUCTS_SERVICE_URL`` con las URL de ambos servicios dadas por Railway.

> Aunuqe no se realiza un Deploy orquestado por Docker Compose, la configuración de todos los servicios mediante Railway resulta muy sencilla porque tanto el backend como el frontend se encuentran completamente Dockerizados medainte un DockerFile.
