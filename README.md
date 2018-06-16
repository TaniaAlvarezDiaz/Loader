[![Build Status](https://travis-ci.com/TaniaAlvarezDiaz/Loader.svg?token=ENc151Ahc3Y3oqzaSf7S&branch=master)](https://travis-ci.com/TaniaAlvarezDiaz/Loader)
[![codecov](https://codecov.io/gh/TaniaAlvarezDiaz/Loader/branch/master/graph/badge.svg?token=q8lncSfYmK)](https://codecov.io/gh/TaniaAlvarezDiaz/Loader)

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

Es recomendable incluir la ruta completa de los archivos.

El .jar generado se encontrará dentro de la carpeta "target" del proyecto.

El excel con los datos de carga se encontrará en "src/main/resources/datosACargar.xlsx"

  - java -jar "ruta .jar generado" "ruta del excel de carga"

# Test

Windows:
  - mvn test
 
Otros:

  - /"rutamaven"/bin/mvn.cmd test
