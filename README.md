<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proyecto Delichi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            color: #333;
        }
        h1, h2, h3 {
            margin-top: 0;
        }
        pre {
            background-color: #f4f4f4;
            padding: 10px;
            border-radius: 5px;
            overflow-x: auto;
        }
        code {
            font-family: Consolas, monospace;
        }
    </style>
</head>
<body>
    <h1>Proyecto Delichi</h1>

    <p>¡Bienvenido al repositorio del proyecto Delichi! Este proyecto es una aplicación web desarrollada en Spring Boot que facilita la gestión de restaurantes, reservas y usuarios.</p>

    <h2>Instalación</h2>

    <p>Sigue estos pasos para configurar y ejecutar el proyecto en tu entorno local:</p>

    <h3>Requisitos previos</h3>

    <p>Asegúrate de tener instalados los siguientes requisitos previos en tu sistema:</p>

    <ul>
        <li><strong>Java Development Kit (JDK) 11 o superior:</strong> <a href="https://adoptopenjdk.net/">Descargar JDK</a></li>
        <li><strong>Apache Maven:</strong> <a href="https://maven.apache.org/download.cgi">Descargar Apache Maven</a></li>
        <li><strong>Git:</strong> <a href="https://git-scm.com/downloads">Descargar Git</a></li>
        <li><strong>IDE de tu preferencia</strong> (por ejemplo, IntelliJ IDEA, Eclipse)</li>
    </ul>

    <h3>Clonar el repositorio</h3>

    <p>Abre tu terminal o línea de comandos y ejecuta el siguiente comando para clonar este repositorio:</p>

    <pre><code>git clone https://github.com/tu-usuario/delichi-proyecto.git</code></pre>

    <h3>Importar el proyecto</h3>

    <p>Abre tu IDE y selecciona la opción para importar un proyecto Maven. Navega hasta el directorio donde clonaste el repositorio y selecciona el archivo <code>pom.xml</code>.</p>

    <h3>Configuración de la base de datos</h3>

    <p>El proyecto utiliza una base de datos MySQL. Asegúrate de tener MySQL instalado en tu sistema.</p>

    <ol>
        <li>Crea una base de datos en MySQL con el nombre <code>delichi</code>.</li>
        <li>Configura las credenciales de la base de datos en el archivo <code>application.properties</code> ubicado en <code>src/main/resources</code>. Aquí está un ejemplo de configuración:</li>
    </ol>

    <pre><code>spring.datasource.url=jdbc:mysql://localhost:3306/delichi
spring.datasource.username=tu-usuario
spring.datasource.password=Tu-Contraseña</code></pre>

    <h3>Ejecutar la aplicación</h3>

    <p>Una vez que hayas configurado la base de datos, puedes ejecutar la aplicación desde tu IDE o utilizando Maven. Desde la línea de comandos, navega hasta el directorio raíz del proyecto y ejecuta el siguiente comando:</p>

    <pre><code>mvn spring-boot:run</code></pre>

    <p>La aplicación estará disponible en <code>http://localhost:8080</code>.</p>

    <h2>Contribuciones</h2>

    <p>¡Las contribuciones son bienvenidas! Si deseas contribuir a este proyecto, por favor sigue estos pasos:</p>

    <ol>
        <li>Haz un fork del proyecto.</li>
        <li>Crea una nueva rama (<code>git checkout -b feature/nueva-caracteristica</code>).</li>
        <li>Haz tus cambios y haz commits (<code>git commit -am 'Añadir nueva característica'</code>).</li>
        <li>Sube tus cambios al repositorio (<code>git push origin feature/nueva-caracteristica</code>).</li>
        <li>Crea un nuevo pull request.</li>
    </ol>

    <h2>Licencia</h2>

    <p>Este proyecto está bajo la Licencia MIT. Para más detalles, por favor consulta el archivo <code>LICENSE</code>.</p>
</body>
</html>
