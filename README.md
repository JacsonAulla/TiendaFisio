# TiendaFisio

1. Instalar Java 21
Verificar instalación

Abrir el Símbolo del sistema (CMD) y ejecutar:

java -version

Luego:

javac -version

Si ambos comandos muestran la versión 21, puedes continuar.

Si no está instalado

Buscar en Google:

Oracle JDK 21

o ingresar a:

https://www.oracle.com/java/technologies/downloads/#java21

Descargar:

Windows
x64 Installer (.exe)
Java SE Development Kit 21 (LTS)

Instalar con la configuración predeterminada.

2. Instalar Node.js
Verificar instalación
node -v
npm -v
Si no está instalado

Buscar:

Node.js 22 LTS

o ingresar a:

https://nodejs.org/

Descargar la versión LTS para Windows e instalarla con la configuración predeterminada.

3. Instalar Angular CLI
Verificar instalación
ng version
Si no está instalado

Ejecutar:

npm install -g @angular/cli@21

Luego verificar nuevamente:

ng version

#MySQL

Durante la instalación, recordar el usuario y la contraseña configurados para root, ya que serán utilizados por el proyecto.

Abrir MySQL Workbench.
Crear una nueva base de datos ejecutando:
CREATE DATABASE bdfisiohaven;
Abrir el archivo SQL incluido junto al proyecto.
Ejecutar el script para importar los datos iniciales de los artículos.


#Configuración del Backend

Abrir el proyecto del backend con  visual.

Esperar a que visual descargue automáticamente todas las dependencias del proyecto.

Luego abrir el archivo:

src/main/resources/application-private.properties

y modificar los datos de conexión a MySQL según la configuración local:

spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA

Guardar los cambios.

#Ejecutar el Backend

Desde visual ejecutar la clase principal del proyecto (la clase que contiene el método main y la anotación @SpringBootApplication)(es el archivo FisiohavenApplication.java que esta junto a las carpetas specs, services, security, etc del back)

Como alternativa, desde la carpeta raíz del proyecto ejecutar:

mvnw.cmd spring-boot:run

No es necesario instalar Maven, ya que el proyecto utiliza Maven Wrapper.

#Ejecutar el Frontend

Abrir una nueva terminal.

Ingresar a la carpeta del frontend:( es con cd y nombre de la carpeta)

cd fisiohaven-frontend

Instalar las dependencias:

npm install

Iniciar el servidor de desarrollo:

ng serve (o puedes usar "ng serve -o" para que te lleve directo a la pagina)

#Acceso al Proyecto

Frontend:

http://localhost:4200

Backend:

http://localhost:8080

#nota

no hice documentacion de la api asi que confien nomas xd
