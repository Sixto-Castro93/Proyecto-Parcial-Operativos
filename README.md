# Proyecto-Parcial-Operativos

## TÍTULO:
Key­value store de un solo nodo



## INTEGRANTES:
* Sixto Castro R.
* Jordy Vásquez C.
* Kevin Zambrano C.
* Marlón Espinoza P.




## DESCRIPCIÓN:
El almacenamiento de clave-valor es un tipo de base de datos NoSQL simple que utiliza una matriz asociativa (similar a un diccionario o un hashmap) como el modelo de datos fundamentales, donde cada clave está asociada con un único valor de una colección. Esta relación se conoce como un par clave-valor.

En cada par clave-valor, la clave está representada por una cadena arbitraria como un nombre de archivo, URL o un hash. El valor puede ser cualquier tipo de datos como una imagen, archivo de preferencias de usuario o un documento. El valor se almacena como un dato primitivo que no requiere el modelado de datos o esquema de definición por adelantado.




##METODOLOGÍA
El protocolo de conexión TCP fue implementado con las clases Socket y ServerSocket, las cuales son librerías de Java.

Para el diseño e implementación del key-value store que permita a uno o más clientes conectarse con el key-value store en memoria. Se utilizó una base de datos NoSQL para el almacenamiento de las claves con sus respectivos valores. 

Se implementó un modelo múltiples productores múltiples consumidores en donde se almacenan las peticiones de los clientes en una cola de peticiones (linked list) ya que nos permite manejar peticiones concurrentes y en el orden que van solicitando la conexión. Cuando un cliente se conecta por primera vez al servidor, el productor ubica la petición de este cliente en la cola de petición para que luego el consumidor la atienda creando un hilo hacia el socket de conexión.

La base NoSQL fue implementada mediante una clase que contiene un atributo hashmap para almacenar las claves con sus respectivos valores y contiene métodos synchronized y con candados (wait y notify). A su vez, se encuentra particionado los valores de las claves cuando se presente el caso que este valor sea muy grande con el fin de realizar consultas de manera eficiente.

Los hilos que sirven tanto para el productor y consumidor fueron implementados mediante el ExecutorService donde coloca los hilos en un ‘pool’ para limitar el número de hilos de conexión ya que esto ocasionaría que nuestro proceso ocupe demasiada memoria en un caso que requiera demasiadas conexiones.




##CONCLUSIONES
Un sistema de key-value store permite un fácil acceso a sus registros debido a sus fáciles métodos para accederlos y por la forma eficaz que las claves se encuentran relacionadas con sus valores.

La aplicación de candados para el manejo de condiciones de carrera resultó eficiente para este proyecto ya que solo se trató con la variable que sirve de candado para habilitar o deshabilitar el acceso a un recurso en la sección crítica.
	
El modelo productor-consumidor se adaptó de muy buena forma al proyecto porque facilitó el buen manejo de las peticiones de conexión de los clientes a medida que las iban solicitando y de manera concurrente.
	
La implementación de multi-threading para la asignación de peticiones concurrentes por parte de los clientes administró de manera eficiente las mismas permitiendo simular las condiciones de carrera.
	
Las diferentes librerías de JAVA ayudaron al desarrollo de varias implementaciones en las funcionalidades del proyecto, por ejemplo: thread, protocolos de conexión, diccionarios y hashmaps, herramientas de sincronización; y por parte del IDE, la herramienta de debugging resultó útil para el desarrollo.

