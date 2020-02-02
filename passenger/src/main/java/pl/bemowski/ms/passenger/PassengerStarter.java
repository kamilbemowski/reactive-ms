package pl.bemowski.ms.passenger;

import io.vertx.core.AbstractVerticle;
import pl.bemowski.ms.common.model.EventBusAddresses;
import pl.bemowski.ms.database.passanger.PassangerStorage;
import pl.bemowski.ms.passenger.handler.PassengersHandler;

public class PassengerStarter extends AbstractVerticle {

    @Override
    public void start() {
        PassengersHandler passengersHandler = new PassengersHandler(new PassangerStorage());
        vertx.eventBus()
                .consumer(EventBusAddresses.PASSENGERS, passengersHandler::handle);
    }
}
