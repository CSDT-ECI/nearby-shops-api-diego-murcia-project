# Introducción
Como se ha mencionado en las entregas anteriores que se encuentran en el
[Proyecto](https://github.com/CSDT-ECI/nearby-shops-api-diego-murcia-project) acerca de la deuda técnica
existente, para esta primera entrega se aplicaran algunas de las técnicas de refactorización y limpieza de código para
la reducción de la deuda técnica.

## Análisis de Deuda Técnica
Con ayuda de la herramienta [SonarQube](https://www.sonarqube.org/) se ha realizado un análisis estático del código para
identificar los problemas de calidad de código y deuda técnica. A continuación se presentan los resultados obtenidos:

![Analisis Inicial](/images/sonarqube/sonarqube-01.png)

Como se ha mencionado en las entregas anteriores. La deuda técnica de este proyecto es grande y la herramienta de
[SonarQube](https://www.sonarqube.org/) nos confirma esto. 

Aquí se puede ver que, en cuanto a calidad de código y testing, el proyecto tiene una gran falencia. Comenzado porque
hay un alto porcentaje de código duplicado, una cantidad considerablemente alta de brechas de seguridad y una nula
cobertura de pruebas unitarias.

## Reducción de Deuda Técnica
Una vez identificado los problemas anteriormente mencionados, se tomaron las siguientes acciones:
1. Utilización de la herramienta [SonarLint](https://www.sonarsource.com/products/sonarlint/) para la identificación y
   corrección rápida de todos los issues reportados por la herramienta de [SonarQube](https://www.sonarqube.org/) en el
   proyecto actual.


2. Utilización de la herramienta [GitHub Copilot](https://github.com/features/copilot) para la eficiencia en la
   escritura de código y la corrección de errores.


3. Creación del primer microservicio que sera refactorizado y limpiado siguiendo los principios de *Clean Code* y *Clean
   Architecture*. Este microservicio sera el encargado de manejar la autenticación de los usuarios en la aplicación y
   sera llamado [nearbyshops-user-auth](https://github.com/CSDT-ECI/nearbyshops-user-auth).

Adicionalmente, en este proyecto se hicieron algunas correciones de las mencionadas por la herramienta de **SonarQube**,
como se puede ver en los archivos modificados del proyecto. Esto con ayuda de las herramientas de AI nombradas en la
sección anterior.

![SonarLint](/images/sonarqube/sonarqube-02.png)

## Consideraciones Finales
Con las acciones tomadas anteriormente, debido a la gigantesca deuda técnica que tiene el proyecto, la cantidad de
efuerzo necesario para reducir la mayor parte de los issues reportados por **SonarQube** es bastante alto.

Adicionalmente, con la ausencia de pruebas unitarias, el modificar algo da temor como desarrollador ya que no se
garantiza que el comportamiento de las funcionalidades presentes en el proyecto se mantengan.

Por un lado, no se presentan bugs a la hora de usar la aplicación, pero por otro lado, el garantizar que no se vayan a
presentar algunos bugs unas vez se tome como accion principal la separación del monolito es completamente imposible.

Como desarrollador, al ver este proyecto y realizar los analisis que se han venido haciendo a lo largo del curso veo la
importancia de mantener una deuda tecnica casi nula en cada proyecto que se trabaje para garantizar la mantenibilidad
de los mismos y evitar situaciones como la que estoy viviendo con la reduccion de deuda tecnica en este proyecto.

Gracias a las herramientas de AI y a las herramientas de analisis de codigo estatico, logre reducir un poco la deuda
tecnica en este proyecto.

![Analisis Final](/images/sonarqube/sonarqube-03.png)

Pero como he mencionado en varias oportunidades, el separar este monolito en microservicios es la opcion mas viable para
la reduccion de todos los problemas que se viene presentando en el proyecto. En terminos de tiempo de esfuerzo esto sera
bastante, pero en terminos de mantenibilidad y escalabilidad del proyecto, sera lo mejor que se puede hacer.
