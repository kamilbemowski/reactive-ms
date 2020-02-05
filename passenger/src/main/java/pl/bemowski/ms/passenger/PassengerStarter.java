package pl.bemowski.ms.passenger;

import io.vertx.core.AbstractVerticle;
import pl.bemowski.ms.database.passanger.PassangerStorage;
import pl.bemowski.ms.passenger.handler.PassengersHandler;

public class PassengerStarter extends AbstractVerticle {

    @Override
    public void start() {
        PassengersHandler passengersHandler = new PassengersHandler(new PassangerStorage());
    }
}
