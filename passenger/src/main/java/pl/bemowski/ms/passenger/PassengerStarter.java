package pl.bemowski.ms.passenger;

import io.vertx.core.AbstractVerticle;
import pl.bemowski.ms.passenger.handler.PassengersHandler;

public class PassengerStarter extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        PassengersHandler passengersHandler = new PassengersHandler(vertx);
        vertx.eventBus()
                .consumer("passengers", passengersHandler::handle);
    }
}
