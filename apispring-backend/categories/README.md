# Category-API

El siguiente proyecto está desarrollado en SpringBoot y ha sido Dockerizado. Siguiendo las instrucciones que se muestran a continuación, podrá realizar peticiones HTTP para las operaciones CRUD básicas hacia un modelo de **"categorias"** en la ruta: 

http://localhost:8082/api/categories

*NOTA: esta ruta es referencial, pues el puerto puede ser diferente según la configuración realizada*

***
## Red de Docker

Puesto que se va a estar ejecutando un contenedor con la base de datos y otro con la aplicación, estos se tienen que comunicar bajo una misma red. Para ello se crea la siguiente red en Docker en la terminal:

```bash
docker network create api-net
```
***
## Base de datos MySQL

Para usar esta aplicación es necesario primero generar un contenedor con una imagen de MySQL. Para ello se tiene que ejecutar el siguiente comando en la terminal:

```bash
docker run --name sprgFT-db -e MYSQL_ROOT_PASSWORD=admin123 -e MYSQL_DATABASE=apispring -p 3306:3306 -d --network api-net --rm mysql
```
**Impoortante**

+ El nombre del contenedor es ``sprgFT-db``
+ El nombre de la base de datos es ``apispring``
+ El puerto interno del contenedor de la base de datos es el ``3306``, cambiar el puerto local en caso de ser necesario.
+ El usuario y contraseña para acceder a la base de datos es ``root`` y ``admin123`` respectivamente

***
## Inicializar imagen de Docker

Una vez se tenga el contenedor de MySQL levantado, se procede a ejecutar la aplicación.

La imagen de este proyecto se encuentra en DockerHub, se la puede descargar e inicializar direcamente a través del siguiente comando en la terminal:

```bash
docker run -dit --name c-api-categoryFT --network api-net -p 8082:8002 -e PORT=8002 -e DB_HOST=sprgFT-db:3306 --rm fredericktm/img-api-categories-ft
```

**Impoortante**

+ Internamente el contenedor corre por defecto en el puerto ``8082``, es posible que pueda cambiar el valor de esta variable de entorno ``PORT`` por otro como en este caso, el ``8002``
+ Cambiar el puerto local del contenedor en caso de ser necesario, en el comando está para ejecutarse en el puerto ``8082``
+ Internamente se está referenciando al contenedor de la base de datos MySQL que se levantó anteriormente (``sprgFT-db``), para este caso se tiene que tener configurado el puerto ``3306`` internamente en el contenedor