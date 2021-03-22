# MiniStockbitApp

### Preview

A Simple Android Mobile Application which has been implemented using Clean Architecture alongside MVVM design.

### Technologies & Methodologies which used:

- Clean Architecture
- Modularization
- MVVM Pattern
- Coroutines
- LiveData
- Espresso
- Mockk
- Koin

## Clean Architecture
#### Modularization
* __Presentation__: Layer with the Android Framework, the MVVM pattern and the DI module. Depends on domain to access the use cases and on di, to inject dependencies.
* __Domain__: Layer with the business logic. Contains the use cases, in charge of calling the correct repository or data member.
* __Data__: Layer with the responsibility of selecting the proper data source for the domain layer. It contains the implementations of  the repositories declared in the domain layer. It may, for example, check if the data in a database is up to date, and retrieve it from a service if it’s not.
