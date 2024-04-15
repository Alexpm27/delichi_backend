Proyecto Delichi
¡Bienvenido al repositorio del proyecto Delichi! Este proyecto es una aplicación web desarrollada en Spring Boot que facilita la gestión de restaurantes, reservas y usuarios.

Instalación
Sigue estos pasos para configurar y ejecutar el proyecto en tu entorno local:

Requisitos previos
Asegúrate de tener instalados los siguientes requisitos previos en tu sistema:

Java Development Kit (JDK) 11 o superior: Descargar JDK
Apache Maven: Descargar Apache Maven
Git: Descargar Git
IDE de tu preferencia (por ejemplo, IntelliJ IDEA, Eclipse)
Clonar el repositorio
Abre tu terminal o línea de comandos y ejecuta el siguiente comando para clonar este repositorio:

bash
git clone https://github.com/tu-usuario/delichi-proyecto.git
Importar el proyecto
Abre tu IDE y selecciona la opción para importar un proyecto Maven. Navega hasta el directorio donde clonaste el repositorio y selecciona el archivo pom.xml.

Configuración de la base de datos
El proyecto utiliza una base de datos MySQL. Asegúrate de tener MySQL instalado en tu sistema.

Crea una base de datos en MySQL con el nombre delichi.
Configura las credenciales de la base de datos en el archivo application.properties ubicado en src/main/resources. Aquí está un ejemplo de configuración:
properties
spring.datasource.url=jdbc:mysql://localhost:3306/delichi
spring.datasource.username=tu-usuario
spring.datasource.password=Tu-Contraseña
Ejecutar la aplicación
Una vez que hayas configurado la base de datos, puedes ejecutar la aplicación desde tu IDE o utilizando Maven. Desde la línea de comandos, navega hasta el directorio raíz del proyecto y ejecuta el siguiente comando:

bash
mvn spring-boot:run
La aplicación estará disponible en http://localhost:8080.

Licencia
Este proyecto está bajo la Licencia MIT. Para más detalles, por favor consulta el archivo LICENSE.

