[![Build Status](https://travis-ci.org/TaniaAlvarezDiaz/Loader.svg?branch=master)](https://travis-ci.com/TaniaAlvarezDiaz/Loader)

# Loader
Módulo para cargar los datos de los agentes que podrán enviar incidencias al sistema.

# Skeleton authors 

Daniel Alba Muñiz

José Luis Bugallo González

Ignacio Escribano Burgos

Daniel Duque Barrientos

Rubén de la Varga Cabero

# Author

Tania Álvarez Díaz ([@TaniaAlvarezDiaz](https://github.com/TaniaAlvarezDiaz))

# Compile

Ejecutar una base de datos relacional, en este caso será HSQLDB.
En la carpeta del proyecto ejecutar las siguientes instrucciones dependiendo del sistema operativo.

Windows:
  - mvn compile
  - mvn package
 
Otros:
  - /"rutamaven"/bin/mvn.cmd compile
  - /"rutamaven"/bin/mvn.cmd package
  
# Execute

Es recomendable incluir la ruta completa de los archivos:

  - java -jar "jar generado" "ruta del excel de carga"

# Test

Windows:
  - mvn test
 
Otros:

  - /"rutamaven"/bin/mvn.cmd test
