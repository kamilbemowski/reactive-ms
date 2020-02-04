# Project title

This project simply example of the event-driven microservice architecture. Main library used for the project is vert.x

## Documentation

To find out how to use this library follow [Documentation](http://vertx.io).

## Installation

You can install it with Maven:

```
mvn clean install
```
In the repository you could find run configurations which could be used in intellij idea.

For another IDE here is example of configuration for runs:

Main Class: io.vertx.core.Launcher

program arguments: run pl.bemowski.ms.flight.FlightRunner -cluster

To run multiple applications at once please install `multirun` plugin in the intellij.
## Features

* router which receive events and send to event bus
* couple of microservices which consume events


## Contribute

* Source Code: https://github.com/kamilbemowski/reactive-ms
* Tests: not yet :)

## Author

Kamil Bemowski
