# Introducción
Este proyecto es una API que permite, en terminos generales, encontrar restaurantes cercanos.

Este proyecto sera usado a lo largo del curso de **Calidad de Software y Gestion de Deuda Tecnica** para ser analizado y
poner en practica todos los conceptos que sean vistos a lo largo del curso.

El proyecto esta construido con las siguientes tecnologias:
- Java 8
- Spring Boot - Maven
- PostgreSQL

# Code Smells y Refactoring
## Code Smells
### 1. Codigo Comentado
En varios puntos del proyecto se puede encontrar codigo comentado que no aporta nada el proyecto, no aporta una
funcionalidad nueva o algo por el estilo. A continuacion se veran algunos de estos **Code Smells** identificados.

#### 1.1. Clase `DAOAddBalance`
![Code Smell 01](images/code-smells/commented-code-01.png)

![Code Smell 02](images/code-smells/commented-code-02.png)

#### 1.2. Clase `CartItemService`
![Code Smell 03](images/code-smells/commented-code-03.png)

![Code Smell 04](images/code-smells/commented-code-04.png)

#### 1.3. Clase `CartStatsDAO`
![Code Smell 05](images/code-smells/commented-code-05.png)

#### 1.4. Clase `DeliveryAddressService`
![Code Smell 06](images/code-smells/commented-code-06.png)

### 2. Funciones largas y Funciones con muchos parametros/funciones
A lo largo del proyecto se evidencian multiples metodos/funciones que son muy largas, o poseen muchos metodos o tienen
mas de una sola funcion que va en contra de algunos de los principios *SOLID* y *OOP*.

#### 2.1 Clase `DeliveryAddressService`
![Code Smell 07](images/code-smells/functions-01.png)

#### 2.2 Clase `FavouriteShopResource`
![Code Smell 08](images/code-smells/functions-02.png)

#### 2.4 Clase `ShopItemByShopDAO`
![Code Smell 09](images/code-smells/functions-03.png)

## Tecnicas de Refactoring
### 1. Clean Architecture
Se propone la implementacion de una arquitectura limpia para una mejor organizacion de paquetes y entendimiento del 
proyecto.

![Refactoring 01](images/refactoring/refactoring-01.png)

Se propone re-organizar todo en la siguiente estructura siguiendo los principios de *Arquitectura Hexagonal*:
``` shell
.
|- src
   |- main
        |- java
             |- org.nearbyshops
                |- adapters
                   |- in
                   |- out
                |- application
                   |- services
                   |- ports
                      |- in
                      |- out
                |- domain
                |- Main.java
```

### 2. Eliminar elementos no usados
Los siguientes metodos y funciones son aquellos que no aperantan tener un uso deprecado:

#### 2.1 Clases
- `ItemBrand`

#### 2.2 Metodos
- `MetodogetTimestampHDDelivered`
- `getDeliveryGuyData`
- `getReasonCancelledByShop`
- `getReasonForOrderReturned`
- `getRt_delivery_guy_profile`

### 3. Separacion de responsabilidades
Se propone la separacion de responsabilidades en todos los paquetes *DAO* ya que estos cuentan tanto con logica como
con logica de acceso a datos, que siguiendo un esquema de *Clean Architecture*, para temas relacionados con escalabilidad
y mantenibilidad estos accesos deben estar separados y conectados mediante interfaces.

# Clean Code y Practicas XP
## Clean Code
Al realizar el analisis del proyecto base se identificaron que algunos desarrollos estan cumpliendo las siguientes
caracteristicas de Clean Code:
- **Legible**: El codigo es facil de leer y entender.
- **Reutilizable**: El codigo es facil de reutilizar.
- **Testeable**: El codigo es facil de probar.
- **Simple**: El codigo es simple.
- **Nombrado**: El codigo tiene nombres descriptivos.

Algunas de las caracteristicas que no cumple el codigo son:
- **Mantenible**: El codigo es facil de mantener y cambiar.
- **Eficiente**: El codigo es eficiente.
- **Elegante**: El codigo es elegante.
- **Intuitivo**: El codigo es intuitivo.
- **Documentado**: El codigo esta documentado.
- **SOLID**: El codigo cumple con los principios SOLID.

Para realizar mejoras en el proyecto para cumplir con la mayor parte de las caracteristicas de Clean Code se propone
tomar las siguientes acciones:
1. **Dividir responsabilidades**

   Tomando como ejemplo el metodo `add_balance_to_shop` en la clase `DAOAddBalacnce`, las acciones a tomar deben ser
   las siguientes:
    - Cambiar el nombre del metodo a `addBalanceToShop`.
    - Dividir la consulta a base de datos de la logica de negocio y de los controladores. Es decir, montar todo en la
      siguiente estructura:
      ```shell
        .
        ├── adapters
        │   ├── in
        │   │   ├── controllers
        │   │   │   ├── ShopController.java
        │   ├── out
        │   │   ├── entities
        │   │   │   ├── ShopBalanceEntity.java
        │   │   ├── repositories
        │   │   │   ├── ShopBalanceRepository.java
        │   │   ├── adapters
        │   │   │   ├── ShopBalanceAdapterImpl.java
        ├── application
        │   ├── ports
        │   │   ├── in
        │   │   │   ├── BalanceService.java
        │   │   ├── out
        │   │   │   ├── ShopBalanceAdapter.java
        │   ├── services
        │   │   ├── BalanceServiceImpl.java
        ├── domain
        │   ├── ShopBalance.java
      ```
      Esto hara que el codigo sea mas mantenible, testeable y reutilizable, haciendo que los metodos utilizados sean
      mas atomicos y se puedan reutilizar en otros metodos.


2. **Aplicar principios SOLID**

   Algunos metodos y clases no cumplen con los principios SOLID, no existe una division de responsabilidades clara, no
   existe una inyeccion de dependencias que permita, por ejemplo, hacer un cambio de base de datos sin afectar el
   funcionamiento del sistema. Por lo tanto, se propone aplicar los principios SOLID en el proyecto.


3. **Nombrar correctamente las variables y metodos**

   Algunos metodos y variables no tienen nombres descriptivos, por lo que se propone cambiar los nombres de las
   variables y metodos para que sean mas descriptivos. Adicionalmente, no se usa el standard de camelCase para los
   nombres de las variables y metodos, por lo que se propone cambiar el standard de nombres a camelCase.

## Practicas XP
Para la mejora de este proyecto se propone aplicar las siguientes *Practicas XP*:
1. **Test Driven Development (TDD)**

   Hay una aucencia de pruebas unitarias en el proyecto. Esto implica que no se garantizaria que en una refactorizacion
   del codigo no se rompa el funcionamiento del sistema. Por lo tanto, se propone aplicar TDD en el proyecto para que
   a la hora de un refactoring primero se realicen pruebas unitarias de como esta funcionando todo y luego si se haga
   el refactoring para garantizar que lo modificado no afecte el funcionamiento y el flujo del sistema.


2. **Coding Standars**

   No se esta siguiendo un standard de codificacion en el proyecto. Por lo tanto, se propone aplicar un standard de
   codificacion en el proyecto para que el codigo sea mas legible y entendible. Entre estos, se recomienda manejar una
   nomenclarura de camelCase para los nombres de las variables y metodos. Adicionalmente, se recomienda un mejor manejo
   de las funcionalidades globales del proyecto. Adicionalmente, junto con los estandares de codificacion, tambien se
   recomienda aplicar un standard de commits y ramas para una organizacion de equipo optima. Utilizar las convenciones
   de GitFlow para el manejo de ramas y commits.


3. **Simple Design**

   Este proyecto no posee un diseño simple. Los directorios estan desordenados y tiene muchas responsabilidades lo
   cual puede llegar a ser confuso a la hora de agregar nuevas funcionalidades. Por lo tanto, se recomienda adoptar en
   primer lugar una arquitectura orientada a microservicios, y en segundo lugar, una arquitectura limpia (arquitectura
   hexagonal) para que el proyecto sea mas simple y facil de entender.
