# poo_ifood_project

This is a project of the discipline of Principle of Object Orientation, it consists of a project with functionalities inspired by iFood to put into practice the concepts learned during the course, concepts such as Inheritance, Composition, Polymorphism, Interfaces ..., as a challenge I set myself I will apply several concepts that I use in other languages, below is a summary of the applied techniques.

## For this project the concepts of Clean Architecture were used, which consists of:
#### <a href=“https://github.com/Flutterando/Clean-Dart/blob/master/README_en.md“>(explanation taken from Flutterando's Clean Dart repository)</a>

### Presenter
The Presenter layer is responsible to declare the I/O and the interactions of the application.

If we take Flutter as an example, this layer would contain the Widgets, Pages and the State Management. On the other hand, if we were dealing with the backend, this layer would be where we would have the Handlers or Commands of our API.

### Domain
The Domain layer will contain our core business rules (entity) and application-specific business rules (usecases).

Our entities must be simple objects, that may or not have validation rules for its data through functions or ValueObjects. The entity must not depend on any object of the other layers.

The usecases must run the necessary logic to solve a specific problem. If the usecase needs the any external access, this access may be done through interface contacts that will be implemented by the lower-level layers.

The Domain must be responsible only for the execution of the business rules. It must not have any other object implementations, like repositories or services.

Taking a repository as example, we will have only the interface contract to this repository. The implementation of this contract must be done by a lower-level layer.

### Infrastructure (Infra)
This layer supports the Domain layer by implementing its interfaces. To do this, it have to adapt the external data so that it fullfill the domain contracts.

This layer will, probably, have the implementation for some repository or service interface that can't depend on external data, like an API, or the access to some hardware, like a Bluetooth device.

For the repository to be able to process and adapt the external data we must create contracts for these services, aiming to defer the implementation responsibility to a lower-level layer in our architecture.

Our suggestion is to create DataSource object when we want to access external data, that is, for example, a BaaS like Firebase or a SQLite-based local cache. Another suggestion is to create Driver objects to interface the communication between your application and some device hardware.

The external accesses like data sources and drivers must be implemented by another layer, leaving only the interface contracts in this layer.

### External
Here we implement the external accesses that depends on a hardware, package or highly-specific access.

Basically, the External layer must contain everything that is expected to be highly volatile and constantly changed.

In Flutter, for instance, we use shared_preferences for local cache. However, it may be that, in a later stage of the project, shared_preferences won't be able to meet the requirements of our application and we will want to replace it with another package, like hive. When this happens, all we need to do is to implement, using the logic inherent to hive, a new instance of the contract that the infrastructure layer expects.

Another pragmatic example would be to think in a login system based on Firebase Auth. Another product, however, want to use other authentication provider. To make this substitution it would be as simple as implementing a data source based on this new provider and "invert the dependency", using this implementation instead of the Firebase one's when need.

The data sources must only worry about discovering the external data and sending it to the infra layer, where they will be dealt.

Likewise, the drivers objects must only provide the device hardware info that is required by the contract, and not deal with anything else.


### Dependency Injection (DI) && Service Locator (SL) 
The Dependency and Localized Service injection system of instances was also used to create and inject the class ics into the classes that depend on these instances;

### Singleton
Anti Pattern Singleton was also used to maintain only one instance of certain classes throughout the entire application.

### S.O.L.I.D
It is a set of five principles of good practice for maintaining a maintainable code.
#### <a href=“https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898“>(More info here)</a>

### Modules
The system of modules was used to divide the code into mini-programs, separating the features and making the code more modular, making maintenance and organization easier.

### Tests
Part of the code has test coverage to ensure operation

### Clean Code
Clean code techniques were also applied to make the code more readable, with variable names and methods explaining its functionality in the code.

### Next steps
- Replace mocked external with database integration
- Adding new features
- Create graphical interface
...
