# 🛒 TiendaFisio

Guía paso a paso para levantar el entorno de desarrollo del proyecto (Backend en Spring Boot + Frontend en Angular).

---

## 🛠️ 1. Requisitos Previos

### Java 21 (JDK)
Verifica si ya lo tienes instalado abriendo tu terminal (CMD o Git Bash) y ejecutando:
```bash
java -version
javac -version
```
*Si ambos comandos muestran la versión 21, puedes omitir este paso.*
* **Instalación:** Descarga el **Java SE Development Kit 21 (LTS)** [desde la página de Oracle](https://www.oracle.com/java/technologies/downloads/#java21) (Windows x64 Installer `.exe`) e instálalo con la configuración predeterminada.

### Node.js 22 (LTS)
Verifica tu versión actual:
```bash
node -v
npm -v
```
* **Instalación:** Si no lo tienes, descarga la versión LTS para Windows desde [nodejs.org](https://nodejs.org/) e instálalo con la configuración por defecto.

### Angular CLI 21
Comprueba si Angular está instalado:
```bash
ng version
```
* **Instalación:** Si no está instalado, ejecuta el siguiente comando:
```bash
npm install -g @angular/cli@21
```

---

## 🗄️ 2. Configuración de la Base de Datos (MySQL)

> ⚠️ **Importante:** Durante la instalación de MySQL, recuerda bien el usuario y la contraseña de `root`, ya que el backend los necesitará para conectarse.

1. Abre **MySQL Workbench**.
2. Crea la base de datos ejecutando este comando:
   ```sql
   CREATE DATABASE bdfisiohaven;
   ```
3. Abre el archivo `.sql` incluido en el proyecto (ej. `base.sql`).
4. Ejecuta el script para importar las tablas y los datos iniciales de los artículos.

---

## ⚙️ 3. Configuración y Ejecución del Backend (Spring Boot)

1. Abre la carpeta del backend en **Visual Studio Code**.
2. Espera unos segundos a que el editor descargue automáticamente todas las dependencias del proyecto.
3. Configura tus credenciales de base de datos abriendo el archivo:
   `src/main/resources/application-private.properties`
4. Modifica las siguientes propiedades con tus datos locales:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=TU_CONTRASEÑA
   ```
   *No olvides guardar los cambios.*
5. **Para ejecutar el servidor**, tienes dos opciones:
   * **Opción A (Desde VS Code):** Abre el archivo `FisiohavenApplication.java` (ubicado junto a las carpetas `specs`, `services`, `security`, etc.) y ejecuta el método `main` (puedes usar el botón "Run" que aparece sobre la clase).
   * **Opción B (Desde la terminal):** En la raíz del proyecto backend, ejecuta:
     ```bash
     .\mvnw.cmd spring-boot:run
     ```
     *(Nota: No es necesario instalar Maven manualmente, el proyecto utiliza Maven Wrapper).*

---

## 💻 4. Configuración y Ejecución del Frontend (Angular)

1. Abre una nueva terminal.
2. Ingresa a la carpeta del frontend:
   ```bash
   cd fisiohaven-frontend
   ```
3. Instala las dependencias del proyecto:
   ```bash
   npm install
   ```
4. Inicia el servidor de desarrollo:
   ```bash
   ng serve -o
   ```
   *(El flag `-o` abrirá automáticamente la aplicación en tu navegador predeterminado).*

---

## 🚀 5. Accesos del Proyecto

Una vez que ambos servidores estén corriendo, puedes acceder a:
* **Frontend (Interfaz):** [http://localhost:4200](http://localhost:4200)
* **Backend (API):** [http://localhost:8080](http://localhost:8080)

---

## 📝 Notas Finales
*No hice documentación de la API así que confíen nomás xd.*
